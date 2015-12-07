package br.com.centralit.framework.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * <p>
 * <img src="http://centralit.com.br/images/logo_central.png">
 * </p>
 *
 * <p>
 * <b>Company: </b> Central IT - Governança Corporativa -
 * </p>
 *
 * <p>
 * <b>Title: </b>
 * </p>
 *
 * <p>
 * <b>Description: </b>
 * </p>
 *
 * <p>
 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
 * </p>
 *
 * <p>
 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
 * </p>
 *
 * @since 05/12/2014 - 13:56:20
 *
 * @version 1.0.0
 *
 * @author ally.barra
 *
 */
@Entity
@Table(indexes={@Index(columnList="chave,codigo")})
public class Dominio extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -2994950511670107077L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo nome. */
	@Column(length = 100, nullable = false)
	@JsonView({ Views.GenericView.class })
	private String nome;

	/** Atributo descricao. */
	@Column(length = 255)
	@JsonView({ Views.GenericView.class })
	private String descricao;

	/** Atributo chave. */
	@Column(length = 50, nullable = false)
	@JsonView({ Views.GenericView.class})
	private String chave;

	/** Atributo codigo. */
	@Column(length = 5, nullable = false)
	@JsonView({ Views.GenericView.class })
	private Long codigo;

	public Dominio() {

		super();
	}

	public Dominio(String chave, String descricao, String nome, Long codigo ) {

		this();
		this.nome = nome;
		this.descricao = descricao;
		this.chave = chave;
		this.codigo = codigo;
	}

	/** Atributo TIPO_ABRANGENCIA. */
	public static final String TIPO_ABRANGENCIA = "tipoAbrangencia";

	/** Constante TIPO_ABRANGENCIA_LOCAL. */
	public static final Long TIPO_ABRANGENCIA_LOCAL = 1L;

	/** Constante TIPO_ABRANGENCIA_NACIONAL. */
	public static final Long TIPO_ABRANGENCIA_NACIONAL = 2L;

	/** Constante TIPO_ABRANGENCIA_REGIONAL. */
	public static final Long TIPO_ABRANGENCIA_REGIONAL = 3L;

	/** Constante TIPO_PORTE. */
	public static final String TIPO_PORTE = "tipoPorte";

	/** Constante TIPO_PORTE_MICRO_EMPRESA. */
	public static final Long TIPO_PORTE_MICRO_EMPRESA = 1L;

	/** Constante TIPO_PORTE_MICRO_PEQUENA. */
	public static final Long TIPO_PORTE_MICRO_PEQUENA = 2L;

	/** Constante TIPO_PORTE_MEDIA. */
	public static final Long TIPO_PORTE_MEDIA = 3L;

	/** Constante TIPO_PORTE_MICRO_GRANDE. */
	public static final Long TIPO_PORTE_MICRO_GRANDE = 4L;

	/** Constante TIPO_PRIORIDADE. */
	public static final String TIPO_PRIORIDADE = "tipoPrioridade";

	/** Atributo TIPO_PRIORIDADE_BAIXA. */
	public static final Long TIPO_PRIORIDADE_BAIXA = 1L;

	/** Atributo TIPO_PRIORIDADE_MEDIA. */
	public static final Long TIPO_PRIORIDADE_MEDIA = 2L;

	/** Atributo TIPO_PRIORIDADE_ALTA. */
	public static final Long TIPO_PRIORIDADE_ALTA = 3L;

	/** Constante TIPO_NOTIFICACAO. */
	public static final String TIPO_NOTIFICACAO = "tipoNotificacao";

	/** Atributo TIPO_NOTIFICACAO_PATRIMONIO. */
	public static final Long TIPO_NOTIFICACAO_PATRIMONIO = 1L;

	public static final Long TIPO_NOTIFICACAO_ALMOXARIFADO = 2L;

	/** Constante TIPO_FORNECEDOR. */
	public static final String TIPO_FORNECEDOR = "tipoFornecedor";

	/** Constante TIPO_TELEFONE. */
	public static final String TIPO_TELEFONE = "tipoTelefone";

	/** Constante TIPO_ENDERECO. */
	public static final String TIPO_ENDERECO = "tipoEndereco";

	/** Constante TIPO_PESSOA. */
	public static final String TIPO_PESSOA = "tipoPessoa";

	/** Atributo TIPO_PESSOA_FISICA. */
	public static final Long TIPO_PESSOA_FISICA = 1L;

	/** Atributo TIPO_PESSOA_JURIDICA. */
	public static final Long TIPO_PESSOA_JURIDICA = 2L;

	/** Constante TIPO_SEXO. */
	public static final String TIPO_SEXO = "tipoSexo";

	/** Atributo TIPO_ESTADO_CIVIL. */
	public static final String TIPO_ESTADO_CIVIL = "tipoEstadoCivil";

	/** Atributo TIPO_PARCEIRO. */
	public static final String TIPO_PARCEIRO = "tipoParceiro";

	/** Atributo TIPO_PARCEIRO_COLABORADOR. */
	public static final Long TIPO_PARCEIRO_COLABORADOR = 1L;

	/** Atributo TIPO_PARCEIRO_ORGAO_EXTERNO. */
	public static final Long TIPO_PARCEIRO_ORGAO_EXTERNO = 2L;

	/** Atributo TIPO_PARCEIRO_PORTADOR. */
	public static final Long TIPO_PARCEIRO_PORTADOR = 3L;

	/** Atributo TIPO_PARCEIRO_FORNECEDOR. */
	public static final Long TIPO_PARCEIRO_FORNECEDOR = 4L;

	/** Atributo TIPO_PARCEIRO_SEGURADORA. */
	public static final Long TIPO_PARCEIRO_SEGURADORA = 5L;

	/** Atributo TIPO_PARCEIRO_CLIENTE. */
	public static final Long TIPO_PARCEIRO_CLIENTE = 6L;

	/** Atributo TIPO_PARCEIRO_FUNCIONARIO. */
	public static final Long TIPO_PARCEIRO_FUNCIONARIO = 7L;

	/** Constante TIPO_COMPONENTE_INTERFACE. */
	public static final String TIPO_COMPONENTE_INTERFACE = "tipoComponenteInterface";

	/** Constante TIPO_DADO. */
	public static final String TIPO_DADO = "tipoDado";

	/** Atributo TIPO_DADO_ARQUIVO. */
	public static final String TIPO_DADO_ARQUIVO = "ARQUIVO";

	/** Atributo TIPO_DADO_URL_SERVICO. */
	public static final String TIPO_DADO_URL_SERVICO = "URL_SERVICO";

	/** Atributo TIPO_DADO_ARQUIVO_CODIGO. */
	public static final Long TIPO_DADO_ARQUIVO_CODIGO = 7L;

	/** Atributo TIPO_DADO_URL_SERVICO_CODIGO. */
	public static final Long TIPO_DADO_URL_SERVICO_CODIGO = 9L;

	/** Constante TIPO_RESTRICAO. */
	public static final String TIPO_RESTRICAO = "tipoRestricao";

	/** Atributo TIPO_STATUS_BEM. */
	public static final String TIPO_STATUS_BEM = "tipoStatusBem";

	/** Atributo TIPO_STATUS_BEM_UTILIZADO. */
	public static final Long TIPO_STATUS_BEM_UTILIZADO = 1L;

	/** Atributo TIPO_STATUS_BEM_BAIXADO. */
	public static final Long TIPO_STATUS_BEM_BAIXADO = 2L;

	/** Atributo TIPO_STATUS_BEM_DISPONIVEL. */
	public static final Long TIPO_STATUS_BEM_DISPONIVEL = 3L;

	/** Atributo TIPO_STATUS_BEM_EM_PROCESSO_DE_BAIXA. */
	public static final Long TIPO_STATUS_BEM_EM_PROCESSO_DE_BAIXA = 4L;

	/** Atributo TIPO_STATUS_BEM_EM_PROCESSO_DE_TRANSFERENCIA. */
	public static final Long TIPO_STATUS_BEM_EM_PROCESSO_DE_TRANSFERENCIA = 5L;

	/** Atributo TIPO_STATUS_BEM_EM_SAIDA_TEMPORARIA. */
	public static final Long TIPO_STATUS_BEM_EM_SAIDA_TEMPORARIA = 6L;

	/** Atributo TIPO_STATUS_BEM_EXTRAVIADO. */
	public static final Long TIPO_STATUS_BEM_EXTRAVIADO = 7L;

	/** Atributo TIPO_STATUS_BEM_INDISPONIVEL. */
	public static final Long TIPO_STATUS_BEM_INDISPONIVEL = 8L;

	/** Atributo TIPO_STATUS_BEM_SINDICANCIA. */
	public static final Long TIPO_STATUS_BEM_SINDICANCIA = 9L;

	/** Constante TIPO_STATUS_BEM_NAO_LOCALIZADO. */
	public static final Long TIPO_STATUS_BEM_NAO_LOCALIZADO = 10L;

	/** Atributo TIPO_CLASSIFICACAO_MATERIAL. */
	public static final String TIPO_CLASSIFICACAO_MATERIAL = "tipoClassificacaoMaterial";

	/** Atributo TIPO_CLASSIFICACAO_MATERIAL_GRUPO. */
	public static final Long TIPO_CLASSIFICACAO_MATERIAL_GRUPO = 1L;

	/** Atributo TIPO_CLASSIFICACAO_MATERIAL_SUB_GRUPO. */
	public static final Long TIPO_CLASSIFICACAO_MATERIAL_SUB_GRUPO = 2L;

	/** Constante TIPO_FEDERALSUPPLY_MATERIAL. */
	public static final Long TIPO_CLASSIFICACAO_MATERIAL_MATERIAL = 3L;

	/** Constante TIPO_FEDERALSUPPLY_DETALHE. */
	public static final Long TIPO_CLASSIFICACAO_MATERIAL_DETALHE = 4L;

	/** Constante TIPO_MATERIAL. */
	public static final String TIPO_MATERIAL = "tipoMaterial";

	/** Constante TIPO_DOCUMENTO. */
	public static final String TIPO_DOCUMENTO = "tipoDocumento";

	/** Constante TIPO_MATERIAL_CONSUMO. */
	public static final Long TIPO_MATERIAL_CONSUMO = 1L;

	/** Constante TIPO_MATERIAL_PERMANENTE. */
	public static final Long TIPO_MATERIAL_PERMANENTE = 2L;

	/** Atributo TIPO_UNIDADE_MEDIDA. */
	public static final String TIPO_UNIDADE_MEDIDA = "tipoUnidadeMedida";

	/** Atributo TIPO_MODALIDADE_TRANSFERENCIA_PERMANENTE. */
	public static final String TIPO_MODALIDADE_TRANSFERENCIA_PERMANENTE = "PERMANENTE";

	/** Atributo TIPO_MODALIDADE_TRANSFERENCIA_TEMPORARIA. */
	public static final String TIPO_MODALIDADE_TRANSFERENCIA_TEMPORARIA = "TEMPORARIA";

	/** Atributo TIPO_ANEXO. */
	public static final String TIPO_ANEXO = "tipoAnexo";

	/** Atributo TIPO_ANEXO_XML. */
	public static final String TIPO_ANEXO_XML = "XML";

	/** Atributo TIPO_ANEXO_JPG. */
	public static final String TIPO_ANEXO_JPG = "JPG";

	/** Atributo TIPO_ANEXO_JPEG. */
	public static final String TIPO_ANEXO_JPEG = "JPEG";

	/** Atributo TIPO_ANEXO_PNG. */
	public static final String TIPO_ANEXO_PNG = "PNG";

	/** Atributo TIPO_ANEXO_DOC. */
	public static final String TIPO_ANEXO_DOC = "DOC";

	/** Atributo TIPO_ANEXO_XLS. */
	public static final String TIPO_ANEXO_XLS = "XLS";

	/** Atributo TIPO_ANEXO_TXT. */
	public static final String TIPO_ANEXO_TXT = "TXT";

	/** Atributo TIPO_ANEXO_SEM_EXTENSAO. */
	public static final String TIPO_ANEXO_SEM_EXTENSAO = "SEM_EXTENSAO";

	/** Atributo TIPO_ANEXO_XML_CODIGO. */
	public static final Long TIPO_ANEXO_XML_CODIGO = 1L;

	/** Atributo TIPO_ANEXO_JPG_CODIGO. */
	public static final Long TIPO_ANEXO_JPG_CODIGO = 2L;

	/** Atributo TIPO_ANEXO_JPEG_CODIGO. */
	public static final Long TIPO_ANEXO_JPEG_CODIGO = 3L;

	/** Atributo TIPO_ANEXO_PNG_CODIGO. */
	public static final Long TIPO_ANEXO_PNG_CODIGO = 4L;

	/** Atributo TIPO_ANEXO_DOC_CODIGO. */
	public static final Long TIPO_ANEXO_DOC_CODIGO = 5L;

	/** Atributo TIPO_ANEXO_XLS_CODIGO. */
	public static final Long TIPO_ANEXO_XLS_CODIGO = 6L;

	/** Atributo TIPO_ANEXO_TXT_CODIGO. */
	public static final Long TIPO_ANEXO_TXT_CODIGO = 12L;

	/** Atributo TIPO_ANEXO_XLS_SEM_EXTENSAO_CODIGO. */
	public static final Long TIPO_ANEXO_XLS_SEM_EXTENSAO_CODIGO = 13L;

	/** Atributo TIPO_SAIDA_TEMPORARIA. */
	public static final String TIPO_OBJETIVO_SAIDA_TEMPORARIA = "tipoObjetivoSaidaTemporaria";

	/** Atributo TIPO_SAIDA_TEMPORARIA_COMODATO_CODIGO. */
	public static final Long TIPO_OBJETIVO_SAIDA_TEMPORARIA_COMODATO_CODIGO = 1L;

	/** Atributo TIPO_SAIDA_TEMPORARIA_CONSERTO_CODIGO. */
	public static final Long TIPO_OBJETIVO_SAIDA_TEMPORARIA_CONSERTO_CODIGO = 2L;

	/** Atributo TIPO_SAIDA_TEMPORARIA_EMPRESTIMO_CODIGO. */
	public static final Long TIPO_OBJETIVO_SAIDA_TEMPORARIA_EMPRESTIMO_CODIGO = 3L;

	/** Atributo TIPO_SAIDA_TEMPORARIA_EVENTO_CODIGO. */
	public static final Long TIPO_OBJETIVO_SAIDA_TEMPORARIA_EVENTO_CODIGO = 4L;

	/** Atributo TIPO_SAIDA_TEMPORARIA_COMODATO_CHAVE. */
	public static final String TIPO_OBJETIVO_SAIDA_TEMPORARIA_COMODATO_CHAVE = "COMODATO";

	/** Atributo TIPO_SAIDA_TEMPORARIA_CONSERTO_CHAVE. */
	public static final String TIPO_OBJETIVO_SAIDA_TEMPORARIA_CONSERTO_CHAVE = "CONSERTO";

	/** Atributo TIPO_SAIDA_TEMPORARIA_EMPRESTIMO_CHAVE. */
	public static final String TIPO_OBJETIVO_SAIDA_TEMPORARIA_EMPRESTIMO_CHAVE = "EMPRESTIMO";

	/** Atributo TIPO_SAIDA_TEMPORARIA_EVENTO_CHAVE. */
	public static final String TIPO_OBJETIVO_SAIDA_TEMPORARIA_EVENTO_CHAVE = "EVENTO";

	/** Atributo TIPO_BEM. */
	public static final String TIPO_BEM = "tipoBem";

	/** Atributo TIPO_BEM_PROPRIO_CONTABILIZADO. */
	public static final String TIPO_BEM_PROPRIO_CONTABILIZADO = "PROPRIO_CONTABILIZADO";

	/** Atributo TIPO_BEM_PROPRIO_CONTROLADO. */
	public static final String TIPO_BEM_PROPRIO_CONTROLADO = "PROPRIO_CONTROLADO";

	/** Atributo TIPO_BEM_DE_TERCEIROS. */
	public static final String TIPO_BEM_DE_TERCEIROS = "DE_TERCEIROS";

	/** Atributo TIPO_BEM_PROPRIO_CONTABILIZADO_CODIGO. */
	public static final Long TIPO_BEM_PROPRIO_CONTABILIZADO_CODIGO = 1L;

	/** Atributo TIPO_BEM_PROPRIO_CONTROLADO_CODIGO. */
	public static final Long TIPO_BEM_PROPRIO_CONTROLADO_CODIGO = 2L;

	/** Atributo TIPO_BEM_DE_TERCEIROS_CODIGO. */
	public static final Long TIPO_BEM_DE_TERCEIROS_CODIGO = 3L;

	/** Atributo TIPO_BAIXA. */
	public static final String TIPO_BAIXA = "tipoBaixa";

	/** Atributo TIPO_BAIXA_VENDA. */
	public static final String TIPO_BAIXA_VENDA = "VENDA";

	/** Atributo TIPO_BAIXA_DOACAO. */
	public static final String TIPO_BAIXA_DOACAO = "DOACAO";

	/** Atributo TIPO_BAIXA_CESSAO_DE_USO. */
	public static final String TIPO_BAIXA_CESSAO_DE_USO = "CESSAO_DE_USO";

	/** Atributo TIPO_BAIXA_EXTRAVIO. */
	public static final String TIPO_BAIXA_EXTRAVIO = "EXTRAVIO";

	/** Atributo TIPO_BAIXA_PERMUTA. */
	public static final String TIPO_BAIXA_PERMUTA = "PERMUTA";

	/** Atributo TIPO_BAIXA_VENDA_CODIGO. */
	public static final Long TIPO_BAIXA_VENDA_CODIGO = 1L;

	/** Atributo TIPO_BAIXA_DOACAO_CODIGO. */
	public static final Long TIPO_BAIXA_DOACAO_CODIGO = 2L;

	/** Atributo TIPO_BAIXA_CESSAO_DE_USO_CODIGO. */
	public static final Long TIPO_BAIXA_CESSAO_DE_USO_CODIGO = 3L;

	/** Atributo TIPO_BAIXA_EXTRAVIO_CODIGO. */
	public static final Long TIPO_BAIXA_EXTRAVIO_CODIGO = 4L;

	/** Atributo TIPO_BAIXA_PERMUTA_CODIGO. */
	public static final Long TIPO_BAIXA_PERMUTA_CODIGO = 5L;

	/** Atributo TIPO_SITUACAO_BAIXA. */
	public static final String TIPO_SITUACAO_BAIXA = "tipoSituacaoBaixa";

	/** Atributo TIPO_SITUACAO_BAIXA_EM_ANDAMENTO. */
	public static final String TIPO_SITUACAO_BAIXA_EM_ANDAMENTO = "EM_ANDAMENTO";

	/** Atributo TIPO_SITUACAO_BAIXA_AUTORIZADA. */
	public static final String TIPO_SITUACAO_BAIXA_AUTORIZADA = "AUTORIZADA";

	/** Atributo TIPO_SITUACAO_BAIXA_NAO_AUTORIZADA. */
	public static final String TIPO_SITUACAO_BAIXA_NAO_AUTORIZADA = "NAO_AUTORIZADA";

	/** Atributo TIPO_SITUACAO_BAIXA_EM_ANDAMENTO_CODIGO. */
	public static final Long TIPO_SITUACAO_BAIXA_EM_ANDAMENTO_CODIGO = 1L;

	/** Atributo TIPO_SITUACAO_BAIXA_AUTORIZADA_CODIGO. */
	public static final Long TIPO_SITUACAO_BAIXA_AUTORIZADA_CODIGO = 2L;

	/** Atributo TIPO_SITUACAO_BAIXA_NAO_AUTORIZADA_CODIGO. */
	public static final Long TIPO_SITUACAO_BAIXA_NAO_AUTORIZADA_CODIGO = 3L;

	/** Atributo TIPO_SAIDA_TEMPORARIA. */
	public static final String TIPO_PROJETO_SAIDA_TEMPORARIA = "tipoProjetoSaidaTemporaria";

	/** Atributo TIPO_PROJETO_OUTRO_CODIGO. */
	public static final Long TIPO_PROJETO_OUTRO_CODIGO = 1L;

	/** Atributo TIPO_DEPRECIACAO. */
	public static final String TIPO_DEPRECIACAO = "tipoDepreciacao";

	/** Atributo TIPO_DEPRECIACAO_LINEAR_CODIGO. */
	public static final Long TIPO_DEPRECIACAO_LINEAR_CODIGO = 1L;

	/** Constante TIPO_CONTA_CONTABIL. */
	public static final String TIPO_CONTA_CONTABIL = "tipoContaContabil";

	/** Atributo TIPO_STATUS_DEPRECIACAO. */
	public static final String TIPO_STATUS_DEPRECIACAO = "tipoStatusDepreciacao";

	/** Atributo TIPO_STATUS_DEPRECIACAO_BEM_DEPRECIAVEL. */
	public static final String TIPO_STATUS_DEPRECIACAO_BEM_DEPRECIAVEL = "DEPRECIAVEL";

	/** Atributo TIPO_STATUS_DEPRECIACAO_NAO_DEPRECIAVEL_ALMOXARIFADO. */
	public static final String TIPO_STATUS_DEPRECIACAO_NAO_DEPRECIAVEL_ALMOXARIFADO = "NAO_DEPRECIAVEL_ALMOXARIFADO";

	/** Atributo TIPO_STATUS_DEPRECIACAO_NAO_DEPRECIAVEL_VALOR_RESIDUAL. */
	public static final String TIPO_STATUS_DEPRECIACAO_NAO_DEPRECIAVEL_VALOR_RESIDUAL = "NAO_DEPRECIAVEL_VALOR_RESIDUAL";

	/** Atributo TIPO_STATUS_DEPRECIACAO_NAO_DEPRECIAVEL_MARCACAO_USUARIO. */
	public static final String TIPO_STATUS_DEPRECIACAO_NAO_DEPRECIAVEL_MARCACAO_USUARIO = "NAO_DEPRECIAVEL_MARCACAO_USUARIO";

	/** Atributo TIPO_STATUS_DEPRECIACAO_NAO_DEPRECIADO_EXERCICIO_ANTERIOR. */
	public static final String TIPO_STATUS_DEPRECIACAO_NAO_DEPRECIADO_EXERCICIO_ANTERIOR = "NAO_DEPRECIADO_EXERCICIO_ANTERIOR";

	/** Atributo TIPO_STATUS_DEPRECIACAO_BEM_DEPRECIAVEL_CODIGO. */
	public static final Long TIPO_STATUS_DEPRECIACAO_BEM_DEPRECIAVEL_CODIGO = 1L;

	/** Atributo TIPO_STATUS_DEPRECIACAO_NAO_DEPRECIAVEL_ALMOXARIFADO_CODIGO. */
	public static final Long TIPO_STATUS_DEPRECIACAO_NAO_DEPRECIAVEL_ALMOXARIFADO_CODIGO = 2L;

	/** Atributo TIPO_STATUS_DEPRECIACAO_NAO_DEPRECIAVEL_VALOR_RESIDUAL_CODIGO. */
	public static final Long TIPO_STATUS_DEPRECIACAO_NAO_DEPRECIAVEL_VALOR_RESIDUAL_CODIGO = 3L;

	/** Atributo TIPO_STATUS_DEPRECIACAO_NAO_DEPRECIAVEL_MARCACAO_USUARIO_CODIGO. */
	public static final Long TIPO_STATUS_DEPRECIACAO_NAO_DEPRECIAVEL_MARCACAO_USUARIO_CODIGO = 4l;

	/** Atributo TIPO_STATUS_DEPRECIACAO_NAO_DEPRECIADO_EXERCICIO_ANTERIOR_CODIGO. */
	public static final Long TIPO_STATUS_DEPRECIACAO_NAO_DEPRECIADO_EXERCICIO_ANTERIOR_CODIGO = 5l;

	/** Constante TIPO_ALTERACAO_BEM_PATRIMONIAL. */
	public static final String TIPO_ALTERACAO_BEM_PATRIMONIAL = "tipoAlteracaoBemPatrimonial";

	/** Constante TIPO_ALTERACAO_BEM_PATRIMONIAL_PERIODO_GARANTIA. */
	public static final String TIPO_ALTERACAO_BEM_PATRIMONIAL_PERIODO_GARANTIA = "PERIODO_GARANTIA";

	/** Constante TIPO_ALTERACAO_BEM_PATRIMONIAL_SITUACAO_FISICA. */
	public static final String TIPO_ALTERACAO_BEM_PATRIMONIAL_SITUACAO_FISICA = "SITUACAO_FISICA";

	/** Constante TIPO_ALTERACAO_BEM_PATRIMONIAL_STATUS_BEM. */
	public static final String TIPO_ALTERACAO_BEM_PATRIMONIAL_STATUS_BEM = "STATUS_BEM";

	/** Constante TIPO_ALTERACAO_BEM_PATRIMONIAL_NUMERO_PATRIMONIAL. */
	public static final String TIPO_ALTERACAO_BEM_PATRIMONIAL_NUMERO_PATRIMONIAL = "REAVALIACAO";

	/** Constante TIPO_ALTERACAO_BEM_PATRIMONIAL_VALOR_MERCADO. */
	public static final String TIPO_ALTERACAO_BEM_PATRIMONIAL_VALOR_MERCADO = "VALOR_MERCADO";

	/** Constante TIPO_ALTERACAO_BEM_PATRIMONIAL_PERIODO_GARANTIA. */
	public static final Long TIPO_ALTERACAO_BEM_PATRIMONIAL_PERIODO_GARANTIA_CODIGO = 1L;

	/** Constante TIPO_ALTERACAO_BEM_PATRIMONIAL_SITUACAO_FISICA. */
	public static final Long TIPO_ALTERACAO_BEM_PATRIMONIAL_SITUACAO_FISICA_CODIGO = 2L;

	/** Constante TIPO_ALTERACAO_BEM_PATRIMONIAL_STATUS_BEM_CODIGO. */
	public static final Long TIPO_ALTERACAO_BEM_PATRIMONIAL_STATUS_BEM_CODIGO = 3L;

	/** Constante TIPO_ALTERACAO_BEM_PATRIMONIAL_NUMERO_PATRIMONIAL_CODIGO. */
	public static final Long TIPO_ALTERACAO_BEM_PATRIMONIAL_NUMERO_PATRIMONIAL_CODIGO = 4L;

	/** Constante TIPO_ALTERACAO_BEM_PATRIMONIAL_VALOR_MERCADO_CODIGO. */
	public static final Long TIPO_ALTERACAO_BEM_PATRIMONIAL_VALOR_MERCADO_CODIGO = 5L;

	/** Constante TIPO_MOTIVO_ALTERACAO_BEM. */
	public static final String TIPO_MOTIVO_ALTERACAO_BEM = "tipoMotivoAlteracaoBem";

	/** Constante TIPO_MOTIVO_ALTERACAO_BEM_AJUSTE_DE__DADOS. */
	public static final String TIPO_MOTIVO_ALTERACAO_BEM_AJUSTE_DE_DADOS = "AJUSTE_DE_DADOS";

	/** Constante TIPO_MOTIVO_ALTERACAO_BEM_ATUALIZACAO_MONETARIA. */
	public static final String TIPO_MOTIVO_ALTERACAO_BEM_ATUALIZACAO_MONETARIA = "ATUALIZACAO_MONETARIA";

	/** Constante TIPO_MOTIVO_ALTERACAO_BEM_REAVALIACAO. */
	public static final String TIPO_MOTIVO_ALTERACAO_BEM_REAVALIACAO = "REAVALIACAO";

	/** Constante TIPO_MOTIVO_ALTERACAO_BEM_INVENTARIO. */
	public static final String TIPO_MOTIVO_ALTERACAO_BEM_INVENTARIO = "INVENTARIO";

	/** Constante TIPO_MOTIVO_ALTERACAO_BEM_INVENTARIO. */
	public static final String TIPO_MOTIVO_ALTERACAO_BEM_DEPRECIACAO = "DEPRECIACAO";

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_INCORPORACAO_BEM_PATRIMONIAL. */
	public static final String TIPO_MOTIVO_ALTERACAO_BEM_INCORPORACAO_BEM_PATRIMONIAL = "INCORPORACAO_BEM_PATRIMONIAL";

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_INICIO_PROCESSO_BAIXA. */
	public static final String TIPO_MOTIVO_ALTERACAO_BEM_INICIO_PROCESSO_BAIXA = "INICIO_PROCESSO_BAIXA";

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_PROCESSO_DE_BAIXA_AUTORIZADO. */
	public static final String TIPO_MOTIVO_ALTERACAO_BEM_PROCESSO_DE_BAIXA_AUTORIZADO = "PROCESSO_DE_BAIXA_AUTORIZADO";

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_PROCESSO_DE_BAIXA_NAO_AUTORIZADO. */
	public static final String TIPO_MOTIVO_ALTERACAO_BEM_PROCESSO_DE_BAIXA_NAO_AUTORIZADO = "PROCESSO_DE_BAIXA_NAO_AUTORIZADO";

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_ATUALIZACAO_STATUS_BAIXA. */
	public static final String TIPO_MOTIVO_ALTERACAO_BEM_ATUALIZACAO_STATUS_BAIXA = "ATUALIZACAO_STATUS_BAIXA";

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_ESTORNO_BAIXA. */
	public static final String TIPO_MOTIVO_ALTERACAO_BEM_ESTORNO_BAIXA = "ESTORNO_BAIXA";

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_ESTORNO_ITEM_BAIXA. */
	public static final String TIPO_MOTIVO_ALTERACAO_BEM_ESTORNO_ITEM_BAIXA = "ESTORNO_ITEM_BAIXA";

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_EXCLUIR_BAIXA. */
	public static final String TIPO_MOTIVO_ALTERACAO_BEM_EXCLUIR_BAIXA = "EXCLUIR_BAIXA";

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_EXCLUIR_ITEM_BAIXA. */
	public static final String TIPO_MOTIVO_ALTERACAO_BEM_EXCLUIR_ITEM_BAIXA = "EXCLUIR_ITEM_BAIXA";

	/** Constante TIPO_MOTIVO_ALTERACAO_BEM_DEFINICAO_DETENTOR. */
	public static final String TIPO_MOTIVO_ALTERACAO_BEM_DEFINICAO_DETENTOR = "DEFINICAO_DETENTOR";

	/** Constante TIPO_MOTIVO_ALTERACAO_BEM_EM_SAIDA_TEMPORARIA. */
	public static final String TIPO_MOTIVO_ALTERACAO_BEM_EM_SAIDA_TEMPORARIA = "SAIDA_TEMPORARIA";

	/** Constante TIPO_MOTIVO_ALTERACAO_RETORNO_SAIDA_TEMP_BEM. */
	public static final String TIPO_MOTIVO_ALTERACAO_RETORNO_SAIDA_TEMP_BEM = "RETORNO_SAIDA_TEMPORARIA";

	/** Atributo TIPO_MOTIVO_ALTERACAO_REMOCAO_DE_RESPONSAVEL_POR_OCASIAO_DE_ATRIBUICAO_DE_DETENTOR_QUE_NAO_E_RESPONSAVEL_PELO_BEM. */
	public static final String TIPO_MOTIVO_ALTERACAO_REMOCAO_DE_RESPONSAVEL_POR_OCASIAO_DE_ATRIBUICAO_DE_DETENTOR_QUE_NAO_E_RESPONSAVEL_PELO_BEM = "REMOCAO_DE_RESPONSAVEL_POR_OCASIAO_DE_ATRIBUICAO_DE_DETENTOR_QUE_NAO_E_RESPONSAVEL_PELO_BEM";

	/** Constante TIPO_MOTIVO_ALTERACAO_BEM_AJUSTE_DE_DADOS_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_AJUSTE_DE_DADOS_CODIGO = 1L;

	/** Constante TIPO_MOTIVO_ALTERACAO_BEM_ATUALIZACAO_MONETARIA_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_ATUALIZACAO_MONETARIA_CODIGO = 2L;

	/** Constante TIPO_MOTIVO_ALTERACAO_BEM_REAVALIACAO_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_REAVALIACAO_CODIGO = 3L;

	/** Constante TIPO_MOTIVO_ALTERACAO_BEM_INVENTARIO_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_INVENTARIO_CODIGO = 4L;

	/** Constante TIPO_MOTIVO_ALTERACAO_BEM_INVENTARIO_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_DEPRECIACAO_CODIGO = 5L;

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_INCORPORACAO_BEM_PATRIMONIAL_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_INCORPORACAO_BEM_PATRIMONIAL_CODIGO = 6L;

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_INICIO_PROCESSO_BAIXA_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_INICIO_PROCESSO_BAIXA_CODIGO = 7L;

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_PROCESSO_DE_BAIXA_AUTORIZADO_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_PROCESSO_DE_BAIXA_AUTORIZADO_CODIGO = 8L;

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_PROCESSO_DE_BAIXA_NAO_AUTORIZADO_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_PROCESSO_DE_BAIXA_NAO_AUTORIZADO_CODIGO = 9L;

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_ATUALIZACAO_STATUS_BAIXA_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_ATUALIZACAO_STATUS_BAIXA_CODIGO = 10L;

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_TRANSFERENCIA_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_TRANSFERENCIA_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL = 11L;

	/** Atributo TRANSFERENCIA_ENTRE_ESTRUTURAS_ORGANIZACIONAIS. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_TRANSFERENCIA_ENTRE_ESTRUTURAS_ORGANIZACIONAIS = 12L;

	/** Atributo ATRIBUICAO_DE_NOVO_DETENTOR_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_ATRIBUICAO_DE_NOVO_DETENTOR_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL = 13L;

	/** Atributo ATRIBUICAO_DE_NOVO_RESPONSAVEL_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_ATRIBUICAO_DE_NOVO_RESPONSAVEL_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL = 14L;

	/** Atributo ATRIBUICAO_A_UM_BEM_PRINCIPAL. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_ATRIBUICAO_A_UM_BEM_PRINCIPAL = 15L;

	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_ATRIBUICAO_DE_BENS_FILHOS = 16L;

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_ESTORNO_BAIXA_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_ESTORNO_BAIXA_CODIGO = 17L;

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_ESTORNO_ITEM_BAIXA_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_ESTORNO_ITEM_BAIXA_CODIGO = 18L;

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_EXCLUIR_BAIXA_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_EXCLUIR_BAIXA_CODIGO = 19L;

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_EXCLUIR_ITEM_BAIXA_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_EXCLUIR_ITEM_BAIXA_CODIGO = 20L;

	/** Constante TIPO_MOTIVO_ALTERACAO_BEM_DEFINICAO_DETENTOR_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_DEFINICAO_DETENTOR_CODIGO = 21L;

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_EXCLUSAO_DE_UM_BEM_VINCULADO_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_EXCLUSAO_DE_UM_BEM_VINCULADO_CODIGO = 22L;

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_EXCLUSAO_DE_VINCULO_A_BEM_PRINCIPAL_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_EXCLUSAO_DE_VINCULO_A_BEM_PRINCIPAL_CODIGO = 23L;

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_EXCLUSAO_DO_DETENTOR_POR_OCASIAO_DA_EXCLUSAO_DO_VINCULO_COM_O_BEM_PRINCIPAL_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_EXCLUSAO_DO_DETENTOR_POR_OCASIAO_DA_EXCLUSAO_DO_VINCULO_COM_O_BEM_PRINCIPAL_CODIGO = 24L;

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_EXCLUSAO_DO_RESPONSAVEL_POR_OCASIAO_DA_EXCLUSAO_DO_VINCULO_COM_O_BEM_PRINCIPAL_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_EXCLUSAO_DO_RESPONSAVEL_POR_OCASIAO_DA_EXCLUSAO_DO_VINCULO_COM_O_BEM_PRINCIPAL_CODIGO = 25L;

	/** Constante TIPO_MOTIVO_ALTERACAO_BEM_DEFINICAO_NOVO_RESPONSAVEL_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_DEFINICAO_NOVO_RESPONSAVEL_CODIGO = 26L;

	/** Constante TIPO_MOTIVO_ALTERACAO_BEM_EM_SAIDA_TEMPORARIA_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_EM_SAIDA_TEMPORARIA_CODIGO = 27L;

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_TRANSFERENCIA_POR_OCASIAO_DE_DEFINICAO_DE_NOVO_DETENTOR. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_TRANSFERENCIA_POR_OCASIAO_DE_DEFINICAO_DE_NOVO_DETENTOR = 28L;

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_REMOCAO_DE_DETENTOR_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_REMOCAO_DE_DETENTOR_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL = 29L;

	/** Atributo TIPO_MOTIVO_ALTERACAO_BEM_REMOCAO_DE_RESPONSAVEL_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL. */
	public static final Long TIPO_MOTIVO_ALTERACAO_BEM_REMOCAO_DE_RESPONSAVEL_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL = 30L;

	/** Constante TIPO_MOTIVO_ALTERACAO_RETORNO_SAIDA_TEMP_BEM_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_RETORNO_SAIDA_TEMP_BEM_CODIGO = 31L;

	/** Atributo TIPO_MOTIVO_ALTERACAO_REMOCAO_DE_RESPONSAVEL_POR_OCASIAO_DE_ATRIBUICAO_DE_DETENTOR_QUE_NAO_E_RESPONSAVEL_PELO_BEM_CODIGO. */
	public static final Long TIPO_MOTIVO_ALTERACAO_REMOCAO_DE_RESPONSAVEL_POR_OCASIAO_DE_ATRIBUICAO_DE_DETENTOR_QUE_NAO_E_RESPONSAVEL_PELO_BEM_CODIGO = 32L;

	/** Constante TIPO_SITUACAO_FISICA. */
	public static final String TIPO_SITUACAO_FISICA = "tipoSituacaoFisica";

	/** Constante TIPO_SITUACAO_FISICA_BOM. */
	public static final String TIPO_SITUACAO_FISICA_BOM = "BOM";

	/** Constante TIPO_SITUACAO_FISICA_REGULAR. */
	public static final String TIPO_SITUACAO_FISICA_REGULAR = "REGULAR";

	/** Constante TIPO_SITUACAO_FISICA_INSERVIVEL. */
	public static final String TIPO_SITUACAO_FISICA_INSERVIVEL = "INSERVIVEL";

	/** Constante TIPO_SITUACAO_FISICA_PRECARIO. */
	public static final String TIPO_SITUACAO_FISICA_PRECARIO = "PRECARIO";

	/** Constante TIPO_SITUACAO_FISICA_RUIM. */
	public static final String TIPO_SITUACAO_FISICA_RUIM = "RUIM";

	/** Constante TIPO_SITUACAO_FISICA_ANTIECONOMICO. */
	public static final String TIPO_SITUACAO_FISICA_ANTIECONOMICO = "ANTIECONOMICO";

	/** Constante TIPO_SITUACAO_FISICA_DANIFICADO. */
	public static final String TIPO_SITUACAO_FISICA_DANIFICADO = "DANIFICADO";

	/** Constante TIPO_SITUACAO_FISICA_OBSOLETO. */
	public static final String TIPO_SITUACAO_FISICA_OBSOLETO = "OBSOLETO";

	/** Constante TIPO_SITUACAO_FISICA_OCIOSO. */
	public static final String TIPO_SITUACAO_FISICA_OCIOSO = "OCIOSO";

	/** Constante TIPO_SITUACAO_FISICA_RECUPERAVEL. */
	public static final String TIPO_SITUACAO_FISICA_RECUPERAVEL = "RECUPERAVEL";

	/** Constante TIPO_SITUACAO_FISICA_BOM_CODIGO. */
	public static final Long TIPO_SITUACAO_FISICA_BOM_CODIGO = 1L;

	/** Constante TIPO_SITUACAO_FISICA_REGULAR_CODIGO. */
	public static final Long TIPO_SITUACAO_FISICA_REGULAR_CODIGO = 2L;

	/** Constante TIPO_SITUACAO_FISICA_INSERVIVEL_CODIGO. */
	public static final Long TIPO_SITUACAO_FISICA_INSERVIVEL_CODIGO = 3L;

	/** Constante TIPO_SITUACAO_FISICA_PRECARIO_CODIGO. */
	public static final Long TIPO_SITUACAO_FISICA_PRECARIO_CODIGO = 4L;

	/** Constante TIPO_SITUACAO_FISICA_RUIM_CODIGO. */
	public static final Long TIPO_SITUACAO_FISICA_RUIM_CODIGO = 5L;

	/** Constante TIPO_SITUACAO_FISICA_ANTIECONOMICO_CODIGO. */
	public static final Long TIPO_SITUACAO_FISICA_ANTIECONOMICO_CODIGO = 6L;

	/** Constante TIPO_SITUACAO_FISICA_DANIFICADO_CODIGO. */
	public static final Long TIPO_SITUACAO_FISICA_DANIFICADO_CODIGO = 7L;

	/** Constante TIPO_SITUACAO_FISICA_OBSOLETO_CODIGO. */
	public static final Long TIPO_SITUACAO_FISICA_OBSOLETO_CODIGO = 8L;

	/** Constante TIPO_SITUACAO_FISICA_OCIOSO_CODIGO. */
	public static final Long TIPO_SITUACAO_FISICA_OCIOSO_CODIGO = 9L;

	/** Constante TIPO_SITUACAO_FISICA_RECUPERAVEL_CODIGO. */
	public static final Long TIPO_SITUACAO_FISICA_RECUPERAVEL_CODIGO = 10L;

	/** Constante TIPO_OPERACAO. */
	public static final String TIPO_OPERACAO = "tipoOperacao";

	/** Constante TIPO_OPERACAO_ATUALIZACAO_STATUS. */
	public static final String TIPO_OPERACAO_ATUALIZACAO_STATUS = "ATUALIZACAO_STATUS";

	/** Constante TIPO_OPERACAO_PROCESSO_SINDICANCIA. */
	public static final String TIPO_OPERACAO_PROCESSO_SINDICANCIA = "PROCESSO_SINDICANCIA";

	/** Constante TIPO_OPERACAO_ATUALIZACAO_STATUS_CODIGO. */
	public static final Long TIPO_OPERACAO_ATUALIZACAO_STATUS_CODIGO = 1L;

	/** Constante TIPO_OPERACAO_PROCESSO_SINDICANCIA_CODIGO. */
	public static final Long TIPO_OPERACAO_PROCESSO_SINDICANCIA_CODIGO = 2L;

	/** Constante TIPO_HISTORICO. */
	public static final String TIPO_HISTORICO = "tipoHistorico";

	/** Atributo TIPO_HISTORICO_ENTRADA. */
	public static final String TIPO_HISTORICO_ENTRADA = "HISTORICO_ENTRADA";

	/** Atributo TIPO_HISTORICO_BAIXA. */
	public static final String TIPO_HISTORICO_BAIXA = "HISTORICO_BAIXA";

	/** Constante TIPO_HISTORICO_ATRIBUIR_DETENTOR. */
	public static final String TIPO_HISTORICO_ATRIBUIR_DETENTOR = "ATRIBUIR_DETENTOR";

	/** Constante TIPO_HISTORICO_ALTERACAO_CODIGO. */
	public static final Long TIPO_HISTORICO_ALTERACAO_CODIGO = 1L;

	/** Constante TIPO_HISTORICO_ENTRADA_CODIGO. */
	public static final Long TIPO_HISTORICO_ENTRADA_CODIGO = 2L;

	/** Constante TIPO_HISTORICO_BAIXA_CODIGO. */
	public static final Long TIPO_HISTORICO_BAIXA_CODIGO = 3L;

	/** Constante ADICAO_A_BEM_PRINCIPAL_CODIGO. */
	public static final Long ADICAO_A_BEM_PRINCIPAL_CODIGO = 4L;

	/** Constante TRANSFERENCIA_INTERNA_CODIGO. */
	public static final Long TRANSFERENCIA_INTERNA_CODIGO = 5L;

	/** Constante TIPO_HISTORICO_ATRIBUIR_DETENTOR_CODIGO. */
	public static final Long TIPO_HISTORICO_ATRIBUIR_DETENTOR_CODIGO = 6L;

	/** Atributo TIPO_MOVIMENTO_CONTA_CONTABIL. */
	public static final String TIPO_MOVIMENTO_CONTA_CONTABIL = "tipoMovimentoContaContabil";

	/** Atributo TIPO_MOVIMENTO_CONTA_CONTABIL_ENTRADA_ORCAMENTARIA_CODIGO. */
	public static final Long TIPO_MOVIMENTO_CONTA_CONTABIL_ENTRADA_ORCAMENTARIA_CODIGO = 1L;

	/** Atributo TIPO_MOVIMENTO_CONTA_CONTABIL_ENTRADA_EXTRAORCAMENTARIA_CODIGO. */
	public static final Long TIPO_MOVIMENTO_CONTA_CONTABIL_ENTRADA_EXTRAORCAMENTARIA_CODIGO = 3L;

	/** Atributo TIPO_MOVIMENTO_CONTA_CONTABIL_BAIXA_ORCAMENTARIA_CODIGO. */
	public static final Long TIPO_MOVIMENTO_CONTA_CONTABIL_BAIXA_ORCAMENTARIA_CODIGO = 2L;

	/** Atributo TIPO_MOVIMENTO_CONTA_CONTABIL_BAIXA_EXTRAORCAMENTARIA_CODIGO. */
	public static final Long TIPO_MOVIMENTO_CONTA_CONTABIL_BAIXA_EXTRAORCAMENTARIA_CODIGO = 4L;

	/** Atributo TIPO_ENTRADA. */
	public static final String TIPO_ENTRADA = "tipoEntrada";

	/** Atributo TIPO_ENTRADA_ORCAMENTARIA. */
	public static final String TIPO_ENTRADA_ORCAMENTARIA = "ORCAMENTARIA";

	/** Atributo TIPO_ENTRADA_EXTRA_ORCAMENTARIA. */
	public static final String TIPO_ENTRADA_EXTRA_ORCAMENTARIA = "EXTRA_ORCAMENTARIA";

	/** Atributo TIPO_ENTRADA_ORCAMENTARIA_CODIGO. */
	public static final Long TIPO_ENTRADA_ORCAMENTARIA_CODIGO = 1L;

	/** Atributo TIPO_ENTRADA_EXTRA_ORCAMENTARIA_CODIGO. */
	public static final Long TIPO_ENTRADA_EXTRA_ORCAMENTARIA_CODIGO = 2L;

	/** Atributo TIPO_MOVIMENTACAO. */
	public static final String TIPO_MOVIMENTACAO = "tipoMovimentacao";

	/** Atributo TIPO_MOVIMENTACAO_TRANSFERENCIA. */
	public static final Long TIPO_MOVIMENTACAO_TRANSFERENCIA = 1L;

	/** Atributo TIPO_MOVIMENTACAO_DEFINICAO_DETENTOR. */
	public static final Long TIPO_MOVIMENTACAO_DEFINICAO_DETENTOR = 2L;

	 /** Atributo TIPO_MOVIMENTACAO_COLABORADOR. */
	public static final Long TIPO_MOVIMENTACAO_COLABORADOR = 3L;

	/** Atributo TIPO_MOVIMENTACAO_UNIDADE. */
	public static final Long TIPO_MOVIMENTACAO_UNIDADE = 4L;

	/** Atributo TIPO_RECEBIMENTO. */
	public static final String TIPO_RECEBIMENTO = "tipoRecebimento";

	/** Atributo TIPO_RECEBIMENTO_COMPRA. */
	public static final String TIPO_RECEBIMENTO_COMPRA = "COMPRA";

	/** Atributo TIPO_RECEBIMENTO_CESSAO. */
	public static final String TIPO_RECEBIMENTO_CESSAO = "CESSAO";

	/** Atributo TIPO_RECEBIMENTO_DOACAO. */
	public static final String TIPO_RECEBIMENTO_DOACAO = "DOACAO";

	/** Atributo TIPO_RECEBIMENTO_PERMUTA. */
	public static final String TIPO_RECEBIMENTO_PERMUTA = "PERMUTA";

	/** Atributo TIPO_RECEBIMENTO_TRANSFERENCIA. */
	public static final String TIPO_RECEBIMENTO_TRANSFERENCIA = "TRANSFERENCIA";

	/** Atributo TIPO_RECEBIMENTO_PRODUCAO_INTERNA. */
	public static final String TIPO_RECEBIMENTO_PRODUCAO_INTERNA = "PRODUCAO_INTERNA";

	/** Atributo TIPO_RECEBIMENTO_COMPRA_CODIGO. */
	public static final Long TIPO_RECEBIMENTO_COMPRA_CODIGO = 1L;

	/** Atributo TIPO_RECEBIMENTO_CESSAO_CODIGO. */
	public static final Long TIPO_RECEBIMENTO_CESSAO_CODIGO = 2L;

	/** Atributo TIPO_RECEBIMENTO_DOACAO_CODIGO. */
	public static final Long TIPO_RECEBIMENTO_DOACAO_CODIGO = 3L;

	/** Atributo TIPO_RECEBIMENTO_PERMUTA_CODIGO. */
	public static final Long TIPO_RECEBIMENTO_PERMUTA_CODIGO = 4L;

	/** Atributo TIPO_RECEBIMENTO_TRANSFERENCIA_CODIGO. */
	public static final Long TIPO_RECEBIMENTO_TRANSFERENCIA_CODIGO = 5L;

	/** Atributo TIPO_RECEBIMENTO_PRODUCAO_INTERNA_CODIGO. */
	public static final Long TIPO_RECEBIMENTO_PRODUCAO_INTERNA_CODIGO = 6L;

	/** Atributo TIPO_FERIADO. */
	public static final String TIPO_FERIADO = "tipoFeriado";

	/** Atributo TIPO_FERIADO_DATA_CODIGO. */
	public static final Long TIPO_FERIADO_DATA_CODIGO = 1L;

	/** Atributo TIPO_FERIADO_PERIODO_CODIGO. */
	public static final Long TIPO_FERIADO_PERIODO_CODIGO = 2L;

	/** Atributo TIPO_FERIADO. */
	public static final String ABRANGENCIA_FERIADO = "abrangenciaFeriado";

	/** Atributo ABRANGENCIA_FERIADO_MUNDIAL_CODIGO. */
	public static final Long ABRANGENCIA_FERIADO_MUNDIAL_CODIGO = 1L;

	/** Atributo ABRANGENCIA_FERIADO_NACIONAL_CODIGO. */
	public static final Long ABRANGENCIA_FERIADO_NACIONAL_CODIGO = 2L;

	/** Atributo ABRANGENCIA_FERIADO_ESTADUAL_CODIGO. */
	public static final Long ABRANGENCIA_FERIADO_ESTADUAL_CODIGO = 3L;

	/** Atributo ABRANGENCIA_FERIADO_MUNICIPAL_CODIGO. */
	public static final Long ABRANGENCIA_FERIADO_MUNICIPAL_CODIGO = 4L;

	/** Atributo TIPO_EXCECAO_FERIADO. */
	public static final String TIPO_EXCECAO_FERIADO = "tipoExcecaoFeriado";

	/** Atributo TIPO_EXCECAO_FERIADO_FOLGA_CODIGO. */
	public static final Long TIPO_EXCECAO_FERIADO_FOLGA_CODIGO = 1L;

	/** Atributo TIPO_EXCECAO_FERIADO_TRABALHO_CODIGO. */
	public static final Long TIPO_EXCECAO_FERIADO_TRABALHO_CODIGO = 2L;

	/** Atributo TIPO_WIDGET. */
	public static final String TIPO_WIDGET = "tipoWidget";

	/** Atributo TIPO_HIGHCHART. */
	public static final String TIPO_HIGHCHART = "tipoHighChart";

	/** Atributo TIPO_IDIOMA. */
	public static final String TIPO_IDIOMA = "tipoIdioma";

	/** Atributo TIPO_IDIOMA_PT_BR. */
	public static final String TIPO_IDIOMA_PT_BR = "pt_BR";

	/** Atributo TIPO_IDIOMA_EN_US. */
	public static final String TIPO_IDIOMA_EN_US = "en_US";

	/** Atributo TIPO_IDIOMA_ES_ES. */
	public static final String TIPO_IDIOMA_ES_ES = "es_ES";

	/** Atributo TIPO_IDIOMA_PT_BR_CODIGO. */
	public static final Long TIPO_IDIOMA_PT_BR_CODIGO = 1L;

	/** Atributo TIPO_IDIOMA_EN_US_CODIGO. */
	public static final Long TIPO_IDIOMA_EN_US_CODIGO = 2L;

	/** Atributo TIPO_IDIOMA_ES_ES_CODIGO. */
	public static final Long TIPO_IDIOMA_ES_ES_CODIGO = 3L;


	/** SISTEMA CONTRATOS */

	/** Atributo TIPO_MODALIDADE_CONTRATO_TOMADA_PRECO. Item da Chave tipoModalidadeContrato */
	public static final String TIPO_MODALIDADE_CONTRATO_TOMADA_PRECO = "TOMADA_PRECO";

	/** Atributo TIPO_MODALIDADE_CONTRATO_CONCURSO. Item da Chave tipoModalidadeContrato */
	public static final String TIPO_MODALIDADE_CONTRATO_CONCURSO = "CONCURSO";

	/** Atributo TIPO_MODALIDADE_CONTRATO_PREGAO. Item da Chave tipoModalidadeContrato */
	public static final String TIPO_MODALIDADE_CONTRATO_PREGAO = "PREGAO";

	/** Atributo TIPO_CONTRATO. Chave */
	public static final String TIPO_CONTRATO = "tipoContrato";

	/** Atributo TIPO_CONTRATO_ADMINISTRATIVO. Chave tipoContrato*/
	public static final String TIPO_CONTRATO_ADMINISTRATIVO = "ADMINISTRATIVO";

	/** Atributo TIPO_CONTRATO_POR_EMPENHO. Chave tipoContrato*/
	public static final String TIPO_CONTRATO_POR_EMPENHO = "POR_EMPENHO";

	/** Atributo TIPO_CONTRATO_POR_MODALIDADE. Chave tipoContrato*/
	public static final String TIPO_CONTRATO_POR_MODALIDADE = "POR_MODALIDADE";

	/** Atributo TIPO_RESPOSTA_QUESTAO. Chave */
	public static final String TIPO_RESPOSTA_QUESTAO = "tipoRespostaQuestao";

	/** Atributo TIPO_RESPOSTA_QUESTAO_TEXTO. */
	public static final String TIPO_RESPOSTA_QUESTAO_TEXTO = "TEXTO_LIVRE";

	/** Atributo TIPO_RESPOSTA_QUESTAO_TEXTO_CODIGO. */
	public static final Long TIPO_RESPOSTA_QUESTAO_TEXTO_CODIGO = 1L;

	/** Atributo TIPO_RESPOSTA_QUESTAO_MULTIPLA_ESCOLHA. */
	public static final String TIPO_RESPOSTA_QUESTAO_MULTIPLA_ESCOLHA = "MULTIPLA_ESCOLHA";

	/** Atributo TIPO_RESPOSTA_QUESTAO_MULTIPLA_ESCOLHA_CODIGO. */
	public static final Long TIPO_RESPOSTA_QUESTAO_MULTIPLA_ESCOLHA_CODIGO = 2L;

	/** Atributo TIPO_RESPOSTA_QUESTAO_BOOLEAN. */
	public static final String TIPO_RESPOSTA_QUESTAO_BOOLEAN = "BOOLEAN";

	/** Atributo TIPO_RESPOSTA_QUESTAO_BOOLEAN_CODIGO. */
	public static final Long TIPO_RESPOSTA_QUESTAO_BOOLEAN_CODIGO = 3L;

	/** Atributo TIPO_PAPEL_EQUIPE_CONTRATOS. Chave */
	public static final String TIPO_PAPEL_EQUIPE_CONTRATOS = "tipoPapelEquipeContratos";

	/** Atributo TIPO_PAPEL_EQUIPE_CONTRATOS_GESTOR. Chave tipoPapelEquipeContratos*/
	public static final String TIPO_PAPEL_EQUIPE_CONTRATOS_GESTOR = "GESTOR";

	/** Atributo TIPO_PAPEL_EQUIPE_CONTRATOS_GESTOR_SUBSTITUTO. Chave tipoPapelEquipeContratos*/
	public static final String TIPO_PAPEL_EQUIPE_CONTRATOS_GESTOR_SUBSTITUTO = "GESTOR_SUBSTITUTO";

	/** Atributo TIPO_PAPEL_EQUIPE_CONTRATOS_FISCAL_TECNICO. Chave tipoPapelEquipeContratos*/
	public static final String TIPO_PAPEL_EQUIPE_CONTRATOS_FISCAL_TECNICO = "FISCAL_TECNICO";

	/** Atributo TIPO_PAPEL_EQUIPE_CONTRATOS_FISCAL_ADMINISTRATIVO. Chave tipoPapelEquipeContratos*/
	public static final String TIPO_PAPEL_EQUIPE_CONTRATOS_FISCAL_ADMINISTRATIVO = "FISCAL_ADMINISTRATIVO";

	/** Atributo TIPO_PAPEL_EQUIPE_CONTRATOS_FISCAL_SOLICITANTE. Chave tipoPapelEquipeContratos*/
	public static final String TIPO_PAPEL_EQUIPE_CONTRATOS_FISCAL_SOLICITANTE = "FISCAL_SOLICITANTE";

	/** Atributo Tipo Implementacao Chave e usado no cadastro Regra de Eventos */
	public static final String TIPO_IMPLEMENTACAO = "tipoImplementacao";

	/** Atributo TIPO_REITERACAO. Chave */
	public static final String TIPO_REITERACAO = "tipoReiteracao";

	/** Atributo TIPO_REITERACAO_FINAL_PERIODO. Item da Chave tipoReiteracao */
	public static final String TIPO_REITERACAO_FINAL_PERIODO = "FINAL_PERIODO";

	/** Atributo TIPO_REITERACAO_QUANTIDADE_DEFINIDA. Item da Chave tipoReiteracao */
	public static final String TIPO_REITERACAO_QUANTIDADE_DEFINIDA = "QUANTIDADE_DEFINIDA";

	/** Atributo TIPO_REITERACAO. Chave */
	public static final String TIPO_MODALIDADE_CONTRATO = "tipoModalidadeContrato";

	/** Atributo TIPO_MODALIDADE_CONTRATO_CONCORRENCIA. Item da Chave tipoModalidadeContrato */
	public static final String TIPO_MODALIDADE_CONTRATO_CONCORRENCIA = "CONCORRENCIA";

	/** Atributo TIPO_MODALIDADE_CONTRATO_CONVITE. Item da Chave tipoModalidadeContrato */
	public static final String TIPO_MODALIDADE_CONTRATO_CONVITE = "CONVITE";

	/** Atributo DOMINIO_TIPO_INVENTARIO. */
	public static final String DOMINIO_TIPO_INVENTARIO = "tipoInventario";

	/** Atributo CODIGO_TIPO_INVENTARIO_ANUAL. */
	public static final Long CODIGO_TIPO_INVENTARIO_ANUAL = 1l;

	/** Atributo DOMINIO_STATUS_INVENTARIO. */
	public static final String DOMINIO_STATUS_INVENTARIO = "tipoStatusInventario";

	/** Atributo CODIGO_STATUS_INVENTARIO_ABERTO. */
	public static final Long CODIGO_STATUS_INVENTARIO_ABERTO = 1l;

	/** Atributo CODIGO_STATUS_INVENTARIO_EM_ANDAMENTO. */
	public static final Long CODIGO_STATUS_INVENTARIO_EM_ANDAMENTO = 2l;

	/** Atributo CODIGO_STATUS_INVENTARIO_ENCERRADO. */
	public static final Long CODIGO_STATUS_INVENTARIO_ENCERRADO = 3l;

	/** Atributo CODIGO_STATUS_INVENTARIO_AGUARDANDO_MOBILE. */
	public static final Long CODIGO_STATUS_INVENTARIO_AGUARDANDO_MOBILE = 4l;

	/** Atributo CODIGO_STATUS_INVENTARIO_INICIADO_MOBILE. */
	public static final Long CODIGO_STATUS_INVENTARIO_INICIADO_MOBILE = 5l;

	/** Atributo CODIGO_STATUS_INVENTARIO_FINALIZADO_MOBILE. */
	public static final Long CODIGO_STATUS_INVENTARIO_FINALIZADO_MOBILE = 6l;

	/** Atributo DOMINIO_STATUS_INVENTARIO_BEM_PATRIMONIAL. */
	public static final String DOMINIO_STATUS_INVENTARIO_BEM_PATRIMONIAL = "tipoStatusInventarioBemPatrimonial";

	/** Atributo CODIGO_STATUS_INVENTARIO_BEM_NAO_TRATATO. */
	public static final Long CODIGO_STATUS_INVENTARIO_BEM_NAO_TRATATO = 1l;

	/** Atributo CODIGO_STATUS_INVENTARIO_BEM_NAO_HA_INCONSISTENCIA. */
	public static final Long CODIGO_STATUS_INVENTARIO_BEM_NAO_HA_INCONSISTENCIA = 2l;

	/** Atributo CODIGO_STATUS_INVENTARIO_BEM_INCONSISTENCIA. */
	public static final Long CODIGO_STATUS_INVENTARIO_BEM_INCONSISTENCIA = 3l;

	/** Atributo CODIGO_STATUS_INVENTARIO_BEM_TRATADA. */
	public static final Long CODIGO_STATUS_INVENTARIO_BEM_TRATADA = 4l;

	/** Atributo DOMINIO_STATUS_INVENTARIO_ESTRUTURA. */
	public static final String DOMINIO_STATUS_INVENTARIO_ESTRUTURA = "tipoStatusInventarioEstrutura";

	/** Atributo DESCRICAO_STATUS_INVENTARIO_ESTRUTURA_ABERTO. */
	public static final String DESCRICAO_STATUS_INVENTARIO_ESTRUTURA_ABERTO = "Aberto";

	/** Atributo DESCRICAO_STATUS_INVENTARIO_ESTRUTURA_EM_CAMPO. */
	public static final String DESCRICAO_STATUS_INVENTARIO_ESTRUTURA_EM_CAMPO = "Em campo";

	/** Atributo DESCRICAO_STATUS_INVENTARIO_ESTRUTURA_EM_TRATAMENTO. */
	public static final String DESCRICAO_STATUS_INVENTARIO_ESTRUTURA_EM_TRATAMENTO = "Em tratamento";

	/** Atributo DESCRICAO_STATUS_INVENTARIO_ESTRUTURA_ENCERRADOO. */
	public static final String DESCRICAO_STATUS_INVENTARIO_ESTRUTURA_ENCERRADOO = "Encerrado";

	/** Atributo CODIGO_STATUS_INVENTARIO_ESTRUTURA_ABERTO. */
	public static final Long CODIGO_STATUS_INVENTARIO_ESTRUTURA_ABERTO = 1l;

	/** Atributo CODIGO_STATUS_INVENTARIO_ESTRUTURA_EM_CAMPO. */
	public static final Long CODIGO_STATUS_INVENTARIO_ESTRUTURA_EM_CAMPO = 2l;

	/** Atributo CODIGO_STATUS_INVENTARIO_ESTRUTURA_EM_TRATAMENTO. */
	public static final Long CODIGO_STATUS_INVENTARIO_ESTRUTURA_EM_TRATAMENTO = 3l;

	/** Atributo CODIGO_STATUS_INVENTARIO_ESTRUTURA_ENCERRADO. */
	public static final Long CODIGO_STATUS_INVENTARIO_ESTRUTURA_ENCERRADO = 4l;

	/** Atributo DOMINIO_TIPO_OBJETIVO_INVENTARIO. */
	public static final String DOMINIO_TIPO_OBJETIVO_INVENTARIO = "tipoObjetivoInventario";

	/** Atributo DOMINIO_STATUS_INCONSISTENCIA_INVENTARIADO. */
	public static final String DOMINIO_STATUS_INCONSISTENCIA_INVENTARIADO = "tipoDominioInconsistencia";

	/** Atributo CODIGO_TIPO_INCONSISTENCIA_BEM_NAO_ENCONTRADO. */
	public static final Long CODIGO_TIPO_INCONSISTENCIA_BEM_NAO_ENCONTRADO = 1l;

	/** Atributo CODIGO_TIPO_INCONSISTENCIA_ESTRUTURA_ORGANIZACIONAL. */
	public static final Long CODIGO_TIPO_INCONSISTENCIA_ESTRUTURA_ORGANIZACIONAL = 2l;

	/** Atributo CODIGO_TIPO_INCONSISTENCIA_MATERIAL. */
	public static final Long CODIGO_TIPO_INCONSISTENCIA_MATERIAL = 3l;

	/** Atributo CODIGO_TIPO_INCONSISTENCIA_SITUACAO_FISICA. */
	public static final Long CODIGO_TIPO_INCONSISTENCIA_SITUACAO_FISICA = 4l;

	/** Atributo CODIGO_TIPO_INCONSISTENCIA_STATUS. */
	public static final Long CODIGO_TIPO_INCONSISTENCIA_STATUS = 5l;

	/** Atributo CODIGO_TIPO_INCONSISTENCIA_VALOR. */
	public static final Long CODIGO_TIPO_INCONSISTENCIA_VALOR = 6l;

	/** Atributo CODIGO_TIPO_INCONSISTENCIA_RESPONSAVEL. */
	public static final Long CODIGO_TIPO_INCONSISTENCIA_RESPONSAVEL = 7l;

	/** Atributo CODIGO_TIPO_INCONSISTENCIA_DETENTOR. */
	public static final Long CODIGO_TIPO_INCONSISTENCIA_DETENTOR = 8l;

	/** Atributo CODIGO_TIPO_INCONSISTENCIA_BEM_PRINCIPAL. */
	public static final Long CODIGO_TIPO_INCONSISTENCIA_BEM_PRINCIPAL = 9l;

	/** Atributo CODIGO_TIPO_INCONSISTENCIA_BEM_ENCONTRADO_SEM_PLAQUETA. */
	public static final Long CODIGO_TIPO_INCONSISTENCIA_BEM_ENCONTRADO_SEM_PLAQUETA = 10l;

	/** Atributo DOMINIO_TIPO_LOCAL_INCONSISTENCIA. */
	public static final String DOMINIO_TIPO_LOCAL_INCONSISTENCIA = "tipoLocalInconsistencia";

	/** Atributo CODIGO_TIPO_LOCAL_INCONSISTENCIA_SISTEMA. */
	public static final Long CODIGO_TIPO_LOCAL_INCONSISTENCIA_SISTEMA = 1l;

	/** Atributo CODIGO_TIPO_LOCAL_INCONSISTENCIA_MANUAL. */
	public static final Long CODIGO_TIPO_LOCAL_INCONSISTENCIA_MANUAL = 2l;

	/** Atributo CODIGO_TIPO_LOCAL_INCONSISTENCIA_MOBILE. */
	public static final Long CODIGO_TIPO_LOCAL_INCONSISTENCIA_MOBILE = 3l;

	/** Atributo TIPO_ESTRUTURA_ORGANIZACIONAL. */
	public static final String TIPO_ESTRUTURA_ORGANIZACIONAL = "tipoEstruturaOrganizacional";

	/** Atributo TIPO_UNIDADE_GESTORA. */
	public static final Long TIPO_UNIDADE_GESTORA = 1L;

	/** Atributo TIPO_UNIDADE_ADMINISTRATIVA. */
	public static final Long TIPO_UNIDADE_ADMINISTRATIVA = 2L;

	/** Atributo TIPO_UNIDADE_COLETA. */
	public static final Long TIPO_UNIDADE_COLETA = 3L;

	/** Atributo COMISSAO_TIPO. */
	public static final String COMISSAO_TIPO = "tipoComissao";

	/** Atributo COMISSAO_DESCRICAO_INVENTARIO. */
	public static final String COMISSAO_DESCRICAO_INVENTARIO = "Inventário";

	/** Atributo COMISSAO_DESCRICAO_DESFAZIMENTO. */
	public static final String COMISSAO_DESCRICAO_DESFAZIMENTO = "Desfazimento";

	/** Atributo COMISSAO_NOME_INVENTARIO. */
	public static final String COMISSAO_NOME_INVENTARIO = "INVENTARIO";

	/** Atributo COMISSAO_NOME_DESFAZIMENTO. */
	public static final String COMISSAO_NOME_DESFAZIMENTO = "DESFAZIMENTO";

	/** Atributo COMISSAO_CODIGO_INVENTARIO. */
	public static final Long COMISSAO_CODIGO_INVENTARIO = 1L;

	/** Atributo COMISSAO_CODIGO_DESFAZIMENTO. */
	public static final Long COMISSAO_CODIGO_DESFAZIMENTO = 2L;



	/** Atributo COMISSAO_TIPO. */
	public static final String TIPO_DOCUMENTO_CAIXA = "tipoDocumentoCaixa";

	/** Atributo TIPO_DOCUMENTO_CAIXA_DESCRICAO_POS. */
	public static final String TIPO_DOCUMENTO_CAIXA_DESCRICAO_POS = "P.O.S";

	/** Atributo TIPO_DOCUMENTO_CAIXA_DESCRICAO_REDUCAOZ. */
	public static final String TIPO_DOCUMENTO_CAIXA_DESCRICAO_REDUCAOZ = "Redução Z";

	/** Atributo TIPO_DOCUMENTO_CAIXA_DESCRICAO_PANILHA_EXCEL. */
	public static final String TIPO_DOCUMENTO_CAIXA_DESCRICAO_PANILHA_EXCEL = "Planilha de Excel";

	/** Atributo TIPO_DOCUMENTO_CAIXA_DESCRICAO_RELATORIO_SERVICAOS_NFATURADOS. */
	public static final String TIPO_DOCUMENTO_CAIXA_DESCRICAO_RELATORIO_SERVICAOS_NFATURADOS = "Relatório de serviços não faturados";

	/** Atributo TIPO_DOCUMENTO_CAIXA_DESCRICAO_RELATORIO_DIARIO_FATURAMENTO. */
	public static final String TIPO_DOCUMENTO_CAIXA_DESCRICAO_RELATORIO_DIARIO_FATURAMENTO = "Relatório diário de faturamento";

	/** Atributo TIPO_DOCUMENTO_CAIXA_DESCRICAO_TELA_FATURAMENTO_SISTEMA_LOJA. */
	public static final String TIPO_DOCUMENTO_CAIXA_DESCRICAO_TELA_FATURAMENTO_SISTEMA_LOJA = "Tela faturamento sistema da loja";

	/** Atributo TIPO_DOCUMENTO_CAIXA_NOME_POS. */
	public static final String TIPO_DOCUMENTO_CAIXA_NOME_POS = "POS";

	/** Atributo TIPO_DOCUMENTO_CAIXA_NOME_REDUCAOZ. */
	public static final String TIPO_DOCUMENTO_CAIXA_NOME_REDUCAOZ = "REDUCAOZ";

	/** Atributo TIPO_DOCUMENTO_CAIXA_NOME_PANILHA_EXCEL. */
	public static final String TIPO_DOCUMENTO_CAIXA_NOME_PANILHA_EXCEL = "PANILHA_EXCEL";

	/** Atributo TIPO_DOCUMENTO_CAIXA_NOME_RELATORIO_SERVICAOS_NFATURADOS. */
	public static final String TIPO_DOCUMENTO_CAIXA_NOME_RELATORIO_SERVICAOS_NFATURADOS = "RELATORIO_SERVICAOS_NFATURADOS";

	/** Atributo TIPO_DOCUMENTO_CAIXA_NOME_RELATORIO_DIARIO_FATURAMENTO. */
	public static final String TIPO_DOCUMENTO_CAIXA_NOME_RELATORIO_DIARIO_FATURAMENTO = "RELATORIO_DIARIO_FATURAMENTO";

	/** Atributo TIPO_DOCUMENTO_CAIXA_NOME_TELA_FATURAMENTO_SISTEMA_LOJA. */
	public static final String TIPO_DOCUMENTO_CAIXA_NOME_TELA_FATURAMENTO_SISTEMA_LOJA = "TELA_FATURAMENTO_SISTEMA_LOJA";

	/** Atributo TIPO_DOCUMENTO_CAIXA_CODIGO_POS. */
	public static final Long TIPO_DOCUMENTO_CAIXA_CODIGO_POS = 1L;

	/** Atributo TIPO_DOCUMENTO_CAIXA_CODIGO_REDUCAOZ. */
	public static final Long TIPO_DOCUMENTO_CAIXA_CODIGO_REDUCAOZ = 2L;

	/** Atributo TIPO_DOCUMENTO_CAIXA_CODIGO_PANILHA_EXCEL. */
	public static final Long TIPO_DOCUMENTO_CAIXA_CODIGO_PANILHA_EXCEL = 3L;

	/** Atributo TIPO_DOCUMENTO_CAIXA_CODIGO_RELATORIO_SERVICAOS_NFATURADOS. */
	public static final Long TIPO_DOCUMENTO_CAIXA_CODIGO_RELATORIO_SERVICAOS_NFATURADOS = 4L;

	/** Atributo TIPO_DOCUMENTO_CAIXA_CODIGO_RELATORIO_DIARIO_FATURAMENTO. */
	public static final Long TIPO_DOCUMENTO_CAIXA_CODIGO_RELATORIO_DIARIO_FATURAMENTO = 5L;

	/** Atributo TIPO_DOCUMENTO_CAIXA_CODIGO_TELA_FATURAMENTO_SISTEMA_LOJA. */
	public static final Long TIPO_DOCUMENTO_CAIXA_CODIGO_TELA_FATURAMENTO_SISTEMA_LOJA = 6L;


	/** Atributo TIPO_RECOLHIMENTO_CAIXA. */
	public static final String TIPO_RECOLHIMENTO_CAIXA = "tipoRecolhimentoCaixa";

	/** Atributo TIPO_RECOLHIMENTO_DESCRICAO_RECOLHIMENTO_1. */
	public static final String TIPO_RECOLHIMENTO_DESCRICAO_RECOLHIMENTO_1 = "Recolhimento 1";

	/** Atributo TIPO_RECOLHIMENTO_DESCRICAO_RECOLHIMENTO_2. */
	public static final String TIPO_RECOLHIMENTO_DESCRICAO_RECOLHIMENTO_2 = "Recolhimento 2";

	/** Atributo TIPO_RECOLHIMENTO_NOME_RECOLHIMENTO_1. */
	public static final String TIPO_RECOLHIMENTO_NOME_RECOLHIMENTO_1 = "RECOLHIMENTO_1";

	/** Atributo TIPO_RECOLHIMENTO_NOME_RECOLHIMENTO_2. */
	public static final String TIPO_RECOLHIMENTO_NOME_RECOLHIMENTO_2 = "RECOLHIMENTO_2";

	/** Atributo TIPO_RECOLHIMENTO_CODIGO_RECOLHIMENTO_1. */
	public static final Long TIPO_RECOLHIMENTO_CODIGO_RECOLHIMENTO_1 = 1L;

	/** Atributo TIPO_RECOLHIMENTO_CODIGO_RECOLHIMENTO_2. */
	public static final Long TIPO_RECOLHIMENTO_CODIGO_RECOLHIMENTO_2 = 2L;


	/**
	 * Retorna o valor do atributo <code>id</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getId() {

		return this.id;
	}

	/**
	 * Define o valor do atributo <code>id</code>.
	 *
	 * @param nome
	 */
	public void setId(Long id) {

		this.id = id;
	}

	/**
	 * Retorna o valor do atributo <code>nome</code>
	 *
	 * @return <code>String</code>
	 */
	public String getNome() {

		return nome;
	}

	/**
	 * Define o valor do atributo <code>nome</code>.
	 *
	 * @param nome
	 */
	public void setNome(String nome) {

		this.nome = nome;
	}

	/**
	 * Retorna o valor do atributo <code>descricao</code>
	 *
	 * @return <code>String</code>
	 */
	public String getDescricao() {

		return descricao;
	}

	/**
	 * Define o valor do atributo <code>descricao</code>.
	 *
	 * @param descricao
	 */
	public void setDescricao(String descricao) {

		this.descricao = descricao;
	}

	/**
	 * Retorna o valor do atributo <code>chave</code>
	 *
	 * @return <code>String</code>
	 */
	public String getChave() {

		return chave;
	}

	/**
	 * Define o valor do atributo <code>chave</code>.
	 *
	 * @param chave
	 */
	public void setChave(String chave) {

		this.chave = chave;
	}

	/**
	 * Retorna o valor do atributo <code>serialversionuid</code>
	 *
	 * @return <code>long</code>
	 */
	public static long getSerialversionuid() {

		return serialVersionUID;
	}

	/**
	 * Retorna o valor do atributo <code>codigo</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getCodigo() {

		return codigo;
	}

	/**
	 * Define o valor do atributo <code>codigo</code>.
	 *
	 * @param codigo
	 */
	public void setCodigo(Long codigo) {

		this.codigo = codigo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {

		return "Dominio [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", chave=" + chave + ", codigo=" + codigo + "]";
	}

}
