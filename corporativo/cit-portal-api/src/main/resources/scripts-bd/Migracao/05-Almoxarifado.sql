--===========================================================================--
-- ##INI :: IMPORTACAO TABELAS DE MOVIMENTO DE MATERIAS DE CONSUMO           --
--===========================================================================--


BEGIN;  -- #INI :: DEFINE AS UNIDADES ALMOXARIFADO DO ORGAO
    INSERT INTO public.localestoque
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , inativador_id
    , autor_id
    , editor_id
    , organizacao_id
    , almoxarifado_id
    )
    SELECT (row_number() OVER (ORDER BY EA.ESTRUTURAORGANIZACIONAL_ID)) + M.MAX AS id
         , NULL             AS databloqueio
         , NULL             AS datainativo 
         , LOCALTIMESTAMP   AS datacriacao
         , LOCALTIMESTAMP   AS dataedicao
         , 0                AS "version"
         , NULL             AS inativador_id
         , NULL             AS autor_id
         , NULL             AS editor_id
         , 1                AS organizacao_id
         , EA.ESTRUTURAORGANIZACIONAL_ID         AS almoxarifado_id
    FROM ESTRUTURAORGANIZACIONALALMOXARIFADO EA
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.localestoque) M ON 1=1
    WHERE EA.ISALMOXARIFADO IS TRUE;
COMMIT; -- #FIM :: DEFINE AS UNIDADES ALMOXARIFADO DO ORGAO


