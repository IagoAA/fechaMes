package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.PainelPrivilegioDao;
import br.com.centralit.api.service.PainelPrivilegioService;
import br.com.centralit.framework.model.Painel;
import br.com.centralit.framework.model.PainelPrivilegio;
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
 * <b>Title: </b>PainelPrivilegioServiceImpl
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
 * @since 29/04/2015 - 09:15:52
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Service("painelPrivilegioService")
public class PainelPrivilegioServiceImpl extends GenericServiceImpl<PainelPrivilegio, Long> implements PainelPrivilegioService {

	/** Atributo painelPrivilegioDao. */
	public PainelPrivilegioDao painelPrivilegioDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param painelPrivilegioService
	 */
	@Autowired
	public PainelPrivilegioServiceImpl( PainelPrivilegioDao painelPrivilegioDao ) {

		this.dao = painelPrivilegioDao;

		this.painelPrivilegioDao = painelPrivilegioDao;

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
	public void verificarRemocao(Painel entity) {

		Collection<PainelPrivilegio> listaPainelPrivilegioVinculados = this.painelPrivilegioDao.findPorIdPainel(entity.getId());
		// Verifica se a lista de painelPrivilegio não é null ou vazio.
		if (!UtilColecao.isVazio(listaPainelPrivilegioVinculados)) {

			for (PainelPrivilegio painelPrivilegio : listaPainelPrivilegioVinculados) {
				// Verifica se a lista painelPrivilegio contem o painelPrivilegio, caso não contem remove o mesmo.
				if (!entity.getPainelPrivilegios().contains(painelPrivilegio)) {

					painelPrivilegio.setPainel(null);

					painelPrivilegio.setPainelRemocao(entity);
				}
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
	 * Método responsável por
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idPainel
	 * @return
	 */
	public Collection<PainelPrivilegio> findPorIdPainel(Long idPainel) {

		return this.painelPrivilegioDao.findPorIdPainel(idPainel);
	}
}
