<!-- SQL SERVER -->

INSERT INTO seguranca_privilegio(datacriacao, dataedicao, datainativo, version, nome, descricao) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, 'ROLE_ADMIN', 'ROLE_ADMIN');
INSERT INTO seguranca_privilegio(datacriacao, dataedicao, datainativo, version, nome, descricao) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, 'ROLE_USER', 'ROLE_USER');

INSERT INTO EstruturaOrgao(datacriacao, dataedicao, datainativo, version, dataReferenciaVigente, isOrgao, nome, sigla) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, CONVERT(DATE,'2015-08-01'), 1, 'Org�o 1', 'Orgao');

INSERT INTO seguranca_usuario(datacriacao, dataedicao, datainativo, version, contaBloqueada, contaExpirada, contaHabilitada, credencialExpirada, email, password, sempreNovaAba, username, orgao_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, 0, 0, 1, 0, 'email@email.com', '$2a$10$/mCLiZIxHplKbJBQOt2Hhu6q/1xvxsKNh3AwBAO0JkLs2wjU8jIyq', 1, 'admin', 1);
INSERT INTO seguranca_usuario_privilegio(datacriacao, dataedicao, datainativo, version, privilegio_id, usuario_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, 1, 1);
INSERT INTO seguranca_usuario_privilegio(datacriacao, dataedicao, datainativo, version, privilegio_id, usuario_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, 2, 1);

INSERT INTO OrgaoItem(datacriacao, dataedicao, datainativo, version, usuario_id, orgao_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, 1, 1);

INSERT INTO ConfiguracaoSistema(datacriacao, dataedicao, datainativo, version, orgao_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, 1);

INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, 'EXECUTAR_SCRIPT_MENU-1', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, 'EXECUTAR_SCRIPT_MENU-2', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, 'EXECUTAR_SCRIPT_MENU-3', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, 'EXECUTAR_SCRIPT_MENU-4', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, 'EXECUTAR_SCRIPT_MENU-5', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, 'EXECUTAR_SCRIPT_MENU-6', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, 'EXECUTAR_SCRIPT_MENU-7', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, 'EXECUTAR_SCRIPT_MENU', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, 'EXECUTAR_BASE_INICIAL', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, 'EXECUTAR_DOMINIOS', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, 'EXECUTAR_DEFAULT_FILE', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, 'EXECUTAR_MODULO', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, 'EXECUTAR_INTERNACIONALIZACAO', 'true', 1);

INSERT INTO ClasseParceiro(dominioTipoParceiro_id) VALUES (24);
INSERT INTO ClasseParceiro(dominioTipoParceiro_id) VALUES (27);
INSERT INTO ClasseParceiro(dominioTipoParceiro_id) VALUES (25);
INSERT INTO ClasseParceiro(dominioTipoParceiro_id) VALUES (26);
INSERT INTO ClasseParceiro(dominioTipoParceiro_id) VALUES (28);

INSERT INTO UnidadeMedida(codigo, descricao, sigla, tipoNumerico_id) VALUES ('001', 'Unidade', 'UN', 346);
INSERT INTO UnidadeMedida(codigo, descricao, sigla, tipoNumerico_id) VALUES ('002', 'Caixa', 'CX', 346);
INSERT INTO UnidadeMedida(codigo, descricao, sigla, tipoNumerico_id) VALUES ('003', 'Pacote', 'PC', 346);
INSERT INTO UnidadeMedida(codigo, descricao, sigla, tipoNumerico_id) VALUES ('004', 'Gal�o', 'GL', 347);

INSERT INTO Pais(datacriacao, dataedicao, datainativo, version, codigo, nome, sigla) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, '55', 'Brasil', 'BR');
INSERT INTO Regiao(datacriacao, dataedicao, datainativo, version, codigo, nome, pais_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, '01', 'Centro-Oeste', 1);
INSERT INTO Estado(datacriacao, dataedicao, datainativo, version, codigo, nome, sigla, regiao_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, '0', 'Goi�s', 'GO', 1);
INSERT INTO Cidade(datacriacao, dataedicao, datainativo, version, codigo, codigoibge, nome, estado_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, '0', '12', 'Goi�nia', 1);
INSERT INTO Bairro(datacriacao, dataedicao, datainativo, version, codigo, nome, cidade_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, '0', 'Bairro 1', 1);
INSERT INTO Endereco(datacriacao, dataedicao, datainativo, version, cep, logradouro, nome, numero, bairro_id, cidade_id, dominioTipoEndereco_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, '74110100', 'Complemento 1', 'Endereco Teste', '0000', 1, 1, 9);
INSERT INTO Localizacao(datacriacao, dataedicao, datainativo, version, nome, orgao_id, endereco_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, 'SALA 001', 1, 1);