BEGIN;  -- #INI :: MOVIMENTACAO DE DAS ENTRADAS >> ENTRADA
    /*
        TODAS AS ENTRADAS DO ALMOXARIFADO TEM DT_CONTABIL
    */
    --> REGISTROS DE ENTRADA
    INSERT INTO public.entrada
         (    
    --     id
    --   , databloqueio
    --   , datainativo
           datacriacao
         , dataedicao
         , version
         , codigo
         , codigoasi
         , datacontabil
         , datarecebimento
         , datareferencia
         , valortotalnota
    --   , inativador_id
    --   , autor_id
    --   , editor_id
         , organizacao_id
         , dominiotipoentrada_id
         , dominiotiporecebimento_id
         , fornecedor_id
         )
    SELECT DISTINCT
    --     NEXTVAL('entrada_id_seq')     AS id
    --   , NULL                          AS databloqueio
    --   , NULL                          AS datainativo
           NR.DT_INCLUSAO                AS datacriacao
         , NR.DT_INCLUSAO                AS dataedicao
         , 0                             AS "version"
         , NR.CD_NOTA_REC                AS codigo
         , NR.CD_ALMOXARIFADO||NR.CD_ORGAO||NR.CD_NOTA_REC AS codigoasi
         , NR.DT_CONTABIL                AS datacontabil
         , NR.DT_RECEBIMENTO             AS datarecebimento
         , NR.DT_CONTABIL                AS datareferencia
         , NRT.VL_TOTAL_ENTRADA/100      AS valortotalnota
    --   , NULL                          AS inativador_id
    --   , NULL                          AS autor_id
    --   , NULL                          AS editor_id
         , 1                             AS organizacao_id
         , (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoEntrada' AND CODIGO=1) AS dominiotipoentrada_id
         , CASE 
             WHEN NR.CD_TIPO_MOVIMENTO='001' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoRecebimento' AND CODIGO=1)
             WHEN NR.CD_TIPO_MOVIMENTO='003' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoRecebimento' AND CODIGO=3)
             WHEN NR.CD_TIPO_MOVIMENTO='005' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoRecebimento' AND CODIGO=12)
             WHEN NR.CD_TIPO_MOVIMENTO='013' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoRecebimento' AND CODIGO=10)
             WHEN NR.CD_TIPO_MOVIMENTO='016' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoRecebimento' AND CODIGO=2)
             WHEN NR.CD_TIPO_MOVIMENTO='033' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoRecebimento' AND CODIGO=13)
           END                           AS dominiotiporecebimento_id
         , PAR.ID AS fornecedor_id
    FROM ASIWEB.AX_NOTA_REC NR
    JOIN ( SELECT AX_NOTA_REC_ITEM.CD_ALMOXARIFADO, AX_NOTA_REC_ITEM.CD_ORGAO, AX_NOTA_REC_ITEM.CD_NOTA_REC,
            SUM(AX_NOTA_REC_ITEM.QT_NOTA_REC_ITEM) AS QT_TOTAL_ENTRADA, SUM(AX_NOTA_REC_ITEM.VL_NOTA_REC_ITEM) AS VL_TOTAL_ENTRADA
           FROM ASIWEB.AX_NOTA_REC_ITEM
          GROUP BY AX_NOTA_REC_ITEM.CD_ALMOXARIFADO, AX_NOTA_REC_ITEM.CD_ORGAO, AX_NOTA_REC_ITEM.CD_NOTA_REC) NRT 
    ON NRT.CD_ALMOXARIFADO::TEXT=NR.CD_ALMOXARIFADO::TEXT AND NRT.CD_ORGAO::TEXT=NR.CD_ORGAO::TEXT AND NRT.CD_NOTA_REC::TEXT=NR.CD_NOTA_REC::TEXT
    JOIN ASIWEB.AX_V_AGENTE_ENTRADA AE ON NR.CD_AGENTE_ENTRADA::TEXT=AE.CD_AGENTE::TEXT
    LEFT JOIN PESSOA P ON (P.AGENTE_ID = NR.CD_AGENTE_ENTRADA::numeric)
    LEFT JOIN PARCEIRO PAR ON (PAR.PESSOA_ID = P.ID)    
    WHERE AE.CD_TIPO_AGENTE <> '2'; -- TIPO DE AGENTE <> 2 POIS OS TIPOS 2 SAO URS E DECIDIMOS MIGRAR COMO DEVOLUCOES DEVIDO AO FEEDBACK DO USUARIO

    
    --> REGISTROS DA REFERENCIA NECESSARIA EM ENTRADA DE ALMOXARIFADO
    INSERT INTO public.entradaalmoxarifado
         (
           id
         , notarecebimento
         , almoxarifado_id
         )
    SELECT DISTINCT
           E.ID                    AS id
         , NRI.CD_NOTA_REC::BIGINT AS notarecebimento
         , (SELECT ESTRUTURAORGANIZACIONAL_ID FROM ESTRUTURAORGANIZACIONALALMOXARIFADO WHERE ISALMOXARIFADO IS TRUE LIMIT 1) AS estruturaorganizacional_id -- #REVER
    FROM PUBLIC.ENTRADA E
    INNER JOIN ASIWEB.AX_NOTA_REC_ITEM NRI ON (E.CODIGOASI=NRI.CD_ALMOXARIFADO||NRI.CD_ORGAO||NRI.CD_NOTA_REC);

    /*
     A estratégia usada para incluir os itens, foi agrupá-los por valor unitário para
     bater com o total, pois um dos itens do mesmo material pode ter valor unitario
     diferente por causa do resto de uma dízima periódica.
	 esta dando diferenca entre o total da nota e os itens rever cd_entrada com erro = 2001000186
    */
    --> REGISTROS DOS ITENS DA REFERENCIA NECESSARIA EM ENTRADA DE ALMOXARIFADO
    INSERT INTO public.entradaalmoxarifadoitem
         (
    --     id
    --   , databloqueio
    --   , datainativo
           datacriacao
         , dataedicao
         , version
         , datavalidade
         , numerolote
         , quantidade
         , quantidadedominiotipounidadeentrada
         , quantidadesaldo
    --   , valorsobra
         , valortotal
         , valorunitario
    --   , inativador_id
    --   , autor_id
    --   , editor_id
         , entrada_id
    --   , entradainativo_id
         , material_id
         , unidademedidaentrada_id
         )
    SELECT DISTINCT
    --     NEXTVAL('entradapatrimonioitem_id_seq')  AS id
    --   , NULL                                     AS databloqueio
    --   , NULL                                     AS datainativo
           E.DATACRIACAO                            AS datacriacao
         , E.DATAEDICAO                             AS dataedicao
         , 0                                        AS "version"
         , I.DT_VALIDADE                            AS datavalidade
         , I.NR_LOTE                                AS numerolote
         , I.QT_NOTA_REC_ITEM                       AS quantidade
         , I.QT_NOTA_REC_ITEM                       AS quantidadedominiotipounidadeentrada
         , I.QT_SALDO_ITEM                          AS quantidadesaldo
    --   , NULL                                     AS valorsobra
         , (I.VL_NOTA_REC_ITEM)/100                 AS valortotal
         , (I.VL_NOTA_REC_ITEM/I.QT_NOTA_REC_ITEM)/100 AS valorunitario
    --   , NULL                                     AS inativador_id
    --   , NULL                                     AS autor_id
    --   , NULL                                     AS editor_id
         , E.ID                                     AS entrada_id
         , M.ID                                     AS material_id   
         , (SELECT ID FROM PUBLIC.UNIDADEMEDIDA WHERE CODIGO=I.CD_UM_ELEMENTAR) AS unidademedidaentrada_id
    FROM PUBLIC.ENTRADA E
    JOIN ASIWEB.AX_NOTA_REC_ITEM I ON E.CODIGOASI::TEXT=I.CD_ALMOXARIFADO||I.CD_ORGAO||I.CD_NOTA_REC::TEXT
    JOIN PUBLIC.MATERIAL M ON (M.CODIGO=I.CD_BEM_SERVICO);
    
    -- ATRIBUIR 0.0 ONDE A QUANTIDADESALDO FOR NULA;
    UPDATE ENTRADAALMOXARIFADOITEM 
       SET QUANTIDADESALDO = 0.0 
     WHERE QUANTIDADESALDO IS NULL;
    
    UPDATE ENTRADAALMOXARIFADOITEM 
       SET VALORSOBRA = 0.0
     WHERE VALORSOBRA IS NULL;

	--Possivel erro aqui porque pode não ter a coluna codigo, rodar na mão um por um caso de erro. 
    DO $$
    BEGIN
      ALTER TABLE DOCUMENTO DROP COLUMN CODIGO;        
      ALTER TABLE DOCUMENTO ADD COLUMN codigo CHARACTER VARYING (30);
    EXCEPTION
        WHEN OTHERS THEN NULL;
    END$$;

    --> DOCUMENTOS DA ENTRADA
    INSERT INTO public.documento
         (
           databloqueio
         , datainativo
         , datacriacao
         , dataedicao
         , version
         , dataemissao
         , numero
         , inativador_id
         , autor_id
         , editor_id
         , organizacao_id
         , dominiotipodocumento_id
         , emitente_id
         , codigo
         )
    SELECT 
           NULL                      AS databloqueio
         , NULL                      AS datainativo
         , LOCALTIMESTAMP            AS datacriacao
         , LOCALTIMESTAMP            AS dataedicao
         , 0                         AS "version"
         , DOC.DT_DOCUMENTO          AS dataemissao
         , DOC.NR_DOCUMENTO          AS numero
         , NULL                      AS inativador_id
         , NULL                      AS autor_id
         , NULL                      AS editor_id
         , 1                         AS organizacao_id
         , CASE 
             WHEN DOC.CD_TP_DOCUMENTO='001' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoDocumento' AND CODIGO=1)
             WHEN DOC.CD_TP_DOCUMENTO='002' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoDocumento' AND CODIGO=2)
             WHEN DOC.CD_TP_DOCUMENTO='003' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoDocumento' AND CODIGO=3)
             WHEN DOC.CD_TP_DOCUMENTO='004' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoDocumento' AND CODIGO=9)
             WHEN DOC.CD_TP_DOCUMENTO='005' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoDocumento' AND CODIGO=10)
             WHEN DOC.CD_TP_DOCUMENTO='006' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoDocumento' AND CODIGO=4)
             WHEN DOC.CD_TP_DOCUMENTO='009' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoDocumento' AND CODIGO=5)
             WHEN DOC.CD_TP_DOCUMENTO='010' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoDocumento' AND CODIGO=6)
             WHEN DOC.CD_TP_DOCUMENTO='014' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoDocumento' AND CODIGO=7)
             WHEN DOC.CD_TP_DOCUMENTO='018' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoDocumento' AND CODIGO=8)             
           END                       AS dominiotipodocumento_id
         , NULL                      AS emitente_id
         , DOC.CD_ALMOXARIFADO||DOC.CD_ORGAO||DOC.CD_NOTA_REC AS codigo
    FROM ASIWEB.AX_DOC_NOTA_REC DOC
    ORDER BY DOC.CD_NOTA_REC;

    --> REFERENCIA DOS DOCUMENTOS DAS ENTRADAS
    INSERT INTO public.entradadocumento
         (
           id 
         , entrada_id
         , entradainativo_id
         )
    SELECT D.ID    AS id
         , (SELECT E.ID FROM PUBLIC.ENTRADA E WHERE E.CODIGOASI=D.CODIGO) AS entrada_id
         , NULL    AS entradainativo_id
    FROM PUBLIC.DOCUMENTO D
    WHERE CODIGO IS NOT NULL;
    
    -- SO E PRECISO RODAR ESSE SQL NOS BANCOS JA EXISTENTES CUJO INTERESSE SEJA
    -- ARRUMAR OS FORNECEDORES CORRETOS DO ALMOXARIFADO,
    -- POIS O SQL DE INSERCAO DA ENTRADA NAO ESTAVA CORRETO ANTERIORMENTE
    /*
    UPDATE public.entrada 
       SET fornecedor_id = F.FORNECEDOR_ID
    FROM (
           SELECT COALESCE(F.ID,F2.ID) AS FORNECEDOR_ID
                , CD_NOTA_REC
                , CD_AGENTE_ENTRADA
           FROM ASIWEB.AX_NOTA_REC N 
           LEFT JOIN PESSOA P ON (P.AGENTE_ID = N.CD_AGENTE_ENTRADA::NUMERIC)
           LEFT JOIN PARCEIRO PAR ON (PAR.PESSOA_ID = P.ID)
           LEFT JOIN FORNECEDOR F ON (F.ID = PAR.ID)
           LEFT JOIN PESSOA P2 ON (P2.CODIGO = N.CD_AGENTE_ENTRADA)
           LEFT JOIN PARCEIRO PAR2 ON (PAR2.PESSOA_ID = P2.ID)
           LEFT JOIN FORNECEDOR F2 ON (F2.ID = PAR2.ID)
           INNER JOIN ENTRADA E ON (E.CODIGO = N.CD_NOTA_REC)
           INNER JOIN ENTRADAALMOXARIFADO EA ON (EA.ID = E.ID)
         ) AS F
    WHERE F.CD_NOTA_REC = CODIGO 
      AND ENTRADA.ID IN (
                          SELECT E.ID FROM ENTRADA E
                          INNER JOIN ENTRADAALMOXARIFADO EA ON (EA.ID = E.ID)
                         );
    */
COMMIT; -- #INI :: MOVIMENTACAO DE DAS ENTRADAS >> ENTRADA


BEGIN;  -- #INI :: IMPORTACAO BAIXAS DE ESTOQUE >> BAIXA
    /*
      BAIXA ALMOXARIFADO
      ==================
      1. Somente uma baixa está sem data da baixa, deixei ela de fora pois a data é obrigatória, por isso a codicao dt_baixa is not null;
      2. dominiotipobaixa definido para 'tipoBaixa' do patrimonio, mudar/criar um dominio so da baixa almoxarifado
    */
    INSERT INTO public.baixa
         (
           datacriacao, 
           dataedicao,
           version,
           organizacao_id,
           codigo  
         ) 
    SELECT B.DT_BAIXA AS DATACRIACAO
         , LOCALTIMESTAMP AS DATAEDICAO
         , 0 AS "version"
         , 1 AS organizacao_id
         , (B.CD_BAIXA||B.CD_UG)::bigint AS CODIGO    
    FROM ASIWEB.AX_BAIXA B
    WHERE B.DT_BAIXA IS NOT NULL;

    INSERT INTO PUBLIC.BAIXAALMOXARIFADO
         (
           id,
           databaixa,
           numerobaixa,
           almoxarifado_id,
           destino_id,
           dominiotipobaixa_id
         )
    SELECT B.ID                AS id
         , B.DATACRIACAO       AS databaixa
         , BA.CD_BAIXA::bigint AS numerobaixa
         , (SELECT ESTRUTURAORGANIZACIONAL_ID FROM ESTRUTURAORGANIZACIONALALMOXARIFADO WHERE ISALMOXARIFADO IS TRUE LIMIT 1) AS almoxarifado_id 
         , P.ID AS destino_id
         , CASE 
             WHEN BA.CD_TIPO_MOVIMENTO='007' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoBaixa' AND CODIGO=4)
             WHEN BA.CD_TIPO_MOVIMENTO='021' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoBaixa' AND CODIGO=2)
             WHEN BA.CD_TIPO_MOVIMENTO='023' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoBaixa' AND CODIGO=15)
             WHEN BA.CD_TIPO_MOVIMENTO='024' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoBaixa' AND CODIGO=7)
             WHEN BA.CD_TIPO_MOVIMENTO='044' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoBaixa' AND CODIGO=12)
             WHEN BA.CD_TIPO_MOVIMENTO='047' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoBaixa' AND CODIGO=10)
           END                 AS dominiotipobaixa_id
    FROM BAIXA B
    INNER JOIN ASIWEB.AX_BAIXA BA ON (BA.CD_BAIXA||BA.CD_UG = B.CODIGO::text)
    LEFT JOIN PESSOA P on (P.CODIGO = BA.CD_ORGAO_EXTERNO);    


    /*CREATE SEQUENCE baixaalmoxarifadoitem_id_seq
      INCREMENT 1
      MINVALUE 1
      MAXVALUE 9223372036854775807
      START 1
      CACHE 1;
    ALTER TABLE baixaalmoxarifadoitem_id_seq
      OWNER TO postgres;*/

    --vincular a sequencia recem criada na tabela baixaalmoxarifadoitem
    INSERT INTO PUBLIC.BAIXAALMOXARIFADOITEM
    (    
    DATACRIACAO,
    DATAEDICAO,
    VERSION,
    QUANTIDADE,    
    MATERIALCONSUMO_ID,
    DATAESTORNO,
    BAIXAALMOXARIFADO_ID    
    )
    SELECT    
    B.DATACRIACAO as datacricao,
    B.DATAEDICAO as dataedicao,
    0 as version,
    case when I.QT_BAIXAR is null then 0 else I.QT_BAIXAR end as qtd,    
    M.ID as material_id,
    NULL as dataestorno,
    B.ID
    FROM ASIWEB.AX_BAIXA_ITEM I
    INNER JOIN BAIXA B ON (I.CD_BAIXA||I.CD_UG = B.CODIGO::text)
    LEFT JOIN MATERIAL M ON (M.CODIGO = I.CD_BEM_SERVICO);    


    --ALTER TABLE DOCUMENTO DROP COLUMN CODIGO;
    --ALTER TABLE DOCUMENTO ADD COLUMN CODIGO CHARACTER VARYING (30);
    -- delete from documento  ???

    -- Refazer as migracoes de documentos  (Apagar e criar denovo, apagando o codigo toda vez que inserir nas tabelas
    --> DOCUMENTOS DA BAIXAALMOXARIFADO
    INSERT INTO public.documento
         (
           
           databloqueio
         , datainativo
         , datacriacao
         , dataedicao
         , version
         , dataemissao
         , numero
         , inativador_id
         , autor_id
         , editor_id
         , organizacao_id
         , dominiotipodocumento_id
         , emitente_id
         , CODIGO
         )
    SELECT NULL                      AS databloqueio
         , NULL                      AS datainativo
         , LOCALTIMESTAMP            AS datacriacao
         , LOCALTIMESTAMP            AS dataedicao
         , 0                         AS "version"
         , DOC.DT_DOCUMENTO          AS dataemissao
         , DOC.NR_DOCUMENTO          AS numero
         , NULL                      AS inativador_id
         , NULL                      AS autor_id
         , NULL                      AS editor_id
         , 1 AS organizacao_id
         ,CASE WHEN DOC.CD_TP_DOCUMENTO = '003' THEN (select id from dominio where chave = 'tipoDocumento' and codigo = 3)
                 WHEN DOC.CD_TP_DOCUMENTO = '008' THEN (select id from dominio where chave = 'tipoDocumento' and codigo = 9)
                 WHEN DOC.CD_TP_DOCUMENTO = '010' THEN (select id from dominio where chave = 'tipoDocumento' and codigo = 10)
                 WHEN DOC.CD_TP_DOCUMENTO = '014' THEN (select id from dominio where chave = 'tipoDocumento' and codigo = 11)
                 WHEN DOC.CD_TP_DOCUMENTO = '015' THEN (select id from dominio where chave = 'tipoDocumento' and codigo = 12)
                 WHEN DOC.CD_TP_DOCUMENTO = '018' THEN (select id from dominio where chave = 'tipoDocumento' and codigo = 13)
                 WHEN DOC.CD_TP_DOCUMENTO = '019' THEN (select id from dominio where chave = 'tipoDocumento' and codigo = 14)
                 WHEN DOC.CD_TP_DOCUMENTO = '020' THEN (select id from dominio where chave = 'tipoDocumento' and codigo = 15)             
           END AS DOMINIOTIPODOCUMENTO_ID
         , NULL                      AS emitente_id
         ,DOC.CD_BAIXA||DOC.CD_UG AS CODIGO
    FROM ASIWEB.ax_doc_baixa DOC    
    WHERE DOC.DT_DOCUMENTO IS NOT NULL
    ORDER BY DOC.CD_BAIXA;
    
    --> REFERENCIA DOS DOCUMENTOS DAS BAIXASALMOXARIFADO
    INSERT INTO public.baixadocumento
    (   ID,
        baixa_ID    
    )
    SELECT  
        doc.ID,
        b.id
    FROM public.documento DOC 
    inner join public.baixa b on (b.codigo::TEXT = doc.codigo)
    where doc.codigo is not null
    ORDER BY DOC.codigo;     
    
    --> FROM ASIWEB.AX_BAIXA_ITEM BI
    --> JOIN ASIWEB.AX_BAIXA B ON BI.CD_BAIXA = B.CD_BAIXA
    --> JOIN ASIWEB.AX_BAIXA_UG_LOTE BUL ON BUL.SQ_BAIXA_ITEM = BI.SQ_BAIXA_ITEM AND BUL.CD_BAIXA = BI.CD_BAIXA
COMMIT; -- #FIM :: IMPORTACAO BAIXAS DE ESTOQUE >> BAIXA 


BEGIN;  -- #INI :: IMPORTACAO REQUISICAO >> REQUISICAOCONSUMO
    INSERT INTO PUBLIC.REQUISICAOCONSUMO
    (
        DATACRIACAO,
        DATAEDICAO,
        DATAREQUISICAO,
        VERSION, 
        NUMEROREQUISICAO,
        organizacao_id,
        ALMOXARIFADO_ID,
        CENTROCUSTO_ID,
        TIPOSTATUSREQUISICAO_ID,
        UNIDADEREQUISITANTE_ID,
        DATAFINALIZACAOATENDIMENTO
    )
    SELECT 
         R.DT_INCLUSAO   AS DATACRIACAO
        ,LOCALTIMESTAMP  AS DATAEDICAO
        ,R.DT_REQUISICAO AS DATAREQUISICAO
        ,0               AS VERSION
        ,R.CD_REQUISICAO AS NUMEROREQ
        ,1 AS organizacao_id
        ,(SELECT ID FROM PUBLIC.ESTRUTURAORGANIZACIONALALMOXARIFADO WHERE ISALMOXARIFADO IS TRUE LIMIT 1) AS ALMOXARIFADO_id 
        ,(SELECT ID FROM PUBLIC.CENTROCUSTO WHERE codigo = r.cd_centro_custo) AS CENTROCUSTO_ID
        ,CASE 
                 WHEN R.CD_SITUACAO_REQ_ATUAL='002' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoStatusRequisicao' AND CODIGO=2)
                 WHEN R.CD_SITUACAO_REQ_ATUAL='003' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoStatusRequisicao' AND CODIGO=4)
                 WHEN R.CD_SITUACAO_REQ_ATUAL='007' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoStatusRequisicao' AND CODIGO=5)
             END AS TIPOSTATUSREQUISICAO_ID         
         ,E.ID              
         ,R.DT_FINALIZADA
    FROM asiweb.ax_requisicao r
    INNER JOIN ASIWEB.AX_UR UR ON (UR.CD_ORGAO = R.CD_ORGAO AND UR.CD_AGENTE::TEXT = R.CD_AGENTE::TEXT)
    INNER JOIN ESTRUTURAORGANIZACIONAL E ON (E.CODIGO = UR.CD_UR); -- REVER NAO ESTA PEGANDO TODAS AS UNIDADES REQUISITANTES, ALGUMAS REQUISICOES FICARAM DE FORA

    INSERT INTO PUBLIC.REQUISICAOCONSUMOITEM
    (
        DATACRIACAO,
        DATAEDICAO,
        VERSION,
        QUANTIDADE,
        QUANTIDADEDEVOLVIDA,
        organizacao_id,
        MATERIALCONSUMO_ID,
        REQUISICAOCONSUMO_ID,
        QUANTIDADEATENDIDA
    )
    SELECT 
         R.DATACRIACAO
        ,R.DATAEDICAO
        ,0 AS VERSION
        ,I.QT_REQUISICAO AS QTD
        ,0 AS QTDDEVOLVIDA
        ,R.organizacao_id AS organizacao_id
        ,M.ID AS MATERIAL_ID
        ,R.ID AS REQUISICAO_ID
        ,rul.qt_atendido AS QTDATENDIDA
    FROM ASIWEB.AX_REQUISICAO_ITEM I 
    INNER JOIN REQUISICAOCONSUMO R ON (R.NUMEROREQUISICAO = I.CD_REQUISICAO)
    INNER JOIN MATERIAL M ON (M.CODIGO = I.CD_BEM_SERVICO)
    LEFT JOIN asiweb.ax_req_ug_lote rul ON i.cd_orgao::text = rul.cd_orgao::text AND i.cd_almoxarifado::text = rul.cd_almoxarifado::text AND i.cd_requisicao::text = rul.cd_requisicao::text AND i.sq_requisicao_item = rul.sq_requisicao_item;
COMMIT; -- #FIM :: IMPORTACAO REQUISICAO >> REQUISICAOCONSUMO


BEGIN;  -- #INI :: IMPORTACAO ATENDIMENTO REQUISICAO >> TESTAR
    INSERT INTO atendimentorequisicaoconsumo
        (
          databloqueio, 
          datainativo, 
          datacriacao, 
          dataedicao, 
          version, 
              dataatendimento, 
              numeroatendimento, 
              inativador_id, 
              autor_id, 
              editor_id, 
              organizacao_id, 
              requisicaoconsumo_id)
        select
        NULL AS DATABLOQUEIO,
        NULL AS DATAINATIVO,
        R.DT_CONFIRMADA as datacriacao,
        localtimestamp as dataedicao,
        0 as "version",
        R.dt_finalizada,
        AR.ID_REQ_UG_LOTE,
        NULL AS INATIVADOR_ID,
        NULL AS AUTOR_ID,
        NULL AS EDITOR_ID,
        1,
        (SELECT ID FROM REQUISICAOCONSUMO WHERE NUMEROREQUISICAO = R.CD_REQUISICAO) AS REQUISICAO_ID
    FROM asiweb.ax_req_ug_lote AR 
    INNER JOIN ASIWEB.AX_REQUISICAO_ITEM RI ON (RI.CD_ALMOXARIFADO = AR.CD_ALMOXARIFADO AND RI.CD_UG=AR.CD_UG AND RI.CD_ORGAO = AR.CD_ORGAO AND RI.SQ_REQUISICAO_ITEM = AR.SQ_REQUISICAO_ITEM)
    INNER JOIN ASIWEB.AX_REQUISICAO R ON (R.CD_REQUISICAO = RI.CD_REQUISICAO);

    --NAO TERMINEI ESSE SCRIPT, FALTA DESCOBRIR COMO PEGAR O REQUISICAO CONSUMO ITEM ID, TALVEZ VAI TER QUE MUDAR A REQUISICAO ITEM PARA INSERIR O ATRIBUTO sq_requisicao_item
    INSERT INTO atendimentorequisicaoconsumoitem(         
                databloqueio, 
                datainativo, 
                datacriacao, 
                dataedicao, 
                version, 
                quantidade, 
                inativador_id, 
                autor_id, 
                editor_id, 
                organizacao_id, 
                atendimentorequisicaoconsumo_id, 
                material_id, 
                requisicaoconsumoitem_id)
        SELECT
        NULL AS DATABLOQUEIO,
        NULL AS DATAINATIVO,
        ARC.DATACRIACAO,
        LOCALTIMESTAMP AS DATAEDICAO,
        0 AS "VERSION",
        AR.QT_ATENDIDO,
        NULL AS INATIVADOR_ID,
        NULL AS AUTOR_ID,
        NULL AS EDITOR_ID,
        1,
        ARC.ID,
        M.ID, 
        
       FROM asiweb.ax_req_ug_lote AR
       INNER JOIN ATENDIMENTOREQUISICAOCONSUMO ARC ON (ARC.NUMEROATENDIMENTO::NUMERIC = AR.ID_REQ_UG_LOTE)
       INNER JOIN MATERIAL M ON (M.CODIGO = AR.CD_BEM_SERVICO)
       INNER JOIN REQUISICAOCONSUMOITEM;
COMMIT; -- #FIM :: IMPORTACAO ATENDIMENTO REQUISICAO >> TESTAR


-- REVER COM CONCEITO DE CLASSIFICACAO POR NATUREZA (ESTOCAVEL E NAO ESTOCAVEL)
BEGIN;  -- #INI :: IMPORTACAO SALDOS DE ESTOQUE >> MOVIMENTOESTOQUESALDO E MOVIMENTOESTOQUESALDOMEDIO
    INSERT INTO PUBLIC.MATERIALESTOQUESALDO
    (
        datacriacao,
        dataedicao,
        version,
        datareferencia,
        quantidade,
        organizacao_id,
        localestoque_id,
        material_id,
        tipofechamento_id,
        unidademedidamaterial_id
    )
    SELECT 
        LOCALTIMESTAMP,
        LOCALTIMESTAMP,
        0,
        (SELECT DATAREFERENCIAVIGENTE - INTERVAL '1 MONTH' AS REFERENCIA FROM ORGANIZACAO LIMIT 1) AS REFERENCIA,
        SUM(EST.QT_ESTOQUE) AS QTD,         
        1 AS ORGANIZACAO_ID,
        (SELECT ID FROM LOCALESTOQUE LIMIT 1) AS LOCALESTOQUE_ID,
        M.ID AS MATERIAL_ID,
        NULL AS TIPOFECHAMENTO_ID,
        MC.UNIDADEARMAZENAMENTO_ID
    FROM ASIWEB.AX_ESTOQUE_END EST
    INNER JOIN MATERIAL M ON (M.CODIGO = EST.CD_BEM_SERVICO)
    INNER JOIN MATERIALCONSUMO MC ON (MC.ID = M.ID)
    GROUP BY M.ID, MC.UNIDADEARMAZENAMENTO_ID, EST.CD_BEM_SERVICO;

    UPDATE MATERIALESTOQUESALDO S
       SET VALORFECHADO = (ESTOQUE.QUANTIDADE*ESTOQUE.PRECO)+ESTOQUE.RESTO
    FROM (
           SELECT DISTINCT 
                  E.QUANTIDADE
                , E.MATERIAL_ID
                , (SELECT PR_MEDIO/100   FROM ASIWEB.AX_ESTOQUE_UG 
                   WHERE CD_BEM_SERVICO = M.CODIGO ORDER BY CD_NATUREZA_CLASS LIMIT 1) AS PRECO
                , (SELECT VL_RESIDUO/100 FROM ASIWEB.AX_ESTOQUE_UG 
                   WHERE CD_BEM_SERVICO = M.CODIGO ORDER BY CD_NATUREZA_CLASS LIMIT 1) AS RESTO
           FROM MATERIALESTOQUESALDO E 
           INNER JOIN MATERIAL M ON (M.ID = E.MATERIAL_ID AND DOMINIOTIPOMATERIAL_ID = 8)
          ) AS ESTOQUE
    WHERE S.MATERIAL_ID = ESTOQUE.MATERIAL_ID;

    INSERT INTO PUBLIC.MATERIALESTOQUESALDOMEDIO
    (
        DATACRIACAO,
        DATAEDICAO,
        VERSION,    
        QUANTIDADEATUAL,
        organizacao_id,    
        MATERIAL_ID
    )
    SELECT 
        LOCALTIMESTAMP,
        LOCALTIMESTAMP,
        0,    
        sum(EST.QT_ESTOQUE) as qtd,         
        1 AS organizacao_id,    
        M.ID AS MATERIAL_ID    
    FROM ASIWEB.AX_ESTOQUE_END EST
    INNER JOIN MATERIAL M ON (M.CODIGO = EST.CD_BEM_SERVICO)
    GROUP BY M.ID, EST.CD_BEM_SERVICO;

    UPDATE MATERIALESTOQUESALDOMEDIO S
    SET VALORUNITARIOMEDIO = estoque.preco,
        RESTOMEDIO = estoque.resto
    FROM (
        select material_id, (select pr_medio/100 from asiweb.ax_estoque_ug where cd_bem_servico = m.codigo order by cd_natureza_class limit 1) as preco, (select vl_residuo/100 from asiweb.ax_estoque_ug where cd_bem_servico = m.codigo order by cd_natureza_class limit 1) as resto
        from materialestoquesaldomedio e 
        inner join material m on (m.id = e.material_id and dominiotipomaterial_id = 8)
        ) as estoque
    where s.material_id = estoque.material_id;
COMMIT; -- #FIM :: IMPORTACAO SALDOS DE ESTOQUE >> MOVIMENTOESTOQUESALDO E MOVIMENTOESTOQUESALDOMEDIO 


BEGIN;  -- #INI :: IMPORTACAO DOS SALDOS DAS CONTAS DE ALMOXARIFADO >> CONTACONTABILSALDO
    INSERT INTO public.contacontabilsaldo
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , datareferencia
    , valor
    , inativador_id
    , autor_id
    , editor_id
    , contacontabil_id
    )
    SELECT (row_number() OVER (ORDER BY S.AA_FECHAMENTO||S.MM_FECHAMENTO)) + M.MAX AS id
         , NULL                    AS databloqueio
         , NULL                    AS datainativo
         , LOCALTIMESTAMP          AS datacriacao
         , LOCALTIMESTAMP          AS dataedicao
         , 0                       AS "version"
         , TO_DATE(S.AA_FECHAMENTO||S.MM_FECHAMENTO,'YYYYMM') - interval '1 month' AS datareferencia
         , S.VL_SALDO_ANT/100      AS valor
         , NULL                    AS inativador_id
         , NULL                    AS autor_id
         , NULL                    AS editor_id
         , C.ID                    AS contacontabil_id
    FROM PUBLIC.CONTACONTABIL C
    INNER JOIN ASIWEB.AX_V_SALDO_ANT S ON C.CODIGO=S.CD_CONTA
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.contacontabilsaldo) M ON 1=1
    WHERE S.VL_SALDO_ANT>0
    ORDER BY S.CD_CONTA;
