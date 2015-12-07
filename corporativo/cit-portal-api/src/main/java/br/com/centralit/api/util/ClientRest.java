package br.com.centralit.api.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import br.com.centralit.framework.model.Modulo;
import br.com.centralit.framework.util.UtilObjeto;

public class ClientRest {

	private String host = "${authentication.url}";

	private int port = 8443;

	private String applicationPath;

	private String apiPath = "/rest";

	private String scheme;

	private String metodo;

	private Map<String, String> params;

	private HttpServletRequest request;

	private Modulo modulo;

	private RestTemplate restTemplate = new RestTemplate();

	public static final String MODULO_TABELAS_CORP = "/cit-tabelas-corp-web";

	public static final String MODULO_FECHA_MES = "/fecha-mes-web";

	private static final Logger LOG = Logger.getLogger(ClientRest.class);

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public ClientRest() {

		super();
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param request
	 * @param modulo
	 */
	public ClientRest( HttpServletRequest request, Modulo modulo ) {

		super();
		this.modulo = modulo;
		this.setRequest(request);
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param params
	 * @param request
	 * @param modulo
	 */
	public ClientRest( Map<String, String> params, HttpServletRequest request, Modulo modulo ) {

		this(request, modulo);
		this.params = params;
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param metodo
	 * @param params
	 * @param request
	 * @param modulo
	 */
	public ClientRest( String metodo, Map<String, String> params, HttpServletRequest request, Modulo modulo ) {

		this(params, request, modulo);

		this.metodo = metodo;
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param host
	 * @param port
	 * @param applicationPath
	 * @param apiPath
	 * @param scheme
	 * @param metodo
	 * @param params
	 * @param request
	 * @param modulo
	 */
	public ClientRest( String host, int port, String applicationPath, String apiPath, String scheme, String metodo, Map<String, String> params, HttpServletRequest request, Modulo modulo ) {

		this(params, request, modulo);
		this.host = host;
		this.port = port;
		this.applicationPath = applicationPath;
		this.apiPath = apiPath;
		this.scheme = scheme;
		this.metodo = metodo;
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param scheme
	 * @param host
	 * @param port
	 * @param modulo
	 */
	public ClientRest( String scheme, String host, int port, Modulo modulo ) {

		this.scheme = scheme;

		this.host = host;

		this.port = port;

		this.applicationPath = modulo.getBaseUrl();

		this.setModulo(modulo);

	}

	/**
	 * Método responsável por validar regras presentes em outros modulos
	 *
	 * @author wilker.machado
	 *
	 * @param metodo
	 *
	 * @return {@link Boolean}
	 */
	public Boolean validarRegra(String metodo) {

		RestTemplate restTemplate = new RestTemplate();

		try {

			return restTemplate.getForObject(this.getUrl(metodo), Boolean.class, this.getParams());

		} catch (Exception ex) {

			ClientRest.LOG.error(ex.getMessage());

			return Boolean.TRUE;
		}
	}

	/**
	 * Método responsável por acessar via metodo get algum serviço rest
	 *
	 * @author wilker.machado
	 *
	 * @param metodo
	 *
	 * @return {@link Boolean}
	 */
	public Boolean acessoGet(String metodo) {

		RestTemplate restTemplate = new RestTemplate();

		try {

			return restTemplate.getForObject(this.getUrl(metodo), Boolean.class, this.getParams());

		} catch (Exception ex) {

			ClientRest.LOG.error(ex.getMessage());

			return Boolean.FALSE;
		}
	}

	/**
	 * Método responsável por executar um método em outro módulo e recuperar a resposta do mesmo no formato Object
	 *
	 * @param metodo
	 * @return
	 */
	public Object getResponse(String metodo) {

		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.getForObject(this.getUrl(metodo), Object.class, this.getParams());
	}

	/**
	 * Método responsável por executar um método em outro módulo e recuperar a resposta do mesmo em uma determinada classe.
	 *
	 * @param <T>
	 *
	 * @param metodo
	 * @return
	 */
	public <T> T getResponse(String metodo, Class<T> classe) {

		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.getForObject(this.getUrl(metodo), classe, this.getParams());
	}

	/**
	 * Método responsável por
	 *
	 * @author wilker.machado
	 *
	 * @param metodo
	 * @return
	 */
	private String getUrl(String metodo) {

		return this.getScheme() + "://" + this.getHost() + ":" + this.getPort() + this.getModulo().getBaseUrl() + this.getApiPath() + "/" + metodo + this.concatParamsGet();
	}

	/**
	 * Método responsável por
	 *
	 * @author wilker.machado
	 *
	 * @return
	 */
	private String concatParamsGet() {

		StringBuilder strParam = new StringBuilder();

		strParam.append("?");

		for (String chave : this.getParams().keySet()) {

			strParam.append(chave);

			strParam.append("=");

			strParam.append(this.getParams().get(chave));

			strParam.append("&");
		}

		strParam.deleteCharAt(strParam.length() - 1);

		return strParam.toString();
	}

	/**
	 * Retorna o valor do atributo <code>host</code>
	 *
	 * @return <code>String</code>
	 */
	public String getHost() {

		return host;
	}

	/**
	 * Define o valor do atributo <code>host</code>.
	 *
	 * @param host
	 */
	public void setHost(String host) {

		this.host = host;
	}

	/**
	 * Retorna o valor do atributo <code>port</code>
	 *
	 * @return <code>int</code>
	 */
	public int getPort() {

		return port;
	}

	/**
	 * Define o valor do atributo <code>port</code>.
	 *
	 * @param port
	 */
	public void setPort(int port) {

		this.port = port;
	}

	/**
	 * Retorna o valor do atributo <code>scheme</code>
	 *
	 * @return <code>String</code>
	 */
	public String getScheme() {

		return scheme;
	}

	/**
	 * Define o valor do atributo <code>scheme</code>.
	 *
	 * @param scheme
	 */
	public void setScheme(String scheme) {

		this.scheme = scheme;
	}

	/**
	 * Retorna o valor do atributo <code>applicationPath</code>
	 *
	 * @return <code>String</code>
	 */
	public String getApplicationPath() {

		return applicationPath;
	}

	/**
	 * Define o valor do atributo <code>applicationPath</code>.
	 *
	 * @param applicationPath
	 */
	public void setApplicationPath(String applicationPath) {

		this.applicationPath = applicationPath;
	}

	/**
	 * Retorna o valor do atributo <code>apiPath</code>
	 *
	 * @return <code>String</code>
	 */
	public String getApiPath() {

		return apiPath;
	}

	/**
	 * Define o valor do atributo <code>apiPath</code>.
	 *
	 * @param apiPath
	 */
	public void setApiPath(String apiPath) {

		this.apiPath = apiPath;
	}

	/**
	 * Retorna o valor do atributo <code>metodo</code>
	 *
	 * @return <code>String</code>
	 */
	public String getMetodo() {

		return metodo;
	}

	/**
	 * Define o valor do atributo <code>metodo</code>.
	 *
	 * @param metodo
	 */
	public void setMetodo(String metodo) {

		this.metodo = metodo;
	}

	/**
	 * Retorna o valor do atributo <code>params</code>
	 *
	 * @return <code>Map<String,String></code>
	 */
	public Map<String, String> getParams() {

		if (!UtilObjeto.isReferencia(this.params)) {

			this.params = new HashMap<String, String>();

		}

		return params;
	}

	/**
	 * Método responsável por
	 *
	 * @author wilker.machado
	 *
	 * @param chave
	 * @param valor
	 */
	public void addParametro(String chave, String valor) {

		if (this.getParams().containsKey(chave)) {

			this.getParams().remove(chave);

		}

		this.getParams().put(chave, valor);

	}

	/**
	 * Define o valor do atributo <code>params</code>.
	 *
	 * @param params
	 */
	public void setParams(Map<String, String> params) {

		this.params = params;
	}

	/**
	 * Retorna o valor do atributo <code>request</code>
	 *
	 * @return <code>HttpServletRequest</code>
	 */
	public HttpServletRequest getRequest() {

		return request;
	}

	/**
	 * Retorna o valor do atributo <code>modulo</code>
	 *
	 * @return <code>Modulo</code>
	 */
	public Modulo getModulo() {

		return modulo;
	}

	/**
	 * Define o valor do atributo <code>modulo</code>.
	 *
	 * @param modulo
	 */
	public void setModulo(Modulo modulo) {

		this.modulo = modulo;
	}

	/**
	 * Define o valor do atributo <code>request</code>.
	 *
	 * @param request
	 */
	public void setRequest(HttpServletRequest request) {

		try {

			this.scheme = request.getScheme();

			this.host = request.getServerName();

			this.port = request.getServerPort();

			this.applicationPath = this.getModulo().getBaseUrl();

		} catch (Exception ex) {

			this.scheme = "https";

		}

		this.request = request;
	}

	/**
	 * Retorna o valor do atributo <code>restTemplate</code>
	 *
	 * @return <code>RestTemplate</code>
	 */
	public RestTemplate getRestTemplate() {

		return restTemplate;
	}

	/**
	 * Define o valor do atributo <code>restTemplate</code>.
	 *
	 * @param restTemplate
	 */
	public void setRestTemplate(RestTemplate restTemplate) {

		this.restTemplate = restTemplate;
	}

}
