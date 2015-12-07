package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.PainelItemGrupoDao;
import br.com.centralit.api.service.PainelItemGrupoService;
import br.com.centralit.framework.model.PainelItem;
import br.com.centralit.framework.model.PainelItemGrupo;
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
 * @since 29/04/2015 - 15:09:58
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Service("painelItemGrupoService")
public class PainelItemGrupoServiceImpl extends GenericServiceImpl<PainelItemGrupo, Long> implements PainelItemGrupoService {

	/** Atributo painelItemGrupoDao. */
	private PainelItemGrupoDao painelItemGrupoDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param widgetGrupoDao
	 */
	@Autowired
	public PainelItemGrupoServiceImpl( PainelItemGrupoDao painelItemGrupoDao ) {

		this.dao = painelItemGrupoDao;

		this.painelItemGrupoDao = painelItemGrupoDao;
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

		Collection<PainelItemGrupo> listaPainelItemGrupoVinculados = this.painelItemGrupoDao.findPorIdPainelItem(entity.getId());
		// Verifica se a lista de painelItemGrupo não é null ou vazio.
		if (!UtilColecao.isVazio(listaPainelItemGrupoVinculados)) {

			for (PainelItemGrupo painelItemGrupo : listaPainelItemGrupoVinculados) {
				// Verifica se a lista painelItemGrupo contem o painelItemGrupo, caso não contem remove o mesmo.
				if (!entity.getPainelItemGrupos().contains(painelItemGrupo)) {

					painelItemGrupo.setPainelItem(null);

					painelItemGrupo.setPainelItemRemocao(entity);

					this.merge(painelItemGrupo);

					this.remove(painelItemGrupo);

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
	 * Método responsável por listar os PainelItemGrupo através do id do usuario
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idPainel
	 * 
	 * @return Collection<Parceiro>
	 */
	public Collection<PainelItemGrupo> findPorIdPainelItem(Long idPainel) {

		return this.painelItemGrupoDao.findPorIdPainelItem(idPainel);
	}
}
