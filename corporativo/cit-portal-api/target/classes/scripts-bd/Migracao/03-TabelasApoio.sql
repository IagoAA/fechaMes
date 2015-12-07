--===========================================================================--
-- ##INI :: IMPORTACAO TABELAS DE APOIO AO NEGOCIO                           --
--===========================================================================--


BEGIN;  -- #INI :: IMPORTACAO DAS FUNCOES >> FUNCAO
    INSERT INTO public.funcao
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
    )
    SELECT F.CD_TP_FUNCAO_HIER::INT AS id
         , NULL                     AS databloqueio
         , NULL                     AS datainativo
         , LOCALTIMESTAMP           AS datacriacao
         , LOCALTIMESTAMP           AS dataedicao
         , 0                        AS "version"
         , CAST(F.CD_TP_FUNCAO_HIER AS INTEGER)      AS CODIGO
         , F.NM_TP_FUNCAO_HIER      AS nome
         , NULL                     AS inativador_id
         , NULL                     AS autor_id
         , NULL                     AS editor_id
         , 1
    FROM ASIWEB.CR_TP_FUNCAO_HIER F
    ORDER BY F.CD_TP_FUNCAO_HIER;
COMMIT; -- #FIM :: IMPORTACAO DAS FUNCOES >> FUNCAO


