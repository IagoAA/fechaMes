INSERT INTO pais	(datacriacao,dataedicao,datainativo,version,codigo,nome,sigla,autor_id,editor_id,inativador_id)VALUES('03/04/2015 16:05:11','03/04/2015 16:05:11',NULL,0,'55','Brasil','BR',NULL,NULL,NULL);
INSERT INTO regiao	(datacriacao,dataedicao,datainativo,version,codigo,nome,autor_id,editor_id,inativador_id,pais_id)VALUES('03/04/2015 16:05:11','03/04/2015 16:05:11',NULL,0,'01','Centro-Oeste',NULL,NULL,NULL,1);
INSERT INTO regiao	(datacriacao,dataedicao,datainativo,version,codigo,nome,autor_id,editor_id,inativador_id,pais_id)VALUES('03/04/2015 16:05:11','03/04/2015 16:05:11',NULL,0,'02','Sul',NULL,NULL,NULL,1);
INSERT INTO estado	(datacriacao,dataedicao,datainativo,version,codigo,nome,sigla,autor_id,editor_id,inativador_id,regiao_id)VALUES('03/04/2015 16:05:11','03/04/2015 16:05:11',NULL,0,'0','Goiás','GO',NULL,NULL,NULL,1);
INSERT INTO estado	(datacriacao,dataedicao,datainativo,version,codigo,nome,sigla,autor_id,editor_id,inativador_id,regiao_id)VALUES('03/04/2015 16:05:11','03/04/2015 16:05:11',NULL,0,'1','Rio Grande do Sul','RS',NULL,NULL,NULL,2);
INSERT INTO cidade	(datacriacao,dataedicao,datainativo,version,codigo,codigoibge,nome,autor_id,editor_id,inativador_id,estado_id)VALUES('03/04/2015 16:05:11','03/04/2015 16:05:11',NULL,0,'0','12','Goiania',NULL,NULL,NULL,1);
INSERT INTO cidade	(datacriacao,dataedicao,datainativo,version,codigo,codigoibge,nome,autor_id,editor_id,inativador_id,estado_id)VALUES('03/04/2015 16:05:11','03/04/2015 16:05:11',NULL,0,'1','13','Inhumas',NULL,NULL,NULL,1);
INSERT INTO cidade	(datacriacao,dataedicao,datainativo,version,codigo,codigoibge,nome,autor_id,editor_id,inativador_id,estado_id)VALUES('03/04/2015 16:05:11','03/04/2015 16:05:11',NULL,0,'0','12','Rio Grande',NULL,NULL,NULL,2);
INSERT INTO bairro	(datacriacao,dataedicao,datainativo,version,codigo,nome,autor_id,editor_id,inativador_id,cidade_id)VALUES('03/04/2015 16:05:11','03/04/2015 16:05:11',NULL,0,'0','Bairro 1',NULL,NULL,NULL,1);
INSERT INTO bairro	(datacriacao,dataedicao,datainativo,version,codigo,nome,autor_id,editor_id,inativador_id,cidade_id)VALUES('03/04/2015 16:05:11','03/04/2015 16:05:11',NULL,0,'1','Bairro 2',NULL,NULL,NULL,2);

SET IDENTITY_INSERT modulo ON;

INSERT INTO modulo(id, datacriacao, dataedicao, datainativo, version, nome, caminho, baseUrl, restAngular, habilitado) VALUES (1, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'Patrimônio', '/citgrp-patrimonio-web', '/citgrp-patrimonio-web', 'RestangularPatrimonio', 1);
INSERT INTO modulo(id, datacriacao, dataedicao, datainativo, version, nome, caminho, baseUrl, restAngular, habilitado) VALUES (2, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'ESI', '/cit-esi-web', '/cit-esi-web', 'RestangularEsi', 1);
INSERT INTO modulo(id, datacriacao, dataedicao, datainativo, version, nome, caminho, baseUrl, restAngular, habilitado) VALUES (3, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'Contratos', '/cit-contratos-web', '/cit-contratos-web', 'RestangularContratos', 1);

SET IDENTITY_INSERT modulo OFF;

