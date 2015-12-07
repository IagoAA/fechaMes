INSERT INTO pais	(datacriacao,dataedicao,datainativo,version,codigo,nome,sigla,autor_id,editor_id,inativador_id)VALUES(LOCALTIMESTAMP,LOCALTIMESTAMP,NULL,0,'55','Brasil','BR',NULL,NULL,NULL);
INSERT INTO regiao	(datacriacao,dataedicao,datainativo,version,codigo,nome,autor_id,editor_id,inativador_id,pais_id)VALUES(LOCALTIMESTAMP,LOCALTIMESTAMP,NULL,0,'01','Centro-Oeste',NULL,NULL,NULL,1);
INSERT INTO regiao	(datacriacao,dataedicao,datainativo,version,codigo,nome,autor_id,editor_id,inativador_id,pais_id)VALUES(LOCALTIMESTAMP,LOCALTIMESTAMP,NULL,0,'02','Sul',NULL,NULL,NULL,1);
INSERT INTO estado	(datacriacao,dataedicao,datainativo,version,codigo,nome,sigla,autor_id,editor_id,inativador_id,regiao_id)VALUES(LOCALTIMESTAMP,LOCALTIMESTAMP,NULL,0,'0','Goiás','GO',NULL,NULL,NULL,1);
INSERT INTO estado	(datacriacao,dataedicao,datainativo,version,codigo,nome,sigla,autor_id,editor_id,inativador_id,regiao_id)VALUES(LOCALTIMESTAMP,LOCALTIMESTAMP,NULL,0,'1','Rio Grande do Sul','RS',NULL,NULL,NULL,2);
INSERT INTO cidade	(datacriacao,dataedicao,datainativo,version,codigo,codigoibge,nome,autor_id,editor_id,inativador_id,estado_id)VALUES(LOCALTIMESTAMP,LOCALTIMESTAMP,NULL,0,'0','12','Goiania',NULL,NULL,NULL,1);
INSERT INTO cidade	(datacriacao,dataedicao,datainativo,version,codigo,codigoibge,nome,autor_id,editor_id,inativador_id,estado_id)VALUES(LOCALTIMESTAMP,LOCALTIMESTAMP,NULL,0,'1','13','Inhumas',NULL,NULL,NULL,1);
INSERT INTO cidade	(datacriacao,dataedicao,datainativo,version,codigo,codigoibge,nome,autor_id,editor_id,inativador_id,estado_id)VALUES(LOCALTIMESTAMP,LOCALTIMESTAMP,NULL,0,'0','12','Rio Grande',NULL,NULL,NULL,2);
INSERT INTO bairro	(datacriacao,dataedicao,datainativo,version,codigo,nome,autor_id,editor_id,inativador_id,cidade_id)VALUES(LOCALTIMESTAMP,LOCALTIMESTAMP,NULL,0,'0','Bairro 1',NULL,NULL,NULL,1);
INSERT INTO bairro	(datacriacao,dataedicao,datainativo,version,codigo,nome,autor_id,editor_id,inativador_id,cidade_id)VALUES(LOCALTIMESTAMP,LOCALTIMESTAMP,NULL,0,'1','Bairro 2',NULL,NULL,NULL,2);


