package br.com.centralit.api.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.MapaOrganizacionalDao;
import br.com.centralit.api.model.MapaOrganizacional;
import br.com.centralit.api.model.MapaOrganizacionalObservacao;
import br.com.centralit.api.service.MapaOrganizacionalService;
import br.com.centralit.api.service.UsuarioService;
import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.Usuario;
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
 * @since 10/12/2014 - 15:52:44
 *
 * @version 1.0.0
 *
 * @author thiago.borges
 *
 */
@Service("mapaOrganizacionalService")
public class MapaOrganizacionalServiceImpl extends GenericServiceImpl<MapaOrganizacional, Long> implements MapaOrganizacionalService {

	/** Atributo mapaOrganizacionalDao. */
	private MapaOrganizacionalDao mapaOrganizacionalDao;

	/** Atributo usuarioService. */
	@Autowired
	private UsuarioService usuarioService;

    /**
     * Responsável pela criação de novas instâncias desta classe.
     * @param mapaOrganizacionalDao
     */
    @Autowired
	public MapaOrganizacionalServiceImpl(MapaOrganizacionalDao mapaOrganizacionalDao, @Qualifier("mapaOrganizacionalValidator") Validator validator) {

    	this.dao = mapaOrganizacionalDao;

    	this.mapaOrganizacionalDao = mapaOrganizacionalDao;

    	this.validator = validator;
	}


	/**
	 * Metodo responsavel por salvar o mapa organizacional
	 */
	@Override
	public MapaOrganizacional save(MapaOrganizacional entity) {
		if (UtilObjeto.isReferencia(this.validator)) {

			this.validarEntidade(entity, this.validator);

		}

		//Verifica se o mapa organizacional possui alguma observação para montar os vinculo necessarios
		if(entity.getObservacoes() != null && !entity.getObservacoes().isEmpty())
			this.montarVinculos(entity);
		
		//busca o mapa ativo
		MapaOrganizacional mapaOrganizacional = this.findMapaAtivo();
		
		if(UtilObjeto.isReferencia(mapaOrganizacional) && UtilObjeto.isReferencia(mapaOrganizacional.getId())){
			
			//Pega a data inicio do mapa que vai ser salvo
			Calendar data = (Calendar) entity.getDataInicio().clone();
			
			//Remove um dia da data
			data.add(Calendar.DAY_OF_MONTH, -1);
			
			//seta uma data final no mapa que está ativo
			mapaOrganizacional.setDataFim(data);
			
			//Atualiza o mapa ativo com a dataFim
			mapaOrganizacionalDao.merge(mapaOrganizacional);

		}

		//salva o novo mapa ativo
		MapaOrganizacional entitySaved = (MapaOrganizacional) this.mapaOrganizacionalDao.save(entity);
		
		entitySaved.setCodigo(Long.valueOf(entitySaved.getId().toString().hashCode()).toString());
		
		return entitySaved;

	}

	/**
	 * Metodo responsavel por atualizar o mapa organizacional
	 */
	@Override
	public MapaOrganizacional merge(MapaOrganizacional entity) {
		if (UtilObjeto.isReferencia(this.validator)) {

			this.validarEntidade(entity, this.validator);

		}

		// Verifica se o mapa organizacional possui alguma observação para montar os vinculo necessarios
		if(entity.getObservacoes() != null && !entity.getObservacoes().isEmpty())
			this.montarVinculos(entity);

		// Atualiza o mapa
		return (MapaOrganizacional) this.mapaOrganizacionalDao.merge(entity);

	}

	//Metodo responsavel por montar os vinculos necessarios para salvar o mapa organizacional
	private void montarVinculos(MapaOrganizacional entity) {

		//percorre a lista de observações e seta o mapa em cada uma
		for (MapaOrganizacionalObservacao observacao : entity.getObservacoes()) {

			observacao.setAutor(usuarioService.find(observacao.getAutor().getId()));

			observacao.setMapaOrganizacional(entity);

		}
	}

	/**
	 * Metodo responsavel por fazer a remoção do mapa ativo
	 */
	@Override
	public boolean removeById(Long id){

		//busca o utimo mapa fechado
		MapaOrganizacional mapaOrganizacional = mapaOrganizacionalDao.findUltimoMapaFechado();

		// verifica se o mapa está nulo
		if(!UtilObjeto.isReferencia(mapaOrganizacional)){

			throw new BusinessException("VALIDACAO.ULTIMO_MAPA_VIGENTE", CodigoErro.REGRA_NEGOCIO.getValue());

		}else{

			//retira a data fim do ultimo mapa fechado
			mapaOrganizacional.setDataFim(null);

			//remove o mapa
			return mapaOrganizacionalDao.removeById(id);

		}
	}


	/**
	 *{@inheritDoc}
	 */
	@Override
	public MapaOrganizacional findUltimoMapaFechado() {

		MapaOrganizacional mapa = mapaOrganizacionalDao.findUltimoMapaFechado();

		if(UtilObjeto.isReferencia(mapa)){

			return mapa;

		}else{

			return new MapaOrganizacional();
		}
	}


	/**
	 *{@inheritDoc}
	 */
	@Override
	public MapaOrganizacional findMapaAtivo() {

		Usuario usuarioLogado = this.getUsuarioLogado();
		return UtilObjeto.isReferencia(usuarioLogado.getId()) ? this.mapaOrganizacionalDao.findMapaAtivo(usuarioLogado) : new MapaOrganizacional();
	}


	private Usuario getUsuarioLogado() {

		if(UtilObjeto.isReferencia(SecurityContextHolder.getContext().getAuthentication())){
			Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return usuarioService.find(usuario.getId());
		}
		
		return new Usuario();
	}
}
