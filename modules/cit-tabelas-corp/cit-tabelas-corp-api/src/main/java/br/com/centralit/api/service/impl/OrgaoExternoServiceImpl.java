package br.com.centralit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.ClasseParceiroDao;
import br.com.centralit.api.dao.OrgaoExternoDao;
import br.com.centralit.api.model.OrgaoExterno;
import br.com.centralit.api.service.OrgaoExternoService;
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
 * <b>Title: </b> OrgaoExternoServiceImpl
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
 * @since 05/01/2015 - 15:47:44
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Service("orgaoExternoService")
public class OrgaoExternoServiceImpl extends GenericServiceImpl<OrgaoExterno, Long> implements OrgaoExternoService {

	/** Atributo orgaoExternoDao. */
	private OrgaoExternoDao orgaoExternoDao;

	/** Atributo classeParceiroDao. */
	@Autowired
	private ClasseParceiroDao classeParceiroDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param orgaoExternoDao
	 */
	@Autowired
	public OrgaoExternoServiceImpl( OrgaoExternoDao orgaoExternoDao ) {

		this.dao = orgaoExternoDao;

		this.orgaoExternoDao = orgaoExternoDao;
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
	 * Método responsável por salvar a entidade<code>OrgaoExterno</code>
	 * 
	 * @author rogerio.costa
	 * 
	 * @param OrgaoExterno
	 */
	@Override
	public OrgaoExterno save(OrgaoExterno entity) {

		entity.setClasseParceiro(this.getClasseParceiroDao().obterPorCodigoDominioTipParceiro(Dominio.TIPO_PARCEIRO_ORGAO_EXTERNO));

		return super.save(entity);

	}

	/**
	 * Retorna o valor do atributo <code>orgaoExternoDao</code>
	 * 
	 * @return <code>OrgaoExternoDao</code>
	 */
	public OrgaoExternoDao getOrgaoExternoDao() {

		return orgaoExternoDao;
	}

	/**
	 * Retorna o valor do atributo <code>classeParceiroDao</code>
	 * 
	 * @return <code>ClasseParceiroDao</code>
	 */
	public ClasseParceiroDao getClasseParceiroDao() {

		return classeParceiroDao;
	}

}
