package br.com.centralit.framework.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAuditOrganizacao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * @since 09/03/2015 - 17:33:22
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
@Entity
@JsonIgnoreProperties({ "model" })
public class Painel extends PersistentObjectAuditOrganizacao {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo nome. */
	@JsonView({ Views.PainelListView.class, Views.DashBoardListView.class })
	private String nome;

	/** Atributo ativo. */
	@JsonView({ Views.PainelEditView.class })
	private Boolean ativo;

	/** Atributo recebeAtualizacao. */
	@JsonView({ Views.PainelEditView.class })
	private Boolean recebeAtualizacao;

	/** Atributo painelItens. */
	@JsonView({ Views.PainelEditView.class })
	private String identificacao;

	/** Atributo uuid. */
	@JsonView({ Views.PainelEditView.class })
	private String uuid;

	/** Atributo regraDefinida. */
	private boolean regraDefinida;

	/** Atributo usuario. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonView({ Views.PainelEditView.class })
	private Usuario usuario;

	/** Atributo painelItens. */
	@JsonView({ Views.PainelEditView.class })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "painel", cascade = CascadeType.ALL)
	private Collection<PainelItem> painelItens;

	/** Atributo painelGrupos. */
	@JsonView({ Views.PainelEditView.class })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "painel", cascade = CascadeType.ALL)
	private Collection<PainelGrupo> painelGrupos;

	/** Atributo painelPrivilegios. */
	@JsonView({ Views.PainelEditView.class })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "painel", cascade = CascadeType.ALL)
	private Collection<PainelPrivilegio> painelPrivilegios;

	/** Atributo painelModulos. */
	@JsonView({ Views.PainelEditView.class, Views.DashBoardListView.class })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "painel", cascade = CascadeType.ALL)
	private Collection<PainelModulo> painelModulos;

	/**
	 * Retorna o valor do atributo <code>id</code>
	 * 
	 * @return <code>Long</code>
	 */
	@Override
	public Long getId() {

		return this.id;
	}

	/**
	 * Retorna o valor do atributo <code>nome</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getNome() {

		return this.nome;
	}

	/**
	 * Define o valor do atributo <code>nome</code>.
	 * 
	 * @param nome
	 */
	public void setNome(final String nome) {

		this.nome = nome;
	}

	/**
	 * Retorna o valor do atributo <code>painelItens</code>
	 * 
	 * @return <code>Collection<PainelItem></code>
	 */
	public Collection<PainelItem> getPainelItens() {

		return painelItens;
	}

	/**
	 * Define o valor do atributo <code>painelItens</code>.
	 * 
	 * @param painelItens
	 */
	public void setPainelItens(Collection<PainelItem> painelItens) {

		this.painelItens = painelItens;
	}

	/**
	 * Retorna o valor do atributo <code>ativo</code>
	 * 
	 * @return <code>Boolean</code>
	 */
	public Boolean getAtivo() {

		return ativo;
	}

	/**
	 * Define o valor do atributo <code>ativo</code>.
	 * 
	 * @param ativo
	 */
	public void setAtivo(Boolean ativo) {

		this.ativo = ativo;
	}

	/**
	 * Retorna o valor do atributo <code>recebeAtualizacao</code>
	 * 
	 * @return <code>Boolean</code>
	 */
	public Boolean getRecebeAtualizacao() {

		return recebeAtualizacao;
	}

	/**
	 * Define o valor do atributo <code>recebeAtualizacao</code>.
	 * 
	 * @param recebeAtualizacao
	 */
	public void setRecebeAtualizacao(Boolean recebeAtualizacao) {

		this.recebeAtualizacao = recebeAtualizacao;
	}

	/**
	 * Retorna o valor do atributo <code>identificacao</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getIdentificacao() {

		return identificacao;
	}

	/**
	 * Define o valor do atributo <code>identificacao</code>.
	 * 
	 * @param identificacao
	 */
	public void setIdentificacao(String identificacao) {

		this.identificacao = identificacao;
	}

	/**
	 * Retorna o valor do atributo <code>usuario</code>
	 * 
	 * @return <code>Usuario</code>
	 */
	public Usuario getUsuario() {

		return usuario;
	}

	/**
	 * Define o valor do atributo <code>usuario</code>.
	 * 
	 * @param usuario
	 */
	public void setUsuario(Usuario usuario) {

		this.usuario = usuario;
	}

	/**
	 * Retorna o valor do atributo <code>uuid</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getUuid() {

		return uuid;
	}

	/**
	 * Define o valor do atributo <code>uuid</code>.
	 * 
	 * @param uuid
	 */
	public void setUuid(String uuid) {

		this.uuid = uuid;
	}

	/**
	 * Retorna o valor do atributo <code>painelGrupos</code>
	 * 
	 * @return <code>Collection<PainelGrupo></code>
	 */
	public Collection<PainelGrupo> getPainelGrupos() {

		return painelGrupos;
	}

	/**
	 * Define o valor do atributo <code>painelGrupos</code>.
	 * 
	 * @param painelGrupos
	 */
	public void setPainelGrupos(Collection<PainelGrupo> painelGrupos) {

		this.painelGrupos = painelGrupos;
	}

	/**
	 * Retorna o valor do atributo <code>painelPrivilegios</code>
	 * 
	 * @return <code>Collection<PainelPrivilegio></code>
	 */
	public Collection<PainelPrivilegio> getPainelPrivilegios() {

		return painelPrivilegios;
	}

	/**
	 * Define o valor do atributo <code>painelPrivilegios</code>.
	 * 
	 * @param painelPrivilegios
	 */
	public void setPainelPrivilegios(Collection<PainelPrivilegio> painelPrivilegios) {

		this.painelPrivilegios = painelPrivilegios;
	}

	/**
	 * Define o valor do atributo <code>id</code>.
	 * 
	 * @param id
	 */
	public void setId(Long id) {

		this.id = id;
	}

	/**
	 * Retorna o valor do atributo <code>regraDefinida</code>
	 * 
	 * @return <code>Boolean</code>
	 */
	public boolean getRegraDefinida() {

		return regraDefinida;
	}

	/**
	 * Define o valor do atributo <code>regraDefinida</code>.
	 * 
	 * @param regraDefinida
	 */
	public void setRegraDefinida(boolean regraDefinida) {

		this.regraDefinida = regraDefinida;
	}

	
	/**
	 * Retorna o valor do atributo <code>painelModulos</code>
	 *
	 * @return <code>Collection<PainelModulo></code>
	 */
	public Collection<PainelModulo> getPainelModulos() {
	
		return painelModulos;
	}

	
	/**
	 * Define o valor do atributo <code>painelModulos</code>.
	 *
	 * @param painelModulos 
	 */
	public void setPainelModulos(Collection<PainelModulo> painelModulos) {
	
		this.painelModulos = painelModulos;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( uuid == null ) ? 0 : uuid.hashCode() );
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		Painel other = (Painel) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

}