-------
SET IDENTITY_INSERT dominio ON;
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (1, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoFile', 'css', 'CSS');
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (2, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoFile', 'js', 'JS');

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (3, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoClassificacaoMaterial', 'Grupo', 'GRUPO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (4, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoClassificacaoMaterial', 'Sub grupo', 'SUB_GRUPO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (36, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoClassificacaoMaterial', 'Material', 'MATERIAL', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (37, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoClassificacaoMaterial', 'Detalhe', 'DETALHE', 4);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (5, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMaterial', 'Consumo', 'CONSUMO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (6, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMaterial', 'Permanente', 'PERMANENTE', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (7, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoEndereco', 'Residencial', 'RESIDENCIAL');
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (8, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoEndereco', 'Comercial', 'COMERCIAL');

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (9, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoRestricao', 'nenhum', 'NENHUM');
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (10, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoRestricao', 'por material', 'POR_MATERIAL');
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (11, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoRestricao', 'geral', 'GERAL');

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (12, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoDado', 'TextField', 'TEXT_FIELD', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (13, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoDado', 'TextArea', 'TEXT_AREA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (14, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoDado', 'Numérico', 'NUMBER', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (15, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoDado', 'Valor', 'DECIMAL', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (55, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoDado', 'Data', 'DATA', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (56, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoDado', 'Tabela tipo domínio', 'TIPO_DOMINIO', 6);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (57, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoDado', 'Arquivo', 'ARQUIVO', 7);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (187, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoDado', 'Lógico', 'LOGICO', 8);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (189, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoDado', 'URL de Serviço', 'URL_SERVICO', 9);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (197, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoDado', 'Boolean', 'BOOLEAN', 10);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (19, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoParceiro', 'Colaborador', 'COLABORADOR', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (17, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoParceiro', 'Orgão externo', 'ORGAO_EXTERNO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (18, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoParceiro', 'Portador', 'PORTADOR', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (16, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoParceiro', 'Fornecedor', 'FORNECEDOR', 4);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (20, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoDocumento', 'Nota fiscal', 'NOTA_FISCAL');
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (21, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoDocumento', 'Nota de empenho', 'NOTA_EMPENHO');

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (22, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoPessoa', 'Física', 'FISICA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (23, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoPessoa', 'Jurídica', 'JURIDICA', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (24, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoTelefone', 'Comercial', 'COMERCIAL');
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (25, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoTelefone', 'Residencial', 'RESIDENCIAL');
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (42, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoTelefone', 'Celular', 'CELULAR');

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (26, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoBem', 'Próprio contabilizado', 'PROPRIO_CONTABILIZADO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (79, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoBem', 'Próprio controlado', 'PROPRIO_CONTROLADO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (80, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoBem', 'De terceiros', 'DE_TERCEIROS', 3);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (27, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoEntrada', 'Orçamentária', 'ORCAMENTARIA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (135, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoEntrada', 'Extra orçamentária', 'EXTRA_ORCAMENTARIA', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (30, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoStatusBem', 'Utilizado', 'UTILIZADO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (58, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoStatusBem', 'Baixado', 'BAIXADO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (59, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoStatusBem', 'Disponível', 'DISPONIVEL', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (60, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoStatusBem', 'Em processo de baixa', 'EM_PROCESSO_DE_BAIXA', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (61, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoStatusBem', 'Em processo de transferência', 'EM_PROCESSO_DE_TRANSFERENCIA', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (62, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoStatusBem', 'Em saída temporária', 'EM_SAIDA_TEMPORARIA', 6);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (63, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoStatusBem', 'Extraviado', 'EXTRAVIADO', 7);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (64, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoStatusBem', 'Indisponível', 'INDISPONIVEL', 8);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (65, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoStatusBem', 'Sindicância', 'SINDICANCIA', 9);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (174, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoStatusBem', 'Não localizado', 'TIPO_STATUS_BEM_NAO_LOCALIZADO', 10);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (31, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoPrioridade', 'Baixo', 'BAIXO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (32, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoPrioridade', 'Médio', 'MEDIO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (33, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoPrioridade', 'Alto', 'ALTO', 3);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (34, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoNotificacao', 'Patrimônio', 'MODULO_PATRIMONIO', 1);
-- INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (35, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoNotificacao', 'Sistemas internos', 'MODULO_SISTEMAS_INTERNOS', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (38, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoSexo', 'Masculino', 'MASCULINO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (39, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoSexo', 'Feminino', 'FEMININO', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (40, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoEstadoCivil', 'Solteiro', 'SOLTEIRO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (41, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoEstadoCivil', 'Casado', 'CASADO', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (43, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoEstruturaOrganizacional', 'Unidade gestora', 'UNIDADE_GESTORA');
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (44, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoEstruturaOrganizacional', 'Unidade administrativa', 'UNIDADE_ADMINISTRATIVA');
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (45, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoEstruturaOrganizacional', 'Unidade localizadora', 'UNIDADE_LOCALIZADORA');

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (97, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAbrangencia', 'Local', 'LOCAL', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (46, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAbrangencia', 'Nacional', 'NACIONAL', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (98, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAbrangencia', 'Regional', 'REGIONAL', 3);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (47, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoPorte', 'Micro empresa', 'MICRO_EMPRESA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (94, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoPorte', 'Pequena', 'PEQUENA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (95, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoPorte', 'Media', 'MEDIA', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (96, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoPorte', 'Grande', 'GRANDE', 4);


INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (48, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoFornecedor', 'Juridica', 'JURIDICA');

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (49, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoModalidadeTransferencia', 'Permanente', 'PERMANENTE');
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (50, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoModalidadeTransferencia', 'Temporária', 'TEMPORARIA');

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (51, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoObjetivoTransferencia', 'Comodato', 'COMODATO');
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (52, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoObjetivoTransferencia', 'Conserto', 'CONSERTO');
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (53, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoObjetivoTransferencia', 'Empréstimo', 'EMPRESTIMO');
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (54, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoObjetivoTransferencia', 'Evento', 'EVENTO');


INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (66, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoUnidadeMedida', 'UN', 'UN');
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (67, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoUnidadeMedida', 'CX', 'CX');
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome) VALUES (68, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoUnidadeMedida', 'PC ', 'PC');

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (71, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAnexo', '.xml', 'XML', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (72, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAnexo', '.jpg', 'JPG', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (73, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAnexo', '.jpeg', 'JPEG', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (74, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAnexo', '.png', 'PNG', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (75, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAnexo', '.doc', 'DOC', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (76, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAnexo', '.xls', 'XLS', 6);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (270, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAnexo', '.pdf', 'PDF', 7);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (77, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAnexo', 'Sem extensão', 'SEM_EXTENSAO', 7);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (81, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoBaixa', 'Venda', 'VENDA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (82, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoBaixa', 'Doação', 'DOACAO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (83, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoBaixa', 'Cessão de uso', 'CESSAO_DE_USO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (84, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoBaixa', 'Extravio', 'EXTRAVIO', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (85, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoBaixa', 'Permuta', 'PERMUTA', 5);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (86, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoSituacaoBaixa', 'Em andamento', 'EM_ANDAMENTO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (87, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoSituacaoBaixa', 'Autorizada', 'AUTORIZADA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (88, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoSituacaoBaixa', 'Não autorizada', 'NAO_AUTORIZADA', 3);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (89, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoObjetivoSaidaTemporaria', 'Comodato', 'COMODATO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (90, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoObjetivoSaidaTemporaria', 'Conserto', 'CONSERTO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (91, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoObjetivoSaidaTemporaria', 'Empréstimo', 'EMPRESTIMO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (92, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoObjetivoSaidaTemporaria', 'Evento', 'EVENTO', 4);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (93, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoProjetoSaidaTemporaria', 'Outro', 'OUTRO', 1);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (99, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoContaContabil', 'Resultado', 'RESULTADO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (100, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoContaContabil', 'Receita', 'RECEITA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (101, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoContaContabil', 'Despesa', 'DESPESA', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (102, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoContaContabil', 'Passivo', 'PASSIVO', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (103, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoContaContabil', 'Ativo', 'ATIVO', 5);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (104, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoDepreciacao', 'Linear', 'LINEAR', 1);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (105, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAlteracaoBemPatrimonial', 'Período de garantia', 'PERIODO_GARANTIA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (106, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAlteracaoBemPatrimonial', 'Situação física', 'SITUACAO_FISICA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (107, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAlteracaoBemPatrimonial', 'Status do bem', 'STATUS_BEM', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (108, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAlteracaoBemPatrimonial', 'Número patrimonial', 'NUMERO_PATRIMONIAL', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (109, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAlteracaoBemPatrimonial', 'Reavaliação', 'REAVALIACAO', 5);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (110, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Ajuste de dados', 'AJUSTE_DE_DADOS', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (111, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Atualização monetária', 'ATUALIZACAO_MONETARIA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (112, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Reavaliação', 'REAVALIACAO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (113, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Inventário', 'INVENTARIO', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (136, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Alteração devida a depreciação', 'DEPRECIACAO', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (146, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Incorporação de bem patrimonial', 'INCORPORACAO_BEM_PATRIMONIAL', 6);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (148, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Início do processo de baixa de bens', 'INICIO_PROCESSO_BAIXA', 7);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (149, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Processo de baixa de bens concluído', 'PROCESSO_DE_BAIXA_AUTORIZADO', 8);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (150, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Processo de baixa de bens não autorizado', 'PROCESSO_DE_BAIXA_NAO_AUTORIZADO', 9);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (151, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Atualização do status na baixa', 'ATUALIZACAO_STATUS_BAIXA', 10);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (152, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Transferência por ocasião de atribuição a bem principal', 'TRANSFERENCIA_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL', 11);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (155, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Transferência entre estruturas organizacionais', 'TRANSFERENCIA_ENTRE_ESTRUTURAS_ORGANIZACIONAIS', 12);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (156, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Atribuição de novo detentor por ocasião de atribuição a bem principal', 'ATRIBUICAO_DE_NOVO_DETENTOR_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL', 13);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (157, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Atribuição de novo responsável por ocasião de atribuição a bem principal', 'ATRIBUICAO_DE_NOVO_RESPONSAVEL_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL', 14);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (158, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Atribuição a um bem principal', 'ATRIBUICAO_A_UM_BEM_PRINCIPAL', 15);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (159, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Atribuição de bens filhos', 'ATRIBUICAO_DE_BENS_FILHOS', 16);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (160, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Estorno da baixa', 'ESTORNO_BAIXA', 17);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (161, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Estorno do item da baixa', 'ESTORNO_ITEM_BAIXA', 18);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (162, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Exclusão da baixa', 'EXCLUIR_BAIXA', 19);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (163, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Exclusão do item da baixa', 'EXCLUIR_ITEM_BAIXA', 20);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (164, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Definição detentor', 'DEFINICAO_DETENTOR', 21);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (166, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Exclusão de um bem vinculado', 'EXCLUSAO_DE_UM_BEM_VINCULADO', 22);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (167, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Exclusão de vinculo a bem principal', 'EXCLUSAO_DE_VINCULO_A_BEM_PRINCIPAL', 23);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (168, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Exclusão do detentor por ocasião da exclusão do vínculo com o bem principal', 'EXCLUSAO_DO_DETENTOR_POR_OCASIAO_DA_EXCLUSAO_DO_VINCULO_COM_O_BEM_PRINCIPAL', 24);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (169, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Exclusão do responsável por ocasião da exclusão do vínculo com o bem principal', 'EXCLUSAO_DO_RESPONSAVEL_POR_OCASIAO_DA_EXCLUSAO_DO_VINCULO_COM_O_BEM_PRINCIPAL', 25);


INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (170, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Definição responsável', 'TIPO_MOTIVO_ALTERACAO_BEM_DEFINICAO_NOVO_RESPONSAVEL_CODIGO', 26);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (171, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Saída temporária', 'TIPO_MOTIVO_ALTERACAO_BEM_EM_SAIDA_TEMPORARIA', 27);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (172, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Transferência por ocasião de definição de novo detentor', 'TIPO_MOTIVO_ALTERACAO_BEM_TRANSFERENCIA_POR_OCASIAO_DE_DEFINICAO_DE_NOVO_DETENTOR', 28);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (175, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Remoção de Detentor por ocasião de atribuição a Bem Principal', 'TIPO_MOTIVO_ALTERACAO_BEM_REMOCAO_DE_DETENTOR_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL', 29);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (176, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Remoção de Responsável por ocasião de atribuição a Bem Principal', 'TIPO_MOTIVO_ALTERACAO_BEM_REMOCAO_DE_RESPONSAVEL_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL', 30);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (185, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Retorno da saída temporária', 'RETORNO_SAIDA_TEMPORARIA', 31);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (186, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMotivoAlteracaoBem', 'Remoção de Responsável por ocasião de atribuição de detentor que não é responsável pelo bem', 'REMOCAO_DE_RESPONSAVEL_POR_OCASIAO_DE_ATRIBUICAO_DE_DETENTOR_QUE_NAO_E_RESPONSAVEL_PELO_BEM', 32);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (28, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoSituacaoFisica', 'Bom', 'BOM', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (29, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoSituacaoFisica', 'Regular', 'REGULAR', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (116, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoSituacaoFisica', 'Inservível', 'INSERVIVEL', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (117, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoSituacaoFisica', 'Precário', 'PRECARIO', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (118, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoSituacaoFisica', 'Ruim', 'RUIM', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (119, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoSituacaoFisica', 'Antieconômico', 'ANTIECONOMICO', 6);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (120, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoSituacaoFisica', 'Danificado', 'DANIFICADO', 7);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (121, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoSituacaoFisica', 'Obsoleto', 'OBSOLETO', 8);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (122, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoSituacaoFisica', 'Ocioso', 'OCIOSO', 9);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (123, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoSituacaoFisica', 'Recuperável', 'RECUPERAVEL', 10);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (124, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoOperacao', 'Alteração de status', 'ATUALIZACAO_STATUS', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (125, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoOperacao', 'Processo de sindicância', 'PROCESSO_SINDICANCIA', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (126, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoHistorico', 'Alteração', 'ALTERACAO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (145, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoHistorico', 'Entrada', 'HISTORICO_ENTRADA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (147, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoHistorico', 'Baixa', 'HISTORICO_BAIXA', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (153, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoHistorico', 'Adição a bem principal', 'ADICAO_A_BEM_PRINCIPAL', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (154, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoHistorico', 'Transferência interna', 'TRANSFERENCIA_INTERNA', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (165, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoHistorico', 'Atribuir detentor', 'TIPO_HISTORICO_ATRIBUIR_DETENTOR', 6);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (127, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoStatusDepreciacao', 'Depreciável', 'DEPRECIAVEL', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (128, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoStatusDepreciacao', 'Armazenado no Almoxarifado', 'NAO_DEPRECIAVEL_ALMOXARIFADO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (129, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoStatusDepreciacao', 'Valor mínimo', 'NAO_DEPRECIAVEL_VALOR_MINIMO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (130, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoStatusDepreciacao', 'Vida útil zero', 'NAO_DEPRECIAVEL_VIDA_UTIL', 4);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (131, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMovimentoContaContabil', 'Entrada', 'ENTRADA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (132, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMovimentoContaContabil', 'Baixa', 'BAIXA', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (137, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMovimentacao', 'Transferência interna', 'TRANSFERENCIA_INTERNA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (138, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoMovimentacao', 'Definição detentor', 'DEFINICAO_DETENTOR', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (139, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoRecebimento', 'Compra', 'COMPRA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (140, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoRecebimento', 'Cessão', 'CESSAO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (141, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoRecebimento', 'Doação', 'DOACAO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (142, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoRecebimento', 'Permuta', 'PERMUTA', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (143, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoRecebimento', 'Transferência', 'TRANSFERENCIA', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (144, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoRecebimento', 'Produção interna', 'PRODUCAO_INTERNA', 6);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (177, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoFeriado', 'Data', 'DATA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (178, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoFeriado', 'Período', 'PERIODO', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (179, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'abrangenciaFeriado', 'Mundial', 'MUNDIAL', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (180, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'abrangenciaFeriado', 'Nacional', 'NACIONAL', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (181, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'abrangenciaFeriado', 'Estadual', 'ESTADUAL', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (182, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'abrangenciaFeriado', 'Municipal', 'MUNICIPAL', 4);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (183, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoExcecaoFeriado', 'Folga', 'FOLGA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (184, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoExcecaoFeriado', 'Trabalho', 'TRABALHO', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (188, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoWidget', 'HighChart', 'highchartcit', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (198, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoWidget', 'Link', 'link', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (209, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoWidget', 'Temperatura', 'temperatura', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (210, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoWidget', 'Notícia', 'noticia', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (211, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoWidget', 'Google Chart', 'googlechartcit', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (201, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoWidget', 'Google Chart Gauge', 'googlechartgaugecit', 6);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (269, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoWidget', 'Html', 'markdown', 7);



INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (193, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoHighChart', 'Pizza', 'pie', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (194, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoHighChart', 'Linha', 'line', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (195, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoHighChart', 'Barra', 'bar', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (196, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoHighChart', 'Coluna', 'column', 1);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (202, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoGoogleChart', 'AreaChart', 'AreaChart', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (203, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoGoogleChart', 'PieChart', 'PieChart', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (204, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoGoogleChart', 'ColumnChart', 'ColumnChart', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (205, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoGoogleChart', 'LineChart', 'LineChart', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (206, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoGoogleChart', 'Table', 'Table', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (207, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoGoogleChart', 'BarChart', 'BarChart', 1);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (190, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoIdioma', 'Português', 'pt-BR', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (191, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoIdioma', 'English', 'en_US', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (192, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoIdioma', 'Español', 'es_ES', 3);

--CONTRATOS
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (265, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoPerspectiva', 'Financeiro', 'FINANCEIRO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (266, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoPerspectiva', 'Operacional', 'OPERACIONAL', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (267, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoPerspectiva', 'Cliente', 'CLIENTE', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (268, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoPerspectiva', 'Recursos Humanos', 'RECURSOSHUMANOS', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (212, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAnalise', 'Planejamento Estratégico', 'PLANEJAMENTOESTRATEGICO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (213, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAnalise', 'Controladoria', 'CONTROLADORIA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (214, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAnalise', 'Qualidade', 'QUALIDADE', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (215, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAnalise', 'Legal', 'LEGAL', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (216, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAnalise', 'Contratual', 'CONTRATUAL', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (217, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAnalise', 'Documental', 'DOCUMENTAL', 6);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (218, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoColeta', 'Manual', 'MANUAL', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (219, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoColeta', 'Automática', 'AUTOMATICA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (220, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAtribuicao', 'Responsável pelo centro de resultado', 'RESPONSAVEL', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (221, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAtribuicao', 'Qualquer membro do grupo', 'GRUPO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (222, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoCondicao', 'Nenhuma', 'NENHUMA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (223, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoCondicao', 'Se anterior for executado', 'ANTERIOR_EXECUTADO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (224, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoCondicao', 'Após agendamento anterior', 'APOS_AGENDAMENTO_ANTERIOR', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (225, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoReiteracao', 'Até a execução', 'ATE_EXECUCAO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (226, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoReiteracao', 'Até o final do período', 'FINAL_PERIODO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (227, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoReiteracao', 'Quantidade definida', 'QUANTIDADE_DEFINIDA', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (228, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoDestino', 'Responsáveis', 'RESPONSAVEIS', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (229, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoDestino', 'Superiores', 'SUPERIORES', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (230, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoDisparo', 'No atraso', 'ATRASO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (231, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoDisparo', 'No criação', 'CRIACAO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (232, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoDisparo', 'No Execução', 'EXECUCAO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (233, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoRepeticao', 'Até a execução', 'EXECUCAO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (234, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoRepeticao', 'Nenhuma', 'NENHUMA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (235, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoRepeticao', 'Até o fim do agendamento', 'FIM_AGENDAMENTO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (236, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoIntercorrencia', 'Atividade não entregue', 'NAO_ENTREGUE', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (237, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoImplementacao', 'SQL', 'SQL', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (238, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoImplementacao', 'JavaScript', 'JAVASCRIPT', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (239, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoImplementacao', 'Classe Java', 'CLASSE_JAVA', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (240, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoPapelEquipeContratos', 'Gestor', 'GESTOR', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (241, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoPapelEquipeContratos', 'Gestor substituto', 'GESTOR_SUBSTITUTO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (242, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoPapelEquipeContratos', 'Fiscal técnico', 'FISCAL_TECNICO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (243, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoPapelEquipeContratos', 'Fiscal administrativo', 'FISCAL_ADMINISTRATIVO', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (244, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoPapelEquipeContratos', 'Fiscal solicitante', 'FISCAL_SOLICITANTE', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (245, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAquisicao', 'Serviço', 'SERVICO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (246, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAquisicao', 'Material', 'MATERIAL', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (247, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoAquisicao', 'Material e serviço', 'MATERIAL_SERVICO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (248, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoSituacaoContrato', 'Em execução', 'EM_EXECUCAO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (249, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoSituacaoContrato', 'Concluído', 'CONCLUIDO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (250, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoSituacaoContrato', 'Suspenso', 'SUSPENSO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (251, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoSituacaoContrato', 'Rescindido', 'RESCINDIDO', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (252, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoContrato', 'Administrativo', 'ADMINISTRATIVO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (253, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoContrato', 'Por Empenho', 'POR_EMPENHO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (254, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoContrato', 'Por Modalidade', 'POR_MODALIDADE', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (255, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoModalidade', 'Concorrência', 'CONCORRENCIA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (256, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoModalidade', 'Convite', 'CONVITE', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (257, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoModalidade', 'Tomada de Preço', 'TOMADA_PRECO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (258, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoModalidade', 'Concurso', 'CONCURSO', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (259, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoModalidade', 'Pregão', 'PREGAO', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (260, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoGarantia', 'Contratual', 'CONTRATUAL', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (261, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoGarantia', 'Fornecedor', 'FORNECEDOR', 2);


INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (262, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoModalidadeGarantia', 'Caução em dinheiro ou títulos da dívida pública', 'CAUCAO_DINHEIRO_TITULOS', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (263, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoModalidadeGarantia', 'Seguro-garantia', 'SEGURO_GARANTIA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (264, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoModalidadeGarantia', 'Fiança bancária', 'FIANCA_BANCARIA', 3);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (271, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoTempoAtualizacao', '1 minuto', 'UM', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (272, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoTempoAtualizacao', '5 minuto', 'CINCO', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (273, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoTempoAtualizacao', '10 minuto', 'DEZ', 10);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (274, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoTempoAtualizacao', '15 minuto', 'QUINZE', 15);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (275, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoTempoAtualizacao', '30 minuto', 'TRINTA', 30);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (276, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'tipoTempoAtualizacao', '1 Hora', 'UMA_HORA', 60);


INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (265, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoPerspectiva', 'Financeiro', 'FINANCEIRO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (266, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoPerspectiva', 'Operacional', 'OPERACIONAL', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (267, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoPerspectiva', 'Cliente', 'CLIENTE', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (268, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoPerspectiva', 'Recursos Humanos', 'RECURSOSHUMANOS', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (212, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAnalise', 'Planejamento Estratégico', 'PLANEJAMENTOESTRATEGICO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (213, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAnalise', 'Controladoria', 'CONTROLADORIA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (214, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAnalise', 'Qualidade', 'QUALIDADE', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (215, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAnalise', 'Legal', 'LEGAL', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (216, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAnalise', 'Contratual', 'CONTRATUAL', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (217, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAnalise', 'Documental', 'DOCUMENTAL', 6);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (218, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoColeta', 'Manual', 'MANUAL', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (219, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoColeta', 'Automática', 'AUTOMATICA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (220, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAtribuicao', 'Responsável pelo centro de resultado', 'RESPONSAVEL', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (221, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAtribuicao', 'Qualquer membro do grupo', 'GRUPO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (222, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoCondicao', 'Nenhuma', 'NENHUMA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (223, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoCondicao', 'Se anterior for executado', 'ANTERIOR_EXECUTADO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (224, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoCondicao', 'Após agendamento anterior', 'APOS_AGENDAMENTO_ANTERIOR', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (225, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoReiteracao', 'Até a execução', 'ATE_EXECUCAO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (226, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoReiteracao', 'Até o final do período', 'FINAL_PERIODO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (227, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoReiteracao', 'Quantidade definida', 'QUANTIDADE_DEFINIDA', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (228, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDestino', 'Responsáveis', 'RESPONSAVEIS', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (229, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDestino', 'Superiores', 'SUPERIORES', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (230, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDisparo', 'No atraso', 'ATRASO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (231, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDisparo', 'No criação', 'CRIACAO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (232, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDisparo', 'No Execução', 'EXECUCAO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (233, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoRepeticao', 'Até a execução', 'EXECUCAO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (234, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoRepeticao', 'Nenhuma', 'NENHUMA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (235, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoRepeticao', 'Até o fim do agendamento', 'FIM_AGENDAMENTO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (236, '03/04/2015 16:05:11', TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoIntercorrencia', 'Atividade não entregue', 'NAO_ENTREGUE', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (237, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoImplementacao', 'SQL', 'SQL', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (238, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoImplementacao', 'JavaScript', 'JAVASCRIPT', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (239, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoImplementacao', 'Classe Java', 'CLASSE_JAVA', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (240, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoPapelEquipeContratos', 'Gestor', 'GESTOR', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (241, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoPapelEquipeContratos', 'Gestor substituto', 'GESTOR_SUBSTITUTO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (242, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoPapelEquipeContratos', 'Fiscal técnico', 'FISCAL_TECNICO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (243, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoPapelEquipeContratos', 'Fiscal administrativo', 'FISCAL_ADMINISTRATIVO', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (244, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoPapelEquipeContratos', 'Fiscal solicitante', 'FISCAL_SOLICITANTE', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (245, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAquisicao', 'Serviço', 'SERVICO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (246, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAquisicao', 'Material', 'MATERIAL', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (247, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAquisicao', 'Material e serviço', 'MATERIAL_SERVICO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (248, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoSituacaoContrato', 'Em execução', 'EM_EXECUCAO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (249, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoSituacaoContrato', 'Concluído', 'CONCLUIDO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (250, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoSituacaoContrato', 'Suspenso', 'SUSPENSO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (251, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoSituacaoContrato', 'Rescindido', 'RESCINDIDO', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (252, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoContrato', 'Administrativo', 'ADMINISTRATIVO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (253, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoContrato', 'Por Empenho', 'POR_EMPENHO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (254, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoContrato', 'Por Modalidade', 'POR_MODALIDADE', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (255, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoModalidadeContrato', 'Concorrência', 'CONCORRENCIA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (256, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoModalidadeContrato', 'Convite', 'CONVITE', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (257, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoModalidadeContrato', 'Tomada de Preço', 'TOMADA_PRECO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (258, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoModalidadeContrato', 'Concurso', 'CONCURSO', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (259, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoModalidadeContrato', 'Pregão', 'PREGAO', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (260, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoGarantia', 'Contratual', 'CONTRATUAL', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (261, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoGarantia', 'Fornecedor', 'FORNECEDOR', 2);


INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (262, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoModalidadeGarantia', 'Caução em dinheiro ou títulos da dívida pública', 'CAUCAO_DINHEIRO_TITULOS', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (263, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoModalidadeGarantia', 'Seguro-garantia', 'SEGURO_GARANTIA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (264, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoModalidadeGarantia', 'Fiança bancária', 'FIANCA_BANCARIA', 3);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (282, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoRespostaQuestao', 'Texto livre', 'TEXTO_LIVRE', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (283, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoRespostaQuestao', 'Múltipla escolha', 'MULTIPLA_ESCOLHA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (284, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoRespostaQuestao', 'Booleana', 'BOOLEAN', 3);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (285, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoOcorrenciaContrato', 'Início contrato', 'TIPO_OCORRENCIA_INICIO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (286, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoOcorrenciaContrato', 'Fim contrato', 'TIPO_OCORRENCIA_FIM', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (287, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoOcorrenciaContrato', 'Apostilamento', 'TIPO_OCORRENCIA_APOSTILAMENTO_EXECUCAO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (288, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoOcorrenciaContrato', 'Termo aditivo', 'TIPO_OCORRENCIA_CADASTRO_TERMO_ADITIVO', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (289, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoOcorrenciaContrato', 'Acionamento garantia', 'TIPO_OCORRENCIA_ACIONAMENTO_GARANTIA', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (290, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoOcorrenciaContrato', 'Infração', 'TIPO_OCORRENCIA_INFRACAO', 6);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (291, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoOcorrenciaContrato', 'Penalização', 'TIPO_OCORRENCIA_PENALIZACAO', 7);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (292, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoOcorrenciaContrato', 'Rescisão', 'TIPO_OCORRENCIA_RESCISAO', 8);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (293, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoOcorrenciaContrato', 'Verificação realizada', 'TIPO_OCORRENCIA_VERIFICACAO', 9);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (294, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoOcorrenciaContrato', 'Liberação de pagamento', 'TIPO_OCORRENCIA_PAGAMENTO', 10);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (295, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoOcorrenciaContrato', 'Empenho', 'TIPO_OCORRENCIA_EMPENHO', 11);

SET IDENTITY_INSERT dominio OFF;
-- dominio id a partir de 277 esta liberado -- XD

-- USUARIO - SISTEMA
SET IDENTITY_INSERT seguranca_usuario ON;
INSERT INTO seguranca_usuario (id,datacriacao,dataedicao,datainativo,version,contabloqueada,contaexpirada,contahabilitada,credencialexpirada,email,password,passwordhint,username,website,autor_id,editor_id,inativador_id, semprenovaaba) VALUES (1,'03/04/2015 16:05:11','03/04/2015 16:05:11',NULL,0,0,0,1,0,'usuario@usuario.com','$2a$10$XuZOJQf5jOmNoyURh7lwlOD2RZap8wJdRpiNF4xKfxyRrQUX4uNcC','lembra','admin','',NULL,NULL,NULL, '1');
INSERT INTO seguranca_usuario (id,datacriacao,dataedicao,version,contabloqueada,contaexpirada,contahabilitada,credencialexpirada,email,password,passwordhint,username,website,semprenovaaba) VALUES (2,'03/04/2015 16:05:11','03/04/2015 16:05:11',0,0,0,1,0,'consultor@consultor.com','$2a$10$XuZOJQf5jOmNoyURh7lwlOD2RZap8wJdRpiNF4xKfxyRrQUX4uNcC','consultor','consultor','','1');
SET IDENTITY_INSERT seguranca_usuario OFF;

-- CEIA PRIVILEGIOS DE ACESSO
SET IDENTITY_INSERT seguranca_privilegio ON;
INSERT INTO seguranca_privilegio (id, version, nome, descricao,datacriacao,dataedicao) VALUES(1 ,0, 'ROLE_ADMIN', 'ROLE_ADMIN','03/04/2015 16:05:11','03/04/2015 16:05:11');
INSERT INTO seguranca_privilegio (id, version, nome, descricao,datacriacao,dataedicao) VALUES(2 ,0, 'ROLE_USER', 'ROLE_USER','03/04/2015 16:05:11','03/04/2015 16:05:11');
SET IDENTITY_INSERT seguranca_privilegio OFF;

-- ATRIBUI OS PRIVILEGIOS AO USUARIO
SET IDENTITY_INSERT seguranca_usuario_privilegio ON;
INSERT INTO seguranca_usuario_privilegio(id ,version, usuario_id, privilegio_id ,datacriacao,dataedicao)VALUES(1 ,0, 1, 1,'03/04/2015 16:05:11','03/04/2015 16:05:11');
INSERT INTO seguranca_usuario_privilegio(id ,version, usuario_id, privilegio_id ,datacriacao,dataedicao)VALUES(2 ,0, 1, 2,'03/04/2015 16:05:11','03/04/2015 16:05:11');
INSERT INTO seguranca_usuario_privilegio(id ,version, usuario_id, privilegio_id ,datacriacao,dataedicao)VALUES(3 ,0, 2, 2,'03/04/2015 16:05:11','03/04/2015 16:05:11');
SET IDENTITY_INSERT seguranca_usuario_privilegio OFF;

-- ORGAOS OU ESTRUTURAS - CORE
SET IDENTITY_INSERT estruturaorgao ON;
INSERT INTO estruturaorgao(id, datacriacao, dataedicao, version, autor_id, isorgao, nome, sigla, dataReferenciaVigente)
	VALUES (1, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 1, 'Órgão Teste 1', 'Órgão 1', '2015-01-01 09:26:25');
INSERT INTO estruturaorgao(id, datacriacao, dataedicao, version, autor_id, isOrgao, nome, sigla, dataReferenciaVigente)
	VALUES (2, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 1, 'Órgão Teste 2', 'Órgão 2', '2015-01-01 09:26:25');
INSERT INTO estruturaorgao(id, datacriacao, dataedicao, version, autor_id, isOrgao, nome, sigla)
	VALUES (3, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 0, 'Estrutura Organizacional Teste 1.1 Almoxarifado', 'EOT1.1');
INSERT INTO estruturaorgao(id, datacriacao, dataedicao, version, autor_id, isOrgao, nome, sigla)
	VALUES (4, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 0, 'Estrutura Organizacional Teste 1.2', 'EOT1.2');
INSERT INTO estruturaorgao(id, datacriacao, dataedicao, version, autor_id, isOrgao, nome, sigla)
	VALUES (5, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 0, 'Estrutura Organizacional Teste 2.1', 'EOT2.1');
SET IDENTITY_INSERT estruturaorgao OFF;

-- COLOCA ORGAO NO USUARIO
UPDATE seguranca_usuario SET orgao_id=1 WHERE id = 1;
UPDATE seguranca_usuario SET orgao_id=1 WHERE id = 2;

SET IDENTITY_INSERT orgaoitem ON;
INSERT INTO orgaoitem(id, datacriacao, dataedicao, version, orgao_id, usuario_id) VALUES (1, '03/04/2015 16:05:11','03/04/2015 16:05:11', 1, 1, 1);
INSERT INTO orgaoitem(id, datacriacao, dataedicao, version, orgao_id, usuario_id) VALUES (2, '03/04/2015 16:05:11','03/04/2015 16:05:11', 1, 2, 1);
INSERT INTO orgaoitem(id, datacriacao, dataedicao, version, orgao_id, usuario_id) VALUES (3, '03/04/2015 16:05:11','03/04/2015 16:05:11', 1, 1, 2);
SET IDENTITY_INSERT orgaoitem OFF;

-- ENDERECO
SET IDENTITY_INSERT endereco ON;
INSERT INTO endereco(id, datacriacao, dataedicao, datainativo, version, cep, codigo, complemento, logradouro, nome, numero, bairro_id, cidade_id, dominiotipoendereco_id, pessoa_id, latitude, longitude)
    VALUES (1, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, '74043000', 'COD', 'COMPLEMENTO TESTE', 'LOG. TESTE', 'NOME TESTE', '0000', 1, 1, 7, NULL, 12, 14);
SET IDENTITY_INSERT endereco OFF;

-- PAGINA
SET IDENTITY_INSERT pagina ON;
INSERT INTO pagina (id, nome, pagina) VALUES (1, 'Home', '/cit-portal-web/html/home/home.html');
INSERT INTO pagina (id, nome, pagina) VALUES (2, 'Menu', '/cit-portal-web/html/menu/menu.html');
-- INSERT INTO pagina (id, nome, pagina) VALUES (3, 'Default template', '/cit-portal-web/html/defaultTemplate/defaultTemplate.html');
INSERT INTO pagina (id, nome, pagina) VALUES (4, 'Usuário', '/cit-portal-web/html/usuario/usuario.html');
INSERT INTO pagina (id, nome, pagina) VALUES (5, 'Notificação','/cit-portal-web/html/notificacao/notificacao.html');
INSERT INTO pagina (id, nome, pagina) VALUES (8, 'Arquivos padrão', '/cit-portal-web/html/defaultFile/defaultFile.html');
-- INSERT INTO pagina (id, nome, pagina) VALUES (9, 'Órgão', '/citgrp-patrimonio-web/html/orgao/orgao.html');
INSERT INTO pagina (id, nome, pagina) VALUES (10, 'Domínio', '/cit-portal-web/html/dominio/dominio.html');
INSERT INTO pagina (id, nome, pagina) VALUES (17, 'Mapa', '/cit-portal-web/html/mapa/mapa.html');
INSERT INTO pagina (id, nome, pagina) VALUES (19, 'Pessoa', '/citgrp-patrimonio-web/html/pessoa/pessoa.html');
INSERT INTO pagina (id, nome, pagina) VALUES (20, 'Localização', '/citgrp-patrimonio-web/html/localizacao/localizacao.html');
INSERT INTO pagina (id, nome, pagina) VALUES (21, 'Adição a bem principal', '/citgrp-patrimonio-web/html/adicaoBemPrincipal/adicaoBemPrincipal.html');
-- INSERT INTO pagina (id, nome, pagina) VALUES (26, 'Comissão de inventário', '/citgrp-patrimonio-web/html/inventarioComissao/inventarioComissao.html');
--INSERT INTO pagina (id, nome, pagina) VALUES (36, 'Access roles', '/cit-portal-web/html/accessRole/accessRole.html');
INSERT INTO pagina (id, nome, pagina) VALUES (38, 'Configuração do Sistema', '/cit-portal-web/html/configuracaoSistema/configuracaoSistema.html');
INSERT INTO pagina (id, nome, pagina) VALUES (39, 'Grupo', '/cit-portal-web/html/grupo/grupo.html');
INSERT INTO pagina (id, nome, pagina) VALUES (40, 'Painel', '/cit-portal-web/html/painel/painel.html');
INSERT INTO pagina (id, nome, pagina) VALUES (41, 'Widget', '/cit-portal-web/html/widget/widget.html');
INSERT INTO pagina (id, nome, pagina) VALUES (42, 'Widget Item', '/cit-portal-web/html/widgetItem/widgetItem.html');

INSERT INTO pagina (id, nome, pagina) VALUES (43, 'Módulos', '/cit-portal-web/html/modulo/modulo.html');
INSERT INTO pagina (id, nome, pagina) VALUES (44, 'DashBoard', '/cit-portal-web/html/dashboard-portal.html');

INSERT INTO pagina (id, nome, pagina) VALUES (45, 'Internacionalização', '/cit-portal-web/html/internacionalizacao/internacionalizacao.html');

-- CONTRATO
INSERT INTO pagina (id, nome, pagina) VALUES (46, 'Regra Evento', '/cit-contratos-web/html/regraEvento/regraEvento.html');
INSERT INTO pagina (id, nome, pagina) VALUES (47, 'Monitor', '/cit-contratos-web/html/monitor/monitor.html');

INSERT INTO pagina (id, nome, pagina) VALUES (49, 'Penalidade', '/cit-contratos-web/html/penalidade/penalidade.html');
INSERT INTO pagina (id, nome, pagina) VALUES (50, 'Contrato', '/cit-contratos-web/html/contrato/contrato.html');
INSERT INTO pagina (id, nome, pagina) VALUES (51, 'Visão Contrato', '/cit-contratos-web/html/contratoOcorrencia/contratoOcorrencia.html');
INSERT INTO pagina (id, nome, pagina) VALUES (53, 'Questionário', '/cit-contratos-web/html/questionario/questionario.html');
INSERT INTO pagina (id, nome, pagina) VALUES (54, 'Relatórios', '/cit-contratos-web/html/relatorios/relatorios.html');

-- FIM CONTRATO

INSERT INTO pagina (id, nome, pagina) VALUES (100 , 'Manutenção de Fluxos', '/cit-esi-web/html/fluxo/fluxo.html');
INSERT INTO pagina (id, nome, pagina) VALUES (103, 'Jornada de Trabalho', '/cit-esi-web/html/workDay/workDay.html');
INSERT INTO pagina (id, nome, pagina) VALUES (104, 'Calendário de Trabalho', '/cit-esi-web/html/workCalendar/workCalendar.html');

INSERT INTO pagina (id, nome, pagina) VALUES (101, 'Gerenciamento de tarefas', '/cit-esi-web/html/businessProcess/businessTaskManagement.html');
INSERT INTO pagina (id, nome, pagina) VALUES (105 , 'Manutenção de Categorias', '/cit-esi-web/html/businessProcess/businessProcessCategory.html');
INSERT INTO pagina (id, nome, pagina) VALUES (106 , 'Manutenção de Processos', '/cit-esi-web/html/businessProcess/businessProcess.html');

SET IDENTITY_INSERT pagina OFF;

-- liberador a partir do 105

-- MENUS
SET IDENTITY_INSERT menu ON;
INSERT INTO menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id)
    VALUES
 (1, 'Dashboard', null, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 0, 1, 44);

INSERT INTO menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, cor, classePagina, cssMenu, cssMenuOpacity)
    VALUES
  (2, 'Administração', null, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, '#62a0d2', 'mod-blue', '.mod-blue .bar-buttons-action:before {	background: rgb(255,255,255);	background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(98,160,210,0.5) 100%);	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(98,160,210,0.5)));	background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr="#ffffff", endColorstr="#62a0d2",GradientType=0 );}.modal.modal-buttons-top.mod-blue .modal-dialog .modal-header {	background: rgb(255,255,255);	background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(98,160,210,0.5) 100%);	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(98,160,210,0.5)));	background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr="#ffffff", endColorstr="#62a0d2",GradientType=0 );}#workspace-nav li.mod-blue a {	border-top-color: #62a0d2;}', 5);

--INSERT INTO menu
 -- (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, cor)
  --  VALUES
  --(4, 'Sistemas interno', null, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 3, 1, '#b7b87d');


INSERT INTO menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
    VALUES
  (5, 'Menu', 2, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 0, 1, 2, 'mod-blue', '#62a0d2');
-- INSERT INTO menu
  -- (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
    -- VALUES
  -- (6, 'Template padrão', 2, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 3, 'mod-blue', '#62a0d2');
INSERT INTO menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
    VALUES
  (7, 'Usuário', 2, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 2, 1, 4, 'mod-blue', '#62a0d2');
INSERT INTO menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
    VALUES
  (8, 'Notificações', 2, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 3, 1, 5, 'mod-blue', '#62a0d2');
INSERT INTO menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
    VALUES
  (11, 'Arquivos padrão', 2, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 4, 1, 8, 'mod-blue', '#62a0d2');
INSERT INTO menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
    VALUES
  (13, 'Domínio', 2, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 5, 1, 10, 'mod-blue', '#62a0d2');

INSERT INTO menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
    VALUES
  (46, 'Módulos', 2, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 6, 1, 43, 'mod-blue', '#62a0d2');

INSERT INTO menu (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
	VALUES (47, 'Internacionalizacao', 2, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 7, 1, 45, 'mod-blue', '#62a0d2');

INSERT INTO menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
    VALUES
  (41, 'Configuração do Sistema', 2, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 6, 1, 38, 'mod-blue', '#62a0d2');

INSERT INTO menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
    VALUES
  (42, 'Grupo', 2, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 6, 1, 39, 'mod-blue', '#62a0d2');

INSERT INTO menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (43, 'Painel', 2, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 25, 1, 40, 'mod-green', '#88b67f');

INSERT INTO menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (44, 'Widget', 2, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 26, 1, 41, 'mod-green', '#88b67f');

INSERT INTO menu
	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classepagina, cor, cssMenu, cssMenuOpacity)
		VALUES
	(54, 'BPE/ESI', null, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 4, 1, null, 'mod-red-dark', '#8B0000', '.mod-red-dark .bar-buttons-action:before {	background: rgb(255,255,255);	background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(139,0,0,0.3) 100%);	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(139,0,0,0.3)));	background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(139,0,0,0.3) 100%);	background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(139,0,0,0.3) 100%);	background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(139,0,0,0.3) 100%);	background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(139,0,0,0.3) 100%);	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr="#ffffff", endColorstr="#8B0000",GradientType=0 );}.modal.modal-buttons-top.mod-red-dark .modal-dialog .modal-header {	background: rgb(255,255,255);	background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(139,0,0,0.3) 100%);	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(139,0,0,0.3)));	background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(139,0,0,0.3) 100%);	background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(139,0,0,0.3) 100%);	background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(139,0,0,0.3) 100%);	background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(139,0,0,0.3) 100%);	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr="#ffffff", endColorstr="#8B0000",GradientType=0 );}#workspace-nav li.mod-red-dark a {	border-top-color: #8B0000;}', 3);
INSERT INTO menu
	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classepagina, cor)
		VALUES
	(55, 'Manutenção de fluxos', 54, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 0, 1, 100, 'mod-red-dark', '#8B0000');

INSERT INTO menu
	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id)
		VALUES
	(58, 'Jornada de Trabalho', 2, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 998, 999, 103);
INSERT INTO menu
	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id)
		VALUES
	(59, 'Calendário', 2, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 999, 999, 104);,

-- CONTRATO
INSERT INTO public.menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, cor, classePagina, cssMenu, cssMenuOpacity, ativo)
    VALUES
  (48, 'Contratos', null, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 3, 1, '#00008B', 'mod-blue-dark', '.mod-blue-dark .bar-buttons-action:before {	background: rgb(255,255,255);	background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(0,0,139,0.3) 100%);	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(0,0,139,0.3)));	background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr="#ffffff", endColorstr="#00008B",GradientType=0 );}.modal.modal-buttons-top.mod-blue-dark .modal-dialog .modal-header {	background: rgb(255,255,255);	background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(0,0,139,0.3) 100%);	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(0,0,139,0.3)));	background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr="#ffffff", endColorstr="#00008B",GradientType=0 );}#workspace-nav li.mod-blue-dark a {	border-top-color: #00008B;}', 3, 1);

INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor, ativo) VALUES
  (49, 'Regra Evento', 48, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 0, 1, 46, 'mod-blue-dark', '#00008B', 1);

INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor, ativo) VALUES
  (50, 'Monitor', 48, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 0, 1, 47, 'mod-blue-dark', '#00008B', 1);

INSERT INTO public.menu (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor, ativo)
VALUES (52, 'Penalidade', 48, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 7, 1, 49, 'mod-blue-dark', '#00008B', 1);

INSERT INTO public.menu (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor, ativo)
VALUES (53, 'Contrato', 48, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 7, 1, 50, 'mod-blue-dark', '#00008B', 1);

INSERT INTO public.menu (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor, ativo)
VALUES (60, 'Visão Contrato', 48, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 7, 1, 51, 'mod-blue-dark', '#00008B', 1);
-- FIM CONBTRATO


INSERT INTO menu
	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classepagina, cor)
		VALUES
	(61, 'Processos de Negócio', 54, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 9999, 1, null, 'mod-red-dark', '#8B0000');
INSERT INTO menu
	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classepagina, cor)
		VALUES
	(62, 'Manutenção de Categorias', 61, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 0, 1, 105, 'mod-red-dark', '#8B0000');
INSERT INTO menu
	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classepagina, cor)
		VALUES
	(63, 'Manutenção de Processos', 61, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 106, 'mod-red-dark', '#8B0000');
INSERT INTO menu
	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classepagina, cor)
		VALUES
	(56, 'Gerenciamento de tarefas', 61, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 2, 1, 101, 'mod-red-dark', '#8B0000');

SET IDENTITY_INSERT menu OFF;
-- liberador a partir do 61

update menu set ativo = 1;

-- FILES DO MENU
 INSERT INTO menufile(
       datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
      'assets/js/angular/controller/DashboardController.js', 2, 1);
INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
      'assets/js/angular/controller/MenuController.js', 2, 5);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
      'assets/js/angular/repository/MenuRepository.js', 2, 5);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
      'assets/js/angular/controller/UsuarioController.js', 2, 7);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
      'assets/js/angular/controller/UsuarioListController.js', 2, 7);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
      'assets/js/angular/repository/UsuarioRepository.js', 2, 7);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
      'assets/js/angular/repository/EstruturaOrgaoRepository.js', 2, 7);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
      'assets/js/angular/repository/OrgaoItemRepository.js', 2, 7);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
      'assets/js/angular/repository/FiltroRepository.js', 2, 7);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
      'assets/js/angular/repository/FavoritoRepository.js', 2, 7);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
      'assets/js/angular/repository/PaginaUsuarioRepository.js', 2, 7);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
      'assets/js/angular/controller/DefaultFileController.js', 2, 11);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
      'assets/js/angular/repository/DefaultFileRepository.js', 2, 11);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
      'assets/js/angular/controller/NotificacaoController.js', 2, 8);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
      'assets/js/angular/controller/NotificacaoListController.js', 2, 8);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
      'assets/js/angular/repository/NotificacaoRepository.js', 2, 8);

-- INSERT INTO menufile(
--            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
--    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
--      '/citgrp-patrimonio-web/angular/controller/OrgaoController.js', 2, 12);

-- INSERT INTO menufile(
--            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
--    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
--      '/citgrp-patrimonio-web/angular/controller/OrgaoListController.js', 2, 12);

-- INSERT INTO menufile(
--            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
--    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
--      '/citgrp-patrimonio-web/angular/repository/OrgaoRepository.js', 2, 12);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
      'assets/js/angular/controller/DominioController.js', 2, 13);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
      'assets/js/angular/controller/DominioListController.js', 2, 13);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
      'assets/js/angular/repository/DominioRepository.js', 2, 13);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
        'assets/js/angular/repository/EnderecoRepository.js', 2, 28);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    'assets/js/angular/repository/AccessRoleRepository.js', 2, 38);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    'assets/js/angular/controller/AccessRoleController.js', 2, 38);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    'assets/js/angular/controller/AccessRoleListController.js', 2, 38);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    'assets/js/angular/controller/ConfiguracaoSistemaController.js', 2, 41);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    'assets/js/angular/repository/ConfiguracaoSistemaRepository.js', 2, 41);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
'assets/js/angular/controller/GrupoController.js', 2, 42);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
'assets/js/angular/controller/GrupoListController.js', 2, 42);

 INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
'assets/js/angular/repository/GrupoRepository.js', 2, 42);

 INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
'assets/js/angular/repository/PrivilegioRepository.js', 2, 42);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
'assets/js/angular/controller/PainelListController.js', 2, 43);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
'assets/js/angular/controller/PainelController.js', 2, 43);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
'assets/js/angular/repository/PainelRepository.js', 2, 43);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
'assets/js/angular/controller/WidgetListController.js', 2, 44);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
'assets/js/angular/controller/WidgetController.js', 2, 44);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
'assets/js/angular/repository/WidgetRepository.js', 2, 44);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
'assets/js/angular/repository/WidgetParametroRepository.js', 2, 44);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
'assets/js/angular/controller/WidgetItemListController.js', 2, 45);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
'assets/js/angular/controller/WidgetItemController.js', 2, 45);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
'assets/js/angular/repository/WidgetItemRepository.js', 2, 45);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
      'assets/js/angular/controller/ModuloController.js', 2, 46);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
      'assets/js/angular/controller/ModuloListController.js', 2, 46);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
      'assets/js/angular/repository/ModuloRepository.js', 2, 46);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
	VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 'assets/js/angular/controller/InternacionalizacaoController.js', 2, 47);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
	VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 'assets/js/angular/controller/InternacionalizacaoListController.js', 2, 47);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
	VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 'assets/js/angular/repository/InternacionalizacaoRepository.js', 2, 47);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 103,
	    '/cit-esi-web/angular/controller/WorkDayController.js', 2, 58);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 103,
	    '/cit-esi-web/angular/controller/WorkDayListController.js', 2, 58);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 103,
	    '/cit-esi-web/angular/repository/WorkDayRepository.js', 2, 58);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 104,
	    '/cit-esi-web/angular/controller/WorkCalendarController.js', 2, 59);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 104,
	    '/cit-esi-web/angular/controller/WorkCalendarListController.js', 2, 59);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 104,
	    '/cit-esi-web/angular/repository/WorkCalendarRepository.js', 2, 59);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/controller/FluxoController.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/controller/FluxoListController.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/controller/BusinessTaskController.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/utils/DesenhoFluxo.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/utils/RuntimeEnvironmentInput.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/utils/RuntimeEnvironmentOutput.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/utils/RuntimeManagerUtils.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/controller/DomainController.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/repository/DomainRepository.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/repository/GenericRepository.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/repository/FlowRepository.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/repository/RuntimeManagerRepository.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/repository/FlowVariableRepository.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/repository/FlowStatusRepository.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/repository/FlowElementVariableRepository.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/directive/ExecucaoFluxoDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/directive/StartBusinessProcessDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/directive/VisualizacaoTarefaFluxoDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/directive/ExecucaoTarefaDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/repository/FlowElementActorRepository.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/directive/FormElementoFluxoDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/directive/ImgElementoFluxoDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/directive/InterfaceUsuarioDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/directive/ExpressaoDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/directive/ExpressaoElementoDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/directive/SituacaoElementoFluxoDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/directive/VariavelElementoFluxoDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/directive/AtorElementoFluxoDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	    '/cit-esi-web/angular/filter/DomainFilter.js', 2, 54);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 104,
	    '/cit-esi-web/angular/controller/BusinessProcessCategoryController.js', 2, 61);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 104,
	    '/cit-esi-web/angular/repository/BusinessProcessCategoryRepository.js', 2, 61);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 105,
	    '/cit-esi-web/angular/controller/BusinessProcessListController.js', 2, 61);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 105,
	    '/cit-esi-web/angular/controller/BusinessProcessController.js', 2, 61);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 105,
	    '/cit-esi-web/angular/repository/BusinessProcessRepository.js', 2, 61);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 104,
	    '/cit-esi-web/angular/repository/SecurityRepository.js', 2, 61);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    'assets/js/angular/repository/AccessRoleRepository.js', 2, 38);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    'assets/js/angular/controller/AccessRoleController.js', 2, 38);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    'assets/js/angular/controller/AccessRoleListController.js', 2, 38);

-- CONTRATO
INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    '/cit-contratos-web/angular/controller/RegraEventoListController.js', 2, 47, 1);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    '/cit-contratos-web/angular/controller/RegraEventoController.js', 2, 47, 1);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    '/cit-contratos-web/angular/repository/RegraEventoRepository.js', 2, 47, 1);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    '/cit-contratos-web/angular/controller/MonitorController.js', 2, 50, 1);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    '/cit-contratos-web/angular/controller/MonitorListController.js', 2, 50, 1);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    '/cit-contratos-web/angular/repository/MonitorRepository.js', 2, 50, 1);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    '/cit-contratos-web/angular/repository/QuestionarioRepository.js', 2, 50, 1);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    '/cit-contratos-web/angular/repository/QuestionarioPerguntaRepository.js', 2, 50, 1);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    '/cit-contratos-web/angular/repository/PossivelRespostaQuestionarioPerguntaRepository.js', 2, 50, 1);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    '/cit-contratos-web/angular/controller/ContratosFilterController.js', 2, 50, 1);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    '/cit-contratos-web/angular/repository/MonitorPredecessoraRepository.js', 2, 50, 1);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    '/cit-contratos-web/angular/repository/MonitorResponsavelRepository.js', 2, 50, 1);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    '/cit-contratos-web/angular/repository/FluxoTrabalhoRepository.js', 2, 50, 1);

  INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    '/cit-contratos-web/angular/repository/ProcessoNegocioRepository.js', 2, 50, 1);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    '/cit-contratos-web/angular/repository/NivelAutoridadeRepository.js', 2, 50, 1);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    '/cit-contratos-web/angular/repository/ModeloEmailRepository.js', 2, 50, 1);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    '/cit-contratos-web/angular/repository/MonitorAcaoDecorrenteRepository.js', 2, 50, 1);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    '/cit-contratos-web/angular/repository/MonitorAgendamentoRepository.js', 2, 50, 1);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
    '/cit-contratos-web/angular/repository/MonitorNotificacaoRepository.js', 2, 50, 1);

INSERT INTO menufile(
	  datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
  VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1,
	'/cit-contratos-web/angular/repository/ContratoApostilamentoRepository.js', 2, 53, 1);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/controller/PenalidadeController.js', 2, 52, 1);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/controller/PenalidadeListController.js', 2, 52, 1);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/repository/PenalidadeRepository.js', 2, 52, 1);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/controller/ContratoController.js', 2, 54, 1);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/controller/ContratoListController.js', 2, 53, 1);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/repository/ContratoRepository.js', 2, 53, 1);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/controller/ContratoEquipeGestoraController.js', 2, 53, 1);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/repository/ContratoEquipeGestoraRepository.js', 2, 53, 1);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/repository/ContratoAnexoRepository.js', 2, 53, 1);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/controller/ContratoTermoAditivoController.js', 2, 53, 1);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/repository/ContratoTermoAditivoRepository.js', 2, 53, 1);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/repository/GarantiaRepository.js', 2, 53, 1);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/controller/QuestionarioController.js', 2, 64, 1);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/controller/QuestionarioListController.js', 2, 64, 1);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/repository/QuestionarioRepository.js', 2, 64, 1);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/controller/VisaoContratoController.js', 2, 60, 1);



INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/repository/GarantiaAdiantamentoRepository.js', 2, 60, 1);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/repository/ContratoInfracaoRepository.js', 2, 60, 1);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/repository/ContratoRescisaoRepository.js', 2, 60, 1);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/repository/ContratoOcorrenciaRepository.js', 2, 60, 1);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/repository/CadastroInfracaoPenalidadeRepository.js', 2, 60, 1);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/repository/ContratoExecucaoQuestionarioRespostaRepository.js', 2, 60, 1);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/repository/ContratoExecucaoQuestionarioRepository.js', 2, 60, 1);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/repository/ContratoEmpenhoRepository.js', 2, 60, 1);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id, ativo)
VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, '/cit-contratos-web/angular/repository/ContratoLiberacaoPagamentoRepository.js', 2, 60, 1);

-- FIM CONTRATO


-- DEFAULT FILES
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/css/bootstrap.min.css', 1, 0);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/css/select.min.css', 1, 1);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/css/select2.min.css', 1, 2);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/css/angular-growl.min.css', 1, 3);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/css/font-awesome.min.css', 1, 4);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/css/angular-ui-tree.min.css', 1, 5);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/css/textAngular.css', 1, 6);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/css/bootstrap-layout.css', 1, 7);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/css/layout.css', 1, 8);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/jquery.min.js', 2, 18);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/functions.js', 2, 19);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/bigInteger.js', 2, 20);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/1.3.0/angular.min.js', 2, 21);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/1.3.0/i18n/angular-locale_pt-br.js', 2, 22);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/1.3.0/angular-cookies.min.js', 2, 23);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/1.3.0/angular-route.min.js', 2, 24);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/1.3.0/angular-sanitize.min.js', 2, 25);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/1.3.0/angular-animate.min.js', 2, 26);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/translate/angular-translate.js', 2, 27);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/translate/angular-translate-storage-cookie.js', 2, 28);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/translate/angular-translate-loader-partial.min.js', 2, 29);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/lodash.min.js', 2, 30);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/restangular.min.js', 2, 31);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/ui-bootstrap-tpls-0.12.0-custom.js', 2, 32);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/select.min.js', 2, 33);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/angular-growl.min.js', 2, 34);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/angular-scroll.min.js', 2, 35);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/ui-utils.js', 2, 36);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/angular-lazy-tree.js', 2, 37);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/angular-google-maps.min.js', 2, 38);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/textAngular-rangy.min.js', 2, 39);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/textAngular-sanitize.min.js', 2, 40);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/textAngular.min.js', 2, 41);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/angular-ui-tree.min.js', 2, 42);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/MasksDirective.js', 2, 43);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/utils/ArrayUtil.js', 2, 44);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/loading.js', 2, 45);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/menu.js', 2, 46);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/page-unload.js', 2, 47);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/modernizr-2.8.3.min.js', 2, 48);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular-file-upload.js', 2, 48);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/jquery-ui/core.min.js', 2, 49);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/jquery-ui/widget.min.js', 2, 50);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/jquery-ui/mouse.min.js', 2, 51);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/jquery-ui/sortable.min.js', 2, 52);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/angular-ui-sortable/sortable.min.js', 2, 53);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/adf.js', 2, 54);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/provider.js', 2, 55);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/widget-content.js', 2, 56);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/widget.js', 2, 57);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/dashboard.js', 2, 58);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/column.js', 2, 59);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/row.js', 2, 60);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/structures.js', 2, 61);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/widgets-sample/news/news.js', 2, 61);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/widgets-sample/weather/weather.js', 2, 62);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/widgets-sample/linklist/linklist.js', 2, 63);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/widgets-sample/components/showdown/showdown.js', 2, 64);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/widgets-sample/components/angular-markdown-directive/markdown.js', 2, 65);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/widgets-sample/markdown/markdown.js', 2, 66);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/widgets-sample/components/highcharts/highcharts.js', 2, 68);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/widgets-sample/components/highcharts-ng/dist/highcharts-ng.js', 2, 69);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/widgets-sample/components/angular-google-chart/ng-google-chart.js', 2, 69);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/widgets/charts/highchart/highchartcit.js', 2, 71);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/widgets/charts/google-chart/googlechart.js', 2, 72);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/dashboard-framework/widgets/charts/google-chart-gauge/googlechartgauge.js', 2, 72);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/app.js', 2, 74);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/controller/AppController.js', 2, 75);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/repository/AbstractRepository.js', 2, 76);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/repository/PaginaUsuarioRepository.js', 2, 77);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/repository/UsuarioRepository.js', 2, 78);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/repository/UsuarioPatrimonioRepository.js', 2, 79);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/repository/NotificacaoRepository.js', 2, 80);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/repository/ParceiroRepository.js', 2, 81);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/repository/EnderecoRepository.js', 2, 82);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/controller/DefaultTemplateController.js', 2, 83);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/controller/DefaultTemplateListController.js', 2, 84);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/filter/filter.js', 2, 85);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/cpf-validator.js', 2, 87);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/cnpj-validator.js', 2, 88);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/passwordCheckDirective.js', 2, 89);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/onBlurChangeDirective.js', 2, 90);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/MoneyDirective.js', 2, 91);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/IntegerDirective.js', 2, 92);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/onEnterBlurDirective.js', 2, 93);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/DateDirective.js', 2, 94);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/SortByDirective.js', 2, 95);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/FiltroDirective.js', 2, 96);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/DialogDirective.js', 2, 97);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/FavoritoDirective.js', 2, 98);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/LabelInputDirective.js', 2, 99);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/LabelInputCheckboxDirective.js', 2, 100);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/LabelSelectDirective.js', 2, 101);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/LabelInputNumberDirective.js', 2, 102);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/LabelInputDataDirective.js', 2, 103);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/LabelInputMoneyDirective.js', 2, 104);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/LabelInputRadioDirective.js', 2, 105);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/TelefoneDirective.js', 2, 106);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/ObservacaoDirective.js', 2, 107);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/EnderecoDirective.js', 2, 108);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/AutoCompleteDirective.js', 2, 109);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/AutoCompleteObrigatorioDirective.js', 2, 110);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/LabelTextAreaDirective.js', 2, 111);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/EnderecoUnicoDirective.js', 2, 112);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/TooltipDirective.js', 2, 113);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/ChosenSelectDirective.js', 2, 114);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/DropdownDirective.js', 2, 115);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/PercentDirective.js', 2, 116);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/LabelInputPercentDirective.js', 2, 117);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/CpfCnpjDirective.js', 2, 118);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/ButtonLockDirective.js', 2, 119);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/HelpButtonDirective.js', 2, 120);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/PickListDirective.js', 2, 121);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/IdentifierDirective.js', 2, 122);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/LabelInputIdentifierDirective.js', 2, 123);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/LabelColorSelectDirective.js', 2, 124);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/ListaStringDirective.js', 2, 125);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/OneWayDirective.js', 2, 126);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/BloquearDesbloquearDirective.js', 2, 127);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular-br-filters.js', 2, 128);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/ListViewDirective.js', 2, 129);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/uds_api_contents.js', 2, 130);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/jsapi.js', 2, 131);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, 'assets/js/angular/directive/RegexValidate.js', 2, 134);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, '/cit-contratos-web/assets/css/module-custom.css', 1, 132);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, 1, '/cit-contratos-web/assets/js/timeline-horizontal.js', 2, 133);

