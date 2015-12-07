--===========================================================================--
-- ##INI :: AJUSTES INICIAIS PARA A MIGRACAO                                 --
--===========================================================================--

-- #INI :: -- INCLUSOES ESPECIFICAS PARA MIGRACAO
    ALTER TABLE public.pessoa ADD COLUMN agente_id BIGINT;    -- ** HERBERT
    ALTER TABLE public.parceiro ADD COLUMN agente_id BIGINT;  -- ** HERBERT
    ALTER TABLE public.transferencia ADD COLUMN CODIGOASI CHARACTER VARYING(30);             -- ** JULIANA
    ALTER TABLE public.transferenciaitem ADD COLUMN VL_UNITARIO NUMERIC(15,4);               -- ** JULIANA
    ALTER TABLE public.transferenciaitem ADD COLUMN cd_conta CHARACTER VARYING(10);          -- ** JULIANA
    --ALTER TABLE public.transferenciacontacontabil ADD COLUMN CODIGO CHARACTER VARYING(10); -- ** JULIANA
    ALTER TABLE public.baixa ADD COLUMN CODIGO NUMERIC(10,0);                       -- ** JULIANA
    ALTER TABLE public.baixapatrimonioitem ADD COLUMN VL_UNITARIO NUMERIC(15,4);    -- ** JULIANA
    ALTER TABLE public.observacao ADD COLUMN CODIGO NUMERIC(10,0);                  -- ** JULIANA
    ALTER TABLE public.contacontabilmovimento ADD COLUMN bempatrimonial_id BIGINT;  -- ** JULIANA
    ALTER TABLE public.contacontabilmovimento ADD COLUMN transferencia_id BIGINT;   -- ** JULIANA
    ALTER TABLE public.baixa ALTER COLUMN codigo TYPE bigint; -- ** HERBERT
    

    -- VERIFICAR SE O DROP ESTA NO SCRIPT '99-AjustesFinais.sql'
    /*
    ALTER TABLE public.contacontabilmovimento DROP COLUMN bempatrimonial_id; -- ** JULIANA
    ALTER TABLE public.estruturaorgao DROP COLUMN ur_id;  -- ** HERBERT
    ALTER TABLE public.pessoa DROP COLUMN agente_id;      -- ** HERBERT
    ALTER TABLE public.parceiro DROP COLUMN agente_id;    -- ** HERBERT
    */
-- #FIM :: INCLUSOES ESPECIFICAS PARA MIGRACAO

-- #INI :: VERIFICAR SE OS INDICES CRIADOS SERAO MANTIDOS NO MODELO
    CREATE INDEX ix01_entrada_cd   ON public.entrada (codigo);
    CREATE INDEX ix01_entpat_eoid  ON public.entradapatrimonio (estruturaorganizacional_id);
    CREATE INDEX ix01_estrutura_id ON public.estruturaorganizacional (id);
    CREATE INDEX ix02_estrutura_cd ON public.estruturaorganizacional (codigo);
    CREATE INDEX ix01_baixa_cd     ON public.baixa (codigo);
    CREATE INDEX ix01_baixapat_cd  ON public.baixapatrimonio (codigo);
    CREATE INDEX ix01_histpat_dtop ON public.historicobempatrimonial (dataoperacao);
    CREATE INDEX ix02_histpat_bpid ON public.historicobempatrimonial (bempatrimonial_id);
    CREATE INDEX ix01_ctacont_cd   ON public.contacontabil (codigo);
    CREATE INDEX ix01_ctacmov_mesctatp ON public.contacontabilmovimento (datareferencia, contacontabil_id, dominiotipomovimentocontacontabil_id);
    CREATE INDEX ix02_ctacmov_dtref    ON public.contacontabilmovimento (datareferencia);
    CREATE INDEX ix03_ctacmov_ctaid    ON public.contacontabilmovimento (contacontabil_id);
    CREATE INDEX ix01_ctacsdo_mescta   ON public.contacontabilsaldo (datareferencia, contacontabil_id);
    CREATE UNIQUE INDEX ix01_bempat_cd ON public.bempatrimonial (codigo);
    CREATE UNIQUE INDEX ix02_entrada_cdasi ON public.entrada (codigoasi);
    CREATE UNIQUE INDEX ix01_dominio_chvcd ON public.dominio (chave, codigo);
    CREATE INDEX idx01_transfitem_ctacd ON public.transferenciaitem (cd_conta);
    CREATE INDEX idx02_transfitem_undid ON public.transferenciaitem (estruturaorganizacionalorigem_id);
    CREATE INDEX idx03_transfitem_bemid ON public.transferenciaitem (bempatrimonial_id);
