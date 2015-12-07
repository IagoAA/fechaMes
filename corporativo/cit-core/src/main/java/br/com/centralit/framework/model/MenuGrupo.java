package br.com.centralit.framework.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAuditOrganizacao;

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
 * @since 29/08/2015 - 18:26:57
 * 
 * @version 1.0.0
 * 
 * @author Rogério Gomes
 * 
 */
@Entity
public class MenuGrupo extends PersistentObjectAuditOrganizacao {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 7324734756663575561L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	private Long id;

	/** Atributo grupo. */
	@JsonView({ Views.MenuEditView.class })
	@ManyToOne(fetch = FetchType.EAGER)
	private Grupo grupo;

	@ManyToOne(fetch = FetchType.EAGER)
	private Menu menu;

	/** Atributo menuRemocao. */
	@ManyToOne
	private Menu menuRemocao;

	/**
	 * Retorna o valor do atributo <code>id</code>
	 * 
	 * @return <code>Long</code>
	 */
	public Long getId() {

		return id;
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
	 * Retorna o valor do atributo <code>grupo</code>
	 * 
	 * @return <code>Grupo</code>
	 */
	public Grupo getGrupo() {

		return grupo;
	}

	/**
	 * Define o valor do atributo <code>grupo</code>.
	 * 
	 * @param grupo
	 */
	public void setGrupo(Grupo grupo) {

		this.grupo = grupo;
	}

	/**
	 * Retorna o valor do atributo <code>menu</code>
	 * 
	 * @return <code>Menu</code>
	 */
	public Menu getMenu() {

		return menu;
	}

	/**
	 * Define o valor do atributo <code>menu</code>.
	 * 
	 * @param menu
	 */
	public void setMenu(Menu menu) {

		this.menu = menu;
	}

	/**
	 * Retorna o valor do atributo <code>menuRemocao</code>
	 * 
	 * @return <code>Menu</code>
	 */
	public Menu getMenuRemocao() {

		return menuRemocao;
	}

	/**
	 * Define o valor do atributo <code>menuRemocao</code>.
	 * 
	 * @param menuRemocao
	 */
	public void setMenuRemocao(Menu menuRemocao) {

		this.menuRemocao = menuRemocao;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( grupo == null ) ? 0 : grupo.hashCode() );
		return result;
	}

	@Override
	public boolean equals(Object obj) {

	
		MenuGrupo other = (MenuGrupo) obj;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.getId().equals(other.grupo.getId()))
			return false;
		return true;
	}
	
	

}
