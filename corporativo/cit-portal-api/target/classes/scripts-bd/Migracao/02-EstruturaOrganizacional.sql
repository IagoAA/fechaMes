--===========================================================================--
-- ##INI :: IMPORTACAO DAS UNIDADES DA ESTRUTURA ORGANIZACIONAL DO ORGAO     --
--===========================================================================--

BEGIN;  -- #INI :: INICIALIZA AS TABELAS
    DELETE FROM public.seguranca_usuario_privilegio;
    DELETE FROM public.usuarioorganizacaoitem;
    DELETE FROM public.seguranca_usuario;
    DELETE FROM public.configuracaoparametrosistema;
    DELETE FROM public.configuracao;
    DELETE FROM public.organizacao;
    DELETE FROM public.localizacao;
    DELETE FROM public.estruturaorganizacional;
    DELETE FROM public.centrocusto;
    DELETE FROM public.contacontabil;
COMMIT; -- #FIM :: INICIALIZA AS TABELAS


BEGIN;  -- #INI :: IMPORTACAO DO ORGAO >> ORGANIZACAO
    INSERT INTO public.organizacao
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , codigo
    , datafim
    , datainicio
    , datareferenciavigente
    , nome
    , sigla 
    , inativador_id
    , autor_id
    , editor_id
    )
    SELECT ORG.ID_ORGAO    AS id
         , ORG.DT_BLOQUEIO AS databloqueio
         , NULL            AS datainativo
         , LOCALTIMESTAMP  AS datacriacao
         , LOCALTIMESTAMP  AS dataedicao
         , 0               AS "version"
         , ORG.CD_ORGAO    AS codigo
         , NULL            AS datafim
         , TO_DATE('19730117', 'YYYYMMDD') AS datainicio
         , TO_DATE('20150901', 'YYYYMMDD') AS datareferenciavigente
         , ORG.NM_ORGAO    AS nome
         , ORG.SG_ORGAO    AS sigla
         , NULL            AS inativador_id
         , NULL            AS autor_id
         , NULL            AS editor_id
    FROM ASIWEB.CR_ORGAO ORG
    ORDER BY ORG.ID_ORGAO;
COMMIT; -- #FIM :: IMPORTACAO DO ORGAO >> ORGANIZACAO


BEGIN;  -- #INI :: IMPORTACAO DAS INSTALACOES >> LOCALIZACAO
    INSERT INTO public.localizacao
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , codigo
    , nome
    , inativador_id
    , autor_id
    , editor_id
    , organizacao_id
    , endereco_id
    )
    SELECT L.ID_LOCALIZ    AS id
         , L.DT_BLOQUEIO   AS databloqueio
         , NULL            AS datainativo
         , LOCALTIMESTAMP  AS datacriacao
         , LOCALTIMESTAMP  AS dataedicao
         , 0               AS "version"
         , L.CD_LOCAL      AS codigo
         , L.NM_LOCAL      AS nome
         , NULL            AS inativador_id
         , NULL            AS autor_id
         , NULL            AS editor_id
         , 1               AS organizacao_id
         , E.ID            AS endereco_id
    FROM ASIWEB.CR_LOCALIZ L
    INNER JOIN PUBLIC.ENDERECO E ON E.CODIGO=L.CD_INSTALACAO
    ORDER BY L.ID_LOCALIZ;

COMMIT; -- #FIM :: IMPORTACAO DAS INSTALACOES >> LOCALIZACAO


