package br.com.centralit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.PortadorDao;
import br.com.centralit.api.model.Portador;
import br.com.centralit.api.service.ClasseParceiroService;
import br.com.centralit.api.service.PortadorService;
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
 * @author rogerio.costa
 *
 */
@Service("portadorService")
public class PortadorServiceImp extends GenericServiceImpl<Portador, Long> implements PortadorService {

	/** Atributo portadorDao. */
	private PortadorDao portadorDao;

	/** Atributo classeParceiroService. */
	@Autowired
	private ClasseParceiroService classeParceiroService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param portadorDao
	 */
	@Autowired
	public PortadorServiceImp( PortadorDao portadorDao, @Qualifier("portadorValidator") Validator validator ) {

		this.dao = portadorDao;

		this.portadorDao = portadorDao;

		this.validator = validator;

	}

	@Override
	public Portador save(Portador entity) {

		entity.setClasseParceiro(this.getClasseParceiroService().obterPorCodigoDominioTipParceiro(Dominio.TIPO_PARCEIRO_PORTADOR));

		this.validarEntidade(entity, this.validator);

		return super.save(entity);
	}

	/**
	 * Retorna o valor do atributo <code>portadorDao</code>
	 *
	 * @return <code>PortadorDao</code>
	 */
	public PortadorDao getPortadorDao() {

		return portadorDao;
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
