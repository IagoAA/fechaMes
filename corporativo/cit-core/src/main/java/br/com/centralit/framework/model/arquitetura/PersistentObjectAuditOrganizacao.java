package br.com.centralit.framework.model.arquitetura;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.centralit.framework.model.Organizacao;
import br.com.centralit.framework.model.Usuario;

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
 * @since 19/02/2015 - 09:05:45
 * 
 * @version 1.0.0
 * 
 * @author wilker.machado
 * 
 */
@MappedSuperclass
public abstract class PersistentObjectAuditOrganizacao extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 2608203858830263946L;

	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	protected Organizacao organizacao;

	/**
	 * Retorna o valor do atributo <code>organizacao</code>
	 * 
	 * @return <code>Organizacao</code>
	 */
	public Organizacao getOrganizacao() {

		return organizacao;
	}

	/**
	 * Define o valor do atributo <code>organizacao</code>.
	 * 
	 * @param organizacao
	 */
	public void setOrganizacao(Organizacao organizacao) {

		this.organizacao = organizacao;
	}

	@PrePersist
	public void prePersist() {

		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Usuario) {
				this.setOrganizacao(((Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getOrganizacao());
			}
		}
		
	}

	@PreUpdate
	public void preUpdate() {

		if(SecurityContextHolder.getContext().getAuthentication() != null){
			if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Usuario) {
				this.setOrganizacao(((Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getOrganizacao());
			}
		}
		
	}

}