-- #FIM :: VERIFICAR SE OS INDICES CRIADOS SERAO MANTIDOS NO MODELO

BEGIN;   -- #INI :: ACRESCENTANDO DOMINIOS

    --> UPDATE DE STATUS DEPRECIACAO - PROXIMAS MIGRACOES O STARTUP LISTENER JA VAI CONTEMPLAR ESSES DOMINIOS
	UPDATE dominio SET descricao = 'Depreciação finalizada', nome = 'NAO_DEPRECIAVEL_VALOR_RESIDUAL' where chave like 'tipoStatusDepreciacao' and codigo = 3;
	UPDATE dominio SET descricao = 'Não Depreciável', nome = 'NAO_DEPRECIAVEL_MARCACAO_USUARIO' where chave like 'tipoStatusDepreciacao' and codigo = 4;
	-- INSERT INTO dominio(datacriacao, dataedicao, version, chave, codigo, descricao, nome) VALUES (LOCALTIMESTAMP, LOCALTIMESTAMP, 0, 'tipoStatusDepreciacao', 5, 'Não Depreciado em exercício anterior', 'NAO_DEPRECIADO_EXERCICIO_ANTERIOR');   

    --> PARA CLASSIFICACAO DE MATERIAL
    INSERT INTO public.dominio
        (datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) 
    VALUES 
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoMaterial', 'Serviço', 'SERVICO', 3);

    --> PARA TIPODOCUMENTO
    INSERT INTO public.dominio
        (datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) 
    VALUES 
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoDocumento', 'Requisição', 'REQUISICAO', 9),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoDocumento', 'Contrato', 'CONTRATO', 10),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoDocumento', 'Processo de Baixa', 'PROCESSO_BAIXA', 11),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoDocumento', 'Nº Processo', 'NUMERO_PROCESSO', 12),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoDocumento', 'Memorando', 'MEMORANDO', 13),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoDocumento', 'Portaria', 'PORTARIA', 14),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoDocumento', 'Despacho', 'DESPACHO', 15),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoDocumento', 'Ocorrência', 'OCORRENCIA', 16),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoDocumento', 'Nota de Sistema', 'NOTA_SISTEMA', 17);

    --> PARA TIPORECEBIMENTO
	--Execute esse script para o tipoRecebimento 05-10-2015 Retirar após arrumar o startup

	delete from dominio where chave='tipoRecebimento';

    INSERT INTO public.dominio
        (datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) 
    VALUES 
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoRecebimento', 'Compra Orçamentária', 'COMPRA_ORCAMENTARIA', 1),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoRecebimento', 'Compra Extra Orçamentária', 'COMPRA_EXTRA_ORCAMENTARIA', 2),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoRecebimento', 'Doação', 'DOACAO', 3),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoRecebimento', 'Cessão de uso', 'CESSAO_USO', 4),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoRecebimento', 'Transferência fora do sistema', 'TRANSFERENCIA_FORA_SISTEMA', 5),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoRecebimento', 'Reposição', 'REPOSICAO', 6),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoRecebimento', 'Produção interna', 'PRODUCAO_INTERNA', 7),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoRecebimento', 'Entrada por decisão judicial', 'ENTRADA_DECISAO_JUDICIAL', 8),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoRecebimento', 'Resto a Pagar', 'RESTO_PAGAR', 9),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoRecebimento', 'Cessão/Transferência', 'CESSAO_TRANSFERENCIA', 10),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoRecebimento', 'Aquisição extra orçamentária/Multa', 'AQUISICAO_MULTA', 11),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoRecebimento', 'Devolução', 'DEVOLUCAO', 12),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoRecebimento', 'D02', 'D02', 13);	
	
    /*INSERT INTO public.dominio
        (datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) 
    VALUES 
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoRecebimento', 'Devolução', 'DEVOLUCAO', 12),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoRecebimento', 'D02', 'D02', 13);*/

    --> PARA TIPOINVENTARIO
    INSERT INTO public.dominio 
        (datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) 
    VALUES 
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoInventario', 'POR U.A.', 'POR_UA', 6),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoInventario', 'POR U.L.', 'POR_UL', 7);
       
    DELETE FROM PUBLIC.DOMINIO WHERE CHAVE='tipoObjetivoInventario';
    --> PARA TIPOOBJETIVOINVENTARIO
    INSERT INTO public.dominio 
        (datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) 
    VALUES 
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoObjetivoInventario', 'INVENTÁRIO GERAL', 'INVENTARIO_GERAL', 1),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoObjetivoInventario', 'CRIAÇÃO DE ÓRGÃO/UG', 'CRIACAO_ORGAO_UG', 2),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoObjetivoInventario', 'EXTINÇAO DE ÓRGÃO/UG', 'EXTINCAO_ORGAO_UG', 3),  
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoObjetivoInventario', 'PARA LEVANTAMENTO', 'PARA_LEVANTAMENTO', 4),     
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoObjetivoInventario', 'PARA CONFERÊNCIA', 'PARA_CONFERENCIA', 5),     
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoObjetivoInventario', 'PARA INSPEÇÃO/AVALIAÇÃO', 'PARA_INSPECAO_AVALIACAO', 6),
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoObjetivoInventario', 'PASSAGEM DE CARGA', 'PASSAGEM_CARGA', 7),           
        (LOCALTIMESTAMP, LOCALTIMESTAMP, NULL, 0, 'tipoObjetivoInventario', 'PARA BAIXA', 'PARA_BAIXA', 8);           

    --> PARA TIPOMOVIMENTOCONTACONTABIL
    UPDATE public.dominio 
       SET DESCRICAO = 'Entrada orçamentária'
         , NOME      = 'ENTRADA_ORCAMENTARIA'
     WHERE CHAVE     = 'tipoMovimentoContaContabil'
       AND CODIGO    = 1;

    UPDATE public.dominio 
       SET DESCRICAO = 'Baixa orçamentária'
         , NOME      = 'BAIXA_ORCAMENTARIA'
     WHERE CHAVE     = 'tipoMovimentoContaContabil'
       AND CODIGO    = 2;

