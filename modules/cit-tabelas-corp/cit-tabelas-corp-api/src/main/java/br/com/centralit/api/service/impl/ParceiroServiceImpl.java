package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.ParceiroDao;
import br.com.centralit.api.model.Parceiro;
import br.com.centralit.api.service.ParceiroService;
import br.com.centralit.api.viewHelper.DominioParceirosNomeBuscaVH;
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
 * @since 05/12/2014 - 10:41:26
 *
 * @version 1.0.0
 *
 * @author wilker.machado
 *
 */
@Service("parceiroService")
public class ParceiroServiceImpl extends GenericServiceImpl<Parceiro, Long> implements ParceiroService {

	/** Atributo dominioDao. */
	private ParceiroDao parceiroDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param parceiroDao
	 * @param validator
	 */
	@Autowired
	public ParceiroServiceImpl( ParceiroDao parceiroDao ) {

		this.dao = parceiroDao;

		this.parceiroDao = parceiroDao;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<Parceiro> findParceiros(DominioParceirosNomeBuscaVH objeto) {

		Usuario usuarioLogado = ( (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal() );

		return this.parceiroDao.findParceiros(objeto, usuarioLogado.getOrganizacao().getId());
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
	 * Método responsável por listar os parceiros através do id da pessoa
	 *
	 * @author rogerio.costa
	 *
	 * @param idPessoa
	 *
	 * @return Collection<Parceiro>
	 */
	public Collection<Parceiro> findPorIdPessoa(Long idPessoa) {

		return this.parceiroDao.findPorIdPessoa(idPessoa);
	}

}