INSERT INTO modulo(id, datacriacao, dataedicao, datainativo, version, nome, baseUrl, restAngular, habilitado, caminho) VALUES (1, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'Patrimônio', '/citgrp-patrimonio-web', 'RestangularPatrimonio', true, 'citgrp-patrimonio/citgrp-patrimonio-api');
INSERT INTO modulo(id, datacriacao, dataedicao, datainativo, version, nome, baseUrl, restAngular, habilitado, caminho) VALUES (2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'ESI', '/cit-esi-web', 'RestangularEsi', true, 'cit-esi/cit-esi-api');
INSERT INTO modulo(id, datacriacao, dataedicao, datainativo, version, nome, baseUrl, restAngular, habilitado, caminho) VALUES (3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'Contratos', '/cit-contratos-web', 'RestangularContratos', true, 'cit-contratos/cit-contratos-api');

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (1, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoFile', 'css', 'CSS', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoFile', 'js', 'JS', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoClassificacaoMaterial', 'Grupo', 'GRUPO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (4, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoClassificacaoMaterial', 'Subgrupo', 'SUB_GRUPO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (36, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoClassificacaoMaterial', 'Material', 'MATERIAL', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (37, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoClassificacaoMaterial', 'Detalhe', 'DETALHE', 4);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (5, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMaterial', 'Consumo', 'CONSUMO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (6, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMaterial', 'Permanente', 'PERMANENTE', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (7, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoEndereco', 'Residencial', 'RESIDENCIAL', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (8, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoEndereco', 'Comercial', 'COMERCIAL', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (9, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoRestricao', 'nenhum', 'NENHUM', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (10, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoRestricao', 'por material', 'POR_MATERIAL', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (11, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoRestricao', 'geral', 'GERAL', 3);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (12, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDado', 'Texto curto', 'TEXT_FIELD', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (13, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDado', 'Texto longo', 'TEXT_AREA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (14, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDado', 'Numérico', 'NUMBER', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (15, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDado', 'Valor', 'DECIMAL', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (55, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDado', 'Data', 'DATA', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (56, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDado', 'Tabela tipo domínio', 'TIPO_DOMINIO', 6);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (57, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDado', 'Arquivo', 'ARQUIVO', 7);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (187, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDado', 'Lógico', 'LOGICO', 8);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (189, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDado', 'URL de Serviço', 'URL_SERVICO', 9);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (197, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDado', 'Boolean', 'BOOLEAN', 10);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (19, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoParceiro', 'Colaborador', 'COLABORADOR', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (17, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoParceiro', 'Orgão externo', 'ORGAO_EXTERNO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (18, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoParceiro', 'Portador', 'PORTADOR', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (16, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoParceiro', 'Fornecedor', 'FORNECEDOR', 4);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (20, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDocumento', 'Nota fiscal', 'NOTA_FISCAL', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (21, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDocumento', 'Nota de empenho', 'NOTA_EMPENHO', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (22, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoPessoa', 'Física', 'FISICA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (23, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoPessoa', 'Jurídica', 'JURIDICA', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (24, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoTelefone', 'Comercial', 'COMERCIAL', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (25, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoTelefone', 'Residencial', 'RESIDENCIAL', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (42, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoTelefone', 'Celular', 'CELULAR', 3);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (26, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoBem', 'Próprio contabilizado', 'PROPRIO_CONTABILIZADO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (79, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoBem', 'Próprio controlado', 'PROPRIO_CONTROLADO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (80, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoBem', 'De terceiros', 'DE_TERCEIROS', 3);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (27, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoEntrada', 'Orçamentária', 'ORCAMENTARIA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (135, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoEntrada', 'Extra orçamentária', 'EXTRA_ORCAMENTARIA', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (30, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusBem', 'Utilizado', 'UTILIZADO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (58, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusBem', 'Baixado', 'BAIXADO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (59, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusBem', 'Disponível', 'DISPONIVEL', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (60, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusBem', 'Em processo de baixa', 'EM_PROCESSO_DE_BAIXA', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (61, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusBem', 'Em processo de transferência', 'EM_PROCESSO_DE_TRANSFERENCIA', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (62, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusBem', 'Em saída temporária', 'EM_SAIDA_TEMPORARIA', 6);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (63, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusBem', 'Extraviado', 'EXTRAVIADO', 7);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (64, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusBem', 'Indisponível', 'INDISPONIVEL', 8);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (65, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusBem', 'Sindicância', 'SINDICANCIA', 9);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (174, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusBem', 'Não localizado', 'TIPO_STATUS_BEM_NAO_LOCALIZADO', 10);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (31, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoPrioridade', 'Baixo', 'BAIXO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (32, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoPrioridade', 'Médio', 'MEDIO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (33, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoPrioridade', 'Alto', 'ALTO', 3);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (34, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoNotificacao', 'Patrimônio', 'MODULO_PATRIMONIO', 1);
-- INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (35, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoNotificacao', 'Sistemas internos', 'MODULO_SISTEMAS_INTERNOS', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (38, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoSexo', 'Masculino', 'MASCULINO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (39, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoSexo', 'Feminino', 'FEMININO', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (40, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoEstadoCivil', 'Solteiro', 'SOLTEIRO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (41, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoEstadoCivil', 'Casado', 'CASADO', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (43, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoEstruturaOrganizacional', 'Unidade gestora', 'UNIDADE_GESTORA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (44, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoEstruturaOrganizacional', 'Unidade administrativa', 'UNIDADE_ADMINISTRATIVA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (45, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoEstruturaOrganizacional', 'Unidade localizadora', 'UNIDADE_LOCALIZADORA', 3);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (97, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAbrangencia', 'Local', 'LOCAL', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (46, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAbrangencia', 'Nacional', 'NACIONAL', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (98, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAbrangencia', 'Regional', 'REGIONAL', 3);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (47, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoPorte', 'Micro empresa', 'MICRO_EMPRESA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (94, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoPorte', 'Pequena', 'PEQUENA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (95, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoPorte', 'Media', 'MEDIA', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (96, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoPorte', 'Grande', 'GRANDE', 4);


INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (48, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoFornecedor', 'Juridica', 'JURIDICA', 1);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (49, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoModalidadeTransferencia', 'Permanente', 'PERMANENTE', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (50, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoModalidadeTransferencia', 'Temporária', 'TEMPORARIA', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (51, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoObjetivoTransferencia', 'Comodato', 'COMODATO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (52, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoObjetivoTransferencia', 'Conserto', 'CONSERTO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (53, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoObjetivoTransferencia', 'Empréstimo', 'EMPRESTIMO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (54, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoObjetivoTransferencia', 'Evento', 'EVENTO', 4);


INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (66, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoUnidadeMedida', 'UN', 'UN', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (67, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoUnidadeMedida', 'CX', 'CX', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (68, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoUnidadeMedida', 'PC ', 'PC', 3);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (71, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAnexo', 'application/xml', 'XML', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (72, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAnexo', 'image/jpg', 'JPG', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (73, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAnexo', 'image/jpeg', 'JPEG', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (74, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAnexo', 'image/png', 'PNG', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (75, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAnexo', 'application/octet-stream', 'DOC', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (76, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAnexo', 'application/octet-stream', 'XLS', 6);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (270, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAnexo', 'application/pdf', 'PDF', 7);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (77, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAnexo', 'application/octet-stream', 'SEM_EXTENSAO', 7);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (81, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoBaixa', 'Venda', 'VENDA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (82, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoBaixa', 'Doação', 'DOACAO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (83, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoBaixa', 'Cessão de uso', 'CESSAO_DE_USO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (84, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoBaixa', 'Extravio', 'EXTRAVIO', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (85, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoBaixa', 'Permuta', 'PERMUTA', 5);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (86, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoSituacaoBaixa', 'Em andamento', 'EM_ANDAMENTO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (87, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoSituacaoBaixa', 'Autorizada', 'AUTORIZADA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (88, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoSituacaoBaixa', 'Não autorizada', 'NAO_AUTORIZADA', 3);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (89, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoObjetivoSaidaTemporaria', 'Comodato', 'COMODATO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (90, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoObjetivoSaidaTemporaria', 'Conserto', 'CONSERTO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (91, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoObjetivoSaidaTemporaria', 'Empréstimo', 'EMPRESTIMO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (92, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoObjetivoSaidaTemporaria', 'Evento', 'EVENTO', 4);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (93, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoProjetoSaidaTemporaria', 'Outro', 'OUTRO', 1);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (99, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoContaContabil', 'Resultado', 'RESULTADO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (100, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoContaContabil', 'Receita', 'RECEITA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (101, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoContaContabil', 'Despesa', 'DESPESA', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (102, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoContaContabil', 'Passivo', 'PASSIVO', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (103, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoContaContabil', 'Ativo', 'ATIVO', 5);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (104, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDepreciacao', 'Linear', 'LINEAR', 1);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (105, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAlteracaoBemPatrimonial', 'Período de garantia', 'PERIODO_GARANTIA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (106, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAlteracaoBemPatrimonial', 'Situação física', 'SITUACAO_FISICA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (107, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAlteracaoBemPatrimonial', 'Status do bem', 'STATUS_BEM', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (108, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAlteracaoBemPatrimonial', 'Número patrimonial', 'NUMERO_PATRIMONIAL', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (109, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAlteracaoBemPatrimonial', 'Reavaliação', 'REAVALIACAO', 5);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (110, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Ajuste de dados', 'AJUSTE_DE_DADOS', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (111, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Atualização monetária', 'ATUALIZACAO_MONETARIA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (112, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Reavaliação', 'REAVALIACAO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (113, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Inventário', 'INVENTARIO', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (136, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Alteração devida a depreciação', 'DEPRECIACAO', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (146, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Incorporação de bem patrimonial', 'INCORPORACAO_BEM_PATRIMONIAL', 6);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (148, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Início do processo de baixa de bens', 'INICIO_PROCESSO_BAIXA', 7);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (149, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Processo de baixa de bens concluído', 'PROCESSO_DE_BAIXA_AUTORIZADO', 8);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (150, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Processo de baixa de bens não autorizado', 'PROCESSO_DE_BAIXA_NAO_AUTORIZADO', 9);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (151, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Atualização do status na baixa', 'ATUALIZACAO_STATUS_BAIXA', 10);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (152, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Transferência por ocasião de atribuição a bem principal', 'TRANSFERENCIA_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL', 11);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (155, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Transferência entre estruturas organizacionais', 'TRANSFERENCIA_ENTRE_ESTRUTURAS_ORGANIZACIONAIS', 12);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (156, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Atribuição de novo detentor por ocasião de atribuição a bem principal', 'ATRIBUICAO_DE_NOVO_DETENTOR_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL', 13);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (157, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Atribuição de novo responsável por ocasião de atribuição a bem principal', 'ATRIBUICAO_DE_NOVO_RESPONSAVEL_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL', 14);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (158, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Atribuição a um bem principal', 'ATRIBUICAO_A_UM_BEM_PRINCIPAL', 15);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (159, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Atribuição de bens filhos', 'ATRIBUICAO_DE_BENS_FILHOS', 16);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (160, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Estorno da baixa', 'ESTORNO_BAIXA', 17);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (161, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Estorno do item da baixa', 'ESTORNO_ITEM_BAIXA', 18);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (162, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Exclusão da baixa', 'EXCLUIR_BAIXA', 19);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (163, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Exclusão do item da baixa', 'EXCLUIR_ITEM_BAIXA', 20);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (164, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Definição detentor', 'DEFINICAO_DETENTOR', 21);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (166, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Exclusão de um bem vinculado', 'EXCLUSAO_DE_UM_BEM_VINCULADO', 22);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (167, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Exclusão de vinculo a bem principal', 'EXCLUSAO_DE_VINCULO_A_BEM_PRINCIPAL', 23);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (168, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Exclusão do detentor por ocasião da exclusão do vínculo com o bem principal', 'EXCLUSAO_DO_DETENTOR_POR_OCASIAO_DA_EXCLUSAO_DO_VINCULO_COM_O_BEM_PRINCIPAL', 24);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (169, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Exclusão do responsável por ocasião da exclusão do vínculo com o bem principal', 'EXCLUSAO_DO_RESPONSAVEL_POR_OCASIAO_DA_EXCLUSAO_DO_VINCULO_COM_O_BEM_PRINCIPAL', 25);


INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (170, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Definição responsável', 'TIPO_MOTIVO_ALTERACAO_BEM_DEFINICAO_NOVO_RESPONSAVEL_CODIGO', 26);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (171, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Saída temporária', 'TIPO_MOTIVO_ALTERACAO_BEM_EM_SAIDA_TEMPORARIA', 27);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (172, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Transferência por ocasião de definição de novo detentor', 'TIPO_MOTIVO_ALTERACAO_BEM_TRANSFERENCIA_POR_OCASIAO_DE_DEFINICAO_DE_NOVO_DETENTOR', 28);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (175, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Remoção de Detentor por ocasião de atribuição a Bem Principal', 'TIPO_MOTIVO_ALTERACAO_BEM_REMOCAO_DE_DETENTOR_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL', 29);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (176, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Remoção de Responsável por ocasião de atribuição a Bem Principal', 'TIPO_MOTIVO_ALTERACAO_BEM_REMOCAO_DE_RESPONSAVEL_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL', 30);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (185, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Retorno da saída temporária', 'RETORNO_SAIDA_TEMPORARIA', 31);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (186, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMotivoAlteracaoBem', 'Remoção de Responsável por ocasião de atribuição de detentor que não é responsável pelo bem', 'REMOCAO_DE_RESPONSAVEL_POR_OCASIAO_DE_ATRIBUICAO_DE_DETENTOR_QUE_NAO_E_RESPONSAVEL_PELO_BEM', 32);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (28, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoSituacaoFisica', 'Bom', 'BOM', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (29, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoSituacaoFisica', 'Regular', 'REGULAR', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (116, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoSituacaoFisica', 'Inservível', 'INSERVIVEL', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (117, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoSituacaoFisica', 'Precário', 'PRECARIO', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (118, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoSituacaoFisica', 'Ruim', 'RUIM', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (119, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoSituacaoFisica', 'Antieconômico', 'ANTIECONOMICO', 6);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (120, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoSituacaoFisica', 'Danificado', 'DANIFICADO', 7);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (121, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoSituacaoFisica', 'Obsoleto', 'OBSOLETO', 8);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (122, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoSituacaoFisica', 'Ocioso', 'OCIOSO', 9);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (123, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoSituacaoFisica', 'Recuperável', 'RECUPERAVEL', 10);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (124, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoOperacao', 'Alteração de status', 'ATUALIZACAO_STATUS', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (125, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoOperacao', 'Processo de sindicância', 'PROCESSO_SINDICANCIA', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (126, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoHistorico', 'Alteração', 'ALTERACAO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (145, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoHistorico', 'Entrada', 'HISTORICO_ENTRADA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (147, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoHistorico', 'Baixa', 'HISTORICO_BAIXA', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (153, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoHistorico', 'Adição a bem principal', 'ADICAO_A_BEM_PRINCIPAL', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (154, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoHistorico', 'Transferência interna', 'TRANSFERENCIA_INTERNA', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (165, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoHistorico', 'Atribuir detentor', 'TIPO_HISTORICO_ATRIBUIR_DETENTOR', 6);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (127, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusDepreciacao', 'Depreciável', 'DEPRECIAVEL', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (128, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusDepreciacao', 'Armazenado no Almoxarifado', 'NAO_DEPRECIAVEL_ALMOXARIFADO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (129, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusDepreciacao', 'Valor mínimo', 'NAO_DEPRECIAVEL_VALOR_MINIMO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (130, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusDepreciacao', 'Vida útil zero', 'NAO_DEPRECIAVEL_VIDA_UTIL', 4);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (131, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMovimentoContaContabil', 'Entrada', 'ENTRADA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (132, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMovimentoContaContabil', 'Baixa', 'BAIXA', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (137, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMovimentacao', 'Transferência interna', 'TRANSFERENCIA_INTERNA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (138, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoMovimentacao', 'Definição detentor', 'DEFINICAO_DETENTOR', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (139, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoRecebimento', 'Compra', 'COMPRA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (140, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoRecebimento', 'Cessão', 'CESSAO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (141, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoRecebimento', 'Doação', 'DOACAO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (142, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoRecebimento', 'Permuta', 'PERMUTA', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (143, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoRecebimento', 'Transferência', 'TRANSFERENCIA', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (144, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoRecebimento', 'Produção interna', 'PRODUCAO_INTERNA', 6);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (177, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoFeriado', 'Data', 'DATA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (178, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoFeriado', 'Período', 'PERIODO', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (179, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'abrangenciaFeriado', 'Mundial', 'MUNDIAL', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (180, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'abrangenciaFeriado', 'Nacional', 'NACIONAL', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (181, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'abrangenciaFeriado', 'Estadual', 'ESTADUAL', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (182, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'abrangenciaFeriado', 'Municipal', 'MUNICIPAL', 4);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (183, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoExcecaoFeriado', 'Folga', 'FOLGA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (184, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoExcecaoFeriado', 'Trabalho', 'TRABALHO', 2);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (188, TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoWidget', 'HighChart', 'highchartcit', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (198, TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoWidget', 'Link', 'link', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (209, TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoWidget', 'Temperatura', 'temperatura', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (210, TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoWidget', 'Notícia', 'noticia', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (211, TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoWidget', 'Google Chart', 'googlechartcit', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (201, TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoWidget', 'Google Chart Gauge', 'googlechartgaugecit', 6);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (269, TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoWidget', 'Html', 'markdown', 7);



INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (193, TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoHighChart', 'Pizza', 'pie', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (194, TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoHighChart', 'Linha', 'line', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (195, TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoHighChart', 'Barra', 'bar', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (196, TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoHighChart', 'Coluna', 'column', 1);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (202, TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoGoogleChart', 'AreaChart', 'AreaChart', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (203, TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoGoogleChart', 'PieChart', 'PieChart', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (204, TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoGoogleChart', 'ColumnChart', 'ColumnChart', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (205, TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoGoogleChart', 'LineChart', 'LineChart', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (206, TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoGoogleChart', 'Table', 'Table', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (207, TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoGoogleChart', 'BarChart', 'BarChart', 1);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (190, TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoIdioma', 'Português', 'pt_BR', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (191, TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoIdioma', 'English', 'en_US', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (192, TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-19 15:38:25','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoIdioma', 'Español', 'es_ES', 3);

--CONTRATOS
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (265, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoPerspectiva', 'Financeiro', 'FINANCEIRO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (266, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoPerspectiva', 'Operacional', 'OPERACIONAL', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (267, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoPerspectiva', 'Cliente', 'CLIENTE', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (268, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoPerspectiva', 'Recursos Humanos', 'RECURSOSHUMANOS', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (212, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAnalise', 'Planejamento Estratégico', 'PLANEJAMENTOESTRATEGICO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (213, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAnalise', 'Controladoria', 'CONTROLADORIA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (214, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAnalise', 'Qualidade', 'QUALIDADE', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (215, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAnalise', 'Legal', 'LEGAL', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (216, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAnalise', 'Contratual', 'CONTRATUAL', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (217, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAnalise', 'Documental', 'DOCUMENTAL', 6);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (218, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoColeta', 'Manual', 'MANUAL', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (219, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoColeta', 'Automática', 'AUTOMATICA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (220, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAtribuicao', 'Responsável pelo centro de resultado', 'RESPONSAVEL', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (221, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoAtribuicao', 'Qualquer membro do grupo', 'GRUPO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (222, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoCondicao', 'Nenhuma', 'NENHUMA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (223, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoCondicao', 'Se anterior for executado', 'ANTERIOR_EXECUTADO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (224, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoCondicao', 'Após agendamento anterior', 'APOS_AGENDAMENTO_ANTERIOR', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (225, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoReiteracao', 'Até a execução', 'ATE_EXECUCAO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (226, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoReiteracao', 'Até o final do período', 'FINAL_PERIODO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (227, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoReiteracao', 'Quantidade definida', 'QUANTIDADE_DEFINIDA', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (228, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDestino', 'Responsáveis', 'RESPONSAVEIS', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (229, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDestino', 'Superiores', 'SUPERIORES', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (230, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDisparo', 'No atraso', 'ATRASO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (231, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDisparo', 'No criação', 'CRIACAO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (232, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDisparo', 'No Execução', 'EXECUCAO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (233, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoRepeticao', 'Até a execução', 'EXECUCAO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (234, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoRepeticao', 'Nenhuma', 'NENHUMA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (235, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoRepeticao', 'Até o fim do agendamento', 'FIM_AGENDAMENTO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (236, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoIntercorrencia', 'Atividade não entregue', 'NAO_ENTREGUE', 1);
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
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (255, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoModalidade', 'Concorrência', 'CONCORRENCIA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (256, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoModalidade', 'Convite', 'CONVITE', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (257, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoModalidade', 'Tomada de Preço', 'TOMADA_PRECO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (258, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoModalidade', 'Concurso', 'CONCURSO', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (259, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoModalidade', 'Pregão', 'PREGAO', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (260, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoGarantia', 'Contratual', 'CONTRATUAL', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (261, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoGarantia', 'Fornecedor', 'FORNECEDOR', 2);


INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (262, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoModalidadeGarantia', 'Calção em dinheiro ou títulos da dívida pública', 'CAlCAO_DINHEIRO_TITULOS', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (263, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoModalidadeGarantia', 'Seguro-garantia', 'SEGURO_GARANTIA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (264, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoModalidadeGarantia', 'Fiança bancária', 'FIANCA_BANCARIA', 3);


INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (271, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoTempoAtualizacao', '1 minuto', 'UM', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (272, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoTempoAtualizacao', '5 minuto', 'CINCO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (273, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoTempoAtualizacao', '10 minuto', 'DEZ', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (274, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoTempoAtualizacao', '15 minuto', 'QUINZE', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (275, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoTempoAtualizacao', '30 minuto', 'TRINTA', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (276, TO_DATE('2015-02-25 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-02-25 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoTempoAtualizacao', '1 Hora', 'UMA_HORA', 6);


-- INVENTÁRIO
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (277, TO_DATE('2015-03-30 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-30 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusInventario', 'Aberto', 'ABERTO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (278, TO_DATE('2015-03-30 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-30 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusInventario', 'Em andamento', 'EM_ANDAMENTO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (279, TO_DATE('2015-03-30 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-30 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusInventario', 'Encerrado', 'ENCERRADO', 3);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (280, TO_DATE('2015-03-30 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-30 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoInventario', 'Anual', 'ANUAL', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (281, TO_DATE('2015-03-30 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-30 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoInventario', 'Inicial', 'INICIAL', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (282, TO_DATE('2015-03-30 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-30 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoInventario', 'Transferência', 'TRANSFERENCIA', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (283, TO_DATE('2015-03-30 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-30 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoInventario', 'Extinção ou Tranformação', 'EXTINCAO_TRANSFORMACAO', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (284, TO_DATE('2015-03-30 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-30 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoInventario', 'Eventual', 'Eventual', 5);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (285, TO_DATE('2015-03-30 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-30 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusInventarioEstrutura', 'Aberto', 'ABERTO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (286, TO_DATE('2015-03-30 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-30 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusInventarioEstrutura', 'Em campo', 'EM_CAMPO', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (287, TO_DATE('2015-03-30 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-30 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusInventarioEstrutura', 'Em tratamento', 'EM_TRATAMENTO', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (288, TO_DATE('2015-03-30 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-30 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusInventarioEstrutura', 'Encerrado', 'ENCERRADO', 4);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (289, TO_DATE('2015-03-30 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-30 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusInventarioBemPatrimonial', 'Não informado', 'NAO_INFORMADO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (290, TO_DATE('2015-03-30 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-30 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusInventarioBemPatrimonial', 'Não há inconsistência', 'NAO_HA_INCONSISTENCIA', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (291, TO_DATE('2015-03-30 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-30 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusInventarioBemPatrimonial', 'Em inconsistência', 'EM_INCONSISTENCIA', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (292, TO_DATE('2015-03-30 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-30 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoStatusInventarioBemPatrimonial', 'Tratada', 'TRATADA', 4);

INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (293, TO_DATE('2015-04-06 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-04-06 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDominioInconsistencia', 'Bem patrimonial não encontrado', 'CODIGO_TIPO_INCONSISTENCIA_BEM_NAO_ENCONTRADO', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (294, TO_DATE('2015-04-06 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-04-06 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDominioInconsistencia', 'Estrutura organizacional divergente', 'CODIGO_TIPO_INCONSISTENCIA_ESTRUTURA_ORGANIZACIONAL', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (295, TO_DATE('2015-04-06 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-04-06 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDominioInconsistencia', 'Material divergente', 'CODIGO_TIPO_INCONSISTENCIA_MATERIAL', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (296, TO_DATE('2015-04-06 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-04-06 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDominioInconsistencia', 'Situação física divergente', 'CODIGO_TIPO_INCONSISTENCIA_SITUACAO_FISICA', 4);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (297, TO_DATE('2015-04-06 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-04-06 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDominioInconsistencia', 'Status divergente', 'CODIGO_TIPO_INCONSISTENCIA_STATUS', 5);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (298, TO_DATE('2015-04-06 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-04-06 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDominioInconsistencia', 'Valor divergente', 'CODIGO_TIPO_INCONSISTENCIA_VALOR', 6);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (299, TO_DATE('2015-04-06 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-04-06 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDominioInconsistencia', 'Responsável divergente', 'CODIGO_TIPO_INCONSISTENCIA_RESPONSAVEL', 7);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (300, TO_DATE('2015-04-06 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-04-06 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDominioInconsistencia', 'Detentor divergente', 'CODIGO_TIPO_INCONSISTENCIA_DETENTOR', 8);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (301, TO_DATE('2015-04-06 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-04-06 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDominioInconsistencia', 'Bem principal divergente', 'CODIGO_TIPO_INCONSISTENCIA_BEM_PRINCIPAL', 9);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (302, TO_DATE('2015-04-06 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-04-06 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoDominioInconsistencia', 'Bem patrimonial sem plaqueta', 'CODIGO_TIPO_INCONSISTENCIA_BEM_SEM_PLAQUETA', 10);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (303, TO_DATE('2015-04-06 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-04-06 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoLocalInconsistencia', 'Sistema', 'SISTEMA', 1);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (304, TO_DATE('2015-04-06 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-04-06 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoLocalInconsistencia', 'Manual', 'MANUAL', 2);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (305, TO_DATE('2015-04-06 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-04-06 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoLocalInconsistencia', 'Mobile', 'MOBILE', 3);
INSERT INTO dominio(id, datacriacao, dataedicao, datainativo, version, chave, descricao, nome, codigo) VALUES (306, TO_DATE('2015-05-11 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-05-11 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'tipoObjetivoInventario', 'Objetivo inventário', 'CODIGO_OBJETIVO_INVENTARIO_1', 1);
-- dominio id a partir de 307 esta liberado -- XD

-- USUARIO - SISTEMA
INSERT INTO seguranca_usuario (id,datacriacao,dataedicao,datainativo,version,contabloqueada,contaexpirada,contahabilitada,credencialexpirada,email,password,passwordhint,username,website,autor_id,editor_id,inativador_id, semprenovaaba) VALUES (1,TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'),NULL,0,false,false,true,false,'usuario@usuario.com','$2a$10$/mCLiZIxHplKbJBQOt2Hhu6q/1xvxsKNh3AwBAO0JkLs2wjU8jIyq','lembra','admin','',NULL,NULL,NULL, 'true');
INSERT INTO seguranca_usuario (id,datacriacao,dataedicao,version,contabloqueada,contaexpirada,contahabilitada,credencialexpirada,email,password,passwordhint,username,website,semprenovaaba) VALUES (2,LOCALTIMESTAMP,LOCALTIMESTAMP,0,false,false,true,false,'consultor@consultor.com','$2a$10$s9VYrViK0OQGTh4zG7cPBuwzjxzppa7QtirWgPC3osZEtMZkVKglG','consultor','consultor','','true');

-- CEIA PRIVILEGIOS DE ACESSO
INSERT INTO seguranca_privilegio (id, version, nome, descricao,datacriacao,dataedicao) VALUES(1 ,0, 'ROLE_ADMIN', 'ROLE_ADMIN',TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO seguranca_privilegio (id, version, nome, descricao,datacriacao,dataedicao) VALUES(2 ,0, 'ROLE_USER', 'ROLE_USER',TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'));

-- ATRIBUI OS PRIVILEGIOS AO USUARIO
INSERT INTO seguranca_usuario_privilegio(id ,version, usuario_id, privilegio_id ,datacriacao,dataedicao)VALUES(1 ,0, 1, 1,TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO seguranca_usuario_privilegio(id ,version, usuario_id, privilegio_id ,datacriacao,dataedicao)VALUES(2 ,0, 1, 2,TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO seguranca_usuario_privilegio(id ,version, usuario_id, privilegio_id ,datacriacao,dataedicao)VALUES(3 ,0, 2, 2,TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'));

-- ORGAOS OU ESTRUTURAS - CORE
INSERT INTO estruturaorgao(id, datacriacao, dataedicao, version, autor_id, isorgao, nome, sigla, dataReferenciaVigente)
	VALUES (1, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, 1, true, 'Órgão Teste 1', 'Órgão 1', '2015-01-01 09:26:25');
INSERT INTO estruturaorgao(id, datacriacao, dataedicao, version, autor_id, isOrgao, nome, sigla, dataReferenciaVigente)
	VALUES (2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, 1, true, 'Órgão Teste 2', 'Órgão 2', '2015-01-01 09:26:25');
INSERT INTO estruturaorgao(id, datacriacao, dataedicao, version, autor_id, isOrgao, nome, sigla)
	VALUES (3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, 1, false, 'Estrutura Organizacional Teste 1.1 Almoxarifado', 'EOT1.1');
INSERT INTO estruturaorgao(id, datacriacao, dataedicao, version, autor_id, isOrgao, nome, sigla)
	VALUES (4, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, 1, false, 'Estrutura Organizacional Teste 1.2', 'EOT1.2');
INSERT INTO estruturaorgao(id, datacriacao, dataedicao, version, autor_id, isOrgao, nome, sigla)
	VALUES (5, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, 1, false, 'Estrutura Organizacional Teste 2.1', 'EOT2.1');

-- COLOCA ORGAO NO USUARIO
UPDATE public.seguranca_usuario SET orgao_id=1 WHERE id = 1;
UPDATE public.seguranca_usuario SET orgao_id=1 WHERE id = 2;

INSERT INTO orgaoitem(id, datacriacao, dataedicao, version, orgao_id, usuario_id) VALUES (1, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, 1, 1);
INSERT INTO orgaoitem(id, datacriacao, dataedicao, version, orgao_id, usuario_id) VALUES (2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, 2, 1);
INSERT INTO orgaoitem(id, datacriacao, dataedicao, version, orgao_id, usuario_id) VALUES (3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, 1, 2);

-- ENDERECO
INSERT INTO endereco(id, datacriacao, dataedicao, datainativo, version, cep, codigo, complemento, logradouro, nome, numero, bairro_id, cidade_id, dominiotipoendereco_id, pessoa_id, latitude, longitude)
    VALUES (1, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, '74043000', 'COD', 'COMPLEMENTO TESTE', 'LOG. TESTE', 'NOME TESTE', '0000', 1, 1, 7, NULL, 12, 14);

-- MAPA ORGANIZACIONAL
INSERT INTO mapaorganizacional(id, datacriacao, dataedicao, version, datainicio, nome, orgao_id)
  VALUES (1, TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), 0, TO_DATE('2014-12-23','YYYY-MM-DD'), 'Mapa Organizacional 1', 1);

-- CONFIGURAÇÃO ESTRUTURA ORGANIZACIONAL ORGAO 1
INSERT INTO configuracao(id, datacriacao, dataedicao, version, dominioStatusBemExtraviado_id)
	VALUES (1, TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), 0, 63);
-- CONFIGURAÇÃO ESTRUTURA ORGANIZACIONAL ORGAO 2
INSERT INTO configuracao(id, datacriacao, dataedicao, version, dominioStatusBemExtraviado_id)
	VALUES (2, TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), 0, 63);

-- LOCALIZACAO
INSERT INTO localizacao(id, datacriacao, dataedicao, version, nome, autor_id, editor_id, endereco_id, codigo, orgao_id)
    VALUES (1, '2014-11-20', '2014-11-20', 1, 'Sala 101', 1, 1, 1, 'LOC0001', 1);

-- ESTRUTURA ORGANIZACIONAL - ORGAO
INSERT INTO estruturaorganizacional(id, classificacao, datainicio, possuibempatrimonial, dominiotipoestruturaorganizacional_id, localizacao_id, mapaorganizacional_id, codigo, almoxarifado, configuracao_id)
	VALUES (1, 'classificação  1', '2015-01-01 09:26:25', false, 43, 1, 1, 456, false, 1);
INSERT INTO estruturaorganizacional(id, classificacao, datainicio,possuibempatrimonial, dominiotipoestruturaorganizacional_id, localizacao_id, mapaorganizacional_id, codigo, almoxarifado, configuracao_id)
	VALUES (2, 'classificação  2', '2015-01-01 09:26:25', false, 43, 1, 1, 457, false, 2);

-- CLASSIFICAÇÃO MATERIAL
INSERT INTO classificacaomaterial( id, datacriacao, dataedicao, datainativo, version, codigo, descricao, dominiotipoclassificacaomaterial_id, dominiotipomaterial_id, classificacaomaterialparent_id, orgao_id)
    VALUES (1, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'COD 01', 'Superior 1', 3, 5, NULL, 1);

INSERT INTO classificacaomaterial( id, datacriacao, dataedicao, datainativo, version, codigo, descricao, dominiotipoclassificacaomaterial_id, dominiotipomaterial_id, classificacaomaterialparent_id, orgao_id)
    VALUES (2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'COD 02', 'Superior 2', 3, 5, NULL, 1);

INSERT INTO classificacaomaterial( id, datacriacao, dataedicao, datainativo, version, codigo, descricao, dominiotipoclassificacaomaterial_id, dominiotipomaterial_id, classificacaomaterialparent_id, orgao_id)
    VALUES (3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'COD 03', 'SubGrupo 21', 4, 5, 2, 1);

INSERT INTO classificacaomaterial( id, datacriacao, dataedicao, datainativo, version, codigo, descricao, dominiotipoclassificacaomaterial_id, dominiotipomaterial_id, classificacaomaterialparent_id, orgao_id)
    VALUES (4, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'COD 04', 'SubGrupo 12', 4, 5, 1, 1);

INSERT INTO classificacaomaterial( id, datacriacao, dataedicao, datainativo, version, codigo, descricao, dominiotipoclassificacaomaterial_id, dominiotipomaterial_id, classificacaomaterialparent_id, orgao_id)
    VALUES (5, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'COD 05', 'MATERIAL 121', 36, 5, 4, 1);

INSERT INTO classificacaomaterial( id, datacriacao, dataedicao, datainativo, version, codigo, descricao, dominiotipoclassificacaomaterial_id, dominiotipomaterial_id, classificacaomaterialparent_id, orgao_id)
    VALUES (6, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'COD 06', 'MATERIAL 122', 36, 5, 4, 1);

INSERT INTO classificacaomaterial( id, datacriacao, dataedicao, datainativo, version, codigo, descricao, dominiotipoclassificacaomaterial_id, dominiotipomaterial_id, classificacaomaterialparent_id, orgao_id)
    VALUES (7, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'COD 07', 'DETALHE 1211', 37, 5, 5, 1);

-- CONTA CONTABIL
INSERT INTO contacontabil(id, datacriacao, dataedicao, version, codigo, codigosistemaorigem, descricao, percentualnaodepreciavel, taxadepreciacaomensal, vidautil,
      autor_id, editor_id, dominiotipocontacontabil_id, orgao_id)
    VALUES (1, '2015-01-01 09:26:25', '2015-01-01 09:26:25', 0, 123456, 00001111, 'MAQUINA E EQUIPAMENTOS DE NATUREZA INDUSTRIAL', 0.3000, 0.1000, 180,
        1, 1, 99, 1);

INSERT INTO contacontabilconfiguracaoreferencia(id, datacriacao, dataedicao, version, apresentarelatoriomovimentacaobem, autor_id, editor_id, contacontabil_id, mesreferencia)
    VALUES (1, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 0, true, 1, 1, 1, TO_DATE('2015-01-01 09:26:25', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO contacontabil(id, datacriacao, dataedicao, version, codigo, codigosistemaorigem, descricao, percentualnaodepreciavel, taxadepreciacaomensal, vidautil,
      autor_id, editor_id, dominiotipocontacontabil_id, orgao_id)
    VALUES (2 ,'2015-01-01 09:26:25', '2015-01-01 09:26:25', 0, 123000, 00001111, 'EQUI.DE PROCESSAMENTO DE DADOS', 0.3000, 0.1000, 180,
        1, 1, 100, 1);

INSERT INTO contacontabilconfiguracaoreferencia(id, datacriacao, dataedicao, version, apresentarelatoriomovimentacaobem, autor_id, editor_id, contacontabil_id, mesreferencia)
    VALUES (2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 0, true, 1, 1, 2, TO_DATE('2015-01-01 09:26:25', 'YYYY-MM-DD HH24:MI:SS'));

-- CONTA CONTABIL SALDO
INSERT INTO contacontabilsaldo(id, datacriacao, dataedicao, version, datareferencia, valor, autor_id, editor_id, contacontabil_id)
    VALUES (1, '2015-01-01 09:26:25', '2015-01-01 09:26:25', 0, '2014-12-01 09:26:25', 0, 1, 1, 1);

INSERT INTO contacontabilsaldo(id, datacriacao, dataedicao, version, datareferencia, valor, autor_id, editor_id, contacontabil_id)
    VALUES (2, '2015-01-01 09:26:25', '2015-01-01 09:26:25', 0, '2014-12-01 09:26:25', 0, 1, 1, 2);

-- VINCULA CONTA CONTABIL AO SALDO ANTERIOR
UPDATE contacontabil SET contacontabilsaldoanterior_id=1 WHERE id = 1;
UPDATE contacontabil SET contacontabilsaldoanterior_id=2 WHERE id = 2;

-- VINCULA CONTA CONTABIL ALMOXARIFADO A CONFIGURACAO 1 DA ESTRUTURA ORGAO 1
UPDATE configuracao SET contaContabilAlmoxarifado_id=1 WHERE id = 1;

-- CARACTERISTICA
INSERT INTO caracteristica(id, datacriacao, dataedicao, version, codigo, descricao, expressaoregular, tamanho, autor_id, editor_id, dominiotipodado_id, dominiotiporestricao_id, chavedominio, orgao_id)
    VALUES (1, '2014-11-20', '2014-11-20', 0, 'CODCARACTERE', 'CARACTERE', '', 30, 1, 1, 12, 9, 'tipoRestricao', 1);
INSERT INTO caracteristica(id, datacriacao, dataedicao, version, codigo, descricao, expressaoregular, tamanho, autor_id, editor_id, dominiotipodado_id, dominiotiporestricao_id, chavedominio, orgao_id)
    VALUES (2, '2014-11-20', '2014-11-20', 0, 'CODTEXT_AREA', 'TEXT_AREA', '', 300, 1, 1, 13, 9, 'tipoRestricao', 1);
INSERT INTO caracteristica(id, datacriacao, dataedicao, version, codigo, descricao, expressaoregular, tamanho, autor_id, editor_id, dominiotipodado_id, dominiotiporestricao_id, chavedominio, orgao_id)
    VALUES (3, '2014-11-20', '2014-11-20', 0, 'CODNUMBER', 'NUMBER', '', null, 1, 1, 14, 9, 'tipoRestricao', 1);
INSERT INTO caracteristica(id, datacriacao, dataedicao, version, codigo, descricao, expressaoregular, tamanho, autor_id, editor_id, dominiotipodado_id, dominiotiporestricao_id, chavedominio, orgao_id)
    VALUES (4, '2014-11-20', '2014-11-20', 0, 'CODDECIMAL', 'DECIMAL', '', null, 1, 1, 15, 9, 'tipoRestricao', 1);
INSERT INTO caracteristica(id, datacriacao, dataedicao, version, codigo, descricao, expressaoregular, tamanho, autor_id, editor_id, dominiotipodado_id, dominiotiporestricao_id, chavedominio, orgao_id)
    VALUES (5, '2014-11-20', '2014-11-20', 0, 'CODDATA', 'DATA', '', 10, 1, 1, 55, 9, 'tipoRestricao', 1);
INSERT INTO caracteristica(id, datacriacao, dataedicao, version, codigo, descricao, expressaoregular, tamanho, autor_id, editor_id, dominiotipodado_id, dominiotiporestricao_id, chavedominio, orgao_id)
    VALUES (6, '2014-11-20', '2014-11-20', 0, 'CODTIPO_DOMINIO', 'TIPO_DOMINIO', '', null, 1, 1, 56, 9, 'tipoRestricao', 1);
INSERT INTO caracteristica(id, datacriacao, dataedicao, version, codigo, descricao, expressaoregular, tamanho, autor_id, editor_id, dominiotipodado_id, dominiotiporestricao_id, chavedominio, orgao_id)
    VALUES (7, '2014-11-20', '2014-11-20', 0, 'CODARQUIVO', 'ARQUIVO', '', 5, 1, 1, 57, 9, 'tipoRestricao', 1);

-- MATERIAL PERMANENTE
INSERT INTO material(id, datacriacao, dataedicao, version, descricao, dominiotipomaterial_id, orgao_id, dominiounidademedida_id, contacontabil_id, classificacaomaterial_id)
    VALUES (1, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 0, 'Material Permanente 1', 6, 1, 66, 1, 7);

-- Material Consumo
INSERT INTO material(id, datacriacao, dataedicao, version, descricao, dominiotipomaterial_id, orgao_id, dominiounidademedida_id, contacontabil_id)
    VALUES (2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 0, 'Material Consumo', 5, 1, 50, 1);

-- ESTRUTURA ORGANIZACIONAL FILHAS ORGAO
INSERT INTO estruturaorganizacional(id, classificacao, datainicio, possuibempatrimonial, dominiotipoestruturaorganizacional_id, localizacao_id, mapaorganizacional_id, orgao_id, codigo, almoxarifado, estruturaOrganizacionalParent_id)
	VALUES (3, 'classificação 1.1', '2015-01-01 09:26:25', true, 43, 1, 1, 1, 457, true, 1);
INSERT INTO estruturaorganizacional(id, classificacao, datainicio, possuibempatrimonial, dominiotipoestruturaorganizacional_id, localizacao_id, mapaorganizacional_id, orgao_id, codigo, almoxarifado, estruturaOrganizacionalParent_id)
	VALUES (4, 'classificação  1.2', '2015-01-01 09:26:25', true, 43, 1, 1, 1, 458, false, 1);
INSERT INTO estruturaorganizacional(id,  classificacao, datainicio, possuibempatrimonial, dominiotipoestruturaorganizacional_id, localizacao_id, mapaorganizacional_id, orgao_id, codigo, almoxarifado, estruturaOrganizacionalParent_id)
	VALUES (5, 'classificação  2.1', '2015-01-01 09:26:25', true, 43, 1, 1, 2, 459, false, 2);

-- CONFIGURAÇÃO ALMOXARIFADO
INSERT INTO configuracaoalmoxarifado(id, datacriacao, dataedicao, version, configuracao_id, estruturaOrganizacional_id)
	VALUES (1, TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), 0, 1, 3);

-- FUNCAO
INSERT INTO funcao(id, datacriacao, dataedicao, version, nome)
    VALUES (1, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), 0, 'Função 1');

INSERT INTO funcao(id, datacriacao, dataedicao, version, nome)
    VALUES (2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), 0, 'Função 2');

INSERT INTO funcao(id, datacriacao, dataedicao, version, nome)
    VALUES (3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), 0, 'Função 3');

-- CLASSE PARCEIRO

INSERT INTO classeparceiro(id, dominiotipoparceiro_id)
    VALUES (1, 19);

INSERT INTO classeparceiro(id, dominiotipoparceiro_id)
    VALUES (2, 17);

INSERT INTO classeparceiro(id, dominiotipoparceiro_id)
    VALUES (3, 16);

INSERT INTO classeparceiro(id, dominiotipoparceiro_id)
    VALUES (4, 18);

INSERT INTO classeparceiro(id, dominiotipoparceiro_id)
    VALUES (5, 16);

-- PESSOA
INSERT INTO pessoa(id, datacriacao, dataedicao, version, nome, dominio_pessoa, orgao_id)
    VALUES(1, '2015-01-01 09:26:25', '2015-01-01 09:26:25', 0, 'Colaborador', 22, 1);

-- PESSOA FISICA
INSERT INTO pessoafisica(cpf, datanascimento, nomemae, nomepai, numeropassaporte, rg, tituloeleitor, dominio_estado_civil, dominio_sexo, pessoa_id)
    VALUES ('12345678', '2015-01-01 09:26:25', 'teste', 'teste', '123456', '123456', '123456', 40, 38, 1);

-- PARCEIRO
INSERT INTO parceiro(id, datacriacao, dataedicao, version, codigo, autor_id, editor_id, classeparceiro_id, pessoa_id)
    VALUES (1, '2015-01-01 09:26:25', '2015-01-01 09:26:25', 0, 123, 1, 1, 1, 1);

-- PARCEIRO COLABORADOR
INSERT INTO parceiro_colaborador(id, estruturaorganizacional_id, funcao_id)
    VALUES (1, 1, 1);

-- PARCEIRO FORNECEDOR
INSERT INTO parceiro(id, datacriacao, dataedicao, version, codigo, autor_id, editor_id, classeparceiro_id, pessoa_id)
    VALUES (3, '2015-01-01 09:26:25', '2015-01-01 09:26:25', 0, 123, 1, 1, 3, 1);
    -- FORNECEDOR
INSERT INTO fornecedor(id, estrangeiro, comprasEletronicas)
    VALUES (3, false, true );

-- FORNCEDOR RAMO ATIVIDADE
INSERT INTO fornecedorramoatividade(id, datacriacao, dataedicao, version, grupofederalsupply_id, subgrupofederalsupply_id, fornecedor_id)
    VALUES (1, '2015-01-01 09:26:25', '2015-01-01 09:26:25', 0, 1, 2, 3);

-- PAGINA
INSERT INTO pagina (id, nome, pagina) VALUES (1, 'Home', '/cit-portal-web/html/home/home.html');
INSERT INTO pagina (id, nome, pagina) VALUES (2, 'Menu', '/cit-portal-web/html/menu/menu.html');
-- INSERT INTO pagina (id, nome, pagina) VALUES (3, 'Default template', '/cit-portal-web/html/defaultTemplate/defaultTemplate.html');
INSERT INTO pagina (id, nome, pagina) VALUES (4, 'Usuário', '/cit-portal-web/html/usuario/usuario.html');
INSERT INTO pagina (id, nome, pagina) VALUES (5, 'Notificação','/cit-portal-web/html/notificacao/notificacao.html');
INSERT INTO pagina (id, nome, pagina) VALUES (6, 'País', '/citgrp-patrimonio-web/html/pais/pais.html');
INSERT INTO pagina (id, nome, pagina) VALUES (7, 'Estado', '/citgrp-patrimonio-web/html/estado/estado.html');
INSERT INTO pagina (id, nome, pagina) VALUES (8, 'Arquivos padrão', '/cit-portal-web/html/defaultFile/defaultFile.html');
-- INSERT INTO pagina (id, nome, pagina) VALUES (9, 'Órgão', '/citgrp-patrimonio-web/html/orgao/orgao.html');
INSERT INTO pagina (id, nome, pagina) VALUES (10, 'Domínio', '/cit-portal-web/html/dominio/dominio.html');
INSERT INTO pagina (id, nome, pagina) VALUES (11, 'Mapa organizacional', '/citgrp-patrimonio-web/html/mapaOrganizacional/mapaOrganizacional.html');
INSERT INTO pagina (id, nome, pagina) VALUES (12, 'Classificação de materiais', '/citgrp-patrimonio-web/html/classificacaoMaterial/classificacaoMaterial.html');
INSERT INTO pagina (id, nome, pagina) VALUES (13, 'Entrada de bens', '/citgrp-patrimonio-web/html/entrada/entrada.html');
INSERT INTO pagina (id, nome, pagina) VALUES (14, 'Material', '/citgrp-patrimonio-web/html/material/material.html');
INSERT INTO pagina (id, nome, pagina) VALUES (15, 'Característica', '/citgrp-patrimonio-web/html/caracteristica/caracteristica.html');
INSERT INTO pagina (id, nome, pagina) VALUES (16, 'Transferência interna', '/citgrp-patrimonio-web/html/transferencia/transferencia.html');
INSERT INTO pagina (id, nome, pagina) VALUES (17, 'Mapa', '/cit-portal-web/html/mapa/mapa.html');
INSERT INTO pagina (id, nome, pagina) VALUES (18, 'Estrutura organizacional', '/citgrp-patrimonio-web/html/estruturaOrganizacional/estruturaOrganizacional.html');
INSERT INTO pagina (id, nome, pagina) VALUES (19, 'Pessoa', '/citgrp-patrimonio-web/html/pessoa/pessoa.html');
INSERT INTO pagina (id, nome, pagina) VALUES (20, 'Localização', '/citgrp-patrimonio-web/html/localizacao/localizacao.html');
INSERT INTO pagina (id, nome, pagina) VALUES (21, 'Adição a bem principal', '/citgrp-patrimonio-web/html/adicaoBemPrincipal/adicaoBemPrincipal.html');
INSERT INTO pagina (id, nome, pagina) VALUES (22, 'Região', '/citgrp-patrimonio-web/html/regiao/regiao.html');
INSERT INTO pagina (id, nome, pagina) VALUES (23, 'Cidade', '/citgrp-patrimonio-web/html/cidade/cidade.html');
INSERT INTO pagina (id, nome, pagina) VALUES (24, 'Bairro', '/citgrp-patrimonio-web/html/bairro/bairro.html');
INSERT INTO pagina (id, nome, pagina) VALUES (25, 'Endereço', '/citgrp-patrimonio-web/html/endereco/endereco.html');
INSERT INTO pagina (id, nome, pagina) VALUES (26, 'Comissão de inventário', '/citgrp-patrimonio-web/html/inventarioComissao/inventarioComissao.html');
INSERT INTO pagina (id, nome, pagina) VALUES (27, 'Definir dententor', '/citgrp-patrimonio-web/html/definicaoDetentor/definicaoDetentor.html');
INSERT INTO pagina (id, nome, pagina) VALUES (28, 'Baixa de bens', '/citgrp-patrimonio-web/html/baixa/baixa.html');
INSERT INTO pagina (id, nome, pagina) VALUES (29, 'Saída temporária', '/citgrp-patrimonio-web/html/saidaTemporaria/saidaTemporaria.html');
INSERT INTO pagina (id, nome, pagina) VALUES (30, 'Conta contábil', '/citgrp-patrimonio-web/html/contaContabil/contaContabil.html');
INSERT INTO pagina (id, nome, pagina) VALUES (31, 'Fechamento do mês de referência', '/citgrp-patrimonio-web/html/orgao/fechamentoMes.html');
INSERT INTO pagina (id, nome, pagina) VALUES (32, 'Alteração bem patrimonial', '/citgrp-patrimonio-web/html/alteracaoBemPatrimonial/alterarBemPatrimonialEdit.html');
INSERT INTO pagina (id, nome, pagina) VALUES (33, 'Depreciações', '/citgrp-patrimonio-web/html/depreciacao/depreciacao.html');
INSERT INTO pagina (id, nome, pagina) VALUES (34, 'Termo de responsabilidade', '/citgrp-patrimonio-web/html/termoResponsabilidade/termoResponsabilidade.html');
INSERT INTO pagina (id, nome, pagina) VALUES (35, 'Relatório de Movimentação de Bens', '/citgrp-patrimonio-web/html/contaContabilMovimento/contaContabilMovimento.html');
--INSERT INTO pagina (id, nome, pagina) VALUES (36, 'Access roles', '/cit-portal-web/html/accessRole/accessRole.html');
INSERT INTO pagina (id, nome, pagina) VALUES (37, 'Histórico bem patrimonial', '/citgrp-patrimonio-web/html/historicoBemPatrimonial/historicoBemPatrimonial.html');
INSERT INTO pagina (id, nome, pagina) VALUES (38, 'Configuração do Sistema', '/cit-portal-web/html/configuracaoSistema/configuracaoSistema.html');
INSERT INTO pagina (id, nome, pagina) VALUES (39, 'Grupo', '/cit-portal-web/html/grupo/grupo.html');
INSERT INTO pagina (id, nome, pagina) VALUES (40, 'Painel', '/cit-portal-web/html/painel/painel.html');
INSERT INTO pagina (id, nome, pagina) VALUES (41, 'Widget', '/cit-portal-web/html/widget/widget.html');
INSERT INTO pagina (id, nome, pagina) VALUES (42, 'Widget Item', '/cit-portal-web/html/widgetItem/widgetItem.html');

INSERT INTO pagina (id, nome, pagina) VALUES (43, 'Módulos', '/cit-portal-web/html/modulo/modulo.html');
INSERT INTO pagina (id, nome, pagina) VALUES (44, 'DashBoard', '/cit-portal-web/html/dashboard-portal.html');

INSERT INTO pagina (id, nome, pagina) VALUES (45, 'Internacionalização', '/cit-portal-web/html/internacionalizacao/internacionalizacao.html');

INSERT INTO pagina (id, nome, pagina) VALUES (46, 'Regra Evento', '/cit-contratos-web/html/regraEvento/regraEvento.html');
INSERT INTO pagina (id, nome, pagina) VALUES (47, 'Monitor', '/cit-contratos-web/html/monitor/monitor.html');
INSERT INTO pagina (id, nome, pagina) VALUES (48, 'Apostilamento', '/cit-contratos-web/html/apostilamento/apostilamento.html');
INSERT INTO pagina (id, nome, pagina) VALUES (49, 'Penalidade', '/cit-contratos-web/html/penalidade/penalidade.html');
INSERT INTO pagina (id, nome, pagina) VALUES (50, 'Contrato', '/cit-contratos-web/html/contrato/contrato.html');
INSERT INTO pagina (id, nome, pagina) VALUES (51, 'Visão Contrato', '/cit-contratos-web/html/contrato/visaoContrato.html');

INSERT INTO pagina (id, nome, pagina) VALUES (100 , 'Manutenção de fluxos', '/cit-esi-web/html/fluxo/fluxo.html');
INSERT INTO pagina (id, nome, pagina) VALUES (101, 'Gerenciamento de tarefas', '/cit-esi-web/html/businessProcess/businessTaskManagement.html');
INSERT INTO pagina (id, nome, pagina) VALUES (103, 'Jornada de Trabalho', '/cit-esi-web/html/workDay/workDay.html');
INSERT INTO pagina (id, nome, pagina) VALUES (104, 'Calendário', '/cit-esi-web/html/workCalendar/workCalendar.html');
INSERT INTO pagina (id, nome, pagina) VALUES (105 , 'Manutenção de Categorias', '/cit-esi-web/html/businessProcess/businessProcessCategory.html');
INSERT INTO pagina (id, nome, pagina) VALUES (106 , 'Manutenção de Processos', '/cit-esi-web/html/businessProcess/businessProcess.html');

INSERT INTO pagina (id, nome, pagina) VALUES (107, 'Inventário', '/citgrp-patrimonio-web/html/inventario/inventario.html');
INSERT INTO pagina (id, nome, pagina) VALUES (108, 'Relatório de inventário', '/citgrp-patrimonio-web/html/inventario/relatorioInventario.html');

-- liberado a partir do 109

-- MENUS
INSERT INTO public.menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id)
    VALUES
 (1, 'Dashboard', null, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 0, 1, 44);

INSERT INTO public.menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, cor, classePagina, cssMenu, cssMenuOpacity)
    VALUES
  (2, 'Administração', null, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, 1, '#62a0d2', 'mod-blue', '.mod-blue .bar-buttons-action:before {	background: rgb(255,255,255);	background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(98,160,210,0.5) 100%);	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(98,160,210,0.5)));	background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr="#ffffff", endColorstr="#62a0d2",GradientType=0 );}.modal.modal-buttons-top.mod-blue .modal-dialog .modal-header {	background: rgb(255,255,255);	background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(98,160,210,0.5) 100%);	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(98,160,210,0.5)));	background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr="#ffffff", endColorstr="#62a0d2",GradientType=0 );}#workspace-nav li.mod-blue a {	border-top-color: #62a0d2;}', 5);

INSERT INTO public.menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, cor, classePagina, cssMenu, cssMenuOpacity)
    VALUES
  (3, 'Patrimônio', null, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 2, 1, '#88b67f', 'mod-green', '.mod-green .bar-buttons-action {	background: rgb(255,255,255);	background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(136,182,127,0.5) 100%);	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(136,182,127,0.5)));	background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(136,182,127,0.5) 100%);	background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(136,182,127,0.5) 100%);	background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(136,182,127,0.5) 100%);	background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(136,182,127,0.5) 100%);	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr="#ffffff", endColorstr="#88b67f",GradientType=0 );}.modal.modal-buttons-top.mod-green .modal-dialog .modal-header {	background: rgb(255,255,255);	background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(136,182,127,0.5) 100%);	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(136,182,127,0.5)));	background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(136,182,127,0.5) 100%);	background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(136,182,127,0.5) 100%);	background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(136,182,127,0.5) 100%);	background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(136,182,127,0.5) 100%);	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr="#ffffff", endColorstr="#88b67f",GradientType=0 );}#workspace-nav li.mod-green a {	border-top-color: #88b67f;}', 5);

--INSERT INTO public.menu
 -- (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, cor)
  --  VALUES
  --(4, 'Sistemas interno', null, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 3, 1, '#b7b87d');


INSERT INTO public.menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
    VALUES
  (5, 'Menu', 2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 0, 1, 2, 'mod-blue', '#62a0d2');
-- INSERT INTO public.menu
  -- (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
    -- VALUES
  -- (6, 'Template padrão', 2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, 1, 3, 'mod-blue', '#62a0d2');
INSERT INTO public.menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
    VALUES
  (7, 'Usuário', 2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 2, 1, 4, 'mod-blue', '#62a0d2');
INSERT INTO public.menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
    VALUES
  (8, 'Notificações', 2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 3, 1, 5, 'mod-blue', '#62a0d2');
INSERT INTO public.menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
    VALUES
  (11, 'Arquivos padrão', 2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 4, 1, 8, 'mod-blue', '#62a0d2');
INSERT INTO public.menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
    VALUES
  (13, 'Domínio', 2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 5, 1, 10, 'mod-blue', '#62a0d2');

INSERT INTO public.menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
    VALUES
  (46, 'Módulos', 2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 6, 1, 43, 'mod-blue', '#62a0d2');

INSERT INTO public.menu (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
	VALUES (47, 'Internacionalizacao', 2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 7, 1, 45, 'mod-blue', '#62a0d2');

INSERT INTO public.menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
    VALUES
  (9, 'País', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 0, 1, 6, 'mod-green', '#88b67f');
INSERT INTO public.menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
    VALUES
  (10, 'Estado', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, 1, 7, 'mod-green', '#88b67f');

--INSERT INTO public.menu
--  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
--    VALUES
--  (12, 'Órgão', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 0, 1, 9, 'mod-green', '#88b67f');

INSERT INTO public.menu (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (14, 'Mapa organizacional', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 2, 1, 11, 'mod-green', '#88b67f');

INSERT INTO public.menu (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (15, 'Classificação de materiais', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 3, 1, 12, 'mod-green', '#88b67f');

INSERT INTO public.menu (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (16, 'Entrada de bens', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 4, 1, 13, 'mod-green', '#88b67f');

INSERT INTO public.menu (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (17, 'Material', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 5, 1, 14, 'mod-green', '#88b67f');

  INSERT INTO public.menu (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (18, 'Característica', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 6, 1, 15, 'mod-green', '#88b67f');

INSERT INTO public.menu (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (19, 'Transferência interna', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 7, 1, 16, 'mod-green', '#88b67f');

-- INSERT INTO public.menu
  -- (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
    -- VALUES
  -- (20, 'Mapa', 2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 6, 1, 17, 'mod-blue', '#62a0d2');

INSERT INTO public.menu (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (21, 'Estruturas organizacionais', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 8, 1, 18, 'mod-green', '#88b67f');

INSERT INTO public.menu (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (22, 'Pessoa', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 9, 1, 19, 'mod-green', '#88b67f');

INSERT INTO public.menu (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
    (23, 'Localização', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 10, 1, 20, 'mod-green', '#88b67f');

INSERT INTO public.menu (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
    (24, 'Adição a bem principal', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 11, 1, 21, 'mod-green', '#88b67f');

INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (25, 'Região', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 12, 1, 22, 'mod-green', '#88b67f');

INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (26, 'Cidade', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 13, 1, 23, 'mod-green', '#88b67f');

INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (27, 'Bairro', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 14, 1, 24, 'mod-green', '#88b67f');

INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (28, 'Endereço', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 15, 1, 25, 'mod-green', '#88b67f');

 INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (29, 'Comissão de inventário', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 0, 1, 26, 'mod-green', '#88b67f');

INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (30, 'Definir detentor', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 16, 1, 27, 'mod-green', '#88b67f');

INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (31, 'Baixa de bens', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 17, 1, 28, 'mod-green', '#88b67f');

INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (32, 'Saída temporária', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 18, 1, 29, 'mod-green', '#88b67f');

INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (33, 'Conta contábil', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 19, 1, 30, 'mod-green', '#88b67f');

INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
	(34, 'Fechamento do mês de referência', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 20, 1, 31, 'mod-green', '#88b67f');

INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (35, 'Alteração bem patrimonial', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 21, 1, 32, 'mod-green', '#88b67f');

INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (36, 'Depreciações', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 22, 1, 33, 'mod-green', '#88b67f');

INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (37, 'Termo de responsabilidade', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 23, 1, 34, 'mod-green', '#88b67f');

INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (38, 'Relatório de Movimentação de Bens', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 24, 1, 35, 'mod-green', '#88b67f');

--INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
 -- (39, 'Access roles', 2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 6, 1, 36, 'mod-green', '#88b67f');

INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (40, 'Histórico bem patrimonial', 3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 25, 1, 37, 'mod-green', '#88b67f');

INSERT INTO public.menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
    VALUES
  (41, 'Configuração do Sistema', 2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 6, 1, 38, 'mod-blue', '#62a0d2');

INSERT INTO public.menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
    VALUES
  (42, 'Grupo', 2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 6, 1, 39, 'mod-blue', '#62a0d2');

INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (43, 'Painel', 2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 25, 1, 40, 'mod-green', '#88b67f');

INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (44, 'Widget', 2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 26, 1, 41, 'mod-green', '#88b67f');

INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (45, 'Widget Item', 2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 27, 1, 42, 'mod-green', '#88b67f');

INSERT INTO public.menu
  (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, cor, classePagina, cssMenu, cssMenuOpacity)
    VALUES
  (48, 'Contratos', null, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 3, 1, '#00008B', 'mod-blue-dark', '.mod-blue-dark .bar-buttons-action:before {	background: rgb(255,255,255);	background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(0,0,139,0.3) 100%);	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(0,0,139,0.3)));	background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr="#ffffff", endColorstr="#00008B",GradientType=0 );}.modal.modal-buttons-top.mod-blue-dark .modal-dialog .modal-header {	background: rgb(255,255,255);	background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(0,0,139,0.3) 100%);	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(0,0,139,0.3)));	background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr="#ffffff", endColorstr="#00008B",GradientType=0 );}#workspace-nav li.mod-blue-dark a {	border-top-color: #00008B;}', 3);

INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (49, 'Regra Evento', 48, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 0, 1, 46, 'mod-blue-dark', '#00008B');

INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (50, 'Monitor', 48, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 0, 1, 47, 'mod-blue-dark', '#00008B');

  INSERT INTO public.menu (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
VALUES (51, 'Apostilamento', 48, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 7, 1, 48, 'mod-blue-dark', '#00008B');

INSERT INTO public.menu (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
VALUES (52, 'Penalidade', 48, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 7, 1, 49, 'mod-blue-dark', '#00008B');

INSERT INTO public.menu (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
VALUES (53, 'Contrato', 48, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 7, 1, 50, 'mod-blue-dark', '#00008B');

INSERT INTO public.menu (id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor)
VALUES (60, 'Visão Contrato', 48, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 7, 1, 51, 'mod-blue-dark', '#00008B');


INSERT INTO public.menu
	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classepagina, cor, cssMenu, cssMenuOpacity)
		VALUES
	(54, 'BPE/ESI', null, TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 4, 1, null, 'mod-red-dark', '#8B0000', '.mod-red-dark .bar-buttons-action:before {	background: rgb(255,255,255);	background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(139,0,0,0.3) 100%);	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(139,0,0,0.3)));	background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(139,0,0,0.3) 100%);	background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(139,0,0,0.3) 100%);	background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(139,0,0,0.3) 100%);	background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(139,0,0,0.3) 100%);	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr="#ffffff", endColorstr="#8B0000",GradientType=0 );}.modal.modal-buttons-top.mod-red-dark .modal-dialog .modal-header {	background: rgb(255,255,255);	background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(139,0,0,0.3) 100%);	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(139,0,0,0.3)));	background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(139,0,0,0.3) 100%);	background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(139,0,0,0.3) 100%);	background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(139,0,0,0.3) 100%);	background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(139,0,0,0.3) 100%);	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr="#ffffff", endColorstr="#8B0000",GradientType=0 );}#workspace-nav li.mod-red-dark a {	border-top-color: #8B0000;}', 3);
INSERT INTO public.menu
	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classepagina, cor)
		VALUES
	(55, 'Manutenção de fluxos', 54, TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 0, 1, 100, 'mod-red-dark', '#8B0000');

INSERT INTO public.menu
	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id)
		VALUES
	(58, 'Jornada de Trabalho', 2, TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 998, 999, 103);
INSERT INTO public.menu
	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id)
		VALUES
	(59, 'Calendário', 2, TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 999, 999, 104);

INSERT INTO public.menu
	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classepagina, cor)
		VALUES
	(61, 'Processos de Negócio', 54, TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 9999, 1, null, 'mod-red-dark', '#8B0000');
INSERT INTO public.menu
	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classepagina, cor)
		VALUES
	(62, 'Manutenção de Categorias', 61, TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 0, 1, 105, 'mod-red-dark', '#8B0000');
INSERT INTO public.menu
	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classepagina, cor)
		VALUES
	(63, 'Manutenção de Processos', 61, TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, 1, 106, 'mod-red-dark', '#8B0000');
INSERT INTO public.menu
	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classepagina, cor)
		VALUES
	(56, 'Gerenciamento de tarefas', 61, TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 0, 1, 101, 'mod-red-dark', '#8B0000');


INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (64, 'Inventário', 3, TO_DATE('2015-03-30 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-30 09:26:25','YYYY-MM-DD HH24:MI:SS'), 28, 1, 107, 'mod-green', '#88b67f');
INSERT INTO public.menu	(id, nome, parent_id, dataCriacao, dataEdicao, ordem, version, pagina_id, classePagina, cor) VALUES
  (65, 'Relatório de inventário', 3, TO_DATE('2015-04-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-04-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 29, 1, 108, 'mod-green', '#88b67f');


-- liberador a partir do 66

update public.menu set ativo = true;

-- FILES DO MENU
 INSERT INTO menufile(
       datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      'assets/js/angular/controller/DashboardController.js', 2, 1);
INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      'assets/js/angular/controller/MenuController.js', 2, 5);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      'assets/js/angular/repository/MenuRepository.js', 2, 5);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      'assets/js/angular/controller/UsuarioController.js', 2, 7);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      'assets/js/angular/controller/UsuarioListController.js', 2, 7);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      'assets/js/angular/repository/UsuarioRepository.js', 2, 7);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      'assets/js/angular/repository/EstruturaOrgaoRepository.js', 2, 7);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      'assets/js/angular/repository/OrgaoItemRepository.js', 2, 7);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      'assets/js/angular/repository/FiltroRepository.js', 2, 7);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      'assets/js/angular/repository/FavoritoRepository.js', 2, 7);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      'assets/js/angular/repository/PaginaUsuarioRepository.js', 2, 7);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      'assets/js/angular/controller/DefaultFileController.js', 2, 11);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      'assets/js/angular/repository/DefaultFileRepository.js', 2, 11);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      'assets/js/angular/controller/NotificacaoController.js', 2, 8);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      'assets/js/angular/controller/NotificacaoListController.js', 2, 8);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      'assets/js/angular/repository/NotificacaoRepository.js', 2, 8);

-- INSERT INTO menufile(
--            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
--    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
--      '/citgrp-patrimonio-web/angular/controller/OrgaoController.js', 2, 12);

-- INSERT INTO menufile(
--            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
--    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
--      '/citgrp-patrimonio-web/angular/controller/OrgaoListController.js', 2, 12);

-- INSERT INTO menufile(
--            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
--    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
--      '/citgrp-patrimonio-web/angular/repository/OrgaoRepository.js', 2, 12);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      'assets/js/angular/controller/DominioController.js', 2, 13);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      'assets/js/angular/controller/DominioListController.js', 2, 13);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      'assets/js/angular/repository/DominioRepository.js', 2, 13);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/controller/MapaOrganizacionalController.js', 2, 14);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/controller/MapaOrganizacionalListController.js', 2, 14);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/repository/MapaOrganizacionalRepository.js', 2, 14);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/controller/ClassificacaoMaterialController.js', 2, 15);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/repository/ClassificacaoMaterialRepository.js', 2, 15);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/controller/EntradaController.js', 2, 16);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/controller/EntradaListController.js', 2, 16);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/repository/FuncaoRepository.js', 2, 16);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/repository/ObservacaoRepository.js', 2, 16);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/repository/DocumentoRepository.js', 2, 16);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/directive/BuscaBensPatrimoniaisDirective.js', 2, 16);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/directive/AssociarDocumentoDirective.js', 2, 16);


INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/repository/EntradaRepository.js', 2, 16);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/repository/AnexoRepository.js', 2, 16);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/controller/MaterialController.js', 2, 17);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/controller/MaterialListController.js', 2, 17);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/repository/MaterialRepository.js', 2, 17);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/repository/MaterialConsumoRepository.js', 2, 17);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/repository/MaterialImagemRepository.js', 2, 17);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/repository/ContaContabilRepository.js', 2, 15);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/controller/CaracteristicaController.js', 2, 18);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/controller/CaracteristicaListController.js', 2, 18);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/repository/CaracteristicaRepository.js', 2, 18);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/controller/TransferenciaController.js', 2, 19);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/repository/TransferenciaRepository.js', 2, 19);

-- INSERT INTO menufile(
            -- datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    -- VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      -- 'assets/js/angular/controller/MapaController.js', 2, 20);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/controller/EstruturaOrganizacionalController.js', 2, 21);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/citgrp-patrimonio-web/angular/controller/PessoaListController.js', 2, 22);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/citgrp-patrimonio-web/angular/controller/PessoaController.js', 2, 22);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/citgrp-patrimonio-web/angular/repository/PessoaRepository.js', 2, 22);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/citgrp-patrimonio-web/angular/repository/TelefoneRepository.js', 2, 22);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/repository/EstruturaOrganizacionalRepository.js', 2, 21);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/repository/EstruturaOrganizacionalResponsavelRepository.js', 2, 21);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/repository/FornecedorRepository.js', 2, 21);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/repository/BemPatrimonialRepository.js', 2, 21);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/LocalizacaoController.js', 2, 23);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/LocalizacaoListController.js', 2, 23);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/repository/LocalizacaoRepository.js', 2, 23);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      '/citgrp-patrimonio-web/angular/repository/MaterialCaracteristicaRepository.js', 2, 15);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/AdicaoBemPrincipalController.js', 2, 24);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/repository/AdicaoBemPrincipalRepository.js', 2, 24);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/repository/AdicaoBemPrincipalItemRepository.js', 2, 24);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/PaisController.js', 2, 9);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/PaisListController.js', 2, 9);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/repository/PaisRepository.js', 2, 9);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/EstadoController.js', 2, 10);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/EstadoListController.js', 2, 10);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/repository/EstadoRepository.js', 2, 10);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/RegiaoController.js', 2, 25);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/RegiaoListController.js', 2, 25);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/repository/RegiaoRepository.js', 2, 25);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/CidadeController.js', 2, 26);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/CidadeListController.js', 2, 26);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/repository/CidadeRepository.js', 2, 26);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/BairroController.js', 2, 27);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/BairroListController.js', 2, 27);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/repository/BairroRepository.js', 2, 27);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/EnderecoController.js', 2, 28);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/EnderecoListController.js', 2, 28);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
        'assets/js/angular/repository/EnderecoRepository.js', 2, 28);

INSERT INTO menufile(
             datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
     VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
         '/citgrp-patrimonio-web/angular/controller/InventarioComissaoController.js', 2, 29);

 INSERT INTO menufile(
             datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
     VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
         '/citgrp-patrimonio-web/angular/controller/InventarioComissaoListController.js', 2, 29);

 INSERT INTO menufile(
             datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
     VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
         '/citgrp-patrimonio-web/angular/repository/InventarioComissaoRepository.js', 2, 29);

 INSERT INTO menufile(
             datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
     VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
         '/citgrp-patrimonio-web/angular/repository/InventarioComissaoIntegranteRepository.js', 2, 29);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/DefinicaoDetentorController.js', 2, 30);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/repository/DefinicaoDetentorRepository.js', 2, 30);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/repository/DefinicaoDetentorItemRepository.js', 2, 30);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/repository/BaixaRepository.js', 2, 31);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/repository/BaixaItemRepository.js', 2, 31);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/BaixaController.js', 2, 31);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/BaixaListController.js', 2, 31);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/SaidaTemporariaController.js', 2, 32);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/SaidaTemporariaItemController.js', 2, 32);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/SaidaTemporariaItemListController.js', 2, 32);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/repository/SaidaTemporariaRepository.js', 2, 32);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/repository/SaidaTemporariaItemRepository.js', 2, 32);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/repository/ContaContabilRepository.js', 2, 33);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/ContaContabilController.js', 2, 33);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/ContaContabilListController.js', 2, 33);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/MesReferenciaController.js', 2, 34);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/AlteracaoBemPatrimonialController.js', 2, 35);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/repository/DepreciacaoRepository.js', 2, 36);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/DepreciacaoController.js', 2, 36);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
        '/citgrp-patrimonio-web/angular/controller/DepreciacaoListController.js', 2, 36);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/citgrp-patrimonio-web/angular/repository/TermoResponsabilidadeRepository.js', 2, 37);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/citgrp-patrimonio-web/angular/controller/TermoResponsabilidadeController.js', 2, 37);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/citgrp-patrimonio-web/angular/controller/TermoResponsabilidadeListController.js', 2, 37);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/citgrp-patrimonio-web/angular/repository/ContaContabilMovimentoRepository.js', 2, 38);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/citgrp-patrimonio-web/angular/controller/ContaContabilMovimentoController.js', 2, 38);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/citgrp-patrimonio-web/angular/controller/ContaContabilMovimentoListController.js', 2, 38);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    'assets/js/angular/repository/AccessRoleRepository.js', 2, 38);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    'assets/js/angular/controller/AccessRoleController.js', 2, 38);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    'assets/js/angular/controller/AccessRoleListController.js', 2, 38);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/citgrp-patrimonio-web/angular/controller/HistoricoBemPatrimonialListController.js', 2, 40);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/citgrp-patrimonio-web/angular/repository/HistoricoBemPatrimonialRepository.js', 2, 40);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    'assets/js/angular/controller/ConfiguracaoSistemaController.js', 2, 41);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    'assets/js/angular/repository/ConfiguracaoSistemaRepository.js', 2, 41);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
'assets/js/angular/controller/GrupoController.js', 2, 42);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
'assets/js/angular/controller/GrupoListController.js', 2, 42);

 INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
'assets/js/angular/repository/GrupoRepository.js', 2, 42);

 INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
'assets/js/angular/repository/PrivilegioRepository.js', 2, 42);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
'assets/js/angular/controller/PainelListController.js', 2, 43);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
'assets/js/angular/controller/PainelController.js', 2, 43);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
'assets/js/angular/repository/PainelRepository.js', 2, 43);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
'assets/js/angular/controller/WidgetListController.js', 2, 44);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
'assets/js/angular/controller/WidgetController.js', 2, 44);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
'assets/js/angular/repository/WidgetRepository.js', 2, 44);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
'assets/js/angular/repository/WidgetParametroRepository.js', 2, 44);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
'assets/js/angular/controller/WidgetItemListController.js', 2, 45);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
'assets/js/angular/controller/WidgetItemController.js', 2, 45);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
'assets/js/angular/repository/WidgetItemRepository.js', 2, 45);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      'assets/js/angular/controller/ModuloController.js', 2, 46);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      'assets/js/angular/controller/ModuloListController.js', 2, 46);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
      'assets/js/angular/repository/ModuloRepository.js', 2, 46);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
	VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, 'assets/js/angular/controller/InternacionalizacaoController.js', 2, 47);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
	VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, 'assets/js/angular/controller/InternacionalizacaoListController.js', 2, 47);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
	VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, 'assets/js/angular/repository/InternacionalizacaoRepository.js', 2, 47);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/cit-contratos-web/angular/controller/RegraEventoListController.js', 2, 47);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/cit-contratos-web/angular/controller/RegraEventoController.js', 2, 47);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/cit-contratos-web/angular/repository/RegraEventoRepository.js', 2, 47);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/cit-contratos-web/angular/controller/MonitorController.js', 2, 50);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/cit-contratos-web/angular/controller/MonitorListController.js', 2, 50);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/cit-contratos-web/angular/repository/MonitorRepository.js', 2, 50);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/cit-contratos-web/angular/repository/QuestionarioRepository.js', 2, 50);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/cit-contratos-web/angular/repository/MonitorPredecessoraRepository.js', 2, 50);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/cit-contratos-web/angular/repository/MonitorResponsavelRepository.js', 2, 50);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/cit-contratos-web/angular/repository/FluxoTrabalhoRepository.js', 2, 50);

  INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/cit-contratos-web/angular/repository/ProcessoNegocioRepository.js', 2, 50);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/cit-contratos-web/angular/repository/NivelAutoridadeRepository.js', 2, 50);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/cit-contratos-web/angular/repository/ModeloEmailRepository.js', 2, 50);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/cit-contratos-web/angular/repository/MonitorAcaoDecorrenteRepository.js', 2, 50);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/cit-contratos-web/angular/repository/MonitorAgendamentoRepository.js', 2, 50);

INSERT INTO menufile(
      datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
  VALUES (TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-12-29 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
    '/cit-contratos-web/angular/repository/MonitorNotificacaoRepository.js', 2, 50);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, '/cit-contratos-web/angular/controller/ApostilamentoController.js', 2, 51);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, '/cit-contratos-web/angular/controller/ApostilamentoListController.js', 2, 51);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, '/cit-contratos-web/angular/repository/ApostilamentoRepository.js', 2, 51);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, '/cit-contratos-web/angular/controller/PenalidadeController.js', 2, 52);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, '/cit-contratos-web/angular/controller/PenalidadeListController.js', 2, 52);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, '/cit-contratos-web/angular/repository/PenalidadeRepository.js', 2, 52);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, '/cit-contratos-web/angular/controller/ContratoController.js', 2, 54);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, '/cit-contratos-web/angular/controller/ContratoListController.js', 2, 53);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, '/cit-contratos-web/angular/repository/ContratoRepository.js', 2, 53);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, '/cit-contratos-web/angular/controller/ContratoEquipeGestoraController.js', 2, 53);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, '/cit-contratos-web/angular/repository/ContratoEquipeGestoraRepository.js', 2, 53);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, '/cit-contratos-web/angular/controller/ContratoTermoAditivoController.js', 2, 53);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, '/cit-contratos-web/angular/repository/ContratoTermoAditivoRepository.js', 2, 53);

INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, '/cit-contratos-web/angular/repository/GarantiaRepository.js', 2, 53);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 103,
	    '/cit-esi-web/angular/controller/WorkDayController.js', 2, 58);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 103,
	    '/cit-esi-web/angular/controller/WorkDayListController.js', 2, 58);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 103,
	    '/cit-esi-web/angular/repository/WorkDayRepository.js', 2, 58);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 104,
	    '/cit-esi-web/angular/controller/WorkCalendarController.js', 2, 59);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 104,
	    '/cit-esi-web/angular/controller/WorkCalendarListController.js', 2, 59);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 104,
	    '/cit-esi-web/angular/repository/WorkCalendarRepository.js', 2, 59);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/controller/FluxoController.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/controller/FluxoListController.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/controller/BusinessTaskController.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/utils/DesenhoFluxo.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/utils/RuntimeEnvironmentInput.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/utils/RuntimeEnvironmentOutput.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/utils/RuntimeManagerUtils.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/controller/DomainController.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/repository/DomainRepository.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/repository/GenericRepository.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/repository/FlowRepository.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/repository/RuntimeManagerRepository.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/repository/FlowVariableRepository.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/repository/FlowStatusRepository.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/repository/FlowElementVariableRepository.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/directive/ExecucaoFluxoDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/directive/StartBusinessProcessDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/directive/VisualizacaoTarefaFluxoDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/directive/ExecucaoTarefaDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/repository/FlowElementActorRepository.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/directive/FormElementoFluxoDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/directive/ImgElementoFluxoDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/directive/InterfaceUsuarioDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/directive/ExpressaoDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/directive/ExpressaoElementoDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/directive/SituacaoElementoFluxoDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/directive/VariavelElementoFluxoDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/directive/AtorElementoFluxoDirective.js', 2, 54);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1,
	    '/cit-esi-web/angular/filter/DomainFilter.js', 2, 54);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 104,
	    '/cit-esi-web/angular/controller/BusinessProcessCategoryController.js', 2, 61);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 104,
	    '/cit-esi-web/angular/repository/BusinessProcessCategoryRepository.js', 2, 61);

INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 105,
	    '/cit-esi-web/angular/controller/BusinessProcessListController.js', 2, 61);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 105,
	    '/cit-esi-web/angular/controller/BusinessProcessController.js', 2, 61);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 105,
	    '/cit-esi-web/angular/repository/BusinessProcessRepository.js', 2, 61);
INSERT INTO menufile(
            datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), 104,
	    '/cit-esi-web/angular/repository/SecurityRepository.js', 2, 61);

-- INVENTARIO
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
     VALUES (TO_DATE('2015-03-30 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-30 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
         '/citgrp-patrimonio-web/angular/controller/InventarioController.js', 2, 64);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
     VALUES (TO_DATE('2015-03-30 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-30 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
         '/citgrp-patrimonio-web/angular/controller/InventarioListController.js', 2, 64);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
     VALUES (TO_DATE('2015-03-30 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-30 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
         '/citgrp-patrimonio-web/angular/repository/InventarioRepository.js', 2, 64);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
     VALUES (TO_DATE('2015-03-30 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-03-30 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
         '/citgrp-patrimonio-web/angular/repository/DadosBemPatrimonialRepository.js', 2, 64);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
     VALUES (TO_DATE('2015-04-01 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-04-01 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
         '/citgrp-patrimonio-web/angular/repository/InventarioEstruturaOrganizacionalRepository.js', 2, 64);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
     VALUES (TO_DATE('2015-04-07 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-04-07 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
         '/citgrp-patrimonio-web/angular/repository/InventarioBemPatrimonialRepository.js', 2, 64);
INSERT INTO menufile(datacriacao, dataedicao, version, caminho, dominiomenufile_id, menu_id)
     VALUES (TO_DATE('2015-05-04 11:16:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-05-04 11:16:25','YYYY-MM-DD HH24:MI:SS'), 1,
         '/citgrp-patrimonio-web/angular/repository/DominioInconsistenciaRepository.js', 2, 64);


-- ALTER SEQUENCE
ALTER SEQUENCE seguranca_usuario_id_seq RESTART WITH 500;
ALTER SEQUENCE seguranca_usuario_privilegio_id_seq RESTART WITH 500;
ALTER SEQUENCE menu_id_seq RESTART WITH 500;
ALTER SEQUENCE pagina_id_seq RESTART WITH 500;
ALTER SEQUENCE dominio_id_seq RESTART WITH 500;
ALTER SEQUENCE menufile_id_seq RESTART WITH 500;
ALTER SEQUENCE endereco_id_seq RESTART WITH 500;
ALTER SEQUENCE cidade_id_seq RESTART WITH 500;
ALTER SEQUENCE pais_id_seq RESTART WITH 500;
ALTER SEQUENCE bairro_id_seq RESTART WITH 500;
ALTER SEQUENCE regiao_id_seq RESTART WITH 500;
ALTER SEQUENCE caracteristica_id_seq RESTART WITH 500;
ALTER SEQUENCE classificacaomaterial_id_seq RESTART WITH 500;
ALTER SEQUENCE material_id_seq RESTART WITH 500;
ALTER SEQUENCE localizacao_id_seq RESTART WITH 500;
ALTER SEQUENCE regiao_id_seq RESTART WITH 500;
ALTER SEQUENCE estado_id_seq RESTART WITH 500;
ALTER SEQUENCE pessoa_id_seq RESTART WITH 500;
ALTER SEQUENCE parceiro_id_seq RESTART WITH 500;
ALTER SEQUENCE estruturaorgao_id_seq RESTART WITH 500;
ALTER SEQUENCE fornecedorramoatividade_id_seq RESTART WITH 500;
ALTER SEQUENCE contacontabil_id_seq RESTART WITH 500;
ALTER SEQUENCE termoresponsabilidade_id_seq RESTART WITH 500;
ALTER SEQUENCE configuracao_id_seq RESTART WITH 500;
ALTER SEQUENCE configuracaoalmoxarifado_id_seq RESTART WITH 500;
ALTER SEQUENCE inventariocomissaointegrante_id_seq RESTART WITH 500;
ALTER SEQUENCE inventariocomissaointegrante_id_seq RESTART WITH 500;
ALTER SEQUENCE mapaorganizacional_id_seq RESTART WITH 500;
ALTER SEQUENCE contacontabilsaldo_id_seq RESTART WITH 500;
ALTER SEQUENCE orgaoitem_id_seq RESTART WITH 500;
ALTER SEQUENCE contacontabilconfiguracaoreferencia_id_seq RESTART WITH 500;
ALTER SEQUENCE grupo_id_seq RESTART WITH 500;
ALTER SEQUENCE painel_id_seq RESTART WITH 500;

-- Pessoa
INSERT INTO pessoa(id, datacriacao, dataedicao, version, nome, dominio_pessoa, orgao_id)
    VALUES(2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), 0, 'Pessoa 1', 22, 1);

-- PESSOA JURIDICA
INSERT INTO pessoajuridica(cnpj, filantropico, filial, impostosimples, inscricaoestadual, inscricaomunicipal, nomefantasia, razaosocial, site, dominio_abrangencia, dominio_porte, pessoa_id)
    VALUES ('12345678', true, true, true, 'teste', 'teste', 'teste', 'teste', 'teste', 46, 47, 2);


INSERT INTO pessoa(id, datacriacao, dataedicao, version, nome, dominio_pessoa, orgao_id)
    VALUES(3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), 0, 'Pessoa 2', 22, 1);

INSERT INTO pessoa(id, datacriacao, dataedicao, version, nome, dominio_pessoa, orgao_id)
    VALUES(4, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), 0, 'Pessoa 3', 22, 1);



-- DEFAULT FILES
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/css/bootstrap.min.css', 1, 0);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/css/select.min.css', 1, 1);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/css/select2.min.css', 1, 2);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/css/angular-growl.min.css', 1, 3);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/css/font-awesome.min.css', 1, 4);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/css/angular-ui-tree.min.css', 1, 5);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/css/textAngular.css', 1, 6);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/css/bootstrap-layout.css', 1, 7);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/css/layout.css', 1, 8);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/jquery.min.js', 2, 18);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/functions.js', 2, 19);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/bigInteger.js', 2, 20);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/1.3.0/angular.min.js', 2, 21);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/1.3.0/i18n/angular-locale_pt-br.js', 2, 22);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/1.3.0/angular-cookies.min.js', 2, 23);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/1.3.0/angular-route.min.js', 2, 24);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/1.3.0/angular-sanitize.min.js', 2, 25);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/1.3.0/angular-animate.min.js', 2, 26);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/translate/angular-translate.js', 2, 27);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/translate/angular-translate-storage-cookie.js', 2, 28);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/translate/angular-translate-loader-partial.min.js', 2, 29);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/lodash.min.js', 2, 30);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/restangular.min.js', 2, 31);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/ui-bootstrap-tpls-0.12.0-custom.js', 2, 32);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/select.min.js', 2, 33);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/angular-growl.min.js', 2, 34);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/angular-scroll.min.js', 2, 35);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/ui-utils.js', 2, 36);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/angular-lazy-tree.js', 2, 37);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/angular-google-maps.min.js', 2, 38);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/textAngular-rangy.min.js', 2, 39);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/textAngular-sanitize.min.js', 2, 40);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/textAngular.min.js', 2, 41);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/angular-ui-tree.min.js', 2, 42);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/MasksDirective.js', 2, 43);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/utils/ArrayUtil.js', 2, 44);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/loading.js', 2, 45);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/menu.js', 2, 46);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/page-unload.js', 2, 47);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/modernizr-2.8.3.min.js', 2, 48);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular-file-upload.js', 2, 48);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/jquery-ui/core.min.js', 2, 49);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/jquery-ui/widget.min.js', 2, 50);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/jquery-ui/mouse.min.js', 2, 51);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/jquery-ui/sortable.min.js', 2, 52);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/angular-ui-sortable/sortable.min.js', 2, 53);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/adf.js', 2, 54);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/provider.js', 2, 55);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/widget-content.js', 2, 56);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/widget.js', 2, 57);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/dashboard.js', 2, 58);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/column.js', 2, 59);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/row.js', 2, 60);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/structures.js', 2, 61);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/widgets-sample/news/news.js', 2, 61);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/widgets-sample/weather/weather.js', 2, 62);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/widgets-sample/linklist/linklist.js', 2, 63);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/widgets-sample/components/showdown/showdown.js', 2, 64);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/widgets-sample/components/angular-markdown-directive/markdown.js', 2, 65);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/widgets-sample/markdown/markdown.js', 2, 66);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/widgets-sample/components/highcharts/highcharts.js', 2, 68);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/widgets-sample/components/highcharts-ng/dist/highcharts-ng.js', 2, 69);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/widgets-sample/components/angular-google-chart/ng-google-chart.js', 2, 69);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/widgets/charts/highchart/highchartcit.js', 2, 71);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/widgets/charts/google-chart/googlechart.js', 2, 72);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/dashboard-framework/widgets/charts/google-chart-gauge/googlechartgauge.js', 2, 72);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/app.js', 2, 74);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/controller/AppController.js', 2, 75);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/repository/AbstractRepository.js', 2, 76);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/repository/PaginaUsuarioRepository.js', 2, 77);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/repository/UsuarioRepository.js', 2, 78);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/repository/UsuarioPatrimonioRepository.js', 2, 79);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/repository/NotificacaoRepository.js', 2, 80);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/repository/ParceiroRepository.js', 2, 81);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/repository/EnderecoRepository.js', 2, 82);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/controller/DefaultTemplateController.js', 2, 83);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/controller/DefaultTemplateListController.js', 2, 84);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/filter/filter.js', 2, 85);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/cpf-validator.js', 2, 87);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/cnpj-validator.js', 2, 88);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/passwordCheckDirective.js', 2, 89);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/onBlurChangeDirective.js', 2, 90);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/MoneyDirective.js', 2, 91);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/IntegerDirective.js', 2, 92);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/onEnterBlurDirective.js', 2, 93);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/DateDirective.js', 2, 94);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/SortByDirective.js', 2, 95);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/FiltroDirective.js', 2, 96);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/DialogDirective.js', 2, 97);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/FavoritoDirective.js', 2, 98);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/LabelInputDirective.js', 2, 99);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/LabelInputCheckboxDirective.js', 2, 100);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/LabelSelectDirective.js', 2, 101);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/LabelInputNumberDirective.js', 2, 102);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/LabelInputDataDirective.js', 2, 103);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/LabelInputMoneyDirective.js', 2, 104);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/LabelInputRadioDirective.js', 2, 105);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/TelefoneDirective.js', 2, 106);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/ObservacaoDirective.js', 2, 107);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/EnderecoDirective.js', 2, 108);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/AutoCompleteDirective.js', 2, 109);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/AutoCompleteObrigatorioDirective.js', 2, 110);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/LabelTextAreaDirective.js', 2, 111);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/EnderecoUnicoDirective.js', 2, 112);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/TooltipDirective.js', 2, 113);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/ChosenSelectDirective.js', 2, 114);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/DropdownDirective.js', 2, 115);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/PercentDirective.js', 2, 116);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/LabelInputPercentDirective.js', 2, 117);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/CpfCnpjDirective.js', 2, 118);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/ButtonLockDirective.js', 2, 119);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/HelpButtonDirective.js', 2, 120);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/PickListDirective.js', 2, 121);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/IdentifierDirective.js', 2, 122);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/LabelInputIdentifierDirective.js', 2, 123);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/LabelColorSelectDirective.js', 2, 124);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/ListaStringDirective.js', 2, 125);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/OneWayDirective.js', 2, 126);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/BloquearDesbloquearDirective.js', 2, 127);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular-br-filters.js', 2, 128);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/ListViewDirective.js', 2, 129);

INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, true, 'assets/js/angular/directive/RegexValidate.js', 2, 130);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, true, 'assets/js/uds_api_contents.js', 2, 130);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('03/04/2015 16:05:11', '03/04/2015 16:05:11', 1, true, 'assets/js/jsapi.js', 2, 131);
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES ('13/05/2015 09:47:00', '13/05/2015 09:47:00', 1, true, 'assets/js/angular/directive/TimelineHorizontalDirective.js', 2, 132);


-- PESSOA COLABORADOR
INSERT INTO pessoa(id, datacriacao, dataedicao, version, nome, dominio_pessoa, orgao_id)
    VALUES(5, '2015-01-01 09:26:25', '2015-01-01 09:26:25', 0, 'Colaborador 2', 22, 1);

INSERT INTO parceiro(id, datacriacao, dataedicao, "version", codigo, classeparceiro_id, pessoa_id)
    VALUES (2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, '001', 1, 5);

INSERT INTO parceiro_colaborador(id, estruturaorganizacional_id, funcao_id)  VALUES (2, 1, 1);

-- PESSOA COLABORADOR
INSERT INTO pessoa(id, datacriacao, dataedicao, version, nome, dominio_pessoa, orgao_id)
    VALUES(6, '2015-01-01 09:26:25', '2015-01-01 09:26:25', 0, 'Colaborador 3', 22, 1);