BEGIN;  -- #INI :: IMPORTACAO DA UNIDADE GESTORA >> ESTRUTURA ORGANIZACIONAL
    INSERT INTO public.estruturaorganizacional
     (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , classificacao
    , codigo
    , datafim
    , datainicio
    , nome
    , sigla
    , inativador_id
    , autor_id
    , editor_id
    , organizacao_id
    , dominiotipoestruturaorganizacional_id
    , estruturaorganizacionalparent_id
    , localizacao_id
    )
    SELECT (row_number() OVER (ORDER BY UG.ID_UG)) + A.MAX AS id
         , UG.DT_BLOQUEIO    AS databloqueio
         , NULL              AS datainativo
         , LOCALTIMESTAMP    AS datacriacao 
         , LOCALTIMESTAMP    AS dataedicao
         , 0                 as "version"
         , UG.CD_ORGAO       AS classificacao
         , UG.CD_UG          AS codigo
         , UG.DT_BLOQUEIO    AS datafim
         , COALESCE(UG.DT_BLOQUEIO,LOCALTIMESTAMP,UG.DT_BLOQUEIO) AS datainicio
         , UG.NM_UG          AS nome
         , UG.SG_UG          AS sigla
         , NULL              AS inativador_id
         , NULL              AS autor_id
         , NULL              AS editor_id
         , 1                 AS organizacao_id
         , (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoEstruturaOrganizacional' AND CODIGO=1) AS dominiotipoestruturaorganizacional_id
         , NULL              AS estruturaorganizacionalparent_id
         , 1                 AS localizacao_id  -- COMO CR_UG NAO TEM LOCALIZACAO ATRIBUIMOS A MESMA DO ORGAO
    FROM ASIWEB.CR_UG UG
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.estruturaorganizacional) A ON 1=1
    ORDER BY UG.ID_UG;
COMMIT; -- #FIM :: IMPORTACAO DA UNIDADE GESTORA >> ESTRUTURA ORGANIZACIONAL


BEGIN;  -- #INI :: IMPORTACAO DA UNIDADE ADMINISTRATIVA >> ESTRUTURA ORGANIZACIONAL
    INSERT INTO public.estruturaorganizacional
     (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , classificacao
    , codigo
    , datafim
    , datainicio
    , nome
    , sigla
    , inativador_id
    , autor_id
    , editor_id
    , organizacao_id
    , dominiotipoestruturaorganizacional_id
    , estruturaorganizacionalparent_id
    , localizacao_id
    )
    SELECT (row_number() OVER (ORDER BY UA.ID_UA)) + A.MAX AS id
         , UA.DT_BLOQUEIO    AS databloqueio
         , NULL              AS datainativo
         , LOCALTIMESTAMP    AS datacriacao 
         , LOCALTIMESTAMP    AS dataedicao
         , 0                 as "version"
         , COALESCE(UA.CD_UA_SUP,UA.CD_UG,UA.CD_UA_SUP) AS classificacao
         , UA.CD_UA          AS codigo
         , UA.DT_BLOQUEIO    AS datafim
         , COALESCE(UA.DT_BLOQUEIO,LOCALTIMESTAMP,UA.DT_BLOQUEIO) AS datainicio
         , UA.NM_UA          AS nome
         , UA.SG_UA          AS sigla
         , NULL              AS inativador_id
         , NULL              AS autor_id
         , NULL              AS editor_id
         , 1                 AS organizacao_id
         , (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoEstruturaOrganizacional' AND CODIGO=2) AS dominiotipoestruturaorganizacional_id
         , NULL              AS estruturaorganizacionalparent_id
         , 1                 AS localizacao_id  -- COMO CR_UA NAO TEM LOCALIZACAO ATRIBUIMOS A MESMA DO ORGAO
    FROM ASIWEB.CR_UA UA
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.estruturaorganizacional) A ON 1=1
    ORDER BY UA.ID_UA;
COMMIT; -- #FIM :: IMPORTACAO DA UNIDADE ADMINISTRATIVA >> ESTRUTURA ORGANIZACIONAL


