package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.PainelGrupoDao;
import br.com.centralit.api.service.PainelGrupoService;
import br.com.centralit.framework.model.Painel;
import br.com.centralit.framework.model.PainelGrupo;
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
 * <b>Title: </b>PainelGrupoServiceImpl
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
 * @since 28/04/2015 - 16:47:50
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Service("painelGrupoService")
public class PainelGrupoServiceImpl extends GenericServiceImpl<PainelGrupo, Long> implements PainelGrupoService {

	/** Atributo painelGrupoDao. */
	@Autowired
	private PainelGrupoDao painelGrupoDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param painelGrupoDao
	 */
	@Autowired
	public PainelGrupoServiceImpl( PainelGrupoDao painelGrupoDao ) {

		this.dao = painelGrupoDao;

		this.painelGrupoDao = painelGrupoDao;

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

		Collection<PainelGrupo> listaPainelGrupoVinculados = this.painelGrupoDao.findPorIdPainel(entity.getId());
		// Verifica se a lista de painelGrupo não é null ou vazio.
		if (!UtilColecao.isVazio(listaPainelGrupoVinculados)) {

			for (PainelGrupo painelGrupo : listaPainelGrupoVinculados) {
				// Verifica se a lista painelGrupo contem o painelGrupo, caso não contem remove o mesmo.
				if (!entity.getPainelGrupos().contains(painelGrupo)) {

					painelGrupo.setPainel(null);

					painelGrupo.setPainelRemocao(entity);

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
	 * Método responsável por listar os GrupoUsuario através do id do usuario
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idUsuario
	 * 
	 * @return Collection<Parceiro>
	 */
	public Collection<PainelGrupo> findPorIdPainel(Long idPainel) {

		return this.painelGrupoDao.findPorIdPainel(idPainel);

	}
}
