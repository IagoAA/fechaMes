--===========================================================================--
-- ##INI :: AJUSTE FINAIS DA MIGRACAO                                        --
--===========================================================================--


BEGIN;  -- #INI :: AJUSTES SEQUENCES >> EM LOTE ** HERBERT
    do $$
    declare
      v_seqname text;
      v_keyname text;
      v_tabname text;
      v_command text;
    begin
      for v_seqname, v_keyname, v_tabname in 
        SELECT n.nspname || '.' || replace(replace(replace(replace(replace(replace(replace(replace(a.adsrc,'(',''),')',''),'::',''),'textregclass',''),'nextval',''),'regclass',''),'''',''),n.nspname||'.','') AS seqname
             , ab.attname AS chave
             , n.nspname||'.'||c.relname AS tabname
        FROM pg_class c
        JOIN pg_attrdef a ON c.oid=a.adrelid
        JOIN pg_namespace n ON c.relnamespace = n.oid AND n.nspname = 'public' -- LIKE 'pg_%'
        JOIN pg_index i ON i.indrelid=c.oid AND i.indisprimary='t'
        JOIN pg_attribute ab ON ab.attrelid=c.oid AND ab.attisdropped='f' AND ab.atthasdef='t' AND i.indkey[0]=ab.attnum AND i.indkey[1] IS NULL
        WHERE a.adsrc like 'nextval%'
      loop
        begin
          v_command := 'SELECT setval('||chr(39)|| v_seqname ||chr(39)||',(SELECT MAX('|| v_keyname ||') FROM '|| v_tabname ||'),true);';
          execute v_command;
        exception
          when others then
            raise notice 'Não possível executar comando [ % ]', v_command;
          end;
      end loop;
    end$$;
COMMIT; -- #FIM :: AJUSTES SEQUENCES >> EM LOTE ** HERBERT

BEGIN;  -- #INI :: BLOQUEIO DE ALGUNS DOMINIOS PEDIDO PELO MPOG ** JULIANA

--Alguns tipos de Recebimento
UPDATE PUBLIC.DOMINIO
	SET databloqueio = LOCALTIMESTAMP
WHERE chave = 'tipoRecebimento' and codigo in (5, 9, 11, 13);

--Alguns tipos de baixa
UPDATE PUBLIC.DOMINIO
	SET databloqueio = LOCALTIMESTAMP
WHERE chave = 'tipoBaixa' and codigo in (1,3,6,12,13,14);

COMMIT; -- #FIM :: ALTERACÃO DE NOME DE DOMINIOS PEDIDO PELO MPOG ** JULIANA

BEGIN; -- GARANTINDO QUE AS CONTAS SEM SALDO ANTERIOR, TENHAM UM SALDO ZERADO
insert into CONTACONTABILSALDO (datacriacao, dataedicao, version, datareferencia, valor, contacontabil_id)
SELECT '2015-09-01 00:00:00.000', '2015-09-01 00:00:00.000', 0, '2015-09-01 00:00:00.000', 0, id from CONTACONTABIL
where CONTACONTABILSALDOANTERIOR_ID is null;

-- Referenciar um conta contabil ha um registro do conta contabil
UPDATE PUBLIC.CONTACONTABIL C SET CONTACONTABILSALDOANTERIOR_ID = (SELECT S.ID FROM PUBLIC.CONTACONTABILSALDO S WHERE S.CONTACONTABIL_ID = C.ID ORDER BY DATAREFERENCIA DESC LIMIT 1);

COMMIT;

--===========================================================================--
-- ##FIM :: AJUSTE FINAIS DA MIGRACAO                                        --
--===========================================================================--