BEGIN;  -- #INI :: IMPORTACAO DA UNIDADE LOCALIZADORA >> ESTRUTURA ORGANIZACIONAL
    INSERT INTO public.estruturaorganizacional
     (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , classificacao
    , codigo
    , datafim
    , datainicio
    , nome
    , sigla
    , inativador_id
    , autor_id
    , editor_id
    , organizacao_id
    , dominiotipoestruturaorganizacional_id
    , estruturaorganizacionalparent_id
    , localizacao_id
    )
    SELECT (row_number() OVER (ORDER BY UL.ID_UL)) + A.MAX AS id
         , UL.DT_BLOQUEIO    AS databloqueio
         , NULL              AS datainativo
         , LOCALTIMESTAMP    AS datacriacao 
         , LOCALTIMESTAMP    AS dataedicao
         , 0                 AS "version"
         , UA.CODIGO         AS classificacao
         , UL.CD_UL          AS codigo
         , UL.DT_BLOQUEIO    AS datafim
         , COALESCE(UL.DT_BLOQUEIO,LOCALTIMESTAMP,UL.DT_BLOQUEIO) AS datainicio
         , L.NOME          AS nome
         , UL.SG_UL          AS sigla
         , NULL              AS inativador_id
         , NULL              AS autor_id
         , NULL              AS editor_id
         , 1                 AS organizacao_id
         , (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoEstruturaOrganizacional' AND CODIGO=3) AS dominiotipoestruturaorganizacional_id
         , UA.ID             AS estruturaorganizacionalparent_id
         , L.ID              AS localizacao_id 
    FROM ASIWEB.CR_UL UL
    INNER JOIN PUBLIC.ESTRUTURAORGANIZACIONAL UA ON UA.CODIGO = UL.CD_UA
    --INNER JOIN PUBLIC.DOMINIO D ON UA.DOMINIOTIPOESTRUTURAORGANIZACIONAL_ID = D.ID AND D.CHAVE = 'tipoEstruturaOrganizacional' AND D.CODIGO=2
    INNER JOIN PUBLIC.LOCALIZACAO L ON L.CODIGO = UL.CD_LOCAL
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.estruturaorganizacional) A ON 1=1
    ORDER BY UL.ID_UL;
COMMIT; -- #FIM :: IMPORTACAO DA UNIDADE LOCALIZADORA >> ESTRUTURA ORGANIZACIONAL


BEGIN;  -- #INI :: IMPORTACAO UNIDADE REQUISITANTE >> ESTRUTURAORGANIZACIONAL
    /*
     - Conceito diferente de UG/UA/UL
     - Unidades Requisitantes com mesma UA e mesmo LOCAL que a UL, n�o ser�o criadas
     - Estamos considerando somente as Unidades Requisitantes que n�o possuem correspondente com UL
    */
    INSERT INTO public.estruturaorganizacional
     (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , classificacao
    , codigo
    , datafim
    , datainicio
    , nome
    , sigla
    , inativador_id
    , autor_id
    , editor_id
    , organizacao_id
    , dominiotipoestruturaorganizacional_id
    , estruturaorganizacionalparent_id
    , localizacao_id
    )
    SELECT (row_number() OVER (ORDER BY UR.ID_UR)) + A.MAX AS id
         , UR.DT_BLOQUEIO    AS databloqueio
         , NULL              AS datainativo
         , LOCALTIMESTAMP    AS datacriacao 
         , LOCALTIMESTAMP    AS dataedicao
         , 0                 AS "version"
         , UR.CD_UA          AS classificacao
         , UR.CD_UR          AS codigo
         , UR.DT_BLOQUEIO    AS datafim
         , COALESCE(UR.DT_BLOQUEIO,LOCALTIMESTAMP,UR.DT_BLOQUEIO) AS datainicio
         , UR.NM_UR          AS nome
         , UR.SG_UR          AS sigla
         , NULL              AS inativador_id
         , NULL              AS autor_id
         , NULL              AS editor_id
         , 1                 AS organizacao_id
         , (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoEstruturaOrganizacional' AND CODIGO=4) AS dominiotipoestruturaorganizacional_id
         , UA.ID             AS estruturaorganizacionalparent_id
         , L.ID              AS localizacao_id 
    FROM ASIWEB.AX_UR UR
    INNER JOIN PUBLIC.ESTRUTURAORGANIZACIONAL UA ON UA.CODIGO = UR.CD_UA 
    INNER JOIN PUBLIC.DOMINIO D ON UA.DOMINIOTIPOESTRUTURAORGANIZACIONAL_ID = D.ID AND D.CHAVE = 'tipoEstruturaOrganizacional' AND D.CODIGO=2
    INNER JOIN PUBLIC.LOCALIZACAO L ON L.CODIGO = UR.CD_LOCAL
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.estruturaorganizacional) A ON 1=1
    WHERE UR.CD_UR NOT IN 
         (
           SELECT URQ.CD_UR FROM ASIWEB.AX_UR URQ INNER JOIN ASIWEB.CR_UL ULC ON URQ.CD_ORGAO = ULC.CD_ORGAO AND URQ.CD_UA = ULC.CD_UA AND URQ.CD_LOCAL = ULC.CD_LOCAL
         )
    ORDER BY UR.ID_UR;