COMMIT; -- #FIM :: IMPORTACAO DOS SALDOS DAS CONTAS DE ALMOXARIFADO >> CONTACONTABILSALDO


BEGIN;  -- #INI :: INCLUI AS CONTAS SEM SALDO EM CADA REFERENCIA >> CONTACONTABILSALDO
    DO $$
    DECLARE
        contas RECORD;
        v_tipoMat int;
        v_dataIni date;
        v_dataFin date;
        v_dataRef date;
        v_tipo int;
    BEGIN
        --> RECUPERA O DOMINIO DAS CONTAS DE MATERIAL DE CONSUMO
        SELECT ID INTO v_tipo
          FROM PUBLIC.DOMINIO 
         WHERE CHAVE='tipoMaterial'
           AND CODIGO=1;

        --> RECUPERA O PRIMEIRO MES DE REFERENCIA DAS CONTAS DE MATERIAL DE CONSUMO
        SELECT MIN(datareferencia) INTO v_dataIni
          FROM PUBLIC.CONTACONTABILSALDO;

        --> INICIALIZA A DATA DE REFERENCIA
        v_dataRef := v_dataIni;

        --> RECUPERA O ULTIMO MES DE REFERENCIA DAS CONTAS DE MATERIAL DE CONSUMO
        SELECT MAX(datareferencia) INTO v_dataFin
          FROM PUBLIC.CONTACONTABILSALDO; 

        WHILE (v_dataRef BETWEEN v_dataIni AND v_dataFin) LOOP
            FOR contas IN SELECT ID FROM public.contacontabil WHERE dominiotipomaterial_id = v_tipoMat LOOP
                IF (SELECT ID FROM public.contacontabilsaldo WHERE contacontabil_id=contas.id AND datareferencia=v_dataRef) IS NULL THEN
                    INSERT INTO public.contacontabilsaldo
                         ( 
                           id, datacriacao, dataedicao, 
                           version, datareferencia, valor, contacontabil_id
                         )
                    VALUES 
                         ( 
                           NEXTVAL('contacontabilsaldo_id_seq'), LOCALTIMESTAMP, LOCALTIMESTAMP, 0,
                           v_dataRef, 0.0, contas.id
                         );
                END IF;
            END LOOP;
            v_dataRef := v_dataRef + interval '1 month';
        END LOOP;
    END;
    $$ LANGUAGE plpgsql;
