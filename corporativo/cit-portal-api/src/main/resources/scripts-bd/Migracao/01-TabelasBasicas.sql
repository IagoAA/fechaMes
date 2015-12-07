--===========================================================================--
-- ##INI :: IMPORTACAO TABELAS BASICAS                                       --
--===========================================================================--


BEGIN;  -- #INI :: INICIALIZA AS TABELAS
    DELETE FROM public.pais;
    DELETE FROM public.regiao;
    DELETE FROM public.estado;
    DELETE FROM public.cidade;
    DELETE FROM public.bairro;
    DELETE FROM public.endereco;
    DELETE FROM public.unidademedida;
COMMIT; -- #FIM :: INICIALIZA AS TABELAS


BEGIN;  -- #INI :: IMPORTACAO DA TABELA DE PAISES >> PAIS
    INSERT INTO public.pais
    (
       id
     , databloqueio
     , datainativo
     , datacriacao
     , dataedicao
     , version
     , codigo
     , nome
     , sigla
     , inativador_id
     , autor_id
     , editor_id 
    )
    SELECT PAIS.ID_PAIS       AS id
         , PAIS.DT_BLOQUEIO   AS databloqueio
         , NULL               AS datainativo
         , LOCALTIMESTAMP     AS datacriacao
         , LOCALTIMESTAMP     AS dataedicao
         , 0                  AS "version"
         , PAIS.CD_PAIS       AS codigo
         , PAIS.NM_PAIS       AS nome
         , PAIS.NM_SIGLA_PAIS AS sigla
         , NULL               AS inativador_id
         , NULL               AS autor_id
         , NULL               AS editor_id
    FROM ASIWEB.CR_PAIS PAIS
    ORDER BY PAIS.ID_PAIS;
COMMIT; -- #FIM :: IMPORTACAO DA TABELA DE PAISES >> PAIS


BEGIN;  -- #INI :: IMPORTACAO DA TABELA DE REGIOES >> REGIAO
    INSERT INTO public.regiao
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
    , pais_id
    )
    SELECT REGIAO.ID_REGIAO_UF AS id
         , REGIAO.DT_BLOQUEIO  AS databloqueio
         , NULL                AS datainativo
         , LOCALTIMESTAMP      AS datacriacao
         , LOCALTIMESTAMP      AS dataedicao
         , 0                   AS "version"
         , REGIAO.CD_REGIAO    AS codigo
         , REGIAO.NM_REGIAO    AS nome
         , NULL                AS inativador_id
         , NULL                AS autor_id
         , NULL                AS editor_id
         , CASE REGIAO.NM_REGIAO 
             WHEN 'AMERICA DO NORTE' THEN 2 
             ELSE 1 
           END                 AS pais_id
    FROM ASIWEB.CR_REGIAO_UF REGIAO
    ORDER BY REGIAO.ID_REGIAO_UF;
COMMIT; -- #FIM :: IMPORTACAO DA TABELA DE REGIOES >> REGIAO


BEGIN;  -- #INI :: IMPORTACAO DAS UNIDADES FEDERATIVAS >> ESTADO
    INSERT INTO public.estado
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , codigo
    , nome
    , sigla
    , inativador_id
    , autor_id
    , editor_id 
    , regiao_id
    )
    SELECT UF.ID_UF       AS id
         , UF.DT_BLOQUEIO AS databloqueio
         , NULL           AS datainativo
         , LOCALTIMESTAMP AS datacriacao
         , LOCALTIMESTAMP AS dataedicao
         , 0              AS "version"
         , UF.CD_UF       AS codigo
         , UF.NM_UF       AS nome
         , UF.NM_SIGLA_UF AS sigla
         , NULL           AS inativador_id
         , NULL           AS autor_id
         , NULL           AS editor_id
         , CASE 
             WHEN UF.NM_SIGLA_UF IN ('MT','GO','MS','DF') 
               THEN 1 -- CENTRO OESTE
             WHEN UF.NM_SIGLA_UF IN ('ES','RJ','SP','MG')
               THEN 2 -- SUDESTE
             WHEN UF.NM_SIGLA_UF IN ('RS','PR','SC') 
               THEN 3 -- SUL
             WHEN UF.NM_SIGLA_UF IN ('CE','AL','PI','MA','PB','PE','BA','SE','RN')
               THEN 4 -- NORDESTE
             WHEN UF.NM_SIGLA_UF IN ('AC','AM','PA','RR','AP','RO','TO')
               THEN 5 -- NORTE
             WHEN UF.NM_SIGLA_UF IN ('US')
               THEN 6 -- AMERICA DO NORTE
             ELSE   7 -- CENTROESTE :: REGIAO DUPLICADA E DESCONHECIDA
            END           AS regiao_id
    FROM ASIWEB.CR_UF UF
    ORDER BY UF.ID_UF;
COMMIT; -- #FIM :: IMPORTACAO DAS UNIDADES FEDERATIVAS >> UNIDADEMEDIDA


