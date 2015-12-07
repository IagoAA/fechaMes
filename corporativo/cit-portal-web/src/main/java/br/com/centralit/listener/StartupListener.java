package br.com.centralit.listener;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import br.com.centralit.api.service.ConfiguracaoParametroSistemaService;
import br.com.centralit.api.service.ConfiguracaoService;
import br.com.centralit.api.service.DefaultFileService;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.InternacionalizacaoService;
import br.com.centralit.api.service.MenuService;
import br.com.centralit.api.service.ModuloService;
import br.com.centralit.api.service.OrganizacaoService;
import br.com.centralit.api.service.PaginaService;
import br.com.centralit.api.service.PrivilegioService;
import br.com.centralit.api.service.UsuarioOrganizacaoItemService;
import br.com.centralit.api.service.UsuarioService;
import br.com.centralit.api.service.WidgetParametroService;
import br.com.centralit.api.service.WidgetService;
import br.com.centralit.api.util.ClientRest;
import br.com.centralit.framework.model.Configuracao;
import br.com.centralit.framework.model.ConfiguracaoParametroSistema;
import br.com.centralit.framework.model.Modulo;
import br.com.centralit.framework.model.Organizacao;
import br.com.centralit.framework.model.Privilegio;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.model.UsuarioOrganizacaoItem;
import br.com.centralit.framework.model.UsuarioPrivilegio;
import br.com.centralit.framework.util.UtilColecao;
import br.com.centralit.framework.util.UtilDate;
import br.com.centralit.framework.util.UtilObjeto;

