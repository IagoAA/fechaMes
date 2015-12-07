INSERT INTO classeparceiro(id, dominiotipoparceiro_id) VALUES (1, 16);
INSERT INTO classeparceiro(id, dominiotipoparceiro_id) VALUES (2, 17);
INSERT INTO classeparceiro(id, dominiotipoparceiro_id) VALUES (3, 18);
INSERT INTO classeparceiro(id, dominiotipoparceiro_id) VALUES (4, 19);

INSERT INTO pessoa(id, datacriacao, dataedicao, version, email, nome )
    VALUES (10, '2014-11-24 09:26:25', '2014-11-24 09:26:25', 0, 'fornec@for.com', 'fornecedor');
            
INSERT INTO fornecedorramoatividade(
           id, datacriacao, dataedicao, version, grupofederalsupply_id, subgrupofederalsupply_id)
   VALUES (1, '2014-11-24 09:26:25', '2014-11-24 09:26:25', 0, 1, 2);

INSERT INTO parceiro(
          id, datacriacao, dataedicao, version, codigo, classeparceiro_id, pessoa_id)
   VALUES (1, '2014-11-24 09:26:25', '2014-11-24 09:26:25', 0, 123, 1, 10);

INSERT INTO fornecedor(id, filantropico, impostosimples, dominioabrangencia_id, dominioporte_id, 
           dominiotipofornecedor_id, ramoatividade_id)
   VALUES (1, true, true, 4, 3, 4, 5, 1);

INSERT INTO pessoa(id, datacriacao, dataedicao, version, email, nome )
    VALUES (11, '2014-11-24 09:26:25', '2014-11-24 09:26:25', 0, 'colaborador@colaborador.com', 'colaborador');
    
INSERT INTO parceiro(
          id, datacriacao, dataedicao, version, codigo, classeparceiro_id, pessoa_id)
   VALUES (2, '2014-11-24 09:26:25', '2014-11-24 09:26:25', 0, 1234, 4, 11);

INSERT INTO localizacao(
            id, datacriacao, dataedicao, version, nome, endereco_id)
    VALUES (1, '2014-11-24 09:26:25', '2014-11-24 09:26:25', 0, 'teste', 1);

INSERT INTO estruturaorganizacional(
            id, datacriacao, dataedicao, version, 
            classificacao, datainicio, nome, possuibempatrimonial, sigla, 
            dominiotipoestruturaorganizacional_id, localizacao_id, mapaorganizacional_id, orgao_id)
VALUES (1,'2014-11-24 09:26:25', '2014-11-24 09:26:25', 0, 'teste', '2014-11-24 09:26:25', 'teste', true, 'tst', 5, 1, 1, 1);

INSERT INTO classeparceiro(
            id, dominiotipoparceiro_id)
    VALUES (1, 2);

INSERT INTO pessoafisica(
            id, cpf, datanascimento, nomemae, nomepai, numeropassaporte, 
            rg, tituloeleitor, dominio_estado_civil, dominio_sexo)
    VALUES (1, '123', '2014-11-24 09:26:25', 'teste', 'teste', '123456', 
            '123456', '123456', 1, 2);

INSERT INTO pessoajuridica(
            id, cnpj, filantropico, filial, impostosimples, inscricaoestadual, 
            inscricaomunicipal, nomefantasia, razaosocial, site, dominio_abrangencia, 
            dominio_porte)
    VALUES (1, '123', true, true, true, 'teste', 
            'teste', 'teste', 'teste', 'teste', 1, 
            2);

INSERT INTO pessoa(
            id, datacriacao, dataedicao, version, email, nome, 
            autor_id, editor_id, contato_id, dominio_pessoa, 
            pessoafisica_id, pessoajuridica_id)
    VALUES (1, '2014-11-24 09:26:25', '2014-11-24 09:26:25', 0 , 'teste@teste.com', 'teste', 
            1, 1, 1, 1, 
            1, 1);

INSERT INTO parceiro(
            id, datacriacao, dataedicao, version, codigo, autor_id, 
            editor_id, classeparceiro_id, pessoa_id)
    VALUES (1, '2014-11-24 09:26:25', '2014-11-24 09:26:25', 0, 123, 1, 
           1, 1, 1);

INSERT INTO parceiro_colaborador(
            id, estruturaorganizacional_id)
    VALUES (1, 1);

INSERT INTO fornecedorramoatividade(
            id, datacriacao, dataedicao, version, grupofederalsupply_id, subgrupofederalsupply_id)
    VALUES (1, '2014-11-24 09:26:25', '2014-11-24 09:26:25', 0, 1, 2);


INSERT INTO fornecedor(id, filantropico, impostosimples, dominioabrangencia_id, dominioporte_id, 
            dominiotipofornecedor_id, ramoatividade_id)
    VALUES (1, true, true, 1, 3, 4, 5, 1);

INSERT INTO entrada(
            id, datacriacao, dataedicao, version, datareferencia, dominiotipobem_id, dominiotipoentrada_id, estruturaorganizacional_id, 
            fornecedor_id)
    VALUES (1, '2014-11-24 09:26:25', '2014-11-24 09:26:25', 0, '2014-11-24 09:26:25', 1, 1, 1, 1);

INSERT INTO entradaitem(
            id, datacriacao, dataedicao, version, emplaquetado, 
            quantidade, entrada_id, material_id)
    VALUES (1, '2014-11-24 09:26:25', '2014-11-24 09:26:25', 0, true, 
            2, 1, 3);

INSERT INTO bempatrimonial(
            id, datacriacao, dataedicao, version, 
            codigo, datafinalgarantia, datainicialgarantia, numeropatrimonial, 
            valoraquisicao, autor_id, editor_id, material_id, 
            bempatrimonialprincipal_id,detentor_id, dominiosituacaofisica_id, 
            dominiostatus_id, entradaitem_id, estruturaorganizacionalatual_id, 
            estruturaorganizacionalcompra_id, fornecedor_id, responsavel_id)
    VALUES (1, '2014-11-24 09:26:25', '2014-11-24 09:26:25', 0,  
            123, '2015-11-24 09:26:25', '2014-11-24 09:26:25', '123456', 
            50.0, 1, 1, 3, 
            1, 1, 1, 
            1, 1, 1, 
            1, 1, 1);
                
INSERT INTO contacontabil(
            datacriacao, dataedicao, version, codigo, autor_id, editor_id)
    VALUES ('2014-11-24 09:26:25', '2014-11-24 09:26:25', 0, 123, 1, 1);

INSERT INTO material(
            datacriacao, dataedicao, version, emplaquetavel, 
            unidademedida, autor_id, editor_id, inativador_id, contacontabil_id, 
            dominiotipomaterial_id, federalsupply_id, orgao_id, descricao)
    VALUES ('2014-11-24 09:26:25', '2014-11-24 09:26:25', 0, true, 
            'un', 1, 1, 1, 4, 1, 1, 1, 'teste');