COMMIT; -- #FIM :: INCLUI AS CONTAS SEM SALDO EM CADA REFERENCIA >> CONTACONTABILSALDO


BEGIN;  -- #INI :: IMPORTACAO DOS MOVIMENTO DAS CONTAS DE ALMOXARIFADO >> CONTACONTABILMOVIMENTO
    INSERT INTO public.contacontabilmovimento
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , datareferencia
    , valor
    , inativador_id
    , autor_id
    , editor_id
    , baixa_id
    , contacontabil_id
    , dominiotipomovimentocontacontabil_id
    , entrada_id
    , transferenciaconta_id
    )
    SELECT (row_number() OVER (ORDER BY S.AA_FECHAMENTO||S.MM_FECHAMENTO)) + M.MAX AS id
         , NULL                    AS databloqueio
         , NULL                    AS datainativo
         , LOCALTIMESTAMP          AS datacriacao
         , LOCALTIMESTAMP          AS dataedicao
         , 0                       AS "version"
         , TO_DATE(S.AA_FECHAMENTO||S.MM_FECHAMENTO,'YYYYMM') AS datareferencia
         , CASE 
             WHEN S.VL_ENTRADA > 0 THEN (S.VL_ENTRADA/100)
             WHEN S.VL_SAIDA   > 0 THEN (ABS(S.VL_SAIDA/100)*(-1))
           END                     AS valor
         , NULL                    AS inativador_id
         , NULL                    AS autor_id
         , NULL                    AS editor_id
         , NULL                    AS baixa_id
         , C.ID                    AS contacontabil_id
         , CASE 
             WHEN S.VL_ENTRADA > 0 THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoMovimentoContaContabil' AND CODIGO=1)
             WHEN S.VL_SAIDA   > 0 THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoMovimentoContaContabil' AND CODIGO=2)
           END                     AS dominiotipomovimentocontacontabil_id
         , NULL                    AS entrada_id
         , NULL                    AS transferenciaconta_id
    FROM PUBLIC.CONTACONTABIL C
    INNER JOIN ASIWEB.AX_V_SALDO_ANT S ON C.CODIGO=S.CD_CONTA
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.contacontabilmovimento) M ON 1=1
    WHERE (S.VL_ENTRADA+S.VL_SAIDA)>0
    ORDER BY S.CD_CONTA; 
