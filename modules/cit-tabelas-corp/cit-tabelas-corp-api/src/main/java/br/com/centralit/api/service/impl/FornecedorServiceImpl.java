package br.com.centralit.api.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.FornecedorDao;
import br.com.centralit.api.model.Fornecedor;
import br.com.centralit.api.model.FornecedorObservacao;
import br.com.centralit.api.service.ClasseParceiroService;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.FornecedorService;
import br.com.centralit.api.service.UsuarioService;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilColecao;

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
 * <b>Title: </b>FornecedorServiceImpl
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
 * @since 26/11/2014 - 15:19:29
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Service("fornecedorService")
public class FornecedorServiceImpl extends GenericServiceImpl<Fornecedor, Long> implements FornecedorService {

	/** Atributo fornecedorDao. */
	private final FornecedorDao fornecedorDao;

	/** Atributo dominioDao. */
	@Autowired
	private DominioService dominioService;

	/** Atributo classeParceiroService. */
	@Autowired
	private ClasseParceiroService classeParceiroService;

	/** Atributo usuarioService. */
	@Autowired
	private UsuarioService usuarioService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	@Autowired
	public FornecedorServiceImpl( final FornecedorDao fornecedorDao ) {

		this.dao = fornecedorDao;

		this.fornecedorDao = fornecedorDao;
	}

	@Override
	public Fornecedor save(final Fornecedor entity) {

		entity.setClasseParceiro(this.getClasseParceiroService().obterPorCodigoDominioTipParceiro(Dominio.TIPO_PARCEIRO_FORNECEDOR));

		this.montarDadosFornecedor(entity);

		return super.save(entity);
	}

	@Override
	public Fornecedor merge(Fornecedor entity) {

		this.montarDadosFornecedor(entity);

		return super.merge(entity);
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
	 * Método responsável por
	 *
	 * @author rogerio.costa
	 *
	 * @param entity
	 */
	private void montarDadosFornecedor(final Fornecedor entity) {

		if (!UtilColecao.isVazio(entity.getObservacoes())) {

			for (FornecedorObservacao fornecedorObservacao : entity.getObservacoes()) {

				fornecedorObservacao.setFornecedor(entity);
			}
		}
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
	 * Método responsável por listar a entidade <code>Dominio</code> através da chave
	 *
	 * @author rogerio.costa
	 *
	 * @param chave
	 *
	 * @return Collection<Dominio>
	 */
	public Collection<Dominio> listarPorChave(final String chave) {

		return this.dominioService.listarPorChave(chave);
	}

	/**
	 * Retorna o valor do atributo <code>fornecedorDao</code>
	 *
	 * @return <code>FornecedorDao</code>
	 */
	public FornecedorDao getFornecedorDao() {

		return this.fornecedorDao;
	}

	@Override
	public List<Fornecedor> listarFornecedores(final String nome) {

		Usuario usuarioLogado = usuarioService.find(( (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getId());

		return this.fornecedorDao.listarFornecedores(nome, usuarioLogado.getOrganizacao().getId());
	}

	/**
	 * Retorna o valor do atributo <code>dominioService</code>
	 *
	 * @return <code>DominioService</code>
	 */
	public DominioService getDominioService() {

		return this.dominioService;
	}

	/**
	 * Retorna o valor do atributo <code>classeParceiroService</code>
	 *
	 * @return <code>ClasseParceiroService</code>
	 */
	public ClasseParceiroService getClasseParceiroService() {

		return this.classeParceiroService;
	}

}