-- ACCESS ROLES
SET IDENTITY_INSERT accessrole ON;
INSERT INTO accessrole(id, datacriacao, dataedicao, datainativo, version, url, roles) VALUES (1, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, '/html/**', 'ROLE_ADMIN');
INSERT INTO accessrole(id, datacriacao, dataedicao, datainativo, version, url, roles) VALUES (2, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, '/admin/**', 'ROLE_ADMIN,ROLE_MANAGER');
SET IDENTITY_INSERT accessrole OFF;

-- JORNADA TRABALHO - carlos.santos em 10.02.2015
SET IDENTITY_INSERT workday ON;
INSERT INTO workday(id, datacriacao, dataedicao, datainativo, version, description)
  VALUES (1, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'Jornada padrão');

INSERT INTO workday(id, datacriacao, dataedicao, datainativo, version, description)
  VALUES (2, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0, 'Jornada horas corridas');
SET IDENTITY_INSERT workday OFF;

-- HORARIO JORNADA TRABALHO - carlos.santos em 10.02.2015
SET IDENTITY_INSERT worktime ON;
INSERT INTO worktime(id, starttime, endtime, workday_id)
  VALUES (1, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 1);

INSERT INTO worktime(id, starttime, endtime, workday_id)
  VALUES (2, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 1);