COMMIT; -- #FIM :: IMPORTACAO DOS MOVIMENTO DAS CONTAS DE ALMOXARIFADO >> CONTACONTABILMOVIMENTO


BEGIN;  -- #INI :: IMPORTACAO DOS MOVIMENTO DE ESTOQUE >> MOVIMENTOESTOQUE (rever) nao esta correto
    INSERT INTO public.movimentoestoque
    (
    , id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , datamovimento
    , quantidade
    , valor
    , inativador_id
    , autor_id
    , editor_id
    , organizacao_id
    , atendimentorequisicaoconsumo_id
    , baixaalmoxarifadoitem_id
    , devolucaoitem_id
    , entradaalmoxarifadoitem_id
    , localestoque_id
    , material_id
    , materialestoquesaldo_id
    , tipomovimento_id
COMMIT; -- #FIM :: IMPORTACAO DOS MOVIMENTO DE ESTOQUE >> MOVIMENTOESTOQUE


BEGIN;  -- #INI :: IMPORTACAO DOS SALDOS DE ESTOQUE >> MATERIALESTOQUESALDO
    INSERT INTO public.materialestoquesaldo
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , datareferencia
    , quantidade
    , valorfechado
    , inativador_id
    , autor_id
    , editor_id
    , organizacao_id
    , localestoque_id
    , material_id_material
    , tipofechamento_id
    , unidademedidamaterial_id
    )
    --> FROM ASIWEB.AX_ESTOQUE_ALM EA
    --> FROM ASIWEB.AX_ESTOQUE_END EE JOIN ASIWEB.CR_ENDERECO_EST ENDEST ON ENDEST.CD_ORGAO = EE.CD_ORGAO 
    --> FROM ASIWEB.AX_ESTOQUE_UG
