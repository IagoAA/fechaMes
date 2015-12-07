package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.FuncionarioDao;
import br.com.centralit.api.model.Funcionario;
import br.com.centralit.api.service.ClasseParceiroService;
import br.com.centralit.api.service.FuncionarioService;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;

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
 * @since 05/01/2015 - 16:05:16
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *
 */
@Service("funcionarioService")
public class FuncionarioServiceImp extends GenericServiceImpl<Funcionario, Long> implements FuncionarioService {

	/** Atributo funcionarioDao. */
	private FuncionarioDao funcionarioDao;

	/** Atributo classeParceiroService. */
	@Autowired
	private ClasseParceiroService classeParceiroService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param portadorDao
	 */
	@Autowired
	public FuncionarioServiceImp( FuncionarioDao funcionarioDao, @Qualifier("funcionarioValidator") Validator validator ) {

		this.dao = funcionarioDao;

		this.funcionarioDao = funcionarioDao;

		this.validator = validator;

	}

	@Override
	public Funcionario save(Funcionario entity) {

		entity.setClasseParceiro(this.getClasseParceiroService().obterPorCodigoDominioTipParceiro(Dominio.TIPO_PARCEIRO_FUNCIONARIO));

		this.validarEntidade(entity, this.validator);

		return super.save(entity);
	}


	/**
	 * Método responsável por listar a entidade <code>Funcionario</code> através do nome
	 *
	 * @author iago
	 *
	 * @param nome
	 *
	 * @return Collection<Funcionario>
	 */
	public Collection<Funcionario> findPorNome(String nome) {

		return this.getFuncionarioDao().findPorNome(nome);
	}

	/**
	 *
	 * Método responsável por listar a entidade <code>Funcionario</code> através do nome e organizacao
	 *
	 * @author iago
	 *
	 * @param nome
	 *
	 * @return Collection<Funcionario>
	 */
	@Override
	public Collection<Funcionario> findPorNomeAndOrganizacao(String nome, Long idOrganizacao) {

		return this.getFuncionarioDao().findPorNomeAndOrganizacao(nome, idOrganizacao);
	}

	/**
	 * Retorna o valor do atributo <code>portadorDao</code>
	 *
	 * @return <code>funcionarioDao</code>
	 */
	public FuncionarioDao getFuncionarioDao() {

		return funcionarioDao;
	}

	/**
	 * Retorna o valor do atributo <code>classeParceiroService</code>
	 *
	 * @return <code>ClasseParceiroService</code>
	 */
	public ClasseParceiroService getClasseParceiroService() {

		return classeParceiroService;
	}

}