BEGIN;  -- #INI :: IMPORTACAO DOS AGENTES >> PESSOAS
    --> PESSOAS :: TIPO DE AGENTE :: INDIFERENTE >> PESSOA
    INSERT INTO public.pessoa
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , codigo
    , email
    , nome
    , inativador_id
    , autor_id
    , editor_id
    , organizacao_id
    , dominio_pessoa
    , agente_id
    )
    SELECT (row_number() OVER (ORDER BY A.ID_AGENTE)) + M.MAX AS id 
         , NULL                    AS databloqueio
         , NULL                    AS datainativo
         , COALESCE(A.DT_CADASTRO,LOCALTIMESTAMP) AS datacriacao
         , LOCALTIMESTAMP          AS dataedicao
         , 0                       AS "version"
         , A.CD_AGENTE             AS codigo
         , CASE WHEN POSITION('@'  IN A.NM_EMAIL) > 0 
             THEN A.NM_EMAIL 
             ELSE ' '
           END                     AS email
         , A.NM_AGENTE             AS nome
         , NULL                    AS inativador_id
         , NULL                    AS autor_id
         , NULL                    AS editor_id
         , 1                       AS organizacao_id
         , CASE 
             WHEN A.CD_TP_SIT_JURIDICA = '1' THEN -- PESSOA JURIDICA
               (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE = 'tipoPessoa' AND CODIGO = 2) 
             WHEN A.CD_TP_SIT_JURIDICA = '2' THEN -- PESSOA FISICA
               (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE = 'tipoPessoa' AND CODIGO = 1)
           END                     AS dominio_pessoa
         , A.CD_AGENTE::BIGINT     AS agente_id -- PARA FACILITAR VINCULAR PARCEIROS
    FROM ASIWEB.CR_AGENTE A
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.pessoa) M ON 1=1
    ORDER BY A.ID_AGENTE;

    --> PESSOAS :: NÃO AGENTES :: FUNCIONARIOS >> PESSOA
    INSERT INTO public.pessoa
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , codigo
    , email
    , nome
    , inativador_id
    , autor_id
    , editor_id
    , organizacao_id
    , dominio_pessoa
    , agente_id
    )
    SELECT (row_number() OVER (ORDER BY F.CD_FUNCIONARIO)) + M.MAX AS id 
         , F.DT_BLOQUEIO           AS databloqueio
         , NULL                    AS datainativo
         , COALESCE(F.DT_CADASTRO,LOCALTIMESTAMP) AS datacriacao
         , LOCALTIMESTAMP          AS dataedicao
         , 0                       AS "version"
         , F.CD_FUNCIONARIO        AS codigo
         , CASE WHEN POSITION('@'  IN F.NM_EMAIL) > 0 
             THEN F.NM_EMAIL 
             ELSE ' '
           END                     AS email
         , F.NM_FUNCIONARIO        AS nome
         , NULL                    AS inativador_id
         , NULL                    AS autor_id
         , NULL                    AS editor_id
         , 1                          AS orgao_id
         , (SELECT ID FROM PUBLIC.DOMINIO 
             WHERE CHAVE = 'tipoPessoa' AND CODIGO = 1)
                                   AS dominio_pessoa
         , F.CD_AGENTE::BIGINT     AS agente_id -- PARA FACILITAR VINCULAR PARCEIROS
    FROM ASIWEB.CR_FUNCIONARIO F
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.pessoa) M ON 1=1
    ORDER BY F.CD_FUNCIONARIO;

    --> PESSOAS :: FISICAS :: NÃO AGENTES :: FUNCIONARIOS >> PESSOAFISICA
    INSERT INTO public.pessoafisica 
    (
      databloqueio
    , datainativo
    , cpf
    , datanascimento
    , nomemae
    , nomepai
    , numeropassaporte
    , rg
    , tituloeleitor
    , pessoa_id
    , inativador_id
    , dominio_estado_civil
    , dominio_sexo
    )
    SELECT F.DT_BLOQUEIO           AS databloqueio
         , NULL                    AS datainativo
         , REPLACE(
             REPLACE(
               F.NR_CPF,'.',''     -- RETIRA PONTOS
             ),'-',''              -- RETIRA TRAÇO
           )                       AS cpf
         , TO_DATE('1900-01-01','YYYY-MM-DD') AS datanascimento
         , NULL                    AS nomemae
         , NULL                    AS nomepai
         , NULL                    AS numeropassaporte
         , F.NR_RG                 AS rg
         , NULL                    AS tituloeleitor
         , PES.ID                  AS pessoa_id
         , NULL                    AS inativador_id
         , NULL                    AS dominio_estado_civil
         , NULL                    AS dominio_sexo
    FROM ASIWEB.CR_FUNCIONARIO F
    INNER JOIN PUBLIC.PESSOA PES ON PES.AGENTE_ID=F.CD_AGENTE::BIGINT
    ORDER BY F.CD_FUNCIONARIO;

    --> ENDERECOS DE PESSOAS :: NÃO AGENTES :: FUNCIONARIOS
    --  ASIWEB.CR_FUNCIONARIO NAO TEM ENDERECOS

    --> CONTATOS DE PESSOAS :: NÃO AGENTES :: FUNCIONARIOS
    --  ASIWEB.CR_FUNCIONARIO NAO TEM VINCULO COM CR_CONTATOS

    --> VINCULO DE PARCEIRO :: PESSOAS NÃO AGENTES :: FUNCIONARIOS >> PARCEIRO
    INSERT INTO public.parceiro 
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , codigo
    , inativador_id
    , autor_id
    , editor_id
    , classeparceiro_id
    , pessoa_id
    , agente_id
    )
    SELECT (row_number() OVER (ORDER BY F.CD_FUNCIONARIO)) + M.MAX AS id
         , F.DT_BLOQUEIO           AS databloqueio
         , NULL                    AS datainativo
         , COALESCE(F.DT_CADASTRO,LOCALTIMESTAMP) AS datacriacao
         , LOCALTIMESTAMP          AS dataedicao
         , 0                       AS "version"
         , F.CD_FUNCIONARIO        AS codigo
         , NULL                    AS inativador_id
         , NULL                    AS autor_id
         , NULL                    AS editor_id
         , 1                       AS classeparceiro_id --3, -- 1 COLABORADOR, 2 - ORGÃO EXTERNO, 3 - PORTADOR, 4 - FORNECEDOR
         , PES.ID                  AS pessoa_id
         , F.CD_AGENTE::BIGINT     AS agente_id
    FROM ASIWEB.CR_FUNCIONARIO F
    INNER JOIN PUBLIC.PESSOA PES ON PES.AGENTE_ID=F.CD_AGENTE::BIGINT
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.parceiro) M ON 1=1
    ORDER BY F.CD_FUNCIONARIO;

    --> DADOS DE PARCEIRO :: PESSOAS NÃO AGENTES :: FUNCIONARIOS >> PARCEIRO_COLABORADOR
    INSERT INTO public.parceiro_colaborador
    (
      id
    , cargo
    , matricula
    , estruturaorganizacional_id
    , funcao_id
    )
    SELECT P.ID                     AS id
         , F.NM_CARGO               AS cargo
         , F.CD_FUNCIONARIO         AS matricula
         , COALESCE(E.ID,(SELECT ID FROM PUBLIC.ESTRUTURAORGANIZACIONAL WHERE CODIGO='201004' LIMIT 1)) AS estruturaorganizacional_id
         , (SELECT COALESCE(ID,1) FROM PUBLIC.FUNCAO
            WHERE NOME = F.NM_FUNCAO) AS funcao_id
    FROM ASIWEB.CR_FUNCIONARIO F
    INNER JOIN PUBLIC.PARCEIRO P ON P.AGENTE_ID=F.CD_AGENTE::BIGINT
     LEFT JOIN PUBLIC.ESTRUTURAORGANIZACIONAL E ON E.CODIGO=F.CD_UA
    ORDER BY F.CD_FUNCIONARIO;

    --> TELEFONES :: PESSOAS :: NÃO AGENTES :: FUNCIONARIOS
    --  ASIWEB.CR_FUNCIONARIO NAO TEM NUMEROS DE TELEFONES

    --> PESSOAS :: FISICAS :: TIPO DE AGENTE :: PORTADOR >> PESSOAFISICA
    INSERT INTO public.pessoafisica 
    (
      databloqueio
    , datainativo
    , cpf
    , datanascimento
    , nomemae
    , nomepai
    , numeropassaporte
    , rg
    , tituloeleitor
    , pessoa_id
    , inativador_id
    , dominio_estado_civil
    , dominio_sexo
    )
    SELECT A.DT_BLOQUEIO           AS databloqueio
         , NULL                    AS datainativo
         , REPLACE(
             REPLACE(
               A.NR_IDENT_AGENTE,'.',''-- RETIRA PONTOS
             ),'-',''                  -- RETIRA TRAÇO
           )                       AS cpf
         , TO_DATE('1900-01-01','YYYY-MM-DD') AS datanascimento
         , NULL                    AS nomemae
         , NULL                    AS nomepai
         , P.NR_PASSAPORTE         AS numeropassaporte
         , P.NR_RG                 AS rg
         , NULL                    AS tituloeleitor
         , PES.ID                  AS pessoa_id
         , NULL                    AS inativador_id
         , NULL                    AS dominio_estado_civil
         , NULL                    AS dominio_sexo
    FROM ASIWEB.CR_AGENTE A
    INNER JOIN ASIWEB.BP_PORTADOR P ON P.CD_AGENTE=A.CD_AGENTE
    INNER JOIN PUBLIC.PESSOA PES ON PES.AGENTE_ID=A.CD_AGENTE::BIGINT
    WHERE A.CD_TP_SIT_JURIDICA = '2' -- "1";"PESSOA JURIDICA" "2";"PESSOA FISICA"
    AND A.CD_TP_AGENTE = '6'         -- "6";"PORTADOR"
    ORDER BY A.ID_AGENTE;

    --> ENDERECOS :: PESSOAS FISICAS :: TIPO DE AGENTE :: PORTADOR >> ENDERECO
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
    SELECT (row_number() OVER (ORDER BY A.ID_AGENTE)) + M.MAX AS id  
         , A.DT_BLOQUEIO                     AS databloqueio
         , NULL                              AS datainativo
         , COALESCE(A.DT_CADASTRO,LOCALTIMESTAMP) AS datacriacao
         , LOCALTIMESTAMP                    AS dataedicao
         , 0                                 AS "version"
         , REPLACE(
             REPLACE(
               A.NR_CEP,'.',''     -- RETIRA PONTOS
             ),'-',''              -- RETIRA TRAÇO
           )                                 AS cep
         , A.CD_AGENTE                       AS codigo
         , NULL                              AS complemento
         , NULL                              AS latitude
         , A.ED_AGENTE                       AS logradouro
         , NULL                              AS longitude
         , NULL                              AS nome
         , NULL                              AS numero
         , NULL                              AS inativador_id
         , NULL                              AS autor_id
         , NULL                              AS editor_id
         , getidbairro(A.NM_BAIRRO, A.CD_CIDADE, 
                        A.CD_AGENTE, A.DT_BLOQUEIO, 
                        A.DT_CADASTRO)        AS bairro_id
         , (SELECT ID FROM PUBLIC.CIDADE
             WHERE CODIGO = A.CD_CIDADE)      AS cidade_id
         , (SELECT ID FROM PUBLIC.DOMINIO
            WHERE CHAVE = 'tipoEndereco' 
            AND CODIGO = 1)                  AS dominiotipoendereco_id
         , PES.ID                            AS pessoa_id
    FROM ASIWEB.CR_AGENTE A
    INNER JOIN ASIWEB.BP_PORTADOR P ON P.CD_AGENTE=A.CD_AGENTE
    INNER JOIN PUBLIC.PESSOA PES ON PES.AGENTE_ID=A.CD_AGENTE::BIGINT
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.endereco) M ON 1=1
    WHERE A.CD_TP_SIT_JURIDICA = '2' -- "1";"PESSOA JURIDICA" "2";"PESSOA FISICA"
    AND A.CD_TP_AGENTE = '6'         -- "6";"PORTADOR"
    ORDER BY A.ID_AGENTE;

    --> CONTATOS :: PESSOAS FISICAS :: TIPO DE AGENTE :: PORTADOR >> CONTATO
    INSERT INTO public.contato
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , cargo
    , cpf
    , email
    , nome
    , ordem
    , representantelegal
    , telefone
    , inativador_id
    , autor_id
    , editor_id
    , pessoa_id
    )
    SELECT (row_number() OVER (ORDER BY A.ID_AGENTE)) + M.MAX AS id  
         , C.DT_BLOQUEIO                     AS databloqueio
         , NULL                              AS datainativo
         , COALESCE(C.DT_VALIDACAO,LOCALTIMESTAMP) AS datacriacao
         , LOCALTIMESTAMP                    AS dataedicao
         , 0                                 AS "version"
         , C.NM_CARGO                        AS cargo
         , REPLACE(
             REPLACE(
               C.NR_CPF,'.',''               -- RETIRA PONTOS
             ),'-',''                        -- RETIRA TRAÇO
           )                                 AS cpf
         , C.NM_EMAIL                        AS email
         , C.NM_CONTATO                      AS nome
         , C.SQ_CONTATO                      AS ordem
         , CASE WHEN C.IN_RESPONSAVEL = 'S'
             THEN TRUE
             ELSE FALSE
           END                               AS representantelegal
         , C.NR_DDD||' '||C.NR_FONE          AS telefone
         , NULL                              AS inativador_id
         , NULL                              AS autor_id
         , NULL                              AS editor_id
         , PES.ID                            AS pessoa_id
    FROM ASIWEB.CR_AGENTE A
    INNER JOIN ASIWEB.CR_CONTATO C ON C.CD_AGENTE=A.CD_AGENTE
    INNER JOIN PUBLIC.PESSOA PES ON PES.AGENTE_ID=A.CD_AGENTE::BIGINT
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.contato) M ON 1=1
    WHERE A.CD_TP_SIT_JURIDICA = '2' -- "1";"PESSOA JURIDICA" "2";"PESSOA FISICA"
    AND A.CD_TP_AGENTE = '6'         -- "6";"PORTADOR"
    ORDER BY A.ID_AGENTE;

    --> VINCULO PARCEIRO :: PESSOAS FISICAS :: TIPO DE AGENTE :: PORTADOR >> PARCEIRO
    INSERT INTO public.parceiro 
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , codigo
    , inativador_id
    , autor_id
    , editor_id
    , classeparceiro_id
    , pessoa_id
    , agente_id
    )
    SELECT (row_number() OVER (ORDER BY A.ID_AGENTE)) + M.MAX AS id
         , A.DT_BLOQUEIO           AS databloqueio
         , NULL                    AS datainativo
         , COALESCE(A.DT_CADASTRO,LOCALTIMESTAMP) AS datacriacao
         , LOCALTIMESTAMP          AS dataedicao
         , 0                       AS "version"
         , A.CD_AGENTE             AS codigo
         , NULL                    AS inativador_id
         , NULL                    AS autor_id
         , NULL                    AS editor_id
         , 3                       AS classeparceiro_id -- 1 COLABORADOR, 2 - ORGÃO EXTERNO, 3 - PORTADOR, 4 - FORNECEDOR
         , PES.ID                  AS pessoa_id
         , A.CD_AGENTE::BIGINT     AS agente_id  -- PARA FACILITAR VINCULAR PARCEIROS
    FROM ASIWEB.CR_AGENTE A
    INNER JOIN PUBLIC.PESSOA PES ON PES.AGENTE_ID=A.CD_AGENTE::BIGINT
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.parceiro) M ON 1=1
    WHERE A.CD_TP_SIT_JURIDICA = '2' -- "1";"PESSOA JURIDICA" "2";"PESSOA FISICA"
    AND A.CD_TP_AGENTE = '6'         -- "6";"PORTADOR"
    ORDER BY A.ID_AGENTE;

    --> DADOS DO PARCEIRO :: PESSOAS FISICAS :: TIPO DE AGENTE :: PORTADOR >> PARCEIRO_PORTADOR
    INSERT INTO public.parceiro_portador
    (
      id
    )
    SELECT PAR.ID AS id
    FROM ASIWEB.CR_AGENTE A
    INNER JOIN PUBLIC.PARCEIRO PAR ON PAR.AGENTE_ID=A.CD_AGENTE::BIGINT
    WHERE A.CD_TP_SIT_JURIDICA = '2' -- "1";"PESSOA JURIDICA" "2";"PESSOA FISICA"
    AND A.CD_TP_AGENTE = '6'         -- "6";"PORTADOR"
    ORDER BY A.ID_AGENTE;

    --> PESSOAS :: FISICAS :: TIPO DE AGENTE :: FORNECEDOR >> PESSOAFISICA
    INSERT INTO public.pessoafisica 
    (
      databloqueio
    , datainativo
    , cpf
    , datanascimento
    , nomemae
    , nomepai
    , numeropassaporte
    , rg
    , tituloeleitor
    , pessoa_id
    , inativador_id
    , dominio_estado_civil
    , dominio_sexo
    )
    SELECT A.DT_BLOQUEIO           AS databloqueio
         , NULL                    AS datainativo
         , REPLACE(
             REPLACE(
               A.NR_IDENT_AGENTE,'.',''-- RETIRA PONTOS
             ),'-',''                  -- RETIRA TRAÇO
           )                       AS cpf
         , TO_DATE('1900-01-01','YYYY-MM-DD') AS datanascimento
         , NULL                    AS nomemae
         , NULL                    AS nomepai
         , NULL                    AS numeropassaporte
         , F.NR_RG                 AS rg
         , NULL                    AS tituloeleitor
         , PES.ID                  AS pessoa_id
         , NULL                    AS inativador_id
         , NULL                    AS dominio_estado_civil
         , NULL                    AS dominio_sexo
    FROM ASIWEB.CR_AGENTE A
    INNER JOIN ASIWEB.CR_FORNECEDOR F ON F.CD_AGENTE=A.CD_AGENTE
    INNER JOIN PUBLIC.PESSOA PES ON PES.AGENTE_ID=A.CD_AGENTE::BIGINT
    WHERE A.CD_TP_SIT_JURIDICA = '2' -- "1";"PESSOA JURIDICA" "2";"PESSOA FISICA"
    AND A.CD_TP_AGENTE = '1'         -- "1";"FORNECEDOR" 
    ORDER BY A.ID_AGENTE;

    --> ENDERECOS :: PESSOAS FISICAS :: TIPO DE AGENTE :: FORNECEDOR >> ENDERECO
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
    SELECT (row_number() OVER (ORDER BY A.ID_AGENTE)) + M.MAX AS id  
         , A.DT_BLOQUEIO                     AS databloqueio
         , NULL                              AS datainativo
         , COALESCE(A.DT_CADASTRO,LOCALTIMESTAMP) 
                                             AS datacriacao
         , LOCALTIMESTAMP                    AS dataedicao
         , 0                                 AS "version"
         , REPLACE(
             REPLACE(
               A.NR_CEP,'.',''     -- RETIRA PONTOS
             ),'-',''              -- RETIRA TRAÇO
           )                                 AS cep
         , A.CD_AGENTE                       AS codigo
         , NULL                              AS complemento
         , NULL                              AS latitude
         , A.ED_AGENTE                       AS logradouro
         , NULL                              AS longitude
         , NULL                              AS nome
         , NULL                              AS numero
         , NULL                              AS inativador_id
         , NULL                              AS autor_id
         , NULL                              AS editor_id
         , getidbairro(A.NM_BAIRRO, A.CD_CIDADE, 
                        A.CD_AGENTE, A.DT_BLOQUEIO, 
                        A.DT_CADASTRO)        AS bairro_id
         , (SELECT ID FROM PUBLIC.CIDADE
             WHERE CODIGO = A.CD_CIDADE)      AS cidade_id
         , (SELECT ID FROM PUBLIC.DOMINIO
            WHERE CHAVE = 'tipoEndereco' 
            AND CODIGO = 2)                  AS dominiotipoendereco_id
         , PES.ID                            AS pessoa_id
    FROM ASIWEB.CR_AGENTE A
    INNER JOIN ASIWEB.CR_FORNECEDOR F ON F.CD_AGENTE=A.CD_AGENTE
    INNER JOIN PUBLIC.PESSOA PES ON PES.AGENTE_ID=A.CD_AGENTE::BIGINT
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.endereco) M ON 1=1
    WHERE A.CD_TP_SIT_JURIDICA = '2' -- "1";"PESSOA JURIDICA" "2";"PESSOA FISICA"
    AND A.CD_TP_AGENTE = '1'         -- "1";"FORNECEDOR" 
    ORDER BY A.ID_AGENTE;

    --> CONTATOS :: PESSOAS FISICAS :: TIPO DE AGENTE :: FORNECEDOR >> CONTATO
    INSERT INTO public.contato
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , cargo
    , cpf
    , email
    , nome
    , ordem
    , representantelegal
    , telefone
    , inativador_id
    , autor_id
    , editor_id
    , pessoa_id
    )
    SELECT (row_number() OVER (ORDER BY A.ID_AGENTE)) + M.MAX AS id  
         , C.DT_BLOQUEIO                     AS databloqueio
         , NULL                              AS datainativo
         , COALESCE(C.DT_VALIDACAO, LOCALTIMESTAMP) AS datacriacao
         , LOCALTIMESTAMP                    AS dataedicao
         , 0                                 AS "version"
         , C.NM_CARGO                        AS cargo
         , REPLACE(
             REPLACE(
               C.NR_CPF,'.',''     -- RETIRA PONTOS
             ),'-',''              -- RETIRA TRAÇO
           )                                 AS cpf
         , C.NM_EMAIL                        AS email
         , C.NM_CONTATO                      AS nome
         , C.SQ_CONTATO                      AS ordem
         , CASE WHEN C.IN_RESPONSAVEL = 'S'
             THEN TRUE
             ELSE FALSE
           END                               AS representantelegal
         , C.NR_DDD||' '||C.NR_FONE          AS telefone
         , NULL                              AS inativador_id
         , NULL                              AS autor_id
         , NULL                              AS editor_id
         , PES.ID                            AS pessoa_id    
    FROM ASIWEB.CR_AGENTE A
    INNER JOIN ASIWEB.CR_CONTATO C ON C.CD_AGENTE=A.CD_AGENTE
    INNER JOIN PUBLIC.PESSOA PES ON PES.AGENTE_ID=A.CD_AGENTE::BIGINT
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.contato) M ON 1=1
    WHERE A.CD_TP_SIT_JURIDICA = '2' -- "1";"PESSOA JURIDICA" "2";"PESSOA FISICA"
    AND A.CD_TP_AGENTE = '1'         -- "1";"FORNECEDOR" 
    ORDER BY A.ID_AGENTE;

    --> VINCULO PARCEIRO :: PESSOAS FISICAS :: TIPO DE AGENTE :: FORNECEDOR >> PARCEIRO
    INSERT INTO public.parceiro 
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , codigo
    , inativador_id
    , autor_id
    , editor_id
    , classeparceiro_id
    , pessoa_id
    , agente_id
    )
    SELECT (row_number() OVER (ORDER BY A.ID_AGENTE)) + M.MAX AS id
         , A.DT_BLOQUEIO           AS databloqueio
         , NULL                    AS datainativo
         , COALESCE(A.DT_CADASTRO,LOCALTIMESTAMP) AS datacriacao
         , LOCALTIMESTAMP          AS dataedicao
         , 0                       AS "version"
         , A.CD_AGENTE             AS codigo
         , NULL                    AS inativador_id
         , NULL                    AS autor_id
         , NULL                    AS editor_id
         , 4                       AS classeparceiro_id -- 1 COLABORADOR, 2 - ORGÃO EXTERNO, 3 - PORTADOR, 4 - FORNECEDOR
         , PES.ID                  AS pessoa_id
         , A.CD_AGENTE::BIGINT     AS agente_id -- PARA FACILITAR VINCULAR PARCEIROS
    FROM ASIWEB.CR_AGENTE A
    INNER JOIN PUBLIC.PESSOA PES ON PES.AGENTE_ID=A.CD_AGENTE::BIGINT
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.parceiro) M ON 1=1
    WHERE A.CD_TP_SIT_JURIDICA = '2' -- "1";"PESSOA JURIDICA" "2";"PESSOA FISICA"
    AND A.CD_TP_AGENTE = '1'         -- "1";"FORNECEDOR"
    ORDER BY A.ID_AGENTE;

    --> DADOS DO PARCEIRO :: PESSOAS FISICAS :: TIPO DE AGENTE :: FORNECEDOR >> FORNECEDOR
    INSERT INTO public.fornecedor
    (
      id
    , compraseletronicas
    , estrangeiro
    )
    SELECT PAR.ID      AS id
         , CASE WHEN F.IN_COMPRA_ELETRONICA = 'S' 
            THEN TRUE
            ELSE FALSE
           END         AS compraseletronicas
         , FALSE       AS estrangeiro
    FROM ASIWEB.CR_AGENTE A
    INNER JOIN ASIWEB.CR_FORNECEDOR F ON F.CD_AGENTE=A.CD_AGENTE
    INNER JOIN PUBLIC.PARCEIRO PAR ON PAR.AGENTE_ID=A.CD_AGENTE::BIGINT
    WHERE A.CD_TP_SIT_JURIDICA = '2' -- "1";"PESSOA JURIDICA" "2";"PESSOA FISICA"
    AND A.CD_TP_AGENTE = '1'         -- "1";"FORNECEDOR"
    ORDER BY A.ID_AGENTE;

    --> PESSOAS :: JURIDICAS :: TIPO DE AGENTE :: ORGAO EXTERNO >> PESSOAJURIDICA
    INSERT INTO public.pessoajuridica
    (
      databloqueio
    , datainativo
    , cnpj
    , filantropico
    , filial
    , impostosimples
    , inscricaoestadual
    , inscricaomunicipal
    , nomefantasia
    , razaosocial
    , site
    , pessoa_id
    , inativador_id
    , dominio_abrangencia
    , dominio_porte
    )
    SELECT A.DT_BLOQUEIO                 AS databloqueio
         , NULL                          AS datainativo
         , REPLACE(
             REPLACE(
               REPLACE(
                 A.NR_IDENT_AGENTE,'.',''-- RETIRA PONTOS
               ),'/',''                  -- RETIRA BARRAS
             ),'-',''                    -- RETIRA TRAÇO
           )                             AS cnpj
         , FALSE                         AS filantropico
         , FALSE                         AS filial
         , FALSE                         AS impostosimples
         , NULL                          AS inscricaoestadual
         , NULL                          AS inscricaomunicipal
         , O.SG_ORGAO_EXTERNO            AS nomefantasia
         , A.NM_AGENTE                   AS razaosocial
         , CASE WHEN POSITION('www.' IN LOWER(A.NM_EMAIL)) > 0 
             THEN A.NM_EMAIL 
             ELSE ' '
           END                           AS site
         , PES.ID                        AS pessoa_id
         , NULL                          AS inativador_id
         , NULL                          AS dominio_abrangencia
         , NULL                          AS dominio_porte
    FROM ASIWEB.CR_AGENTE A
    INNER JOIN ASIWEB.CR_ORGAO_EXTERNO O ON O.CD_AGENTE=A.CD_AGENTE
    INNER JOIN PUBLIC.PESSOA PES ON PES.AGENTE_ID=A.CD_AGENTE::BIGINT
    WHERE A.CD_TP_SIT_JURIDICA = '1' -- "1";"PESSOA JURIDICA" "2";"PESSOA FISICA"
    AND A.CD_TP_AGENTE = '4'         -- "4";"ORGAO EXTERNO"
    ORDER BY A.ID_AGENTE;

    --> ENDERECOS :: PESSOAS JURIDICAS :: TIPO DE AGENTE :: ORGAO EXTERNO >> ENDERECO
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
    SELECT (row_number() OVER (ORDER BY A.ID_AGENTE)) + M.MAX AS id  
         , A.DT_BLOQUEIO                     AS databloqueio
         , NULL                              AS datainativo
         , COALESCE(A.DT_CADASTRO,LOCALTIMESTAMP) AS datacriacao
         , LOCALTIMESTAMP                    AS dataedicao
         , 0                                 AS "version"
         , REPLACE(
             REPLACE(
               A.NR_CEP,'.',''     -- RETIRA PONTOS
             ),'-',''              -- RETIRA TRAÇO
           )                                 AS cep
         , A.CD_AGENTE                       AS codigo
         , NULL                              AS complemento
         , NULL                              AS latitude
         , A.ED_AGENTE                       AS logradouro
         , NULL                              AS longitude
         , NULL                              AS nome
         , NULL                              AS numero
         , NULL                              AS inativador_id
         , NULL                              AS autor_id
         , NULL                              AS editor_id
         , getidbairro(A.NM_BAIRRO, A.CD_CIDADE, 
                        A.CD_AGENTE, A.DT_BLOQUEIO, 
                        A.DT_CADASTRO)        AS bairro_id
         , (SELECT ID FROM PUBLIC.CIDADE
             WHERE CODIGO = A.CD_CIDADE)      AS cidade_id
         , (SELECT ID FROM PUBLIC.DOMINIO
            WHERE CHAVE = 'tipoEndereco' 
            AND CODIGO = 2)                  AS dominiotipoendereco_id
         , PES.ID                            AS pessoa_id
    FROM ASIWEB.CR_AGENTE A
    INNER JOIN ASIWEB.CR_ORGAO_EXTERNO O ON O.CD_AGENTE=A.CD_AGENTE
    INNER JOIN PUBLIC.PESSOA PES ON PES.AGENTE_ID=A.CD_AGENTE::BIGINT
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.endereco) M ON 1=1
    WHERE A.CD_TP_SIT_JURIDICA = '1' -- "1";"PESSOA JURIDICA" "2";"PESSOA FISICA"
    AND A.CD_TP_AGENTE = '4'         -- "4";"ORGAO EXTERNO"
    ORDER BY A.ID_AGENTE;

    --> CONTATOS :: PESSOAS JURIDICAS :: TIPO DE AGENTE :: ORGAO EXTERNO >> CONTATO
    INSERT INTO public.contato
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , cargo
    , cpf
    , email
    , nome
    , ordem
    , representantelegal
    , telefone
    , inativador_id
    , autor_id
    , editor_id
    , pessoa_id
    )
    SELECT (row_number() OVER (ORDER BY A.ID_AGENTE)) + M.MAX AS id  
         , C.DT_BLOQUEIO                     AS databloqueio
         , NULL                              AS datainativo
         , COALESCE(C.DT_VALIDACAO,LOCALTIMESTAMP) AS datacriacao
         , LOCALTIMESTAMP                    AS dataedicao
         , 0                                 AS "version"
         , C.NM_CARGO                        AS cargo
         , REPLACE(
             REPLACE(
               C.NR_CPF,'.',''     -- RETIRA PONTOS
             ),'-',''              -- RETIRA TRAÇO
           )                                 AS cpf
         , C.NM_EMAIL                        AS email
         , C.NM_CONTATO                      AS nome
         , C.SQ_CONTATO                      AS ordem
         , CASE WHEN C.IN_RESPONSAVEL = 'S'
             THEN TRUE
             ELSE FALSE
           END                               AS representantelegal
         , C.NR_DDD||' '||C.NR_FONE          AS telefone
         , NULL                              AS inativador_id
         , NULL                              AS autor_id
         , NULL                              AS editor_id
         , PES.ID                            AS pessoa_id    
    FROM ASIWEB.CR_AGENTE A
    INNER JOIN ASIWEB.CR_CONTATO C ON C.CD_AGENTE=A.CD_AGENTE
    INNER JOIN PUBLIC.PESSOA PES ON PES.AGENTE_ID=A.CD_AGENTE::BIGINT
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.contato) M ON 1=1
    WHERE A.CD_TP_SIT_JURIDICA = '1' -- "1";"PESSOA JURIDICA" "2";"PESSOA FISICA"
    AND A.CD_TP_AGENTE = '4'         -- "4";"ORGAO EXTERNO" 
    ORDER BY A.ID_AGENTE;

    --> VINCULO PARCEIRO :: PESSOAS JURIDICAS :: TIPO DE AGENTE :: ORGAO EXTERNO >> PARCEIRO
    INSERT INTO public.parceiro 
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , codigo
    , inativador_id
    , autor_id
    , editor_id
    , classeparceiro_id
    , pessoa_id
    , agente_id
    )
    SELECT (row_number() OVER (ORDER BY A.ID_AGENTE)) + M.MAX AS id
         , A.DT_BLOQUEIO           AS databloqueio
         , NULL                    AS datainativo
         , COALESCE(A.DT_CADASTRO,LOCALTIMESTAMP) AS datacriacao
         , LOCALTIMESTAMP          AS dataedicao
         , 0                       AS "version"
         , A.CD_AGENTE             AS codigo
         , NULL                    AS inativador_id
         , NULL                    AS autor_id
         , NULL                    AS editor_id
         , 2                       AS classeparceiro_id -- 1 COLABORADOR, 2 - ORGÃO EXTERNO, 3 - PORTADOR, 4 - FORNECEDOR
         , PES.ID                  AS pessoa_id    
         , A.CD_AGENTE::BIGINT     AS agente_id
    FROM ASIWEB.CR_AGENTE A
    INNER JOIN PUBLIC.PESSOA PES ON PES.AGENTE_ID=A.CD_AGENTE::BIGINT
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.parceiro) M ON 1=1
    WHERE A.CD_TP_SIT_JURIDICA = '1' -- "1";"PESSOA JURIDICA" "2";"PESSOA FISICA"
    AND A.CD_TP_AGENTE = '4'         -- "4";"ORGAO EXTERNO"
    ORDER BY A.ID_AGENTE;

    --> DADOS DO PARCEIRO :: PESSOAS JURIDICAS :: TIPO DE AGENTE :: ORGAO EXTERNO >> ORGAOEXTERNO
    INSERT INTO public.orgaoexterno
    (
      id
    , sigla
    )
    SELECT PAR.ID             AS id
         , O.SG_ORGAO_EXTERNO AS sigla
    FROM ASIWEB.CR_AGENTE A
    INNER JOIN ASIWEB.CR_ORGAO_EXTERNO O ON O.CD_AGENTE=A.CD_AGENTE
    INNER JOIN PUBLIC.PARCEIRO PAR ON PAR.AGENTE_ID=A.CD_AGENTE::BIGINT
    WHERE A.CD_TP_SIT_JURIDICA = '1' -- "1";"PESSOA JURIDICA" "2";"PESSOA FISICA"
    AND A.CD_TP_AGENTE = '4'         -- "4";"ORGAO EXTERNO" 
    ORDER BY A.ID_AGENTE;

    --> PESSOAS :: JURIDICAS :: TIPO DE AGENTE :: FORNECEDOR >> PESSOAJURIDICAS
    INSERT INTO public.pessoajuridica
    (
      databloqueio
    , datainativo
    , cnpj
    , filantropico
    , filial
    , impostosimples
    , inscricaoestadual
    , inscricaomunicipal
    , nomefantasia
    , razaosocial
    , site
    , pessoa_id
    , inativador_id
    , dominio_abrangencia
    , dominio_porte
    )
    SELECT A.DT_BLOQUEIO                 AS databloqueio
         , NULL                          AS datainativo
         , REPLACE(
             REPLACE(
               REPLACE(
                 A.NR_IDENT_AGENTE,'.',''-- RETIRA PONTOS
               ),'/',''                  -- RETIRA BARRAS
             ),'-',''                    -- RETIRA TRAÇO
           )                             AS cnpj
         , CASE
             WHEN F.IN_FILANTROPICO = 'S'
               THEN TRUE
               ELSE FALSE
           END                           AS filantropico
         , FALSE                         AS filial
         , CASE
             WHEN F.IN_IMPOSTO_SIMPLES = 'S'
               THEN TRUE
               ELSE FALSE
           END                           AS impostosimples
         , F.NR_INSC_ESTAD               AS inscricaoestadual
         , F.NR_INSC_MUNICIPAL           AS inscricaomunicipal
         , F.NM_RAZAO_SOCIAL             AS nomefantasia
         , F.NM_RAZAO_SOCIAL             AS razaosocial
         , COALESCE(
             F.DS_URL,
             CASE WHEN POSITION('www.' IN LOWER(A.NM_EMAIL)) > 0 
               THEN A.NM_EMAIL 
               ELSE ' '
             END)                        AS site
         , PES.ID                        AS pessoa_id
         , NULL                          AS inativador_id
         , CASE 
             WHEN F.CD_TP_FOR_ABRANG = '01' THEN -- LOCAL
               (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE = 'tipoAbrangencia' AND CODIGO = 1)
             WHEN F.CD_TP_FOR_ABRANG = '02' THEN -- REGIONAL
               (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE = 'tipoAbrangencia' AND CODIGO = 3)
             WHEN F.CD_TP_FOR_ABRANG = '03' THEN -- NACIONAL
               (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE = 'tipoAbrangencia' AND CODIGO = 2)
           END                           AS dominio_abrangencia
         , CASE 
             WHEN F.CD_TP_FOR_PORTE = '01' THEN -- MICRO-EMPRESA
               (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE = 'tipoPorte' AND CODIGO = 1)
             WHEN F.CD_TP_FOR_PORTE = '02' THEN -- PEQUENA
               (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE = 'tipoPorte' AND CODIGO = 2)
             WHEN F.CD_TP_FOR_PORTE = '03' THEN -- MÉDIA
               (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE = 'tipoPorte' AND CODIGO = 3)
             WHEN F.CD_TP_FOR_PORTE = '03' THEN -- GRANDE
               (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE = 'tipoPorte' AND CODIGO = 4)
           END                           AS dominio_porte
    FROM ASIWEB.CR_AGENTE A
    INNER JOIN ASIWEB.CR_FORNECEDOR F ON F.CD_AGENTE=A.CD_AGENTE
    INNER JOIN PUBLIC.PESSOA PES ON PES.AGENTE_ID=A.CD_AGENTE::BIGINT
    WHERE A.CD_TP_SIT_JURIDICA = '1' -- "1";"PESSOA JURIDICA" "2";"PESSOA FISICA"
    AND A.CD_TP_AGENTE = '1'         -- "1";"FORNECEDOR"
    ORDER BY A.ID_AGENTE;

    --> ENDERECOS :: PESSOAS JURIDICAS :: TIPO DE AGENTE :: FORNECEDOR >> ENDERECO
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
    SELECT (row_number() OVER (ORDER BY A.ID_AGENTE)) + M.MAX AS id  
         , A.DT_BLOQUEIO                     AS databloqueio
         , NULL                              AS datainativo
         , COALESCE(A.DT_CADASTRO,LOCALTIMESTAMP) AS datacriacao
         , LOCALTIMESTAMP                    AS dataedicao
         , 0                                 AS "version"
         , REPLACE(
             REPLACE(
               A.NR_CEP,'.',''     -- RETIRA PONTOS
             ),'-',''              -- RETIRA TRAÇO
           )                                 AS cep
         , A.CD_AGENTE                       AS codigo
         , NULL                              AS complemento
         , NULL                              AS latitude
         , A.ED_AGENTE                       AS logradouro
         , NULL                              AS longitude
         , NULL                              AS nome
         , NULL                              AS numero
         , NULL                              AS inativador_id
         , NULL                              AS autor_id
         , NULL                              AS editor_id
         , getidbairro(A.NM_BAIRRO, A.CD_CIDADE, 
                        A.CD_AGENTE, A.DT_BLOQUEIO, 
                        A.DT_CADASTRO)        AS bairro_id
         , (SELECT ID FROM PUBLIC.CIDADE
             WHERE CODIGO = A.CD_CIDADE)      AS cidade_id
         , (SELECT ID FROM PUBLIC.DOMINIO
            WHERE CHAVE = 'tipoEndereco' 
            AND CODIGO = 2)                  AS dominiotipoendereco_id
         , PES.ID                            AS pessoa_id
    FROM ASIWEB.CR_AGENTE A
    INNER JOIN ASIWEB.CR_FORNECEDOR F ON F.CD_AGENTE=A.CD_AGENTE
    INNER JOIN PUBLIC.PESSOA PES ON PES.AGENTE_ID=A.CD_AGENTE::BIGINT
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.endereco) M ON 1=1
    WHERE A.CD_TP_SIT_JURIDICA = '1' -- "1";"PESSOA JURIDICA" "2";"PESSOA FISICA"
    AND A.CD_TP_AGENTE = '1'         -- "1";"FORNECEDOR"
    ORDER BY A.ID_AGENTE;

    --> CONTATOS :: PESSOAS JURIDICAS :: TIPO DE AGENTE :: FORNECEDOR >> CONTATO
    INSERT INTO public.contato
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , cargo
    , cpf
    , email
    , nome
    , ordem
    , representantelegal
    , telefone
    , inativador_id
    , autor_id
    , editor_id
    , pessoa_id
    )
    SELECT (row_number() OVER (ORDER BY A.ID_AGENTE)) + M.MAX AS id  
         , C.DT_BLOQUEIO                     AS databloqueio
         , NULL                              AS datainativo
         , COALESCE(C.DT_VALIDACAO,LOCALTIMESTAMP) AS datacriacao
         , LOCALTIMESTAMP                    AS dataedicao
         , 0                                 AS "version"
         , C.NM_CARGO                        AS cargo
         , REPLACE(
             REPLACE(
               C.NR_CPF,'.',''     -- RETIRA PONTOS
             ),'-',''              -- RETIRA TRAÇO
           )                                 AS cpf
         , C.NM_EMAIL                        AS email
         , C.NM_CONTATO                      AS nome
         , C.SQ_CONTATO                      AS ordem
         , CASE WHEN C.IN_RESPONSAVEL = 'S'
             THEN TRUE
             ELSE FALSE
           END                               AS representantelegal
         , C.NR_DDD||' '||C.NR_FONE          AS telefone
         , NULL                              AS inativador_id
         , NULL                              AS autor_id
         , NULL                              AS editor_id
         , PES.ID                            AS pessoa_id    
    FROM ASIWEB.CR_AGENTE A
    INNER JOIN ASIWEB.CR_CONTATO C ON C.CD_AGENTE=A.CD_AGENTE
    INNER JOIN PUBLIC.PESSOA PES ON PES.AGENTE_ID=A.CD_AGENTE::BIGINT
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.contato) M ON 1=1
    WHERE A.CD_TP_SIT_JURIDICA = '1' -- "1";"PESSOA JURIDICA" "2";"PESSOA FISICA"
    AND A.CD_TP_AGENTE = '1'         -- "1";"FORNECEDOR"
    ORDER BY A.ID_AGENTE;

    --> VINCULO PARCEIRO :: PESSOAS JURIDICAS :: TIPO DE AGENTE :: FORNECEDOR >> PARCEIRO
    INSERT INTO public.parceiro 
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , codigo
    , inativador_id
    , autor_id
    , editor_id
    , classeparceiro_id
    , pessoa_id
    , agente_id
    )
    SELECT (row_number() OVER (ORDER BY A.ID_AGENTE)) + M.MAX AS id
         , A.DT_BLOQUEIO           AS databloqueio
         , NULL                    AS datainativo
         , COALESCE(A.DT_CADASTRO,LOCALTIMESTAMP) AS datacriacao
         , LOCALTIMESTAMP          AS dataedicao
         , 0                       AS "version"
         , A.CD_AGENTE             AS codigo
         , NULL                    AS inativador_id
         , NULL                    AS autor_id
         , NULL                    AS editor_id
         , 4                       AS classeparceiro_id -- 1 COLABORADOR, 2 - ORGÃO EXTERNO, 3 - PORTADOR, 4 - FORNECEDOR
         , PES.ID                  AS pessoa_id
         , A.CD_AGENTE::BIGINT     AS agente_id
    FROM ASIWEB.CR_AGENTE A
    INNER JOIN PUBLIC.PESSOA PES ON PES.AGENTE_ID=A.CD_AGENTE::BIGINT
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.parceiro) M ON 1=1
    WHERE A.CD_TP_SIT_JURIDICA = '1' -- "1";"PESSOA JURIDICA" "2";"PESSOA FISICA"
    AND A.CD_TP_AGENTE = '1'         -- "1";"FORNECEDOR"
    ORDER BY A.ID_AGENTE;

    --> DADOS DO PARCEIRO :: PESSOAS JURIDICAS :: TIPO DE AGENTE :: FORNECEDOR >> FORNECEDOR
    INSERT INTO public.fornecedor
    (
      id
    , compraseletronicas
    , estrangeiro
    )
    SELECT PAR.ID      AS id
         , CASE WHEN F.IN_COMPRA_ELETRONICA = 'S' 
            THEN TRUE
            ELSE FALSE
           END         AS compraseletronicas
         , FALSE       AS estrangeiro
    FROM ASIWEB.CR_AGENTE A
    INNER JOIN ASIWEB.CR_FORNECEDOR F ON F.CD_AGENTE=A.CD_AGENTE
    INNER JOIN PUBLIC.PARCEIRO PAR ON PAR.AGENTE_ID=A.CD_AGENTE::BIGINT
    WHERE A.CD_TP_SIT_JURIDICA = '1' -- "1";"PESSOA JURIDICA" "2";"PESSOA FISICA"
    AND A.CD_TP_AGENTE = '1'         -- "1";"FORNECEDOR"
    ORDER BY A.ID_AGENTE;

    --> TELEFONES :: PESSOAS :: TIPO AGENTE :: INDIFERENTE >> TELEFONE
    INSERT INTO public.telefone
    (
      id
    , databloqueio
    , datacriacao
    , dataedicao
    , version
    , numero
    , dominio_telefone
    , pessoa_id
    ) 
    SELECT (row_number() OVER (ORDER BY FONE.DATAEDICAO)) + (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.telefone) AS Id
       , FONE.databloqueio::DATE
       , FONE.datacriacao::DATE
       , FONE.dataedicao::DATE
       , FONE.version::INT
       , FONE.telefone::TEXT
       , FONE.dominio_telefone::INT
       , FONE.pessoa_id::INT
    FROM 
       ( SELECT A.DT_BLOQUEIO                     AS databloqueio
              , A.DT_CADASTRO                     AS datacriacao
              , LOCALTIMESTAMP                    AS dataedicao
              , 0                                 AS "version"
              , TRIM(COALESCE(A.NR_DDD1,' ')||' '||COALESCE(A.NR_FONE1,' ')) AS telefone
              , (SELECT ID FROM PUBLIC.DOMINIO
                 WHERE CHAVE = 'tipoTelefone' 
                 AND CODIGO = 1)                  AS dominio_telefone
              , PES.ID                            AS pessoa_id
         FROM ASIWEB.CR_AGENTE A
         INNER JOIN PUBLIC.PESSOA PES ON PES.AGENTE_ID=A.CD_AGENTE::BIGINT
         UNION
         SELECT A.DT_BLOQUEIO                     AS databloqueio
              , A.DT_CADASTRO                     AS datacriacao
              , LOCALTIMESTAMP                    AS dataedicao
              , 0                                 AS "version"
              , TRIM(COALESCE(A.NR_DDD2,' ')||' '||COALESCE(A.NR_FONE2,' ')) AS telefone
              , (SELECT ID FROM PUBLIC.DOMINIO
                 WHERE CHAVE = 'tipoTelefone' 
                 AND CODIGO = 1)                  AS dominio_telefone
              , PES.ID                            AS pessoa_id
         FROM ASIWEB.CR_AGENTE A
         INNER JOIN PUBLIC.PESSOA PES ON PES.AGENTE_ID=A.CD_AGENTE::BIGINT
         UNION
         SELECT A.DT_BLOQUEIO                     AS databloqueio
              , A.DT_CADASTRO                     AS datacriacao
              , LOCALTIMESTAMP                    AS dataedicao
              , 0                                 AS "version"
              , TRIM(COALESCE(A.NR_DDD_FAX,' ')||' '||COALESCE(A.NR_FAX,' ')) AS telefone
              , (SELECT ID FROM PUBLIC.DOMINIO
                  WHERE CHAVE = 'tipoTelefone' 
                  AND CODIGO = 1)                 AS dominio_telefone
              , PES.ID                            AS pessoa_id
         FROM ASIWEB.CR_AGENTE A
         INNER JOIN PUBLIC.PESSOA PES ON PES.AGENTE_ID=A.CD_AGENTE::BIGINT
        ) AS FONE 
    ORDER BY FONE.DATAEDICAO;