INSERT INTO parceiro(id, datacriacao, dataedicao, "version", codigo, classeparceiro_id, pessoa_id)
    VALUES (4, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, '001', 1, 6);

INSERT INTO parceiro_colaborador(id, estruturaorganizacional_id, funcao_id)  VALUES (4, 1, 1);

INSERT INTO inventariocomissao(datacriacao, dataedicao, version, codigo, dataformacao, nome, numprocesso, portaria, autor_id, editor_id, estruturaorganizacional_id, orgao_id, presidente_id) VALUES ('2014-12-30 00:00:00', '2014-12-30 00:00:00', 1, 1, '2014-12-30', 'Comissão de Teste', '201400009002245', 1234, 1, 1, 1, 1, 1);
INSERT INTO inventariocomissaointegrante(id, datacriacao, dataedicao, version, integrante_id, inventariocomissao_id) VALUES (1, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 0, 1, 1);
INSERT INTO inventariocomissaointegrante(id, datacriacao, dataedicao, version, integrante_id, inventariocomissao_id) VALUES (2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 0, 2, 1);
INSERT INTO inventariocomissaointegrante(id, datacriacao, dataedicao, version, integrante_id, inventariocomissao_id) VALUES (3, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 0, 4, 1);

-- PESSOA PORTADOR
INSERT INTO pessoa(id, datacriacao, dataedicao, version, nome, dominio_pessoa, orgao_id)
    VALUES(7, '2015-01-01 09:26:25', '2015-01-01 09:26:25', 0, 'Portador', 22, 1);

