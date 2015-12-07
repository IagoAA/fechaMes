package br.com.centralit.api.service.impl;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.PainelItemDao;
import br.com.centralit.api.service.LinkService;
import br.com.centralit.api.service.PainelItemGrupoService;
import br.com.centralit.api.service.PainelItemPrivilegioService;
import br.com.centralit.api.service.PainelItemService;
import br.com.centralit.api.service.UsuarioService;
import br.com.centralit.framework.model.Link;
import br.com.centralit.framework.model.Painel;
import br.com.centralit.framework.model.PainelItem;
import br.com.centralit.framework.model.PainelItemGrupo;
import br.com.centralit.framework.model.PainelItemParametro;
import br.com.centralit.framework.model.PainelItemPrivilegio;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.model.WidgetParametro;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilColecao;
import br.com.centralit.framework.util.UtilObjeto;

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
 * @since 23/03/2015 - 10:39:21
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Service("painelItemService")
public class PainelItemServiceImpl extends GenericServiceImpl<PainelItem, Long> implements PainelItemService {

	/** Atributo painelItemDao. */
	@Autowired
	private PainelItemDao painelItemDao;

	@Autowired
	private LinkService linkService;

	/** Atributo painelItemGrupoService. */
	@Autowired
	private PainelItemGrupoService painelItemGrupoService;

	/** Atributo painelItemPrivilegioService. */
	@Autowired
	private PainelItemPrivilegioService painelItemPrivilegioService;

	/** Atributo usuarioService. */
	@Autowired
	private UsuarioService usuarioService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param painelItemDao
	 */
	@Autowired
	private PainelItemServiceImpl( PainelItemDao painelItemDao ) {

		this.dao = painelItemDao;

		this.painelItemDao = painelItemDao;

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
	 * Método responsável por resolver as pripriedades transiente
	 * 
	 * @author rogerio.costa
	 * 
	 * @param entity
	 */
	public void resolverPropriedadesTransiante(Painel entity) {

		for (PainelItem painelItem : entity.getPainelItens()) {

			painelItem.setPainel(entity);

			// Verifica se o painelItem é novo, caso não seja verifica a remoção do painelItemGrupo
			if (!painelItem.isNew()) {

				this.painelItemGrupoService.verificarRemocao(painelItem);

				this.painelItemPrivilegioService.verificarRemocao(painelItem);
			}

			// Verifica se a propriedade remover do painelItem é true, caso seja remove o painelItem, se não atualiza os dados do mesmo
			if (!painelItem.isNew() && UtilObjeto.isReferencia(painelItem.getRemover()) && painelItem.getRemover()) {

				this.remove(painelItem);

			} else {

				this.montarDadosPainelItem(entity, painelItem);
			}

		}
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
	 * Método responsável por montar os dados do PainelItem
	 * 
	 * @author rogerio.costa
	 * 
	 * @param entity
	 * @param painelItem
	 */
	private void montarDadosPainelItem(Painel entity, PainelItem painelItem) {

		if (!UtilColecao.isVazio(painelItem.getLinks())) {

			for (Link link : painelItem.getLinks()) {

				if (!link.isNew() && UtilObjeto.isReferencia(link.getRemover()) && link.getRemover()) {

					this.linkService.remove(link);
				} else {

					link.setPainelItem(painelItem);
				}

			}
		}

		if (!UtilColecao.isVazio(painelItem.getPainelItemGrupos())) {

			for (PainelItemGrupo painelItemGrupo : painelItem.getPainelItemGrupos()) {

				painelItemGrupo.setPainelItem(painelItem);

				painelItem.setRegraDefinida(true);
			}

		}

		if (!UtilColecao.isVazio(painelItem.getPainelItemPrivilegios())) {

			for (PainelItemPrivilegio painelItemPrivilegio : painelItem.getPainelItemPrivilegios()) {

				painelItemPrivilegio.setPainelItem(painelItem);

				painelItem.setRegraDefinida(true);

			}

		}

		// Verifica se o widget contem referência
		if (UtilObjeto.isReferencia(painelItem.getWidget())) {

			// Verifica se o painelItem já foi persistido, caso ainda não tenha sido persistido monta a lista de parametros.
			if (!UtilObjeto.isReferencia(painelItem.getId()) && !painelItem.isCopia()) {

				this.montarDadosPainelItemParametros(painelItem);
			} else {

				if (!UtilColecao.isVazio(painelItem.getPainelItemParametros())) {

					for (PainelItemParametro painelItemParametro : painelItem.getPainelItemParametros()) {

						painelItemParametro.setPainelItem(painelItem);

					}
				}

			}

		}
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
	 * Método responsável por
	 * 
	 * @author rogerio.costa
	 * 
	 * @param painelItem
	 * @param widget
	 */
	private void montarDadosPainelItemParametros(PainelItem painelItem) {

		Collection<PainelItemParametro> painelItemParametros = new LinkedList<PainelItemParametro>();

		if (!UtilColecao.isVazio(painelItem.getWidget().getParametros())) {

			for (WidgetParametro widgetParametro : painelItem.getWidget().getParametros()) {

				PainelItemParametro painelItemParametro = new PainelItemParametro();

				painelItemParametro.setNome(widgetParametro.getNome());
				painelItemParametro.setTipoWidgetParametroDominio(widgetParametro.getTipoWidgetParametroDominio());
				painelItemParametro.setChaveDominioDefault(widgetParametro.getChaveDominioDefault());
				painelItemParametro.setDominioDefault(widgetParametro.getDominioDefault());
				painelItemParametro.setTextoDefault(widgetParametro.getTextoDefault());
				painelItemParametro.setDataDefault(widgetParametro.getDataDefault());
				painelItemParametro.setNumeroDefault(widgetParametro.getNumeroDefault());
				painelItemParametro.setBooleanDefault(widgetParametro.getBooleanDefault());
				painelItemParametro.setAtributoFiltro(widgetParametro.getAtributoFiltro());
				painelItemParametro.setPainelItem(painelItem);

				painelItemParametros.add(painelItemParametro);

			}

			painelItem.setPainelItemParametros(painelItemParametros);
		}
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
	 * Método responsável por listar o painelItem através do id do painel
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idPainel
	 * 
	 * @return
	 */
	public Collection<PainelItem> findPorIdPainel(Long idPainel) {

		return this.painelItemDao.findPorIdPainel(idPainel);
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
	 * Método responsável por obter painelItem através do id do painel e regras de permissão do usuario logado
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idPainel
	 * @param usuario
	 * @return Collection<PainelItem>
	 */
	public Collection<PainelItem> findPorPainelEPermissaoUsuario(Long idPainel) {

		Usuario usuario = this.usuarioService.getReference(( (Usuario) this.getUsuario() ).getId());

		return this.painelItemDao.findPorPainelEPermissaoUsuario(idPainel, usuario);
	}
}