COMMIT; -- #FIM :: IMPORTACAO DOS AGENTES >> PESSOAS


-- #INI :: MIGRACAO DAS ESTRUTURAS ORGANIZACIONAIS RESPONSAVEL DAS UAS 
    DO $$
    DECLARE
      r        RECORD;
    BEGIN      
 
       -- RECUPERA AS ESTRUTURAS ORGANIZACIONAIS DO TIPO UA QUE TEM RESPONSAVEIS ATRIBUIDOS
      FOR r IN 
	    SELECT DISTINCT UND.ID AS estruturaorganizacional_id                  
	    FROM ASIWEB.CR_UA UA
	    INNER JOIN PUBLIC.ESTRUTURAORGANIZACIONAL UND ON UA.CD_UA=UND.CODIGO
	    INNER JOIN ASIWEB.CR_HIERARQUIA_UA HUA ON UA.CD_ORGAO=HUA.CD_ORGAO AND UA.CD_UA=HUA.CD_UA
	    INNER JOIN ASIWEB.CR_FUNCIONARIO F ON F.CD_FUNCIONARIO=HUA.CD_FUNCIONARIO AND F.CD_ORGAO=HUA.CD_ORGAO
	    INNER JOIN PUBLIC.PARCEIRO PAR ON PAR.AGENTE_ID=F.CD_AGENTE::BIGINT
	    INNER JOIN PUBLIC.PARCEIRO_COLABORADOR PRC ON PRC.ID=PAR.ID
	    order by und.id
      LOOP
	    INSERT INTO public.estruturaorganizacionalresponsavel
	    (
	      id
	    , databloqueio
	    , datainativo
	    , datacriacao
	    , dataedicao
	    , version
	    , ordem
	    , inativador_id
	    , autor_id
	    , editor_id
	    , estruturaorganizacional_id
	    , responsavel_id
	    )
	    SELECT (row_number() OVER (ORDER BY UA.ID_UA)) + M.MAX AS id  
		 , NULL                 AS databloqueio 
		 , HUA.DT_FIM           AS datainativo
		 , COALESCE(HUA.DT_INICIO,LOCALTIMESTAMP) AS datacriacao
		 , COALESCE(HUA.DT_INICIO,LOCALTIMESTAMP) AS dataedicao
		 , 0                    AS "version"
		 , (row_number() over (order by hua.cd_ua, HUA.SQ_HIERARQUIA))  AS ordem
		 , NULL                 AS inativador_id
		 , NULL                 AS autor_id
		 , NULL                 AS editor_id
		 , UND.ID               AS estruturaorganizacional_id         
		 , PRC.ID               AS responsavel_id
	    FROM ASIWEB.CR_UA UA
	    INNER JOIN PUBLIC.ESTRUTURAORGANIZACIONAL UND ON UA.CD_UA=UND.CODIGO
	    INNER JOIN ASIWEB.CR_HIERARQUIA_UA HUA ON UA.CD_ORGAO=HUA.CD_ORGAO AND UA.CD_UA=HUA.CD_UA
	    INNER JOIN ASIWEB.CR_FUNCIONARIO F ON F.CD_FUNCIONARIO=HUA.CD_FUNCIONARIO AND F.CD_ORGAO=HUA.CD_ORGAO
	    INNER JOIN PUBLIC.PARCEIRO PAR ON PAR.AGENTE_ID=F.CD_AGENTE::BIGINT
	    INNER JOIN PUBLIC.PARCEIRO_COLABORADOR PRC ON PRC.ID=PAR.ID
	    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.estruturaorganizacionalresponsavel) M ON 1=1
	    where und.id = R.ESTRUTURAORGANIZACIONAL_ID
	    order by und.id, hua.sq_hierarquia;	    
        
      END LOOP;
    END$$;
   
