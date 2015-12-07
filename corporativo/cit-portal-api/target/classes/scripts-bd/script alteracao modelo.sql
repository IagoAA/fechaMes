-- VERSÃO 0.0.6 --

-- GEOVANE retirada da coluna parent_id_id em localizacao --
alter table localizacao drop parent_id_id;

-- LUÍS CESAR alteração dos tipos e tamanhos dos campos código do sistema --
ALTER TABLE baixapatrimonio ALTER COLUMN codigo TYPE varchar (30);
ALTER TABLE centroresultado ALTER COLUMN codigo TYPE varchar (30);
ALTER TABLE centroresultado ALTER COLUMN codigosistemaorigem TYPE varchar (30);
ALTER TABLE cidade ALTER COLUMN codigoibge TYPE varchar (30);
ALTER TABLE contacontabil ALTER COLUMN codigo TYPE varchar (30);
ALTER TABLE contacontabil ALTER COLUMN codigosistemaorigem TYPE varchar (30);
ALTER TABLE estruturaorganizacional ALTER COLUMN codigo TYPE varchar (30);
ALTER TABLE entrada ALTER COLUMN codigo TYPE varchar (30);
ALTER TABLE entrada ALTER COLUMN codigoasi TYPE varchar (30);
ALTER TABLE funcao ALTER COLUMN codigo TYPE varchar (30);
ALTER TABLE material ALTER COLUMN codigo TYPE varchar (30);
ALTER TABLE organizacao ALTER COLUMN codigo TYPE varchar (30);
ALTER TABLE centrocusto ALTER COLUMN codigo TYPE varchar (30);


--ROGÉRIO GOMES--
---- Table: bempatrimonialalteracao ---