-- PARCEIRO PORTADOR
INSERT INTO parceiro(id, datacriacao, dataedicao, "version", codigo, classeparceiro_id, pessoa_id)
    VALUES (5, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, '001', 4, 7);

-- PORTADOR
INSERT INTO parceiro_portador(id) VALUES (5);

-- PESSOA FORNECEDOR
INSERT INTO pessoa(id, datacriacao, dataedicao, version, nome, dominio_pessoa, orgao_id)
    VALUES(8, '2015-01-01 09:26:25', '2015-01-01 09:26:25', 0, 'Fornecedor', 22, 1);

-- PARCEIRO FORNECEDOR
INSERT INTO parceiro(id, datacriacao, dataedicao, version, codigo, autor_id, editor_id, classeparceiro_id, pessoa_id)
    VALUES (6, '2015-01-01 09:26:25', '2015-01-01 09:26:25', 0, 123, 1, 1, 3, 8);

-- FORNECEDOR
INSERT INTO fornecedor(id, estrangeiro, comprasEletronicas)
    VALUES (6, false, true );

-- PESSOA ORGAO EXTERNO
INSERT INTO pessoa(id, datacriacao, dataedicao, version, nome, dominio_pessoa, orgao_id)
    VALUES(9, '2015-01-01 09:26:25', '2015-01-01 09:26:25', 0, 'Orgão Externo', 22, 1);