-- #FIM :: MIGRACAO DAS ESTRUTURAS ORGANIZACIONAIS RESPONSAVEL 


-- #INI :: MIGRACAO DAS ESTRUTURAS ORGANIZACIONAIS RESPONSAVEL  DAS ULS
    DO $$
    DECLARE
      r        RECORD;
    BEGIN      
 
      -- RECUPERA AS ESTRUTURAS ORGANIZACIONAIS DO TIPO UL QUE TEM RESPONSAVEIS ATRIBUIDOS
      FOR r IN 

	    SELECT DISTINCT UND.ID AS estruturaorganizacional_id         
	    FROM ASIWEB.CR_UL UL
	    INNER JOIN PUBLIC.ESTRUTURAORGANIZACIONAL UND ON UL.CD_UL=UND.CODIGO
	    INNER JOIN ASIWEB.CR_HIERARQUIA_UL HUL ON UL.CD_ORGAO=HUL.CD_ORGAO AND UL.CD_UL=HUL.CD_UL
	    INNER JOIN ASIWEB.CR_FUNCIONARIO F ON F.CD_FUNCIONARIO=HUL.CD_FUNCIONARIO AND F.CD_ORGAO=HUL.CD_ORGAO
	    INNER JOIN PUBLIC.PARCEIRO PAR ON PAR.AGENTE_ID=F.CD_AGENTE::BIGINT
	    INNER JOIN PUBLIC.PARCEIRO_COLABORADOR PRC ON PRC.ID=PAR.ID
	    order by und.id
      LOOP
	    INSERT INTO public.estruturaorganizacionalresponsavel
	    (
	      id
	    , databloqueio
	    , datainativo
	    , datacriacao
	    , dataedicao
	    , version
	    , ordem
	    , inativador_id
	    , autor_id
	    , editor_id
	    , estruturaorganizacional_id
	    , responsavel_id
	    )
	    SELECT (row_number() OVER (ORDER BY UL.ID_UL)) + M.MAX AS id  
		 , NULL                 AS databloqueio 
		 , HUL.DT_FIM           AS datainativo
		 , COALESCE(HUL.DT_INICIO,LOCALTIMESTAMP) AS datacriacao
		 , COALESCE(HUL.DT_INICIO,LOCALTIMESTAMP) AS dataedicao
		 , 0                    AS "version"
		 , (row_number() over (order by HUL.CD_UL, HUL.SQ_HIERARQUIA))  AS ordem
		 , NULL                 AS inativador_id
		 , NULL                 AS autor_id
		 , NULL                 AS editor_id
		 , UND.ID               AS estruturaorganizacional_id
		 , PRC.ID               AS responsavel_id
	    FROM ASIWEB.CR_UL UL
	    INNER JOIN PUBLIC.ESTRUTURAORGANIZACIONAL UND ON UL.CD_UL=UND.CODIGO
	    INNER JOIN ASIWEB.CR_HIERARQUIA_UL HUL ON UL.CD_ORGAO=HUL.CD_ORGAO AND UL.CD_UL=HUL.CD_UL
	    INNER JOIN ASIWEB.CR_FUNCIONARIO F ON F.CD_FUNCIONARIO=HUL.CD_FUNCIONARIO AND F.CD_ORGAO=HUL.CD_ORGAO
	    INNER JOIN PUBLIC.PARCEIRO PAR ON PAR.AGENTE_ID=F.CD_AGENTE::BIGINT
	    INNER JOIN PUBLIC.PARCEIRO_COLABORADOR PRC ON PRC.ID=PAR.ID
	    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.estruturaorganizacionalresponsavel) M ON 1=1
	    where und.id = R.ESTRUTURAORGANIZACIONAL_ID
	    ORDER BY UND.ID, HUL.SQ_HIERARQUIA;	    
        
      END LOOP;
    END$$;
    