BEGIN;  -- #INI :: IMPORTACAO DAS CIDADES >> CIDADE
    INSERT INTO public.cidade
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , codigo
    , codigoibge
    , nome
    , inativador_id
    , autor_id
    , editor_id 
    , estado_id
    )
    SELECT CID.ID_CIDADE   AS id
         , CID.DT_BLOQUEIO AS databloqueio
         , NULL            AS datainativo
         , LOCALTIMESTAMP  AS datacriacao
         , LOCALTIMESTAMP  AS dataedicao
         , 0               AS "version"
         , CID.CD_CIDADE   AS codigo
         , CID.CD_IBGE     AS codigoibge
         , CID.NM_CIDADE   AS nome
         , NULL            AS inativador_id
         , NULL            AS autor_id
         , NULL            AS editor_id
         , UF.ID           AS estado_id
    FROM ASIWEB.CR_CIDADE CID
    INNER JOIN PUBLIC.ESTADO UF ON UF.CODIGO = CID.CD_UF
    ORDER BY CID.CD_CIDADE;
COMMIT; -- #FIM :: IMPORTACAO DAS CIDADES >> CIDADE


BEGIN;  -- #INI :: CRIA UM BAIRRO PADRAO :: DEMAIS SERAO INSERIDOS PELA FUNCAO "GETIDBAIRRO()"
    INSERT INTO public.bairro
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
    , cidade_id
    )
    SELECT 1               AS id
         , NULL            AS databloqueio
         , NULL            AS datainativo
         , LOCALTIMESTAMP  AS datacriacao
         , LOCALTIMESTAMP  AS dataedicao
         , 0               AS "version"
         , 1               AS codigo
         , '(MIGRACAO)'    AS nome
         , NULL            AS inativador_id
         , NULL            AS autor_id
         , NULL            AS editor_id
         , CID.ID_CIDADE   AS cidade_id
    FROM ASIWEB.CR_CIDADE CID
    WHERE CID.ID_CIDADE=2;
COMMIT; -- #FIM :: CRIA UM BAIRRO PADRAO :: DEMAIS SERAO INSERIDOS PELA FUNCAO "GETIDBAIRRO()"


BEGIN;  -- #INI :: IMPORTACAO DAS INSTALACOES >> ENDERECO
    INSERT INTO public.endereco
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , cep
    , codigo
    , complemento
    , latitude
    , logradouro
    , longitude
    , nome
    , numero
    , inativador_id
    , autor_id
    , editor_id
    , bairro_id
    , cidade_id
    , dominiotipoendereco_id
    , pessoa_id
    )
    SELECT ENDER.ID_INSTALACAO  AS id
         , ENDER.DT_BLOQUEIO    AS databloqueio
         , NULL                 AS datainativo
         , LOCALTIMESTAMP       AS datacriacao
         , LOCALTIMESTAMP       AS dataedicao
         , 0                    AS "version"
         , ENDER.NR_CEP         AS cep
         , ENDER.CD_INSTALACAO  AS codigo
         , ''                   AS complemento
         , NULL                 AS latitude
         , ENDER.DS_ENDERECO    AS logradouro
         , NULL                 AS longitude
         , ENDER.NM_INSTALACAO  AS nome
         , NULL                 AS numero
         , NULL                 AS inativador_id
         , NULL                 AS autor_id
         , NULL                 AS editor_id
         , 1                    AS bairro_id
         , CID.ID               AS cidade_id
         , NULL                 AS dominiotipoendereco_id
         , NULL                 AS pessoa_id
    FROM ASIWEB.CR_INSTALACAO ENDER
    INNER JOIN PUBLIC.CIDADE CID ON CID.CODIGO = ENDER.CD_CIDADE
    ORDER BY ENDER.CD_INSTALACAO;
COMMIT; -- #FIM :: IMPORTACAO DAS INSTALACOES >> ENDERECO


BEGIN;  -- #INI :: IMPORTACAO DAS UNIDADES DE MEDIDAS >> UNIDADEMEDIDA
    INSERT INTO public.unidademedida
    (
      id
    , databloqueio
    , datainativo
    , codigo
    , descricao
    , sigla
    , inativador_id
    , tiponumerico_id
    )
    SELECT UM.ID_UM         AS id
         , NULL             AS databloqueio
         , NULL             AS datainativo
         , UM.CD_UM         AS codigo
         , UM.DS_UM         AS descricao
         , UM.SG_UM         AS sigla
         , NULL             AS inativador_id
         , (SELECT ID FROM PUBLIC.DOMINIO 
            WHERE CHAVE = 'tipoNumerico' -- 1-INTEIRO / 2-DECIMAL
            AND CODIGO = 1) AS tiponumerico_id
    FROM ASIWEB.CR_UM UM  -- #REVER NECESSIDADE DE FATOR DE CONVERSAO
    ORDER BY UM.ID_UM;
COMMIT; -- #FIM :: IMPORTACAO DAS UNIDADES DE MEDIDAS >> UNIDADEMEDIDA


--===========================================================================--
-- ##FIM :: IMPORTACAO TABELAS BASICAS                                       --
--===========================================================================--