COMMIT; -- #FIM :: IMPORTACAO UNIDADE REQUISITANTE >> ESTRUTURAORGANIZACIONAL


BEGIN;  -- #INI :: REPLICACAO ESTRUTURAORGANIZACIONAL >> ESTRUTURAORGANIZACIONALPATRIMONIO
    INSERT INTO public.estruturaorganizacionalpatrimonio
    (
      databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version    
    , isalmoxarifado
    , islocalizadoradebem
    , inativador_id
    , autor_id
    , editor_id
    , estruturaorganizacional_id
    )
    SELECT NULL             AS databloqueio
         , NULL             AS datainativo
         , LOCALTIMESTAMP   AS datacriacao
         , LOCALTIMESTAMP   AS dataedicao
         , 0                AS version
         , FALSE            AS isalmoxarifado
         , FALSE            AS islocalizadoradebem
         , NULL             AS inativador_id
         , NULL             AS autor_id
         , NULL             AS editor_id
         , E.ID             AS estruturaorganizacional_id
    FROM ESTRUTURAORGANIZACIONAL E;    
COMMIT; -- #FIM :: REPLICACAO ESTRUTURAORGANIZACIONAL >> ESTRUTURAORGANIZACIONALPATRIMONIO


BEGIN;  -- #INI :: REPLICACAO ESTRUTURAORGANIZACIONAL >> ESTRUTURAORGANIZACIONALALMOXARIFADO
    INSERT INTO public.estruturaorganizacionalalmoxarifado
    ( 
      databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , diasrequisicao
    , isalmoxarifado
    , isunidadeconsumidorarequisitante
    , inativador_id
    , autor_id
    , editor_id
    , estruturaorganizacional_id
    )
    SELECT NULL             AS databloqueio
         , NULL             AS datainativo
         , LOCALTIMESTAMP   AS datacriacao
         , LOCALTIMESTAMP   AS dataedicao
         , 0                AS version
         , NULL             AS diasrequisicao
         , FALSE            AS isalmoxarifado
         , FALSE            AS isunidadeconsumidorarequisitante
         , NULL             AS inativador_id
         , NULL             AS autor_id
         , NULL             AS editor_id
         , E.ID             AS estruturaorganizacional_id
    FROM ESTRUTURAORGANIZACIONAL E;    
COMMIT; -- #FIM :: REPLICACAO ESTRUTURAORGANIZACIONAL >> ESTRUTURAORGANIZACIONALALMOXARIFADO


BEGIN;  -- #INI :: AJUSTAR PARENT_ID COM BASE NO CD_UA_SUP IMPORTADO PARA CLASSIFICACAO
    UPDATE public.estruturaorganizacional
       SET estruturaorganizacionalparent_id = ep.parent_id
      FROM 
         ( SELECT f.id, p.id AS parent_id
             FROM public.estruturaorganizacional p  INNER JOIN 
                  public.estruturaorganizacional f  ON f.classificacao = p.codigo
         ) AS ep
     WHERE public.estruturaorganizacional.id = ep.id
       AND public.estruturaorganizacional.id <> 1;
COMMIT; -- #FIM :: AJUSTAR PARENT_ID COM BASE NO CD_UA_SUP IMPORTADO PARA CLASSIFICACAO


