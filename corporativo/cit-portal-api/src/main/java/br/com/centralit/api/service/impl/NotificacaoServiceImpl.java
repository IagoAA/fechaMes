package br.com.centralit.api.service.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.NotificacaoDao;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.GrupoService;
import br.com.centralit.api.service.GrupoUsuarioService;
import br.com.centralit.api.service.NotificacaoService;
import br.com.centralit.api.service.UsuarioService;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.Notificacao;
import br.com.centralit.framework.model.NotificacaoGrupo;
import br.com.centralit.framework.model.NotificacaoUsuario;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilColecao;
import br.com.centralit.framework.util.UtilObjeto;

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
 * <b>Title: NotificacaoServiceImpl</b>
 * </p>
 * 
 * <p>
 * <b>Description: </b>
 * </p>
 * 
 * @since 20/11/2014 - 09:59:04
 * 
 * @version 1.0.0
 * 
 * @author rodrigo.anaice
 * 
 */
@Service("notificacaoService")
public class NotificacaoServiceImpl extends GenericServiceImpl<Notificacao, Long> implements NotificacaoService {

	private static final String ALERTA_DE_RESSUPRIMENTO_DE_MATERIAL = "Alerta de ressuprimento de material";

	/** Atributo notificacaoDao. */
	private NotificacaoDao notificacaoDao;

	/** Atributo dominioService. */
	@Autowired
	private DominioService dominioService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private GrupoService grupoService;

	/** Atributo grupoUsuarioService. */
	@Autowired
	private GrupoUsuarioService grupoUsuarioService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param notificacaoDao
	 */
	@Autowired
	public NotificacaoServiceImpl( NotificacaoDao notificacaoDao, @Qualifier("notificacaoValidator") Validator validator ) {

		this.dao = notificacaoDao;

		this.notificacaoDao = notificacaoDao;

		this.validator = validator;
	}

	@Override
	public Notificacao save(Notificacao entity) {

		montarDadosNotificacao(entity);

		return super.save(entity);
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
	 * @return
	 */
	public Notificacao visualizar(Notificacao entity) {

		Usuario usuario = this.usuarioService.find(( (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getId());

		entity = this.getReference(entity.getId());

		Notificacao notificacaoUsuario = new Notificacao(entity.getAssunto(), entity.getMensagem(), entity.getTipoNotificacao(), entity.getTipoPrioridade(), usuario, Calendar.getInstance(), entity.getIdentificador());

		return this.save(notificacaoUsuario);

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
	private void montarDadosNotificacao(Notificacao entity) {

		entity.setUsuario(this.usuarioService.find(entity.getUsuario().getId()));

		if (UtilColecao.isVazio(entity.getUsuario().getPaginasUsuario())) {

			entity.getUsuario().setPaginasUsuario(null);
		}

		// Verifica se o tipoNotificação não é null
		if (UtilObjeto.isReferencia(entity.getTipoNotificacao()) && UtilObjeto.isReferencia(entity.getTipoNotificacao().getCodigo())) {

			entity.setTipoNotificacao(this.dominioService.findByChaveAndCodigo(Dominio.TIPO_NOTIFICACAO, entity.getTipoNotificacao().getCodigo()));
		}
		// Verifica se o tipoPrioridade não é null
		if (UtilObjeto.isReferencia(entity.getTipoPrioridade()) && UtilObjeto.isReferencia(entity.getTipoPrioridade().getCodigo())) {

			entity.setTipoPrioridade(this.dominioService.findByChaveAndCodigo(Dominio.TIPO_PRIORIDADE, entity.getTipoPrioridade().getCodigo()));
		}
		
		if (UtilObjeto.isReferencia(entity.getNotificacaoGrupos())) {
			for (NotificacaoGrupo notificacaoGrupo : entity.getNotificacaoGrupos()) {
				notificacaoGrupo.setNotificacao(entity);
				notificacaoGrupo.setGrupo(this.grupoService.find(notificacaoGrupo.getGrupo().getId()));
			}
		}
	
		if (UtilObjeto.isReferencia(entity.getNotificacaoUsuarios())) {
			for (NotificacaoUsuario notificacaoUsuario : entity.getNotificacaoUsuarios()) {
				notificacaoUsuario.setNotificacao(entity);
				notificacaoUsuario.setUsuario(this.usuarioService.find(notificacaoUsuario.getUsuario().getId()));
			}
		}

	}

	@Override
	public Notificacao merge(Notificacao entity) {

		montarDadosNotificacao(entity);

		return super.merge(entity);
	}

	@Override
	public Notificacao salvarNotificacaoRessuprimento(Notificacao notificacao) {

		notificacao.setIdentificador(UUID.randomUUID().toString());

		notificacao.setAssunto(ALERTA_DE_RESSUPRIMENTO_DE_MATERIAL);

		notificacao.setTipoNotificacao(this.dominioService.findByChaveAndCodigo(Dominio.TIPO_NOTIFICACAO, Dominio.TIPO_NOTIFICACAO_ALMOXARIFADO));

		notificacao.setTipoPrioridade(this.dominioService.findByChaveAndCodigo(Dominio.TIPO_PRIORIDADE, Dominio.TIPO_PRIORIDADE_ALTA));

		return super.merge(notificacao);
	}

	/**
	 * 
	 * Método responsável por listar a entidade <code>Notificacao</code> através do id do usuario
	 * 
	 * @author rodrigo.anaice
	 * 
	 * @param idUsuario
	 * 
	 * @return Collection<Notificacao>
	 */
	public Collection<Notificacao> listarNotificacaoPorUsuario() {

		Long idUsuarioLogado = ( (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getId();

		Collection<Long> idsGrupoUsuario = this.grupoUsuarioService.findIdsGrupoUsuarioPorIdUsuario(idUsuarioLogado);

		return this.getNotificacaoDao().listarNotificacaoPorUsuarioGrupo(idUsuarioLogado, idsGrupoUsuario);

	}

	/**
	 * Retorna o valor do atributo <code>notificacaoDao</code>
	 * 
	 * @return <code>NotificacaoDao</code>
	 */
	public NotificacaoDao getNotificacaoDao() {

		return notificacaoDao;
	}

}