INSERT INTO worktime(id, starttime, endtime, workday_id)
  VALUES (3, '03/04/2015 16:05:11', '03/04/2015 16:05:11', 2);
SET IDENTITY_INSERT worktime OFF;

-- CALENDARIO - carlos.santos em 10.02.2015
SET IDENTITY_INSERT workcalendar ON;
INSERT INTO workcalendar(id, datacriacao, dataedicao, datainativo, version,
  considerholiday, description, workdaymon_id,  workdaytue_id,  workdaywed_id,  workdaythu_id,  workdayfri_id)
  VALUES (1, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0,
  1, 'Calendário padrão', 1, 1, 1, 1, 1);

INSERT INTO workcalendar(id, datacriacao, dataedicao, datainativo, version,
  considerholiday, description, workdaymon_id,  workdaytue_id,  workdaywed_id,  workdaythu_id,  workdayfri_id)
  VALUES (2, '03/04/2015 16:05:11', '03/04/2015 16:05:11', NULL, 0,
  1, 'Calendário horas corridas', 2, 2, 2, 2, 2);
SET IDENTITY_INSERT workcalendar OFF;

-- WIDGET
SET IDENTITY_INSERT widget ON;
INSERT INTO "widget"
  ("id","databloqueio","datainativo","datacriacao","dataedicao","version","apresentarurlservico","descricao","nome","urlservico","inativador_id","autor_id","editor_id","tipocomponente_id")
