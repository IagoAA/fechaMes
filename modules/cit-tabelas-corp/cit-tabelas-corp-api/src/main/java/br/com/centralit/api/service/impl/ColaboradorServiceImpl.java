package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.ColaboradorDao;
import br.com.centralit.api.model.Colaborador;
import br.com.centralit.api.service.ClasseParceiroService;
import br.com.centralit.api.service.ColaboradorService;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.Usuario;
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
 * @since 02/01/2015 - 13:59:39
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Service("colaboradorService")
public class ColaboradorServiceImpl extends GenericServiceImpl<Colaborador, Long> implements ColaboradorService {

	/** Atributo colaboradorDao. */
	private ColaboradorDao colaboradorDao;

	/** Atributo classeParceiroService. */
	@Autowired
	private ClasseParceiroService classeParceiroService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param colaboradorDao
	 */
	@Autowired
	public ColaboradorServiceImpl( ColaboradorDao colaboradorDao, @Qualifier("colaboradorValidator") Validator validator ) {

		this.dao = colaboradorDao;

		this.colaboradorDao = colaboradorDao;

		this.validator = validator;

	}

	@Override
	public Colaborador save(Colaborador entity) {

		entity.setClasseParceiro(this.getClasseParceiroService().obterPorCodigoDominioTipParceiro(Dominio.TIPO_PARCEIRO_COLABORADOR));

		this.validarEntidade(entity, this.validator);

		return (Colaborador) this.getColaboradorDao().save(entity);
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

		return this.getColaboradorDao().findPorNome(nome);
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
	 * Método responsável por listar a entidade <code>Colaborador</code> através do nome e organizacao
	 * 
	 * @author thiago.borges
	 *
	 * @param nome
	 *
	 * @return Collection<Colaborador>
	 */
	@Override
	public Collection<Colaborador> findPorNomeAndOrganizacao(String nome, Long idOrganizacao) {

		return this.getColaboradorDao().findPorNomeAndOrganizacao(nome, idOrganizacao);
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

		return this.colaboradorDao.exiteColaboradorVinculadoAEstrutura(idEstrutura);
	}

	/**
	 * Retorna o valor do atributo <code>colaboradorDao</code>
	 *
	 * @return <code>ColaboradorDao</code>
	 */
	public ColaboradorDao getColaboradorDao() {

		return colaboradorDao;
	}

	/**
	 * Retorna o valor do atributo <code>classeParceiroService</code>
	 *
	 * @return <code>ClasseParceiroService</code>
	 */
	public ClasseParceiroService getClasseParceiroService() {

		return classeParceiroService;
	}

	@Override
	public Colaborador findPorUsuario(Usuario usuario) {
		return colaboradorDao.findPorUsuario(usuario);
	}

	public boolean existeFuncaoVinculadaAColaborador(Long idFuncao) {

		return this.colaboradorDao.existeFuncaoVinculadaAColaborador(idFuncao);

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

		return this.colaboradorDao.findPessoaColaboradorUsuarioPorNome(nome);
	}

}