COMMIT;  -- #FIM :: ACRESCENTANDO O ITEM 'SERVICO' NO TIPOMATERIAL


-- FACILITAR A INCLUSAO DE BAIRROS NO CADASTRO DE PESSOAS
CREATE OR REPLACE FUNCTION getidbairro 
(
  IN p_nm_bai character varying,
  IN p_nm_cid character varying,
  IN p_cd_cid character varying,
  IN p_dt_blq timestamp without time zone,
  IN p_dt_cad timestamp without time zone
) RETURNS integer 
AS
$BODY$DECLARE
    v_id_bai bigint;
    v_nm_bai text  := COALESCE(TRIM(p_nm_bai),'(MIGRACAO)');
  BEGIN
    IF (SELECT ID FROM public.bairro WHERE UPPER(TRIM(nome)) = v_nm_bai LIMIT 1) IS NULL THEN
        v_id_bai := (SELECT COALESCE(MAX(ID),0) AS MAX FROM public.bairro) + 1;
        INSERT INTO public.bairro
        (
          id, databloqueio, datainativo, datacriacao, dataedicao, version,
          codigo, nome, inativador_id, autor_id, editor_id, 
          cidade_id
        ) 
        VALUES 
        (
          v_id_bai, p_dt_blq, NULL, p_dt_cad, LOCALTIMESTAMP, 0,
          NULL, p_nm_bai, NULL, NULL, NULL,
          (SELECT COALESCE(MAX(ID),2) FROM PUBLIC.CIDADE WHERE CODIGO=p_cd_cid LIMIT 1) -- CONSIDERAMOS BSB A CIDADE DEFAULT
        );
    END IF;
    RETURN v_id_bai;
  END;$BODY$
LANGUAGE plpgsql;
ALTER FUNCTION public.getidbairro(IN character varying, IN character varying, IN character varying, IN timestamp without time zone, IN timestamp without time zone)
  OWNER TO postgres;

-- FACILITAR A MIGRACAO DOS NUMEROS DE PATRIMONIO
CREATE OR REPLACE FUNCTION hashCode
(
  IN p_code character varying
) RETURNS bigint 
AS 
$BODY$DECLARE
    h bigint := 0;
    i int    := 0;
  BEGIN
    FOR i IN 1..LENGTH(p_code) LOOP
        h := 31 * h + ASCII(SUBSTRING(p_code,i,1));
    END LOOP;
    RETURN h;
  END;$BODY$
LANGUAGE plpgsql;
ALTER FUNCTION public.hashCode(IN p_code character varying)
  OWNER TO postgres;


--===========================================================================--
-- ##FIM :: AJUSTES INICIAIS PARA A MIGRACAO                                 --
--===========================================================================--