VALUES(6,null,null,'02/04/2015 10:17:33','02/04/2015 10:17:33',0,1,'HighChart','HighChart',null,null,1,1,188);

INSERT INTO "widget"
  ("id","databloqueio","datainativo","datacriacao","dataedicao","version","apresentarurlservico","descricao","nome","urlservico","inativador_id","autor_id","editor_id","tipocomponente_id")
VALUES(8,null,null,'02/04/2015 10:27:08','02/04/2015 10:27:08',0,1,'Google Chart Gauge','Google Chart Gauge',null,null,1,1,201);

INSERT INTO "widget"
  ("id","databloqueio","datainativo","datacriacao","dataedicao","version","apresentarurlservico","descricao","nome","urlservico","inativador_id","autor_id","editor_id","tipocomponente_id")
VALUES(7,null,null,'02/04/2015 10:23:04','03/04/2015 16:41:37',1,1,'Google Chart','Google Chart',null,null,1,1,211);

INSERT INTO "widget"
  ("id","databloqueio","datainativo","datacriacao","dataedicao","version","apresentarurlservico","descricao","nome","urlservico","inativador_id","autor_id","editor_id","tipocomponente_id")
VALUES(10,null,null,'02/04/2015 10:27:08','02/04/2015 10:27:08',0,1,'Notícia','Notícia',null,null,1,1,210);