COMMIT; -- #FIM :: IMPORTACAO DOS SALDOS DE ESTOQUE >> MATERIALESTOQUESALDO


BEGIN;  -- #INI :: IMPORTACAO REQUISICAO DE MATERIAIS / ATENDIMENTO >> 
    --> FROM ASIWEB.AX_REQUISICAO AR
    --> FROM ASIWEB.AX_PARECER_REQ PR
    --> FROM ASIWEB.AX_REQUISICAO_ITEM ARI
    --> FROM ASIWEB.AX_REQ_UG_LOTE ARU
    --> FROM ASIWEB.AX_REQ_USUARIO_SIT ARUS
COMMIT; -- #FIM :: IMPORTACAO REQUISICAO DE MATERIAIS / ATENDIMENTO >>


BEGIN;  -- #INI :: IMPORTACAO NOTAS_FISCAIS >>
    --> FROM ASIWEB.AX_NOTA_REC NR JOIN ASIWEB.AX_TIPO_MOVIMENTO TM 
    --> FROM ASIWEB.AX_NOTA_REC_ITEM NRI 
COMMIT; -- #FIM :: IMPORTACAO NOTAS_FISCAIS >>


BEGIN;  -- #INI :: IMPORTACAO TRANSFERENCIAS DE ESTOQUE >>
    --> FROM ASIWEB.AX_TRANSF_ENDER TE
    --> FROM ASIWEB.AX_TRANSF_END_ITEM TEI
