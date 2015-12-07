package br.com.centralit.api.service.impl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import br.com.centralit.api.service.InformacaoSistemaService;
import br.com.centralit.framework.model.InformacaoSistema;
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
 * <b>Title: InformacaoSistemaServiceImpl</b>
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
 * @since 06/08/2015 - 11:00
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *
 */
@Service("InformacaoSistemaService")
public class InformacaoSistemaServiceImpl extends GenericServiceImpl<InformacaoSistema, Long> implements InformacaoSistemaService {

	/** Atributo LOG para classe InformacaoSistemaServiceImpl. */
	static final Logger LOG = Logger.getLogger(InformacaoSistemaServiceImpl.class);

	/**
	 * Método responsável por obter informacoes do sistema do sistema
	 *
	 * @author iago.almeida
	 *
	 * @return InformacaoSistema
	 */
	public InformacaoSistema getInformacaoSistema() {

		InformacaoSistema informacaoSistema = new InformacaoSistema();

		ClassPathXmlApplicationContext appContext = null;
		try {

			appContext = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");

			Resource[] resource = appContext.getResources("classpath*:/**.tag");
			// Verifica se foi encontrado com a extensao .tag no portal
			if (resource.length > 0) {

				String versao = resource[0].getFilename();

				// Remove a extensao do arquivo de versao para apresentar na tela
				versao = versao.replace(".tag", "");

				informacaoSistema.setVersao(versao);
			}

			return informacaoSistema;

		} catch (IOException e) {
			LOG.error("N\u00e3o foi poss\u00edvel encontrar arquivo com a vers\u00e3o do sistema. Verifique a exist\u00eancia do arquivo na "
					+ "extens\u00e3o '.tag'" + e.getCause());
		} finally {
			if(appContext != null){
				appContext.close();
			}
		}

		return informacaoSistema;

	}
}
