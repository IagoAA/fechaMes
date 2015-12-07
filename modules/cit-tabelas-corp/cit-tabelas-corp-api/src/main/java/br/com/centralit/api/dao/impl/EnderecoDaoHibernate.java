package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.EnderecoDao;
import br.com.centralit.api.model.Endereco;
import br.com.centralit.api.model.Localizacao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;

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
 * <b>Title: </b>EnderecoDaoHibernate
 * </p>
 * 
 * <p>
 * <b>Description: </b>
 * </p>
 * 
 * @since 05/12/2014 - 11:05:56
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Repository("enderecoDao")
public class EnderecoDaoHibernate extends CitGenericDAOImpl implements EnderecoDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param classs
	 */
	public EnderecoDaoHibernate() {

		super(Endereco.class);
	}

	@Override
	public Collection<Endereco> listarEndereco(String nome) {

		SearchSeven search = new SearchSeven();

		search.addFilterILike("nome", "%" + nome + "%");

		search.addFilterEmpty("dataInativo");

		search.addSort(Sort.asc("nome"));

		search.setMaxResults(10);

		return this.search(search, Endereco.class);

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
	 * Método responsável por listar os enderecos através do id da pessoa
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idPessoa
	 * 
	 * @return Collection<Parceiro>
	 */
	public Collection<Endereco> findPorIdPessoa(Long idPessoa) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("pessoa.id", idPessoa);

		return this.search(search, Endereco.class);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existeBairroVinculadoAEndereco(Long bairroId) {

		SearchSeven search = new SearchSeven();
		
		search.addFilterEqual("bairro.id", bairroId);
		
		return this.count(search) > 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existeEnderecoVinculadoALocalizacao(Long enderecoId) {

		SearchSeven search = new SearchSeven();
		
		search.addFilterEqual("endereco.id", enderecoId);
		
		return this._count(Localizacao.class, search) > 0;
	}

}
