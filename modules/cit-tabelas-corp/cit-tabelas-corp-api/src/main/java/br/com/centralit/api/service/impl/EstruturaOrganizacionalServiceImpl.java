package br.com.centralit.api.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.EstruturaOrganizacionalDao;
import br.com.centralit.api.model.EstruturaOrganizacional;
import br.com.centralit.api.model.EstruturaOrganizacionalResponsavel;
import br.com.centralit.api.model.MapaOrganizacional;
import br.com.centralit.api.service.ColaboradorService;
import br.com.centralit.api.service.ConfiguracaoParametroSistemaService;
import br.com.centralit.api.service.ConfiguracaoService;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.EstruturaOrganizacionalResponsavelService;
import br.com.centralit.api.service.EstruturaOrganizacionalService;
import br.com.centralit.api.service.LocalizacaoService;
import br.com.centralit.api.service.MapaOrganizacionalService;
import br.com.centralit.api.service.ModuloService;
import br.com.centralit.api.service.UsuarioService;
import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilColecao;
import br.com.centralit.framework.util.UtilDate;
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
 * <b>Title: EstruturaOrganizacionalServiceImpl</b>
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
 * @since 10/12/2014 - 15:43:44
 *
 * @version 1.0.0
 *
 * @author renato.jesus
 *
 */
@Service("estruturaOrganizacionalService")
public class EstruturaOrganizacionalServiceImpl extends GenericServiceImpl<EstruturaOrganizacional, Long> implements EstruturaOrganizacionalService {

	/** Atributo estruturaOrganizacionalDao. */
	private EstruturaOrganizacionalDao estruturaOrganizacionalDao;

	/** Atributo localizacaoService. */
	@Autowired
	private LocalizacaoService localizacaoService;

	@Autowired
	private EstruturaOrganizacionalResponsavelService estruturaOrganizacionalResponsavelService;

	/** Atributo mapaOrganizacionalService. */
	@Autowired
	private MapaOrganizacionalService mapaOrganizacionalService;

	/** Atributo configuracaoService. */
	@Autowired
	private ConfiguracaoService configuracaoService;

	@Autowired
	private ConfiguracaoParametroSistemaService configuracaoParametroSistemaService;

	/** Atributo colaboradorService. */
	@Autowired
	private ColaboradorService colaboradorService;

	/** Atributo moduloService. */
	@Autowired
	private ModuloService moduloService;

	/** Atributo request. */
	@Autowired
	private HttpServletRequest request;

	/** Atributo usuarioService. */
	@Autowired
	private UsuarioService usuarioService;

	/** Atributo dominioService. */
	@Autowired
	private DominioService dominioService;

	/** Atributo ERRO_EXCLUSAO_ESTRUTURA. */
	private static final String ERRO_EXCLUSAO_ESTRUTURA = "VALIDACAO.ERRO_EXCLUSAO_ESTRUTURA";

	/** Atributo isValidarEntidades. */
	private boolean isValidarEntidades;