@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent>, Ordered {

	/** Atributo WAR. */
	private static final String WAR = ".war.";

	/** Atributo FECHA_MES_WEB. */
	private static final String FECHA_MES_WEB = "fecha-mes-web";

	/** Atributo EXECUTAR_MODULO. */
	private final String EXECUTAR_BASE_INICIAL = "EXECUTAR_BASE_INICIAL";

	/** Atributo internacionalizacaoService. */
	@Autowired
	private InternacionalizacaoService internacionalizacaoService;

	/** Atributo dominioService. */
	@Autowired
	private DominioService dominioService;

	/** Atributo defaultFileService. */
	@Autowired
	private DefaultFileService defaultFileService;

	/** Atributo moduloService. */
	@Autowired
	private ModuloService moduloService;

	/** Atributo paginaService. */
	@Autowired
	private PaginaService paginaService;

	/** Atributo menuService. */
	@Autowired
	private MenuService menuService;

	/** Atributo privilegioService. */
	@Autowired
	private PrivilegioService privilegioService;

	/** Atributo usuarioService. */
	@Autowired
	private UsuarioService usuarioService;

	/** Atributo organizacaoService. */
	@Autowired
	private OrganizacaoService organizacaoService;

	/** Atributo usuarioOrganizacaoItemService. */
	@Autowired
	private UsuarioOrganizacaoItemService usuarioOrganizacaoItemService;

	/** Atributo configuracaoSistemaService. */
	@Autowired
	private ConfiguracaoService configuracaoService;

	/** Atributo configuracaoParametroSistemaService. */
	@Autowired
	private ConfiguracaoParametroSistemaService configuracaoParametroSistemaService;

	/** Atributo widgetService. */
	@Autowired
	private WidgetService widgetService;

	/** Atributo widgetParametroService. */
	@Autowired
	private WidgetParametroService widgetParametroService;

	/** Atributo LOG. */
	static final Logger LOG = Logger.getLogger(StartupListener.class);

	/** Atributo inicializarMenu. */
	@Autowired
	private InicializarMenu inicializarMenu;

	/** Atributo inicializarTabelasCorp. */
	@Autowired
	private InicializarTabelasCorp inicializarTabelasCorp;

	/** Atributo inicializarFechaMes. */
	@Autowired
	private InicializarFechaMes inicializarFechaMes;

	/** Atributo servletContext. */
	@Autowired
	private ServletContext servletContext;

	/** Atributo listaWAR. */
	private String listaWAR;

	/** Atributo isDiretorioStandalone. */
	private Boolean isDiretorioStandalone = true;

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {

		if (UtilColecao.isVazio(configuracaoParametroSistemaService.findAll())) {

			LOG.info("Executando as configura\u00e7\u00f5es do startup listener pela primeira vez... ");

			inicializarMenu.criarDominios();
			LOG.info("Dominios criados e salvos no banco");

			criarModulos(Boolean.FALSE);
			criarMenus();
			criarArquivosPadrao();

			internacionalizacaoService.salvarLabelsDoPortalJson();
			LOG.info("Arquivos internacionalizados do portal.json est\u00e3o salvos no banco");

			internacionalizacaoService.atualizarArquivoPortalJson();
			LOG.info("Arquivos internacionalizados do portal.json est\u00e3o atualizados com o banco");

			criarBaseInicial();
			LOG.info("Cadastros b\u00e1sicos populados");

		} else {

			LOG.info("Executando atualiza\u00e7\u00f5es das configura\u00e7\u00f5es do startup listener...");

			/*
			 * Removida a verificao para executar os itens comentados, todos serão executados ConfiguracaoParametroSistema executarScriptMenu = configuracaoParametroSistemaService.getParametro(EXECUTAR_SCRIPT_MENU, null); ConfiguracaoParametroSistema executarDominio =
			 * configuracaoParametroSistemaService.getParametro(EXECUTAR_DOMINIOS, null); ConfiguracaoParametroSistema executarDefaultFile = configuracaoParametroSistemaService.getParametro(EXECUTAR_DEFAULT_FILE, null); ConfiguracaoParametroSistema executarModulo =
			 * configuracaoParametroSistemaService.getParametro(EXECUTAR_MODULO, null); ConfiguracaoParametroSistema executarScriptInternacionalizacao = configuracaoParametroSistemaService.getParametro(EXECUTAR_INTERNACIONALIZACAO, null);
			 */

			ConfiguracaoParametroSistema executarBaseInicial = configuracaoParametroSistemaService.getParametro(EXECUTAR_BASE_INICIAL, null);

			internacionalizacaoService.salvarLabelsDoPortalJson();
			LOG.info("Arquivos internacionalizados do portal.json est\u00e3o salvos no banco");

			internacionalizacaoService.atualizarArquivoPortalJson();
			LOG.info("Arquivos internacionalizados do portal.json est\u00e3o atualizados com o banco");

			inicializarMenu.criarDominios();

			criarModulos(Boolean.TRUE);

			criarMenus();

			criarArquivosPadrao();

			if (!UtilObjeto.isReferencia(executarBaseInicial) || Boolean.valueOf(executarBaseInicial.getValor())) {

				criarBaseInicial();

				// Setar parametro para não executar base inicial
				executarBaseInicial.setValor("false");
				configuracaoParametroSistemaService.merge(executarBaseInicial);

			}
		}

		LOG.info("Startup listener PORTAL finalizado!");

	}

	/**
	 * Método responsável por
	 *
	 * @author wilker.machado
	 *
	 */
	private void criarModulos(Boolean executarParametro) {

		// Obtém a lista de war do diretório do JBoss
		this.obterWARJBoss();

		/**
		 *
		 * @author gilberto.nery
		 * @author rogerio.cassimiro
		 * @data 08/09/2015
		 *
		 */
		List<Modulo> modulos;

		if (executarParametro) {

			modulos = this.moduloService.findAll();

			String bareUrl = null;

			for (Modulo modulo : modulos) {

				bareUrl = modulo.getBaseUrl().replace("/", "");

				// Localizou war no diretório standalone
				if (isDiretorioStandalone && modulo.getHabilitado()) {

					modulo.setHabilitado(listaWAR.contains(bareUrl));

					this.moduloService.merge(modulo);
				}

				bareUrl = null;
			}

		} else {

			modulos = new ArrayList<Modulo>();
			modulos.add(new Modulo("Corporativo", "/cit-tabelas-corp-web", "RestangularTabelasCorp", "cit-tabelas-corp-api", Boolean.TRUE));
			modulos.add(new Modulo("Fecha Mês", "/fecha-mes-web", "RestangularFechaMes", "fecha-mes-api", listaWAR.contains(FECHA_MES_WEB)));

			this.moduloService.saveListIfNotExist(modulos);
		}

		if(isDiretorioStandalone) {
			LOG.info("Informa\u00e7\u00f5es dos m\u00f3dulos salvas no banco");
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
	 * Método responsável por obter a lista de *.war do diretório do JBoss
	 *
	 * @author rogerio.cassimiro
	 *
	 */
	private void obterWARJBoss() {

		listaWAR = new String();

		try {

			String pathJBoss = servletContext.getRealPath("");

			pathJBoss = pathJBoss.substring(0, pathJBoss.indexOf("standalone")) + "standalone" + System.getProperty("file.separator") + "deployments";

			Log.info("Obtendo os war do servidor!");

			File diretorioJBoss = new File(pathJBoss);

			String[] arquivos = diretorioJBoss.list();

			for (int i = 0; i < arquivos.length; i++) {
				if (!arquivos[i].contains(WAR)) {
					listaWAR = listaWAR.concat(arquivos[i]).concat(";");
				}
			}
		} catch (Exception e) {
			isDiretorioStandalone = Boolean.FALSE;
			e.printStackTrace();
			LOG.info("Erro ao obter os arquivos war do servidor! Estrutura de diret\u00f3rios do JBoss diferente de standalone");
		}

	}

	/**
	 * Ordem Para subir contexto
	 */
	@Override
	public int getOrder() {

		return HIGHEST_PRECEDENCE;
	}

	private void criarBaseInicial() {

		try {

			Organizacao organizacao = new Organizacao();
			organizacao.setDataCriacao(Calendar.getInstance());
			organizacao.setDataEdicao(Calendar.getInstance());
			organizacao.setDataInicio(Calendar.getInstance());
			organizacao.setVersion(0l);
			organizacao.setDataReferenciaVigente(UtilDate.getMesAtualComPrimeiroDiaHoraZerada());
			organizacao.setDataInicio(Calendar.getInstance());
			organizacao.setCodigo("1");
			organizacao.setNome("BR MALLS 1");
			organizacao.setSigla("BR MALLS");
			organizacao = organizacaoService.saveIfNotExist(organizacao);

			Privilegio privilegioAdmin = new Privilegio("ROLE_ADMIN", "ROLE_ADMIN");
			privilegioAdmin = privilegioService.saveIfNotExist(privilegioAdmin);

			Privilegio privilegioUser = new Privilegio("ROLE_USER", "ROLE_USER");
			privilegioUser = privilegioService.saveIfNotExist(privilegioUser);

			Usuario usuario = new Usuario();

			Set<UsuarioPrivilegio> usuarioPrivilegios = new LinkedHashSet<UsuarioPrivilegio>();
			usuarioPrivilegios.add(new UsuarioPrivilegio(usuario, privilegioAdmin));
			usuarioPrivilegios.add(new UsuarioPrivilegio(usuario, privilegioUser));

			usuario.setDataCriacao(Calendar.getInstance());
			usuario.setDataEdicao(Calendar.getInstance());
			usuario.setVersion(0l);
			usuario.setContaBloqueada(false);
			usuario.setContaExpirada(false);
			usuario.setContaHabilitada(true);
			usuario.setCredencialExpirada(false);
			usuario.setEmail("admin@email.com");
			usuario.setPassword("$2a$10$/mCLiZIxHplKbJBQOt2Hhu6q/1xvxsKNh3AwBAO0JkLs2wjU8jIyq");
			usuario.setSempreNovaAba(true);
			usuario.setUsername("admin");
			usuario.setOrganizacao(organizacao);
			usuario.setUsuarioPrivilegios(usuarioPrivilegios);
			usuario = usuarioService.saveIfNotExist(usuario);

			UsuarioOrganizacaoItem usuarioOrganizacaoItem = new UsuarioOrganizacaoItem(organizacao, usuario);
			usuarioOrganizacaoItem.setDataCriacao(Calendar.getInstance());
			usuarioOrganizacaoItem.setDataEdicao(Calendar.getInstance());
			usuarioOrganizacaoItem.setVersion(0l);
			usuarioOrganizacaoItem = usuarioOrganizacaoItemService.saveIfNotExist(usuarioOrganizacaoItem);

			Configuracao configuracao = new Configuracao();
			configuracao.setDataCriacao(Calendar.getInstance());
			configuracao.setDataEdicao(Calendar.getInstance());
			configuracao.setVersion(0l);
			configuracao.setOrganizacao(organizacao);

			List<ConfiguracaoParametroSistema> parametros = new ArrayList<ConfiguracaoParametroSistema>();
			parametros.add(new ConfiguracaoParametroSistema("EXECUTAR_BASE_INICIAL", "false", configuracao));
			parametros.add(new ConfiguracaoParametroSistema("EXECUTAR_SCRIPT_MENU", "false", configuracao));
			parametros.add(new ConfiguracaoParametroSistema("EXECUTAR_DOMINIOS", "false", configuracao));
			parametros.add(new ConfiguracaoParametroSistema("EXECUTAR_DEFAULT_FILE", "false", configuracao));
			parametros.add(new ConfiguracaoParametroSistema("EXECUTAR_MODULO", "false", configuracao));
			parametros.add(new ConfiguracaoParametroSistema("EXECUTAR_INTERNACIONALIZACAO", "true", configuracao));

			parametros.add(new ConfiguracaoParametroSistema("EXECUTAR_SCRIPT_MENU-1", "false", configuracao));
			parametros.add(new ConfiguracaoParametroSistema("EXECUTAR_SCRIPT_MENU-2", "false", configuracao));
			parametros.add(new ConfiguracaoParametroSistema("EXECUTAR_SCRIPT_MENU-3", "false", configuracao));
			parametros.add(new ConfiguracaoParametroSistema("EXECUTAR_SCRIPT_MENU-4", "false", configuracao));
			parametros.add(new ConfiguracaoParametroSistema("EXECUTAR_SCRIPT_MENU-5", "false", configuracao));
			parametros.add(new ConfiguracaoParametroSistema("EXECUTAR_SCRIPT_MENU-6", "true", configuracao));
			parametros.add(new ConfiguracaoParametroSistema("EXECUTAR_SCRIPT_MENU-7", "false", configuracao));

			parametros.add(new ConfiguracaoParametroSistema("RELATORIO_PRIMEIRO_TITULO", "Defina o Primeiro t\u00edtulo", configuracao));
			parametros.add(new ConfiguracaoParametroSistema("RELATORIO_SEGUNDO_TITULO", "Defina o Segundo t\u00edtulo", configuracao));
			parametros.add(new ConfiguracaoParametroSistema("RELATORIO_TERCEIRO_TITULO", "Defina o Terceiro t\u00edtulo", configuracao));
			parametros.add(new ConfiguracaoParametroSistema("MASCARA_NUMERO_IDENTIFICACAO", "AAAA999999", configuracao));

			configuracao.setParametros(parametros);
			configuracao = configuracaoService.saveIfNotExist(configuracao);

			LOG.info("Base inicial salva no banco");

		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("Erro ao salvar base inicial no banco");
		}
	}

	private void criarMenus() {

		inicializarMenu.criarPaginasMenus();
		LOG.info("Menu do portal salvo no banco");

		Modulo modulo = this.moduloService.moduloEstaAtivo(ClientRest.MODULO_TABELAS_CORP);

		if (UtilObjeto.isReferencia(modulo)) {
			inicializarTabelasCorp.criarPaginasMenus(modulo);
			LOG.info("Menu do tabelas corp salvo no banco");
		}

		modulo = this.moduloService.moduloEstaAtivo(ClientRest.MODULO_FECHA_MES);

		if (UtilObjeto.isReferencia(modulo)) {
			inicializarFechaMes.criarPaginasMenus(modulo);
			LOG.info("Menu do modulo fecha mês salvo no banco");
		}
	}

	private void criarArquivosPadrao() {

		inicializarMenu.criarArquivosPadrao();
		LOG.info("Arquivos padr\u00e3o JS e CSS do portal salvos no banco");

		Modulo modulo = this.moduloService.moduloEstaAtivo(ClientRest.MODULO_TABELAS_CORP);
		if (UtilObjeto.isReferencia(modulo)) {
			inicializarTabelasCorp.criarArquivosPadrao();
			LOG.info("Arquivos padr\u00e3o JS e CSS do tabelas corp salvo no banco");
		}

		modulo = this.moduloService.moduloEstaAtivo(ClientRest.MODULO_FECHA_MES);
		if (UtilObjeto.isReferencia(modulo)) {
			inicializarFechaMes.criarArquivosPadrao();
			LOG.info("Arquivos padr\u00e3o JS e CSS do modulo fecha mês salvo no banco");

		}
	}

}