INSERT INTO ContaContabil(datacriacao, dataedicao, datainativo, version, codigo, descricao, percentualNaoDepreciavel, taxaDepreciacaoMensal, vidaUtil, orgao_id, dominioTipoContaContabil_id, dominioTipoMaterial_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, '4512', 'Conta do Almoxarifado', 0, 0, 0, 1, 125, 8);

INSERT INTO Configuracao(datacriacao, dataedicao, datainativo, version, contaContabilAlmoxarifado_id, dominioStatusBemExtraviado_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, 1, 53);

INSERT INTO MapaOrganizacional(datacriacao, dataedicao, datainativo, version, codigo, dataFim, dataInicio, nome, orgao_id) VALUES (SYSDATETIME(), SYSDATETIME(), null, 0, null, null, SYSDATETIME(), 'Mapa 1', 1);
INSERT INTO EstruturaOrganizacional(almoxarifado, classificacao, codigo, dataInicio, dataFim, possuiBemPatrimonial, id, configuracao_id, dominioTipoEstruturaOrganizacional_id, localizacao_id, orgao_id) VALUES (0, 'classificacao teste', null,  SYSDATETIME(), null, 0, 1, 1, 67, 1, null);


<!-- DASHBOARD -->


SET IDENTITY_INSERT widget ON;

INSERT INTO widget ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "apresentarurlservico", "descricao", "nome", "urlservico", "inativador_id", "autor_id", "editor_id", "tipocomponente_id")
VALUES (6, NULL, NULL, '2015-04-02 10:17:33.535', '2015-04-02 10:17:33.535', 0, 1, 'HighChart', 'HighChart', NULL, NULL, 1, 1, (select max(id) from dominio where chave='tipoWidget' and nome='highchartcit'));

INSERT INTO widget ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "apresentarurlservico", "descricao", "nome", "urlservico", "inativador_id", "autor_id", "editor_id", "tipocomponente_id")
VALUES (7, NULL, NULL, '2015-04-02 10:23:04.388', '2015-04-02 10:23:04.388', 0, 1, 'Google Chart', 'Google Chart', NULL, NULL, 1, 1, (select max(id) from dominio where chave='tipoWidget' and nome='googlechartcit'));

INSERT INTO widget ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "apresentarurlservico", "descricao", "nome", "urlservico", "inativador_id", "autor_id", "editor_id", "tipocomponente_id")
VALUES (8, NULL, NULL, '2015-04-02 10:27:08.019', '2015-04-02 10:27:08.019', 0, 1, 'Google Chart Gaug', 'Google Chart Gaug', NULL, NULL, 1, 1, (select max(id) from dominio where chave='tipoWidget' and nome='googlechartgaugecit'));

INSERT INTO widget ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "apresentarurlservico", "descricao", "nome", "urlservico", "inativador_id", "autor_id", "editor_id", "tipocomponente_id")
VALUES (9, NULL, NULL, '2015-04-02 10:27:08.019', '2015-04-02 10:27:08.019', 0, 1, 'Html', 'Html', NULL, NULL, 1, 1, (select max(id) from dominio where chave='tipoWidget' and nome='markdown'));

INSERT INTO widget ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "apresentarurlservico", "descricao", "nome", "urlservico", "inativador_id", "autor_id", "editor_id", "tipocomponente_id")
VALUES (10, NULL, NULL, '2015-04-02 10:27:08.019', '2015-04-02 10:27:08.019', 0, 1, 'Notícia', 'Notícia', NULL, NULL, 1, 1, (select max(id) from dominio where chave='tipoWidget' and nome='noticia'));

INSERT INTO widget ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "apresentarurlservico", "descricao", "nome", "urlservico", "inativador_id", "autor_id", "editor_id", "tipocomponente_id")
VALUES (11, NULL, NULL, '2015-04-02 10:27:08.019', '2015-04-02 10:27:08.019', 0, 1, 'Temperatura', 'Temperatura', NULL, NULL, 1, 1, (select max(id) from dominio where chave='tipoWidget' and nome='temperatura'));

INSERT INTO widget ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "apresentarurlservico", "descricao", "nome", "urlservico", "inativador_id", "autor_id", "editor_id", "tipocomponente_id")
VALUES (12, NULL, NULL, '2015-04-02 10:27:08.019', '2015-04-02 10:27:08.019', 0, 1, 'Link', 'Link', NULL, NULL, 1, 1, (select max(id) from dominio where chave='tipoWidget' and nome='link'));

SET IDENTITY_INSERT widget OFF;

SET IDENTITY_INSERT widgetparametro ON;

INSERT INTO widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (21, NULL, NULL, '2015-04-02 10:17:33.567', '2015-04-02 10:17:33.567', 0, 0, 'tipoHighChart', NULL, 'Tipo Grafico', NULL, NULL, NULL, 1, 1, (select max(id) from dominio where chave='tipoHighChart' and nome='pi'), (select max(id) from dominio where chave='tipoDado' and nome='TIPO_DOMINIO'), 6, NULL);