INSERT INTO "widget"
  ("id","databloqueio","datainativo","datacriacao","dataedicao","version","apresentarurlservico","descricao","nome","urlservico","inativador_id","autor_id","editor_id","tipocomponente_id")
VALUES(11,null,null,'02/04/2015 10:27:08','03/04/2015 20:25:00',1,1,'Temperatura','Temperatura',null,null,1,1,209);

INSERT INTO widget ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "apresentarurlservico", "descricao", "nome", "urlservico", "inativador_id", "autor_id", "editor_id", "tipocomponente_id")
VALUES (12, NULL, NULL, '02/04/2015 10:27:08', '02/04/2015 10:27:08', 0, 0, 'Link', 'Link', NULL, NULL, 1, 1, 198);
SET IDENTITY_INSERT widget OFF;

/* Data for the 'widgetparametro' table  (Records 1 - 16) */

SET IDENTITY_INSERT widgetparametro ON;
INSERT INTO "widgetparametro"
  ("id","databloqueio","datainativo","datacriacao","dataedicao","version","booleandefault","chavedominiodefault","datadefault","nome","numerodefault","textodefault","inativador_id","autor_id","editor_id","dominiodefault_id","tipowidgetparametrodominio_id","widget_id","widgetinativo_id")
VALUES(21,null,null,'02/04/2015 10:17:33','02/04/2015 10:17:33',0,0,'tipoHighChart',null,'tipoHighChart',null,null,null,1,1,193,56,6,null);