COMMIT; -- #FIM :: IMPORTACAO TRANSFERENCIAS DE ESTOQUE >> 


#### MOVIMENTAÇÕES ####


BEGIN;  -- #INI :: IMPORTACAO DOAÇÕES DE MATERIAIS >> 
    --> FROM ASIWEB.AX_NOTA_REC NR
    --> JOIN ASIWEB.AX_NOTA_REC_ITEM NRI ON NRI.CD_NOTA_REC = NR.CD_NOTA_REC
COMMIT; -- #FIM :: IMPORTACAO DOAÇÕES DE MATERIAIS >> 


BEGIN;  -- #FIM :: IMPORTACAO >> AJUSTES SALDOSANTERIORES RMB
    DELETE FROM public.contacontabilsaldo
     WHERE contacontabil_id IN (SELECT ID FROM PUBLIC.CONTACONTABIL 
                                WHERE DOMINIOTIPOMATERIAL_ID = 7);
    INSERT INTO public.contacontabilsaldo
    (
      databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , datareferencia
    , valor
    , inativador_id
    , autor_id
    , editor_id
    , contacontabil_id
    )
    SELECT 
           NULL                    AS databloqueio
         , NULL                    AS datainativo
         , LOCALTIMESTAMP          AS datacriacao
         , LOCALTIMESTAMP          AS dataedicao
         , 0                       AS "version"
         , TO_DATE(S.AA_FECHAMENTO||S.MM_FECHAMENTO,'YYYYMM') - interval '1 month' AS datareferencia
         , sum(S.VL_SALDO_ANT/100)      AS valor
         , NULL                    AS inativador_id
         , NULL                    AS autor_id
         , NULL                    AS editor_id
         , C.ID                    AS contacontabil_id
    FROM PUBLIC.CONTACONTABIL C
    INNER JOIN ASIWEB.AX_V_SALDO_ANT S ON C.CODIGO=S.CD_CONTA    
    --JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.contacontabilsaldo) M ON 1=1
    GROUP BY S.AA_FECHAMENTO, S.MM_FECHAMENTO, C.ID
    ORDER BY C.ID, S.AA_FECHAMENTO, S.MM_FECHAMENTO;   

    DELETE FROM public.contacontabilsaldo
     WHERE contacontabil_id IN (SELECT ID FROM PUBLIC.CONTACONTABIL 
                                WHERE DOMINIOTIPOMATERIAL_ID = 7)
       AND DATE_TRUNC('month',DATAREFERENCIA) BETWEEN DATE_TRUNC(timestamp '2015-03-01') AND DATE_TRUNC(timestamp '2015-05-01');

    -- delete from contacontabilsaldo where contacontabil_id in (select id from contacontabil where dominiotipomaterial_id = 7) and datareferencia = TO_DATE('2015'||'03','YYYYMM');
    -- delete from contacontabilsaldo where contacontabil_id in (select id from contacontabil where dominiotipomaterial_id = 7) and datareferencia = TO_DATE('2015'||'04','YYYYMM');
    -- delete from contacontabilsaldo where contacontabil_id in (select id from contacontabil where dominiotipomaterial_id = 7) and datareferencia = TO_DATE('2015'||'05','YYYYMM');
    
    INSERT INTO public.contacontabilsaldo
    (
      databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , datareferencia
    , valor
    , inativador_id
    , autor_id
    , editor_id
    , contacontabil_id
    )
    SELECT C.DATABLOQUEIO
         , C.DATAINATIVO
         , C.DATACRIACAO
         , C.DATAEDICAO
         , C.VERSION
         , C.DATAREFERENCIA+interval '1 month' AS datareferencia
         , C.VALOR
         , C.INATIVADOR_ID
         , C.AUTOR_ID
         , C.EDITOR_ID
         , C.CONTACONTABIL_ID
    FROM CONTACONTABILSALDO C    
    WHERE C.CONTACONTABIL_ID IN (select id from contacontabil where dominiotipomaterial_id = 7) and C.datareferencia = TO_DATE('2015'||'02','YYYYMM');

    INSERT INTO public.contacontabilsaldo
    (
      databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , datareferencia
    , valor
    , inativador_id
    , autor_id
    , editor_id
    , contacontabil_id
    )
    select
    c.databloqueio,
    c.datainativo,
    c.datacriacao,
    c.dataedicao,
    c.version,
    c.datareferencia+interval '1 MONTH' AS REFERENCIA,
    c.valor,
    c.inativador_id,
    c.autor_id,
    c.editor_id,
    c.contacontabil_id
    FROM CONTACONTABILSALDO C    
    WHERE C.CONTACONTABIL_ID IN (select id from contacontabil where dominiotipomaterial_id = 7) and C.datareferencia = TO_DATE('2015'||'03','YYYYMM');     

    INSERT INTO public.contacontabilsaldo
    (
      databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , datareferencia
    , valor
    , inativador_id
    , autor_id
    , editor_id
    , contacontabil_id
    )
    select
    c.databloqueio,
    c.datainativo,
    c.datacriacao,
    c.dataedicao,
    c.version,
    c.datareferencia+interval '1 MONTH' AS REFERENCIA,
    c.valor,
    c.inativador_id,
    c.autor_id,
    c.editor_id,
    c.contacontabil_id
     FROM CONTACONTABILSALDO C    
     WHERE C.CONTACONTABIL_ID IN (select id from contacontabil where dominiotipomaterial_id = 7) and C.datareferencia = TO_DATE('2015'||'04','YYYYMM');       
    
    
    --DELETE FROM CONTACONTABILSALDO WHERE ID IN (34087, 34102, 34103, 34107, 34105, 34109, 34111, 34118, 34119, 34130, 34135, 34136, 34140)
    
    INSERT INTO public.contacontabilsaldo
    (    datacriacao
    , dataedicao
    , version
    , datareferencia
    , valor
    , contacontabil_id
    )
    VALUES (LOCALTIMESTAMP, LOCALTIMESTAMP, 0, TO_DATE('2015'||'05','YYYYMM'), 0, 1);

    /* INSERT INTO public.contacontabilsaldo
    (    datacriacao
    , dataedicao
    , version
    , datareferencia
    , valor
    , contacontabil_id
    )
    VALUES (LOCALTIMESTAMP, LOCALTIMESTAMP, 0, TO_DATE('2015'||'05','YYYYMM'), 0, 3);
    */
    
    UPDATE CONTACONTABIL C SET CONTACONTABILSALDOANTERIOR_ID  = (SELECT ID FROM CONTACONTABILSALDO WHERE CONTACONTABIL_ID = C.ID AND DATAREFERENCIA = TO_DATE('2015'||'05','YYYYMM'));
    
    UPDATE CONTACONTABIL SET PERCENTUALNAODEPRECIAVEL = 0, TAXADEPRECIACAOMENSAL = 0, VIDAUTIL = 0 WHERE ID = 2;
    
    UPDATE CONTACONTABILSALDO SET VALOR = 0 WHERE VALOR IS NULL;
COMMIT; -- #FIM :: IMPORTACAO >> AJUSTES SALDOSANTERIORES RMB


--===========================================================================--
-- ##INI :: IMPORTACAO TABELAS DE MOVIMENTO DE MATERIAS DE CONSUMO           --
--===========================================================================--