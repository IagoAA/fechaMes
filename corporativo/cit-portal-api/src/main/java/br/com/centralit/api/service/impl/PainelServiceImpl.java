package br.com.centralit.api.service.impl;

import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.PainelDao;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.LinkService;
import br.com.centralit.api.service.PainelGrupoService;
import br.com.centralit.api.service.PainelItemService;
import br.com.centralit.api.service.PainelModuloService;
import br.com.centralit.api.service.PainelPrivilegioService;
import br.com.centralit.api.service.PainelService;
import br.com.centralit.api.service.UsuarioService;
import br.com.centralit.api.service.WidgetService;
import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.Link;
import br.com.centralit.framework.model.Painel;
import br.com.centralit.framework.model.PainelGrupo;
import br.com.centralit.framework.model.PainelItem;
import br.com.centralit.framework.model.PainelItemGrupo;
import br.com.centralit.framework.model.PainelItemParametro;
import br.com.centralit.framework.model.PainelItemPrivilegio;
import br.com.centralit.framework.model.PainelModulo;
import br.com.centralit.framework.model.PainelPrivilegio;
import br.com.centralit.framework.model.Usuario;
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
 * @since 10/03/2015 - 15:38:06
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Service("painelService")
public class PainelServiceImpl extends GenericServiceImpl<Painel, Long> implements PainelService {

	private static final String VALIDACAO_PAINEL_EXISTENTE = "VALIDACAO.PAINEL_EXISTENTE";

	/** Atributo painelDao. */
	private PainelDao painelDao;

	/** Atributo widgetService. */
	@Autowired
	private WidgetService widgetService;

	/** Atributo dominioService. */
	@Autowired
	private DominioService dominioService;

	/** Atributo painelItemService. */
	@Autowired
	private PainelItemService painelItemService;

	/** Atributo painelGrupoService. */
	@Autowired
	private PainelGrupoService painelGrupoService;

	/** Atributo painelPrivilegioService. */
	@Autowired
	private PainelPrivilegioService painelPrivilegioService;

	/** Atributo painelModuloService. */
	@Autowired
	private PainelModuloService painelModuloService;

	/** Atributo linkService. */
	@Autowired
	private LinkService linkService;

	/** Atributo usuarioService. */
	@Autowired
	private UsuarioService usuarioService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param painelDao
	 */
	@Autowired
	public PainelServiceImpl( PainelDao painelDao ) {

		this.dao = painelDao;

		this.painelDao = painelDao;
	}

