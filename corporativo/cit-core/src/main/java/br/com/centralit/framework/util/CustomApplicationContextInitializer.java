package br.com.centralit.framework.util;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;

/**
 * <p>
 * <img src="http://centralit.com.br/images/logo_central.png">
 * </p>
 *
 * <p>
 * <b>Company: </b> Central IT - Governan&ccedil;a Corporativa -
 * </p>
 *
 * <p>
 * <b>Description: </b>
 * </p>
 * Personaliza a inicializa&ccedil;&atilde;o do contexto da aplica&ccedil;&atilde;o permitindo carregar o arquivo properties definido na
 * pasta /config/cit-app.properties antes de todos os outros properties sources do spring. Desta forma todos os statements {@code <import>}
 * no {@code aplication-context.xml} podem ser resolvidos antes dos beans.
 *
 * @since 25/06/2015
 * @version 1.0.0
 * @author ciro.junior (<a href="mailto:ciro.junior@centralit.com.br">ciro.junior@centralit.com.br</a>)
 *
 */
public class CustomApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	private static Logger LOG = Logger.getLogger(CustomApplicationContextInitializer.class);

	private final static String PROPERTIES_FILE_LOAD_ORDER_FIRST = "FIRST";

	private final static String PROPERTIES_FILE_LOAD_ORDER_LAST = "LAST";

	private final static String PROPERTIES_FILE_LOAD_ORDER_DEFAULT = PROPERTIES_FILE_LOAD_ORDER_FIRST;

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {

		ConfigurableEnvironment environment = applicationContext.getEnvironment();
		String propertiesFilePath = environment.getProperty("propertiesFile");
		String propertiesFileLoadOrder = getPropertiesFileLoadOrderFrom(environment);

		try {
			MutablePropertySources propertySources = environment.getPropertySources();
			if (propertiesFileLoadOrder.equalsIgnoreCase(PROPERTIES_FILE_LOAD_ORDER_LAST)) {
				propertySources.addLast(new ResourcePropertySource(propertiesFilePath));
			} else {
				propertySources.addFirst(new ResourcePropertySource(propertiesFilePath));
			}
			LOG.info(String.format("========== Caminho do properties:'%s' carregado com a ordem: '%s' ==========", propertiesFilePath,
					propertiesFileLoadOrder));
		} catch (IOException ioException) {
			LOG.info(String.format(
					"========== N\u00e3o consegui carregar o arquivo: '%s' com a ordem: '%s' no ApplicationContext da aplica\u00e7\u00e3o",
					propertiesFilePath, propertiesFileLoadOrder));
		}
	}

	/**
	 *
	 * M&eacute;todo respons&aacute;vel por carregar as configura&ccedil;&otilde;es de ordem de carregamento do property source definido no
	 * web.xml.
	 *
	 * @author ciro.junior (<a href="mailto:ciro.junior@centralit.com.br">ciro.junior@centralit.com.br</a>)
	 *
	 * @param environment
	 * @return
	 */
	private String getPropertiesFileLoadOrderFrom(final ConfigurableEnvironment environment) {

		String propertiesFileLoadOrder = environment.getProperty("propertiesFileLoadOrder");
		if (propertiesFileLoadOrder == null || propertiesFileLoadOrder.isEmpty()) {
			return PROPERTIES_FILE_LOAD_ORDER_DEFAULT;
		}
		if (!propertiesFileLoadOrder.equalsIgnoreCase(PROPERTIES_FILE_LOAD_ORDER_FIRST)
				&& !propertiesFileLoadOrder.equalsIgnoreCase(PROPERTIES_FILE_LOAD_ORDER_LAST)) {
			return PROPERTIES_FILE_LOAD_ORDER_DEFAULT;
		}
		return propertiesFileLoadOrder;
	}
}
