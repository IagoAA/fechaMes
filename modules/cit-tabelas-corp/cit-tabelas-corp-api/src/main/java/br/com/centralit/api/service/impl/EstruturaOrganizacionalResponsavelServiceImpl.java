package br.com.centralit.api.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.EstruturaOrganizacionalResponsavelDao;
import br.com.centralit.api.model.EstruturaOrganizacional;
import br.com.centralit.api.model.EstruturaOrganizacionalResponsavel;
import br.com.centralit.api.service.EstruturaOrganizacionalResponsavelService;
import br.com.centralit.api.service.EstruturaOrganizacionalService;
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
 * @since 06/01/2015 - 15:10:01
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
@Service("estruturaOrganizacionalResponsavelService")
public class EstruturaOrganizacionalResponsavelServiceImpl extends GenericServiceImpl<EstruturaOrganizacionalResponsavel, Long> implements EstruturaOrganizacionalResponsavelService {

	private static final int UM = 1;
	private EstruturaOrganizacionalResponsavelDao estruturaOrganizacionalResponsavelDao;
	
	@Autowired
	private EstruturaOrganizacionalService estruturaOrganizacionalService;

	@Autowired
	public EstruturaOrganizacionalResponsavelServiceImpl( EstruturaOrganizacionalResponsavelDao estruturaOrganizacionalResponsavelDao, @Qualifier("estruturaOrganizacionalResponsavelValidator") Validator validator ) {

		this.estruturaOrganizacionalResponsavelDao = estruturaOrganizacionalResponsavelDao;

		this.dao = estruturaOrganizacionalResponsavelDao;

		this.validator = validator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<EstruturaOrganizacionalResponsavel> findByIdEstruturaOrganizacional(EstruturaOrganizacional estruturaOrganizacional) {

		return estruturaOrganizacionalResponsavelDao.findByIdEstruturaOrganizacional(estruturaOrganizacional);
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
	 * @param idDetentor
	 * @return
	 */
	public boolean exiteEstruturaOrganizacionalResponsavelVinculadoAoColaborador(Long idColaborador) {

		return this.estruturaOrganizacionalResponsavelDao.exiteEstruturaOrganizacionalResponsavelVinculadoAoColaborador(idColaborador);
	}
	
	@Override
	public boolean removeById(Long id) {
		//busca o item
		EstruturaOrganizacionalResponsavel responsavel = this.find(id);

		//Verifica se o responsavel não está nulo
		if(responsavel != null){
			responsavel.setEstruturaOrganizacionalInativo(responsavel.getEstruturaOrganizacional());
			responsavel.setEstruturaOrganizacional(null);
			
			if (super.remove(responsavel)) {
				this.atualizaOrdemResponsaveis(responsavel.getEstruturaOrganizacionalInativo().getId());
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Método responsável por reordenar a lista de responsaveis de uma estrutura organizacional
	 * 
	 * @author geovane.filho
	 * 
	 * @param estruturaOrganizacionalId id da estrutura a se reordenar
	 */
	private void atualizaOrdemResponsaveis(Long estruturaOrganizacionalId) {
		List<EstruturaOrganizacionalResponsavel> responsaveis = (List<EstruturaOrganizacionalResponsavel>) this.estruturaOrganizacionalResponsavelDao.findByIdEstruturaOrganizacionalOrdemAsc(estruturaOrganizacionalId);
		for (int i = 0; i < responsaveis.size(); i++) {
			EstruturaOrganizacionalResponsavel responsavel = responsaveis.get(i);
			Integer ordem = i + UM;
			responsavel.setOrdem(ordem);
		}
		this.saveList(responsaveis);
	}
}
