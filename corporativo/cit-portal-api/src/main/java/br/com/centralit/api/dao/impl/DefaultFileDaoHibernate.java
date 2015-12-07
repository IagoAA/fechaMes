package br.com.centralit.api.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.DefaultFileDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.DefaultFile;
import br.com.centralit.framework.model.arquitetura.PersistentObject;
import br.com.centralit.framework.util.UtilColecao;
import br.com.centralit.framework.util.UtilObjeto;
import br.com.centralit.framework.util.UtilString;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.Sort;

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
 * <b>Title: DefaultFileDaoHibernate</b>
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
 * @since 09/12/2014 - 17:28:11
 *
 * @version 1.0.0
 *
 * @author renato.jesus
 *
 */
@Repository("defaultFileDao")
public class DefaultFileDaoHibernate extends CitGenericDAOImpl implements DefaultFileDao {

	public DefaultFileDaoHibernate() {

		super(DefaultFile.class);
	}

	/** Atributo TIPO_ARQUIVO_JS. */
	private static final String TIPO_ARQUIVO_JS = "js";

	/** Atributo TIPO_ARQUIVO_CSS. */
	private static final String TIPO_ARQUIVO_CSS = "css";

	@Override
	public List<PersistentObject> findAll() {

		SearchSeven search = new SearchSeven(DefaultFile.class);

		search.addSort(Sort.asc("ordem"));

		return this.search(search);
	}

	public List<DefaultFile> findAllAtivos() {

		SearchSeven search = new SearchSeven(DefaultFile.class);

		search.addFilterEqual("ativo", true);
		search.addSort(Sort.asc("ordem"));

		return this.search(search);
	}

	@Override
	public List<DefaultFile> findAllJSAtivos() {

		return findAllAtivosPorTipoArquivo(TIPO_ARQUIVO_JS);
	}

	@Override
	public List<DefaultFile> findAllCSSAtivos() {

		return findAllAtivosPorTipoArquivo(TIPO_ARQUIVO_CSS);
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
	 * Método responsável por obter todos DefaultFile ativo e por tipo de arquivo
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param tipoArquivo
	 * @return List<DefaultFile>
	 */
	private List<DefaultFile> findAllAtivosPorTipoArquivo(String tipoArquivo) {

		SearchSeven search = new SearchSeven(DefaultFile.class);

		search.addFilterEqual("ativo", true);
		search.addSort(Sort.asc("ordem"));

		if (!UtilString.isNullOrEmpty(tipoArquivo)) {
			search.addFilterILike("dominioDefaultFile.nome", tipoArquivo);
		}

		return this.search(search);
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
	 * Método responsável por obter ids dos DefaultFile de módulos inativos
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param baseUrlModulosInativos
	 * @return List<Long>
	 */
	@Override
	public List<Long> findIdsDefaultFileModulosInativos(List<String> baseUrlModulosInativos) {

		List<Long> idsBareUrlInativos = new ArrayList<Long>();

		SearchSeven searchSevenModulosInativos = new SearchSeven(DefaultFile.class);

		Filter[] listFilter = new Filter[baseUrlModulosInativos.size()];

		for (int i = 0; i < baseUrlModulosInativos.size(); i++) {

			listFilter[i] = Filter.ilike("caminho", baseUrlModulosInativos.get(i) + "%");
		}

		if (baseUrlModulosInativos.size() > 0) {

			searchSevenModulosInativos.addFilterOr(listFilter);
			searchSevenModulosInativos.addField("id");
			idsBareUrlInativos = this.search(searchSevenModulosInativos);
		}

		return idsBareUrlInativos;
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
	 * Método responsável por buscar todos js ativos de módulos ativos
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param idDefaultFileInativosPorModulo
	 *
	 * @return List<DefaultFile>
	 */
	@Override
	public List<DefaultFile> findAllJSAtivosPorModulosAtivos(List<Long> idDefaultFileInativosPorModulo) {

		SearchSeven search = new SearchSeven(DefaultFile.class);

		search.addFilterEqual("ativo", true);
		search.addFilterILike("dominioDefaultFile.nome", TIPO_ARQUIVO_JS);
		if(!UtilColecao.isVazio(idDefaultFileInativosPorModulo)) {
			search.addFilterNotIn("id", idDefaultFileInativosPorModulo);
		}
		search.addSort("id", false);

		return this.search(search);
	}


	/**
	 *
	 * Retorna todos os DefaultFile do tipo CSS que estão ativos e não esta na lista de ids passados como parametros
	 *
	 *
	 */
	@Override
	public List<DefaultFile> findAllCSSAtivosPorModulosAtivos(List<Long> idDefaultFileInativosPorModulo) {

		SearchSeven search = new SearchSeven(DefaultFile.class);

		search.addFilterEqual("ativo", true);
		search.addFilterILike("dominioDefaultFile.nome", TIPO_ARQUIVO_CSS);
		if(!UtilColecao.isVazio(idDefaultFileInativosPorModulo)) {
			search.addFilterNotIn("id", idDefaultFileInativosPorModulo);
		}
		search.addSort("id", false);

		return this.search(search);
	}

	/**
	 * <p>
	 * <b>Regra(s) de negócio:</b> Consulta entidade de acordo com os parametros,
	 * caso ela não exista, salva o registro
	 * </p>
	 *
	 * @author gilberto.nery
	 * @date 09/09/2015
	 *
	 * @return PersistentObject - Entidade que foi salva ou entidade que estava cadastrada
	 */
	@Override
	public PersistentObject saveIfNotExist(PersistentObject entity) {

		DefaultFile defaultFile = (DefaultFile) entity;

		Search search = new Search();

		search.addFilterEqual("caminho", defaultFile.getCaminho());

		search.addFilterEqual("dominioDefaultFile.id", defaultFile.getDominioDefaultFile().getId());

		search.setMaxResults(1);

		PersistentObject obj = searchUnique(search);

		if (UtilObjeto.isReferencia(obj)) {

			return obj;

		} else {

			return this.save(entity);

		}
	}
}