	@Override
	public Painel save(Painel entity) {

		this.validarUnicidadePainel(entity);

		entity.setUuid(UUID.randomUUID().toString());

		this.resolveTransientes(entity);

		return super.save(entity);
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
	 * Método responsável por validar a unicidade do painel
	 * 
	 * @author rogerio.costa
	 * 
	 * @param entity
	 */
	private void validarUnicidadePainel(Painel entity) {

		boolean contemPainel = this.painelDao.contemPainelPorNome(entity.getNome());
		// Verifica se já existe um painel com o mesmo nome
		if (contemPainel) {

			throw new BusinessException(VALIDACAO_PAINEL_EXISTENTE, CodigoErro.REGRA_NEGOCIO.getValue());
		}
	}

	@Override
	public Painel merge(Painel entity) {

		this.resolveTransientes(entity);

		this.painelGrupoService.verificarRemocao(entity);

		this.painelPrivilegioService.verificarRemocao(entity);

		this.painelModuloService.verificarRemocao(entity);

		super.merge(entity);

		this.painelDao.flush();

		return this.getReference(entity.getId());

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
	 * Método responsável por salvar a dashBoard
	 * 
	 * @author rogerio.costa
	 * 
	 * @param entity
	 */
	public Painel salvarDashBoard(Painel painel) {

		Painel painelUsuario = this.montarDadosPainelUsuario(painel);

		return (Painel) painelDao.save(painelUsuario);
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
	 * Método responsável por montar os dados do painelUsuario através dos dados do painel
	 * 
	 * @author rogerio.costa
	 * 
	 * @param painel
	 * 
	 * @return Painel
	 */
	private Painel montarDadosPainelUsuario(Painel painel) {

		Painel painelUsuario = new Painel();

		painelUsuario.setNome(painel.getNome());
		painelUsuario.setIdentificacao(painel.getIdentificacao());
		painelUsuario.setRecebeAtualizacao(painel.getRecebeAtualizacao());
		painelUsuario.setPainelItens(painel.getPainelItens());
		painelUsuario.setPainelGrupos(painel.getPainelGrupos());
		painelUsuario.setPainelPrivilegios(painel.getPainelPrivilegios());
		painelUsuario.setPainelModulos(painel.getPainelModulos());
		painelUsuario.setUsuario((Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		painelUsuario.setUuid(painel.getUuid());

		this.vincularPainelItemAoPainelUsuario(painelUsuario);

		this.vincularPainelGrupoAoPainelUsuario(painel, painelUsuario);

		this.vincularPainelPrivilegioAoPainelUsuario(painelUsuario);

		this.vincularPainelModuloAoPainelUsuario(painelUsuario);

		this.resolveTransientes(painelUsuario);

		return painelUsuario;
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
	 * @param painelUsuario
	 */
	private void vincularPainelPrivilegioAoPainelUsuario(Painel painelUsuario) {

		if (!UtilColecao.isVazio(painelUsuario.getPainelPrivilegios())) {

			for (PainelPrivilegio painelPrivilegio : painelUsuario.getPainelPrivilegios()) {

				painelPrivilegio.setId(null);

				painelPrivilegio.setPainel(painelUsuario);
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
	 * @param painelUsuario
	 */
	private void vincularPainelModuloAoPainelUsuario(Painel painelUsuario) {

		if (!UtilColecao.isVazio(painelUsuario.getPainelModulos())) {

			for (PainelModulo painelModulo : painelUsuario.getPainelModulos()) {

				painelModulo.setId(null);

				painelModulo.setPainel(painelUsuario);
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
	 * @param painel
	 * @param painelUsuario
	 */
	private void vincularPainelGrupoAoPainelUsuario(Painel painel, Painel painelUsuario) {

		if (!UtilColecao.isVazio(painelUsuario.getPainelGrupos())) {

			for (PainelGrupo painelGrupo : painel.getPainelGrupos()) {

				painelGrupo.setId(null);

				painelGrupo.setPainel(painelUsuario);

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
	 * Método responsável por vincular painelItem ao Painel
	 * 
	 * @author rogerio.costa
	 * 
	 * @param painelUsuario
	 */
	private void vincularPainelItemAoPainelUsuario(Painel painelUsuario) {

		if (!UtilColecao.isVazio(painelUsuario.getPainelItens())) {

			for (PainelItem painelItem : painelUsuario.getPainelItens()) {

				painelItem.setCopia(UtilObjeto.isReferencia(painelItem.getId()));

				painelItem.setId(null);

				painelItem.setPainel(painelUsuario);

				if (!UtilColecao.isVazio(painelItem.getPainelItemGrupos())) {

					for (PainelItemGrupo painelItemGrupo : painelItem.getPainelItemGrupos()) {

						painelItemGrupo.setId(null);

						painelItemGrupo.setPainelItem(painelItem);

					}

				}

				if (!UtilColecao.isVazio(painelItem.getPainelItemPrivilegios())) {

					for (PainelItemPrivilegio painelItemPrivilegio : painelItem.getPainelItemPrivilegios()) {

						painelItemPrivilegio.setId(null);

						painelItemPrivilegio.setPainelItem(painelItem);

					}

				}

				if (!UtilColecao.isVazio(painelItem.getPainelItemParametros())) {

					for (PainelItemParametro painelItemParametro : painelItem.getPainelItemParametros()) {

						painelItemParametro.setId(null);

						painelItemParametro.setPainelItem(painelItem);

					}

				}

				if (!UtilColecao.isVazio(painelItem.getLinks())) {

					for (Link link : painelItem.getLinks()) {

						link.setId(null);

						link.setPainelItem(painelItem);

					}
				}

			}
		}
	}

	@Override
	public Painel getReference(Long id) {

		Painel painel = super.getReference(id);

		painel.setPainelItens(this.painelItemService.findPorIdPainel(id));

		painel.setPainelGrupos(this.painelGrupoService.findPorIdPainel(id));

		painel.setPainelPrivilegios(this.painelPrivilegioService.findPorIdPainel(id));

		painel.setPainelModulos(this.painelModuloService.findPorIdPainel(id));

		return painel;
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
	 * Método responsável por resolver as propriedades transiente
	 * 
	 * @author rogerio.costa
	 * 
	 * @param entity
	 */
	private void resolveTransientes(Painel entity) {

		if (!UtilColecao.isVazio(entity.getPainelItens())) {

			this.painelItemService.resolverPropriedadesTransiante(entity);

		}

		if (!UtilColecao.isVazio(entity.getPainelGrupos())) {

			for (PainelGrupo painelGrupo : entity.getPainelGrupos()) {

				painelGrupo.setPainel(entity);

				entity.setRegraDefinida(true);

			}

		}

		if (!UtilColecao.isVazio(entity.getPainelPrivilegios())) {

			for (PainelPrivilegio painelPrivilegio : entity.getPainelPrivilegios()) {

				painelPrivilegio.setPainel(entity);

				entity.setRegraDefinida(true);

			}

		}

		if (!UtilColecao.isVazio(entity.getPainelModulos())) {

			for (PainelModulo painelModulo : entity.getPainelModulos()) {

				painelModulo.setPainel(entity);

				entity.setRegraDefinida(true);

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
	 * Método responsável por listar os paineis do usuario
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idTipoComponente
	 * 
	 * @return Collection<Painel>
	 */
	public Collection<Painel> findPorUsuario() {

		Usuario usuario = this.usuarioService.getReference(( (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getId());

		return this.painelDao.findPorUsuario(usuario);

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
	 * @param id
	 * @return
	 */
	public Painel findPainelDashBoard(Long id) {

		Painel painel = this.find(id);

		painel.setPainelItens(this.painelItemService.findPorPainelEPermissaoUsuario(painel.getId()));

		return painel;

	}

}
