package br.com.centralit.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.ModuloDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.Modulo;

@Repository("moduloDao")
public class ModuloDaoHibernate extends CitGenericDAOImpl implements ModuloDao {

	public ModuloDaoHibernate() {

		super(Modulo.class);
	}

	@Override
	public boolean existeNomeModulo(Modulo entity) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("nome", entity.getNome());

		return this.count(search) > 0;
	}

	@Override
	public List<Modulo> getModulosAtivos() {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("habilitado", Boolean.TRUE);

		return this.search(search);
	}

	@Override
	public Modulo moduloEstaAtivo(String baseUrl) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("baseUrl", baseUrl);

		return this.searchUnique(search);
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de neg�cio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * M�todo respons�vel por obter m�dulo por baseUrl
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param baseUrl
	 * @return Modulo
	 */
	@Override
	public Modulo getModuloPorBaseUrl(String baseUrl) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("baseUrl", baseUrl);
		
		search.addFilterEqual("habilitado", Boolean.TRUE);

		return this.searchUnique(search);
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
	 * Método responsável por obter os id dos módulos ativos
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @return List<Long>
	 */
	@Override
	public List<Long> getIdModulosAtivos() {
		
		SearchSeven search = new SearchSeven();

		search.addFilterEqual("habilitado", Boolean.FALSE);
		
		search.addField("id");

		return this.search(search);
	}
	
	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por obter os Modulo inativos
	 *
	 * @author rogerio.cassimiro
	 *
	 * @return List<Modulo>
	 */
	@Override
	public List<Modulo> getModulosInativos() {
		
		SearchSeven searchSeven = new SearchSeven();

		searchSeven.addFilterEqual("habilitado", Boolean.FALSE);
		
		return this.search(searchSeven);
		
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
	 * Método responsável por obter módulo por nome
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param nome
	 * 
	 * @return Modulo
	 */
	@Override
	public Modulo getModuloPorNome(String nome) {
		
		SearchSeven searchSeven = new SearchSeven();
		
		searchSeven.addFilterEqual("nome", nome);
		
		searchSeven.setMaxResults(1);
		
		return this.searchUnique(searchSeven);
	}
	
}