-- PARCEIRO ORGAO EXTERNO
INSERT INTO parceiro(id, datacriacao, dataedicao, version, codigo, autor_id, editor_id, classeparceiro_id, pessoa_id)
    VALUES (7, '2015-01-01 09:26:25', '2015-01-01 09:26:25', 0, 123, 1, 1, 2, 9);

-- ORGAO EXTERNO
INSERT INTO orgaoexterno(sigla, id) VALUES ('OE', 7);

-- PESSOA COLABORADOR
INSERT INTO pessoa(id, datacriacao, dataedicao, version, nome, dominio_pessoa, orgao_id)
    VALUES(10, '2015-01-01 09:26:25', '2015-01-01 09:26:25', 0, 'Colaborador 4', 22, 1);

INSERT INTO parceiro(id, datacriacao, dataedicao, "version", codigo, classeparceiro_id, pessoa_id)
    VALUES (10, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), 1, '001', 1, 10);

INSERT INTO parceiro_colaborador(id, estruturaorganizacional_id, funcao_id)  VALUES (10, 1, 1);


-- ACCESS ROLES
INSERT INTO accessrole(id, datacriacao, dataedicao, datainativo, version, url, roles) VALUES (1, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, '/html/**', 'ROLE_ADMIN');
INSERT INTO accessrole(id, datacriacao, dataedicao, datainativo, version, url, roles) VALUES (2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, '/admin/**', 'ROLE_ADMIN,ROLE_MANAGER');

