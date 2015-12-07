package br.com.centralit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.ClienteDao;
import br.com.centralit.api.model.Cliente;
import br.com.centralit.api.service.ClasseParceiroService;
import br.com.centralit.api.service.ClienteService;
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
@Service("clienteService")
public class ClienteServiceImp extends GenericServiceImpl<Cliente, Long> implements ClienteService {

	/** Atributo clienteDao. */
	private ClienteDao clienteDao;

	/** Atributo classeParceiroService. */
	@Autowired
	private ClasseParceiroService classeParceiroService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param portadorDao
	 */
	@Autowired
	public ClienteServiceImp( ClienteDao clienteDao, @Qualifier("clienteValidator") Validator validator ) {

		this.dao = clienteDao;

		this.clienteDao = clienteDao;

		this.validator = validator;

	}

	@Override
	public Cliente save(Cliente entity) {

		entity.setClasseParceiro(this.getClasseParceiroService().obterPorCodigoDominioTipParceiro(Dominio.TIPO_PARCEIRO_CLIENTE));

		this.validarEntidade(entity, this.validator);

		return super.save(entity);
	}

	/**
	 * Retorna o valor do atributo <code>portadorDao</code>
	 *
	 * @return <code>clienteDao</code>
	 */
	public ClienteDao getClienteDao() {

		return clienteDao;
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
