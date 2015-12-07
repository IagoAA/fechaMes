package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.CidadeDao;
import br.com.centralit.api.model.Cidade;
import br.com.centralit.api.model.Estado;
import br.com.centralit.api.service.BairroService;
import br.com.centralit.api.service.CidadeService;
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
 * @since 03/01/2015 - 14:53:32
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *	
 */
@Service("cidadeService")
public class CidadeServiceImpl extends GenericServiceImpl<Cidade, Long> implements CidadeService {

	/** Atributo bairroService. */
	@Autowired
	private BairroService bairroService;
	
	/** Atributo cidadeDao. */
	private CidadeDao cidadeDao;
	
    /**
     * Responsável pela criação de novas instâncias desta classe.
     * @param cidadeDao
     * @param validator
     */
    @Autowired
	public CidadeServiceImpl(CidadeDao cidadeDao, @Qualifier("cidadeValidator") Validator validator) {
    	
    	this.dao = cidadeDao;
    	
    	this.cidadeDao = cidadeDao;
    	
    	this.validator = validator;
    	
	}
    
	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">563</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por salvar cidade 
	 *
	 * @author iago.almeida
	 *
	 * @param cidade
	 * @return <Cidade>
	 */
	@Override
	public Cidade save(Cidade cidade) {

		// VALIDA CAMPO OBRIGATÓRIO DA ENTIDADE
		if (UtilObjeto.isReferencia(this.validator)) {

			this.validarEntidade(cidade, this.validator);
		}
		
		validarExistenciaCidadeMesmoNome(cidade);

		// VERIFICA SE O CIDADE JÁ TEM CÓDIGO, SENÃO GERA E SALVA ENTIDADE
		if(cidade.getCodigo() != null){
			
			return super.save(cidade);
			
		}else{
			Cidade entitySaved = super.save(cidade);
			
			entitySaved.setCodigo(Long.valueOf(entitySaved.getId().toString().hashCode()).toString());

			return entitySaved;
			
		}
	}
	
	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por listar a entidade <code>Cidade</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param estado
	 * @return Collection<Cidade>
	 */
	public Collection<Cidade> listarCidades(EntidadeNomeBuscaVH<Estado> estado) {

		return this.cidadeDao.listarCidades(estado);
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por verificar se o estado a ser excluido é utilizado por cidade
	 *
	 * @author iago.almeida
	 *
	 * @param idEstado
	 * @return boolean
	 */
	@Override
	public boolean existeCidadeVinculadoAoEstado(Long idEstado) {
		return this.cidadeDao.existeCidadeVinculadoAoEstado(idEstado);
	}
	
	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por inativar a cidade setando dataInativo.
	 *
	 * @author iago.almeida
	 *
	 * @param idCidade
	 * @return
	 */
	@Override
	public boolean removeById(Long idCidade) {
		
		if(this.bairroService.existeBairroVinculadoACidade(idCidade)){
			throw new BusinessException("MSG.MN020", CodigoErro.REGRA_NEGOCIO.getValue());
		}
		
		return this.dao.removeById(idCidade);
	}

	/**
	 * Método responsável por atualizar cidade
	 * 
	 * @author luis.camargo
	 * 
	 * @param cidade
	 * @return <Cidade> 
	 */
	@Override
	public Cidade merge(Cidade entity) {
		validarExistenciaCidadeMesmoNome(entity);
		return super.merge(entity);
	}
	
	/**
	 * Método responsável por verificar se existe uma cidade já cadastrada com o mesmo nome para um estado.
	 * 
	 * @author luis.camargo
	 * 
	 * @param cidade
	 * @throws BusinessException
	 */
	private void validarExistenciaCidadeMesmoNome(Cidade cidade) {
		boolean hasCidadeMesmoNome = this.cidadeDao.existeCidadeMesmoNomePorEstado(cidade);
		
		if (hasCidadeMesmoNome) {
			throw new BusinessException("MSG.CIDADE_DUPLICADA", CodigoErro.REGRA_NEGOCIO.getValue());
		}
	}

}