BEGIN;  -- #INI :: IMPORTACAO DOS CENTROS DE CUSTO >> CENTROCUSTO
    INSERT INTO public.centrocusto
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , codigo
    , descricao
    , inativador_id
    , autor_id
    , editor_id
    , organizacao_id
    , centrocustoparent_id
    )
    SELECT CC.ID_CENTRO_CUSTO AS id
         , NULL               AS databloqueio
         , NULL               AS datainativo
         , LOCALTIMESTAMP     AS datacriacao
         , LOCALTIMESTAMP     AS dataedicao
         , 0                  AS "version"
         , CC.CD_CENTRO_CUSTO AS codigo
         , CC.NM_CENTRO_CUSTO AS descricao
         , NULL               AS inativador_id
         , NULL               AS autor_id
         , NULL               AS editor_id
         , 1                  AS organizacao_id
         , NULL               AS centrocustoparent_id 
         -- DISTINCT CD_CENTRO_CUSTO_SUP RETORNOU NULL
    FROM ASIWEB.CR_CENTRO_CUSTO CC
    ORDER BY CC.ID_CENTRO_CUSTO;
COMMIT; -- #FIM :: IMPORTACAO DOS CENTROS DE CUSTO >> CENTROCUSTO


BEGIN;  -- #INI :: IMPORTACAO DAS CONTAS CONTABEIS >> CONTACONTABIL
    INSERT INTO public.contacontabil
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , codigo
    , codigosistemaorigem
    , descricao
    , percentualnaodepreciavel
    , taxadepreciacaomensal
    , vidautil
    , inativador_id
    , autor_id
    , editor_id
    , organizacao_id
    , contacontabilsaldoanterior_id
    , dominiotipocontacontabil_id
    , dominiotipomaterial_id
    )
    SELECT (row_number() OVER (ORDER BY CC.ID_CONTA)) + M.MAX AS id
         , CC.DT_BLOQUEIO     AS databloqueio
         , NULL               AS datainativo
         , LOCALTIMESTAMP     AS datacriacao
         , LOCALTIMESTAMP     AS dataedicao
         , 0                  AS "version"
         , CC.CD_CONTA        AS codigo
         , NULL               AS codigosistemaorigem
         , CC.NM_CONTA        AS descricao
         , (CC.VL_PERCENT_NAO_DEPRECIAVEL/100) AS percentualnaodepreciavel
         , (case when cc.nr_vida_util = 0 or cc.nr_vida_util is null then 0.0 else 1/cc.nr_vida_util end)  AS taxadepreciacaomensal 
         , COALESCE(CC.NR_VIDA_UTIL, 0.0) AS vidautil
         , NULL               AS inativador_id
         , NULL               AS autor_id
         , NULL               AS editor_id
         , 1 AS organizacao_id
         , NULL               AS contacontabilsaldoanterior_id
         , (SELECT ID FROM PUBLIC.DOMINIO   -- Todas como contas do "Ativo"
             WHERE CHAVE='tipoContaContabil' 
               AND CODIGO=5)  AS dominiotipocontacontabil_id
         , CASE 
             WHEN CC.CD_SISTEMA = 'AX' THEN  -- Material de consumo
               (select id from public.dominio where chave = 'tipoMaterial' and codigo = 1) 
             WHEN CC.CD_SISTEMA = 'PA' THEN  -- Material de permanente
               (select id from public.dominio where chave = 'tipoMaterial' and codigo = 2)
           END                AS dominiotipomaterial_id
    FROM ASIWEB.CR_CONTA CC
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.contacontabil) M ON 1=1
    ORDER BY CC.ID_CONTA;
COMMIT; -- #FIM :: IMPORTACAO DAS CONTAS CONTABEIS >> CONTACONTABIL


