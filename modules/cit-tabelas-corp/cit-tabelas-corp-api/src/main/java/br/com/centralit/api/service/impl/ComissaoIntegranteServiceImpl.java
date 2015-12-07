package br.com.centralit.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.ComissaoIntegranteDao;
import br.com.centralit.api.model.Colaborador;
import br.com.centralit.api.model.ComissaoIntegrante;
import br.com.centralit.api.service.ComissaoIntegranteService;
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
 * <b>Title: Implementação dos Serviços de InventarioComissaoIntegrante </b>
 * </p>
 * 
 * <p>
 * <b>Description: Implementação dos serviços de InventarioComissaoIntegrante </b>
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
 * @since 29/12/2014 - 10:49:27
 * 
 * @version 1.0.0
 * 
 * @author geovane.filho
 * 
 */
@Service("inventarioComissaoIntegranteService")
public class ComissaoIntegranteServiceImpl extends GenericServiceImpl<ComissaoIntegrante, Long> implements ComissaoIntegranteService {

	/** Atributo inventarioComissaoDao. */
	private ComissaoIntegranteDao inventarioComissaoIntegranteDao;

	/**
	 * 
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param inventarioComissaoDao
	 *            DAO de InventarioComissao.
	 */
	@Autowired
	public ComissaoIntegranteServiceImpl( ComissaoIntegranteDao inventarioComissaoIntegranteDao ) {

		this.dao = inventarioComissaoIntegranteDao;

		this.inventarioComissaoIntegranteDao = inventarioComissaoIntegranteDao;
	}
	
	@Override
	public boolean removeById(Long id){

		//busca o item da adição a bem
		ComissaoIntegrante integrante = this.find(id);

		//Verifica se o adicaoBemPrincipalItem não está nulo
		if(integrante != null){
			integrante.setComissaoInativo(integrante.getInventarioComissao());
			integrante.setInventarioComissao(null);
			
			return super.remove(integrante);
		}

		return true;
	}
	
	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por as lista de InventarioComissaoIntegrante por nome do colaborador e idComissao
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param nome
	 * @param idComissao
	 * @return List<InventarioComissaoIntegrante>
	 */
	@Override
	public List<Colaborador> findComissaoIntegrantePorColaborador(String nome, Long idComissao){
		
		List<ComissaoIntegrante> inventarioComissoesIntegrantes = this.inventarioComissaoIntegranteDao.findComissaoIntegrantePorColaborador(nome, idComissao);
		
		List<Colaborador> listaColaboradores = new ArrayList<Colaborador>();
		
		for(ComissaoIntegrante inventarioComissaoIntegrante : inventarioComissoesIntegrantes) {
			
			listaColaboradores.add(inventarioComissaoIntegrante.getIntegrante());
		}
		
		return listaColaboradores;
	}
	
}
