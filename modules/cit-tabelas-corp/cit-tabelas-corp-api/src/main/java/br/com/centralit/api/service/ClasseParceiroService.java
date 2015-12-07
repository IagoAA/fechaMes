package br.com.centralit.api.service;

import br.com.centralit.api.model.ClasseParceiro;
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
 * <b>Title: </b>ClasseParceiroService
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
 * @since 05/01/2015 - 15:29:04
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
public interface ClasseParceiroService extends GenericService<ClasseParceiro, Long> {

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
	ClasseParceiro obterPorCodigoDominioTipParceiro(Long codigo);

}
