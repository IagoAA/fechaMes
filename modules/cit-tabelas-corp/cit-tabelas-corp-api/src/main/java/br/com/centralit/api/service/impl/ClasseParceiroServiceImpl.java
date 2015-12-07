package br.com.centralit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.ClasseParceiroDao;
import br.com.centralit.api.model.ClasseParceiro;
import br.com.centralit.api.service.ClasseParceiroService;
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
 * @since 05/01/2015 - 15:31:06
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Service("classeParceiroService")
public class ClasseParceiroServiceImpl extends GenericServiceImpl<ClasseParceiro, Long> implements ClasseParceiroService {

	/** Atributo classeParceiroDao. */
	private ClasseParceiroDao classeParceiroDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param classeParceiroDao
	 */
	@Autowired
	public ClasseParceiroServiceImpl( ClasseParceiroDao classeParceiroDao ) {

		this.dao = classeParceiroDao;
		
		this.classeParceiroDao = classeParceiroDao;

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
	 * Método responsável por obter através do codigo do dominioClasseParceiro
	 * 
	 * @author rogerio.costa
	 * 
	 * @param codigo
	 * 
	 * @return ClasseParceiro
	 */
	public ClasseParceiro obterPorCodigoDominioTipParceiro(Long codigo) {

		return this.getClasseParceiroDao().obterPorCodigoDominioTipParceiro(codigo);
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
