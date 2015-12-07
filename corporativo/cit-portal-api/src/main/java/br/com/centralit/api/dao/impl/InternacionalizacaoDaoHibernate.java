package br.com.centralit.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.InternacionalizacaoDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.Internacionalizacao;
import br.com.centralit.framework.model.Modulo;
import br.com.centralit.framework.model.arquitetura.PersistentObject;
import br.com.centralit.framework.util.UtilObjeto;

import com.googlecode.genericdao.search.Search;

@Repository("internacionalizacaoDao")
public class InternacionalizacaoDaoHibernate extends CitGenericDAOImpl
		implements InternacionalizacaoDao {

	public InternacionalizacaoDaoHibernate() {

		super(Internacionalizacao.class);
	}

	@Override
	public boolean existeChave(Internacionalizacao entity) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("chave", entity.getChave());

		/* A chave não poderá ser duplicada, independente do módulo
		if (UtilObjeto.isReferencia(entity.getModulo()) && UtilObjeto.isReferencia(entity.getModulo().getId())) {

			search.addFilterEqual("modulo.id", entity.getModulo().getId());

		}*/

		search.addFilterEqual("tipoDominioIdioma.id", entity.getTipoDominioIdioma().getId());

		return this.count(search) > 0;
	}

	@Override
	public boolean existeValor(Internacionalizacao entity) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("valor", entity.getValor());

		//search.addFilterEqual("modulo.id", entity.getModulo().getId());

		search.addFilterEqual("tipoDominioIdioma.id", entity
				.getTipoDominioIdioma().getId());

		return this.count(search) > 0;
	}


	@Override
	public List<Internacionalizacao> buscarPorModulo(Modulo moduloAtivo,
			Dominio idioma) {

		SearchSeven search = new SearchSeven();

		search.addFilterNull("modulo");

		search.addFilterEqual("tipoDominioIdioma.id", idioma.getId());

		return this.search(search);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTranslate(String chave, Dominio idioma) {
		SearchSeven search = new SearchSeven();

		search.addFilterEqual("chave", chave);
		search.addFilterEqual("tipoDominioIdioma.id", idioma.getId());
		search.setMaxResults(1);

		List<Internacionalizacao> listaTraducoes = this.search(search);

		if (listaTraducoes == null || listaTraducoes.isEmpty()) {
			return chave;
		} else {
			return listaTraducoes.get(0).getValor();
		}
	}

	/**
	 * <p>
	 * <b>Regra(s) de negócio:</b> Consulta entidade de acordo com os parametros(username),
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

		Internacionalizacao internacionaliza = (Internacionalizacao) entity;

		Search search = new Search();

		search.addFilterEqual("chave", internacionaliza.getChave());

		search.addFilterEqual("tipoDominioIdioma.id", internacionaliza.getTipoDominioIdioma().getId());

		search.setMaxResults(1);

		PersistentObject obj = searchUnique(search);

		if (UtilObjeto.isReferencia(obj)) {

			return obj;

		} else {

			return this.save(entity);

		}
	}


	/**
	 * @author gilberto.nery
	 * @data 09/09/2015
	 *
	 * Consulta a entidade Internacionalizacao por chave e idioma
	 *
	 * @param entity
	 * @return
	 */
	@Override
	public Internacionalizacao consultaInternacionalizacao (Internacionalizacao entity) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("chave", entity.getChave());
		search.addFilterEqual("tipoDominioIdioma.id", entity.getTipoDominioIdioma().getId());
		search.setMaxResults(1);

		return this.searchUnique(search, Internacionalizacao.class);

	}
}