INSERT INTO widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (22, NULL, NULL, '2015-04-02 10:17:33.571', '2015-04-02 10:17:33.571', 0, 0, NULL, NULL, 'titulo', NULL, 'titulo', NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 6, NULL);

INSERT INTO widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (23, NULL, NULL, '2015-04-02 10:17:33.574', '2015-04-02 10:17:33.574', 0, 0, NULL, NULL, 'sub titulo', NULL, 'sub titulo', NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 6, NULL);

INSERT INTO widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (24, NULL, NULL, '2015-04-02 10:23:04.391', '2015-04-02 10:23:04.391', 0, 0, NULL, NULL, 'hAxis titulo', NULL, 'hAxis titulo', NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 7, NULL);

INSERT INTO widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (25, NULL, NULL, '2015-04-02 10:23:04.394', '2015-04-02 10:23:04.394', 0, 0, NULL, NULL, 'vAxis titulo', NULL, 'vAxis titulo', NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 7, NULL);

INSERT INTO widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (26, NULL, NULL, '2015-04-02 10:23:04.397', '2015-04-02 10:23:04.397', 0, 1, NULL, NULL, 'isStacked', NULL, NULL, NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='BOOLEAN'), 7, NULL);

INSERT INTO widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (27, NULL, NULL, '2015-04-02 10:23:04.400', '2015-04-02 10:23:04.400', 0, 0, NULL, NULL, 'Titulo', NULL, 'Titulo', NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 7, NULL);

INSERT INTO widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (28, NULL, NULL, '2015-04-02 10:23:04.402', '2015-04-02 10:23:04.402', 0, 0, 'tipoGoogleChart', NULL, 'Tipo Grafico', NULL, NULL, NULL, 1, 1, (select max(id) from dominio where chave='tipoGoogleChart' and nome='PieChart'), (select max(id) from dominio where chave='tipoDado' and nome='TIPO_DOMINIO'), 7, NULL);

INSERT INTO widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (29, NULL, NULL, '2015-04-02 10:27:08.023', '2015-04-02 10:27:08.023', 0, 0, NULL, NULL, 'max', 100, NULL, NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 8, NULL);

INSERT INTO widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (30, NULL, NULL, '2015-04-02 10:27:08.029', '2015-04-02 10:27:08.029', 0, 0, NULL, NULL, 'yellowFrom', 70, NULL, NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 8, NULL);

INSERT INTO widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (31, NULL, NULL, '2015-04-02 10:27:08.035', '2015-04-02 10:27:08.035', 0, 0, NULL, NULL, 'yellowTo', 80, NULL, NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 8, NULL);

INSERT INTO widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (32, NULL, NULL, '2015-04-02 10:27:08.038', '2015-04-02 10:27:08.038', 0, 0, NULL, NULL, 'redFrom', 80, NULL, NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 8, NULL);

INSERT INTO widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (33, NULL, NULL, '2015-04-02 10:27:08.042', '2015-04-02 10:27:08.042', 0, 0, NULL, NULL, 'redTo', 100, NULL, NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 8, NULL);

INSERT INTO widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (34, NULL, NULL, '2015-04-02 10:27:08.049', '2015-04-02 10:27:08.049', 0, 0, NULL, NULL, 'greenFrom', 40, NULL, NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 8, NULL);

INSERT INTO widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (35, NULL, NULL, '2015-04-02 10:27:08.054', '2015-04-02 10:27:08.054', 0, 0, NULL, NULL, 'greenTo', 70, NULL, NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 8, NULL);

INSERT INTO widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (36, NULL, NULL, '2015-04-02 10:27:08.057', '2015-04-02 10:27:08.057', 0, 0, NULL, NULL, 'minorTicks', 5, NULL, NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 8, NULL);

SET IDENTITY_INSERT widgetparametro OFF;

<!-- FIM SQL SERVER -->


<!-- POSTGRESQL -->
INSERT INTO seguranca_privilegio(datacriacao, dataedicao, datainativo, version, nome, descricao) VALUES (now(), now(), null, 0, 'ROLE_ADMIN', 'ROLE_ADMIN');
INSERT INTO seguranca_privilegio(datacriacao, dataedicao, datainativo, version, nome, descricao) VALUES (now(), now(), null, 0, 'ROLE_USER', 'ROLE_USER');

INSERT INTO EstruturaOrgao(datacriacao, dataedicao, datainativo, version, dataReferenciaVigente, isOrgao, nome, sigla) VALUES (now(), now(), null, 0, now(), true, 'Org�o 1', 'Orgao');