CREATE TABLE bempatrimonialalteracao
(
  id bigserial NOT NULL,
  databloqueio date,
  datainativo date,
  datacriacao timestamp without time zone NOT NULL,
  dataedicao timestamp without time zone NOT NULL,
  version bigint,
  dataalteracao timestamp without time zone,
  valoranterior character varying(255),
  valoratualizado character varying(255),
  inativador_id bigint,
  autor_id bigint,
  editor_id bigint,
  organizacao_id bigint,
  bempatrimonial_id bigint,
  CONSTRAINT bempatrimonialalteracao_pkey PRIMARY KEY (id),
  CONSTRAINT fk_bempatrimonialAlteracao_usuario_editor FOREIGN KEY (editor_id)
      REFERENCES seguranca_usuario (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_bempatrimonialAlteracao_usuario_inativador FOREIGN KEY (inativador_id)
      REFERENCES seguranca_usuario (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_bempatrimonialAlteracao_bempatrimonial FOREIGN KEY (bempatrimonial_id)
      REFERENCES bempatrimonial (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_bempatrimonialAlteracao_usuario_autor FOREIGN KEY (autor_id)
      REFERENCES seguranca_usuario (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_bempatrimonialAlteracao_organizacao FOREIGN KEY (organizacao_id)
      REFERENCES organizacao (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

---- Fim Table: bempatrimonialalteracao---

-- GILBERTO adicionar campo enderecoPrincipal na tabela de endereco, e o primeiro valor seta-lo como true --

alter table endereco add enderecoprincipal boolean;

update endereco set enderecoprincipal = true where id in (
select min(e.id) from endereco e
inner join pessoa p on (e.pessoa_id = p.id)
group by pessoa_id order by pessoa_id)

-- FIM ALTERAÇÕES GILBERTO--

-- GEOVANE 21/09/2015 --
-- mudança de Bem Patrimonial para tratar valor liquido a partir da depreciação acumulada --
ALTER TABLE bempatrimonial ADD depreciacaoAcumulada numeric(20,4) NOT NULL DEFAULT(0);
UPDATE bempatrimonial SET depreciacaoAcumulada = (valorAquisicao - valorLiquido);
ALTER TABLE bempatrimonial DROP valorLiquido;
ALTER TABLE bempatrimonial ALTER COLUMN depreciacaoacumulada DROP DEFAULT;

-- mudança de Bem Patrimonial para guardar seu percentualNaoDepreciavel --
ALTER TABLE bempatrimonial ADD percentualNaoDepreciavel numeric(20,4);
UPDATE bempatrimonial b SET percentualNaoDepreciavel = (SELECT c.percentualNaoDepreciavel FROM contaContabil c INNER JOIN material m ON m.contacontabil_id = c.id WHERE m.id = b.material_id) WHERE b.dataInicioDepreciacao IS NOT NULL;

-- mudança de Bem Patrimonial para guardar atributo que bloqueia depreciacao --
ALTER TABLE bempatrimonial ADD naoDepreciar BOOLEAN NOT NULL DEFAULT(false);

-- GEOVANE FINAL 21/09/2015

-- ROGERIO CASSIMIRO INICIO 22/09/2015 acrescimo coluna para controle de copia de DadosBemPatrimonial Confirmacao/Fechamento de Inventario
alter table dadosbempatrimonial add column copiaDeDadosFinal boolean not null default false;
-- ROGERIO CASSIMIRO FINAL 22/09/2015

-- GEOVANE 24/09/2015 --
-- Voltando valorLiquido devido a necessidade do mesmo --
ALTER TABLE bempatrimonial ADD valorLiquido numeric(20,4) NOT NULL DEFAULT(0);
UPDATE bempatrimonial SET valorLiquido = (valorAquisicao - depreciacaoacumulada);
ALTER TABLE bempatrimonial ALTER COLUMN valorLiquido DROP DEFAULT;
-- GEOVANE FINAL 24/09/2015

-- GILBERTO 25/09/2015
-- adicionar e atualizar novos campos na tabela de transferencia (quantidade de material e valor da transferencia)

alter table transferenciacontacontabil add quantidadematerial bigint;
alter table transferenciacontacontabil add valortransferencia numeric(20,4);

UPDATE transferenciacontacontabil SET quantidadematerial = 0;
UPDATE transferenciacontacontabil SET valortransferencia = 0.00;

-- GILBERTO FIM -------

-- TODOS ACIMA JA EXECUTADOS NA BASE DO MPOG

-- GEOVANE 30/09/2015 --

-- Adicionando atributos para tratar a vida util do bem
ALTER TABLE bempatrimonial ADD vidaUtilAtual integer;
UPDATE bempatrimonial SET vidaUtilAtual = vidaUtilOriginal;
ALTER TABLE bempatrimonial ADD vidaUtilAcumulada integer;
UPDATE bempatrimonial SET vidaUtilAcumulada = (vidaUtilAtual - vidaUtilRestante);
ALTER TABLE bempatrimonial DROP vidaUtilRestante;

-- Adicionando atributos para tratar valor bruto do bem
ALTER TABLE bempatrimonial ADD valorBrutoAtual numeric(20,4) NOT NULL DEFAULT(0);
ALTER TABLE bempatrimonial ADD valorBrutoCalculo numeric(20,4) NOT NULL DEFAULT(0);
UPDATE bempatrimonial SET valorBrutoAtual = valorAquisicao;
UPDATE bempatrimonial SET valorBrutoCalculo = valorAquisicao;
ALTER TABLE bempatrimonial ALTER COLUMN valorBrutoAtual DROP DEFAULT;
ALTER TABLE bempatrimonial ALTER COLUMN valorBrutoCalculo DROP DEFAULT;

-- GEOVANE FINAL 30/09/2015 --

-- ROGERIO CASSIMIRO 01/10/2015 Executar de acordo com modulo do cliente para os casos de servidor configurado para usar domain
-- UPDATE modulo SET habilitado = true where caminho like 'citgrp-patrimonio-api';
-- UPDATE modulo SET habilitado = true where caminho like 'cit-esi-api';
-- UPDATE modulo SET habilitado = true where caminho like 'cit-contratos-api';
-- UPDATE modulo SET habilitado = true where caminho like 'cit-adm-materiais-api';
-- UPDATE modulo SET habilitado = true where caminho like 'cit-almoxarifado-api';
-- UPDATE modulo SET habilitado = true where caminho like 'cit-alcada-api';
-- ROGERIO CASSIMIRO 01/10/2015

-- GEOVANE 01/10/2015 --
-- Adicionando atributos para tratar as reavaliacoes e reducoes do bem
ALTER TABLE bempatrimonial ADD reavaliacaoAcumulada numeric(20,4) NOT NULL DEFAULT(0);
ALTER TABLE bempatrimonial ADD reducaoAcumulada numeric(20,4) NOT NULL DEFAULT(0);
ALTER TABLE bempatrimonial ADD reavaliacaoPeriodo numeric(20,4) NOT NULL DEFAULT(0);
ALTER TABLE bempatrimonial ADD reducaoPeriodo numeric(20,4) NOT NULL DEFAULT(0);

-- deletando menus antigos
delete from menufile where menu_id in (select id from menu where nome in ('Calendário', 'Manutenção de Fluxos', 'Jornada de Trabalho', 'Manutenção de Categorias', 'Manutenção de Processos', 'Histórico'));
delete from menu where id in (select id from menu where nome in ('Calendário', 'Manutenção de Fluxos', 'Jornada de Trabalho', 'Manutenção de Categorias', 'Manutenção de Processos', 'Histórico'));
-- GEOVANE FINAL 01/10/2015 --

-- GILBERTO NERY 05/10/2015 - Adicionar novos campos na tabela bemparimonialvalor

alter table bempatrimonialvalor
add column contacontabil_id bigint,
add column vidaUtilAtual int,
add column vidaUtilRestante int,
add column taxaDepreciacao numeric(20,4),
add column valorBrutoAtual numeric(20,4),
add column valorBrutoCalculo numeric(20,4),
add column valorLiquido numeric(20,4),
add column percentualTaxaResidual numeric(20,4),
add column valorResidual numeric(20,4),
add column valorDepreciavel numeric(20,4),
add column depreciacaoMensal numeric(20,4),
add column depreciacaoAcumulada numeric(20,4),
add column reavaliacaoAcumulada numeric(20,4),
add column reducaoAcumulada numeric(20,4),
add column reavaliacaoPeriodo numeric(20,4),
add column reducaoPeriodo numeric(20,4),
add CONSTRAINT fk_bempatrimonialvalor_contacontabil FOREIGN KEY (contacontabil_id)
      REFERENCES contacontabil (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

--FIM GILBERTO NERY 05/10/2015

-- GEOVANE 05/10/2015 --
-- Adicionando novo dominio de depreciação, retirando não útilizável e editando outro
UPDATE dominio SET descricao = 'Depreciação finalizada', nome = 'NAO_DEPRECIAVEL_VALOR_RESIDUAL' where chave like 'tipoStatusDepreciacao' and codigo = 3;
UPDATE dominio SET descricao = 'Não Depreciável', nome = 'NAO_DEPRECIAVEL_MARCACAO_USUARIO' where chave like 'tipoStatusDepreciacao' and codigo = 4;
INSERT INTO dominio(datacriacao, dataedicao, version, chave, codigo, descricao, nome) VALUES (LOCALTIMESTAMP, LOCALTIMESTAMP, 0, 'tipoStatusDepreciacao', 5, 'Não Depreciado em exercício anterior', 'NAO_DEPRECIADO_EXERCICIO_ANTERIOR');

ALTER TABLE depreciacao ADD qntItensNaoDepreciadoValorResidual bigint;
UPDATE depreciacao SET qntItensNaoDepreciadoValorResidual = qntItensNaoDepreciadoValorMinimo + qntItensNaoDepreciadoVidaUtil;
ALTER TABLE depreciacao DROP qntItensNaoDepreciadoVidaUtil;
ALTER TABLE depreciacao DROP qntItensNaoDepreciadoValorMinimo;
-- GEOVANE FINAL 05/10/2015 --

-- ROGERIO CASSIMIRO 13/10/2015 --
-- Adiciona coluna chave para garantir integridade do menu ao rodar starttup
ALTER TABLE menu ADD chave varchar(255);
-- ROGERIO CASSIMIRO 13/10/2015


-- GEOVANE 16/10/2015 --
ALTER TABLE contacontabilmovimentopatrimonio ADD valorDepreciacao numeric(20,4);
ALTER TABLE contacontabilmovimentopatrimonio ADD valorReavaliacao numeric(20,4);
ALTER TABLE contacontabilmovimentopatrimonio ADD valorReducao numeric(20,4);

ALTER TABLE DepreciacaoItem
	ADD valorBrutoAtual numeric(20,4),
	ADD valorBrutoCalculo numeric(20,4),
	ADD percentualNaoDepreciavel numeric(20,4),
	ADD valorResidual numeric(20,4),
	ADD valorDepreciavel numeric(20,4),
	ADD valorLiquidoAnterior numeric(20,4),
	ADD vidaUtilAtual int,
	ADD vidaUtilRestanteAnterior int,
	ADD vidaUtilRestanteAtual int,
	ADD valorDepreciacaoAtual numeric(20,4),
	ADD valorDepreciacaoAcumuladaAnterior numeric(20,4),
	ADD valorDepreciacaoAcumuladaAtual numeric(20,4),
	ADD valorLiquidoAtual numeric(20,4);

UPDATE DepreciacaoItem SET valorLiquidoAnterior = valorAnterior;
ALTER TABLE DepreciacaoItem DROP valorAnterior;

UPDATE DepreciacaoItem SET vidaUtilRestanteAtual = vidaUtilRestante;
ALTER TABLE DepreciacaoItem DROP vidaUtilRestante;

UPDATE DepreciacaoItem SET valorLiquidoAtual = valorAtual;
ALTER TABLE DepreciacaoItem DROP valorAtual;


UPDATE BemPatrimonialValor SET valorLiquido = valor;
ALTER TABLE BemPatrimonialValor DROP valor;

CREATE INDEX ON bempatrimonial (estruturaorganizacionalatual_id, dominiostatus_id, dominiostatusdepreciacao_id);
CREATE INDEX ON estruturaorganizacional (organizacao_id);
CREATE INDEX ON dominio (chave, codigo);
CREATE INDEX ON bempatrimonialvalor (bemPatrimonial_id, datareferencia);
CREATE INDEX ON contacontabil (organizacao_id, dominiotipomaterial_id);
CREATE INDEX ON contacontabilmovimento (contacontabil_id);
CREATE INDEX ON contacontabilmovimento (dataReferencia, contacontabil_id);
CREATE INDEX ON contacontabilmovimento (dominiotipomovimentocontacontabil_id);
CREATE INDEX ON contacontabilmovimento (dominioclassereferencia_id, idClasseReferencia);

ALTER TABLE DepreciacaoItem ADD taxadepreciacaoTemp numeric(20,4);
UPDATE DepreciacaoItem SET taxadepreciacaoTemp = taxadepreciacao;
ALTER TABLE DepreciacaoItem DROP taxadepreciacao;
ALTER TABLE DepreciacaoItem ADD taxadepreciacao numeric(20,4);
UPDATE DepreciacaoItem SET taxadepreciacao = taxadepreciacaoTemp;
ALTER TABLE DepreciacaoItem DROP taxadepreciacaoTemp;

-- Executar apenas para teste de fechamento de mês/ Será feito pela migração
-- UPDATE BemPatrimonial SET dominiostatusdepreciacao_id = 415 WHERE dominiostatus_id = 53;
-- UPDATE BemPatrimonial SET dominiostatusdepreciacao_id = 192 WHERE dominiostatusdepreciacao_id is null;
-- UPDATE BemPatrimonial SET dominiostatusdepreciacao_id = 194 WHERE valorLiquido = (CASE WHEN bemPatrimonial.percentualNaoDepreciavel is null THEN 0 ELSE (bemPatrimonial.valorBrutoAtual * bemPatrimonial.percentualNaoDepreciavel) END);
-- INSERT into CONTACONTABILSALDO (datacriacao, dataedicao, version, datareferencia, valor, contacontabil_id)
-- SELECT '2015-09-01 00:00:00.000', '2015-09-01 00:00:00.000', 0, '2015-09-01 00:00:00.000', 0, id from CONTACONTABIL where CONTACONTABILSALDOANTERIOR_ID is null;
-- UPDATE PUBLIC.CONTACONTABIL C SET CONTACONTABILSALDOANTERIOR_ID = (SELECT S.ID FROM PUBLIC.CONTACONTABILSALDO S WHERE S.CONTACONTABIL_ID = C.ID ORDER BY DATAREFERENCIA DESC LIMIT 1);

-- Executar caso não tenha na base o vidaUtilRestante do BemPatrimonialValor 
-- ALTER TABLE bempatrimonialvalor add column vidaUtilRestante int;

-- GEOVANE FINAL 16/10/2015 --

-- GEOVANE 16/10/2015 --
ALTER TABLE ContaContabilSaldo ADD depreciacaoMensal numeric(20,4),
				ADD depreciacaoAcumulada numeric(20,4),
				ADD reavaliacaoPeriodo numeric(20,4),
				ADD reavaliacaoAcumulada numeric(20,4),
				ADD reducaoPeriodo numeric(20,4),
				ADD reducaoAcumulada numeric(20,4);
-- GEOVANE FINAL 16/10/2015 --
				
-- GEOVANE 18/10/2015 --
CREATE TABLE bempatrimonialalteracaoitem
(
  id bigserial NOT NULL,
  databloqueio date,
  datainativo date,
  datacriacao timestamp without time zone NOT NULL,
  dataedicao timestamp without time zone NOT NULL,
  version bigint,
  campoAlterado character varying(255),
  valoranterior character varying(255),
  valoratualizado character varying(255),
  inativador_id bigint,
  autor_id bigint,
  editor_id bigint,
  bempatrimonialalteracao_id bigint,
  CONSTRAINT bempatrimonialalteracaoItem_pkey PRIMARY KEY (id),
  CONSTRAINT fk_bempatrimonialalteracaoItem_usuario_editor FOREIGN KEY (editor_id)
      REFERENCES seguranca_usuario (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_bempatrimonialalteracaoItem_usuario_inativador FOREIGN KEY (inativador_id)
      REFERENCES seguranca_usuario (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_bempatrimonialalteracaoItem_bempatrimonialalteracao_id FOREIGN KEY (bempatrimonialalteracao_id)
      REFERENCES bempatrimonialalteracao (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_bempatrimonialAlteracao_usuario_autor FOREIGN KEY (autor_id)
      REFERENCES seguranca_usuario (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

ALTER TABLE bempatrimonialalteracao DROP valorAnterior;
ALTER TABLE bempatrimonialalteracao DROP valorAtualizado;

alter table bempatrimonialalteracaoitem
add column bemPatrimonialAlteracaoInativo_id bigint,
add CONSTRAINT fk_bempatrimonialalteracaoitem_bemPatrimonialAlteracaoInativo FOREIGN KEY (bemPatrimonialAlteracaoInativo_id)
      REFERENCES bemPatrimonialAlteracao (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
-- GEOVANE FINAL 18/10/2015 --

-- GEOVANE 19/10/2015 --
UPDATE MenuFile SET menu_id = (SELECT id FROM Menu WHERE chave = 'BEM_PATRIMONIAL') WHERE id IN (SELECT id FROM MenuFile WHERE menu_id = (SELECT id FROM Menu WHERE chave = 'ALTERACAO_DE_BEM_PATRIMONIAL'));
DELETE FROM Menu WHERE chave = 'ALTERACAO_DE_BEM_PATRIMONIAL';
-- GEOVANE FINAL 19/10/2015 --

-- GEOVANE 20/10/2015 --
ALTER TABLE bempatrimonialalteracaoitem ADD COLUMN campoalterado_id bigint NOT NULL DEFAULT(1),
add CONSTRAINT fk_bempatrimonialalteracaoitem_dominiocampoalterado FOREIGN KEY (campoalterado_id)
      REFERENCES Dominio (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
      
UPDATE bempatrimonialalteracaoitem SET campoalterado_id = (SELECT id FROM dominio WHERE chave = 'campoReferencia' AND nome = 'BEM_PAT_VALOR_BRUTO_ATUAL') WHERE campoalterado = 'Valor bruto';
UPDATE bempatrimonialalteracaoitem SET campoalterado_id = (SELECT id FROM dominio WHERE chave = 'campoReferencia' AND nome = 'BEM_PAT_VIDA_UTIL_RESTANTE') WHERE campoalterado = 'Vida útil restante';

ALTER TABLE bempatrimonialalteracaoitem DROP campoalterado;
ALTER TABLE bempatrimonialalteracaoitem ALTER COLUMN campoalterado_id DROP DEFAULT;

ALTER TABLE bempatrimonialalteracaoitem ADD COLUMN bempatrimonial_id bigint,
add CONSTRAINT fk_bempatrimonialalteracaoitem_bempatrimonial FOREIGN KEY (bempatrimonial_id)
      REFERENCES BemPatrimonial (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
      
ALTER TABLE bempatrimonial DROP dominiotipooperacao_id;
-- GEOVANE FINAL 20/10/2015 --