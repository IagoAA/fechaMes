package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.PaisDao;
import br.com.centralit.api.model.Pais;
import br.com.centralit.api.service.PaisService;
import br.com.centralit.api.service.RegiaoService;
import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilObjeto;


/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p>
 * 
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
 * 	
 * @since 26/11/2014 - 10:10:25
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *	
 */

@Service("paisService")
public class PaisServiceImpl extends GenericServiceImpl<Pais, Long> implements PaisService{

	/** Atributo paisDao. */
	private PaisDao paisDao;
	
	/** Atributo regiaoService. */
	@Autowired
	private RegiaoService regiaoService;
	
	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * @param paisDao
	 * @param validator
	 */
	@Autowired
	public PaisServiceImpl(PaisDao paisDao, @Qualifier("paisValidator") Validator validator) {
		
		this.paisDao = paisDao;
		
		this.dao = paisDao;
		
		this.validator = validator;
		
	}
	
	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por listar a entidade<code>Pais</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param nome
	 * @return Collection<Pais>
	 */
	public Collection<Pais> listarPais(String nome) {

		return this.paisDao.listarPais(nome);
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">563</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por salvar pais
	 *
	 * @author iago.almeida
	 *
	 * @param pais
	 * @return <Pais>
	 */
	@Override
	public Pais save(Pais pais) {
		
		// VALIDA CAMPO OBRIGATÓRIO DA ENTIDADE
		if (UtilObjeto.isReferencia(this.validator)) {

			this.validarEntidade(pais, this.validator);
		}
		
		validarExistenciaPaisMesmoNome(pais);
		
		// VERIFICA SE O PAÍS JÁ TEM CÓDIGO, SENÃO GERA E SALVA ENTIDADE
		if(pais.getCodigo() != null){
			
			return super.save(pais);
			
		}else{
			
			Pais entitySaved = super.save(pais);
			
			entitySaved.setCodigo(Long.valueOf(entitySaved.getId().toString().hashCode()).toString());
			
			return entitySaved;
			
		}
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por inativar o pais setando dataInativo.
	 *
	 * @author iago.almeida
	 *
	 * @param idPais
	 * @return
	 */
	@Override
	public boolean removeById(Long idPais) {
		
		if(this.regiaoService.existeRegiaoVinculadoAoPais(idPais)){
			throw new BusinessException("MSG.MN020", CodigoErro.REGRA_NEGOCIO.getValue());
		}
		
		return this.dao.removeById(idPais);
	}	

	/**
	 * Método responsável por atualizar país
	 * 
	 * @author luis.camargo
	 * 
	 * @param pais
	 * @return <Pais>
	 */
	@Override
	public Pais merge(Pais entity) {
		validarExistenciaPaisMesmoNome(entity);
		return super.merge(entity);
	}
	
	/**
	 * Método responsável por verificar se existe um país já cadastrado com o mesmo nome.
	 * 
	 * @author luis.camargo
	 * 
	 * @param pais
	 * @throws BusinessException
	 */
	private void validarExistenciaPaisMesmoNome(Pais pais) {
		boolean hasPaisMesmoNome = this.paisDao.existePaisMesmoNome(pais);
		
		if (hasPaisMesmoNome) {
			throw new BusinessException("MSG.PAIS_DUPLICADO", CodigoErro.REGRA_NEGOCIO.getValue());
		}			
	}

}
