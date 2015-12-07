INSERT INTO pais(
            datacriacao, dataedicao, datainativo, version, codigo, nome,
            sigla, autor_id, editor_id, inativador_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2014-11-24 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, '55', 'Brasil',
            'BR', NULL, NULL, NULL);

INSERT INTO regiao(
            datacriacao, dataedicao, datainativo, version, codigo, nome,
            autor_id, editor_id, inativador_id, pais_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2014-11-24 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, '01', 'Centro-Oeste',
            NULL, NULL, NULL, 1);

INSERT INTO regiao(
            datacriacao, dataedicao, datainativo, version, codigo, nome,
            autor_id, editor_id, inativador_id, pais_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2014-11-24 10:26:27','YYYY-MM-DD HH24:MI:SS'), NULL, 0, '02', 'Sul',
            NULL, NULL, NULL, 1);

INSERT INTO estado(
            datacriacao, dataedicao, datainativo, version, codigo, nome,
            sigla, autor_id, editor_id, inativador_id, regiao_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2014-11-24 10:26:27','YYYY-MM-DD HH24:MI:SS'),NULL,0, '0', 'Goiás',
            'GO', NULL, NULL, NULL, 1);

INSERT INTO estado(
            datacriacao, dataedicao, datainativo, version, codigo, nome,
            sigla, autor_id, editor_id, inativador_id, regiao_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2014-11-24 10:26:27','YYYY-MM-DD HH24:MI:SS'),NULL,0, '1', 'Rio Grande do Sul',
            'RS', NULL, NULL, NULL, 2);

INSERT INTO cidade(
            datacriacao, dataedicao, datainativo, version, codigo, codigoibge,
            nome, autor_id, editor_id, inativador_id, estado_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), null, 0, '0', '12',
            'Goiania', NULL, NULL, NULL, 1);

INSERT INTO cidade(
            datacriacao, dataedicao, datainativo, version, codigo, codigoibge,
            nome, autor_id, editor_id, inativador_id, estado_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), null, 0, '1', '13',
            'Inhumas', NULL, NULL, NULL, 1);

INSERT INTO cidade(
            datacriacao, dataedicao, datainativo, version, codigo, codigoibge,
            nome, autor_id, editor_id, inativador_id, estado_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), null, 0, '0', '12',
            'Rio Grande', NULL, NULL, NULL, 2);

INSERT INTO bairro(
            datacriacao, dataedicao, datainativo, version, codigo, nome,
            autor_id, editor_id, inativador_id, cidade_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), null, 0, '0', 'Bairro 1',
            null, null, null, 1);

INSERT INTO bairro(
            datacriacao, dataedicao, datainativo, version, codigo, nome,
            autor_id, editor_id, inativador_id, cidade_id)
    VALUES (TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2014-11-24 09:26:25','YYYY-MM-DD HH24:MI:SS'), null, 0, '1', 'Bairro 2',
            null, null, null, 2);

