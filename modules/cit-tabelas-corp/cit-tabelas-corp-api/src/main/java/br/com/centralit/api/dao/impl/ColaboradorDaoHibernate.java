package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.ColaboradorDao;
import br.com.centralit.api.model.Colaborador;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.Usuario;

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
 * @since 02/01/2015 - 13:57:58
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Repository("colaboradorDao")
public class ColaboradorDaoHibernate extends CitGenericDAOImpl implements ColaboradorDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param classs
	 */
	public ColaboradorDaoHibernate() {

		super(Colaborador.class);
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
	 * Método responsável por listar a entidade <code>Colaborador</code> através do nome
	 * 
	 * @author rogerio.costa
	 * 
	 * @param nome
	 * 
	 * @return Collection<Colaborador>
	 */
	public Collection<Colaborador> findPorNome(String nome) {

		SearchSeven search = new SearchSeven();

		search.addFilterILike("pessoa.nome", "%" + nome + "%");

		search.addFilterEqual("classeParceiro.dominioTipoParceiro.codigo", Dominio.TIPO_PARCEIRO_COLABORADOR);

		search.addFilterEmpty("dataInativo");

		search.addSort(Sort.asc("pessoa.nome"));

		search.setMaxResults(10);

		return this.search(search, Colaborador.class);

	}

	@Override
	public Collection<Colaborador> findPorNomeAndOrganizacao(String nome, Long idOrganizacao) {

		SearchSeven search = new SearchSeven();

		search.addFilterILike("pessoa.nome", "%" + nome + "%");

		search.addFilterEqual("pessoa.organizacao.id", idOrganizacao);

		search.addFilterEqual("classeParceiro.dominioTipoParceiro.codigo", Dominio.TIPO_PARCEIRO_COLABORADOR);

		search.addSort(Sort.asc("pessoa.nome"));

		search.setMaxResults(10);

		return this.search(search, Colaborador.class);
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
	 * Método responsável por verificar se contem colaborador vinculado a estruturaOrganizacional
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idEstrutura
	 * 
	 * @return boolean
	 */
	public boolean exiteColaboradorVinculadoAEstrutura(Long idEstrutura) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("estruturaOrganizacional.id", idEstrutura);

		return this.count(search) > 0 ? Boolean.TRUE : Boolean.FALSE;

	}

	@Override
	public Colaborador findPorUsuario(Usuario usuario) {
		SearchSeven search = new SearchSeven();

		search.addFilterEqual("pessoa.usuario.username", usuario.getUsername());

		search.addFilterEqual("classeParceiro.dominioTipoParceiro.codigo", Dominio.TIPO_PARCEIRO_COLABORADOR);

    	search.addFilterEmpty("dataInativo");

    	search.setMaxResults(1);
		
		return this.searchUnique(search, Colaborador.class);
	}
	
	public boolean existeFuncaoVinculadaAColaborador(Long idFuncao) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("funcao.id", idFuncao);

		return this.count(search) > 0 ? Boolean.TRUE : Boolean.FALSE;

	}	

	/**
	 *
	 * Método responsável por buscar uma pessoa que seja um usuário no sistema
	 *
	 * @author iago.almeida
	 *
	 * @param nome
	 * @return Collection<Colaborador>
	 */
	@Override
	public Collection<Colaborador> findPessoaColaboradorUsuarioPorNome(String nome) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("pessoa.nome", nome);

		search.addFilterNotEmpty("usuario.id");

		return this.search(search, Colaborador.class);
	}	
}