INSERT INTO seguranca_usuario(datacriacao, dataedicao, datainativo, version, contaBloqueada, contaExpirada, contaHabilitada, credencialExpirada, email, password, sempreNovaAba, username, orgao_id) VALUES (now(), now(), null, 0, false, false, true, false, 'email@email.com', '$2a$10$/mCLiZIxHplKbJBQOt2Hhu6q/1xvxsKNh3AwBAO0JkLs2wjU8jIyq', true, 'admin', 1);
INSERT INTO seguranca_usuario_privilegio(datacriacao, dataedicao, datainativo, version, privilegio_id, usuario_id) VALUES (now(), now(), null, 0, 1, 1);
INSERT INTO seguranca_usuario_privilegio(datacriacao, dataedicao, datainativo, version, privilegio_id, usuario_id) VALUES (now(), now(), null, 0, 2, 1);

INSERT INTO OrgaoItem(datacriacao, dataedicao, datainativo, version, usuario_id, orgao_id) VALUES (now(), now(), null, 0, 1, 1);

INSERT INTO ConfiguracaoSistema(datacriacao, dataedicao, datainativo, version, orgao_id) VALUES (now(), now(), null, 0, 1);

INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (now(), now(), null, 0, 'EXECUTAR_SCRIPT_MENU-1', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (now(), now(), null, 0, 'EXECUTAR_SCRIPT_MENU-2', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (now(), now(), null, 0, 'EXECUTAR_SCRIPT_MENU-3', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (now(), now(), null, 0, 'EXECUTAR_SCRIPT_MENU-4', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (now(), now(), null, 0, 'EXECUTAR_SCRIPT_MENU-5', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (now(), now(), null, 0, 'EXECUTAR_SCRIPT_MENU-6', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (now(), now(), null, 0, 'EXECUTAR_SCRIPT_MENU-7', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (now(), now(), null, 0, 'EXECUTAR_SCRIPT_MENU', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (now(), now(), null, 0, 'EXECUTAR_DOMINIOS', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (now(), now(), null, 0, 'EXECUTAR_DEFAULT_FILE', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (now(), now(), null, 0, 'EXECUTAR_MODULO', 'false', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (now(), now(), null, 0, 'EXECUTAR_INTERNACIONALIZACAO', 'true', 1);
INSERT INTO ParametroSistema(datacriacao, dataedicao, datainativo, version, chave, valor, configuracaoSistema_id) VALUES (now(), now(), null, 0, 'MASCARA_NUMERO_IDENTIFICACAO', 'AAAA999999', 1);

INSERT INTO ClasseParceiro(dominioTipoParceiro_id) VALUES (24);
INSERT INTO ClasseParceiro(dominioTipoParceiro_id) VALUES (27);
INSERT INTO ClasseParceiro(dominioTipoParceiro_id) VALUES (25);
INSERT INTO ClasseParceiro(dominioTipoParceiro_id) VALUES (26);
INSERT INTO ClasseParceiro(dominioTipoParceiro_id) VALUES (28);

INSERT INTO UnidadeMedida(codigo, descricao, sigla, tipoNumerico_id) VALUES ('001', 'Unidade', 'UN', 346);
INSERT INTO UnidadeMedida(codigo, descricao, sigla, tipoNumerico_id) VALUES ('002', 'Caixa', 'CX', 346);
INSERT INTO UnidadeMedida(codigo, descricao, sigla, tipoNumerico_id) VALUES ('003', 'Pacote', 'PC', 346);
INSERT INTO UnidadeMedida(codigo, descricao, sigla, tipoNumerico_id) VALUES ('004', 'Gal�o', 'GL', 347);

INSERT INTO Pais(datacriacao, dataedicao, datainativo, version, codigo, nome, sigla) VALUES (now(), now(), null, 0, '55', 'Brasil', 'BR');
INSERT INTO Regiao(datacriacao, dataedicao, datainativo, version, codigo, nome, pais_id) VALUES (now(), now(), null, 0, '01', 'Centro-Oeste', 1);
INSERT INTO Estado(datacriacao, dataedicao, datainativo, version, codigo, nome, sigla, regiao_id) VALUES (now(), now(), null, 0, '0', 'Goi�s', 'GO', 1);
INSERT INTO Cidade(datacriacao, dataedicao, datainativo, version, codigo, codigoibge, nome, estado_id) VALUES (now(), now(), null, 0, '0', '12', 'Goi�nia', 1);
INSERT INTO Bairro(datacriacao, dataedicao, datainativo, version, codigo, nome, cidade_id) VALUES (now(), now(), null, 0, '0', 'Bairro 1', 1);
INSERT INTO Endereco(datacriacao, dataedicao, datainativo, version, cep, logradouro, nome, numero, bairro_id, cidade_id, dominioTipoEndereco_id) VALUES (now(), now(), null, 0, '74110100', 'Complemento 1', 'Endereco Teste', '0000', 1, 1, 9);
INSERT INTO Localizacao(datacriacao, dataedicao, datainativo, version, nome, orgao_id, endereco_id) VALUES (now(), now(), null, 0, 'SALA 001', 1, 1);

INSERT INTO ContaContabil(datacriacao, dataedicao, datainativo, version, codigo, descricao, percentualNaoDepreciavel, taxaDepreciacaoMensal, vidaUtil, orgao_id, dominioTipoContaContabil_id, dominioTipoMaterial_id) VALUES (now(), now(), null, 0, '4512', 'Conta do Almoxarifado', 0, 0, 0, 1, 125, 8);

INSERT INTO Configuracao(datacriacao, dataedicao, datainativo, version, contaContabilAlmoxarifado_id, dominioStatusBemExtraviado_id) VALUES (now(), now(), null, 0, 1, 53);

INSERT INTO MapaOrganizacional(datacriacao, dataedicao, datainativo, version, codigo, dataFim, dataInicio, nome, orgao_id) VALUES (now(), now(), null, 0, null, null, now(), 'Mapa 1', 1);
INSERT INTO EstruturaOrganizacional(almoxarifado, classificacao, codigo, dataInicio, dataFim, possuiBemPatrimonial, id, configuracao_id, dominioTipoEstruturaOrganizacional_id, localizacao_id, orgao_id) VALUES (FALSE, 'classificacao teste', null,  now(), null, false, 1, 1, 67, 1, null);


<!-- DASHBOARDS -->

INSERT INTO public.widget ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "apresentarurlservico", "descricao", "nome", "urlservico", "inativador_id", "autor_id", "editor_id", "tipocomponente_id")
VALUES (6, NULL, NULL, E'2015-04-02 10:17:33.535', E'2015-04-02 10:17:33.535', 0, True, E'HighChart', E'HighChart', NULL, NULL, 1, 1, (select max(id) from dominio where chave='tipoWidget' and nome='highchartcit'));

INSERT INTO public.widget ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "apresentarurlservico", "descricao", "nome", "urlservico", "inativador_id", "autor_id", "editor_id", "tipocomponente_id")
VALUES (7, NULL, NULL, E'2015-04-02 10:23:04.388', E'2015-04-02 10:23:04.388', 0, True, E'Google Chart', E'Google Chart', NULL, NULL, 1, 1, (select max(id) from dominio where chave='tipoWidget' and nome='googlechartcit'));

INSERT INTO public.widget ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "apresentarurlservico", "descricao", "nome", "urlservico", "inativador_id", "autor_id", "editor_id", "tipocomponente_id")
VALUES (8, NULL, NULL, E'2015-04-02 10:27:08.019', E'2015-04-02 10:27:08.019', 0, True, E'Google Chart Gauge', E'Google Chart Gauge', NULL, NULL, 1, 1, (select max(id) from dominio where chave='tipoWidget' and nome='googlechartgaugecit'));

INSERT INTO public.widget ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "apresentarurlservico", "descricao", "nome", "urlservico", "inativador_id", "autor_id", "editor_id", "tipocomponente_id")
VALUES (9, NULL, NULL, E'2015-04-02 10:27:08.019', E'2015-04-02 10:27:08.019', 0, True, E'Html', E'Html', NULL, NULL, 1, 1, (select max(id) from dominio where chave='tipoWidget' and nome='markdown'));

INSERT INTO public.widget ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "apresentarurlservico", "descricao", "nome", "urlservico", "inativador_id", "autor_id", "editor_id", "tipocomponente_id")
VALUES (10, NULL, NULL, E'2015-04-02 10:27:08.019', E'2015-04-02 10:27:08.019', 0, True, E'Notícia', E'Notícia', NULL, NULL, 1, 1, (select max(id) from dominio where chave='tipoWidget' and nome='noticia'));

INSERT INTO public.widget ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "apresentarurlservico", "descricao", "nome", "urlservico", "inativador_id", "autor_id", "editor_id", "tipocomponente_id")
VALUES (11, NULL, NULL, E'2015-04-02 10:27:08.019', E'2015-04-02 10:27:08.019', 0, True, E'Temperatura', E'Temperatura', NULL, NULL, 1, 1, (select max(id) from dominio where chave='tipoWidget' and nome='temperatura'));

INSERT INTO public.widget ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "apresentarurlservico", "descricao", "nome", "urlservico", "inativador_id", "autor_id", "editor_id", "tipocomponente_id")
VALUES (12, NULL, NULL, E'2015-04-02 10:27:08.019', E'2015-04-02 10:27:08.019', 0, True, E'Link', E'Link', NULL, NULL, 1, 1, (select max(id) from dominio where chave='tipoWidget' and nome='link'));

/* Data for the 'public.widgetparametro' table  (Records 1 - 16) */

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (21, NULL, NULL, E'2015-04-02 10:17:33.567', E'2015-04-02 10:17:33.567', 0, False, E'tipoHighChart', NULL, E'Tipo Grafico', NULL, NULL, NULL, 1, 1, (select max(id) from dominio where chave='tipoHighChart' and nome='pie'), (select max(id) from dominio where chave='tipoDado' and nome='TIPO_DOMINIO'), 6, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (22, NULL, NULL, E'2015-04-02 10:17:33.571', E'2015-04-02 10:17:33.571', 0, False, NULL, NULL, E'titulo', NULL, E'titulo', NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 6, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (23, NULL, NULL, E'2015-04-02 10:17:33.574', E'2015-04-02 10:17:33.574', 0, False, NULL, NULL, E'sub titulo', NULL, E'sub titulo', NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 6, NULL);



INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (24, NULL, NULL, E'2015-04-02 10:23:04.391', E'2015-04-02 10:23:04.391', 0, False, NULL, NULL, E'hAxis titulo', NULL, E'hAxis titulo', NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 7, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (25, NULL, NULL, E'2015-04-02 10:23:04.394', E'2015-04-02 10:23:04.394', 0, False, NULL, NULL, E'vAxis titulo', NULL, E'vAxis titulo', NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 7, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (26, NULL, NULL, E'2015-04-02 10:23:04.397', E'2015-04-02 10:23:04.397', 0, True, NULL, NULL, E'isStacked', NULL, NULL, NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='BOOLEAN'), 7, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (27, NULL, NULL, E'2015-04-02 10:23:04.400', E'2015-04-02 10:23:04.400', 0, False, NULL, NULL, E'Titulo', NULL, E'Titulo', NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 7, NULL);



INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (28, NULL, NULL, E'2015-04-02 10:23:04.402', E'2015-04-02 10:23:04.402', 0, False, E'tipoGoogleChart', NULL, E'Tipo Grafico', NULL, NULL, NULL, 1, 1, (select max(id) from dominio where chave='tipoGoogleChart' and nome='PieChart'), (select max(id) from dominio where chave='tipoDado' and nome='TIPO_DOMINIO'), 7, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (29, NULL, NULL, E'2015-04-02 10:27:08.023', E'2015-04-02 10:27:08.023', 0, False, NULL, NULL, E'max', 100, NULL, NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 8, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (30, NULL, NULL, E'2015-04-02 10:27:08.029', E'2015-04-02 10:27:08.029', 0, False, NULL, NULL, E'yellowFrom', 70, NULL, NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 8, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (31, NULL, NULL, E'2015-04-02 10:27:08.035', E'2015-04-02 10:27:08.035', 0, False, NULL, NULL, E'yellowTo', 80, NULL, NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 8, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (32, NULL, NULL, E'2015-04-02 10:27:08.038', E'2015-04-02 10:27:08.038', 0, False, NULL, NULL, E'redFrom', 80, NULL, NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 8, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (33, NULL, NULL, E'2015-04-02 10:27:08.042', E'2015-04-02 10:27:08.042', 0, False, NULL, NULL, E'redTo', 100, NULL, NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 8, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (34, NULL, NULL, E'2015-04-02 10:27:08.049', E'2015-04-02 10:27:08.049', 0, False, NULL, NULL, E'greenFrom', 40, NULL, NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 8, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (35, NULL, NULL, E'2015-04-02 10:27:08.054', E'2015-04-02 10:27:08.054', 0, False, NULL, NULL, E'greenTo', 70, NULL, NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 8, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (36, NULL, NULL, E'2015-04-02 10:27:08.057', E'2015-04-02 10:27:08.057', 0, False, NULL, NULL, E'minorTicks', 5, NULL, NULL, 1, 1, NULL, (select max(id) from dominio where chave='tipoDado' and nome='TEXT_FIELD'), 8, NULL);

<!-- FIM POSTGRESQL -->


<!-- INICIO NOVA ESTRUTURA POSTGRESQL -->

INSERT INTO seguranca_privilegio(datacriacao, dataedicao, datainativo, version, nome, descricao) VALUES (now(), now(), null, 0, 'ROLE_ADMIN', 'ROLE_ADMIN');
INSERT INTO seguranca_privilegio(datacriacao, dataedicao, datainativo, version, nome, descricao) VALUES (now(), now(), null, 0, 'ROLE_USER', 'ROLE_USER');

INSERT INTO organizacao(datacriacao, dataedicao, version, datainicio, datareferenciavigente, nome, sigla) VALUES (now(), now(), 0, now(), now(), 'Central IT', 'CIT');

INSERT INTO seguranca_usuario(datacriacao, dataedicao, datainativo, version, contaBloqueada, contaExpirada, contaHabilitada, credencialExpirada, email, password, sempreNovaAba, username, organizacao_id) VALUES (now(), now(), null, 0, false, false, true, false, 'email@email.com', '$2a$10$/mCLiZIxHplKbJBQOt2Hhu6q/1xvxsKNh3AwBAO0JkLs2wjU8jIyq', true, 'admin', 1);
INSERT INTO seguranca_usuario_privilegio(datacriacao, dataedicao, datainativo, version, privilegio_id, usuario_id) VALUES (now(), now(), null, 0, 1, 1);
INSERT INTO seguranca_usuario_privilegio(datacriacao, dataedicao, datainativo, version, privilegio_id, usuario_id) VALUES (now(), now(), null, 0, 2, 1);

INSERT INTO usuarioorganizacaoitem(datacriacao, dataedicao, version, organizacao_id, usuario_id) VALUES (now(), now(), 0, 1, 1);

INSERT INTO configuracao(datacriacao, dataedicao, version, organizacao_id) VALUES (now(), now(), 0, 1);

INSERT INTO configuracaoparametrosistema(datacriacao, dataedicao, version, grupo, chave, valor, configuracao_id) VALUES (now(), now(), 0, null, 'EXECUTAR_SCRIPT_MENU-1', 'false', 1);
INSERT INTO configuracaoparametrosistema(datacriacao, dataedicao, version, grupo, chave, valor, configuracao_id) VALUES (now(), now(), 0, null, 'EXECUTAR_SCRIPT_MENU-2', 'false', 1);
INSERT INTO configuracaoparametrosistema(datacriacao, dataedicao, version, grupo, chave, valor, configuracao_id) VALUES (now(), now(), 0, null, 'EXECUTAR_SCRIPT_MENU-3', 'false', 1);
INSERT INTO configuracaoparametrosistema(datacriacao, dataedicao, version, grupo, chave, valor, configuracao_id) VALUES (now(), now(), 0, null, 'EXECUTAR_SCRIPT_MENU-4', 'false', 1);
INSERT INTO configuracaoparametrosistema(datacriacao, dataedicao, version, grupo, chave, valor, configuracao_id) VALUES (now(), now(), 0, null, 'EXECUTAR_SCRIPT_MENU-5', 'false', 1);
INSERT INTO configuracaoparametrosistema(datacriacao, dataedicao, version, grupo, chave, valor, configuracao_id) VALUES (now(), now(), 0, null, 'EXECUTAR_SCRIPT_MENU-6', 'false', 1);
INSERT INTO configuracaoparametrosistema(datacriacao, dataedicao, version, grupo, chave, valor, configuracao_id) VALUES (now(), now(), 0, null, 'EXECUTAR_SCRIPT_MENU-7', 'false', 1);
INSERT INTO configuracaoparametrosistema(datacriacao, dataedicao, version, grupo, chave, valor, configuracao_id) VALUES (now(), now(), 0, null, 'EXECUTAR_SCRIPT_MENU', 'false', 1);
INSERT INTO configuracaoparametrosistema(datacriacao, dataedicao, version, grupo, chave, valor, configuracao_id) VALUES (now(), now(), 0, null, 'EXECUTAR_DOMINIOS', 'false', 1);
INSERT INTO configuracaoparametrosistema(datacriacao, dataedicao, version, grupo, chave, valor, configuracao_id) VALUES (now(), now(), 0, null, 'EXECUTAR_DEFAULT_FILE', 'false', 1);
INSERT INTO configuracaoparametrosistema(datacriacao, dataedicao, version, grupo, chave, valor, configuracao_id) VALUES (now(), now(), 0, null, 'EXECUTAR_MODULO', 'false', 1);
INSERT INTO configuracaoparametrosistema(datacriacao, dataedicao, version, grupo, chave, valor, configuracao_id) VALUES (now(), now(), 0, null, 'EXECUTAR_INTERNACIONALIZACAO', 'true', 1);
INSERT INTO configuracaoparametrosistema(datacriacao, dataedicao, version, grupo, chave, valor, configuracao_id) VALUES (now(), now(), 0, null, 'PATRIMONIO_MASCARA_NUMERO_PATRIMONIO', '9999999999', 1);
INSERT INTO configuracaoparametrosistema(datacriacao, dataedicao, version, grupo, chave, valor, configuracao_id) VALUES (now(), now(), 0, null, 'CONTA_CONTABIL_ALMOXARIFADO', '1', 1);
INSERT INTO configuracaoparametrosistema(datacriacao, dataedicao, version, grupo, chave, valor, configuracao_id) VALUES (now(), now(), 0, null, 'TIPO_AVALIACAO_MONETARIA_ESTOQUE', '2', 1);
INSERT INTO configuracaoparametrosistema(datacriacao, dataedicao, version, grupo, chave, valor, configuracao_id) VALUES (now(), now(), 0, null, 'RELATORIO_PRIMEIRO_TITULO', 'Defina o Primeiro título', 1);
INSERT INTO configuracaoparametrosistema(datacriacao, dataedicao, version, grupo, chave, valor, configuracao_id) VALUES (now(), now(), 0, null, 'RELATORIO_SEGUNDO_TITULO', 'Defina o Segundo título', 1);
INSERT INTO configuracaoparametrosistema(datacriacao, dataedicao, version, grupo, chave, valor, configuracao_id) VALUES (now(), now(), 0, null, 'RELATORIO_TERCEIRO_TITULO', 'Defina o Terceiro título', 1);
INSERT INTO configuracaoparametrosistema(datacriacao, dataedicao, version, grupo, chave, valor, configuracao_id) VALUES (now(), now(), 0, null, 'MASCARA_NUMERO_IDENTIFICACAO', 'AAAA999999', 1);

INSERT INTO ClasseParceiro(dominioTipoParceiro_id) VALUES (24);
INSERT INTO ClasseParceiro(dominioTipoParceiro_id) VALUES (27);
INSERT INTO ClasseParceiro(dominioTipoParceiro_id) VALUES (25);
INSERT INTO ClasseParceiro(dominioTipoParceiro_id) VALUES (26);
INSERT INTO ClasseParceiro(dominioTipoParceiro_id) VALUES (28);

INSERT INTO Pais(datacriacao, dataedicao, datainativo, version, codigo, nome, sigla) VALUES (now(), now(), null, 0, '55', 'Brasil', 'BR');
INSERT INTO Regiao(datacriacao, dataedicao, datainativo, version, codigo, nome, pais_id) VALUES (now(), now(), null, 0, '01', 'Centro-Oeste', 1);
INSERT INTO Estado(datacriacao, dataedicao, datainativo, version, codigo, nome, sigla, regiao_id) VALUES (now(), now(), null, 0, '0', 'Goi�s', 'GO', 1);
INSERT INTO Cidade(datacriacao, dataedicao, datainativo, version, codigo, codigoibge, nome, estado_id) VALUES (now(), now(), null, 0, '0', '12', 'Goi�nia', 1);
INSERT INTO Bairro(datacriacao, dataedicao, datainativo, version, codigo, nome, cidade_id) VALUES (now(), now(), null, 0, '0', 'Bairro 1', 1);
INSERT INTO Endereco(datacriacao, dataedicao, datainativo, version, cep, logradouro, nome, numero, bairro_id, cidade_id, dominioTipoEndereco_id) VALUES (now(), now(), null, 0, '74110100', 'Complemento 1', 'Endereco Teste', '0000', 1, 1, 9);
INSERT INTO Localizacao(datacriacao, dataedicao, datainativo, version, nome, organizacao_id, endereco_id) VALUES (now(), now(), null, 0, 'SALA 001', 1, 1);

INSERT INTO ContaContabil(datacriacao, dataedicao, datainativo, version, codigo, descricao, percentualNaoDepreciavel, taxaDepreciacaoMensal, vidaUtil, organizacao_id, dominioTipoContaContabil_id, dominioTipoMaterial_id) VALUES (now(), now(), null, 0, '4512', 'Conta do Almoxarifado', 0, 0, 0, 1, 125, 8);

INSERT INTO MapaOrganizacional(datacriacao, dataedicao, datainativo, version, codigo, dataFim, dataInicio, nome, organizacao_id) VALUES (now(), now(), null, 0, null, null, now(), 'Mapa 1', 1);
--INSERT INTO EstruturaOrganizacional(almoxarifado, classificacao, codigo, dataInicio, dataFim, possuiBemPatrimonial, id, configuracao_id, dominioTipoEstruturaOrganizacional_id, localizacao_id, orgao_id) VALUES (FALSE, 'classificacao teste', null,  now(), null, false, 1, 1, 67, 1, null);

<!-- FIM NOVA ESTRUTURA POSTGRESQL -->

<!-- INICIO	CENTROCUSTO POSTGRESQL -->

INSERT INTO centrocusto(datacriacao, dataedicao, version, codigo, descricao, autor_id, editor_id, organizacao_id) VALUES (now(), now(), 0, '000.0', 'CENTRO DE CUSTO PARENT', 1, 1, 1);
INSERT INTO centrocusto(datacriacao, dataedicao, version, codigo, descricao, autor_id, editor_id, organizacao_id, centrocustoparent_id) VALUES (now(), now(), 0, '000.1', 'CENTRO DE CUSTO ORGAO', 1, 1, 1, 1);

<!-- FIM CENTROCUSTO POSTGRESQL -->
