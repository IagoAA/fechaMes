package br.com.centralit.api.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.InternacionalizacaoDao;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.InternacionalizacaoService;
import br.com.centralit.api.service.ModuloService;
import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.Internacionalizacao;
import br.com.centralit.framework.model.Modulo;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilObjeto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("internacionalizacaoService")
public class InternacionalizacaoServiceImpl extends GenericServiceImpl<Internacionalizacao, Long> implements InternacionalizacaoService {

	/** Atributo moduloService. */
	@Autowired
	private ModuloService moduloService;

	/** Atributo dominioService. */
	@Autowired
	private DominioService dominioService;

	/** Atributo servletContext. */
	@Autowired
	private ServletContext servletContext;

	/** Atributo internacionalizacaoDao. */
	private InternacionalizacaoDao internacionalizacaoDao;

	static final Logger LOG = Logger.getLogger(InternacionalizacaoServiceImpl.class);

	/**
	 * Responsavel pela criacao de novas instancias desta classe.
	 *
	 * @param internacionalizacaoDao
	 * @param validator
	 */
	@Autowired
	public InternacionalizacaoServiceImpl( InternacionalizacaoDao internacionalizacaoDao, @Qualifier("internacionalizacaoValidator") Validator validator ) {

		this.dao = internacionalizacaoDao;

		this.internacionalizacaoDao = internacionalizacaoDao;

		this.validator = validator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Internacionalizacao save(Internacionalizacao entity) {

		Internacionalizacao entidade = null;

		if (UtilObjeto.isReferencia(entity.getJsonPortal())) {

			entidade = this.salvarJsonInternacionalizacao(entity);

		} else {

			this.validarEntidade(entity, this.validator);

			validarRegras(entity);

			entidade = super.save(entity);

		}

		return entidade;

	}

	/**
	 * Metodo responsavel por
	 *
	 * @author wilker.machado
	 *
	 * @param entity
	 */
	private void validarRegras(Internacionalizacao entity) {

		if (this.internacionalizacaoDao.existeChave(entity)) {

			throw new BusinessException("VALIDACAO_CHAVE_REPETIDA", CodigoErro.REGRA_NEGOCIO.getValue());

		}

		if (this.internacionalizacaoDao.existeValor(entity)) {

			throw new BusinessException("VALIDACAO_VALOR_REPETIDO", CodigoErro.REGRA_NEGOCIO.getValue());
		}
	}

	/**
	 * Metodo responsavel por
	 *
	 * @author wilker.machado
	 *
	 * @param entity
	 * @return
	 */
	private Internacionalizacao salvarJsonInternacionalizacao(Internacionalizacao entity) {

		Set<String> chaves = entity.getJsonPortal().keySet();

		Internacionalizacao internacionalizacao = null;

		for (String chave : chaves) {

			internacionalizacao = new Internacionalizacao();

			internacionalizacao.setChave(chave);

			internacionalizacao.setValor(entity.getJsonPortal().get(chave));

			internacionalizacao.setModulo(entity.getModulo());

			internacionalizacao.setTipoDominioIdioma(entity.getTipoDominioIdioma());

			this.validarEntidade(internacionalizacao, this.validator);

			Internacionalizacao valorAntigo = (Internacionalizacao) internacionalizacaoDao.consultaInternacionalizacao(internacionalizacao);

			if(valorAntigo == null){
				super.save(internacionalizacao);

			} else if(!valorAntigo.getValor().equals(internacionalizacao.getValor())){
				internacionalizacao.setId(valorAntigo.getId());
				super.merge(internacionalizacao);

			}

		}

		return internacionalizacao;

	}

	@Override
	public List<Internacionalizacao> buscarPorModulo(Modulo moduloAtivo, Dominio idioma) {

		return this.internacionalizacaoDao.buscarPorModulo(moduloAtivo, idioma);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean atualizarArquivoPortalJson() {

		ObjectMapper mapper = new ObjectMapper();

		List<Dominio> idiomas = new ArrayList<Dominio>(this.dominioService.listarPorChave("tipoIdioma"));

		for (Dominio idioma : idiomas) {

			List<Internacionalizacao> result = recuperaInternacionalizacaoPorIdioma(idioma);

			Map<String, Object> internacionalizacaoMap = ConverteListaObjEmMapaParaJson(result);

			String path = servletContext.getRealPath("/assets/i18n/" + idioma.getNome() + "/portal.json");

			try {

				mapper.writeValue(new File(path), internacionalizacaoMap);

			} catch (JsonProcessingException e) {

				return false;

			} catch (IOException e) {

				return false;

			}

		}

		return true;

	}


	/**
	 * MMetodo responsavel portodo responsavel por
	 *
	 * @author wilker.machado
	 *
	 * @param idioma
	 * @return
	 */
	private List<Internacionalizacao> recuperaInternacionalizacaoPorIdioma(Dominio idioma) {

		return this.buscarPorModulo(null, idioma);

	}

	/**
	 * Metodo responsavel por
	 *
	 * @author wilker.machado
	 *
	 * @param result
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> ConverteListaObjEmMapaParaJson(List<Internacionalizacao> result) {

		Map<String, Object> internacionalizacaoMap = new HashMap<String, Object>();

		for (Internacionalizacao internacionalizacao : result) {

			String[] chaves = internacionalizacao.getChave().split("\\.");

			if (chaves.length == 1) {

				internacionalizacaoMap.put(chaves[0].trim(), internacionalizacao.getValor());

			} else if (chaves.length == 2) {

				if (internacionalizacaoMap.containsKey(chaves[0].trim())) {

					( (Map<String, Object>) internacionalizacaoMap.get(chaves[0].trim()) ).put(chaves[1], internacionalizacao.getValor());

				} else {

					Map<String, Object> valores = new HashMap<String, Object>();

					valores.put(chaves[1].trim(), internacionalizacao.getValor());

					internacionalizacaoMap.put(chaves[0].trim(), valores);

				}

			} else if (chaves.length == 3) {

				if (internacionalizacaoMap.containsKey(chaves[0].trim())) {

					if (( (Map<String, Object>) internacionalizacaoMap.get(chaves[0].trim()) ).containsKey(chaves[1].trim())) {

						( (Map<String, Object>) ( (Map<String, Object>) internacionalizacaoMap.get(chaves[0].trim()) ).get(chaves[1].trim()) ).put(chaves[2].trim(), internacionalizacao.getValor());

					} else {

						Map<String, Object> valores2 = new HashMap<String, Object>();

						valores2.put(chaves[2].trim(), internacionalizacao.getValor());

						( (Map<String, Object>) internacionalizacaoMap.get(chaves[0].trim()) ).put(chaves[1].trim(), valores2);
					}

				} else {

					Map<String, Object> valores = new HashMap<String, Object>();

					Map<String, Object> valores2 = new HashMap<String, Object>();

					valores2.put(chaves[2].trim(), internacionalizacao.getValor());

					valores.put(chaves[1].trim(), valores2);

					internacionalizacaoMap.put(chaves[0].trim(), valores);
				}
			}

		}

		return internacionalizacaoMap;
	}

	/**
	 * Método responsável por salvar as labels do arquivo portal.json no banco
	 *
	 * @author gilberto.nery
	 * @since 2015-09-02
	 */
	@Override
	public void salvarLabelsDoPortalJson() {

		List<Dominio> idiomas = new ArrayList<Dominio>(this.dominioService.listarPorChave("tipoIdioma"));

		this.salvarLabelsPortalWeb(idiomas);

	}

	/**
	 * Método responsável por
	 *
	 * @author wilker
	 *
	 * @param idiomas
	 */
	private void salvarLabelsPortalWeb(List<Dominio> idiomas) {

		for (Dominio idioma : idiomas) {

			String pathPortal = servletContext.getRealPath("/assets/i18n/" + idioma.getNome() + "/portal.json");

			File arquivoPortal = new File(pathPortal);

			this.realizarOperacoesParaSalvarLabels(arquivoPortal, null, idioma);
		}
	}

	/**
	 *
	 * Método responsável por
	 *
	 * @author wilker
	 *
	 * @param arquivoPortal
	 * @param idioma
	 * @param moduloAtivo
	 */
	private void realizarOperacoesParaSalvarLabels(File arquivoPortal, Modulo moduloAtivo, Dominio idioma) {

		if (arquivoPortal.exists()) {

			Map<String, Object> restMap = this.converterArquivoEmMapa(arquivoPortal);

			if(restMap == null){
				List<Internacionalizacao> result = recuperaInternacionalizacaoPorIdioma(idioma);
				restMap = ConverteListaObjEmMapaParaJson(result);
				LOG.info("As labels serão carregados do banco. O arquivo portal.json foi desconsiderado.");
			}

			Internacionalizacao internacionalizacao = new Internacionalizacao();

			internacionalizacao.setTipoDominioIdioma(idioma);

			internacionalizacao.setModulo(moduloAtivo);

			internacionalizacao.setJsonPortal(this.converterMapEmObjeto(restMap, ""));

			if (internacionalizacao.getJsonPortal().size() > 0) {

				this.save(internacionalizacao);
			}
		}
	}

	/**
	 *
	 * Método responsável por
	 *
	 * @author wilker
	 *
	 * @param restMap
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, String> converterMapEmObjeto(Map<String, Object> restMap, String str) {

		Map<String, String> map = new HashMap<String, String>();

		if(UtilObjeto.isReferencia(restMap)){

			for (String chavePrimaria : restMap.keySet()) {

				String inicioLabel;

				if (!str.trim().isEmpty()) {

					inicioLabel = str + "." + chavePrimaria;

				} else {

					inicioLabel = chavePrimaria;

				}
				if (restMap.get(chavePrimaria) instanceof String) {

					map.put(inicioLabel, (String) restMap.get(chavePrimaria));

				} else {

					map.putAll(converterMapEmObjeto((Map<String, Object>) restMap.get(chavePrimaria), inicioLabel));
				}
			}
		}

		return map;
	}

	/**
	 *
	 * Método responsável por
	 *
	 * @author wilker
	 *
	 * @param arquivoPortal
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> converterArquivoEmMapa(File arquivoPortal) {

		try {

			ObjectMapper mapper = new ObjectMapper();

			Map<String, Object> restMap = mapper.readValue(arquivoPortal, Map.class);

			return restMap;

		} catch (IOException e) {
			LOG.error("Problemas ao carregar internacionalização.");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTranslate(String chave, Dominio idioma) {
		return this.internacionalizacaoDao.getTranslate(chave, idioma);
	}

}
