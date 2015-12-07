package br.com.centralit.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.MenuFileDao;
import br.com.centralit.api.service.MenuFileService;
import br.com.centralit.api.service.MenuService;
import br.com.centralit.api.service.ModuloService;
import br.com.centralit.framework.model.Menu;
import br.com.centralit.framework.model.MenuFile;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
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
 * @since 11/09/2015 - 11:02:58
 *
 * @version 1.0.0
 *
 * @author rogerio.cassimiro
 *
 */
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
 * @since 11/09/2015 - 11:03:00
 *
 * @version 1.0.0
 *
 * @author rogerio.cassimiro
 *
 */
@Service("menuFileService")
public class MenuFileServiceImpl extends GenericServiceImpl<MenuFile, Long> implements MenuFileService {

	/** Atributo menuFileDao. */
	private MenuFileDao menuFileDao;

	/** Atributo menuFile. */
	private MenuFile menuFile;

	/** Atributo TIPO_MIN_JS. */
	private static final String TIPO_MIN_JS = "min.js";

	/** Atributo ENVIRONMENT_DEV. */
	private static final String ENVIRONMENT_PROD = "prod";

	/** Atributo environment, definido no arquivo cit-app.properties. */
	@Value("${environment}")
	protected String environment;

	/** Atributo menuService. */
	@Autowired
	private MenuService menuService;

	/** Atributo moduloService. */
	@Autowired
	private ModuloService moduloService;

	@Autowired
	public MenuFileServiceImpl( MenuFileDao menuFileDao ) {

		this.dao = menuFileDao;

		this.menuFileDao = menuFileDao;
	}

	@Override
	public List<MenuFile> findByIdMenu(Long idMenu) {

		return this.menuFileDao.findByIdMenu(idMenu);
	}

	@Override
	public MenuFile save(MenuFile menuFile) {

		this.menuFile = menuFile;

		this.resolveTransientMenu();

		return super.save(this.menuFile);
	}

	@Override
	public MenuFile merge(MenuFile menuFile) {

		this.menuFile = menuFile;

		this.resolveTransientMenu();

		return super.merge(this.menuFile);
	}

	@Override
	public List<MenuFile> findAllJSAtivos() {

		List<MenuFile> menuFilesJS = this.menuFileDao.findAllJSAtivos();

		List<MenuFile> listaItensJS = new ArrayList<MenuFile>();

		if (menuFilesJS != null && !menuFilesJS.isEmpty()) {

			for (MenuFile menuFile : menuFilesJS) {

				if (getMinify() && menuFile.getCaminho().contains(TIPO_MIN_JS)) {

					listaItensJS.add(menuFile);

				} else if (!getMinify() && !menuFile.getCaminho().contains(TIPO_MIN_JS)) {

					listaItensJS.add(menuFile);

				}
			}
		}

		return listaItensJS;
	}

	@Override
	public List<MenuFile> findAllCSSAtivos() {

		return this.menuFileDao.findAllCSSAtivos();
	}

	@Override
	public List<MenuFile> findAllCSSAtivosPorModulosAtivos() {

		return this.menuFileDao.findAllCSSAtivosPorModulosAtivos();
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
	 * @author renato.jesus
	 *
	 */
	private void resolveTransientMenu() {

		Menu menu = menuService.find(this.menuFile.getMenu().getId());

		this.menuFile.setMenu(menu);
	}

	/**
	 * Método responsável por retornar se o ambiente é de dev ou prod
	 *
	 * @author iago.almeida
	 * @return Boolean
	 */
	private boolean getMinify() {

		return UtilObjeto.isReferencia(environment) ? environment.equals(ENVIRONMENT_PROD) : Boolean.FALSE;

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
	 * Método responsável por obter ids dos MenuFile de módulos inativos
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param baseUrlModulosInativos
	 * @return List<Long>
	 */
	@Override
	public List<Long> findIdsMenuFileModulosInativos(List<String> baseUrlModulosInativos) {

		return this.menuFileDao.findIdsMenuFileModulosInativos(baseUrlModulosInativos);
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por buscar todos JS ativos por módulos ativos
	 *
	 * @author rogerio.cassimiro
	 *
	 * @return List<MenuFile>
	 */
	@Override
	public List<MenuFile> findAllJSAtivosPorModulosAtivos() {

		List<String> listBaseUrlInativas = this.moduloService.getBaseUrlModulosInativos();

		List<MenuFile> menuFilesJS = this.menuFileDao.findAllJSAtivosPorModulosAtivos(listBaseUrlInativas);

		List<MenuFile> listaItensJS = new ArrayList<MenuFile>();

		if (menuFilesJS != null && !menuFilesJS.isEmpty()) {

			for (MenuFile menuFile : menuFilesJS) {

				if (getMinify() && menuFile.getCaminho().contains(TIPO_MIN_JS)) {

					listaItensJS.add(menuFile);

				} else if (!getMinify() && !menuFile.getCaminho().contains(TIPO_MIN_JS)) {

					listaItensJS.add(menuFile);

				}
			}
		}

		return listaItensJS;
	}

}