INSERT INTO "widgetparametro"
  ("id","databloqueio","datainativo","datacriacao","dataedicao","version","booleandefault","chavedominiodefault","datadefault","nome","numerodefault","textodefault","inativador_id","autor_id","editor_id","dominiodefault_id","tipowidgetparametrodominio_id","widget_id","widgetinativo_id")
VALUES(22,null,null,'02/04/2015 10:17:33','02/04/2015 10:17:33',0,0,null,null,'titulo',null,'titulo',null,1,1,null,13,6,null);

INSERT INTO "widgetparametro"
  ("id","databloqueio","datainativo","datacriacao","dataedicao","version","booleandefault","chavedominiodefault","datadefault","nome","numerodefault","textodefault","inativador_id","autor_id","editor_id","dominiodefault_id","tipowidgetparametrodominio_id","widget_id","widgetinativo_id")
VALUES(23,null,null,'02/04/2015 10:17:33','02/04/2015 10:17:33',0,0,null,null,'sub titulo',null,'sub titulo',null,1,1,null,13,6,null);

INSERT INTO "widgetparametro"
  ("id","databloqueio","datainativo","datacriacao","dataedicao","version","booleandefault","chavedominiodefault","datadefault","nome","numerodefault","textodefault","inativador_id","autor_id","editor_id","dominiodefault_id","tipowidgetparametrodominio_id","widget_id","widgetinativo_id")
