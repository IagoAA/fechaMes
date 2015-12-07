package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.PainelModuloDao;
import br.com.centralit.api.service.PainelModuloService;
import br.com.centralit.framework.model.Painel;
import br.com.centralit.framework.model.PainelModulo;
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
 * <b>Title: </b>PainelModuloServiceImpl
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
@Service("painelModuloService")
public class PainelModuloServiceImpl extends GenericServiceImpl<PainelModulo, Long> implements PainelModuloService {

	/** Atributo painelModuloDao. */
	@Autowired
	private PainelModuloDao painelModuloDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param painelModuloDao
	 */
	@Autowired
	public PainelModuloServiceImpl( PainelModuloDao painelModuloDao ) {

		this.dao = painelModuloDao;

		this.painelModuloDao = painelModuloDao;

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

		Collection<PainelModulo> listaPainelModuloVinculados = this.painelModuloDao.findPorIdPainel(entity.getId());
		// Verifica se a lista de painelModulo não é null ou vazio.
		if (!UtilColecao.isVazio(listaPainelModuloVinculados)) {

			for (PainelModulo painelModulo : listaPainelModuloVinculados) {
				// Verifica se a lista painelModulo contem o painelModulo, caso não contem remove o mesmo.
				if (!entity.getPainelModulos().contains(painelModulo)) {

					painelModulo.setPainel(null);

					painelModulo.setPainelRemocao(entity);

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
	 * Método responsável por listar os ModuloUsuario através do id do usuario
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idUsuario
	 * 
	 * @return Collection<Parceiro>
	 */
	public Collection<PainelModulo> findPorIdPainel(Long idPainel) {

		return this.painelModuloDao.findPorIdPainel(idPainel);

	}
}
