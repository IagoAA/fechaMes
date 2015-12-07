package br.com.centralit.api.service.impl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.FavoritoDao;
import br.com.centralit.api.dao.PaginaUsuarioDao;
import br.com.centralit.api.dao.SearchParamsDao;
import br.com.centralit.api.dao.UsuarioDao;
import br.com.centralit.api.service.ConfiguracaoService;
import br.com.centralit.api.service.FavoritoService;
import br.com.centralit.api.service.GrupoUsuarioService;
import br.com.centralit.api.service.OrganizacaoService;
import br.com.centralit.api.service.PaginaUsuarioService;
import br.com.centralit.api.service.UsuarioOrganizacaoItemService;
import br.com.centralit.api.service.UsuarioPrivilegioService;
import br.com.centralit.api.service.UsuarioService;
import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.Favorito;
import br.com.centralit.framework.model.Filter;
import br.com.centralit.framework.model.GrupoUsuario;
import br.com.centralit.framework.model.Organizacao;
import br.com.centralit.framework.model.PaginaUsuario;
import br.com.centralit.framework.model.SearchParams;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.model.UsuarioOrganizacaoItem;
import br.com.centralit.framework.model.UsuarioPrivilegio;
import br.com.centralit.framework.model.arquitetura.PersistentObject;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilColecao;
import br.com.centralit.framework.util.UtilObjeto;

/**
 * Implementation of UserService interface.
 * 
 * @author <a href="mailto:allyjunio@gmail.com">Ally Junio</a>
 */