-- JORNADA TRABALHO - carlos.santos em 10.02.2015
INSERT INTO workday(id, datacriacao, dataedicao, datainativo, version, description)
  VALUES (1, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'Jornada padrão');

INSERT INTO workday(id, datacriacao, dataedicao, datainativo, version, description)
  VALUES (2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, 'Jornada horas corridas');

-- HORARIO JORNADA TRABALHO - carlos.santos em 10.02.2015
INSERT INTO worktime(id, starttime, endtime, workday_id)
  VALUES (1, TO_DATE('2000-01-01 08:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2000-01-01 12:00:00','YYYY-MM-DD HH24:MI:SS'), 1);

INSERT INTO worktime(id, starttime, endtime, workday_id)
  VALUES (2, TO_DATE('2000-01-01 14:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2000-01-01 18:00:00','YYYY-MM-DD HH24:MI:SS'), 1);

INSERT INTO worktime(id, starttime, endtime, workday_id)
  VALUES (3, TO_DATE('2000-01-01 00:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2000-01-01 24:00:00','YYYY-MM-DD HH24:MI:SS'), 2);

-- CALENDARIO - carlos.santos em 10.02.2015
INSERT INTO workcalendar(id, datacriacao, dataedicao, datainativo, version,
  considerholiday, description, workdaymon_id,  workdaytue_id,  workdaywed_id,  workdaythu_id,  workdayfri_id)
  VALUES (1, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0,
  true, 'Calendário padrão', 1, 1, 1, 1, 1);

