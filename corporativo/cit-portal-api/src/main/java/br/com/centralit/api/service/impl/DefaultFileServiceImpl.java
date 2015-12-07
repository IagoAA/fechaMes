package br.com.centralit.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.DefaultFileDao;
import br.com.centralit.api.service.DefaultFileService;
import br.com.centralit.api.service.ModuloService;
import br.com.centralit.framework.model.DefaultFile;
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
 * <b>Title: DefaultFileServiceImpl</b>
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
 * @since 09/12/2014 - 17:30:24
 *
 * @version 1.0.0
 *
 * @author renato.jesus
 *
 */
@Service("defaultFileService")
public class DefaultFileServiceImpl extends GenericServiceImpl<DefaultFile, Long> implements DefaultFileService {

	/** Atributo defaultFileDao. */
	@Autowired
	private DefaultFileDao defaultFileDao;

	/** Atributo CAMINHO_CUSTOM. */
	private static final String CAMINHO_CUSTOM = "/custom/";

	/** Atributo TIPO_MIN_JS. */
	private static final String TIPO_MIN_JS = "min.js";

	/** Atributo ENVIRONMENT_DEV. */
	private static final String ENVIRONMENT_PROD = "prod";

	/** Atributo environment, definido no arquivo cit-app.properties. */
	@Value("${environment}")
	protected String environment;

	/** Atributo moduloService. */
	@Autowired
	private ModuloService moduloService;

	@Autowired
	public DefaultFileServiceImpl( DefaultFileDao defaultFileDao, @Qualifier("defaultFileValidator") Validator validator ) {

		this.dao = defaultFileDao;

		this.validator = validator;

	}

	public List<DefaultFile> findAllJSAtivos() {

		List<DefaultFile> defaultFilesJS = this.defaultFileDao.findAllJSAtivos();

		List<DefaultFile> listaDefaultFileJS = new ArrayList<DefaultFile>();

		if (defaultFilesJS != null && !defaultFilesJS.isEmpty()) {

			for (DefaultFile defaultFile : defaultFilesJS) {

				// Traz os min.js
				if (getMinify() && defaultFile.getCaminho().contains(CAMINHO_CUSTOM) && defaultFile.getCaminho().contains(TIPO_MIN_JS) && !defaultFile.getCaminho().contains("app.min.js")) {

					listaDefaultFileJS.add(defaultFile);

					// Traz os sem min.js
				} else if (!getMinify() && defaultFile.getCaminho().contains(CAMINHO_CUSTOM) && !defaultFile.getCaminho().contains(TIPO_MIN_JS)) {

					listaDefaultFileJS.add(defaultFile);

					// Traz os js fora da pasta custom
				} else if (!defaultFile.getCaminho().contains(CAMINHO_CUSTOM)) {

					listaDefaultFileJS.add(defaultFile);

					// Traz os sempre o app.js
				} else if (defaultFile.getCaminho().contains("app.js")) {

					listaDefaultFileJS.add(defaultFile);
				}

			}
		}

		return listaDefaultFileJS;
	}

	public List<DefaultFile> findAllCSSAtivos() {

		return this.defaultFileDao.findAllCSSAtivos();

	}


	/**
	 *
	 * Retorna List<DefaultFile> padrões e dos modulos ativos
	 *
	 */
	public List<DefaultFile> findAllCSSAtivosComModulosAtivos() {

		List<String> baseUrlsInativos = this.moduloService.getBaseUrlModulosInativos();

		List<Long> idsQueNaoSeraoCarregados = new ArrayList<Long>();

		List<DefaultFile> listaDefaultFile = this.defaultFileDao.findAllCSSAtivos();
		for(DefaultFile df : listaDefaultFile){

			forModuloInativo: for(String burlInativo : baseUrlsInativos){

				if(df.getCaminho().contains(burlInativo)){

					idsQueNaoSeraoCarregados.add(df.getId());
					break forModuloInativo;

				}

			}

		}

		return this.defaultFileDao.findAllCSSAtivosPorModulosAtivos(idsQueNaoSeraoCarregados);

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
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por obter todos JS ativos por módulos ativos
	 *
	 * @author rogerio.cassimiro
	 *
	 * @return List<DefaultFile>
	 */
	@Override
	public List<DefaultFile> findAllJSAtivosPorModulosAtivos() {

		List<String> baseUrlsInativos = this.moduloService.getBaseUrlModulosInativos();

		List<Long> idsDefaultFileInativosPorModulo = this.defaultFileDao.findIdsDefaultFileModulosInativos(baseUrlsInativos);

		List<DefaultFile> defaultFilesJS = this.defaultFileDao.findAllJSAtivosPorModulosAtivos(idsDefaultFileInativosPorModulo);

		List<DefaultFile> listaDefaultFileJS = new ArrayList<DefaultFile>();

		if (defaultFilesJS != null && !defaultFilesJS.isEmpty()) {

			for (DefaultFile defaultFile : defaultFilesJS) {

				// Traz os min.js
				if (getMinify() && defaultFile.getCaminho().contains(CAMINHO_CUSTOM) && defaultFile.getCaminho().contains(TIPO_MIN_JS) && !defaultFile.getCaminho().contains("app.min.js")) {

					listaDefaultFileJS.add(defaultFile);

					// Traz os sem min.js
				} else if (!getMinify() && defaultFile.getCaminho().contains(CAMINHO_CUSTOM) && !defaultFile.getCaminho().contains(TIPO_MIN_JS)) {

					listaDefaultFileJS.add(defaultFile);

					// Traz os js fora da pasta custom
				} else if (!defaultFile.getCaminho().contains(CAMINHO_CUSTOM)) {

					listaDefaultFileJS.add(defaultFile);

					// Traz os sempre o app.js
				} else if (defaultFile.getCaminho().contains("app.js")) {

					listaDefaultFileJS.add(defaultFile);
				}

			}
		}

		return listaDefaultFileJS;
	}

}