@Service("usuarioService")
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long> implements UsuarioService, UserDetailsService {

	/** Atributo passwordEncoder. */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/** Atributo usuarioDao. */
	private UsuarioDao usuarioDao;

	@Autowired
	private ConfiguracaoService configuracaoService;

	/** Atributo saltSource. */
	@Autowired(required = false)
	private SaltSource saltSource;

	/** Atributo searchParamsDao. */
	@Autowired
	private SearchParamsDao searchParamsDao;

	/** Atributo favoritoDao. */
	@Autowired
	private FavoritoDao favoritoDao;

	/** Atributo paginaUsuarioDao. */
	@Autowired
	private PaginaUsuarioDao paginaUsuarioDao;

	/** Atributo favoritoService. */
	@Autowired
	private FavoritoService favoritoService;

	/** Atributo paginaUsuarioService. */
	@Autowired
	private PaginaUsuarioService paginaUsuarioService;

	/** Atributo organizacaoService. */
	@Autowired
	private OrganizacaoService organizacaoService;

	/** Atributo usuarioOrganizacaoItemService. */
	@Autowired
	private UsuarioOrganizacaoItemService usuarioOrganizacaoItemService;

	/** Atributo grupoUsuarioService. */
	@Autowired
	private GrupoUsuarioService grupoUsuarioService;

	/** Atributo usuarioPrivilegioService. */
	@Autowired
	private UsuarioPrivilegioService usuarioPrivilegioService;
	@Autowired
	public UsuarioServiceImpl( UsuarioDao usuarioDao, @Qualifier("usuarioValidator") Validator validator ) {

		this.dao = usuarioDao;

		this.usuarioDao = usuarioDao;

		this.validator = validator;
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">563</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por atualizar usuário
	 * 
	 * @author iago.almeida
	 * 
	 * @param user
	 * @return <Usuario>
	 */
	@Override
	public Usuario merge(Usuario user) {

		montarObjeto(user);

		String passwordAnterior = usuarioDao.getUserPassword(user.getId());		

		this.grupoUsuarioService.verificarRemocaoGrupoUsuarioAtravesDoUsuario(user);

		this.usuarioPrivilegioService.verificarRemocaoAtravesDoUsuario(user);

		if (user.getNovaSenha() != null) {

			// Verifica se o password informado é válido alterado
			if (!StringUtils.isEmpty(passwordAnterior) && !passwordEncoder.matches(user.getPassword(), passwordAnterior)) {
				throw new BusinessException("MSG.SENHA_ANTIGA_USUARIO_INVALIDA", CodigoErro.REGRA_NEGOCIO.getValue());
			}

			// Verifica se o password foi alterado
			if (!passwordEncoder.matches(user.getPassword(), passwordEncoder.encode(user.getNovaSenha()))) {
				user.setPassword(passwordEncoder.encode(user.getNovaSenha()));
			} else {

				user.setPassword(passwordEncoder.encode(user.getPassword()));
			}

			if (UtilObjeto.isReferencia(user.getPasswordMobile())) {

				user.setPasswordMobile(passwordEncoder.encode(user.getPasswordMobile()));
			}

		} else {

			user.setPassword(passwordAnterior);
		}

		// VALIDA CAMPO OBRIGATÓRIO DA ENTIDADE
		if (UtilObjeto.isReferencia(this.validator)) {

			this.validarEntidade(user, this.validator);
		}

		if (user.getPaginasUsuario() != null && !user.getPaginasUsuario().isEmpty()) {

			for (PaginaUsuario paginaUsuario : user.getPaginasUsuario()) {

				paginaUsuario.setUsuario(user);

				if (paginaUsuario.getSearchParams() != null) {

					for (SearchParams searchParams : paginaUsuario.getSearchParams()) {

						searchParams.setPaginaUsuario(paginaUsuario);

						if (searchParams.getFilters() != null) {

							for (Filter filter : searchParams.getFilters()) {

								filter.setSearchParams(searchParams);

							}

						}

					}

				}
			}
		}

		return (Usuario) usuarioDao.merge(user);
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">563</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por recuperar usuários
	 * 
	 * @author iago.almeida
	 * 
	 * @param id
	 * @return <Usuario>
	 */
	@Override
	public Usuario getReference(Long idUsuario) {

		Usuario usuario = super.getReference(idUsuario);

		usuario.setPaginasUsuario(this.paginaUsuarioService.findPorIdUsuario(idUsuario));

		usuario.setOrganizacoes(this.usuarioOrganizacaoItemService.buscaOrganizacoesAtivasPorIdUsuario(idUsuario));

		usuario.setGrupoUsuarios(this.grupoUsuarioService.findPorIdUsuario(idUsuario));

		return usuario;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {

		this.passwordEncoder = passwordEncoder;
	}

	public void setUserDao(UsuarioDao usuarioDao) {

		this.usuarioDao = usuarioDao;
	}

	/**
	 * {@inheritDoc}
	 */
	public Usuario getUser(String userId) {

		return (Usuario) usuarioDao.getReference(Long.valueOf(userId));
	}

	/**
	 * {@inheritDoc}
	 */
	public List<PersistentObject> getUsers() {

		return usuarioDao.findAll();
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">563</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por salvar usuário
	 * 
	 * @author iago.almeida
	 * 
	 * @param user
	 * @return <Usuario>
	 */
	@Override
	public Usuario save(Usuario user) {

		montarObjeto(user);

		// VALIDA CAMPO OBRIGATÓRIO DA ENTIDADE
		if (UtilObjeto.isReferencia(this.validator)) {

			this.validarEntidade(user, this.validator);
		}

		// Formata string username (nome usuario)
		user.setUsername(user.getUsername().toLowerCase(new Locale("pt", "BR")));

		// Gera encode password
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		// Gera encode passwordMobile
		if (UtilObjeto.isReferencia(user.getPasswordMobile())) {

			user.setPasswordMobile(passwordEncoder.encode(user.getPasswordMobile()));
		}

		return usuarioDao.saveUser(user);
	}

	/**
	 * Método responsável por
	 * 
	 * @author wilker.machado
	 * 
	 * @param user
	 */
	private void montarObjeto(Usuario user) {

		Organizacao organizacaoUsuario = null;

		if (!UtilColecao.isVazio(user.getOrganizacoes())) {

			for (UsuarioOrganizacaoItem usuarioOrganizacaoItem : user.getOrganizacoes()) {

				usuarioOrganizacaoItem.setUsuario(user);

				usuarioOrganizacaoItem.setOrganizacao(organizacaoService.getReference(usuarioOrganizacaoItem.getOrganizacao().getId()));

				if (!UtilObjeto.isReferencia(organizacaoUsuario)) {

					organizacaoUsuario = usuarioOrganizacaoItem.getOrganizacao();
				}
			}
		}

		if (UtilObjeto.isReferencia(user.getOrganizacao()) && UtilObjeto.isReferencia(user.getOrganizacao().getId())) {

			user.setOrganizacao(organizacaoService.getReference(user.getOrganizacao().getId()));

		} else {

			user.setOrganizacao(organizacaoUsuario);

		}

		if (!UtilColecao.isVazio(user.getUsuarioPrivilegios())) {
			for (UsuarioPrivilegio usuarioPrivilegio : user.getUsuarioPrivilegios()) {

				usuarioPrivilegio.setUsuario(user);

			}
		}

		if (!UtilColecao.isVazio(user.getGrupoUsuarios())) {

			for (GrupoUsuario grupoUsuario : user.getGrupoUsuarios()) {

				grupoUsuario.setUsuario(user);

			}

		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeUser(Usuario user) {

		log.debug("removing user: " + user);
		usuarioDao.remove(user);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeUser(String userId) {

		log.debug("removing user: " + userId);
		usuarioDao.remove(Long.valueOf(userId), Usuario.class);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param username
	 *            the login name of the human
	 * @return User the populated user object
	 * @throws UsernameNotFoundException
	 *             thrown when username not found
	 */
	public Usuario getUserByUsername(String username) throws UsernameNotFoundException {

		return (Usuario) usuarioDao.loadUserByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario user = (Usuario) usuarioDao.loadUserByUsername(username);

		return user;

	}

	public Usuario loadUserByUsernamePassword(String username, String password) {

		Usuario usuario = (Usuario) usuarioDao.loadUserByUsername(username);

		System.out.println(passwordEncoder.encode(password));

		if (UtilObjeto.isReferencia(usuario)) {
			if (usuario.getTokenPasswordRecovery() != null && !usuario.getTokenPasswordRecovery().isEmpty()) {
				this.updateTokenUsuario(usuario.getId(), null);
			}

			// VERIFICA SE A SENHA DIGITADA BATE COM A SENHA DO USUÁRIO
			if (!passwordEncoder.matches(password, usuario.getPassword())) {
				return null;
			}

			return usuario;
		} else {
			return null;
		}

	}

	public void registerUser(Usuario user) {

		if (user.getPassword() == null) {
			String password = generatePassword();
			user.setPassword(password);
		}

		usuarioDao.save(user);
	}

	private static final String RANDOM_PASSWORD_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890-_!$*";

	private static final int RANDOM_PASSWORD_LENGTH = 12;

	private String generatePassword() {

		StringBuilder password = new StringBuilder();
		for (int i = 0; i < RANDOM_PASSWORD_LENGTH; i++) {
			int charIndex = (int) ( Math.random() * RANDOM_PASSWORD_CHARS.length() );
			char randomChar = RANDOM_PASSWORD_CHARS.charAt(charIndex);
			password.append(randomChar);
		}
		return password.toString();
	}

	// Remove favorito do Usuário
	public boolean removeFavorito(PaginaUsuario paginaUsuario) {

		paginaUsuario = (PaginaUsuario) paginaUsuarioDao.getReference(paginaUsuario.getId());

		Favorito favorito = paginaUsuario.getFavorito();

		paginaUsuario.setFavorito(null);

		paginaUsuarioDao.merge(paginaUsuario);

		return favoritoDao.removeById(favorito.getId());
	}

	// Remove filtro do Usuário
	public boolean removeFiltro(SearchParams searchParams) {

		return searchParamsDao.removeById(searchParams.getId());

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
	 * Método responsável por buscar usuário por nome
	 * 
	 * @author iago.almeida
	 * 
	 * @param username
	 * @return
	 */
	@Override
	public Usuario buscaUsuarioByUsername(String username) {

		return this.usuarioDao.buscaUsuarioByUsername(username.toLowerCase(new Locale("pt", "BR")));
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
	 * Método responsável por buscar usuário por email
	 * 
	 * @author iago.almeida
	 * 
	 * @param email
	 * @return
	 */
	@Override
	public Usuario buscaUsuarioByEmail(String email) {

		return this.usuarioDao.buscaUsuarioByEmail(email);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Usuario atualizarOrganizacaoUsuario(Organizacao organizacao) {

		Usuario usuario = this.find(( (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getId());

		usuario.setOrganizacao(this.organizacaoService.find(organizacao.getId()));

		( (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).setOrganizacao(usuario.getOrganizacao());

		return this.merge(usuario);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String setUserTokenPasswordRecovery(Usuario usuario) {

		SecureRandom random = new SecureRandom();
		String token = new BigInteger(130, random).toString(32);

		this.updateTokenUsuario(usuario.getId(), token);

		return token;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String requestRecoveryPasswordEmail(String email, String url) {

		Usuario usuario = this.buscaUsuarioByEmail(email);

		if (UtilObjeto.isReferencia(usuario)) {
			// VERIFICA SE A CONTA ESTA BLOQUEADA
			if (usuario.getContaBloqueada()) {
				return "Conta bloqueada!";
			}

			final String from = configuracaoService.getParametro("EMAIL_LOGIN");
			final String to = usuario.getEmail();

			Properties props = new Properties();

			props.put("mail.smtp.host", configuracaoService.getParametro("EMAIL_SMTP_HOST"));
			props.put("mail.smtp.socketFactory.port", configuracaoService.getParametro("EMAIL_SMTP_PORTA"));

			if (configuracaoService.getParametro("EMAIL_TSL_SSL").equalsIgnoreCase("true")) {
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", configuracaoService.getParametro("EMAIL_SMTP_PORTA"));
			}

			Session session;

			if (configuracaoService.getParametro("EMAIL_EXIGE_AUTENTICACAO").equalsIgnoreCase("true")) {
				final String username = configuracaoService.getParametro("EMAIL_LOGIN");
				final String password = configuracaoService.getParametro("EMAIL_SENHA");

				session = Session.getInstance(props, new Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {

						return new PasswordAuthentication(username, password);
					}
				});
			} else {
				session = Session.getDefaultInstance(props);
			}

			// session.setDebug(true);

			String token = this.setUserTokenPasswordRecovery(usuario);
			url += "/recoveryPassword?token=" + token;

			try {
				Message message = new MimeMessage(session);

				message.setFrom(new InternetAddress(from));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

				message.setSubject("Recuperação de senha");

				StringBuilder bodyMessage = new StringBuilder();

				bodyMessage.append("<p>Olá {0},</p>");

				bodyMessage.append("<p>");
				bodyMessage.append("Recentemente você solicitou a alteração de sua senha.<br />");
				bodyMessage.append("Para alterar a sua senha clique no link abaixo:<br />");
				bodyMessage.append("{1}");
				bodyMessage.append("</p>");

				bodyMessage.append("<p>Obs.: Caso não foi você basta ignorar.</p>");

				bodyMessage.append("<p>");
				bodyMessage.append("Att,<br />");
				bodyMessage.append("Administrado do sistema");
				bodyMessage.append("</p>");

				String body = MessageFormat.format(bodyMessage.toString(), usuario.getUsername(), url);

				message.setContent(body, "text/html");

				Transport.send(message);
			} catch (MessagingException e) {
				e.printStackTrace();
				return "Falha ao enviar o email!";
			}

			return "Enviamos um e-mail de recuperação de senha para você!";
		} else {
			return "Conta não existe!";
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean verifyIfTokenRecoveryPasswordExists(String token) {

		return usuarioDao.verifyIfTokenRecoveryPasswordExists(token);
	}

	/**
	 * {@inheritDoc}
	 */
	public Usuario getUserByToken(String token) {

		return usuarioDao.getUserByToken(token);
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateUserPassword(Usuario user, String password) {

		password = passwordEncoder.encode(password);

		usuarioDao.updateUserPassword(user.getId(), password);

		this.updateTokenUsuario(user.getId(), null);
	}

	// TODO REMOVER ESSE METODO APOS CORRIGIR USUARIO
	public void updateTokenUsuario(Long idUsuario, String token) {

		usuarioDao.updateTokenUsuario(idUsuario, token);
	}

	@Override
	public void atualizarOrganizacaoUsuario(Organizacao organizacao, Usuario usuario) {

		Usuario user = this.find(usuario.getId());

		user.setOrganizacao(organizacao);

		this.merge(user);
	}

	@Override
	public List<Usuario> findByUsername(String username) {

		return usuarioDao.findByUsername(username);
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
	 * Método responsável por validar se um usuário mobile é valido
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param usuario
	 * @return Boolean
	 */
	@Override
	public Boolean usuarioMobileValido(Usuario usuario) {

		try {

			Usuario user = this.loadUserByUsernamePasswordMobile(usuario.getUsername().toLowerCase(), usuario.getPasswordMobile());

			return UtilObjeto.isReferencia(user);

		} catch (Exception e) {

			return Boolean.FALSE;
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
	 * Método responsável por
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idGrupo
	 * @return
	 */
	public Long quantidadeUsarioPorGrupo(Long idGrupo) {

		Collection<GrupoUsuario> listaGrupoUsuario = this.grupoUsuarioService.findPorIdGrupo(idGrupo);

		return UtilColecao.isVazio(listaGrupoUsuario) ? 0L : listaGrupoUsuario.size();

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
	 * Método responsável por buscar e validar usuário mobile
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param username
	 * @param passwordMobile
	 * @return Usuario
	 */
	private Usuario loadUserByUsernamePasswordMobile(String username, String passwordMobile) {

		Usuario usuario = (Usuario) usuarioDao.loadUserByUsername(username);

		if (UtilObjeto.isReferencia(usuario)) {
			if (usuario.getTokenPasswordRecovery() != null && !usuario.getTokenPasswordRecovery().isEmpty()) {
				this.updateTokenUsuario(usuario.getId(), null);
			}

			// VERIFICA SE A SENHA DIGITADA BATE COM A SENHA DO USUÁRIO
			if (!passwordEncoder.matches(passwordMobile, usuario.getPasswordMobile())) {
				return null;
			}

			return usuario;
		} else {
			return null;
		}

	}
}