-- #FIM :: MIGRACAO DAS ESTRUTURAS ORGANIZACIONAIS RESPONSAVEL 

BEGIN;  -- #INI :: IMPORTACAO DAS COMISSOES DE INVENTARIO >> INVENTARIOCOMISSAO
    -- COMISSAO
    INSERT INTO public.comissao
    (
       id
     , databloqueio
     , datainativo
     , datacriacao
     , dataedicao
     , version
     , codigo
     , dataextincao
     , dataformacao
     , nome
     , numprocesso
     , portaria
     , inativador_id
     , autor_id
     , editor_id
     , organizacao_id     
     , presidente_id
     , dominiotipocomissao_id         
     )
    SELECT (row_number() OVER (ORDER BY C.CD_COMISSAO)) AS id  
         , NULL                 AS databloqueio 
         , NULL                 AS datainativo
         , LOCALTIMESTAMP       AS datacriacao
         , LOCALTIMESTAMP       AS dataedicao
         , 0                    AS "version"
         , C.CD_COMISSAO        AS codigo
         , C.DT_EXTINCAO        AS dataextincao
         , C.DT_FORMACAO        AS dataformacao
         , C.NM_COMISSAO        AS nome
         , C.NR_PROCESSO        AS numprocesso
         , C.NR_PORTARIA        AS portaria
         , NULL                 AS inativador_id
         , NULL                 AS autor_id
         , NULL                 AS editor_id
         , 1                     AS organizacao_id         
         , PRC.ID               AS presidente_id
         , (select id from dominio where chave = 'tipoComissao' and codigo = 1) as dominioTipoComissao_id
    FROM ASIWEB.BP_COMISSAO_INV C   
    INNER JOIN ASIWEB.BP_COMISSAO_REPR R ON C.CD_COMISSAO = R.CD_COMISSAO
    INNER JOIN PUBLIC.ESTRUTURAORGANIZACIONAL U ON C.CD_UG=U.CODIGO
    INNER JOIN ASIWEB.CR_FUNCIONARIO F ON F.CD_FUNCIONARIO=R.CD_FUNCIONARIO AND F.CD_ORGAO=C.CD_ORGAO
    INNER JOIN PUBLIC.PARCEIRO PAR ON PAR.AGENTE_ID=F.CD_AGENTE::BIGINT
    INNER JOIN PUBLIC.PARCEIRO_COLABORADOR PRC ON PRC.ID=PAR.ID
    WHERE R.IN_PRESIDENTE = 'S';

    -- MEMBROS
    INSERT INTO public.comissaointegrante
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , sequencia
    , inativador_id
    , autor_id
    , editor_id
    , integrante_id
    , comissao_id
    , comissaoinativo_id        
    )
    SELECT (row_number() OVER (ORDER BY R.CD_COMISSAO)) AS id  
         , NULL                 AS databloqueio 
         , NULL                 AS datainativo
         , LOCALTIMESTAMP       AS datacriacao
         , LOCALTIMESTAMP       AS dataedicao
         , 0                    AS "version"
         , (row_number() OVER (ORDER BY R.CD_COMISSAO)) AS sequencia
         , NULL                 AS inativador_id
         , NULL                 AS autor_id
         , NULL                 AS editor_id
         , PRC.ID               AS integrante_id
         , I.ID                 AS inventariocomissao_id
         , NULL                 AS inventariocomissaoinativo_id         
    FROM ASIWEB.BP_COMISSAO_REPR R 
    INNER JOIN ASIWEB.BP_COMISSAO_INV C ON C.CD_COMISSAO = R.CD_COMISSAO
    INNER JOIN PUBLIC.COMISSAO I ON I.CODIGO = C.CD_COMISSAO
    INNER JOIN ASIWEB.CR_FUNCIONARIO F ON F.CD_FUNCIONARIO=R.CD_FUNCIONARIO AND F.CD_ORGAO=C.CD_ORGAO
    INNER JOIN PUBLIC.PARCEIRO PAR ON PAR.AGENTE_ID=F.CD_AGENTE::BIGINT
    INNER JOIN PUBLIC.PARCEIRO_COLABORADOR PRC ON PRC.ID=PAR.ID;
