package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.ServicoDao;
import br.com.centralit.api.model.Servico;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;

import com.googlecode.genericdao.search.Sort;

@Repository("servicoDao")
public class ServicoDaoHibernate extends CitGenericDAOImpl implements ServicoDao {

	public ServicoDaoHibernate() {

		super(Servico.class);
	}

	/**
	 * Método responsável por verifica se existe um país cadastrado com o mesmo nome.
	 *
	 * @author iago.almeida
	 *
	 *  @param servico
	 *  @return boolean
	 */
	@Override
	public boolean existeServicoMesmoNome(Servico servico) {

		SearchSeven search = new SearchSeven();
		if(servico.getId() != null) {
			search.addFilterNotEqual("id", servico.getId());
		}
		search.addFilterILike("nome", servico.getNome());
		return this.count(search) > 0;
	}

	/**
	 * Método responsável por listar a Entidade<code>Servico</code>
	 *
	 * @author iago.almeida
	 *
	 * @return Collection<Cidade>
	 */
	public Collection<Servico> listarServico(String nome) {

		SearchSeven search = new SearchSeven();

		search.addFilterILike("nome", "%" + nome + "%");

		search.addFilterEmpty("dataInativo");

		search.addSort(Sort.asc("nome"));

		search.setMaxResults(10);

		return this.search(search, Servico.class);

	}

	public Collection<Servico> findPorNomeAndOrganizacao(String nome, Long organizacaoId) {

		SearchSeven search = new SearchSeven();

		search.addFilterILike("nome", "%" + nome + "%");

		search.addFilterEmpty("dataInativo");

		search.addFilterEqual("organizacao.id", organizacaoId);

		search.setMaxResults(10);

		return this.search(search, Servico.class);

	}

}