	@Autowired
	public EstruturaOrganizacionalServiceImpl( EstruturaOrganizacionalDao estruturaOrganizacionalDao, @Qualifier("estruturaOrganizacionalValidator") Validator validator ) {

		this.estruturaOrganizacionalDao = estruturaOrganizacionalDao;

		this.dao = estruturaOrganizacionalDao;

		this.validator = validator;
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
	 * Método responsável por salva a estrutura organizacional
	 *
	 * @author renato.jesus
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param estruturaOrganizacionalPai
	 */
	@Override
	public EstruturaOrganizacional save(EstruturaOrganizacional estruturaOrganizacional) {

		// VALIDA SE A DATA INÍCIO É MAIOR QUE A DATA ATIVA DO MAPA ORGANIZACIONAL
		this.validarDataInicioMapaVigente(estruturaOrganizacional.getDataInicio());

		// RESOLVE O TRANSIENT
		this.montarTransients(estruturaOrganizacional);

		// VALIDA DATA FIM PARA NÓ PAI E NÓ FILHO
		this.validarDataFim(estruturaOrganizacional);

		return saveEstruturaFilha(estruturaOrganizacional);
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por validar se a data início da estrutura é maior ou igual a data de ativação do mapa organizacional atual
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param dataInicio
	 */
	private void validarDataInicioMapaVigente(Calendar dataInicio) {

		MapaOrganizacional mapaOrganizacionalAtivo = this.mapaOrganizacionalService.findMapaAtivo();

		if(UtilObjeto.isReferencia(mapaOrganizacionalAtivo) && UtilObjeto.isReferencia(mapaOrganizacionalAtivo.getId())) {

			if( UtilDate.isPrimeiraDataMenorSegundaData(dataInicio, mapaOrganizacionalAtivo.getDataInicio()) ) {

				throw new BusinessException("VALIDACAO.DATA_INICIO_MAPA_VIGENTE", CodigoErro.REGRA_NEGOCIO.getValue(), " "+ UtilDate.getDataSemHorasString(mapaOrganizacionalAtivo.getDataInicio().getTime()));
			}

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
	 * Método responsável por montar transients da estrutura
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param estruturaOrganizacional
	 */
	private void montarTransients(EstruturaOrganizacional estruturaOrganizacional) {

		// RESOLVE O TRANSIENT DE LOCALIZAÇÃO
		if (estruturaOrganizacional.getLocalizacao() != null && estruturaOrganizacional.getLocalizacao().getId() != null) {

			estruturaOrganizacional.setLocalizacao(localizacaoService.find(estruturaOrganizacional.getLocalizacao().getId()));
		}

		// VERIFICA SE EXISTE RESPONSAVEIS PELA ESTRUTURA
		if (estruturaOrganizacional.getEstruturasOrganizacionalResponsaveis() != null && !estruturaOrganizacional.getEstruturasOrganizacionalResponsaveis().isEmpty()) {

			// PERCORRER LISTA DE RESPONSAVEIS E DEFINE QUAIS AS ESTRURAS QUE ELE É RESPONSAVEL
			for (EstruturaOrganizacionalResponsavel estruturaOrganizacionalResponsavel : estruturaOrganizacional.getEstruturasOrganizacionalResponsaveis()) {

				estruturaOrganizacionalResponsavel.setEstruturaOrganizacional(estruturaOrganizacional);
			}
		}

		// Obtém o parent da estrutura selecionada caso seja filha
		estruturaOrganizacional.setEstruturaOrganizacionalParent(UtilObjeto.isReferencia(estruturaOrganizacional.getEstruturaOrganizacionalParent()) ? (EstruturaOrganizacional) this.estruturaOrganizacionalDao.getReference(estruturaOrganizacional.getEstruturaOrganizacionalParent().getId()) : null);

		// DEFINE A SIGLA COMO UPPERCASE
		estruturaOrganizacional.setSigla(estruturaOrganizacional.getSigla().toUpperCase(new Locale("pt", "BR")));
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
	 * Método responsável por salvar uma estrutura organizacional filha
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param estruturaOrganizacionalFilha
	 * @return EstruturaOrganizacional
	 */
	private EstruturaOrganizacional saveEstruturaFilha(EstruturaOrganizacional estruturaOrganizacionalFilha) {

		// Salva estrutura organizacional filha
		estruturaOrganizacionalFilha = (EstruturaOrganizacional) this.estruturaOrganizacionalDao.save(estruturaOrganizacionalFilha);

		// Gerar código da estrutura organizacional filha
		this.gerarCodigoEstrutura(estruturaOrganizacionalFilha);

		return estruturaOrganizacionalFilha;
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
	 * Método responsável por gerar código para estrutura organizacional
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param estruturaOrganizacional
	 */
	private void gerarCodigoEstrutura(EstruturaOrganizacional estruturaOrganizacional) {

		// Gera código se não houver código para estrutura
		if (!UtilObjeto.isReferencia(estruturaOrganizacional.getCodigo())) {

			estruturaOrganizacional.setCodigo(Long.valueOf(estruturaOrganizacional.getId().toString().hashCode()).toString());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeById(Long id) {

		EstruturaOrganizacional estruturaOrganizacional = this.find(id);

		return remove(estruturaOrganizacional);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean remove(EstruturaOrganizacional estruturaOrganizacional) {

		// Valida se a entidade está vinculada a alguma outra entidade - Regra remoção
		this.contemEntidadeVinculadaRemocao(estruturaOrganizacional);

		// Valida se existe nó filho
		if (this.estruturaOrganizacionalDao.existeVinculo("estruturaOrganizacionalParent.id", estruturaOrganizacional.getId())) {

			// Monta a árvore do nó selecionado até o último filho
			this.montarTreePorEstruturaSelecionadaRecursivaRegraExclusao(estruturaOrganizacional, Boolean.FALSE);

		}

		return super.remove(estruturaOrganizacional);
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
	 * Método responsável por montar estruturas filhas recursivamente a partir de um nó pai selecionado validando para cada estrutura se existe vínculo. Se existir vínculo não pode excluir nenhum registro - Regra exclusão
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param estruturaOrganizacionalSelecionadaPai
	 * @param isNoFilho
	 */
	private void montarTreePorEstruturaSelecionadaRecursivaRegraExclusao(EstruturaOrganizacional estruturaOrganizacionalSelecionadaPai, Boolean isNoFilho) {

		// Valida entidade em uso para regra de remoção para cada estrutura da árvore a partir do nó selecionado - Apenas os filhos
		if (isNoFilho) {

			this.contemEntidadeVinculadaRemocao(estruturaOrganizacionalSelecionadaPai);
		}

		estruturaOrganizacionalSelecionadaPai.setSubEstruturasOrganizacionais((ArrayList<EstruturaOrganizacional>) this.estruturaOrganizacionalDao.findChildrens(estruturaOrganizacionalSelecionadaPai.getId(), false));

		for (EstruturaOrganizacional estruturaOrganizacionalFilha : estruturaOrganizacionalSelecionadaPai.getSubEstruturasOrganizacionais()) {

			super.remove(estruturaOrganizacionalFilha);

			estruturaOrganizacionalFilha.setSubEstruturasOrganizacionais((ArrayList<EstruturaOrganizacional>) this.estruturaOrganizacionalDao.findChildrens(estruturaOrganizacionalFilha.getId(), false));

			this.montarTreePorEstruturaSelecionadaRecursivaRegraExclusao(estruturaOrganizacionalFilha, Boolean.TRUE);

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
	 * Método responsável por montar estrutura filhas recursivamente a partir de um nó pai selecionado validando para cada estrutura se existe vínculo. Se existir vínculo não pode alterar nenhum registro - Regra data fim
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param estruturaOrganizacionalSelecionadaPai
	 * @param isNoFilho
	 */
	public void montarTreePorEstruturaSelecionadaRecursivaRegraDataFim(EstruturaOrganizacional estruturaOrganizacionalSelecionadaPai, Calendar dataFim, Boolean isNoFilho) {

		estruturaOrganizacionalSelecionadaPai.setSubEstruturasOrganizacionais((ArrayList<EstruturaOrganizacional>) this.estruturaOrganizacionalDao.findChildrens(estruturaOrganizacionalSelecionadaPai.getId(), false));

		for (EstruturaOrganizacional estruturaOrganizacionalFilha : estruturaOrganizacionalSelecionadaPai.getSubEstruturasOrganizacionais()) {

			estruturaOrganizacionalFilha.setSubEstruturasOrganizacionais((ArrayList<EstruturaOrganizacional>) this.estruturaOrganizacionalDao.findChildrens(estruturaOrganizacionalFilha.getId(), false));

			if( !UtilObjeto.isReferencia(estruturaOrganizacionalFilha.getDataFim()) ) {

				estruturaOrganizacionalFilha.setDataFim(dataFim);

			}

			this.montarTreePorEstruturaSelecionadaRecursivaRegraDataFim(estruturaOrganizacionalFilha, dataFim, Boolean.TRUE);

		}

	}

	@Override
	public List<EstruturaOrganizacional> listarEstruturasOrganizacionais(String nome) {

		return estruturaOrganizacionalDao.listarEstruturasOrganizacionais(nome);
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
	 * Método responsável por buscar todas as estruturas organizacionais nivel 0 (zero) e setar o transiente se possui filhos
	 *
	 * @author renato.jesus
	 *
	 * @param idOrganizacao
	 * @return
	 */
	@Override
	public List<EstruturaOrganizacional> findParents(Long idOrganizacao) {

		ArrayList<EstruturaOrganizacional> estruturas = (ArrayList<EstruturaOrganizacional>) estruturaOrganizacionalDao.findParents(idOrganizacao);

		for (EstruturaOrganizacional estruturaOrganizacional : estruturas) {

			estruturaOrganizacional.setPossuiFilho(this.estruturaOrganizacionalDao.possuiFilhos(estruturaOrganizacional.getId()));
		}

		return estruturas;
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
	 * Método responsável por listar <code>EstruturaOrganizacional</code> de uma <code>Organizacao</code> a partir do nome da <code>EstruturaOrganizacional</code>.
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param nome
	 *            Nome da EstruturaOrganizacional a se procurar.
	 * @param idOrganizacao
	 *            ID da <code>Organizacao</code> a se procurar a <code>EstruturaOrganizacional</code>.
	 * @return <code>List<EstruturaOrganizacional></code>
	 */
	@Override
	public List<EstruturaOrganizacional> listarEstruturasOrganizacionaisPorOrganizacao(String nome, Long idOrganizacao) {

		return this.estruturaOrganizacionalDao.listarEstruturasOrganizacionaisPorOrganizacao(nome, idOrganizacao);
	}

	/**
	 * Método responsável por listar estruturas organizacionais filhas por orgão
	 *
	 * @author wilker.machado
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @return List<EstruturaOrganizacional>
	 */
	@Override
	public List<EstruturaOrganizacional> listarEstruturasOrganizacionaisFilhasPorOrganizacao(String nome, long idOrganizacao) {

		return this.estruturaOrganizacionalDao.listarEstruturasOrganizacionaisFilhasPorOrganizacao(nome, idOrganizacao);
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
	 * Método responsável por listar todas as <code>EstruturaOrganizacional</code> de uma <code>Organizacao</code>
	 *
	 * @author geovane.filho
	 *
	 * @param idOrganizacao
	 *            ID da Organização a se obter as Estruturas Organizacionais.
	 *
	 * @return <code>List<EstruturaOrganizacional></code>
	 */
	@Override
	public List<EstruturaOrganizacional> listarEstruturasOrganizacionaisPorOrganizacao(Long idOrganizacao) {

		return this.estruturaOrganizacionalDao.listarEstruturasOrganizacionaisPorOrganizacao(idOrganizacao);
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
	 * Método responsável por listar estruturas organizacionais na tree por nome
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param nome
	 * @return List<EstruturaOrganizacional>
	 */
	@Override
	public List<EstruturaOrganizacional> listarEstruturasOrganizacionaisPorNomeTree(String nome) {

		return this.estruturaOrganizacionalDao.listarEstruturasOrganizacionaisPorNomeTree(nome);
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
	 * Método responsável por listar estruturas organizacionais da tree pelo id da órgão do usuário logado e nome e filtro datafim
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param idEstrutura
	 * @param nome
	 * @param exibirEstruturasAtivas
	 * @return List<EstruturaOrganizacional>
	 */
	@Override
	public List<EstruturaOrganizacional> listarEstruturasOrganizacionaisDaTree(Long idOrganizacao, String nome, Boolean exibirEstruturasAtivas) {

		List<EstruturaOrganizacional> estruturasOrganizacionais = new ArrayList<EstruturaOrganizacional>();

		ArrayList<EstruturaOrganizacional> estruturasOrganizacionaisResultBusca = (ArrayList<EstruturaOrganizacional>) this.estruturaOrganizacionalDao.listarEstruturasOrganizacionaisDaTree(idOrganizacao, nome, exibirEstruturasAtivas);

		for (EstruturaOrganizacional estruturaOrganizacional : estruturasOrganizacionaisResultBusca) {

			if (!UtilObjeto.isReferencia(estruturaOrganizacional.getEstruturaOrganizacionalParent())) {

				EstruturaOrganizacional estrutura = estruturaOrganizacional.clone();

				this.montarEstruturasArvore(estrutura, estruturasOrganizacionaisResultBusca);

				estruturasOrganizacionais.add(estrutura);

			}

		}

		// Seta o transiente booleano, caso possua filho
		this.setarTransientPossuiFilhoArvore(estruturasOrganizacionais);

		return estruturasOrganizacionais;
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
	 * Método responsável por montar o transient booleano possuiFilho de cada estrutura da lista
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param estruturas
	 */
	private void setarTransientPossuiFilhoArvore(List<EstruturaOrganizacional> estruturas) {

		// Valida se a lista de estrutura é diferente de vazia
		if (!UtilColecao.isVazio(estruturas)) {

			// Percorre a lista de estrutura se seta o transiente booleano de acordo a condição tem filhos
			for (EstruturaOrganizacional estruturaOrganizacional : estruturas) {

				estruturaOrganizacional.setPossuiFilho(this.estruturaOrganizacionalDao.possuiFilhos(estruturaOrganizacional.getId()));
			}
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
	 * Método responsável por montar recursivamente as estruturas
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param estrutura
	 * @param estruturasOrganizacionaisResultBusca
	 */
	private void montarEstruturasArvore(EstruturaOrganizacional estrutura, ArrayList<EstruturaOrganizacional> estruturasOrganizacionaisResultBusca) {

		// percorre todos os itens da busca ou percorrer os filhos da estrutura enviado da chamada recursiva
		for (EstruturaOrganizacional estruturaOrganizacional : estruturasOrganizacionaisResultBusca) {

			// verifica se o item existe a referencia da estrutura pai e verifica se o pai e o no raiz da arvore
			if (UtilObjeto.isReferencia(estruturaOrganizacional.getEstruturaOrganizacionalParent()) && estruturaOrganizacional.getEstruturaOrganizacionalParent().getId().equals(estrutura.getId())) {

				EstruturaOrganizacional estruturaTemp = estruturaOrganizacional.clone();

				// chamada recursiva para preencher os filhos do item da iteração
				montarEstruturasArvore(estruturaTemp, estruturasOrganizacionaisResultBusca);

				// adiciona o item como filho da estrutura
				if (UtilColecao.isVazio(estrutura.getSubEstruturasOrganizacionais())) {

					estrutura.setSubEstruturasOrganizacionais(new ArrayList<EstruturaOrganizacional>());
				}

				estrutura.getSubEstruturasOrganizacionais().add(estruturaTemp);
			}
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
	 * Método responsável por buscar todas as estruturas organizacionais filhas com filtro datafim ou não
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param idOrganizacao
	 * @param exibirEstruturasAtivas
	 * @return List<EstruturaOrganizacional>
	 */
	@Override
	public List<EstruturaOrganizacional> findChildrens(Long idOrganizacao, Boolean exibirEstruturasAtivas) {

		ArrayList<EstruturaOrganizacional> estruturas = (ArrayList<EstruturaOrganizacional>) estruturaOrganizacionalDao.findChildrens(idOrganizacao, exibirEstruturasAtivas);

		for (EstruturaOrganizacional estruturaOrganizacional : estruturas) {
			estruturaOrganizacional.setIdEstruturaOrganizacionalParent(UtilObjeto.isReferencia(estruturaOrganizacional.getEstruturaOrganizacionalParent()) ? estruturaOrganizacional.getEstruturaOrganizacionalParent().getId() : null);
			estruturaOrganizacional.setPossuiFilho(this.estruturaOrganizacionalDao.possuiFilhos(estruturaOrganizacional.getId()));
		}

		return estruturas;
	}


	@Override
	public EstruturaOrganizacional getReference(Long id) {

		EstruturaOrganizacional estruturaOrganizacional = super.getReference(id);

		// Seta transient idParent se houver
		estruturaOrganizacional.setIdEstruturaOrganizacionalParent(UtilObjeto.isReferencia(estruturaOrganizacional.getEstruturaOrganizacionalParent()) ? estruturaOrganizacional.getEstruturaOrganizacionalParent().getId() : null);

		return UtilObjeto.isReferencia(estruturaOrganizacional) ? estruturaOrganizacional : new EstruturaOrganizacional();
	}


	/**********************************************************************************************************************************
	 *
	 * INICIO DOS METODOS UTILIZADOS PARA VALIDACOES DAS ESTRUTURAS
	 *
	 ***********************************************************************************************************************************/

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por validar se o nó possui data fim e se algum filho possue unidade localizadora de bens e com bens ativos - Não permite salvar data fim. Senão seta data fim para todos os filhos
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param estruturaOrganizacional
	 */
	private void validarDataFim(EstruturaOrganizacional estruturaOrganizacional) {

		this.validarDataFimComReferencia(estruturaOrganizacional.getDataFim());

		// Valida se é uma nova entidade
		if (!estruturaOrganizacional.isNew()) {

			// Valida uso entidade se apenas estiver preenchendo data fim e a validação acima for true
			if (UtilObjeto.isReferencia(estruturaOrganizacional.getDataFim()) && this.isValidarEntidades) {

				// Valida se existe nó filho
				if (this.estruturaOrganizacionalDao.existeVinculo("estruturaOrganizacionalParent.id", estruturaOrganizacional.getId())) {

					// Monta a árvore do nó selecionado até o último filho
					this.montarTreePorEstruturaSelecionadaRecursivaRegraDataFim(estruturaOrganizacional, estruturaOrganizacional.getDataFim(), Boolean.FALSE);

				}

				// Apenas percorre os nós filhos setando a data fim de acordo com a estrutura selecionada a ser salva, caso a filha já não possua data fim
			} else if(UtilObjeto.isReferencia(estruturaOrganizacional.getDataFim())){

				// Valida se existe nó filho
				if (this.estruturaOrganizacionalDao.existeVinculo("estruturaOrganizacionalParent.id", estruturaOrganizacional.getId())) {

					// Monta a árvore do nó selecionado até o último filho
					this.montarTreePorEstruturaSelecionadaRecursivaRegraDataFim(estruturaOrganizacional, estruturaOrganizacional.getDataFim(), Boolean.FALSE);

				}
			}

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
	 * Método responsável por validar se a data fim é válida em relação a referência vigente. Data fim só pode ser igual ou maior. Se for no mesmo mês, faz validações de entidade em uso. Se for maior que mês referência não faz validações.
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param dataFim
	 */
	private void validarDataFimComReferencia(Calendar dataFim) {

		this.isValidarEntidades = false;

		if (UtilObjeto.isReferencia(dataFim)) {

			Usuario usuario = this.getUsuario();

			if (UtilObjeto.isReferencia(usuario) && UtilObjeto.isReferencia(usuario.getOrganizacao()) && UtilObjeto.isReferencia(usuario.getOrganizacao().getDataReferenciaVigente())) {

				Calendar referenciaVigente = usuario.getOrganizacao().getDataReferenciaVigente();

				// Se data fim for inferior a data referência vigente
				if (UtilDate.isPrimeiraDataMenorSegundaData(dataFim, referenciaVigente)) {

					throw new BusinessException("VALIDACAO.DATA_FIM_REFERENCIA", CodigoErro.REGRA_NEGOCIO.getValue());

					// Se data fim for no mesmo mês da referência vigente, devo fazer as validações de entidade em uso
				} else if (dataFim.get(Calendar.MONTH) == referenciaVigente.get(Calendar.MONTH)) {

					this.isValidarEntidades = true;

				}

			}

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
	 * Método responsável por validar se a estrutura tem vínculo com colaborador
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param idEstrutura
	 * @param mensagem
	 */
	private void existeVinculoColaborador(Long idEstrutura, String mensagem) {

		if (this.colaboradorService.existeVinculo("estruturaOrganizacional.id", idEstrutura)) {

			throw new BusinessException(mensagem, CodigoErro.REGRA_NEGOCIO.getValue(), " : Colaborador");
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
	 * Método responsável por validar se a estrutura está vinculada em outras entidades - Regra remoção
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param estruturaOrganizacionalSelecionada
	 */
	private void contemEntidadeVinculadaRemocao(EstruturaOrganizacional estruturaOrganizacionalSelecionada) {

		this.existeVinculoColaborador(estruturaOrganizacionalSelecionada.getId(), ERRO_EXCLUSAO_ESTRUTURA);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contemEntidadeVinculada(Long id) {

		return this.colaboradorService.existeVinculo("estruturaOrganizacional.id", id);

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
	 * Método responsável por verificar se a localização possui uma estrutura vinculada
	 *
	 * @author iago.almeida
	 *
	 * @param localizacaoId
	 * @return boolean
	 */
	@Override
	public boolean existeEstruturaVinculadaALocacalizacao(Long localizacaoId, Usuario usuarioLogado) {

		return this.estruturaOrganizacionalDao.existeEstruturaVinculadaALocacalizacao(localizacaoId, usuarioLogado);
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
	 * Método responsável por listar <code>EstruturaOrganizacional</code>que são loja de uma <code>Organizacao</code> a partir do nome da <code>EstruturaOrganizacional</code>.
	 *
	 * @author iago
	 *
	 * @param nome
	 *            Nome da EstruturaOrganizacional a se procurar.
	 * @param idOrganizacao
	 *            ID da <code>Organizacao</code> a se procurar a <code>EstruturaOrganizacional</code>.
	 * @return <code>List<EstruturaOrganizacional></code>
	 */
	@Override
	public List<EstruturaOrganizacional> listarEstruturasOrganizacionaisLojaPorNomeEOrganizacao(String nome, Long idOrganizacao) {

		Long idDominioUnidadeColeta = this.dominioService.findByChaveAndCodigo(Dominio.TIPO_ESTRUTURA_ORGANIZACIONAL, Dominio.TIPO_UNIDADE_COLETA).getId();

		return this.estruturaOrganizacionalDao.listarEstruturasOrganizacionaisLojaPorNomeEOrganizacao(nome, idOrganizacao, idDominioUnidadeColeta);

	}

}