INSERT INTO workcalendar(id, datacriacao, dataedicao, datainativo, version,
  considerholiday, description, workdaymon_id,  workdaytue_id,  workdaywed_id,  workdaythu_id,  workdayfri_id)
  VALUES (2, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0,
  true, 'Calendário horas corridas', 2, 2, 2, 2, 2);

-- WIDGET
INSERT INTO public.widget ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "apresentarurlservico", "descricao", "nome", "urlservico", "inativador_id", "autor_id", "editor_id", "tipocomponente_id")
VALUES (6, NULL, NULL, E'2015-04-02 10:17:33.535', E'2015-04-02 10:17:33.535', 0, True, E'HighChart', E'HighChart', NULL, NULL, 1, 1, 188);

INSERT INTO public.widget ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "apresentarurlservico", "descricao", "nome", "urlservico", "inativador_id", "autor_id", "editor_id", "tipocomponente_id")
VALUES (7, NULL, NULL, E'2015-04-02 10:23:04.388', E'2015-04-02 10:23:04.388', 0, True, E'Google Chart', E'Google Chart', NULL, NULL, 1, 1, 211);

INSERT INTO public.widget ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "apresentarurlservico", "descricao", "nome", "urlservico", "inativador_id", "autor_id", "editor_id", "tipocomponente_id")
VALUES (8, NULL, NULL, E'2015-04-02 10:27:08.019', E'2015-04-02 10:27:08.019', 0, True, E'Google Chart Gauge', E'Google Chart Gauge', NULL, NULL, 1, 1, 201);

INSERT INTO public.widget ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "apresentarurlservico", "descricao", "nome", "urlservico", "inativador_id", "autor_id", "editor_id", "tipocomponente_id")
VALUES (9, NULL, NULL, E'2015-04-02 10:27:08.019', E'2015-04-02 10:27:08.019', 0, True, E'Html', E'Html', NULL, NULL, 1, 1, 269);

INSERT INTO public.widget ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "apresentarurlservico", "descricao", "nome", "urlservico", "inativador_id", "autor_id", "editor_id", "tipocomponente_id")
VALUES (10, NULL, NULL, E'2015-04-02 10:27:08.019', E'2015-04-02 10:27:08.019', 0, True, E'Notícia', E'Notícia', NULL, NULL, 1, 1, 210);

INSERT INTO public.widget ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "apresentarurlservico", "descricao", "nome", "urlservico", "inativador_id", "autor_id", "editor_id", "tipocomponente_id")
VALUES (11, NULL, NULL, E'2015-04-02 10:27:08.019', E'2015-04-02 10:27:08.019', 0, True, E'Temperatura', E'Temperatura', NULL, NULL, 1, 1, 209);

INSERT INTO public.widget ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "apresentarurlservico", "descricao", "nome", "urlservico", "inativador_id", "autor_id", "editor_id", "tipocomponente_id")
VALUES (12, NULL, NULL, E'2015-04-02 10:27:08.019', E'2015-04-02 10:27:08.019', 0, True, E'Link', E'Link', NULL, NULL, 1, 1, 198);

/* Data for the 'public.widgetparametro' table  (Records 1 - 16) */

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (21, NULL, NULL, E'2015-04-02 10:17:33.567', E'2015-04-02 10:17:33.567', 0, False, E'tipoHighChart', NULL, E'tipoHighChart', NULL, NULL, NULL, 1, 1, 193, 56, 6, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (22, NULL, NULL, E'2015-04-02 10:17:33.571', E'2015-04-02 10:17:33.571', 0, False, NULL, NULL, E'titulo', NULL, E'titulo', NULL, 1, 1, NULL, 13, 6, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (23, NULL, NULL, E'2015-04-02 10:17:33.574', E'2015-04-02 10:17:33.574', 0, False, NULL, NULL, E'sub titulo', NULL, E'sub titulo', NULL, 1, 1, NULL, 13, 6, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (24, NULL, NULL, E'2015-04-02 10:23:04.391', E'2015-04-02 10:23:04.391', 0, False, NULL, NULL, E'hAxis titulo', NULL, E'hAxis titulo', NULL, 1, 1, NULL, 12, 7, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (25, NULL, NULL, E'2015-04-02 10:23:04.394', E'2015-04-02 10:23:04.394', 0, False, NULL, NULL, E'vAxis titulo', NULL, E'vAxis titulo', NULL, 1, 1, NULL, 12, 7, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (26, NULL, NULL, E'2015-04-02 10:23:04.397', E'2015-04-02 10:23:04.397', 0, True, NULL, NULL, E'isStacked', NULL, NULL, NULL, 1, 1, NULL, 197, 7, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (27, NULL, NULL, E'2015-04-02 10:23:04.400', E'2015-04-02 10:23:04.400', 0, False, NULL, NULL, E'Titulo', NULL, E'Titulo', NULL, 1, 1, NULL, 12, 7, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (28, NULL, NULL, E'2015-04-02 10:23:04.402', E'2015-04-02 10:23:04.402', 0, False, E'tipoGoogleChart', NULL, E'Tipo Grafico', NULL, NULL, NULL, 1, 1, 207, 56, 7, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (29, NULL, NULL, E'2015-04-02 10:27:08.023', E'2015-04-02 10:27:08.023', 0, False, NULL, NULL, E'max', 100, NULL, NULL, 1, 1, NULL, 14, 8, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (30, NULL, NULL, E'2015-04-02 10:27:08.029', E'2015-04-02 10:27:08.029', 0, False, NULL, NULL, E'yellowFrom', 70, NULL, NULL, 1, 1, NULL, 14, 8, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (31, NULL, NULL, E'2015-04-02 10:27:08.035', E'2015-04-02 10:27:08.035', 0, False, NULL, NULL, E'yellowTo', 80, NULL, NULL, 1, 1, NULL, 14, 8, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (32, NULL, NULL, E'2015-04-02 10:27:08.038', E'2015-04-02 10:27:08.038', 0, False, NULL, NULL, E'redFrom', 80, NULL, NULL, 1, 1, NULL, 14, 8, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (33, NULL, NULL, E'2015-04-02 10:27:08.042', E'2015-04-02 10:27:08.042', 0, False, NULL, NULL, E'redTo', 100, NULL, NULL, 1, 1, NULL, 14, 8, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (34, NULL, NULL, E'2015-04-02 10:27:08.049', E'2015-04-02 10:27:08.049', 0, False, NULL, NULL, E'greenFrom', 40, NULL, NULL, 1, 1, NULL, 14, 8, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (35, NULL, NULL, E'2015-04-02 10:27:08.054', E'2015-04-02 10:27:08.054', 0, False, NULL, NULL, E'greenTo', 70, NULL, NULL, 1, 1, NULL, 14, 8, NULL);

INSERT INTO public.widgetparametro ("id", "databloqueio", "datainativo", "datacriacao", "dataedicao", "version", "booleandefault", "chavedominiodefault", "datadefault", "nome", "numerodefault", "textodefault", "inativador_id", "autor_id", "editor_id", "dominiodefault_id", "tipowidgetparametrodominio_id", "widget_id", "widgetinativo_id")
VALUES (36, NULL, NULL, E'2015-04-02 10:27:08.057', E'2015-04-02 10:27:08.057', 0, False, NULL, NULL, E'minorTicks', 5, NULL, NULL, 1, 1, NULL, 14, 8, NULL);
