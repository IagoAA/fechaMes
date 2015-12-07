package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.NotificacaoDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.Notificacao;
import br.com.centralit.framework.util.UtilColecao;

import com.googlecode.genericdao.search.Filter;

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
 * <b>Title: NotificacaoDaoHibernate</b>
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
 * @since 20/11/2014 - 09:55:04
 * 
 * @version 1.0.0
 * 
 * @author rodrigo.anaice
 * 
 */
@Repository("notificacaoDao")
public class NotificacaoDaoHibernate extends CitGenericDAOImpl implements NotificacaoDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public NotificacaoDaoHibernate() {

		super(Notificacao.class);
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por listar a entidade <code>Notificacao</code> através do id do usuario, desconsiderando registros com dataVisualização nula.
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idUsuario
	 * 
	 * @return Collection<Notificacao>
	 */
	public Collection<Notificacao> listarNotificacaoPorUsuarioGrupo(Long idUsuario, Collection<Long> listaIdGrupoUsuarios) {

		SearchSeven search = new SearchSeven();

		SearchSeven searchSevenNotificacaoUsuario = new SearchSeven();

		searchSevenNotificacaoUsuario.addField("identificador");

		searchSevenNotificacaoUsuario.addFilterEqual("usuario.id", idUsuario);

		searchSevenNotificacaoUsuario.addFilterNotEmpty("identificador");

		Collection<String> listaIdentificadoresNotificacaoUsuario = this.search(searchSevenNotificacaoUsuario, Notificacao.class);

		search.addFilterOr(Filter.in("notificacaoGrupos.grupo.id", listaIdGrupoUsuarios), Filter.equal("notificacaoUsuarios.usuario.id", idUsuario));

		if (!UtilColecao.isVazio(listaIdentificadoresNotificacaoUsuario)) {

			search.addFilterNotIn("identificador", listaIdentificadoresNotificacaoUsuario);
		}

		search.addFilterNull("dataVisualizacao");

		return this.search(search, Notificacao.class);
	}
}