VALUES(29,null,null,'02/04/2015 10:27:08','02/04/2015 10:27:08',0,0,null,null,'max',100,null,null,1,1,null,14,8,null);

INSERT INTO "widgetparametro"
  ("id","databloqueio","datainativo","datacriacao","dataedicao","version","booleandefault","chavedominiodefault","datadefault","nome","numerodefault","textodefault","inativador_id","autor_id","editor_id","dominiodefault_id","tipowidgetparametrodominio_id","widget_id","widgetinativo_id")
VALUES(30,null,null,'02/04/2015 10:27:08','02/04/2015 10:27:08',0,0,null,null,'yellowFrom',70,null,null,1,1,null,14,8,null);

INSERT INTO "widgetparametro"
  ("id","databloqueio","datainativo","datacriacao","dataedicao","version","booleandefault","chavedominiodefault","datadefault","nome","numerodefault","textodefault","inativador_id","autor_id","editor_id","dominiodefault_id","tipowidgetparametrodominio_id","widget_id","widgetinativo_id")
VALUES(31,null,null,'02/04/2015 10:27:08','02/04/2015 10:27:08',0,0,null,null,'yellowTo',80,null,null,1,1,null,14,8,null);

INSERT INTO "widgetparametro"
  ("id","databloqueio","datainativo","datacriacao","dataedicao","version","booleandefault","chavedominiodefault","datadefault","nome","numerodefault","textodefault","inativador_id","autor_id","editor_id","dominiodefault_id","tipowidgetparametrodominio_id","widget_id","widgetinativo_id")
VALUES(32,null,null,'02/04/2015 10:27:08','02/04/2015 10:27:08',0,0,null,null,'redFrom',80,null,null,1,1,null,14,8,null);

INSERT INTO "widgetparametro"
  ("id","databloqueio","datainativo","datacriacao","dataedicao","version","booleandefault","chavedominiodefault","datadefault","nome","numerodefault","textodefault","inativador_id","autor_id","editor_id","dominiodefault_id","tipowidgetparametrodominio_id","widget_id","widgetinativo_id")
VALUES(33,null,null,'02/04/2015 10:27:08','02/04/2015 10:27:08',0,0,null,null,'redTo',100,null,null,1,1,null,14,8,null);

INSERT INTO "widgetparametro"
  ("id","databloqueio","datainativo","datacriacao","dataedicao","version","booleandefault","chavedominiodefault","datadefault","nome","numerodefault","textodefault","inativador_id","autor_id","editor_id","dominiodefault_id","tipowidgetparametrodominio_id","widget_id","widgetinativo_id")
VALUES(34,null,null,'02/04/2015 10:27:08','02/04/2015 10:27:08',0,0,null,null,'greenFrom',40,null,null,1,1,null,14,8,null);

INSERT INTO "widgetparametro"
  ("id","databloqueio","datainativo","datacriacao","dataedicao","version","booleandefault","chavedominiodefault","datadefault","nome","numerodefault","textodefault","inativador_id","autor_id","editor_id","dominiodefault_id","tipowidgetparametrodominio_id","widget_id","widgetinativo_id")
VALUES(35,null,null,'02/04/2015 10:27:08','02/04/2015 10:27:08',0,0,null,null,'greenTo',70,null,null,1,1,null,14,8,null);

INSERT INTO "widgetparametro"
  ("id","databloqueio","datainativo","datacriacao","dataedicao","version","booleandefault","chavedominiodefault","datadefault","nome","numerodefault","textodefault","inativador_id","autor_id","editor_id","dominiodefault_id","tipowidgetparametrodominio_id","widget_id","widgetinativo_id")
VALUES(36,null,null,'02/04/2015 10:27:08','02/04/2015 10:27:08',0,0,null,null,'minorTicks',5,null,null,1,1,null,14,8,null);

INSERT INTO "widgetparametro"
  ("id","databloqueio","datainativo","datacriacao","dataedicao","version","booleandefault","chavedominiodefault","datadefault","nome","numerodefault","textodefault","inativador_id","autor_id","editor_id","dominiodefault_id","tipowidgetparametrodominio_id","widget_id","widgetinativo_id")
VALUES(24,null,null,'02/04/2015 10:23:04','03/04/2015 16:41:37',1,0,null,null,'hAxis titulo',null,'hAxis titulo',null,1,1,null,12,7,null);

INSERT INTO "widgetparametro"
  ("id","databloqueio","datainativo","datacriacao","dataedicao","version","booleandefault","chavedominiodefault","datadefault","nome","numerodefault","textodefault","inativador_id","autor_id","editor_id","dominiodefault_id","tipowidgetparametrodominio_id","widget_id","widgetinativo_id")
VALUES(25,null,null,'02/04/2015 10:23:04','03/04/2015 16:41:37',1,0,null,null,'vAxis titulo',null,'vAxis titulo',null,1,1,null,12,7,null);

INSERT INTO "widgetparametro"
  ("id","databloqueio","datainativo","datacriacao","dataedicao","version","booleandefault","chavedominiodefault","datadefault","nome","numerodefault","textodefault","inativador_id","autor_id","editor_id","dominiodefault_id","tipowidgetparametrodominio_id","widget_id","widgetinativo_id")
VALUES(26,null,null,'02/04/2015 10:23:04','03/04/2015 16:41:37',1,1,null,null,'isStacked',null,null,null,1,1,null,197,7,null);

INSERT INTO "widgetparametro"
  ("id","databloqueio","datainativo","datacriacao","dataedicao","version","booleandefault","chavedominiodefault","datadefault","nome","numerodefault","textodefault","inativador_id","autor_id","editor_id","dominiodefault_id","tipowidgetparametrodominio_id","widget_id","widgetinativo_id")
VALUES(27,null,null,'02/04/2015 10:23:04','03/04/2015 16:41:37',1,0,null,null,'Titulo',null,'Titulo',null,1,1,null,12,7,null);

INSERT INTO "widgetparametro"
  ("id","databloqueio","datainativo","datacriacao","dataedicao","version","booleandefault","chavedominiodefault","datadefault","nome","numerodefault","textodefault","inativador_id","autor_id","editor_id","dominiodefault_id","tipowidgetparametrodominio_id","widget_id","widgetinativo_id")
VALUES(28,null,null,'02/04/2015 10:23:04','03/04/2015 16:41:37',1,0,'tipoGoogleChart',null,'Tipo Grafico',null,null,null,1,1,204,56,7,null);

INSERT INTO "widgetparametro"
  ("id","databloqueio","datainativo","datacriacao","dataedicao","version","booleandefault","chavedominiodefault","datadefault","nome","numerodefault","textodefault","inativador_id","autor_id","editor_id","dominiodefault_id","tipowidgetparametrodominio_id","widget_id","widgetinativo_id")
VALUES(1,null,null,'03/04/2015 20:25:00','03/04/2015 20:25:00',0,0,null,null,'Cidade',null,'Goiania',null,1,1,null,12,11,null);
SET IDENTITY_INSERT widgetparametro OFF;

