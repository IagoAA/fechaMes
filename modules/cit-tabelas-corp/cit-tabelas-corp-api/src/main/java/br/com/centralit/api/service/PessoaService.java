package br.com.centralit.api.service;

import java.util.Collection;

import br.com.centralit.api.model.Colaborador;
import br.com.centralit.api.model.Funcionario;
import br.com.centralit.api.model.Pessoa;
import br.com.centralit.api.viewHelper.PessoaVH;
import br.com.centralit.framework.service.arquitetura.GenericService;

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
 * <b>Title: </b>PessoaService
 * </p>
 *
 * <p>
 * <b>Description: </b>
 * </p>
 *
 * <p>
 *
 * @since 28/11/2014 - 11:44:54
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
public interface PessoaService extends GenericService<Pessoa, Long> {

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
	Collection<Colaborador> findColaboradorPorNome(String nome);

	/**
	 * Método responsável por buscar funcionario por nome <code>Funcionario</code>
	 *
	 * @author iago.almeida
	 *
	 * @param nome
	 *
	 * @return Collection<Funcionario>
	 */
	Collection<Funcionario> findFuncionarioPorNome(String nome);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por listar a entidade <code>Colaborador</code> através do nome e organizacao
	 *
	 * @author thiago.borges
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @return
	 */
	Collection<Colaborador> findColaboradorPorNomeAndOrganizacao(String nome, Long idOrganizacao);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por salvar a entidade <code>Pessoa</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param pessoaVH
	 *
	 * @return Pessoa
	 */
	Pessoa save(PessoaVH pessoaVH);

	/**
	 * Método responsável atualizar <code>Pessoa</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param entity
	 */
	Pessoa merge(PessoaVH pessoaVH);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por remover a entidade <code>Colaborador</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param id
	 *
	 * @return boolean
	 */
	boolean removeColaborador(Long id);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por remover a entidade <code>OrgaoExterno</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param id
	 *
	 * @return boolean
	 */
	boolean removeOrgaoExterno(Long id);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por remover a entidade <code>Portador</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param id
	 *
	 * @return boolean
	 */
	boolean removePortador(Long id);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por remover a entidade <code>Fornecedor</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param id
	 *
	 * @return boolean
	 */
	boolean removeFornecedor(Long id);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por remover a entidade<code>FornecedorObservacao</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param id
	 *
	 * @return boolean
	 */
	boolean removeFornecedorObservacao(Long id);

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
	boolean removeFornecedorRamosAtividade(Long id);

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">595</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por remover parceiro seguradora
	 *
	 * @author juliana.barbosa
	 *
	 * @param id
	 * @return
	 */
	boolean removeSeguradora(Long id);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por remover a entidade <code>Cliente</code>
	 *
	 * @author iago.almeida
	 *
	 * @param id
	 *
	 * @return boolean
	 */
	boolean removeCliente(Long id);


	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por remover a entidade <code>Funcionario</code>
	 *
	 * @author iago.almeida
	 *
	 * @param id
	 *
	 * @return boolean
	 */
	boolean removeFuncionario(Long id);

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
	boolean removeContato(Long id);

	/**
	 *
	 * Método responsável por buscar uma pessoa que seja um usuário no sistema
	 *
	 * @author iago.almeida
	 *
	 * @param nome
	 * @return Collection<Colaborador>
	 */
	Collection<Colaborador> findPessoaColaboradorUsuarioPorNome(String nome);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por listar colaboradores por nome na organização do usuário logado
	 *
	 * @author luis.camargo
	 *
	 * @param nome
	 *
	 * @return Collection<Colaborador>
	 */
	Collection<Colaborador> findColaboradorPorNomeNaOrganizacaoUsuarioLogado(String nome);

	/**
	 *
	 * Método responsável por listar funcionarios por nome na organização do usuário logado
	 *
	 * @author iago
	 *
	 * @param nome
	 *
	 * @return Collection<Funcionario>
	 */
	Collection<Funcionario> findFuncionarioPorNomeNaOrganizacaoUsuarioLogado(String nome);

	Pessoa bloquearPessoa(Pessoa pessoa);
}