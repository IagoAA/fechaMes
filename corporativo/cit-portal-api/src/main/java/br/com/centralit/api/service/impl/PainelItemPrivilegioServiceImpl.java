package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.PainelItemPrivilegioDao;
import br.com.centralit.api.service.PainelItemPrivilegioService;
import br.com.centralit.framework.model.PainelItem;
import br.com.centralit.framework.model.PainelItemPrivilegio;
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
 * @since 29/04/2015 - 16:51:37
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Service("painelItemPrivilegioService")
public class PainelItemPrivilegioServiceImpl extends GenericServiceImpl<PainelItemPrivilegio, Long> implements PainelItemPrivilegioService {

	/** Atributo painelItemPrivilegioDao. */
	private PainelItemPrivilegioDao painelItemPrivilegioDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param painelItemPrivilegioDao
	 */
	@Autowired
	public PainelItemPrivilegioServiceImpl( PainelItemPrivilegioDao painelItemPrivilegioDao ) {

		this.dao = painelItemPrivilegioDao;

		this.painelItemPrivilegioDao = painelItemPrivilegioDao;
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
	public void verificarRemocao(PainelItem entity) {

		Collection<PainelItemPrivilegio> listaPainelItemPrivilegioVinculados = this.painelItemPrivilegioDao.findPorIdPainelItem(entity.getId());
		// Verifica se a lista de painelItemGrupo não é null ou vazio.
		if (!UtilColecao.isVazio(listaPainelItemPrivilegioVinculados)) {

			for (PainelItemPrivilegio painelItemPrivilegio : listaPainelItemPrivilegioVinculados) {
				// Verifica se a lista painelItemGrupo contem o painelItemGrupo, caso não contem remove o mesmo.
				if (!entity.getPainelItemPrivilegios().contains(painelItemPrivilegio)) {

					painelItemPrivilegio.setPainelItem(null);

					painelItemPrivilegio.setPainelItemRemocao(entity);

					this.merge(painelItemPrivilegio);

					this.remove(painelItemPrivilegio);

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
	 * Método responsável por listar os PainelItemPrivilegio através do id do PainelItem
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idPainelItem
	 * 
	 * @return Collection<Parceiro>
	 */
	public Collection<PainelItemPrivilegio> findPorIdPainelItem(Long idPainelItem) {

		return this.painelItemPrivilegioDao.findPorIdPainelItem(idPainelItem);
	}

}