COMMIT; -- #FIM :: IMPORTACAO DAS COMISSOES DE INVENTARIO >> INVENTARIOCOMISSAO


BEGIN;  -- #INI :: IMPORTACAO CR_BEM_GENERICO >> CLASSIFICACAOMATERIAL
    --> NIVEIS  :: "CLASSE">>GRUPO">>"SUB_GRUPO">>"MATERIAL"
    INSERT INTO public.classificacaomaterial
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
    , classificacaomaterialparent_id
    , dominiotipoclassificacaomaterial_id
    , dominiotipomaterial_id
    )
    SELECT C.ID_BEM_GENERICO         AS id
         , NULL                      AS databloqueio
         , NULL                      AS datainativo
         , LOCALTIMESTAMP            AS datacriacao
         , LOCALTIMESTAMP            AS dataedicao
         , 0                         AS "version"
         , C.CD_BEM_GENERICO         AS codigo
         , C.DS_BEM_GENERICO         AS descricao
         , NULL                      AS inativador_id
         , NULL                      AS autor_id
         , NULL                      AS editor_id
         , 1   AS organizacao_id
         , CASE 
             WHEN C.CD_GRAU = 0 THEN NULL 
             ELSE ( SELECT S.ID_BEM_GENERICO FROM ASIWEB.CR_BEM_GENERICO S
                    WHERE S.CD_BEM_GENERICO = C.CD_BEM_GEN_SUP)
           END                       AS classificacaomaterialparent_id
         , CASE 
             WHEN C.CD_GRAU = 0 THEN (SELECT ID FROM DOMINIO WHERE CHAVE='tipoClassificacaoMaterial' AND CODIGO=0)
             WHEN C.CD_GRAU = 1 THEN (SELECT ID FROM DOMINIO WHERE CHAVE='tipoClassificacaoMaterial' AND CODIGO=1)
             WHEN C.CD_GRAU = 2 THEN (SELECT ID FROM DOMINIO WHERE CHAVE='tipoClassificacaoMaterial' AND CODIGO=2)
             WHEN C.CD_GRAU = 3 THEN (SELECT ID FROM DOMINIO WHERE CHAVE='tipoClassificacaoMaterial' AND CODIGO=3)
           END                       AS dominiotipoclassificacaomaterial_id
         , CASE 
             WHEN C.CD_TIPO_BS='C' THEN (SELECT ID FROM DOMINIO WHERE CHAVE='tipoMaterial' AND CODIGO=1)
             WHEN C.CD_TIPO_BS='P' THEN (SELECT ID FROM DOMINIO WHERE CHAVE='tipoMaterial' AND CODIGO=2)
             WHEN C.CD_TIPO_BS='S' THEN (SELECT ID FROM DOMINIO WHERE CHAVE='tipoMaterial' AND CODIGO=3)
           END                       AS dominiotipomaterial_id
        FROM ASIWEB.CR_BEM_GENERICO C
        WHERE C.CD_GRAU != 4
        ORDER BY C.ID_BEM_GENERICO;        

    --> NIVEL :: "DETALHE"
    INSERT INTO public.classificacaomaterial
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
    , classificacaomaterialparent_id
    , dominiotipoclassificacaomaterial_id
    , dominiotipomaterial_id
    )
    SELECT (row_number() OVER (ORDER BY B.ID_BEM_SERVICO)) + M.MAX AS id
         , NULL                      AS databloqueio
         , NULL                      AS datainativo
         , LOCALTIMESTAMP            AS datacriacao
         , LOCALTIMESTAMP            AS dataedicao
         , 0                         AS "version"
         , ('D'||B.CD_BEM_SERVICO)   AS codigo
         , B.DS_COMPLETA             AS descricao
         , NULL                      AS inativador_id
         , NULL                      AS autor_id
         , NULL                      AS editor_id
         , 1   AS organizacao_id
         , C.ID_BEM_GENERICO         AS classificacaomaterialparent_id
         , (SELECT ID FROM DOMINIO WHERE CHAVE='tipoClassificacaoMaterial' AND CODIGO=4) AS dominiotipoclassificacaomaterial_id
         , CASE 
             WHEN C.CD_TIPO_BS='C' THEN (SELECT ID FROM DOMINIO WHERE CHAVE='tipoMaterial' AND CODIGO=1)
             WHEN C.CD_TIPO_BS='P' THEN (SELECT ID FROM DOMINIO WHERE CHAVE='tipoMaterial' AND CODIGO=2)
             WHEN C.CD_TIPO_BS='S' THEN (SELECT ID FROM DOMINIO WHERE CHAVE='tipoMaterial' AND CODIGO=3)
           END                       AS dominiotipomaterial_id
    FROM ASIWEB.CR_BEM_SERVICO B
    INNER JOIN ASIWEB.CR_BEM_GENERICO C ON C.CD_BEM_GENERICO=B.CD_BEM_GENERICO
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.classificacaomaterial) M ON 1=1
    ORDER BY B.ID_BEM_SERVICO;
