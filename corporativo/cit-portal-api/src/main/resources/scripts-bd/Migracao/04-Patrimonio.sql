--===========================================================================--
-- ##INI :: IMPORTACAO TABELAS DE MOVIMENTO DE MATERIAS PERMANENTES          --
--===========================================================================--


BEGIN;  -- #INI :: MOVIMENTACAO DE DAS ENTRADAS >> ENTRADA
    /*
      OBSERVAÇÕES:

      1. O código da entrada foi formado por CD_ENTRADA||CD_UG para garantir unicidade,
         pois no ASI a chave é CD_ORGAO + CD_UG + CD_ENTRADA;
      2. Tempo estimado de execução de ~12 minutos;
      3. Foi necessário filtrar somente registros com DT_CONTABIL, porque o ASI considera
         os registros sem conteúdo neste campo como "apagados". Já no GRP a data contábil
         é obrigatória.
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
           E1.DT_INCLUSAO                AS datacriacao
         , E1.DT_INCLUSAO                AS dataedicao
         , 0                             AS "version"
         , E1.CD_ENTRADA                 AS codigo
         , E1.CD_ENTRADA||E1.CD_UG       AS codigoasi
         , E1.DT_CONTABIL                AS datacontabil
         , E1.DT_AQUISICAO               AS datarecebimento
         , E1.DT_CONTABIL                AS datareferencia
         , VT.VL_ENTRADA/100             AS valortotalnota
    --   , NULL                          AS inativador_id
    --   , NULL                          AS autor_id
    --   , NULL                          AS editor_id
         , (SELECT ID FROM PUBLIC.ORGANIZACAO LIMIT 1) AS organizacao_id
         , CASE 
             WHEN TP.IN_ORCAMENTARIO='S' THEN 
               (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoEntrada' AND CODIGO=1)
             ELSE 
               (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoEntrada' AND CODIGO=2)
           END                           AS dominiotipoentrada_id
         , CASE 
             WHEN E1.CD_TP_FORMA_OP='01' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoRecebimento' AND CODIGO=1)
             WHEN E1.CD_TP_FORMA_OP='02' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoRecebimento' AND CODIGO=2)
             WHEN E1.CD_TP_FORMA_OP='03' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoRecebimento' AND CODIGO=3)
             WHEN E1.CD_TP_FORMA_OP='04' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoRecebimento' AND CODIGO=4)
             WHEN E1.CD_TP_FORMA_OP='06' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoRecebimento' AND CODIGO=5)
             WHEN E1.CD_TP_FORMA_OP='09' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoRecebimento' AND CODIGO=6)
             WHEN E1.CD_TP_FORMA_OP='31' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoRecebimento' AND CODIGO=7)
             WHEN E1.CD_TP_FORMA_OP='33' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoRecebimento' AND CODIGO=8)
             WHEN E1.CD_TP_FORMA_OP='34' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoRecebimento' AND CODIGO=9)
             WHEN E1.CD_TP_FORMA_OP='37' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoRecebimento' AND CODIGO=10)
             WHEN E1.CD_TP_FORMA_OP='38' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoRecebimento' AND CODIGO=11)
           END                           AS dominiotiporecebimento_id
         , P.ID fornecedor_id
    FROM ASIWEB.BP_ENTRADA E1
    JOIN ASIWEB.BP_TP_FORMA_OP_POSSIVEL TP 
         ON TP.CD_TP_OPERACAO=E1.CD_TP_OPERACAO AND TP.CD_TP_FORMA_OP=E1.CD_TP_FORMA_OP AND TP.CD_TP_BEM=E1.CD_TP_BEM
    JOIN ASIWEB.BP_ENT_ITEM E2 
         ON E1.CD_ORGAO=E2.CD_ORGAO AND E1.CD_UG=E2.CD_UG AND E1.CD_ENTRADA=E2.CD_ENTRADA
    JOIN ASIWEB.BP_BEM E3 
         ON E2.CD_ORGAO=E3.CD_ORGAO AND E2.CD_UG=E3.CD_UG AND E2.CD_ENTRADA=E3.CD_ENTRADA AND E2.SQ_ITEM=E3.SQ_ITEM
    JOIN ASIWEB.CR_BEM_SERVICO BS 
         ON BS.CD_BEM_SERVICO=E3.CD_BEM_SERVICO     
    JOIN (
         SELECT BP_BEM.CD_ORGAO, 
         BP_BEM.CD_UG, 
         BP_BEM.CD_ENTRADA, 
         SUM(BP_BEM.VL_UNITARIO) 
         AS VL_ENTRADA 
         FROM ASIWEB.BP_BEM 
         GROUP BY 
         BP_BEM.CD_ORGAO, 
         BP_BEM.CD_UG, 
         BP_BEM.CD_ENTRADA) 
         AS VT ON VT.CD_ORGAO=E1.CD_ORGAO AND VT.CD_UG=E1.CD_UG AND VT.CD_ENTRADA=E1.CD_ENTRADA
     LEFT JOIN PUBLIC.PARCEIRO P ON P.CODIGO=E1.CD_AGENTE
    WHERE E1.DT_CONTABIL IS NOT NULL
    ;-- AND E1.DT_CONTABIL >= TO_DATE('201412','YYYYMM'); --<< LIMITADOR >> #COMENTAR
	--maquina Ju 1.9 minutos

    -- 93841 registros 1 minutos e 33 segundos

    --> REGISTROS DA REFERENCIA NECESSARIA EM ENTRADA DE PATRIMONIO
    INSERT INTO public.entradapatrimonio
         (
           id
         , dominiotipobem_id
         , estruturaorganizacional_id
         )
    SELECT DISTINCT
           E.ID AS id
         , (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoBem' AND CODIGO=1) AS dominiotipobem_id --E fixo nesse tipo porque nao tem nenhum no banco do mpog de outro tipo
         , (SELECT ID FROM PUBLIC.ESTRUTURAORGANIZACIONAL E WHERE E.CODIGO=B.CD_UL_ENTRADA AND E.DOMINIOTIPOESTRUTURAORGANIZACIONAL_ID = (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoEstruturaOrganizacional' AND CODIGO=3)) AS estruturaorganizacional_id 
    FROM PUBLIC.ENTRADA E
    LEFT JOIN ASIWEB.BP_BEM B ON (E.CODIGOASI=B.CD_ENTRADA||CD_UG)
    ;--WHERE E.DATACONTABIL >= TO_DATE('201412','YYYYMM'); --<< LIMITADOR >> #COMENTAR
	
	--maquina Ju 0,02 minutos


    --  O trecho abaixo foi executado devido aos seguintes acontecimentos:
    --  1. A subquery de estruturaorganizacional estava retornando mais de uma linha
    --  2. Verificamos que o mesmo código de UL aparecia em mais de uma ENTRADA+UG
    --  3. Inicialmente a suspeita era de que UL com datafim tem mesmo código, então
    --     o bloco abaixo verifica que qual o id criado para UL vigente na época da entrada
    --  4. Como tal tratamento nunca foi necessário, bastou acrescentar o critério do tipo
    --     de dominio na subquery, pois o mesmo código não era uma UL e sim uma UR.
    /*
    DO $$
    DECLARE
      v_entrada RECORD;
      v_tipobem BIGINT;
      v_unidade BIGINT;
    BEGIN
      SELECT id INTO v_tipobem FROM PUBLIC.DOMINIO WHERE CHAVE='tipoBem' AND CODIGO=1;
      FOR v_entrada IN SELECT DISTINCT ID, CODIGOASI, DATACONTABIL FROM PUBLIC.ENTRADA LOOP;
        SELECT E.ID INTO v_unidade
          FROM PUBLIC.ESTRUTURAORGANIZACIONAL E 
         WHERE E.DOMINIOTIPOESTRUTURAORGANIZACIONAL_ID = (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoEstruturaOrganizacional' AND CODIGO=3) 
           AND E.CODIGO   = (SELECT DISTINCT B.CD_UL_ENTRADA FROM ASIWEB.BP_BEM B WHERE v_entrada.CODIGOASI=B.CD_ENTRADA||B.CD_UG)
           AND E.DATAFIM <= (SELECT V.DATAFIM 
                               FROM PUBLIC.ESTRUTURAORGANIZACIONAL V -- ESTRUTURA VIGENTE NA EPOCA DA ENTRADA
                              WHERE V.ID = E.ID
                                AND V.DOMINIOTIPOESTRUTURAORGANIZACIONAL_ID = E.DOMINIOTIPOESTRUTURAORGANIZACIONAL_ID
                                AND V.DATAFIM >= v_entrada.DATACONTABIL
                           ORDER BY V.DATAFIM LIMIT 1);
        IF v_unidade IS NULL THEN
          SELECT E.ID INTO v_unidade 
            FROM PUBLIC.ESTRUTURAORGANIZACIONAL E 
           WHERE E.DOMINIOTIPOESTRUTURAORGANIZACIONAL_ID = (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoEstruturaOrganizacional' AND CODIGO=3) 
             AND E.CODIGO = (SELECT DISTINCT B.CD_UL_ENTRADA FROM ASIWEB.BP_BEM B WHERE v_entrada.CODIGOASI=B.CD_ENTRADA||B.CD_UG)
             AND E.DATAFIM IS NULL;
        END IF;
        IF (v_tipobem IS NOT NULL) AND (v_unidade IS NOT NULL) THEN
          INSERT INTO public.entradapatrimonio
               (id, dominiotipobem_id, estruturaorganizacional_id)
          VALUES
               (v_entrada.ID, v_tipobem, v_unidade); 
        ELSE
          RAISE NOTICE 'ID unidade da entrada NÃO ENCONTRADO!';
        END IF;
        v_unidade := NULL;
      END LOOP;
    END$$;
    */

    /*
     A estratégia usada para incluir os itens, foi agrupá-los por valor unitário para
     bater com o total, pois um dos itens do mesmo material pode ter valor unitario
     diferente por causa do resto de uma dízima periódica.
    */
    --> REGISTROS DOS ITENS DA REFERENCIA NECESSARIA EM ENTRADA DE PATRIMONIO
    INSERT INTO public.entradapatrimonioitem
         (
    --     id
    --   , databloqueio
    --   , datainativo
           datacriacao
         , dataedicao
         , version
         , emplaquetado
         , quantidade
         , sequencia
         , valor
         , valorunitario
    --   , inativador_id
    --   , autor_id
    --   , editor_id
         , entrada_id
         , material_id
         )
    SELECT DISTINCT
    --     NEXTVAL('entradapatrimonioitem_id_seq')  AS id
    --   , NULL                                     AS databloqueio
    --   , NULL                                     AS datainativo
           E.DATACRIACAO                            AS datacriacao
         , E.DATAEDICAO                             AS dataedicao
         , 0                                        AS "version"
         , TRUE                                     AS emplaquetado
         , COUNT(*)                                 AS quantidade
         , B.SQ_ITEM                                AS sequencia
         , ((B.VL_UNITARIO/100)*COUNT(*))           AS valor
         , (B.VL_UNITARIO/100)                      AS valorunitario
    --   , NULL                                     AS inativador_id
    --   , NULL                                     AS autor_id
    --   , NULL                                     AS editor_id
         , E.ID                                     AS entrada_id
         , M.ID                                     AS material_id
    FROM PUBLIC.ENTRADA E
    JOIN ASIWEB.BP_ENT_ITEM I ON E.CODIGOASI::TEXT=I.CD_ENTRADA::TEXT||I.CD_UG::TEXT
    JOIN ASIWEB.BP_BEM B ON I.CD_ORGAO::TEXT=B.CD_ORGAO::TEXT AND I.CD_UG::TEXT=B.CD_UG::TEXT AND I.CD_ENTRADA::TEXT=B.CD_ENTRADA::TEXT AND I.SQ_ITEM=B.SQ_ITEM
    JOIN PUBLIC.MATERIAL M ON (M.CODIGO=B.CD_BEM_SERVICO)
    --WHERE E.DATACONTABIL >= TO_DATE('201412','YYYYMM') --<< LIMITADOR >> #COMENTAR
    GROUP BY B.CD_BEM_SERVICO, B.VL_UNITARIO, E.DATACRIACAO, E.DATAEDICAO, B.SQ_ITEM, E.ID, M.ID;
    --Maquina Ju 0,12 min

    --> DOCUMENTOS DA ENTRADA
    INSERT INTO public.documento
         (
           id
         , databloqueio
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
         )
    SELECT (row_number() OVER (ORDER BY DOC.CD_ENTRADA)) + M.MAX AS id
         , NULL                      AS databloqueio
         , NULL                      AS datainativo
         , LOCALTIMESTAMP            AS datacriacao
         , LOCALTIMESTAMP            AS dataedicao
         , 0                         AS "version"
         , DOC.DT_EMISSAO            AS dataemissao
         , DOC.NR_DOCUMENTO          AS numero
         , NULL                      AS inativador_id
         , NULL                      AS autor_id
         , NULL                      AS editor_id
         , 1 -- (SELECT ID FROM PUBLIC.ORGANIZACAO LIMIT 1) AS organizacao_id
         , CASE 
             WHEN DOC.CD_TP_DOCUMENTO='001' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoDocumento' AND CODIGO=1)
             WHEN DOC.CD_TP_DOCUMENTO='002' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoDocumento' AND CODIGO=2)
             WHEN DOC.CD_TP_DOCUMENTO='003' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoDocumento' AND CODIGO=3)
             WHEN DOC.CD_TP_DOCUMENTO='006' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoDocumento' AND CODIGO=4)
             WHEN DOC.CD_TP_DOCUMENTO='009' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoDocumento' AND CODIGO=5)
             WHEN DOC.CD_TP_DOCUMENTO='010' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoDocumento' AND CODIGO=6)
             WHEN DOC.CD_TP_DOCUMENTO='014' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoDocumento' AND CODIGO=7)
             WHEN DOC.CD_TP_DOCUMENTO='018' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoDocumento' AND CODIGO=8)             
           END                       AS dominiotipodocumento_id
         , P.ID AS emitente_id
    FROM ASIWEB.BP_ENT_DOC DOC
    --INNER JOIN ASIWEB.BP_ENTRADA E ON E.CD_ENTRADA=DOC.CD_ENTRADA  --<< LIMITADOR >> #COMENTAR
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.documento) M ON 1=1
    LEFT  JOIN PARCEIRO P ON P.CODIGO = DOC.CD_AGENTE;
    --WHERE E.DT_CONTABIL >= TO_DATE('201412','YYYYMM') --<< LIMITADOR >> #COMENTAR
    --ORDER BY DOC.CD_ENTRADA;

    --> REFERENCIA DOS DOCUMENTOS DAS ENTRADAS
    INSERT INTO public.entradadocumento
         (
           id
         , entrada_id
         , entradainativo_id
         )
    SELECT (row_number() OVER (ORDER BY DOC.CD_ENTRADA)) + M.MAX AS id
         , E.ID AS entrada_id
         , NULL AS entradainativo_id
    FROM ASIWEB.BP_ENT_DOC DOC
    --INNER JOIN ASIWEB.BP_ENTRADA E ON E.CD_ENTRADA=DOC.CD_ENTRADA  --<< LIMITADOR >> #COMENTAR
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.entradadocumento) M ON 1=1
    --WHERE E.DT_CONTABIL >= TO_DATE('201412','YYYYMM') --<< LIMITADOR >> #COMENTAR
    LEFT JOIN PUBLIC.ENTRADA E ON E.CODIGOASI=DOC.CD_ENTRADA||DOC.CD_UG
    ORDER BY DOC.CD_ENTRADA;
	--Maquina Ju 0.02 min
	
    /*
     CONSIDERAÇÕES:
     1. Como o banco do ASI mantém apenas MES/ANO da data de início da depreciacao, foram 
        acrescentados a todos os meses o dia 01;
     2. Para buscar o VALOR_LIQUIDO do bem, buscamos o último registro da tabela de depreciações,
        e calculamos a diferença entre o valor unitário do bem e VL_ACUMULADO da depreciação;
     3. A vida útil restante também foi obtida da tabela de depreciações;
     4. O conteúdo do campo SQ_BEM_PERM do ASI foi migrado para atributo CODIGO, para fazer o
        relacionamento do bem com algumas tabelas do ASI.
    */
		--> REGISTROS DO BEMPATRIMONIAL DA ENTRADA
	ALTER TABLE bempatrimonial DROP CONSTRAINT fk_2lyppwdaqeghbrhvpqd0xfsfg;
	ALTER TABLE bempatrimonial DROP CONSTRAINT fk_5wpgein2ksk4x95rwoxybd1xb;
	ALTER TABLE bempatrimonial DROP CONSTRAINT fk_63r2bp2cavldnn0x7jof98ik0;
	ALTER TABLE bempatrimonial DROP CONSTRAINT fk_6axsw7vlnwfmk3kul1i5snbw9;
	ALTER TABLE bempatrimonial DROP CONSTRAINT fk_7ln6wvjsslq6pde27mpskfkk4;
	ALTER TABLE bempatrimonial DROP CONSTRAINT fk_84t3s4qhvk3hux9nja6yvuskw;
	ALTER TABLE bempatrimonial DROP CONSTRAINT fk_b0qq4c20jxhk0ld9fd6o81cxo;
	ALTER TABLE bempatrimonial DROP CONSTRAINT fk_bms01vleymwfnpe82lu6ucw4q;
	ALTER TABLE bempatrimonial DROP CONSTRAINT fk_cgrev0ob4hgxw11tutjjlecr9;
	ALTER TABLE bempatrimonial DROP CONSTRAINT fk_ecwjmdimmqyl14q2vq4w7b1l2;
	ALTER TABLE bempatrimonial DROP CONSTRAINT fk_fggpluw08vyj2w91l51i8vnti;
	ALTER TABLE bempatrimonial DROP CONSTRAINT fk_ggja01bbgksq3nhioymv7yoxd;
	ALTER TABLE bempatrimonial DROP CONSTRAINT fk_hb7coo6b3g6vrqcxtbnvvxfy3;
	ALTER TABLE bempatrimonial DROP CONSTRAINT fk_lo2mirunnjfe1jsrfhmvtx2b6;
	ALTER TABLE bempatrimonial DROP CONSTRAINT fk_oy7vor9l4t0k163p2mgqeahm7;
	ALTER TABLE bempatrimonial DROP CONSTRAINT fk_qjtg4otjjxqsa7fkjp8or00e7;
    
    INSERT INTO public.bempatrimonial
         (
    --     id
    --   , databloqueio
    --   , datainativo
           datacriacao
         , dataedicao
         , version
         , bemprincipal
         , codigo         
         , datafinalgarantia
         , datainicialgarantia
         , datainiciodepreciacao
         , numeropatrimonial
         , numeropatrimonialnumerico
         , valoraquisicao
         , valorliquido
         , vidautiloriginal
         , vidautilatual
    --   , inativador_id
    --   , autor_id
    --   , editor_id
         , organizacao_id
    --   , bempatrimonialprincipal_id
    --   , bempatrimonialvaloranterior_id
    --   , detentor_id
         , dominiosituacaofisica_id
         , dominiostatus_id
    --   , dominiostatusdepreciacao_id
    --   , dominiotipooperacao_id
         , entradaitem_id
         , estruturaorganizacionalatual_id
         , estruturaorganizacionalcompra_id
         , fornecedor_id
         , material_id
         , depreciacaoacumulada
         , naodepreciar
    --   , responsavel_id
         , percentualnaodepreciavel
         , reavaliacaoacumulada
         , reavaliacaoperiodo
         , reducaoacumulada
         , reducaoperiodo
         , valorbrutoatual
         , valorbrutocalculo
         )
    SELECT DISTINCT
    --     NEXTVAL('bempatrimonial_id_seq')    AS id
    --   , NULL                                AS databloqueio
    --   , NULL                                AS datainativo
           I.DATACRIACAO                       AS datacriacao
         , I.DATAEDICAO                        AS dataedicao
         , 0                                   AS "version"
         ,FALSE                                AS bemprincipal
         , B.SQ_BEM_PERM                       AS codigo         
         , B.DT_GARANTIA_FIM                   AS datafinalgarantia
         , B.DT_GARANTIA_INI                   AS datainicialgarantia
         , TO_DATE(B.DT_ANOMES_INICIO_DEP,'YYYYMM') AS datainiciodepreciacao
         , B.CD_BEM_PERM                       AS numeropatrimonial
         , HASHCODE(B.CD_BEM_PERM)             AS numeropatrimonialnumerico
         , (V.VL_UNITARIO/100) 		       AS valoraquisicao
         , 0.0                                 AS valorliquido       -- ATUALIZADO EM SEGUIDA
         , B.NR_VIDA_UTIL                      AS vidautiloriginal
         , B.NR_VIDA_UTIL                      AS vidautilatual   -- ATUALIZADO EM SEGUIDA
    --   , NULL                                AS inativador_id
    --   , NULL                                AS autor_id
    --   , NULL                                AS editor_id
         , 1 -- (SELECT ID FROM PUBLIC.organizacao LIMIT 1) AS organizacao_id
    --   , NULL                                AS bempatrimonialprincipal_id
    --   , NULL                                AS bempatrimonialvaloranterior_id
    --   , NULL                                AS detentor_id
         , CASE
             WHEN B.CD_TP_SIT_FISICA='02' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoSituacaoFisica' AND CODIGO=1)
             WHEN B.CD_TP_SIT_FISICA='03' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoSituacaoFisica' AND CODIGO=2)
             WHEN B.CD_TP_SIT_FISICA='04' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoSituacaoFisica' AND CODIGO=3)
             WHEN B.CD_TP_SIT_FISICA='05' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoSituacaoFisica' AND CODIGO=4)
             WHEN B.CD_TP_SIT_FISICA='06' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoSituacaoFisica' AND CODIGO=5)
             WHEN B.CD_TP_SIT_FISICA='07' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoSituacaoFisica' AND CODIGO=6)
             WHEN B.CD_TP_SIT_FISICA='08' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoSituacaoFisica' AND CODIGO=7)
             WHEN B.CD_TP_SIT_FISICA='09' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoSituacaoFisica' AND CODIGO=8)
             WHEN B.CD_TP_SIT_FISICA='10' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoSituacaoFisica' AND CODIGO=9)
             WHEN B.CD_TP_SIT_FISICA='11' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoSituacaoFisica' AND CODIGO=10)
           END                                 AS dominiosituacaofisica_id
         , CASE
             WHEN B.CD_TP_STATUS='01' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoStatusBem' AND CODIGO=1)
             WHEN B.CD_TP_STATUS='02' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoStatusBem' AND CODIGO=2)
             WHEN B.CD_TP_STATUS='03' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoStatusBem' AND CODIGO=3)
             WHEN B.CD_TP_STATUS='04' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoStatusBem' AND CODIGO=4)
             WHEN B.CD_TP_STATUS='05' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoStatusBem' AND CODIGO=5)
             WHEN B.CD_TP_STATUS='06' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoStatusBem' AND CODIGO=6)
             WHEN B.CD_TP_STATUS='07' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoStatusBem' AND CODIGO=7)
             WHEN B.CD_TP_STATUS='08' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoStatusBem' AND CODIGO=8)
             WHEN B.CD_TP_STATUS='09' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoStatusBem' AND CODIGO=9)
           END                                 AS dominiostatus_id
    --   , NULL                                AS dominiostatusdepreciacao_id
    --   , NULL                                AS dominiotipooperacao_id  -- VERIFICAR UTILIZACAO
         , I.ID                                AS entradaitem_id
         , UND_ATUAL.ID                        AS estruturaorganizacionalatual_id
         , UND_ENTRADA.ID                      AS estruturaorganizacionalcompra_id
         , E.FORNECEDOR_ID                     AS fornecedor_id
         , I.MATERIAL_ID                       AS material_id
    --   , NULL                                AS responsavel_id   -- VERIFICAR UPDATE USANDO REGISTRO DE DETENTOR
	 , 0
	 , FALSE
	 , B.VL_PERCENT_NAO_DEPRECIAVEL/100 AS PERCENTUALNAODEPRECIAVEL
	 , 0
	 , 0
	 , 0
	 , 0
	 , (V.VL_UNITARIO/100)
	 , (V.VL_UNITARIO/100)
    FROM ASIWEB.BP_BEM B
    LEFT JOIN PUBLIC.ENTRADA E ON (E.CODIGOASI=B.CD_ENTRADA||B.CD_UG)
    LEFT JOIN PUBLIC.MATERIAL M ON (M.CODIGO=B.CD_BEM_SERVICO)
    LEFT JOIN PUBLIC.ENTRADAPATRIMONIOITEM I ON (I.ENTRADA_ID= E.ID AND I.SEQUENCIA=B.SQ_ITEM AND I.VALORUNITARIO = (B.VL_UNITARIO/100) AND I.MATERIAL_ID=M.ID)
    LEFT JOIN PUBLIC.ESTRUTURAORGANIZACIONAL UND_ATUAL ON (UND_ATUAL.CODIGO=B.CD_UL_ATUAL AND UND_ATUAL.DOMINIOTIPOESTRUTURAORGANIZACIONAL_ID = (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoEstruturaOrganizacional' AND CODIGO=3))
    LEFT JOIN PUBLIC.ESTRUTURAORGANIZACIONAL UND_ENTRADA ON (UND_ENTRADA.CODIGO=B.CD_UL_ENTRADA AND UND_ENTRADA.DOMINIOTIPOESTRUTURAORGANIZACIONAL_ID = (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoEstruturaOrganizacional' AND CODIGO=3))
    LEFT JOIN ASIWEB.BP_BEM V ON V.SQ_BEM_PERM=B.SQ_BEM_PERM
    WHERE B.CD_ENTRADA NOT IN (SELECT ENT.CD_ENTRADA FROM ASIWEB.BP_ENTRADA ENT WHERE ENT.DT_CONTABIL IS NULL);
    --  AND E.DATACONTABIL >= TO_DE('201412','YYYYMM') --<< LIMITADOR >> #COMENTAR
    --ORDER BY B.SQ_BEM_PERM;
    --Maquina Ju 0,17 min
COMMIT; -- #INI :: MOVIMENTACAO DE DAS ENTRADAS >> ENTRADA


-- #INI :: AJUSTES DADOS TABELA DEPRECIACAO >> BEMPATRIMONIAL
    --> UPDAATE BEMPATRIMONIAL >> DADOS QUE PRECISO PEGAR DA TABELA DE DEPRECIACAO
    DO $$
    DECLARE
      rBEM     RECORD;
      rDEPR    RECORD;
    BEGIN
      -- RECUPERA OS BENS PATRIMONIAIS QUE ESTAO NA TABELA DE DEPRECIACAO
      FOR rBEM IN 
        SELECT B.CODIGO::BIGINT, B.ID
        FROM BEMPATRIMONIAL B 
        WHERE B.CODIGO::BIGINT IN (SELECT DISTINCT SQ_BEM_PERM FROM ASIWEB.BP_DEPRECIACAO) 
      LOOP
        SELECT ((DEPR.VL_UNITARIO)-(DEPR.VL_DEPR_ACUM)) AS VALOR_LIQUIDO
             , DEPR.NR_MESES_DEPRECIADOS AS NR_MESES_DEPRECIADOS
             , DEPR.VL_DEPR_ACUM AS DEPR_ACUM
             , DEPR.VL_UNITARIO  AS VL_UNITARIO                                   
          FROM ASIWEB.BP_DEPRECIACAO DEPR              
         WHERE DEPR.SQ_BEM_PERM = rBEM.CODIGO 
           AND DEPR.DT_ANOMES_FECHAMENTO = (SELECT MAX(DT_ANOMES_FECHAMENTO) 
                                              FROM ASIWEB.BP_DEPRECIACAO 
                                             WHERE SQ_BEM_PERM = DEPR.SQ_BEM_PERM)
          INTO rDEPR;

        UPDATE PUBLIC.BEMPATRIMONIAL
           SET valorliquido         = rDEPR.VALOR_LIQUIDO
	         , vidautilacumulada    = rDEPR.NR_MESES_DEPRECIADOS
             , depreciacaoacumulada = rDEPR.DEPR_ACUM
             , valorbrutocalculo    = rDEPR.VL_UNITARIO
             , valorbrutoatual      = rDEPR.VL_UNITARIO              
         WHERE ID = rBEM.id;
      END LOOP;
	  --Maquina Ju 5,62 min
    END$$;    
-- #FIM :: AJUSTES DADOS TABELA DEPRECIACAO >> BEMPATRIMONIAL


-- #INI :: RETORNANDO AS CONSTRAINTS DE BEMPATRIMONIAL
  ALTER TABLE bempatrimonial
    ADD CONSTRAINT fk_2lyppwdaqeghbrhvpqd0xfsfg FOREIGN KEY (dominiosituacaofisica_id)
        REFERENCES dominio (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION;

  ALTER TABLE bempatrimonial
    ADD CONSTRAINT fk_5wpgein2ksk4x95rwoxybd1xb FOREIGN KEY (autor_id)
        REFERENCES seguranca_usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION;

  ALTER TABLE bempatrimonial
    ADD CONSTRAINT fk_63r2bp2cavldnn0x7jof98ik0 FOREIGN KEY (dominiotipooperacao_id)
        REFERENCES dominio (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION;

  ALTER TABLE bempatrimonial
     ADD CONSTRAINT fk_6axsw7vlnwfmk3kul1i5snbw9 FOREIGN KEY (entradaitem_id)
        REFERENCES entradapatrimonioitem (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION;

  ALTER TABLE bempatrimonial
    ADD CONSTRAINT fk_7ln6wvjsslq6pde27mpskfkk4 FOREIGN KEY (responsavel_id)
        REFERENCES parceiro_colaborador (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION;

  ALTER TABLE bempatrimonial
    ADD CONSTRAINT fk_84t3s4qhvk3hux9nja6yvuskw FOREIGN KEY (organizacao_id)
        REFERENCES organizacao (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION;


  ALTER TABLE bempatrimonial
    ADD CONSTRAINT fk_b0qq4c20jxhk0ld9fd6o81cxo FOREIGN KEY (bempatrimonialprincipal_id)
        REFERENCES bempatrimonial (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION;

  ALTER TABLE bempatrimonial
    ADD CONSTRAINT fk_bms01vleymwfnpe82lu6ucw4q FOREIGN KEY (detentor_id)
        REFERENCES parceiro_colaborador (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION;

  ALTER TABLE bempatrimonial
    ADD CONSTRAINT fk_cgrev0ob4hgxw11tutjjlecr9 FOREIGN KEY (inativador_id)
        REFERENCES seguranca_usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION;

  ALTER TABLE bempatrimonial
    ADD CONSTRAINT fk_ecwjmdimmqyl14q2vq4w7b1l2 FOREIGN KEY (dominiostatus_id)
        REFERENCES dominio (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION;

  ALTER TABLE bempatrimonial
    ADD CONSTRAINT fk_fggpluw08vyj2w91l51i8vnti FOREIGN KEY (estruturaorganizacionalatual_id)
        REFERENCES estruturaorganizacional (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION;

  ALTER TABLE bempatrimonial
    ADD CONSTRAINT fk_ggja01bbgksq3nhioymv7yoxd FOREIGN KEY (dominiostatusdepreciacao_id)
        REFERENCES dominio (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION;

  ALTER TABLE bempatrimonial
    ADD CONSTRAINT fk_hb7coo6b3g6vrqcxtbnvvxfy3 FOREIGN KEY (editor_id)
        REFERENCES seguranca_usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION;

  ALTER TABLE bempatrimonial
    ADD CONSTRAINT fk_lo2mirunnjfe1jsrfhmvtx2b6 FOREIGN KEY (material_id)
       REFERENCES materialpermanente (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION;

  ALTER TABLE bempatrimonial
    ADD CONSTRAINT fk_oy7vor9l4t0k163p2mgqeahm7 FOREIGN KEY (estruturaorganizacionalcompra_id)
        REFERENCES estruturaorganizacional (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION;

  ALTER TABLE bempatrimonial
    ADD CONSTRAINT fk_qjtg4otjjxqsa7fkjp8or00e7 FOREIGN KEY (fornecedor_id)
        REFERENCES parceiro (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION;
-- #FIM :: RETORNANDO AS CONSTRAINTS DE BEMPATRIMONIAL

-- #INI ::ALTERACAO NECESSARIA PORQUE NO ASI O TAMANHO DO CAMPO ERA ESSE, MUDAR NO GRP DEPOIS RETIRAR ESSE SCRIPT **JULIANA 14-10-2015

alter table bempatrimonialcaracteristica
drop column valormaterialcaracteristica;

alter table bempatrimonialcaracteristica
add column valormaterialcaracteristica character varying(4000);

-- #FIM :: ALTERACAO NECESSARIA PORQUE NO ASI O TAMANHO DO CAMPO ERA ESSE, MUDAR NO GRP DEPOIS RETIRAR ESSE SCRIPT **JULIANA 14-10-2015

-- #INI :: MIGRACAO DOS VALORES DAS CARACTERISTICAS GENERICAS DOS BENS
    DO $$
    DECLARE
      r        RECORD;
      v_DescricaoDominio character varying(255);
      v_Conteudo character varying(4000);
    BEGIN 
      -- RECUPERA AS CARACTERISTICAS DOS BENS
      FOR r IN 
                -- Na tabela de caracteristica existem mais campos que guardam valor, mas fiz uma busca e nenhum no sistema e diferente de null: cg.ds_car_date as valorData, cg.ds_car_blob as arquivo
		select carAsi.tp_dado as tipoDado, cg.ds_car_char as valorText, cg.ds_car_number as valorNumero, bem.id as bemId, mc.id as materialCaracteristicaId, car.chaveDominio as chaveDominio
		from asiweb.bp_ent_car_gen cg
		inner join asiweb.cr_caracteristica carAsi on (carAsi.cd_caracteristica = cg.cd_caracteristica)		
		inner join asiweb.bp_bem b on (b.cd_orgao = cg.cd_orgao and b.cd_ug = cg.cd_ug and b.cd_entrada = cg.cd_entrada and b.sq_item = cg.sq_item)
		inner join public.bempatrimonial bem on (bem.codigo::bigint = b.sq_bem_perm)
		inner join public.material m on (m.codigo = b.cd_bem_servico) 
		inner join public.materialcaracteristica mc on (mc.material_id = m.id and mc.codigo = cg.cd_caracteristica)
		inner join public.caracteristica car on (car.id = mc.caracteristica_id)
      LOOP

          IF (r.chaveDominio IS NOT NULL) THEN
		select descricao from dominio where chave=r.chaveDominio and codigo = r.valorText::bigint into v_DescricaoDominio;	
		v_Conteudo = v_DescricaoDominio;	
	  ELSE 
	        v_DescricaoDominio = NULL;
		  IF (r.tipoDado = 'NM') THEN
			IF (select cast(r.valorNumero as varchar) ~ '^[0-9]+$') THEN
				v_Conteudo = cast(trunc(r.valorNumero) as character varying(4000));
			ELSE
				v_Conteudo = cast(r.valorNumero as character varying(4000));
			END IF;
		  ELSE 
			v_Conteudo = r.valorText;
		  END IF;	        
          END IF;	  
          
		INSERT INTO public.bempatrimonialcaracteristica
		 (    
	    --     id
	    --   , databloqueio
	    --   , datainativo
		   datacriacao
		 , dataedicao
		 , version
		 --, arquivo
		 , valormaterialcaracteristica                           
	    --   , inativador_id
	    --   , autor_id
	    --   , editor_id
		 , bempatrimonial_id
		 , materialcaracteristica_id
		 )
		VALUES
		(
		   LOCALTIMESTAMP
		 , LOCALTIMESTAMP
		 , 0
		 , v_Conteudo
		 , r.bemId
		 , r.materialCaracteristicaId
		);
   
      END LOOP;
    END$$;
	
-- #FIM :: MIGRACAO DOS VALORES DAS CARACTERISTICAS GENERICAS DOS BENS	


-- #INI :: MIGRACAO DOS VALORES DAS CARACTERISTICAS ESPECIFICAS DOS BENS
    DO $$
    DECLARE
      r        RECORD;
    BEGIN 
      -- RECUPERA AS CARACTERISTICAS ESPECIFICAS DOS BENS
      FOR r IN                 
		-- Na tabela de caracteristicas especificas so existe valor no campo ce.ds_car_char, todas as caracteristicas desse tipo sao do tipo character
		select carAsi.tp_dado as tipoDado, ce.ds_car_char as valorText, bem.id as bemId, mc.id as materialCaracteristicaId
		from asiweb.bp_ent_car_bem ce
		inner join asiweb.cr_caracteristica carAsi on (carAsi.cd_caracteristica = ce.cd_caracteristica)				
		inner join public.bempatrimonial bem on (bem.codigo::bigint = ce.sq_bem_perm)
		inner join public.material m on (m.id = bem.material_id) 
		inner join public.materialcaracteristica mc on (mc.material_id = m.id and mc.codigo = ce.cd_caracteristica)		
      LOOP


  
          
		INSERT INTO public.bempatrimonialcaracteristica
		 (    
	    --     id
	    --   , databloqueio
	    --   , datainativo
		   datacriacao
		 , dataedicao
		 , version
		 --, arquivo
		 , valormaterialcaracteristica                           
	    --   , inativador_id
	    --   , autor_id
	    --   , editor_id
		 , bempatrimonial_id
		 , materialcaracteristica_id
		 )
		VALUES
		(
		   LOCALTIMESTAMP
		 , LOCALTIMESTAMP
		 , 0
		 , r.valorText
		 , r.bemId
		 , r.materialCaracteristicaId
		);
   
      END LOOP;
    END$$;
-- maquina ju 0,14 min
-- #FIM :: MIGRACAO DOS VALORES DAS CARACTERISTICAS ESPECIFICAS DOS BENS


BEGIN;  -- #INI :: AJUSTA ATRIBUTOS 'ISLOCALIZADORADEBENS'/'ISALMOXARIFADO'/'ISUNIDADECONSUMIDORAREQUISITANTE'
    --> ATUALIZANDO AS UNIDADES COMO LOCAL DE BENS SE EXISTIR BENS
    UPDATE ESTRUTURAORGANIZACIONALPATRIMONIO
       SET ISLOCALIZADORADEBEM=TRUE
      FROM  
         (
           SELECT BP.ESTRUTURAORGANIZACIONALATUAL_ID, COUNT(*) AS QTD
           FROM   BEMPATRIMONIAL BP   -- VERIFICA SE A ESTRUTURA POSSUI BENS NÃO BAIXADOS
           WHERE  BP.DOMINIOSTATUS_ID <> (SELECT ID FROM DOMINIO WHERE CHAVE='tipoStatusBem' AND CODIGO=2)
           GROUP  BY BP.ESTRUTURAORGANIZACIONALATUAL_ID
         ) AS B
     WHERE B.ESTRUTURAORGANIZACIONALATUAL_ID=ESTRUTURAORGANIZACIONAL_ID
       AND B.QTD > 0;
     
    --> ATUALIZANDO AS UNIDADES COMO ALMOXARIFADO SE FOI INFORMADA NA ENTRADA DE BENS
    UPDATE PUBLIC.ESTRUTURAORGANIZACIONALALMOXARIFADO
       SET ISALMOXARIFADO = TRUE
      FROM
         (
           SELECT EO.ID AS ALM_ID
           FROM PUBLIC.ESTRUTURAORGANIZACIONAL EO
           WHERE EO.DATAFIM IS NULL
           AND EO.ID IN (SELECT DISTINCT(ESTRUTURAORGANIZACIONALCOMPRA_ID) FROM PUBLIC.BEMPATRIMONIAL)
         ) AS B
    WHERE ESTRUTURAORGANIZACIONAL_ID=B.ALM_ID;
	
    --> ATUALIZANDO AS UNIDADES COMO ALMOXARIFADO SE FOI INFORMADA NA ENTRADA DE BENS
    UPDATE PUBLIC.ESTRUTURAORGANIZACIONALPATRIMONIO
       SET ISALMOXARIFADO = TRUE
      FROM
         (
           SELECT EO.ID AS ALM_ID
           FROM PUBLIC.ESTRUTURAORGANIZACIONAL EO
           WHERE EO.DATAFIM IS NULL
           AND EO.ID IN (SELECT DISTINCT(ESTRUTURAORGANIZACIONALCOMPRA_ID) FROM PUBLIC.BEMPATRIMONIAL)
         ) AS B
    WHERE ESTRUTURAORGANIZACIONAL_ID=B.ALM_ID;	

    --> ATUALIZANDO AS UNIDADES LOCALIZADORAS COMO REQUISITANTES
    UPDATE ESTRUTURAORGANIZACIONALALMOXARIFADO
       SET ISUNIDADECONSUMIDORAREQUISITANTE=TRUE
      FROM  
         (
           SELECT UL.ID FROM ASIWEB.AX_UR URQ
           INNER JOIN ASIWEB.CR_UL ULC ON URQ.CD_ORGAO = ULC.CD_ORGAO AND URQ.CD_UA = ULC.CD_UA AND URQ.CD_LOCAL = ULC.CD_LOCAL
           INNER JOIN PUBLIC.ESTRUTURAORGANIZACIONAL UL ON UL.CODIGO = ULC.CD_UL
           INNER JOIN PUBLIC.DOMINIO D ON UL.DOMINIOTIPOESTRUTURAORGANIZACIONAL_ID = D.ID AND D.CHAVE = 'tipoEstruturaOrganizacional' AND D.CODIGO=3
         ) AS U
     WHERE U.ID = ESTRUTURAORGANIZACIONAL_ID;

    --> ATUALIZANDO AS UNIDADES REQUISITANTES (SEM UL CORRESPONDENTE) COMO REQUISITANTES
    UPDATE ESTRUTURAORGANIZACIONALALMOXARIFADO
       SET ISUNIDADECONSUMIDORAREQUISITANTE=TRUE
      FROM  
         (
            SELECT UR.ID FROM PUBLIC.ESTRUTURAORGANIZACIONAL UR 
            INNER JOIN PUBLIC.DOMINIO D ON UR.DOMINIOTIPOESTRUTURAORGANIZACIONAL_ID = D.ID AND D.CHAVE = 'tipoEstruturaOrganizacional' AND D.CODIGO=4
            WHERE UR.CODIGO NOT IN 
                 (
                   SELECT URQ.CD_UR FROM ASIWEB.AX_UR URQ INNER JOIN ASIWEB.CR_UL ULC ON URQ.CD_ORGAO = ULC.CD_ORGAO AND URQ.CD_UA = ULC.CD_UA AND URQ.CD_LOCAL = ULC.CD_LOCAL
                 )
         ) AS U
     WHERE U.ID = ESTRUTURAORGANIZACIONAL_ID;
COMMIT; -- #FIM :: AJUSTA ATRIBUTOS 'ISLOCALIZADORADEBENS'/'ISALMOXARIFADO'/'ISUNIDADECONSUMIDORAREQUISITANTE


BEGIN;  -- #INI :: AJUSTES PARA SETAR OS BENS PRINCIPAIS COMO PRINCIPAIS
    UPDATE BEMPATRIMONIAL
        SET BEMPRINCIPAL = TRUE 
    WHERE CODIGO::NUMERIC IN (SELECT SQ_BEM_PERM_SUP 
                                FROM ASIWEB.BP_BEM BEM 
                               WHERE BEM.SQ_BEM_PERM_SUP IS NOT NULL);
COMMIT; -- #FIM :: AJUSTES PARA SETAR OS BENS PRINCIPAIS COMO PRINCIPAIS 


BEGIN;  -- #INI :: AJUSTES PARA SETAR OS BENS PRINCIPAIS NOS SEUS FILHOS
    UPDATE PUBLIC.BEMPATRIMONIAL  
       SET BEMPATRIMONIALPRINCIPAL_ID = D.PAI
      FROM
         ( 
           SELECT BEM.SQ_BEM_PERM                                    AS FILHO
                , (SELECT ID FROM BEMPATRIMONIAL BSUP 
                   WHERE BSUP.CODIGO::NUMERIC = BEM.SQ_BEM_PERM_SUP) AS PAI
             FROM BEMPATRIMONIAL B INNER JOIN ASIWEB.BP_BEM BEM ON (BEM.SQ_BEM_PERM = B.CODIGO::NUMERIC)               
            WHERE SQ_BEM_PERM_SUP IS NOT NULL             
         ) AS D
       WHERE D.FILHO=CODIGO::BIGINT;
COMMIT; -- #FIM :: AJUSTES PARA SETAR OS BENS PRINCIPAIS NOS SEUS FILHOS


BEGIN;  -- #INI :: AJUSTES PARA CRIAR O CODIGO UNICO DA TRANSFERENCIA
    DO $$
    BEGIN
        ALTER TABLE ASIWEB.BP_TRANSF_INT_BEM ADD COLUMN codigomigracao VARCHAR(30);
    EXCEPTION
        WHEN OTHERS THEN NULL;
    END$$;

    DO $$
    BEGIN
        ALTER TABLE ASIWEB.BP_TRANSF_INT_BEM DROP CONSTRAINT fk_tibp_bem;
    EXCEPTION
        WHEN OTHERS THEN NULL;
    END$$;

    DO $$
    BEGIN
        ALTER TABLE ASIWEB.BP_TRANSF_INT_BEM DROP CONSTRAINT fk_tibp_cont_d;
    EXCEPTION
        WHEN OTHERS THEN NULL;
    END$$;

    DO $$
    BEGIN
        ALTER TABLE ASIWEB.BP_TRANSF_INT_BEM DROP CONSTRAINT fk_tibp_cont_o;
    EXCEPTION
        WHEN OTHERS THEN NULL;
    END$$;

    DO $$
    BEGIN
        ALTER TABLE ASIWEB.BP_TRANSF_INT_BEM DROP CONSTRAINT fk_tibp_fun;
    EXCEPTION
        WHEN OTHERS THEN NULL;
    END$$;

    DO $$
    BEGIN
        ALTER TABLE ASIWEB.BP_TRANSF_INT_BEM DROP CONSTRAINT fk_tibp_prqi;
    EXCEPTION
        WHEN OTHERS THEN NULL;
    END$$;

    DO $$
    BEGIN
        ALTER TABLE ASIWEB.BP_TRANSF_INT_BEM DROP CONSTRAINT fk_tibp_tpst_o;
    EXCEPTION
        WHEN OTHERS THEN NULL;
    END$$;

    DO $$
    BEGIN
      ALTER TABLE ASIWEB.BP_TRANSF_INT_BEM DROP CONSTRAINT fk_tibp_trin;
    EXCEPTION
        WHEN OTHERS THEN NULL;
    END$$;

    --   UPDATE ASIWEB.BP_TRANSF_INT_BEM TI -- #tunar
    --      SET CODIGOMIGRACAO = D.CODIGOASI
    --     FROM
    --         ( 
    --            SELECT T.CD_TRANSFERENCIA||T.CD_UG||TO_CHAR(COALESCE(I.DT_CONTABIL, T.DT_AUTORIZACAO), 'YYYYMM') AS CODIGOASI,
    --                   I.CD_ORGAO, I.CD_UG, I.CD_TRANSFERENCIA, I.SQ_BEM_PERM
    --            FROM ASIWEB.BP_TRANSF_INT_BEM I
    --            INNER JOIN ASIWEB.BP_TRANSF_INTERNA T ON (T.CD_TRANSFERENCIA = I.CD_TRANSFERENCIA AND T.CD_UG = I.CD_UG)                        
    --        ) AS D
    --      WHERE D.CD_ORGAO = TI.CD_ORGAO 
    --        AND D.CD_TRANSFERENCIA = TI.CD_TRANSFERENCIA 
    --        AND D.CD_UG = TI.CD_UG 
    --        AND D.SQ_BEM_PERM = TI.SQ_BEM_PERM;   

            -- Para evitar o update acima que estava muito demorado resolvi criar uma tabela temporaria com todos os campos que será utilizado nas proximas inserções com isso caiu muito o tempo de execução
            CREATE TABLE PUBLIC.TMP_BP_TRANS_INT_BEM
            AS
            SELECT T.CD_TRANSFERENCIA||T.CD_UG||TO_CHAR(COALESCE(I.DT_CONTABIL, T.DT_AUTORIZACAO), 'YYYYMM') AS CODIGOMIGRACAO,
                    I.CD_ORGAO, I.CD_UG, I.CD_TRANSFERENCIA, I.SQ_BEM_PERM,I.DT_CONTABIL, T.DT_AUTORIZACAO, T.CD_UL_D, T.CD_UL_O,
                    T.DT_INCLUSAO, T.DT_TRANSFERENCIA, I.VL_UNITARIO, I.CD_CONTA_O,I.CD_CONTA_D
             FROM ASIWEB.BP_TRANSF_INT_BEM I
             INNER JOIN ASIWEB.BP_TRANSF_INTERNA T ON (T.CD_TRANSFERENCIA = I.CD_TRANSFERENCIA AND T.CD_UG = I.CD_UG);  

             CREATE INDEX IX_TMP_BP_TRANS_INT_BEM_CODIGOMIGRACAO ON PUBLIC.TMP_BP_TRANS_INT_BEM USING BTREE (CODIGOMIGRACAO);
             CREATE INDEX IX_TMP_BP_TRANS_INT_BEM_SQ_BEM_PERM ON PUBLIC.TMP_BP_TRANS_INT_BEM USING BTREE (SQ_BEM_PERM);
             CREATE INDEX IX_TMP_BP_TRANS_INT_BEM_CD_UL_O ON PUBLIC.TMP_BP_TRANS_INT_BEM USING BTREE (CD_UL_O);
			 --Maquina Ju 0,4 min
COMMIT; -- #FIM :: AJUSTES PARA CRIAR O CODIGO UNICO DA TRANSFERENCIA


BEGIN;  -- #INI :: MOVIMENTACAO DE TRANSFERENCIA INTERNA >> TRANSFERENCIA
    /*
      Observar a ocorrencia de transferencias sem itens
	  Ja arrumei o problema que estava lançando essas transferencias, conferir
    */
    INSERT INTO public.transferencia
         (       
           datacriacao
         , dataedicao
         , version
         , codigo
         , codigoasi
         , datatransferencia
         , referenciavigente
         , estruturaorganizacionaldestino_id
         , organizacao_id 
         )
    SELECT DISTINCT
           COALESCE(I.DT_INCLUSAO,I.DT_TRANSFERENCIA,I.DT_INCLUSAO) AS datacriacao
         , LOCALTIMESTAMP     AS dataedicao
         , 0                  AS "version"
         , I.CD_TRANSFERENCIA AS codigo
         , I.CODIGOMIGRACAO   AS codigoasi
         , CAST(CASE WHEN I.DT_CONTABIL IS NULL THEN I.DT_AUTORIZACAO ELSE I.DT_CONTABIL END AS DATE) AS datatransferencia    
         , CAST(CASE WHEN I.DT_CONTABIL IS NULL THEN I.DT_AUTORIZACAO ELSE I.DT_CONTABIL END AS DATE) AS referenciavigente    
         , E.ID               AS estruturaorganizacionaldestino_id
         , 1                  AS organizacao_id
    FROM PUBLIC.TMP_BP_TRANS_INT_BEM I
    --INNER JOIN ASIWEB.BP_TRANSF_INTERNA T ON (T.CD_TRANSFERENCIA = I.CD_TRANSFERENCIA AND T.CD_UG = I.CD_UG)
    INNER JOIN PUBLIC.ESTRUTURAORGANIZACIONAL E ON (E.CODIGO = I.CD_UL_D AND E.DOMINIOTIPOESTRUTURAORGANIZACIONAL_ID = (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoEstruturaOrganizacional' AND CODIGO=3))
    WHERE I.DT_AUTORIZACAO IS NOT NULL;
	--Maquina Ju 0,14 min
	
    --106656 38 SEGUNDOS
    --106656 56 SEGUNDOS EVITANDO O UPDATE DE 6 MINUTOS
    --106656 32 SEGUNDOS CORRIDO TABELA TEMPORARIA COM A JUNCAO DAS DUAS TABELAS DO ASIWEB.
    --UPDATE TOTAL DE 106656 REGISTROS EM 34 SEGUNDOS.


    -- Preciso adicionar o campo com a conta contabil destino porque o material pode sofrer alteracao de conta contabil
    -- com o tempo, e se for a conta errada para o movimentos o rmb fica errado
    DO $$
    BEGIN
        ALTER TABLE PUBLIC.TRANSFERENCIAITEM ADD COLUMN cd_conta_d VARCHAR(30);
    EXCEPTION
        WHEN OTHERS THEN NULL;
    END$$;

    INSERT INTO public.transferenciaitem
         (       
         datacriacao, 
         dataedicao,
         version,
         transferencia_id,   
         bempatrimonial_id,
         estruturaorganizacionalorigem_id,
         vl_unitario,
         cd_conta,
         cd_conta_d
         )
    SELECT T.DATACRIACAO,
           T.DATAEDICAO,
           0,
           T.ID,
           B.ID,
           E.ID,
           I.VL_UNITARIO/100,
           I.CD_CONTA_O,
           I.CD_CONTA_D
    FROM PUBLIC.TMP_BP_TRANS_INT_BEM I 
    --INNER JOIN ASIWEB.BP_TRANSF_INTERNA TR ON (TR.CD_TRANSFERENCIA = I.CD_TRANSFERENCIA AND TR.CD_UG = I.CD_UG)
    INNER JOIN PUBLIC.TRANSFERENCIA T ON (T.CODIGOASI = I.CODIGOMIGRACAO)
    INNER JOIN PUBLIC.BEMPATRIMONIAL B ON (B.CODIGO::NUMERIC = I.SQ_BEM_PERM)
    INNER JOIN PUBLIC.ESTRUTURAORGANIZACIONAL E ON (E.CODIGO = I.CD_UL_O AND E.DOMINIOTIPOESTRUTURAORGANIZACIONAL_ID = (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoEstruturaOrganizacional' AND CODIGO=3));
    -- WHERE T.REFERENCIAVIGENTE BETWEEN (to_timestamp('2010-01-01', 'YYYY-MM-DD')::timestamp without time zone) AND (to_timestamp('2015-09-21 23:59:59', 'YYYY-MM-DD HH24:MI:SS')::timestamp without time zone)
    -- WHERE I.DT_CONTABIL BETWEEN (to_timestamp('2015-01-01', 'YYYY-MM-DD')::timestamp without time zone) AND (to_timestamp('2015-09-21 23:59:59', 'YYYY-MM-DD HH24:MI:SS')::timestamp without time zone) or    
    --ORDER BY I.CD_TRANSFERENCIA;
	
	--Maquina Ju 1.83 min

    --690781 REGISTROS EM 1 MINUTO E 9 SEGUNDOS
    --690781 REGISTROS EM 59 SEGUNDOS
    --UPDATE TOTAL DE 690781 REGISTROS EM 2 MINUTOS E 13 SEGUNDOS.
    

    --“campodata”  BETWEEN (TO_TIMESTAMP(‘2015-01-01’,'YYYY-MM-DD')::timestamp without time zone) and (TO_TIMESTAMP(‘2015-01-31 23:59:59','YYYY-MM-DD HH24:MI:SS')::timestamp without time zone)

    --NAO PRECISA RODAR ESSE UPDATE, ELE E UTIL CASO PRECISEMOS PEGAR A CONTA DESTINO DE UMA TRANSFERENCIA NO BANCO DO ASI, JA QUE NAO DA PRA GARANTIR QUE A DO MATERIAL ERA A CONTA DO BEM NO MOMENTO DA TRANSFERENCIA PASSADA    
   /* 
   UPDATE public.transferenciaitem t 
      SET cd_conta_d = d.contacontabil
     FROM
         ( 
             SELECT ti.cd_conta_d as contacontabil,
            B.ID as bemseq,
            t.ID AS TRANSFERENCIA_ID
             from asiweb.bp_transf_int_bem ti        
             INNER JOIN TRANSFERENCIA T ON (T.CODIGOORIGINAL = TI.CD_TRANSFERENCIA)
             INNER JOIN BEMPATRIMONIAL B ON (B.CODIGO::NUMERIC = TI.SQ_BEM_PERM)

         ) AS D
     WHERE D.TRANSFERENCIA_ID=T.TRANSFERENCIA_ID AND D.BEMSEQ = T.BEMPATRIMONIAL_ID AND T.TRANSFERENCIA_ID IN   (SELECT ID FROM TRANSFERENCIA WHERE REFERENCIAVIGENTE BETWEEN (to_timestamp('2009-01-01', 'YYYY-MM-DD')at time zone 'UTC' ) AND (to_timestamp('2009-12-31', 'YYYY-MM-DD')at time zone 'UTC' ) );
    */

    INSERT INTO transferenciacontacontabil
         (
            datacriacao, 
            dataedicao, 
            version, 
            datatransferencia, 
            organizacao_id,
            contadestino_id,
            contaorigem_id,            
            material_id,
            codigo,
	    valortransferencia			
         )
    SELECT DISTINCT
           TC.DT_TRANSFERENCIA,
           LOCALTIMESTAMP,
           0,
           TC.DT_TRANSFERENCIA,
           1,
           CD.ID,
           CO.ID,
           M.ID,
           T.CD_TRANSFERENCIA,
	   SUM(T.VL_UNITARIO/100)
    FROM ASIWEB.BP_TRANSF_CONTA_BEM T
    INNER JOIN ASIWEB.BP_TRANSF_CONTA TC ON (TC.CD_TRANSFERENCIA = T.CD_TRANSFERENCIA)
    INNER JOIN PUBLIC.MATERIAL M ON (M.CODIGO = TC.CD_BEM_SERVICO)
    INNER JOIN PUBLIC.CONTACONTABIL CO ON (CO.CODIGO = T.CD_CONTA_O)
    INNER JOIN PUBLIC.CONTACONTABIL CD ON (CD.CODIGO = TC.CD_CONTA_D)
    GROUP BY TC.DT_TRANSFERENCIA, CD.ID, CO.ID, M.ID, T.CD_TRANSFERENCIA;
COMMIT; -- #FIM :: MOVIMENTACAO DE TRANSFERENCIA INTERNA >> TRANSFERENCIA


BEGIN;  -- #INI :: MOVIMENTACAO DE TRANSFERENCIA EXTERNA >> SAIDATEMPORARIA
    INSERT INTO public.saidatemporaria
         (
           datacriacao,
           dataedicao,
           version,
           codigo,
           datasaida,
           previsaoretorno,
           organizacao_id,
           destino_id,
           dominiotipoobjetivo_id,
           dominiotipoprojeto_id,
           estruturaorganizacional_id,
           portador_id,
           responsavel_id
         )
    SELECT CASE WHEN TRANSF.DT_INCLUSAO IS NULL THEN LOCALTIMESTAMP  ELSE TRANSF.DT_INCLUSAO END,
           LOCALTIMESTAMP,
           0,
           CAST(TRANSF.CD_TRANSFERENCIA AS TEXT),
           TRANSF.DT_TRANSFERENCIA,
           TRANSF.DT_PREV_RETORNO,
           1,
           PDESTINO.ID, 
           CASE 
             WHEN TRANSF.CD_MOTIVO_SAIDA_TEMP='02' THEN (SELECT ID FROM DOMINIO WHERE CHAVE='tipoObjetivoTransferencia' AND CODIGO=2)
             WHEN TRANSF.CD_MOTIVO_SAIDA_TEMP='04' THEN (SELECT ID FROM DOMINIO WHERE CHAVE='tipoObjetivoTransferencia' AND CODIGO=3)
             WHEN TRANSF.CD_MOTIVO_SAIDA_TEMP='06' THEN (SELECT ID FROM DOMINIO WHERE CHAVE='tipoObjetivoTransferencia' AND CODIGO=5)
           END,
           NULL,
           (SELECT ID FROM PUBLIC.ESTRUTURAORGANIZACIONAL EO WHERE EO.CODIGO = TRANSF.CD_UG),
           PPORTADOR.ID,
           PRESPONSAVEL.ID
    FROM ASIWEB.BP_TRANSF_EXTERNA TRANSF
    LEFT JOIN PARCEIRO PDESTINO ON (PDESTINO.CODIGO = TRANSF.CD_FORNEC_ORGEXT)
    LEFT JOIN PARCEIRO PPORTADOR ON (PPORTADOR.CODIGO = TRANSF.CD_PORTADOR)
    LEFT JOIN PARCEIRO PRESPONSAVEL ON (PRESPONSAVEL.CODIGO = TRANSF.CD_FUN_AUTORIZ_SAIDA)
    ORDER BY TRANSF.CD_TRANSFERENCIA;

    --SAIDA TEMPORARIA ITEM
    INSERT INTO public.saidatemporariaitem
    (
        datacriacao,
        dataedicao,
        version,
        dataretorno,
        garantiafinal,
        garantiainicial,
        sequencia,
        solucaoconserto,
        valorconserto, 
        bempatrimonial_id,
        dominiostatusanterior_id,
        dominiostatusretorno_id,
        recebedor_id,
        saidatemporaria_id,
        saidatemporariainativo_id
    )
    SELECT 
        S.DATACRIACAO,
        S.DATAEDICAO,
        0,
        TRANSFBEM.dt_recebimento,
        TRANSFBEM.dt_garantia_fim,
        TRANSFBEM.dt_garantia_ini,
        NULL,
        NULL,
        TRANSFBEM.vl_conserto,
        B.ID,
        NULL,
        NULL,
        PRECEBEDOR.ID,
        S.ID,
        NULL
    FROM ASIWEB.BP_TRANSF_EXT_BEM TRANSFBEM
    INNER JOIN PUBLIC.SAIDATEMPORARIA S ON (S.CODIGO = TRANSFBEM.CD_TRANSFERENCIA)
    LEFT JOIN PUBLIC.BEMPATRIMONIAL B ON (B.CODIGO::numeric = TRANSFBEM.SQ_BEM_PERM)
    LEFT JOIN PARCEIRO PRECEBEDOR ON (PRECEBEDOR.CODIGO = transfbem.CD_recebedor)
    ORDER BY TRANSFBEM.CD_TRANSFERENCIA;
COMMIT; -- #FIM :: MOVIMENTACAO DE TRANSFERENCIA EXTERNA >> SAIDATEMPORARIA


BEGIN;  -- #INI :: DEFINICAO DE DENTENTOR DE BEM >> DEFINICAODETENTOR
    INSERT INTO public.definicaodetentor
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , dataatribuicao
    , datafimatribuicao
    , detentorresponsavel
    , numerotermo
    , referenciavigente
    , inativador_id
    , autor_id
    , editor_id
    , organizacao_id
    , colaborador_id
    )
    SELECT (row_number() OVER (ORDER BY TD.CD_TRANSFERENCIA)) + M.MAX AS id
         , NULL                              AS databloqueio
         , NULL                              AS datainativo
         , COALESCE(TD.DT_INCLUSAO,LOCALTIMESTAMP) AS datacriacao
         , LOCALTIMESTAMP                    AS dataedicao
         , 0                                 AS "version"
         , TD.DT_TRANSFERENCIA               AS dataatribuicao
         , NULL                              AS datafimatribuicao
         , CASE WHEN TD.IN_RESPONSAVEL='S' 
             THEN TRUE
             ELSE FALSE                      
           END                               AS detentorresponsavel
         , TD.CD_TRANSFERENCIA::BIGINT       AS numerotermo
         , TD.DT_TRANSFERENCIA               AS referenciavigente
         , NULL                              AS inativador_id
         , NULL                              AS autor_id
         , NULL                              AS editor_id
         , (SELECT ID FROM PUBLIC.organizacao LIMIT 1) AS organizacao_id
         , PAR.ID                            AS colaborador_id
    FROM ASIWEB.BP_TRANSF_DETENTOR TD
    INNER JOIN ASIWEB.CR_FUNCIONARIO FC ON TD.CD_DETENTOR_D=FC.CD_FUNCIONARIO
    INNER JOIN PUBLIC.PESSOA P ON P.CODIGO=FC.CD_FUNCIONARIO
    INNER JOIN PUBLIC.PARCEIRO PAR ON PAR.PESSOA_ID=P.ID
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.definicaodetentor) M ON 1=1
    WHERE TD.CD_DETENTOR_D IS NOT NULL;

    INSERT INTO public.definicaodetentoritem
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , dataatribuicao
    , datafimatribuicao
    , sequencia
    , inativador_id
    , autor_id
    , editor_id
    , bempatrimonial_id
    , definicaodetentor_id
    )
    SELECT (row_number() OVER (ORDER BY TD.CD_TRANSFERENCIA)) + M.MAX AS id
         , NULL                              AS databloqueio
         , NULL                              AS datainativo
         , COALESCE(TD.DT_INCLUSAO,LOCALTIMESTAMP) AS datacriacao
         , LOCALTIMESTAMP                    AS dataedicao
         , NULL                              AS "version"
         , TD.DT_TRANSFERENCIA               AS dataatribuicao
         , NULL                              AS datafimatribuicao
         , TI.SQ_BEM_PERM                    AS sequencia
         , NULL                              AS inativador_id
         , NULL                              AS autor_id
         , NULL                              AS editor_id
         , BP.ID                             AS bempatrimonial_id
         , DD.ID                             AS definicaodetentor_id
    FROM ASIWEB.BP_TRANSF_DETENTOR TD
    INNER JOIN ASIWEB.BP_TRANSF_BEM_DET TI ON TD.CD_ORGAO::TEXT=TI.CD_ORGAO::TEXT AND TD.CD_TRANSFERENCIA::TEXT=TI.CD_TRANSFERENCIA::TEXT AND TD.CD_UG::TEXT=TI.CD_UG::TEXT
    INNER JOIN PUBLIC.DEFINICAODETENTOR DD ON DD.NUMEROTERMO=TD.CD_TRANSFERENCIA::BIGINT
    INNER JOIN PUBLIC.BEMPATRIMONIAL BP ON BP.CODIGO::TEXT=TI.SQ_BEM_PERM::TEXT
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.definicaodetentoritem) M ON 1=1
    WHERE TD.CD_DETENTOR_D IS NOT NULL
    ORDER BY TD.CD_TRANSFERENCIA;
COMMIT; -- #FIM :: DEFINICAO DE DENTENTOR DE BEM >> DEFINICAODETENTOR


BEGIN;  -- #INI :: ADICAO A BEM PRINCIPAL >> ADICAOBEMPRINCIPAL
    INSERT INTO public.adicaobemprincipal
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
    , bempatrimonial_id
    )
    SELECT (row_number() OVER (ORDER BY BH.DT_HISTORICO)) + M.MAX AS id
         , NULL                              AS databloqueio
         , NULL                              AS datainativo
         , COALESCE(BH.DT_HISTORICO,LOCALTIMESTAMP) AS datacriacao
         , LOCALTIMESTAMP                    AS dataedicao
         , 0                              AS "version"
         , NULL                              AS inativador_id
         , NULL                              AS autor_id
         , NULL                              AS editor_id
         , (SELECT ID FROM PUBLIC.ORGANIZACAO LIMIT 1)           AS organizacao_id
         , PRI.ID                            AS bempatrimonial_id
    FROM ASIWEB.BP_BEM BP 
    INNER JOIN ASIWEB.BP_HISTORICO BH ON BP.SQ_BEM_PERM=BH.SQ_BEM_PERM AND BP.CD_ORGAO=BH.CD_ORGAO
    INNER JOIN PUBLIC.BEMPATRIMONIAL PRI ON PRI.CODIGO::TEXT=BP.SQ_BEM_PERM_SUP::TEXT
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.adicaobemprincipal) M ON 1=1
    WHERE BH.CD_TP_HISTORICO = '01'
    ORDER BY BH.DT_HISTORICO;

    --> ITENS
    INSERT INTO public.adicaobemprincipalitem
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , datareferencia
    , inativador_id
    , autor_id
    , editor_id
    , adicaobemprincipal_id
    , adicaobemprincipalinativado_id
    , bempatrimonial_id
    )
    SELECT (row_number() OVER (ORDER BY BH.DT_HISTORICO)) + M.MAX AS id
         , NULL                              AS databloqueio
         , NULL                              AS datainativo
         , COALESCE(BH.DT_HISTORICO,LOCALTIMESTAMP) AS datacriacao
         , LOCALTIMESTAMP                    AS dataedicao
         , 0                             AS "version"
         , BH.DT_HISTORICO                   AS datareferencia
         , NULL                              AS inativador_id
         , NULL                              AS autor_id
         , NULL                              AS editor_id
         , ADC.ID                            AS adicaobemprincipal_id
         , NULL                              AS adicaobemprincipalinativado_id
         , ADI.ID                            AS bempatrimonial_id
    FROM ASIWEB.BP_BEM BP 
    INNER JOIN ASIWEB.BP_HISTORICO BH ON BP.SQ_BEM_PERM=BH.SQ_BEM_PERM AND BP.CD_ORGAO=BH.CD_ORGAO
    INNER JOIN PUBLIC.BEMPATRIMONIAL ADI ON ADI.CODIGO::TEXT=BP.SQ_BEM_PERM::TEXT
    INNER JOIN PUBLIC.BEMPATRIMONIAL PRI ON PRI.CODIGO::TEXT=BP.SQ_BEM_PERM_SUP::TEXT
    INNER JOIN PUBLIC.ADICAOBEMPRINCIPAL ADC ON ADC.BEMPATRIMONIAL_ID=PRI.ID
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.adicaobemprincipalitem) M ON 1=1
    WHERE BH.CD_TP_HISTORICO = '01'
    ORDER BY BH.DT_HISTORICO;
COMMIT; -- #FIM :: ADICAO A BEM PRINCIPAL >> ADICAOBEMPRINCIPAL


BEGIN;  -- #INI :: MOVIMENTACAO DE BAIXAS >> BAIXA
    INSERT INTO public.baixa
         (   
           id,
           datacriacao, 
           dataedicao,
           version,
           organizacao_id,
           codigo  
         )
    SELECT row_number() OVER (ORDER BY BAIXA.SQ_BAIXA),
           LOCALTIMESTAMP,
           LOCALTIMESTAMP,
           0,
           1,
           BAIXA.SQ_BAIXA
    FROM ASIWEB.BP_BAIXA BAIXA
    ORDER BY BAIXA.NR_BAIXA;

    INSERT INTO PUBLIC.BAIXAPATRIMONIO
         (   
           id,
           codigo,
           datacontabilizacao,
           dataconclusao,      
           datainicio,
           destino_id,
           portador_id,
           dominiosituacaobaixa_id,
           dominiotipobaixa_id,
           dominiotipobem_id
         )
    SELECT B.ID,
           BAIXA.NR_BAIXA,
           BAIXA.DT_CONTABIL,
           BAIXA.DT_CONTABIL,
           BAIXA.DT_INICIO_BAIXA,
           PDESTINO.ID,
           PPORTADOR.ID,
           CASE 
             WHEN BAIXA.CD_TP_SIT_BAIXA = '01' THEN (select id from dominio where chave = 'tipoSituacaoBaixa' and codigo = 1)
             WHEN BAIXA.CD_TP_SIT_BAIXA = '02' THEN (select id from dominio where chave = 'tipoSituacaoBaixa' and codigo = 2)
             WHEN BAIXA.CD_TP_SIT_BAIXA = '03' THEN (select id from dominio where chave = 'tipoSituacaoBaixa' and codigo = 3)
           END,
           CASE 
             WHEN BAIXA.CD_TP_FORMA_OP = '14' THEN (select id from dominio where chave = 'tipoBaixa' and codigo = 1)
             WHEN BAIXA.CD_TP_FORMA_OP = '15' THEN (select id from dominio where chave = 'tipoBaixa' and codigo = 2)
             WHEN BAIXA.CD_TP_FORMA_OP = '16' THEN (select id from dominio where chave = 'tipoBaixa' and codigo = 3)
             WHEN BAIXA.CD_TP_FORMA_OP = '17' THEN (select id from dominio where chave = 'tipoBaixa' and codigo = 4)
             WHEN BAIXA.CD_TP_FORMA_OP = '18' THEN (select id from dominio where chave = 'tipoBaixa' and codigo = 5)
             WHEN BAIXA.CD_TP_FORMA_OP = '19' THEN (select id from dominio where chave = 'tipoBaixa' and codigo = 6)
             WHEN BAIXA.CD_TP_FORMA_OP = '20' THEN (select id from dominio where chave = 'tipoBaixa' and codigo = 7)
             WHEN BAIXA.CD_TP_FORMA_OP = '23' THEN (select id from dominio where chave = 'tipoBaixa' and codigo = 8)
             WHEN BAIXA.CD_TP_FORMA_OP = '25' THEN (select id from dominio where chave = 'tipoBaixa' and codigo = 9)
             WHEN BAIXA.CD_TP_FORMA_OP = '26' THEN (select id from dominio where chave = 'tipoBaixa' and codigo = 10)
             WHEN BAIXA.CD_TP_FORMA_OP = '27' THEN (select id from dominio where chave = 'tipoBaixa' and codigo = 11)
             WHEN BAIXA.CD_TP_FORMA_OP = '28' THEN (select id from dominio where chave = 'tipoBaixa' and codigo = 12)
             WHEN BAIXA.CD_TP_FORMA_OP = '29' THEN (select id from dominio where chave = 'tipoBaixa' and codigo = 13)
             WHEN BAIXA.CD_TP_FORMA_OP = '30' THEN (select id from dominio where chave = 'tipoBaixa' and codigo = 14)
             WHEN BAIXA.CD_TP_FORMA_OP = '36' THEN (select id from dominio where chave = 'tipoBaixa' and codigo = 15)
        END,   
        (SELECT ID FROM DOMINIO WHERE CHAVE = 'tipoBem' AND CODIGO = 1)
    FROM PUBLIC.BAIXA B
    INNER JOIN ASIWEB.BP_BAIXA BAIXA ON (B.CODIGO = BAIXA.SQ_BAIXA) 
    LEFT JOIN PUBLIC.PARCEIRO PDESTINO ON (PDESTINO.CODIGO = BAIXA.CD_DESTINO)
    LEFT JOIN PUBLIC.PARCEIRO PPORTADOR ON (PPORTADOR.CODIGO = BAIXA.CD_PORTADOR)
    ORDER BY B.CODIGO;  

    -- PRECISEI CRIAR ESSE CAMPO PARA A MIGRACAO POIS A CONTA DO MATERIAL NA EPOCA DA BAIXA
    -- PODE SER DIFERENTE DA QUE ESTA NO BEM NO MOMENTO
    DO $$
    BEGIN
      ALTER TABLE BAIXAPATRIMONIOITEM ADD COLUMN CONTA_CONTABIL_O VARCHAR(10);
    EXCEPTION
      WHEN OTHERS THEN 
        NULL;
    END$$;

    INSERT INTO PUBLIC.BAIXAPATRIMONIOITEM
         (   
            id,
            datacriacao, 
            dataedicao,
            version,
            baixa_id,   
            bempatrimonial_id,
            dataestorno,
            vl_unitario,
            conta_contabil_o 
         )
    SELECT row_number() OVER (ORDER BY BAIXA_ITEM.SQ_BAIXA, BAIXA_ITEM.SQ_BEM_PERM),
           B.DATACRIACAO,
           B.DATAEDICAO,
           0,
           B.id,
           BEM.ID, 
           BAIXA_ITEM.DT_ESTORNO,
           BAIXA_ITEM.VL_UNITARIO/100,
           BAIXA_ITEM.CD_CONTA  
    FROM ASIWEB.BP_BAIXA_ITEM BAIXA_ITEM 
    INNER JOIN PUBLIC.BAIXA B ON (B.CODIGO = BAIXA_ITEM.SQ_BAIXA)
    INNER JOIN PUBLIC.BEMPATRIMONIAL BEM ON (BEM.CODIGO::NUMERIC = BAIXA_ITEM.SQ_BEM_PERM);
    --ORDER BY BAIXA_ITEM.SQ_BAIXA, BAIXA_ITEM.SQ_BEM_PERM;

    -- OBSERVACAO DAS BAIXAS    
    INSERT INTO public.observacao
         (   
           datacriacao, 
           dataedicao,
           version,    
           descricao,
           codigo
         )
    SELECT OBS.DT_OBSERVACAO,
           LOCALTIMESTAMP,
           0,
           OBS.DS_OBSERVACAO,
           OBS.SQ_BAIXA
    FROM ASIWEB.BP_BAIXA_OBS OBS;

    INSERT INTO public.baixaobservacao
         (   
           ID,
           BAIXA_ID
         )
    SELECT OBS.ID,
           B.ID
    FROM PUBLIC.OBSERVACAO OBS
    INNER JOIN PUBLIC.BAIXA B ON (B.CODIGO = OBS.CODIGO);

    -- DOCUMENTOS DAS BAIXAS
    DO $$
    BEGIN
      ALTER TABLE DOCUMENTO ADD COLUMN CODIGO VARCHAR(30);
    EXCEPTION
      WHEN OTHERS THEN 
        NULL;
    END$$;
	
	--Arrumando a sequencia do documento
	SELECT setval('documento_id_seq' ,(SELECT MAX(id) FROM documento),true);


    --FAVOR VERIFICAR REGRA DE NEGOCIO DA QUERY ABAIXO
    INSERT INTO public.documento
         (
           datacriacao, 
           dataedicao,
           version,
           dataemissao,
           numero,
           dominiotipodocumento_id,
           emitente_id,
           codigo
         )
    SELECT COALESCE(DOC.DT_EMISSAO, LOCALTIMESTAMP, DOC.DT_EMISSAO),
           LOCALTIMESTAMP,
           0,
           DOC.DT_EMISSAO,
           DOC.NR_DOCUMENTO,
           CASE 
             WHEN DOC.CD_TP_DOCUMENTO = '003' THEN (select id from dominio where chave='tipoDocumento' and codigo=3)
             WHEN DOC.CD_TP_DOCUMENTO = '008' THEN (select id from dominio where chave='tipoDocumento' and codigo=11)
             WHEN DOC.CD_TP_DOCUMENTO = '010' THEN (select id from dominio where chave='tipoDocumento' and codigo=12)
             WHEN DOC.CD_TP_DOCUMENTO = '014' THEN (select id from dominio where chave='tipoDocumento' and codigo=13)
             WHEN DOC.CD_TP_DOCUMENTO = '015' THEN (select id from dominio where chave='tipoDocumento' and codigo=14)
             WHEN DOC.CD_TP_DOCUMENTO = '018' THEN (select id from dominio where chave='tipoDocumento' and codigo=15)
             WHEN DOC.CD_TP_DOCUMENTO = '019' THEN (select id from dominio where chave='tipoDocumento' and codigo=16)
             WHEN DOC.CD_TP_DOCUMENTO = '020' THEN (select id from dominio where chave='tipoDocumento' and codigo=17)             
           END,
           PAR.ID,
           DOC.SQ_BAIXA::TEXT
    FROM ASIWEB.BP_BAIXA_DOC DOC
    LEFT JOIN PARCEIRO PAR ON PAR.CODIGO = DOC.CD_AGENTE
    ORDER BY DOC.SQ_BAIXA;

    --DOCUMENTOS BAIXA
    INSERT INTO public.baixadocumento
         (   
           id,
           baixa_id    
         )
    SELECT DOC.ID,
           B.ID
    FROM PUBLIC.DOCUMENTO DOC 
    INNER JOIN PUBLIC.BAIXA B ON (B.CODIGO = DOC.CODIGO::NUMERIC)
    WHERE DOC.CODIGO IS NOT NULL
    ORDER BY DOC.CODIGO;
	
    -- DOCUMENTOS DAS BAIXAS
    DO $$
    BEGIN
      UPDATE DOCUMENTO SET CODIGO = NULL;
    EXCEPTION
      WHEN OTHERS THEN 
        NULL;
    END$$;
COMMIT; -- #FIM :: MOVIMENTACAO DE BAIXAS >> BAIXA


BEGIN;  -- #INI :: MIGRACAO DE MOVIMENTOS DE ENTRADA >> CONTACONTABILMOVIMENTO
    INSERT INTO public.contacontabilmovimento
    (
    datacriacao, 
    dataedicao, 
    version, 
    datareferencia, 
    valor,             
    contacontabil_id, 
    dominiotipomovimentocontacontabil_id, 
    entrada_id
    )
    SELECT       
        LOCALTIMESTAMP,
        LOCALTIMESTAMP,
        0,
        E.DATACONTABIL,
        I.VALOR,        
        28, --(SELECT ID FROM CONTACONTABIL WHERE CODIGO = '5292'), -- CONTA ESTOQUE
        (CASE WHEN E.DOMINIOTIPOENTRADA_ID = (SELECT ID FROM DOMINIO WHERE CHAVE = 'tipoEntrada' AND CODIGO = 1) THEN (SELECT ID FROM DOMINIO WHERE CHAVE='tipoMovimentoContaContabil' AND CODIGO = 1) ELSE (SELECT ID FROM DOMINIO WHERE CHAVE='tipoMovimentoContaContabil' AND CODIGO = 3) END) as entrada,
        E.ID
    FROM PUBLIC.ENTRADAPATRIMONIOITEM I
    INNER JOIN PUBLIC.ENTRADA E ON (E.ID = I.ENTRADA_ID)
    ORDER BY E.ID;
COMMIT; -- #FIM :: MIGRACAO DE MOVIMENTOS DE ENTRADA >> CONTACONTABILMOVIMENTO


-- #INI :: MIGRACAO DAS TRANSFERENCIAS INTERNAS >> CONTACONTABILMOVIMENTO
    DO $$
    DECLARE
      r        RECORD;
      v_moventoorcamentaria BIGINT;
      v_moventextraorcamentaria	BIGINT;
	  v_tipoEntradaOrcamentaria BIGINT;
	  v_tipoEntradaExtraOrcamentaria BIGINT;
      v_movsai BIGINT;
      v_movent BIGINT;
      v_contad BIGINT;
      v_contae BIGINT;
      v_ccm_id BIGINT;
    BEGIN
      -- RECUPERA O ID DO DOMINIO ENTRADA EXTRA-ORCAMENTARIA
      SELECT ID INTO v_moventoorcamentaria
        FROM PUBLIC.DOMINIO 
       WHERE CHAVE  = 'tipoMovimentoContaContabil' 
         AND CODIGO = 1;
		 
      -- RECUPERA O ID DO DOMINIO ENTRADA EXTRA-ORCAMENTARIA
      SELECT ID INTO v_moventextraorcamentaria
        FROM PUBLIC.DOMINIO 
       WHERE CHAVE  = 'tipoMovimentoContaContabil' 
         AND CODIGO = 3;		 
		 
      SELECT ID INTO v_tipoEntradaOrcamentaria
        FROM PUBLIC.DOMINIO 
       WHERE CHAVE  = 'tipoEntrada' 
         AND CODIGO = 1;		 	 		 
 
       -- RECUPERA O ID DO DOMINIO BAIXA EXTRA-ORCAMENTARIA
      SELECT ID INTO v_movsai
        FROM PUBLIC.DOMINIO 
       WHERE CHAVE  = 'tipoMovimentoContaContabil' 
         AND CODIGO = 2;
		 
		-- RECUPERA O ID DA CONTA CONTACONTABIL ESTOQUE
        SELECT ID INTO v_contae
          FROM PUBLIC.CONTACONTABIL 
         WHERE CODIGO = '5292';
 
      -- RECUPERA AS TRANSFERENCIAS DO ALMOXARIFADO
      FOR r IN 
        SELECT T.REFERENCIAVIGENTE, TI.VL_UNITARIO, TI.CD_CONTA_D, B.ID AS ID_BEM, T.ID AS ID_TRANSF, TI.ID, E.DOMINIOTIPOENTRADA_ID
        FROM TRANSFERENCIA T 
        INNER JOIN TRANSFERENCIAITEM TI ON (TI.TRANSFERENCIA_ID = T.ID) 
        INNER JOIN BEMPATRIMONIAL B ON (B.ID = TI.BEMPATRIMONIAL_ID)   
        INNER JOIN ENTRADAPATRIMONIOITEM EI ON (EI.ID = B.ENTRADAITEM_ID)
        INNER JOIN ENTRADA E ON (E.ID = EI.ENTRADA_ID)  
        INNER JOIN ESTRUTURAORGANIZACIONAL EO ON (EO.ID = TI.ESTRUTURAORGANIZACIONALORIGEM_ID)
        WHERE TI.CD_CONTA ='5292'
      LOOP

        -- RECUPERA O ID DA CONTA CONTACONTABIL DESTINO
        SELECT ID INTO v_contad
          FROM PUBLIC.CONTACONTABIL 
         WHERE CODIGO = r.CD_CONTA_D;

         IF (v_contad IS NOT NULL) THEN
          -- RECUPERA O ID DO MOVIMENTO
          SELECT NEXTVAL('contacontabilmovimento_id_seq') INTO v_ccm_id;
  
		  IF (r.DOMINIOTIPOENTRADA_ID = v_tipoEntradaOrcamentaria) THEN
			v_movent = v_moventoorcamentaria;
		  ELSE
		    v_movent = v_moventextraorcamentaria;
		  END IF;
		  
          -- INSERE MOVIMENTO DE ENTRADA NA CONTA DESTINO
          INSERT INTO public.contacontabilmovimento
               ( id, datacriacao, dataedicao, version, datareferencia,
                 valor, contacontabil_id, dominiotipomovimentocontacontabil_id,
                 entrada_id, transferenciaconta_id, bempatrimonial_id, transferencia_id )
          VALUES 
               ( v_ccm_id, LOCALTIMESTAMP, LOCALTIMESTAMP, 0, r.REFERENCIAVIGENTE,
                 r.VL_UNITARIO, v_contad, v_movent,
                 NULL, NULL, r.ID_BEM, r.ID_TRANSF );
          
          -- INSERE AS REFERENCIAS DA TRANSFERENCIA DE PATRIMONIO
          INSERT INTO public.contacontabilmovimentopatrimonio
               ( id, bempatrimonial_id, transferencia_id )               
          VALUES 
               ( v_ccm_id, r.ID_BEM, r.ID_TRANSF );
        ELSE
          RAISE NOTICE 'Transferência sem conta destino (%)', r.ID;
        END IF;

        IF (v_contae IS NOT NULL) THEN
          -- RECUPERA O ID DO MOVIMENTO
          SELECT NEXTVAL('contacontabilmovimento_id_seq') INTO v_ccm_id;

          -- INSERE MOVIMENTO DE SAIDA DA CONTA ESTOQUE
          INSERT INTO public.contacontabilmovimento
               ( id, datacriacao, dataedicao, version, datareferencia,
                 valor, contacontabil_id, dominiotipomovimentocontacontabil_id,
                 entrada_id, transferenciaconta_id, bempatrimonial_id, transferencia_id )
          VALUES 
               ( v_ccm_id, LOCALTIMESTAMP, LOCALTIMESTAMP, 0, r.REFERENCIAVIGENTE,
                 -r.VL_UNITARIO, v_contae, v_movsai,
                 NULL, NULL, r.ID_BEM, r.ID_TRANSF );

          -- INSERE AS REFERENCIAS DA TRANSFERENCIA DE PATRIMONIO
          INSERT INTO public.contacontabilmovimentopatrimonio
               ( id, bempatrimonial_id, transferencia_id )               
          VALUES 
               ( v_ccm_id, r.ID_BEM, r.ID_TRANSF );
        ELSE
          RAISE NOTICE 'Transferência sem conta origem (%)', r.ID;
        END IF;
         
        v_contad := NULL;

      END LOOP;
    END$$;
	--Maquina Ju 1,711
-- #FIM :: MIGRACAO DAS TRANSFERENCIAS INTERNAS >> CONTACONTABILMOVIMENTO


-- #INI :: MIGRACAO DAS BAIXAS >> CONTACONTABILMOVIMENTO
    DO $$
    DECLARE
      r        RECORD;
      v_movent BIGINT;
      v_movsai BIGINT;
    BEGIN
      -- RECUPERA O ID DO DOMINIO ENTRADA EXTRA-ORCAMENTARIA
      SELECT ID INTO v_movent
        FROM PUBLIC.DOMINIO 
       WHERE CHAVE  = 'tipoMovimentoContaContabil' 
         AND CODIGO = 1;
 
       -- RECUPERA O ID DO DOMINIO BAIXA EXTRA-ORCAMENTARIA
      SELECT ID INTO v_movsai
        FROM PUBLIC.DOMINIO 
       WHERE CHAVE  = 'tipoMovimentoContaContabil' 
         AND CODIGO = 2;
 
      -- RECUPERA AS BAIXAS DE PATRIMONIO
      FOR r IN 
        SELECT B.DATACONTABILIZACAO, BI.DATAESTORNO, BI.VL_UNITARIO, CC.ID AS ID_CTA, B.ID
        FROM BAIXAPATRIMONIO B 
        INNER JOIN BAIXAPATRIMONIOITEM BI ON (BI.BAIXA_ID = B.ID) 
        INNER JOIN BEMPATRIMONIAL BEM ON (BEM.ID = BI.BEMPATRIMONIAL_ID)
        INNER JOIN CONTACONTABIL CC ON (CC.CODIGO = BI.CONTA_CONTABIL_O)
      LOOP

        --> SAIDAS DA CONTA CONTABIL ORIGEM
        IF (v_movent IS NOT NULL) AND (v_movsai IS NOT NULL) THEN
          INSERT INTO public.contacontabilmovimento
               ( datacriacao, dataedicao, version, datareferencia, 
                 valor, contacontabil_id, dominiotipomovimentocontacontabil_id, 
                 baixa_id )
          VALUES
               ( LOCALTIMESTAMP, LOCALTIMESTAMP, 0, r.DATACONTABILIZACAO,
                 -r.VL_UNITARIO, r.ID_CTA, v_movsai, r.ID );

          -- O ASI CONSIDERA UMA BAIXA ESTORNADA COMO UMA ENTRADA
          -- REGISTRAMOS SAIDA DE TODAS AS BAIXA E ENTRADA SOMENTE
          -- DAS BAIXAS ESTORNADAS
          IF (r.DATAESTORNO IS NOT NULL) THEN
            INSERT INTO public.contacontabilmovimento
                 ( datacriacao, dataedicao, version, datareferencia, 
                   valor, contacontabil_id, dominiotipomovimentocontacontabil_id, 
                   baixa_id )
            VALUES
                 ( LOCALTIMESTAMP, LOCALTIMESTAMP, 0, r.DATAESTORNO,
                   r.VL_UNITARIO, r.ID_CTA, v_movent, r.ID );
          END IF;
        END IF;
      END LOOP;
    END$$;
    -- Maquina Ju 0,14 min
-- #FIM :: MIGRACAO DAS BAIXAS >> CONTACONTABILMOVIMENTO


BEGIN;  -- #INI :: MIGRACAO DAS TRANSFERENCIAS ENTRE CONTAS >> CONTACONTABILMOVIMENTO
	--> SAIDAS DA CONTA CONTABIL
	INSERT INTO public.contacontabilmovimento
	(
	datacriacao, 
	dataedicao, 
	version, 
	datareferencia, 
	valor,             
	contacontabil_id, 
	dominiotipomovimentocontacontabil_id, 
	entrada_id,
	baixa_id,
	transferenciaconta_id
	)
	SELECT
		TN.DATACRIACAO,
		LOCALTIMESTAMP,
		0,
		TN.DATATRANSFERENCIA,
		-TN.VALORTRANSFERENCIA,
		TN.CONTAORIGEM_ID,
		(select id from dominio where chave='tipoMovimentoContaContabil' and codigo = 2) as BAIXA,
		NULL,
		NULL,
		TN.ID
	FROM PUBLIC.TRANSFERENCIACONTACONTABIL TN;

	--> ENTRADAS DA CONTA CONTABIL
	INSERT INTO public.contacontabilmovimento
	(
	datacriacao, 
	dataedicao, 
	version, 
	datareferencia, 
	valor,             
	contacontabil_id, 
	dominiotipomovimentocontacontabil_id, 
	entrada_id,
	baixa_id,
	transferenciaconta_id
	)
	SELECT
		TN.DATACRIACAO,
		LOCALTIMESTAMP,
		0,
		TN.DATATRANSFERENCIA,
		TN.VALORTRANSFERENCIA,
		TN.CONTADESTINO_ID,
		(select id from dominio where chave='tipoMovimentoContaContabil' and codigo = 3) as entradaExtraOrcamentaria,
		NULL,
		NULL,
		TN.ID
	FROM PUBLIC.TRANSFERENCIACONTACONTABIL TN;
COMMIT; -- #FIM :: MIGRACAO DAS TRANSFERENCIAS ENTRE CONTAS >> CONTACONTABILMOVIMENTO    


BEGIN;  -- #INI :: MIGRACAO DAS REAVALIACOES >> CONTACONTABILMOVIMENTO
    -- CRIEI ESSE CAMPO REAVALIACAO PARA DIFERENCIAR QUAIS REGISTROS DO CONTACONTABILMOVIMENTO
    -- SAO PROVENIENTES DESSAS MOVIMENTACOES O CORRETO E APAGAR ESSES REGISTROS FUTURAMENTE E 
    -- LINKAR A TABELA DE REAVALIACOES

    DO $$
    BEGIN
        ALTER TABLE PUBLIC.CONTACONTABILMOVIMENTOPATRIMONIO ADD COLUMN reavaliacao BOOLEAN;
    EXCEPTION
        WHEN OTHERS THEN NULL;
    END$$;

	INSERT INTO public.contacontabilmovimento
	(    
		datacriacao, 
		dataedicao, 
		version, 
		datareferencia, 
		valor,             
		contacontabil_id, 
		dominiotipomovimentocontacontabil_id, 
		entrada_id,
		transferenciaconta_id,
		bempatrimonial_id,
		transferencia_id
	)
	SELECT
		R.DT_INCLUSAO,
		LOCALTIMESTAMP,
		0,
		R.DT_REAVALIACAO,
		-I.VL_BRUTO_O,
		c.id AS CONTACONTABIL_ID,
		(select id from dominio where chave='tipoMovimentoContaContabil' and codigo = 2) as baixa,
		NULL AS ENTRADA_ID,
		NULL AS TRANSFERENCIA_CONTA_ID,
		b.id AS BEMPATRIMONIAL_ID,
		NULL		
	FROM ASIWEB.BP_NOVA_REAVALIACAO R
    INNER JOIN ASIWEB.BP_NOVA_REAVALIACAO_ITEM I ON (I.ID_REAVALIACAO = R.ID_REAVALIACAO)
    INNER JOIN BEMPATRIMONIAL B ON (B.CODIGO::NUMERIC = I.SQ_BEM_PERM)
    INNER JOIN CONTACONTABIL C ON (C.CODIGO = I.CD_CONTA)
    ORDER BY R.DT_REAVALIACAO;

	INSERT INTO public.contacontabilmovimento
	(    
		datacriacao, 
		dataedicao, 
		version, 
		datareferencia, 
		valor,             
		contacontabil_id, 
		dominiotipomovimentocontacontabil_id, 
		entrada_id,
		transferenciaconta_id,
		bempatrimonial_id,
		transferencia_id		
	)
	SELECT
		R.DT_INCLUSAO,
		LOCALTIMESTAMP,
		0,
		R.DT_REAVALIACAO,
		I.VL_BRUTO_D,
		c.id AS CONTACONTABIL_ID,
		(select id from dominio where chave='tipoMovimentoContaContabil' and codigo = 3) as entradaExtraOrcamentaria,
		NULL AS ENTRADA_ID,
		NULL AS TRANSFERENCIA_CONTA_ID,
		b.id AS BEMPATRIMONIAL_ID,
		NULL		
	FROM ASIWEB.BP_NOVA_REAVALIACAO R
	INNER JOIN ASIWEB.BP_NOVA_REAVALIACAO_ITEM I ON (I.ID_REAVALIACAO = R.ID_REAVALIACAO)
	inner join bempatrimonial b on (b.codigo::numeric = i.sq_bem_perm)
	inner join contacontabil c on (c.codigo = i.cd_conta)
	order by r.dt_reavaliacao;
	
	
	INSERT INTO public.contacontabilmovimentopatrimonio
	(
	id,
	bempatrimonial_id,
	transferencia_id,
	REAVALIACAO
	)
	SELECT 
		ID,
		BEMPATRIMONIAL_ID,
		NULL,
		TRUE
	FROM PUBLIC.CONTACONTABILMOVIMENTO where bempatrimonial_id is not null and entrada_id is null and baixa_id is null and transferencia_id is null;
	
	--É necessário setar como false o reavaliacao para todos os registros que não forem reavaliação, isso impacta em alguns relatórios dos GRP que usam no where reavaliacao = false.
	--Esse e o ultimo insert do script na contacontabilmovimentopatrimonio por isso coloquei o update aqui
	UPDATE public.contacontabilmovimentopatrimonio
	SET reavaliacao = false
	where reavaliacao is null;
	
	--Maquina Ju 0,35 min
COMMIT; -- #FIM :: MIGRACAO DAS REAVALIACOES >> CONTACONTABILMOVIMENTO


BEGIN;  -- #INI :: GERACAO DE SALDOS E CONFIGURACOES A PARTIR DE 2010-01
	--> LANCAMENTO DE SALDOS DO MES 2010-01
	INSERT INTO public.contacontabilsaldo
	(
		datacriacao, 
		dataedicao, 
		version, 
		datareferencia, 
		valor,             
		contacontabil_id
	)
    VALUES 
    (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		1204259.68,
		(select id from contacontabil where codigo = '5204') -- APARELHOS DE MEDICAO
	 ),
	 (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		1769932.33,
		(select id from contacontabil where codigo = '5206') -- 5206 APARELHOS EQUIP. COMUNICACAO
	 ),
	 (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		73199.17,
		(select id from contacontabil where codigo = '5208') -- 5208 APAR. EQUIP. P/ ESPORTE E DIVERSOES
	 ),
	 (
	 	LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		890022.33,
		(select id from contacontabil where codigo = '5212') -- 5212 "APAR. E UTENSILIOS DOMESTICOS"
	 ),
	 (
	 	LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		0.01,
		(select id from contacontabil where codigo = '5214') -- 5214 "ARMAMENTOS"
	 ),
	 (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		248403.83,
		(select id from contacontabil where codigo = '5218') -- 5218 "COLECOES  MAT. BIBLIOGRAFICOS"
 	 ),
	 (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		142538.67,
		(select id from contacontabil where codigo = '5224') -- 5224 "EQUIP. PROTECAO SEGURANCA E SOCORRO"
 	 ),
	 (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		88413.17,
		(select id from contacontabil where codigo = '5228') -- 5228 "MAQ.E EQUIP. DE NATUREZA INDUSTRIAL"
	 ),
	 (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		209049.23,
		(select id from contacontabil where codigo = '5230') -- 5230 "MAQUINAS E EQUIPAMENTOS ENERGETICOS"
	 ),
	 (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		88512.55,
		(select id from contacontabil where codigo = '5232') -- 5232 "MAQUINAS E EQUIPAMENTOS GRAFICOS"
 	 ),
	 (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		2543851.23,
		(select id from contacontabil where codigo = '5233') -- 5233 "EQUIP.P/ AUDIO VIDEO E FOTO"
 	 ),
	 (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		261173.46,
		(select id from contacontabil where codigo = '5234') -- 5234 "MAQ. UTENSILIOS E EQUIP. DIVERSOS"
 	 ),
	 (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		32553047.58,
		(select id from contacontabil where codigo = '5235') -- 5235 "EQUIP. DE PROCESSAMENTO DE DADOS"
 	 ),
	 (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		406413.78,
		(select id from contacontabil where codigo = '5236') -- 5236 "MAQ. INST.E UTENSILIOS DE ESRITORIO"
 	 ),
	 (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		28046.62,
		(select id from contacontabil where codigo = '5238') -- 5238 "MAQ., FERRAM. E UTENS. DE OFICINA"
	 ),
	 (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		42793.37,
		(select id from contacontabil where codigo = '5239') -- 5239 "EQUIPAMENTOS HIDRAULICOS E ELETRIC."
 	 ),
	 (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		115126.46,
		(select id from contacontabil where codigo = '5240') -- 5240 "MAQ. E EQUIP. AGRIC. E RODOVIARIOS"
 	 ),
	 (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		10733025.73,
		(select id from contacontabil where codigo = '5242') -- 5242 "MOBILIARIO EM GERAL"
 	 ),
	 (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		1175.56,
		(select id from contacontabil where codigo = '5244') -- 5244 "OBRAS DE ARTE E PECAS PARA MUSEU"
 	 ),
	 (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		18344.27,
		(select id from contacontabil where codigo = '5248') -- 5248 "VEICULOS DIVERSOS"
 	 ),
	 (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		41101.35,
		(select id from contacontabil where codigo = '5251') -- 5251 "PECAS NAO INCORPORAVEIS A IMOVEIS"
	 ),
	 (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		5671230.81,
		(select id from contacontabil where codigo = '5252') -- 5252 "VEICULOS DE TRACAO MECANICA"
	 ),
	 (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		11980,
		(select id from contacontabil where codigo = '5257') -- 5257 "ACESSÓRIOS PARA VEÍCULOS"
	 ),
	 (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		0,
		(select id from contacontabil where codigo = '5292') -- 5292 "BENS MÓVEIS EM ESTOQUE"
	 ),
	 (
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		334.87,
		(select id from contacontabil where codigo = '5299') -- 5299 "OUTROS MATERIAIS PERMANENTES"
	 );

	--> LANCA SALDO ZERO PARA AS DEMAIS CONTAS DE MATERIAL PEMANENTE
	INSERT INTO public.contacontabilsaldo
	(
		datacriacao, 
		dataedicao, 
		version, 
		datareferencia, 
		valor,             
		contacontabil_id
	)
	SELECT
		LOCALTIMESTAMP,
		LOCALTIMESTAMP,
		0,
		TO_DATE('2009-12-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),
		0,
		C.ID
	FROM PUBLIC.CONTACONTABIL C 
	WHERE C.ID NOT IN (SELECT CONTACONTABIL_ID FROM PUBLIC.CONTACONTABILSALDO)
	  AND C.DOMINIOTIPOMATERIAL_ID = (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoMaterial' AND CODIGO=2);
COMMIT; -- #FIM :: GERACAO DE SALDOS E CONFIGURACOES A PARTIR DE 2010-01


-- #INI :: GERACAO DE SALDOS >> CONTACONTABILSALDO
    /*
      SCRIPT QUE GERA O SALDO
      =======================
      1. Definir o mesAtual e anoAtual a partir dos quais você quer gerar o saldo
      2. Definir o mesAnterior e anoAnterior ao saldo que você quer gerar 
         (os saldos já devem estar gerados para esse mês)
      3. Apos o insert definir onde você quer que a funcao pare de gerar saldo,
         ou seja o ultimo mes de referencia fechado no banco
    */
    DO $$
    DECLARE
      v_moventOrcamentaria     int;
	  v_moventExtraOrcamentaria     int;
      v_movbai     int;
      v_matperm    int;
      v_dtaini_ant timestamp without time zone;
      v_dtafin_ant timestamp without time zone;
      v_dtaini_ref timestamp without time zone;
      v_dtafin_ref timestamp without time zone;
      v_anomes_fin timestamp without time zone;
    BEGIN
      SELECT ID INTO v_moventOrcamentaria
        FROM PUBLIC.DOMINIO 
       WHERE CHAVE  = 'tipoMovimentoContaContabil' 
         AND CODIGO = 1;
		 
      SELECT ID INTO v_moventExtraOrcamentaria
        FROM PUBLIC.DOMINIO 
       WHERE CHAVE  = 'tipoMovimentoContaContabil' 
         AND CODIGO = 3;		 
      
      SELECT ID INTO v_movbai
        FROM PUBLIC.DOMINIO
       WHERE CHAVE  = 'tipoMovimentoContaContabil'
         AND CODIGO = 2;
      
      SELECT ID INTO v_matperm
        FROM PUBLIC.DOMINIO
       WHERE CHAVE  = 'tipoMaterial'
         AND CODIGO = 2;
      
      v_anomes_fin := TO_TIMESTAMP('20151001','YYYYMMDD')::timestamp without time zone;

      v_dtaini_ant := TO_TIMESTAMP('20091201','YYYYMMDD')::timestamp without time zone;
      v_dtafin_ant := v_dtaini_ant + interval '1 month' - interval '1 second';

      v_dtaini_ref := TO_TIMESTAMP('20100101','YYYYMMDD')::timestamp without time zone;
      v_dtafin_ref := v_dtaini_ref + interval '1 month' - interval '1 second';

      LOOP 
          INSERT INTO public.contacontabilsaldo
               (
                 datacriacao
               , dataedicao
               , version
               , datareferencia
               , valor
               , contacontabil_id
               )
          SELECT LOCALTIMESTAMP
               , LOCALTIMESTAMP
               , 0
               , v_dtaini_ref
               , (T.SALDOANTERIOR+T.ENTRADAGRP)+T.SAIDAGRP AS SALDOATUAL
               , T.ID
            FROM (
                   SELECT CONTACONTABIL.ID
                        , CONTACONTABIL.CODIGO AS CONTA
                        , SALDO.VALOR          AS SALDOANTERIOR
                        , COALESCE(
                            (SELECT SUM(M.VALOR) AS ENTRADATOTAL 
                               FROM CONTACONTABILMOVIMENTO M
                              WHERE CONTACONTABIL_ID = CONTACONTABIL.ID 
                                AND M.DATAREFERENCIA BETWEEN v_dtaini_ref AND v_dtafin_ref
                                AND DOMINIOTIPOMOVIMENTOCONTACONTABIL_ID in (v_moventOrcamentaria, v_moventExtraOrcamentaria))
                            , 0) AS ENTRADAGRP
                        , COALESCE(
                            (SELECT SUM(M.VALOR) AS SAIDA 
                               FROM CONTACONTABILMOVIMENTO M 
                              WHERE CONTACONTABIL_ID = CONTACONTABIL.ID
                                AND M.DATAREFERENCIA BETWEEN v_dtaini_ref AND v_dtafin_ref
                                AND DOMINIOTIPOMOVIMENTOCONTACONTABIL_ID = v_movbai)
                            , 0) AS SAIDAGRP
                    FROM CONTACONTABIL
                    LEFT JOIN CONTACONTABILSALDO SALDO ON SALDO.CONTACONTABIL_ID = CONTACONTABIL.ID 
                          AND SALDO.DATAREFERENCIA BETWEEN v_dtaini_ant AND v_dtafin_ant
                    WHERE CONTACONTABIL.DATAINATIVO IS NULL
                      AND DOMINIOTIPOMATERIAL_ID = v_matperm
                  ) AS T
           ORDER BY ID;

           IF DATE_TRUNC('month',v_dtaini_ref) = DATE_TRUNC('month', v_anomes_fin) THEN 
				--Quando termina de gerar os saldos eu atribuo o ultimo no contacontabilsaldoanterior_id na tabela contacontabil (Necessario para o RMB e fechamento de mes)
				UPDATE PUBLIC.CONTACONTABIL C
				SET CONTACONTABILSALDOANTERIOR_ID = (SELECT S.ID FROM PUBLIC.CONTACONTABILSALDO S WHERE S.CONTACONTABIL_ID = C.ID ORDER BY DATAREFERENCIA DESC LIMIT 1);
				
             return;
           END IF;

           v_dtaini_ref := v_dtaini_ref + interval '1 month';
           v_dtafin_ref := v_dtaini_ref + interval '1 month' - interval '1 second';

           v_dtaini_ant := v_dtaini_ant + interval '1 month';
           v_dtafin_ant := v_dtaini_ant + interval '1 month' - interval '1 second';

      END LOOP;    
	  --Maquina Ju 0.02 min
    END$$;
-- #FIM :: GERACAO DE SALDOS >> CONTACONTABILSALDO


BEGIN;  -- #INI :: ZERA OS SALDOS DAS CONTAS COM VALOR NULO >> CONTACONTABILSALDO
    UPDATE CONTACONTABILSALDO SET VALOR=0.0
    WHERE VALOR IS NULL;
COMMIT; -- #FIM :: ZERA OS SALDOS DAS CONTAS COM VALOR NULO >> CONTACONTABILSALDO


-- #INI :: GERACAO DE CONFIGURACAO DE CONTAS PARA O RMA/RMB >> CONTACONTABILCONFIGURACAOREFERENCIA
    /*
    SCRIPT QUE GERA A REFERENCIA DE CONTAS
    ======================================
    1. Definir v_dtaini_ref e v_dtafin_ref com o período inicial que você quer gerar a referência
    2. Definir v_anomes_ant com o mês de referência anterior ao saldo que você quer gerar 
       (os saldos já devem estar gerados para esse mês)
    3. Apos o insert definir onde você quer quer a função pare de gerar a referência,
       ou seja o ultimo mes de referencia fechado no banco
    */
    DO $$
    DECLARE
      r  RECORD;
      v_anomes_ant timestamp without time zone;
      v_dtaini_ref timestamp without time zone;
      v_dtafin_ref timestamp without time zone;
      v_anomes_fin timestamp without time zone;
      v_rma boolean;
      v_rmb boolean;
    BEGIN

      v_anomes_fin := TO_TIMESTAMP('20151001','YYYYMMDD')::timestamp without time zone;
      v_anomes_ant := TO_TIMESTAMP('20091201','YYYYMMDD')::timestamp without time zone;
      v_dtaini_ref := TO_TIMESTAMP('20100101','YYYYMMDD')::timestamp without time zone;
      v_dtafin_ref := v_dtaini_ref + interval '1 month' - interval '1 second';

      LOOP
        FOR r IN 
          SELECT C.ID, D.NOME FROM PUBLIC.CONTACONTABIL C
          INNER JOIN PUBLIC.DOMINIO D ON (D.ID = DOMINIOTIPOMATERIAL_ID) 
          ORDER BY 1
        LOOP
          
          --> A PRINCIPIO NAO APARECE NEM NO RMA NEM NO RMB
          v_rmb := FALSE;
          v_rma := FALSE;

          --> VERIFICA SE A CONTA TEM MOVIMENTO
          IF EXISTS (SELECT 'X' FROM CONTACONTABILMOVIMENTO M 
                      WHERE M.DATAREFERENCIA BETWEEN v_dtaini_ref AND v_dtafin_ref
                        AND M.CONTACONTABIL_ID = r.ID)
          THEN
            v_rmb := (r.NOME='PERMANENTE');
            v_rma := (r.NOME='CONSUMO');
          END IF;

          --> OU SE A CONTA TEM SALDO
          IF EXISTS (SELECT 'X' FROM CONTACONTABILSALDO S 
                      WHERE S.DATAREFERENCIA BETWEEN v_dtaini_ref AND v_dtafin_ref
                        AND S.CONTACONTABIL_ID = r.ID AND S.VALOR > 0.0)
          THEN
            v_rmb := (r.NOME='PERMANENTE');
            v_rma := (r.NOME='CONSUMO');
          END IF;

          --> INSERE UM REGISTRO EM CONFIGURACAO PARA CADA CONTA / MESREFERENCIA
          INSERT INTO public.contacontabilconfiguracaoreferencia
               ( datacriacao, dataedicao, version, apresentarma,
                 apresentarmb, mesreferencia, contacontabil_id )
          VALUES 
               ( LOCALTIMESTAMP, LOCALTIMESTAMP, 0, v_rma,
                 v_rmb, v_dtaini_ref, r.ID );

        END LOOP;
       
        IF DATE_TRUNC('month',v_dtaini_ref) = DATE_TRUNC('month', v_anomes_fin) THEN 
          return;
        END IF;

        v_dtaini_ref := v_dtaini_ref + interval '1 month';
        v_dtafin_ref := v_dtaini_ref + interval '1 month' - interval '1 second';

      END LOOP;         
	  --Maquina Ju 0,02 min
    END$$;
-- #FIM :: GERACAO DE CONFIGURACAO DE CONTAS PARA O RMA/RMB >> CONTACONTABILCONFIGURACAOREFERENCIA


BEGIN;  -- #INI :: GERACAO DO HISTORICO DOS BENS PATRIMONIAIS >> HISTORICOBEMPATRIMONIAL
    /*
     ======================================
     SCRIPT QUE GERA HISTORICOS DO BEM
     ======================================
    */
    DO $$
    DECLARE
      hist_alteracao int;
      hist_entrada int;
      hist_baixa int;
      hist_adicao_bem int;
      hist_transf_interna int;      
      hist_atribuir_detentor int;

      mot_ajuste int;
      mot_atual_monetaria int;
      mot_reavaliacao int;
      mot_inventario int;
      mot_depreciacao int;
      mot_incorporacao int;
      mot_inicio_baixa int;
      mot_baixa int;
      mot_baixa_nao_autorizada int;
      mot_status_na_baixa int;
      mot_transf_bem_principal int;
      mot_transf_interna int;
      mot_detentor_bem_principal int;
      mot_responsavel_bem_principal int;
      mot_atribuicao_bem_principal int;
      mot_atribuicao_bens int;
      mot_estorno_baixa int;
      mot_estorno_item_baixa int;
      mot_exclusao_baixa int;
      mot_exclusao_item_baixa int;
      mot_definicao_detentor int;
      mot_exclusao_bem_vinculado int;
      mot_exclusao_vinculo int;
      mot_exclusao_detentor_desvinculo int;
      mot_exclusao_responsavel_desvinculo int;
      mot_definicao_responsavel int;
      mot_saida_temporaria int;
      mot_transf_detentor int;
      mot_exclusao_detentor_atrib_bem int;
      mot_exclusao_resp_atrib_bem int;
      mot_retorno_saida int;
      mot_exclusao_resp_detentor_nao_resp int;

      bens_filhos varchar;
      adicao record;
      adicaoitem record;
    BEGIN
      SELECT id FROM public.dominio WHERE chave='tipoHistorico' AND CODIGO=1 INTO hist_alteracao;
      SELECT id FROM public.dominio WHERE chave='tipoHistorico' AND CODIGO=2 INTO hist_entrada;
      SELECT id FROM public.dominio WHERE chave='tipoHistorico' AND CODIGO=3 INTO hist_baixa;
      SELECT id FROM public.dominio WHERE chave='tipoHistorico' AND CODIGO=4 INTO hist_adicao_bem;
      SELECT id FROM public.dominio WHERE chave='tipoHistorico' AND CODIGO=5 INTO hist_transf_interna;
      SELECT id FROM public.dominio WHERE chave='tipoHistorico' AND CODIGO=6 INTO hist_atribuir_detentor;

      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=1 INTO mot_ajuste;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=2 INTO mot_atual_monetaria;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=3 INTO mot_reavaliacao;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=4 INTO mot_inventario;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=5 INTO mot_depreciacao;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=6 INTO mot_incorporacao;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=7 INTO mot_inicio_baixa;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=8 INTO mot_baixa;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=9 INTO mot_baixa_nao_autorizada;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=10 INTO mot_status_na_baixa;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=11 INTO mot_transf_bem_principal;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=12 INTO mot_transf_interna;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=13 INTO mot_detentor_bem_principal;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=14 INTO mot_responsavel_bem_principal;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=15 INTO mot_atribuicao_bem_principal;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=16 INTO mot_atribuicao_bens;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=17 INTO mot_estorno_baixa;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=18 INTO mot_estorno_item_baixa;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=19 INTO mot_exclusao_baixa;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=20 INTO mot_exclusao_item_baixa;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=21 INTO mot_definicao_detentor;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=22 INTO mot_exclusao_bem_vinculado;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=23 INTO mot_exclusao_vinculo;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=24 INTO mot_exclusao_detentor_desvinculo;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=25 INTO mot_exclusao_responsavel_desvinculo;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=26 INTO mot_definicao_responsavel;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=27 INTO mot_saida_temporaria;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=28 INTO mot_transf_detentor;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=29 INTO mot_exclusao_detentor_atrib_bem;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=30 INTO mot_exclusao_resp_atrib_bem;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=31 INTO mot_retorno_saida;
      SELECT id FROM public.dominio WHERE chave='tipoMotivoAlteracaoBem' AND CODIGO=32 INTO mot_exclusao_resp_detentor_nao_resp;
      
      INSERT INTO public.historicobempatrimonial
           (
            datacriacao, 
            dataedicao, 
            version, 
            descricao, 
            valoranterior, 
            valornovo, 
            numeroprocesso,
            dataoperacao,
            organizacao_id,
            bempatrimonial_id,
            dominiotipohistorico_id,
            dominiotipomotivoalteracao_id
           )
      SELECT 
            LOCALTIMESTAMP,
            LOCALTIMESTAMP,
            0,
            'Entrada do Bem em: '||TO_CHAR(E.DATACONTABIL, 'DD-MM-YYYY')||' - Estrutura organizacional: '||eo.nome||' – Número Patrimonial: '||b.numeropatrimonial||'.' as descricao,
            null as valoranterior,
            cast(b.numeropatrimonialnumerico as varchar) as valornovo,    
            null as numeroprocesso,
            e.datacontabil as dataoperacao,
            1 as organizacao_id,
            b.id,
            hist_entrada,
            mot_incorporacao
        FROM ENTRADA E
        INNER JOIN ENTRADAPATRIMONIO EP ON (EP.ID = E.ID)
        INNER JOIN ENTRADAPATRIMONIOITEM I ON (I.ENTRADA_ID = E.ID)
        INNER JOIN ESTRUTURAORGANIZACIONAL EO ON (EO.ID = EP.ESTRUTURAORGANIZACIONAL_ID)
        INNER JOIN BEMPATRIMONIAL B ON (B.ENTRADAITEM_ID = I.ID)
        ORDER BY E.ID;

        INSERT INTO public.historicobempatrimonial
        (
            datacriacao, 
            dataedicao, 
            version, 
            descricao, 
            valoranterior, 
            valornovo, 
            numeroprocesso,
            dataoperacao,
            organizacao_id,
            bempatrimonial_id,
            dominiotipohistorico_id,
            dominiotipomotivoalteracao_id
        )
        SELECT 
            LOCALTIMESTAMP,
            LOCALTIMESTAMP,
            0,
            'Processo de Baixa Autorizado - Codigo: '||cast(bp.codigo as varchar)||'.' as descricao,
            (select nome from dominio where id = bem.dominiostatus_id) as valoranterior,
            (select nome from dominio where id = bem.dominiostatus_id) as valornovo,    
            null as numeroprocesso,
            bp.datacontabilizacao as dataoperacao,
            1 as organizacao_id,
            bem.id,
            hist_baixa,
            mot_baixa
        FROM BAIXA B
        INNER JOIN BAIXAPATRIMONIO BP ON (BP.ID = B.ID)
        INNER JOIN BAIXAPATRIMONIOITEM I ON (I.BAIXA_ID = BP.ID)
        INNER JOIN BEMPATRIMONIAL BEM ON (BEM.ID = I.BEMPATRIMONIAL_ID);

        INSERT INTO public.historicobempatrimonial
        (
            datacriacao, 
            dataedicao, 
            version, 
            descricao, 
            valoranterior, 
            valornovo, 
            numeroprocesso,
            dataoperacao,
            organizacao_id,
            bempatrimonial_id,
            dominiotipohistorico_id,
            dominiotipomotivoalteracao_id
        )
        select 
            LOCALTIMESTAMP,
            LOCALTIMESTAMP,
            0,
            'Transferência foi realizada em: '||to_char(t.datatransferencia, 'DD-MM-YYYY')||' - Nova Estrutura Organizacional: '||ed.nome||'.' as descricao,
            eo.nome as valoranterior,
            ed.nome as valornovo,    
            null as numeroprocesso,
            t.datatransferencia as dataoperacao,
            1 as organizacao_id,
            i.bempatrimonial_id,
            hist_transf_interna,
            mot_transf_interna
        from transferencia t
        inner join transferenciaitem i on (i.transferencia_id = t.id)
        inner join estruturaorganizacional ed on (ed.id = t.estruturaorganizacionaldestino_id)
        inner join estruturaorganizacional eo on (eo.id = i.estruturaorganizacionalorigem_id);

        INSERT INTO public.historicobempatrimonial
        (
            datacriacao, 
            dataedicao, 
            version, 
            descricao, 
            valoranterior, 
            valornovo, 
            numeroprocesso,
            dataoperacao,
            organizacao_id,
            bempatrimonial_id,
            dominiotipohistorico_id,
            dominiotipomotivoalteracao_id
        )
        select 
            LOCALTIMESTAMP,
            LOCALTIMESTAMP,
            0,
            'Novo Detentor Atribuído: '||p.nome||'.' as descricao,
            null as valoranterior,
            p.nome as valornovo,    
            null as numeroprocesso,
            di.dataatribuicao as dataoperacao,
            1 as organizacao_id,
            di.bempatrimonial_id,
            hist_atribuir_detentor,
            mot_definicao_detentor
        from definicaodetentor d
        inner join definicaodetentoritem di on (di.definicaodetentor_id = d.id)        
        inner join parceiro par on (par.id = d.colaborador_id)
        inner join pessoa p on (p.id = par.pessoa_id);

        --saida temporaria
        INSERT INTO public.historicobempatrimonial
        (
            datacriacao, 
            dataedicao, 
            version, 
            descricao, 
            valoranterior, 
            valornovo, 
            numeroprocesso,
            dataoperacao,
            organizacao_id,
            bempatrimonial_id,
            dominiotipohistorico_id,
            dominiotipomotivoalteracao_id
        )
        select 
            LOCALTIMESTAMP,
            LOCALTIMESTAMP,
            0,
            'Bem está em saída temporária com o objetivo: '||objetivo.descricao||'.'||CASE WHEN s.previsaoretorno is null then ' Sem previsão de retorno' else ' Data Prevista para Retorno: '||to_char(s.previsaoretorno, 'DD-MM-YYYY') end||'.'  as descricao,
            NULL as valoranterior,
            null as valornovo,    
            null as numeroprocesso,
            s.datasaida as dataoperacao,
            1 as organizacao_id,
            bem.id,
            hist_alteracao as tipo_historico,
            mot_saida_temporaria as motivo_historico
        from saidatemporaria s
        inner join saidatemporariaitem si on (si.saidatemporaria_id = s.id)        
        inner join bempatrimonial bem on (bem.id = si.bempatrimonial_id)
        inner join dominio objetivo on (objetivo.id = s.dominiotipoobjetivo_id); 
        
        INSERT INTO public.historicobempatrimonial
        (
            datacriacao, 
            dataedicao, 
            version, 
            descricao, 
            valoranterior, 
            valornovo, 
            numeroprocesso,
            dataoperacao,
            organizacao_id,
            bempatrimonial_id,
            dominiotipohistorico_id,
            dominiotipomotivoalteracao_id
        )
        select 
            LOCALTIMESTAMP,
            LOCALTIMESTAMP,
            0,
            'Retorno de saída temporária.'  as descricao,
            NULL as valoranterior,
            null as valornovo,    
            null as numeroprocesso,
            si.dataretorno as dataoperacao,
            1 as organizacao_id,
            bem.id,
            hist_alteracao as tipo_historico,
            mot_retorno_saida as motivo_historico
        from saidatemporaria s
        inner join saidatemporariaitem si on (si.saidatemporaria_id = s.id)        
        inner join bempatrimonial bem on (bem.id = si.bempatrimonial_id)
        inner join dominio status on (status.id = bem.dominiostatus_id)
        where si.dataretorno is not null;         

        FOR adicao IN SELECT * FROM ADICAOBEMPRINCIPAL loop

            for adicaoitem in select * from adicaobemprincipalitem loop
                bens_filhos = (select numeropatrimonial from bempatrimonial where id = adicaoitem.bempatrimonial_id)||',';
            end loop;

            bens_filhos = substring(bens_filhos from 1  for (char_length(bens_filhos)-1));
            
            INSERT INTO public.historicobempatrimonial
            (
                datacriacao, 
                dataedicao, 
                version, 
                descricao, 
                valoranterior, 
                valornovo, 
                numeroprocesso,
                dataoperacao,
                organizacao_id,
                bempatrimonial_id,
                dominiotipohistorico_id,
                dominiotipomotivoalteracao_id
            )
            select 
                LOCALTIMESTAMP,
                LOCALTIMESTAMP,
                0,
                'Atribuição de bens filhos. N° Patrimoniais: '||bens_filhos,
                null as valoranterior,
                null as valornovo,    
                null as numeroprocesso,
                a.datacriacao as dataoperacao,
                1 as organizacao_id,
                a.bempatrimonial_id,
                hist_adicao_bem,
                mot_atribuicao_bens
            FROM ADICAOBEMPRINCIPAL A
            WHERE A.ID = ADICAO.ID;

            INSERT INTO public.historicobempatrimonial
            (
                datacriacao, 
                dataedicao, 
                version, 
                descricao, 
                valoranterior, 
                valornovo, 
                numeroprocesso,
                dataoperacao,
                organizacao_id,
                bempatrimonial_id,
                dominiotipohistorico_id,
                dominiotipomotivoalteracao_id
            )
            select 
                LOCALTIMESTAMP,
                LOCALTIMESTAMP,
                0,
                'Bem Principal: '||(select numeropatrimonial from bempatrimonial where id = a.bempatrimonial_id) as descricao,
                null as valoranterior,
                (select numeropatrimonial from bempatrimonial where id = a.bempatrimonial_id) as valornovo,    
                null as numeroprocesso,
                i.datareferencia as dataoperacao,
                1 as organizacao_id,
                i.bempatrimonial_id,
                hist_adicao_bem,
                mot_atribuicao_bem_principal
            FROM ADICAOBEMPRINCIPAL A
            INNER JOIN ADICAOBEMPRINCIPALITEM I ON (I.ADICAOBEMPRINCIPAL_ID = A.ID)
            WHERE A.ID = ADICAO.ID;            

        END LOOP;
      
    END$$;
	--Maquina Ju 2,13 min
COMMIT; -- #FIM :: GERACAO DO HISTORICO DOS BENS PATRIMONIAIS >> HISTORICOBEMPATRIMONIAL


--===========================================================================--
-- ##FIM :: IMPORTACAO TABELAS DE MOVIMENTO DE MATERIAS PERMANENTES          --
--===========================================================================--