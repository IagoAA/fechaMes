package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.BairroDao;
import br.com.centralit.api.model.Bairro;
import br.com.centralit.api.model.Cidade;
import br.com.centralit.api.service.BairroService;
import br.com.centralit.api.service.CidadeService;
import br.com.centralit.api.service.EnderecoService;
import br.com.centralit.api.viewHelper.EntidadeNomeBuscaVH;
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
 * @since 03/01/2015 - 14:29:57
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *	
 */
@Service("bairroService")
public class BairroServiceImpl extends GenericServiceImpl<Bairro, Long> implements BairroService {

	/** Atributo bairroDao. */
	private BairroDao bairroDao;
	
	/** Atributo cidadeService. */
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private EnderecoService enderecoService;
	
    /**
     * Responsável pela criação de novas instâncias desta classe.
     * @param bairroDao
     * @param validator
     */
    @Autowired
	public BairroServiceImpl(BairroDao bairroDao, @Qualifier("bairroValidator") Validator validator) {
    	
    	this.dao = bairroDao;
    	
       	this.bairroDao = bairroDao;
    	
    	this.validator = validator;
  
	}
    
	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">563</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por salvar bairro 
	 *
	 * @author iago.almeida
	 *
	 * @param cidade
	 * @return <Cidade>
	 */
	@Override
	public Bairro save(Bairro bairro) {

		// atualizarAtributos(bairro);
		
		// VALIDA CAMPO OBRIGATÓRIO DA ENTIDADE
		if (UtilObjeto.isReferencia(this.validator)) {

			this.validarEntidade(bairro, this.validator);
		}
		
		validarExistenciaBairroMesmoNome(bairro);
		
		// VERIFICA SE O BAIRRO JÁ TEM CÓDIGO, SENÃO GERA E SALVA ENTIDADE
		if(bairro.getCodigo() != null){
			
			return super.save(bairro);
			
		}else{
			
			Bairro entitySaved = super.save(bairro);
			
			entitySaved.setCodigo(Long.valueOf(entitySaved.getId().toString().hashCode()).toString());

			return entitySaved;
			
		}
	}
	
	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por listar a entidade <code>Bairro</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param cidade
	 * @return Collection<Bairro>
	 */
	public Collection<Bairro> listarBairro(EntidadeNomeBuscaVH<Cidade> cidade) {

		return this.bairroDao.listarBairro(cidade);
	}
	
	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por verificar se a cidade a ser excluida é utilizada por bairro
	 *
	 * @author iago.almeida
	 *
	 * @param idCidade
	 * @return boolean
	 */
	@Override
	public boolean existeBairroVinculadoACidade(Long idCidade) {
		return this.bairroDao.existeBairroVinculadoACidade(idCidade);
	}
	
	/**
	 * Método responsável por inativar o bairro setando dataInativo.
	 *
	 * @author iago.almeida
	 *
	 * @param bairroId
	 * @return
	 */
	@Override
	public boolean removeById(Long bairroId) {
		
		if(this.enderecoService.existeBairroVinculadoAEndereco(bairroId)){
			
			throw new BusinessException("MSG.MN020", CodigoErro.REGRA_NEGOCIO.getValue());
			
		}
		
		return this.dao.removeById(bairroId);
		
	}
	
	/**
	 * Método responsável por atualizar bairro
	 * 
	 * @author luis.camargo
	 * 
	 * @param bairro
	 * @return <Bairro> 
	 */
	@Override
	public Bairro merge(Bairro entity) {
		validarExistenciaBairroMesmoNome(entity);
		return super.merge(entity);
	}
	
	/**
	 * Método responsável por verificar se existe um bairro já cadastrado com o mesmo nome para uma cidade.
	 * 
	 * @author luis.camargo
	 * 
	 * @param bairro
	 * @throws BusinessException
	 */
	private void validarExistenciaBairroMesmoNome(Bairro bairro) {
		boolean hasBairroMesmoNome = this.bairroDao.existeBairroMesmoNomePorCidade(bairro);
		
		if (hasBairroMesmoNome) {
			throw new BusinessException("MSG.BAIRRO_DUPLICADO", CodigoErro.REGRA_NEGOCIO.getValue());
		}
	}

} 