COMMIT; -- #FIM :: IMPORTACAO CR_BEM_GENERICO >> CLASSIFICACAOMATERIAL


BEGIN;  -- #INI :: IMPORTACAO CARACTERISTICAS DE MATERIAIS >> CARACTERISTICA
    --> PRIMEIRO INSERIMOS TODAS :: TABELADAS OU NAO
    INSERT INTO public.caracteristica
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , chavedominio
    , codigo
    , descricao
    , expressaoregular
    , tamanho
    , inativador_id
    , autor_id
    , editor_id
    , organizacao_id
    , dominiotipodado_id
    , dominiotiporestricao_id
    )
    SELECT C.ID_CARACTERISTICA            AS id
         , C.DT_BLOQUEIO                  AS databloqueio
         , NULL                           AS datainativo
         , LOCALTIMESTAMP                 AS datacriacao
         , LOCALTIMESTAMP                 AS dataedicao
         , 0                              AS "version"
         , CASE 
             WHEN C.IN_TABELADO = 'S'
             THEN 'tipoCaract'||INITCAP(NM_CARACTERISTICA) 
             ELSE NULL
           END                            AS chavedominio
         , C.CD_CARACTERISTICA            AS codigo
         , C.NM_CARACTERISTICA            AS descricao
         , C.DS_MASCARA                   AS expressaoregular
         , C.NR_TAMANHO                   AS tamanho
         , NULL                           AS inativador_id
         , NULL                           AS autor_id
         , NULL                           AS editor_id
         , 1       AS organizacao_id
         , D.ID                           AS dominiotipodado_id
         , NULL                           AS dominiotiporestricao_id
    FROM ASIWEB.CR_CARACTERISTICA C
    INNER JOIN 
            (
              SELECT id
                   , CASE 
                       WHEN nome = 'TEXT_FIELD' THEN 'CH'
                       WHEN nome = 'TEXT_AREA'  THEN 'TX'
                       WHEN nome = 'NUMBER'     THEN 'NM'
                       WHEN nome = 'ARQUIVO'    THEN 'IM'
                     END AS tipo
                FROM public.dominio 
               WHERE chave = 'tipoDado' 
                 AND nome IN ('TEXT_FIELD', 'TEXT_AREA', 'NUMBER', 'ARQUIVO')
            ) AS D ON D.TIPO = C.TP_DADO
    ORDER BY C.ID_CARACTERISTICA;

    --> DEPOIS, AJUSTAMOS O DOMINIO DO TIPO DE DADOS PARA CARACTERISTICAS TABELADAS
    UPDATE public.caracteristica
       SET dominiotipodado_id = 
         ( SELECT ID 
              FROM PUBLIC.DOMINIO 
             WHERE CHAVE='tipoDado' 
               AND CODIGO=6 )
     WHERE codigo IN 
         ( SELECT C.CD_CARACTERISTICA 
              FROM ASIWEB.CR_CARACTERISTICA C 
            WHERE C.IN_TABELADO = 'S' )
       AND chavedominio IS NOT NULL;

    --> POR FIM, CRIAMOS AS ENTRADAS NO DOMINIO PARA OS ITENS DAS TABELADAS
    INSERT INTO public.dominio
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , chave
    , codigo
    , descricao
    , nome
    , inativador_id
    , autor_id
    , editor_id
    )
    SELECT (row_number() OVER (ORDER BY T.ID_CARACT_TAB)) + M.MAX AS id
         , T.DT_BLOQUEIO               AS databloqueio
         , NULL                        AS datainativo
         , LOCALTIMESTAMP              AS datacriacao
         , LOCALTIMESTAMP              AS dataedicao
         , 0                           AS "version"
         , 'tipoCaract'||INITCAP(C.NM_CARACTERISTICA) AS chave
         , T.SQ_CARACT_TAB             AS codigo
         , INITCAP(T.NM_CARACT_TAB)    AS descricao
         , T.NM_CARACT_TAB             AS nome
         , NULL                        AS inativador_id
         , NULL                        AS autor_id
         , NULL                        AS editor_id
    FROM ASIWEB.CR_CARACT_TAB T
    INNER JOIN ASIWEB.CR_CARACTERISTICA C ON C.CD_CARACTERISTICA = T.CD_CARACTERISTICA
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.dominio) M ON 1=1
    WHERE C.IN_TABELADO = 'S'
    ORDER BY T.ID_CARACT_TAB;
COMMIT; -- #FIM :: IMPORTACAO CARACTERISTICAS DE MATERIAIS >> CARACTERISTICA


BEGIN;  -- #INI :: IMPORTACAO MATERIAIS >> MATERIAL
    --> MATERIAIS  ::  INDIFERENTE 
    INSERT INTO public.material
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , codigo
    , descricao
    , emplaquetavel
    , inativador_id
    , autor_id
    , editor_id
    , organizacao_id
    , classificacaomaterial_id
    , contacontabil_id
    , dominiotipomaterial_id
    )
    SELECT (row_number() OVER (ORDER BY B.ID_BEM_SERVICO)) + M.MAX AS id
         , B.DT_BLOQUEIO           AS databloqueio
         , NULL                    AS datainativo
         , B.DT_CADASTRO           AS datacriacao
         , LOCALTIMESTAMP          AS dataedicao
         , 0                       AS "version"
         , B.CD_BEM_SERVICO        AS codigo
         , B.DS_COMPLETA           AS descricao
         , CASE WHEN B.IN_EMPLAQUETAVEL = 'S'
             THEN TRUE
             ELSE FALSE 
           END                     AS emplaquetavel
         , NULL                    AS inativador_id
         , NULL                    AS autor_id
         , NULL                    AS editor_id
         , 1 AS organizacao_id
         , (SELECT ID FROM PUBLIC.CLASSIFICACAOMATERIAL
             WHERE CODIGO='D'||B.CD_BEM_SERVICO) AS classificacaomaterial_id
         , CASE 
             WHEN C.CD_TIPO_BS='C' THEN (SELECT COALESCE(ID,1) FROM PUBLIC.CONTACONTABIL WHERE CODIGO=B.CD_CONTA)
             WHEN C.CD_TIPO_BS='P' THEN (SELECT COALESCE(ID,2) FROM PUBLIC.CONTACONTABIL WHERE CODIGO=B.CD_CONTA)
             WHEN C.CD_TIPO_BS='S' THEN (SELECT COALESCE(ID,3) FROM PUBLIC.CONTACONTABIL WHERE CODIGO=B.CD_CONTA)
           END                     AS contacontabil_id
         , CASE 
             WHEN C.CD_TIPO_BS='C' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoMaterial' AND CODIGO=1)
             WHEN C.CD_TIPO_BS='P' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoMaterial' AND CODIGO=2)
             WHEN C.CD_TIPO_BS='S' THEN (SELECT ID FROM PUBLIC.DOMINIO WHERE CHAVE='tipoMaterial' AND CODIGO=3)
           END                     AS dominiotipomaterial_id
    FROM ASIWEB.CR_BEM_SERVICO B
    INNER JOIN ASIWEB.CR_BEM_GENERICO C ON C.CD_BEM_GENERICO=B.CD_BEM_GENERICO
    JOIN (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.material) M ON 1=1
    ORDER BY B.ID_BEM_SERVICO;

    --> MATERIAIS :: CARACTERISTICAS DE BENS E SERVICOS :: MATERIALCARACTERISTICA 
    INSERT INTO public.materialcaracteristica 
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , codigo
    , generico
    , imprimeconteudo
    , obrigatorio
    , sequencia
    , inativador_id
    , autor_id
    , editor_id
    , caracteristica_id
    , material_id
    , materialremocao_id
    )
    SELECT C.ID_CAR_BS_POSSIVEL   AS id
         , C.DT_BLOQUEIO          AS databloqueio
         , NULL                   AS datainativo
         , LOCALTIMESTAMP         AS datacriacao
         , LOCALTIMESTAMP         AS dataedicao
         , 0                      AS "version"
         , C.CD_CARACTERISTICA    AS codigo
         , CASE WHEN C.IN_CAR_GENERICA = 'S' THEN TRUE
                WHEN C.IN_CAR_GENERICA = 'N' THEN FALSE
           END                    AS generico
         , CASE WHEN C.IN_DESCR_MATERIAL = 'S' THEN TRUE
                WHEN C.IN_DESCR_MATERIAL = 'N' THEN FALSE
           END                    AS imprimeconteudo
         , CASE WHEN C.IN_OBRIGATORIO = 'S' THEN TRUE
                WHEN C.IN_OBRIGATORIO = 'N' THEN FALSE
           END                    AS obrigatorio
         , C.NR_ORDEM             AS sequencia
         , NULL                   AS inativador_id
         , NULL                   AS autor_id
         , NULL                   AS editor_id
         , G.ID                   AS caracteristica_id
         , M.ID                   AS material_id
         , NULL                   AS materialremocao_id
    FROM ASIWEB.CR_CAR_BS_POSSIVEL C
    INNER JOIN ASIWEB.CR_BEM_SERVICO B ON B.CD_BEM_SERVICO=C.CD_BEM_SERVICO
    INNER JOIN PUBLIC.MATERIAL M ON M.CODIGO=B.CD_BEM_SERVICO
    INNER JOIN PUBLIC.CARACTERISTICA G ON G.CODIGO=C.CD_CARACTERISTICA
    ORDER BY B.ID_BEM_SERVICO;


    --> MATERIAIS :: BEM SERVICO  :: MATERIALCONSUMO
    INSERT INTO public.materialconsumo
    (
      elementodespesa
    , id
    , unidadearmazenamento_id
    )
    SELECT NULL    AS elementodespesa
         , M.ID    AS id_material
         , U.ID    AS unidadearmazenamento_id
    FROM ASIWEB.CR_BEM_SERVICO B
    INNER JOIN ASIWEB.CR_BEM_GENERICO C ON C.CD_BEM_GENERICO=B.CD_BEM_GENERICO
    INNER JOIN PUBLIC.MATERIAL M ON M.CODIGO=B.CD_BEM_SERVICO
    INNER JOIN PUBLIC.UNIDADEMEDIDA U ON U.CODIGO=B.CD_UM_ELEMENTAR
    WHERE C.CD_TIPO_BS IN ('C','S')  -- REVER MODELO PARA CONTEMPLAR 'SERVICO' TAMBEM
    ORDER BY B.ID_BEM_SERVICO;


    --> MATERIAIS :: CARGA UNIDADE MEDIDA ENTRADA :: MATERIALCONSUMOTIPOUNIDADEMEDIDAENTRADA
    INSERT INTO public.materialconsumotipounidademedidaentrada
    (
      id
    , databloqueio
    , datainativo
    , inativador_id
    , materialconsumo_id
    , materialconsumoremocao_id
    , unidademedida_id
    )
    SELECT B.ID_BEM_SERVICO       AS id
         , B.DT_BLOQUEIO          AS databloqueio
         , NULL                   AS datainativo
         , NULL                   AS inativador_id
         , MC.ID         AS materialconsumo_id
         , NULL                   AS materialconsumoremocao_id
         , U.ID                   AS unidademedida_id
    FROM ASIWEB.CR_BEM_SERVICO B
    INNER JOIN ASIWEB.CR_BEM_GENERICO C ON C.CD_BEM_GENERICO=B.CD_BEM_GENERICO
    INNER JOIN PUBLIC.MATERIAL M ON M.CODIGO=B.CD_BEM_SERVICO
    INNER JOIN PUBLIC.MATERIALCONSUMO MC ON MC.ID=M.ID
    INNER JOIN PUBLIC.UNIDADEMEDIDA U ON U.CODIGO=B.CD_UM_ELEMENTAR
    WHERE C.CD_TIPO_BS IN ('C','S')  -- REVER MODELO PARA CONTEMPLAR 'SERVICO' TAMBEM
    ORDER BY B.ID_BEM_SERVICO;


    --> MATERIAIS :: CARGA UNIDADE MEDIDA ENTRADA  :: MATERIALIMAGEM
    INSERT INTO public.materialimagem
    (
      id
    , databloqueio
    , datainativo
    , datacriacao
    , dataedicao
    , version
    , anexo
    , descricao
    , tamanho
    , inativador_id
    , autor_id
    , editor_id
    , material_id
    , materialinativo_id
    )
    SELECT I.ID_IMAGEM_BEM_SERV   AS id
         , NULL                   AS databloqueio
         , NULL                   AS datainativo
         , LOCALTIMESTAMP         AS datacriacao
         , LOCALTIMESTAMP         AS dataedicao
         , 0                      AS "version"
         , NULL                   AS anexo     -- NM_ARQUIVO PARECE REFERENCIAR A IMAGEM NO FILESYSTEM 
         , I.NM_IMAGEM_BEM_SERV   AS descricao
         , NULL                   AS tamanho
         , NULL                   AS inativador_id
         , NULL                   AS autor_id
         , NULL                   AS editor_id
         , M.ID                   AS material_id
         , NULL                   AS materialinativo_id
    FROM ASIWEB.CR_IMAGEM_BEM_SERV I
    INNER JOIN ASIWEB.CR_BEM_SERVICO B ON B.CD_BEM_SERVICO=I.CD_BEM_SERVICO
    INNER JOIN PUBLIC.MATERIAL M ON M.CODIGO=B.CD_BEM_SERVICO
    ORDER BY I.ID_IMAGEM_BEM_SERV;


    --> MATERIAIS :: BEM SERVICO  :: MATERIALPERMANENTE
    INSERT INTO public.materialpermanente
    (
      id
    , unidademedida_id  -- REVER NO MODELO :: ALTEREI A REFERENCIA DE DOMINIO PARA UNIDADEMEDIDA
    )
    SELECT M.ID         AS id_material
         , U.ID         AS unidademedida_id
    FROM ASIWEB.CR_BEM_SERVICO B
    INNER JOIN ASIWEB.CR_BEM_GENERICO C ON C.CD_BEM_GENERICO=B.CD_BEM_GENERICO
    INNER JOIN PUBLIC.MATERIAL M ON M.CODIGO=B.CD_BEM_SERVICO
    INNER JOIN PUBLIC.UNIDADEMEDIDA U ON U.CODIGO=B.CD_UM_ELEMENTAR
    WHERE C.CD_TIPO_BS='P'
    ORDER BY B.ID_BEM_SERVICO;


    --> MATERIAIS :: BEM SERVICO  :: MATERIALPERMANENTE
    INSERT INTO public.materialpermanenteconsumo
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
    , materialconsumo_id
    , materialpermanente_id
    )
    SELECT B.ID_BEM_SERVICO       AS id
         , NULL                   AS databloqueio
         , NULL                   AS datainativo
         , LOCALTIMESTAMP         AS datacriacao
         , LOCALTIMESTAMP         AS dataedicao
         , 0                      AS "version"
         , NULL                   AS inativador_id
         , NULL                   AS autor_id
         , NULL                   AS editor_id
         , MC.ID         AS materialconsumo_id
         , MP.ID         AS materialpermanente_id
    FROM ASIWEB.CR_BEM_SUBORDINADO S
    INNER JOIN ASIWEB.CR_BEM_SERVICO B ON B.CD_BEM_SERVICO=S.CD_BEM_SERVICO_PRI
    INNER JOIN PUBLIC.MATERIAL M ON M.CODIGO=B.CD_BEM_SERVICO
    INNER JOIN PUBLIC.MATERIALPERMANENTE MP ON MP.ID=M.ID
    INNER JOIN PUBLIC.MATERIALCONSUMO MC ON MC.ID=M.ID
    ORDER BY B.ID_BEM_SERVICO;