BEGIN;  -- #INI :: DEFINE UMA CONFIGURACAO PADRAO DO ORGAO
    DO $$
    DECLARE
      v_conta_id numeric;
    BEGIN
      SELECT id INTO v_conta_id FROM public.contacontabil WHERE codigo = '5292' LIMIT 1;
      IF v_conta_id IS NOT NULL THEN
        INSERT INTO configuracao
             (id, databloqueio, datainativo, datacriacao, dataedicao, version,
              inativador_id, autor_id, editor_id, anexoimagem_id, organizacao_id) 
        VALUES
             (1, NULL, NULL, LOCALTIMESTAMP, LOCALTIMESTAMP, 0,
              NULL, NULL, NULL, NULL, 1);
      
        INSERT INTO configuracaoparametrosistema
             (datacriacao, dataedicao, version, grupo, chave, valor, configuracao_id) 
        VALUES 
             (now(), now(), 0, null, 'EXECUTAR_SCRIPT_MENU-1', 'false', 1)
            ,(now(), now(), 0, null, 'EXECUTAR_SCRIPT_MENU-2', 'false', 1)
            ,(now(), now(), 0, null, 'EXECUTAR_SCRIPT_MENU-3', 'false', 1)
            ,(now(), now(), 0, null, 'EXECUTAR_SCRIPT_MENU-4', 'false', 1)
            ,(now(), now(), 0, null, 'EXECUTAR_SCRIPT_MENU-5', 'false', 1)
            ,(now(), now(), 0, null, 'EXECUTAR_SCRIPT_MENU-6', 'false', 1)
            ,(now(), now(), 0, null, 'EXECUTAR_SCRIPT_MENU-7', 'false', 1)
            ,(now(), now(), 0, null, 'EXECUTAR_SCRIPT_MENU', 'false', 1)
            ,(now(), now(), 0, null, 'EXECUTAR_DOMINIOS', 'false', 1)
            ,(now(), now(), 0, null, 'EXECUTAR_DEFAULT_FILE', 'false', 1)
            ,(now(), now(), 0, null, 'EXECUTAR_MODULO', 'false', 1)
            ,(now(), now(), 0, null, 'EXECUTAR_INTERNACIONALIZACAO', 'true', 1)
            ,(now(), now(), 0, null, 'PATRIMONIO_MASCARA_NUMERO_PATRIMONIO', '999999', 1)
            ,(now(), now(), 0, null, 'CONTA_CONTABIL_ALMOXARIFADO', cast((select id from contacontabil where codigo = '5292') as text), 1)
            ,(now(), now(), 0, null, 'TIPO_AVALIACAO_MONETARIA_ESTOQUE', '2', 1)
            ,(now(), now(), 0, null, 'RELATORIO_PRIMEIRO_TITULO', 'Minist�rio do Planejamento Or�amento e Gest�o - MP', 1)
            ,(now(), now(), 0, null, 'RELATORIO_SEGUNDO_TITULO', 'Diretoria de Administra��o - DIRAD', 1)
            ,(now(), now(), 0, null, 'RELATORIO_TERCEIRO_TITULO', 'Coordena��o-Geral de Administra��o Predial - CGDAP', 1)
            ,(now(), now(), 0, null, 'MASCARA_NUMERO_IDENTIFICACAO', 'AAAA999999', 1);
      ELSE
        RAISE NOTICE 'ID conta_contabil_5292 N�O ENCONTRADO!';
      END IF;
    END$$;
COMMIT; -- #FIM :: DEFINE UMA CONFIGURACAO PADRAO DO ORGAO


BEGIN;  -- #INI :: DEFINE UM MAPA ORGANIZACIONAL PADRAO DO ORGAO
    INSERT INTO public.mapaorganizacional
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , codigo
    , datafim
    , datainicio
    , nome
    , inativador_id
    , autor_id
    , editor_id
    , organizacao_id
    )
    VALUES 
    (
      NEXTVAL('mapaorganizacional_id_seq')
    , NULL
    , NULL
    , LOCALTIMESTAMP
    , LOCALTIMESTAMP
    , 0
    , '2015000001'
    , NULL
    , LOCALTIMESTAMP
    , 'MAPA - MIGRACAO ASI (2015)'
    , NULL
    , NULL
    , NULL
    , 1
    );
COMMIT; -- #FIM :: DEFINE UM MAPA ORGANIZACIONAL PADRAO DO ORGAO


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


--===========================================================================--
-- ##FIM :: IMPORTACAO DAS ESTRUTURAS DO ORGAO                               --
--===========================================================================--