package br.com.centralit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.NivelAutoridadeGrupoDao;
import br.com.centralit.api.model.NivelAutoridade;
import br.com.centralit.api.model.NivelAutoridadeGrupo;
import br.com.centralit.api.service.GrupoService;
import br.com.centralit.api.service.NivelAutoridadeGrupoService;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;

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
 * @since 22/06/2015 - 15:51:01
 *
 * @version 1.0.0
 *
 * @author lucas.ribeiro - (<a href="mailto:lucas.ribeiro@centralit.com.br">lucas.ribeiro@centralit.com.br</a>)
 *
 */
@Service("nivelAutoridadeGrupoService")
public class NivelAutoridadeGrupoServiceImpl extends GenericServiceImpl<NivelAutoridadeGrupo, Long> implements NivelAutoridadeGrupoService {

    @SuppressWarnings("unused")
	private NivelAutoridadeGrupoDao nivelAutoridadeGrupoDao;

    /** Atributo bemPatrimonialService. */
	@Autowired
	private GrupoService grupoService;

    @Autowired
	public NivelAutoridadeGrupoServiceImpl(NivelAutoridadeGrupoDao nivelAutoridadeGrupoDao,  @Qualifier("nivelAutoridadeGrupoValidator") Validator validator) {
    	this.dao = nivelAutoridadeGrupoDao;
        this.nivelAutoridadeGrupoDao = nivelAutoridadeGrupoDao;
    	this.validator = validator;
	}

	/**
	 *
	 */
	@Override
	public void montarGrupos(NivelAutoridade nivelAutoridade) {

		// PERCORRE A LISTA DE GRUPOS
		if(nivelAutoridade.getGrupos() != null){
			for(NivelAutoridadeGrupo nivelAutoridadeGrupo : nivelAutoridade.getGrupos()) {
				// CARREGA O OBJETO GRUPO
				nivelAutoridadeGrupo.setGrupo(this.grupoService.getReference(nivelAutoridadeGrupo.getGrupo().getId()));
				nivelAutoridadeGrupo.setNivelAutoridade(nivelAutoridade);
			}
		}
	}

}