COMMIT; -- #FIM :: IMPORTACAO MATERIAIS >> MATERIAL


BEGIN;  -- #INI :: IMPORTACAO USUARIOS >> SEGURANCA_USUARIO
    INSERT INTO public.seguranca_usuario
    (
      id
    , datacriacao
    , dataedicao
    , version
    , contabloqueada
    , contaexpirada
    , contahabilitada
    , credencialexpirada
    , email
    , password
    , passwordhint
    , semprenovaaba
    , username
    , organizacao_id
    )
    SELECT U.ID_USUARIO                AS id
         , LOCALTIMESTAMP              AS datacriacao
         , LOCALTIMESTAMP              AS dataedicao
         , 0                           AS "version"
         , CASE WHEN U.DT_BLOQUEIO IS NULL
             THEN FALSE
             ELSE TRUE 
           END                         AS contabloqueada
         , FALSE                       AS contaexpirada
         , CASE WHEN U.DT_BLOQUEIO IS NULL
             THEN TRUE
             ELSE FALSE 
           END                         AS contahabilitada
         , FALSE                       AS credencialexpirada
         , 'email@domain.com'          AS email
         , '$2a$10$/bFXp.iaFrDI6.XgRslgq.ofM5a2xQ7e37K5SUPVBUev/gYSXjNdS' AS password
         , 'GRP @ MP'                  AS passwordhint
         , TRUE                        AS semprenovaaba
         , U.CD_FUNCIONARIO            AS username
         , 1                           AS organizacao_id
    FROM ASIWEB.SG_USUARIO U;

    INSERT INTO public.seguranca_usuario
    (
      id
    , datacriacao
    , dataedicao
    , version
    , contabloqueada
    , contaexpirada
    , contahabilitada
    , credencialexpirada
    , email
    , password
    , passwordhint
    , semprenovaaba
    , username
    , organizacao_id
    )
    SELECT (M.MAX + 1)                 AS id
         , LOCALTIMESTAMP              AS datacriacao
         , LOCALTIMESTAMP              AS dataedicao
         , 0                           AS "version"
         , FALSE                       AS contabloqueada
         , FALSE                       AS contaexpirada
         , TRUE                        AS contahabilitada
         , FALSE                       AS credencialexpirada
         , 'email@domain.com'          AS email
         , '$2a$10$/mCLiZIxHplKbJBQOt2Hhu6q/1xvxsKNh3AwBAO0JkLs2wjU8jIyq' AS password
         , 'PADRAO'                    AS passwordhint
         , TRUE                        AS semprenovaaba
         , 'admin'                     AS username
         , 1                           AS organizacao_id
    FROM (SELECT COALESCE(MAX(ID),0) AS MAX FROM PUBLIC.SEGURANCA_USUARIO) M;

    -- CRIA PRIVILEGIOS DE ACESSO
    INSERT INTO public.seguranca_privilegio
        ( id, databloqueio, datainativo, datacriacao, dataedicao, version,
          descricao, nome, inativador_id, autor_id, editor_id ) 
    VALUES
        ( NEXTVAL('seguranca_privilegio_id_seq'), NULL, NULL, LOCALTIMESTAMP, LOCALTIMESTAMP, 0,
         'ROLE_ADMIN', 'ROLE_ADMIN', NULL, NULL, NULL),
        ( NEXTVAL('seguranca_privilegio_id_seq'), NULL, NULL, LOCALTIMESTAMP, LOCALTIMESTAMP, 0,
         'ROLE_USER', 'ROLE_USER', NULL, NULL, NULL);

    -- ATRIBUI OS PRIVILEGIOS AO USUARIO
    INSERT INTO public.seguranca_usuario_privilegio
    (
      id        
    , datacriacao
    , dataedicao
    , version
    , inativador_id
    , autor_id
    , editor_id
    , privilegio_id
    , usuario_id
    , usuarioinativo_id
    )
    SELECT U.ID_USUARIO                AS id                  
         , LOCALTIMESTAMP              AS datacriacao
         , LOCALTIMESTAMP              AS dataedicao
         , 0                           AS "version" 
           , NULL                        AS inativador_id
           , NULL                        AS autor_id
           , NULL                        AS editor_id
         , (SELECT ID FROM PUBLIC.SEGURANCA_PRIVILEGIO WHERE NOME='SYSUSER' LIMIT 1) AS privilegio_id
         , U.ID_USUARIO                AS usuario_id
           , NULL                        AS usuarioinativo_id
    FROM ASIWEB.SG_USUARIO U;

    INSERT INTO public.seguranca_usuario_privilegio
    (
      id        
    , datacriacao
    , dataedicao
    , version
    , inativador_id
    , autor_id
    , editor_id
    , privilegio_id
    , usuario_id
    , usuarioinativo_id
    )    
    SELECT (M.MAX + 1)                 AS id
         , LOCALTIMESTAMP              AS datacriacao
         , LOCALTIMESTAMP              AS dataedicao
         , 0                           AS "version"
           , NULL                        AS inativador_id
           , NULL                        AS autor_id
           , NULL                        AS editor_id
         , (SELECT ID FROM PUBLIC.SEGURANCA_PRIVILEGIO WHERE NOME='SYSADMIN' LIMIT 1) AS privilegio_id
         , (SELECT ID FROM PUBLIC.SEGURANCA_USUARIO WHERE USERNAME='admin' LIMIT 1) AS usuario_id
         , NULL                        AS usuarioinativo_id
    FROM (SELECT COALESCE(MAX(ID),0) AS MAX FROM PUBLIC.SEGURANCA_USUARIO_PRIVILEGIO) M;    
	
    UPDATE public.pessoa  
      SET usuario_id = U.ID
     FROM
        ( 
            SELECT ID, USERNAME
            FROM PUBLIC.SEGURANCA_USUARIO
        ) AS U
      WHERE U.USERNAME=CODIGO;	
COMMIT; -- #FIM :: IMPORTACAO USUARIOS >> SEGURANCA_USUARIO


--===========================================================================--
-- ##FIM :: IMPORTACAO TABELAS DE APOIO AO NEGOCIO                           --
--===========================================================================--