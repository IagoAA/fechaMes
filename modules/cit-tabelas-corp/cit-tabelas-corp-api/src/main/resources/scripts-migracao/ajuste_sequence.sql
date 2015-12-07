-- gera todas as sequence cadastradas e gerenciadas pelo hibernate
SELECT 'SELECT setval(''' || n.nspname || '.' ||
replace(replace(replace(replace(replace(replace(replace(replace(a.adsrc,
'(',''),')',''),'::',''),'textregclass',''),'nextval',''),'regclass',''),'''',''),
n.nspname||'.','')||''',(SELECT MAX('||ab.attname||') FROM '||
n.nspname|| '.'||c.relname||'),true);' as seqname
FROM pg_class c
JOIN pg_attrdef a ON c.oid=a.adrelid
JOIN pg_namespace n ON c.relnamespace = n.oid AND n.nspname NOT LIKE 'pg_%'
JOIN pg_index i ON i.indrelid=c.oid AND i.indisprimary='t'
JOIN pg_attribute ab ON ab.attrelid=c.oid AND ab.attisdropped='f' AND ab.atthasdef='t' AND i.indkey[0]=ab.attnum AND i.indkey[1] IS NULL
Where a.adsrc like 'nextval%';

-----

-- Altera todas a sequence atualizado seus indices
SELECT setval('public.contacontabilmovimento_id_seq',(SELECT MAX(id) FROM public.contacontabilmovimento),true);
SELECT setval('public.accessrole_id_seq',(SELECT MAX(id) FROM public.accessrole),true);
SELECT setval('public.adicaobemprincipal_id_seq',(SELECT MAX(id) FROM public.adicaobemprincipal),true);
SELECT setval('public.adicaobemprincipalitem_id_seq',(SELECT MAX(id) FROM public.adicaobemprincipalitem),true);
SELECT setval('public.anexo_id_seq',(SELECT MAX(id) FROM public.anexo),true);
SELECT setval('public.anexoimagem_id_seq',(SELECT MAX(id) FROM public.anexoimagem),true);
SELECT setval('public.atendimentorequisicaoconsumo_id_seq',(SELECT MAX(id) FROM public.atendimentorequisicaoconsumo),true);
SELECT setval('public.atendimentorequisicaoconsumoitem_id_seq',(SELECT MAX(id) FROM public.atendimentorequisicaoconsumoitem),true);
SELECT setval('public.bairro_id_seq',(SELECT MAX(id) FROM public.bairro),true);
SELECT setval('public.bemalmoxarifadocaracteristica_id_seq',(SELECT MAX(id) FROM public.bemalmoxarifadocaracteristica),true);
SELECT setval('public.bempatrimonial_id_seq',(SELECT MAX(id) FROM public.bempatrimonial),true);
SELECT setval('public.bempatrimonialcaracteristica_id_seq',(SELECT MAX(id) FROM public.bempatrimonialcaracteristica),true);
SELECT setval('public.bempatrimonialvalor_id_seq',(SELECT MAX(id) FROM public.bempatrimonialvalor),true);
SELECT setval('public.caracteristica_id_seq',(SELECT MAX(id) FROM public.caracteristica),true);
SELECT setval('public.catalogomaterial_id_seq',(SELECT MAX(id) FROM public.catalogomaterial),true);
SELECT setval('public.catalogomaterialitem_id_seq',(SELECT MAX(id) FROM public.catalogomaterialitem),true);
SELECT setval('public.catalogomaterialunidadeconsumidorarequisitanteitem_id_seq',(SELECT MAX(id) FROM public.catalogomaterialunidadeconsumidorarequisitanteitem),true);
SELECT setval('public.centrocusto_id_seq',(SELECT MAX(id) FROM public.centrocusto),true);
SELECT setval('public.cidade_id_seq',(SELECT MAX(id) FROM public.cidade),true);
SELECT setval('public.classeparceiro_id_seq',(SELECT MAX(id) FROM public.classeparceiro),true);
SELECT setval('public.classificacaomaterial_id_seq',(SELECT MAX(id) FROM public.classificacaomaterial),true);
SELECT setval('public.configuracao_id_seq',(SELECT MAX(id) FROM public.configuracao),true);
SELECT setval('public.configuracaoalmoxarifado_id_seq',(SELECT MAX(id) FROM public.configuracaoalmoxarifado),true);
SELECT setval('public.configuracaocontacontabilalmoxarifado_id_seq',(SELECT MAX(id) FROM public.configuracaocontacontabilalmoxarifado),true);
SELECT setval('public.configuracaocontacontabilextraviado_id_seq',(SELECT MAX(id) FROM public.configuracaocontacontabilextraviado),true);
SELECT setval('public.configuracaonotificacaoressuprimento_id_seq',(SELECT MAX(id) FROM public.configuracaonotificacaoressuprimento),true);
SELECT setval('public.configuracaonotificacaoressuprimentogrupo_id_seq',(SELECT MAX(id) FROM public.configuracaonotificacaoressuprimentogrupo),true);
SELECT setval('public.configuracaonotificacaoressuprimentousuario_id_seq',(SELECT MAX(id) FROM public.configuracaonotificacaoressuprimentousuario),true);
SELECT setval('public.configuracaosistema_id_seq',(SELECT MAX(id) FROM public.configuracaosistema),true);
SELECT setval('public.contacontabil_id_seq',(SELECT MAX(id) FROM public.contacontabil),true);
SELECT setval('public.contacontabilconfiguracaoreferencia_id_seq',(SELECT MAX(id) FROM public.contacontabilconfiguracaoreferencia),true);
SELECT setval('public.contacontabilsaldo_id_seq',(SELECT MAX(id) FROM public.contacontabilsaldo),true);
SELECT setval('public.contato_id_seq',(SELECT MAX(id) FROM public.contato),true);
SELECT setval('public.contrato_id_seq',(SELECT MAX(id) FROM public.contrato),true);
SELECT setval('public.contratoanexo_id_seq',(SELECT MAX(id) FROM public.contratoanexo),true);
SELECT setval('public.contratoapostilamento_id_seq',(SELECT MAX(id) FROM public.contratoapostilamento),true);
SELECT setval('public.contratoempenho_id_seq',(SELECT MAX(id) FROM public.contratoempenho),true);
SELECT setval('public.contratoequipegestora_id_seq',(SELECT MAX(id) FROM public.contratoequipegestora),true);
SELECT setval('public.contratoexecucaoquestionario_id_seq',(SELECT MAX(id) FROM public.contratoexecucaoquestionario),true);
SELECT setval('public.contratoexecucaoquestionarioresposta_id_seq',(SELECT MAX(id) FROM public.contratoexecucaoquestionarioresposta),true);
SELECT setval('public.contratoinfracao_id_seq',(SELECT MAX(id) FROM public.contratoinfracao),true);
SELECT setval('public.contratoinfracaopenalidade_id_seq',(SELECT MAX(id) FROM public.contratoinfracaopenalidade),true);
SELECT setval('public.contratoinfracaopenalizacao_id_seq',(SELECT MAX(id) FROM public.contratoinfracaopenalizacao),true);
SELECT setval('public.contratoliberacaopagamento_id_seq',(SELECT MAX(id) FROM public.contratoliberacaopagamento),true);
SELECT setval('public.contratoocorrencia_id_seq',(SELECT MAX(id) FROM public.contratoocorrencia),true);
SELECT setval('public.contratorescisao_id_seq',(SELECT MAX(id) FROM public.contratorescisao),true);
SELECT setval('public.contratotermoaditivo_id_seq',(SELECT MAX(id) FROM public.contratotermoaditivo),true);
SELECT setval('public.dadosbempatrimonial_id_seq',(SELECT MAX(id) FROM public.dadosbempatrimonial),true);
SELECT setval('public.defaultfile_id_seq',(SELECT MAX(id) FROM public.defaultfile),true);
SELECT setval('public.definicaodetentor_id_seq',(SELECT MAX(id) FROM public.definicaodetentor),true);
SELECT setval('public.definicaodetentoritem_id_seq',(SELECT MAX(id) FROM public.definicaodetentoritem),true);
SELECT setval('public.depreciacao_id_seq',(SELECT MAX(id) FROM public.depreciacao),true);
SELECT setval('public.depreciacaoitem_id_seq',(SELECT MAX(id) FROM public.depreciacaoitem),true);
SELECT setval('public.devolucao_id_seq',(SELECT MAX(id) FROM public.devolucao),true);
SELECT setval('public.devolucaoitem_id_seq',(SELECT MAX(id) FROM public.devolucaoitem),true);
SELECT setval('public.documento_id_seq',(SELECT MAX(id) FROM public.documento),true);
SELECT setval('public.dominio_id_seq',(SELECT MAX(id) FROM public.dominio),true);
SELECT setval('public.dominioinconsistencia_id_seq',(SELECT MAX(id) FROM public.dominioinconsistencia),true);
SELECT setval('public.endereco_id_seq',(SELECT MAX(id) FROM public.endereco),true);
SELECT setval('public.entradaalmoxarifadoitem_id_seq',(SELECT MAX(id) FROM public.entradaalmoxarifadoitem),true);
SELECT setval('public.esi_businessprocess_id_seq',(SELECT MAX(id) FROM public.esi_businessprocess),true);
SELECT setval('public.esi_businessprocesscategory_id_seq',(SELECT MAX(id) FROM public.esi_businessprocesscategory),true);
SELECT setval('public.esi_businessprocesspermission_id_seq',(SELECT MAX(id) FROM public.esi_businessprocesspermission),true);
SELECT setval('public.esi_errorlog_id_seq',(SELECT MAX(id) FROM public.esi_errorlog),true);
SELECT setval('public.esi_eventjob_id_seq',(SELECT MAX(id) FROM public.esi_eventjob),true);
SELECT setval('public.esi_flow_id_seq',(SELECT MAX(id) FROM public.esi_flow),true);
SELECT setval('public.esi_flowconnection_id_seq',(SELECT MAX(id) FROM public.esi_flowconnection),true);
SELECT setval('public.esi_flowelement_id_seq',(SELECT MAX(id) FROM public.esi_flowelement),true);
SELECT setval('public.esi_flowelementactor_id_seq',(SELECT MAX(id) FROM public.esi_flowelementactor),true);
SELECT setval('public.esi_flowelementvariable_id_seq',(SELECT MAX(id) FROM public.esi_flowelementvariable),true);
SELECT setval('public.esi_flowstatus_id_seq',(SELECT MAX(id) FROM public.esi_flowstatus),true);
SELECT setval('public.esi_flowvariable_id_seq',(SELECT MAX(id) FROM public.esi_flowvariable),true);
SELECT setval('public.esi_flowversion_id_seq',(SELECT MAX(id) FROM public.esi_flowversion),true);
SELECT setval('public.esi_processinstance_id_seq',(SELECT MAX(id) FROM public.esi_processinstance),true);
SELECT setval('public.esi_processinstancevariable_id_seq',(SELECT MAX(id) FROM public.esi_processinstancevariable),true);
SELECT setval('public.esi_processinstancevariablevalue_id_seq',(SELECT MAX(id) FROM public.esi_processinstancevariablevalue),true);
SELECT setval('public.esi_runtimelog_id_seq',(SELECT MAX(id) FROM public.esi_runtimelog),true);
SELECT setval('public.esi_userinterface_id_seq',(SELECT MAX(id) FROM public.esi_userinterface),true);
SELECT setval('public.esi_workitem_id_seq',(SELECT MAX(id) FROM public.esi_workitem),true);
SELECT setval('public.esi_workitemassignment_id_seq',(SELECT MAX(id) FROM public.esi_workitemassignment),true);
SELECT setval('public.estado_id_seq',(SELECT MAX(id) FROM public.estado),true);
SELECT setval('public.estruturaorganizacionalresponsavel_id_seq',(SELECT MAX(id) FROM public.estruturaorganizacionalresponsavel),true);
SELECT setval('public.estruturaorgao_id_seq',(SELECT MAX(id) FROM public.estruturaorgao),true);
SELECT setval('public.favorito_id_seq',(SELECT MAX(id) FROM public.favorito),true);
SELECT setval('public.feriado_id_seq',(SELECT MAX(id) FROM public.feriado),true);
SELECT setval('public.filter_id_seq',(SELECT MAX(id) FROM public.filter),true);
SELECT setval('public.fluxotrabalho_id_seq',(SELECT MAX(id) FROM public.fluxotrabalho),true);
SELECT setval('public.fornecedorramoatividade_id_seq',(SELECT MAX(id) FROM public.fornecedorramoatividade),true);
SELECT setval('public.funcao_id_seq',(SELECT MAX(id) FROM public.funcao),true);
SELECT setval('public.garantia_id_seq',(SELECT MAX(id) FROM public.garantia),true);
SELECT setval('public.garantiaacionamento_id_seq',(SELECT MAX(id) FROM public.garantiaacionamento),true);
SELECT setval('public.grupo_id_seq',(SELECT MAX(id) FROM public.grupo),true);
SELECT setval('public.grupoprivilegio_id_seq',(SELECT MAX(id) FROM public.grupoprivilegio),true);
SELECT setval('public.grupousuario_id_seq',(SELECT MAX(id) FROM public.grupousuario),true);
SELECT setval('public.historicobempatrimonial_id_seq',(SELECT MAX(id) FROM public.historicobempatrimonial),true);
SELECT setval('public.imagembempatrimonial_id_seq',(SELECT MAX(id) FROM public.imagembempatrimonial),true);
SELECT setval('public.internacionalizacao_id_seq',(SELECT MAX(id) FROM public.internacionalizacao),true);
SELECT setval('public.inventario_id_seq',(SELECT MAX(id) FROM public.inventario),true);
SELECT setval('public.inventariobempatrimonial_id_seq',(SELECT MAX(id) FROM public.inventariobempatrimonial),true);
SELECT setval('public.inventariocomissao_id_seq',(SELECT MAX(id) FROM public.inventariocomissao),true);
SELECT setval('public.inventariocomissaointegrante_id_seq',(SELECT MAX(id) FROM public.inventariocomissaointegrante),true);
SELECT setval('public.inventarioestruturaorganizacional_id_seq',(SELECT MAX(id) FROM public.inventarioestruturaorganizacional),true);
SELECT setval('public.link_id_seq',(SELECT MAX(id) FROM public.link),true);
SELECT setval('public.localestoque_id_seq',(SELECT MAX(id) FROM public.localestoque),true);
SELECT setval('public.localizacao_id_seq',(SELECT MAX(id) FROM public.localizacao),true);
SELECT setval('public.mapaorganizacional_id_seq',(SELECT MAX(id) FROM public.mapaorganizacional),true);
SELECT setval('public.materialcaracteristica_id_seq',(SELECT MAX(id) FROM public.materialcaracteristica),true);
SELECT setval('public.materialconsumotipounidademedidaentrada_id_seq',(SELECT MAX(id) FROM public.materialconsumotipounidademedidaentrada),true);
SELECT setval('public.materialestoquesaldo_id_seq',(SELECT MAX(id) FROM public.materialestoquesaldo),true);
SELECT setval('public.materialimagem_id_seq',(SELECT MAX(id) FROM public.materialimagem),true);
SELECT setval('public.materialpermanenteconsumo_id_seq',(SELECT MAX(id) FROM public.materialpermanenteconsumo),true);
SELECT setval('public.menu_id_seq',(SELECT MAX(id) FROM public.menu),true);
SELECT setval('public.menufile_id_seq',(SELECT MAX(id) FROM public.menufile),true);
SELECT setval('public.modeloemail_id_seq',(SELECT MAX(id) FROM public.modeloemail),true);
SELECT setval('public.modulo_id_seq',(SELECT MAX(id) FROM public.modulo),true);
SELECT setval('public.monitor_id_seq',(SELECT MAX(id) FROM public.monitor),true);
SELECT setval('public.monitoracaodecorrente_id_seq',(SELECT MAX(id) FROM public.monitoracaodecorrente),true);
SELECT setval('public.monitoragendamento_id_seq',(SELECT MAX(id) FROM public.monitoragendamento),true);
SELECT setval('public.monitornotificacao_id_seq',(SELECT MAX(id) FROM public.monitornotificacao),true);
SELECT setval('public.monitorpredecessora_id_seq',(SELECT MAX(id) FROM public.monitorpredecessora),true);
SELECT setval('public.monitorresponsavel_id_seq',(SELECT MAX(id) FROM public.monitorresponsavel),true);
SELECT setval('public.movimentoestoque_id_seq',(SELECT MAX(id) FROM public.movimentoestoque),true);
SELECT setval('public.nivelautoridade_id_seq',(SELECT MAX(id) FROM public.nivelautoridade),true);
SELECT setval('public.notificacao_id_seq',(SELECT MAX(id) FROM public.notificacao),true);
SELECT setval('public.notificacaogrupo_id_seq',(SELECT MAX(id) FROM public.notificacaogrupo),true);
SELECT setval('public.notificacaousuario_id_seq',(SELECT MAX(id) FROM public.notificacaousuario),true);
SELECT setval('public.observacao_id_seq',(SELECT MAX(id) FROM public.observacao),true);
SELECT setval('public.orgaoitem_id_seq',(SELECT MAX(id) FROM public.orgaoitem),true);
SELECT setval('public.pagina_id_seq',(SELECT MAX(id) FROM public.pagina),true);
SELECT setval('public.paginausuario_id_seq',(SELECT MAX(id) FROM public.paginausuario),true);
SELECT setval('public.painel_id_seq',(SELECT MAX(id) FROM public.painel),true);
SELECT setval('public.painelgrupo_id_seq',(SELECT MAX(id) FROM public.painelgrupo),true);
SELECT setval('public.painelitem_id_seq',(SELECT MAX(id) FROM public.painelitem),true);
SELECT setval('public.painelitemgrupo_id_seq',(SELECT MAX(id) FROM public.painelitemgrupo),true);
SELECT setval('public.painelitemparametro_id_seq',(SELECT MAX(id) FROM public.painelitemparametro),true);
SELECT setval('public.painelitemprivilegio_id_seq',(SELECT MAX(id) FROM public.painelitemprivilegio),true);
SELECT setval('public.painelitemusuarioconfiguracao_id_seq',(SELECT MAX(id) FROM public.painelitemusuarioconfiguracao),true);
SELECT setval('public.painelprivilegio_id_seq',(SELECT MAX(id) FROM public.painelprivilegio),true);
SELECT setval('public.pais_id_seq',(SELECT MAX(id) FROM public.pais),true);
SELECT setval('public.parametrosistema_id_seq',(SELECT MAX(id) FROM public.parametrosistema),true);
SELECT setval('public.parceiro_id_seq',(SELECT MAX(id) FROM public.parceiro),true);
SELECT setval('public.penalidade_id_seq',(SELECT MAX(id) FROM public.penalidade),true);
SELECT setval('public.pessoa_id_seq',(SELECT MAX(id) FROM public.pessoa),true);
SELECT setval('public.possivelrespostaquestionariopergunta_id_seq',(SELECT MAX(id) FROM public.possivelrespostaquestionariopergunta),true);
SELECT setval('public.alcada_id_seq',(SELECT MAX(id) FROM public.processonegocio),true);
SELECT setval('public.questionario_id_seq',(SELECT MAX(id) FROM public.questionario),true);
SELECT setval('public.questionariopergunta_id_seq',(SELECT MAX(id) FROM public.questionariopergunta),true);
SELECT setval('public.regiao_id_seq',(SELECT MAX(id) FROM public.regiao),true);
SELECT setval('public.regraevento_id_seq',(SELECT MAX(id) FROM public.regraevento),true);
SELECT setval('public.requisicaoconsumo_id_seq',(SELECT MAX(id) FROM public.requisicaoconsumo),true);
SELECT setval('public.requisicaoconsumoitem_id_seq',(SELECT MAX(id) FROM public.requisicaoconsumoitem),true);
SELECT setval('public.requisicaoobservacao_id_seq',(SELECT MAX(id) FROM public.requisicaoobservacao),true);
SELECT setval('public.saidatemporaria_id_seq',(SELECT MAX(id) FROM public.saidatemporaria),true);
SELECT setval('public.saidatemporariaitem_id_seq',(SELECT MAX(id) FROM public.saidatemporariaitem),true);
SELECT setval('public.searchparams_id_seq',(SELECT MAX(id) FROM public.searchparams),true);
SELECT setval('public.seguranca_privilegio_id_seq',(SELECT MAX(id) FROM public.seguranca_privilegio),true);
SELECT setval('public.seguranca_usuario_id_seq',(SELECT MAX(id) FROM public.seguranca_usuario),true);
SELECT setval('public.seguranca_usuario_privilegio_id_seq',(SELECT MAX(id) FROM public.seguranca_usuario_privilegio),true);
SELECT setval('public.telefone_id_seq',(SELECT MAX(id) FROM public.telefone),true);
SELECT setval('public.temp_series_id_seq',(SELECT MAX(id) FROM public.temp_series),true);
SELECT setval('public.termoresponsabilidade_id_seq',(SELECT MAX(id) FROM public.termoresponsabilidade),true);
SELECT setval('public.transferencia_id_seq',(SELECT MAX(id) FROM public.transferencia),true);
SELECT setval('public.transferenciacontacontabil_id_seq',(SELECT MAX(id) FROM public.transferenciacontacontabil),true);
SELECT setval('public.transferenciaitem_id_seq',(SELECT MAX(id) FROM public.transferenciaitem),true);
SELECT setval('public.unidademedida_id_seq',(SELECT MAX(id) FROM public.unidademedida),true);
SELECT setval('public.widget_id_seq',(SELECT MAX(id) FROM public.widget),true);
SELECT setval('public.widgetparametro_id_seq',(SELECT MAX(id) FROM public.widgetparametro),true);
SELECT setval('public.workcalendar_id_seq',(SELECT MAX(id) FROM public.workcalendar),true);
SELECT setval('public.workday_id_seq',(SELECT MAX(id) FROM public.workday),true);
SELECT setval('public.workdayexception_id_seq',(SELECT MAX(id) FROM public.workdayexception),true);
SELECT setval('public.worktime_id_seq',(SELECT MAX(id) FROM public.worktime),true);

-- sequence manual
-- sequence manual
ALTER TABLE entrada ALTER COLUMN id SET DEFAULT nextval('entrada_id_seq');
SELECT setval('public.entrada_id_seq',(SELECT MAX(id) FROM public.entrada),true);

ALTER TABLE entradapatrimonioitem ALTER COLUMN id SET DEFAULT nextval('entradaPatrimonioItem_id_seq');
SELECT setval('public.entradaPatrimonioItem_id_seq',(SELECT MAX(id) FROM public.entradapatrimonioitem),true);

ALTER TABLE contacontabilmovimento ALTER COLUMN id SET DEFAULT nextval('contaContabilMovimento_id_seq');
SELECT setval('public.contaContabilMovimento_id_seq',(SELECT MAX(id) FROM public.contacontabilmovimento),true);

ALTER TABLE baixa ALTER COLUMN id SET DEFAULT nextval('baixa_id_seq');
SELECT setval('public.baixa_id_seq',(SELECT MAX(id) FROM public.baixa),true);

ALTER TABLE baixapatrimonioitem ALTER COLUMN id SET DEFAULT nextval('baixaPatrimonioItem_id_seq');
SELECT setval('public.baixa_id_seq',(SELECT MAX(id) FROM public.baixapatrimonioitem),true);

ALTER TABLE material ALTER COLUMN id SET DEFAULT nextval('material_id_seq');
SELECT setval('public.material_id_seq',(SELECT MAX(id) FROM public.material),true);

ALTER TABLE transferenciaitem ALTER COLUMN id SET DEFAULT nextval('transferenciaitem_id_seq');
SELECT setval('public.transferenciaitem_id_seq',(SELECT MAX(id) FROM public.transferenciaitem),true);

CREATE SEQUENCE baixaAlmoxarifado_id_seq;
ALTER TABLE baixaAlmoxarifado ALTER COLUMN id SET DEFAULT nextval('baixaAlmoxarifado_id_seq');
SELECT setval('public.baixaAlmoxarifado_id_seq',(SELECT MAX(id) FROM public.baixaAlmoxarifado),true);

SELECT setval('public.baixaAlmoxarifadoitem_id_seq',(SELECT MAX(id) FROM public.baixaAlmoxarifadoItem),true);

SELECT setval('public.menu_id_seq',(SELECT MAX(id) FROM public.menu),true);

SELECT setval('public.menufile_id_seq',(SELECT MAX(id) FROM public.menufile),true);

ALTER TABLE menu ALTER COLUMN id SET DEFAULT nextval('menu_id_seq');
SELECT setval('public.menu_id_seq',(SELECT MAX(id) FROM public.menu),true);

ALTER TABLE menu ALTER COLUMN id SET DEFAULT nextval('menufile_id_seq');
SELECT setval('public.menufile_id_seq',(SELECT MAX(id) FROM public.menufile),true);

ALTER TABLE dominio ALTER COLUMN id SET DEFAULT nextval('dominio_id_seq');
SELECT setval('public.dominio_id_seq',(SELECT MAX(id) FROM public.dominio),true);

