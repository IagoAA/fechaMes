package br.com.centralit.api.service.impl;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.EstadoDao;
import br.com.centralit.api.model.Estado;
import br.com.centralit.api.model.Regiao;
import br.com.centralit.api.service.CidadeService;
import br.com.centralit.api.service.EstadoService;
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
 * @since 03/01/2015 - 15:24:37
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *	
 */
@Service("estadoService")
public class EstadoServiceImpl extends GenericServiceImpl<Estado, Long> implements EstadoService {

	/** Atributo cidadeService. */
	@Autowired
	private CidadeService cidadeService;
	
	/** Atributo estadoDao. */
	private EstadoDao estadoDao;
	
	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * @param estadoDao
	 * @param validator
	 */
	@Autowired
	public EstadoServiceImpl (EstadoDao estadoDao, @Qualifier("estadoValidator") Validator validator) {
		
		this.dao = estadoDao;
		
		this.estadoDao = estadoDao;
		
		this.validator = validator;
		
	}
	
	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">563</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por salvar estado 
	 *
	 * @author iago.almeida
	 *
	 * @param estado
	 * @return <Estado>
	 */
	@Override
	public Estado save(Estado estado) {
		
		// VALIDA CAMPO OBRIGATÓRIO DA ENTIDADE
		if (UtilObjeto.isReferencia(this.validator)) {

			this.validarEntidade(estado, this.validator);
		}
		
		validarExistenciaEstadoMesmoNome(estado);
		
		// VERIFICA SE O ESTADO JÁ TEM CÓDIGO, SENÃO GERA E SALVA ENTIDADE
		if(estado.getCodigo() != null){
			
			return super.save(estado);
			
		}else{
			Estado entitySaved = super.save(estado);
			
			entitySaved.setCodigo(Long.valueOf(entitySaved.getId().toString().hashCode()).toString());
			
			return entitySaved;
			
		}
		
	}
	
	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por listar a entidade<code>Estado</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param regiao
	 * @param nome
	 * @return Collection<Estado>
	 */
	public Collection<Estado> listarEstado(Regiao regiao, String nome) {

		return this.estadoDao.listarEstado(regiao, nome);
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por inativar o estado setando dataInativo.
	 *
	 * @author iago.almeida
	 *
	 * @param idEstado
	 * @return
	 */
	@Override
	public boolean removeById(Long idEstado) {
		
		if(this.cidadeService.existeCidadeVinculadoAoEstado(idEstado)){
			throw new BusinessException("MSG.MN020", CodigoErro.REGRA_NEGOCIO.getValue());
		}
		
		return this.dao.removeById(idEstado);
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">563</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por atualizar estado 
	 *
	 * @author iago.almeida
	 *
	 * @param estado
	 * @return <Estado>
	 */
	@Override
	public Estado merge(Estado entity) {
		validarExistenciaEstadoMesmoNome(entity);
		Estado estado = (Estado) this.estadoDao.getReference(entity.getId());
		entity.setListaCidade(estado.getListaCidade());
		return super.merge(entity);
	}
	
	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por verificar se a regiao a ser excluida é utilizada por estado
	 *
	 * @author iago.almeida
	 *
	 * @param idRegiao
	 * @return boolean
	 */
	@Override
	public boolean existeEstadoVinculadoARegiao(Long idRegiao) {
		return this.estadoDao.existeEstadoVinculadoARegiao(idRegiao);
	}
	

	/**
	 * Método responsável por verificar se existe um estado já cadastrado com o mesmo nome.
	 * 
	 * @author luis.camargo
	 * 
	 * @param estado
	 * @throws BusinessException
	 */
	private void validarExistenciaEstadoMesmoNome(Estado estado) {
		boolean hasEstadoMesmoNome = this.estadoDao.existeEstadoMesmoNome(estado);
		
		if (hasEstadoMesmoNome) {
			throw new BusinessException("MSG.ESTADO_DUPLICADO", CodigoErro.REGRA_NEGOCIO.getValue());
		}			
	}

}
