package br.com.centralit.api.service.impl;

import java.util.Calendar;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.ComissaoDao;
import br.com.centralit.api.model.Comissao;
import br.com.centralit.api.model.ComissaoIntegrante;
import br.com.centralit.api.model.ComissaoObservacao;
import br.com.centralit.api.service.ColaboradorService;
import br.com.centralit.api.service.ComissaoService;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.ModuloService;
import br.com.centralit.api.service.ObservacaoService;
import br.com.centralit.api.service.ConfiguracaoParametroSistemaService;
import br.com.centralit.api.service.UsuarioService;
import br.com.centralit.api.util.ClientRest;
import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.Modulo;
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
 * <b>Title: Implementação dos Serviços de InventarioComissao </b>
 * </p>
 * 
 * <p>
 * <b>Description: Implementação dos serviços de InventarioComissao </b>
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
 * @since 29/12/2014 - 10:49:27
 * 
 * @version 1.0.0
 * 
 * @author geovane.filho
 * 
 */
@Service("inventarioComissaoService")
public class ComissaoServiceImpl extends GenericServiceImpl<Comissao, Long> implements ComissaoService {

	/** Atributo CIT_PATRIMONIO_WEB. */
	private static final String CIT_PATRIMONIO_WEB = "/citgrp-patrimonio-web";

	/** Atributo comissaoDao. */
	private ComissaoDao comissaoDao;

	/** Atributo colaboradorService. */
	@Autowired
	private ColaboradorService colaboradorService;

	/** Atributo usuarioService. */
	@Autowired
	private UsuarioService usuarioService;

	/** Atributo dominioService. */
	@Autowired
	private DominioService dominioService;

	/** Atributo moduloService. */
	@Autowired
	private ModuloService moduloService;

	/** Atributo request. */
	@Autowired
	private HttpServletRequest request;

	/** Atributo configuracaoParametroSistemaService. */
	@Autowired
	private ConfiguracaoParametroSistemaService configuracaoParametroSistemaService;

	/** Atributo observacaoService. */
	@Autowired
	private ObservacaoService observacaoService;

	/**
	 * 
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param comissaoDao
	 *            DAO de comissao.
	 */
	@Autowired
	public ComissaoServiceImpl( ComissaoDao comissaoDao ) {

		this.dao = comissaoDao;

		this.comissaoDao = comissaoDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Comissao save(Comissao inventarioComissao) {

		atualizarAtributos(inventarioComissao);

		Comissao entitySaved = (Comissao) this.dao.save(inventarioComissao);

		configurarCodigo(inventarioComissao);

		return entitySaved;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Comissao merge(Comissao inventarioComissao) {

		validarVinculoDominio(inventarioComissao);

		atualizarAtributos(inventarioComissao);

		return (Comissao) this.comissaoDao.merge(inventarioComissao);

	}

	private void validarVinculoDominio(Comissao comissao) {

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
	 * Método responsável por inativar a comissao de inventario setando dataInativo.
	 * 
	 * @author iago.almeida
	 * 
	 * @param idComissaoInventario
	 * @return
	 */
	@Override
	public boolean removeById(Long idComissaoInventario) {

		this.existeVinculo(idComissaoInventario);

		return this.comissaoDao.removeById(idComissaoInventario);
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
	 * Método responsável por validar se existe vínculo com outro caso de uso, caso o módulo esteja ativo
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param idComissao
	 */
	private void existeVinculo(Long idComissao) {

		Modulo modulo = this.moduloService.getModuloPorBaseUrl(CIT_PATRIMONIO_WEB);

		if (UtilObjeto.isReferencia(modulo)) {

			existeVinculoInventario(idComissao, modulo);

			existeVinculoBaixaBens(idComissao, modulo);
		}

	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por validar se existe vínculo com baixa de bens
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param idComissao
	 * @param modulo
	 */
	private void existeVinculoBaixaBens(Long idComissao, Modulo modulo) {

		ClientRest clientRestAlmoxarifadoBaixa = new ClientRest(request, modulo);
		clientRestAlmoxarifadoBaixa.addParametro("joinClass", "desfazimentoComissao.id");
		clientRestAlmoxarifadoBaixa.addParametro("id", idComissao.toString());
		
		if(clientRestAlmoxarifadoBaixa.validarRegra("baixa/existeVinculo")) {
			throw new BusinessException("MSG.MN020", CodigoErro.REGRA_NEGOCIO.getValue(), " Baixa de bens");
		}
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por validar se existe vínculo com inventário
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param idComissao
	 * @param modulo
	 */
	private void existeVinculoInventario(Long idComissao, Modulo modulo) {

		ClientRest clientRestAlmoxarifadoInventario = new ClientRest(request, modulo);
		clientRestAlmoxarifadoInventario.addParametro("joinClass", "inventarioComissao.id");
		clientRestAlmoxarifadoInventario.addParametro("id", idComissao.toString());

		if (clientRestAlmoxarifadoInventario.validarRegra("inventario/existeVinculo")) {
			throw new BusinessException("MSG.MN020", CodigoErro.REGRA_NEGOCIO.getValue(), " Inventário");
		}
	}

	/**
	 * 
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por atualizar (merge) dos atributos do Comissao
	 * 
	 * @author geovane.filho
	 * @author rogerio.cassimiro
	 * 
	 * @param comissao
	 */
	private void atualizarAtributos(Comissao comissao) {

		// Transient de órgão e autor
		if(UtilObjeto.isReferencia(comissao.getId())) {
			Comissao comissaoTemp = (Comissao) this.comissaoDao.find(comissao.getId());
			comissao.setOrganizacao(comissaoTemp.getOrganizacao());
			comissao.setAutor(comissaoTemp.getAutor());
		}
		
		// Tipo da comissão
		if (UtilObjeto.isReferencia(comissao.getDominioTipoComissao()) && UtilObjeto.isReferencia(comissao.getDominioTipoComissao().getId())) {
			comissao.setDominioTipoComissao(dominioService.getReference(comissao.getDominioTipoComissao().getId()));
		}

		// Observações
		if (!UtilColecao.isVazio(comissao.getObservacoes())) {

			// PERCORRE A LISTA DE OBSERVAÇÕES, SETA TRANSIENT
			for (ComissaoObservacao comissaoObservacao : comissao.getObservacoes()) {
				
				if(UtilObjeto.isReferencia(comissaoObservacao.getId())) {
					
					ComissaoObservacao comissaoObservacaoTemp = (ComissaoObservacao) this.observacaoService.find(comissaoObservacao.getId()); 
					comissaoObservacao.setAutor(comissaoObservacaoTemp.getAutor());
					
				} else {
					
					comissaoObservacao.setAutor(this.usuarioService.find(comissaoObservacao.getAutor().getId()));
				}
				
				comissaoObservacao.setComissao(comissao);
			}
		}

		// Integrantes colaboradores
		if (comissao.getIntegrantes() != null) {
			for (ComissaoIntegrante integrante : comissao.getIntegrantes()) {
				integrante.setIntegrante(this.colaboradorService.find(integrante.getIntegrante().getId()));
				integrante.setInventarioComissao(comissao);
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
	 * Método responsável por
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idColaborador
	 * @return
	 */
	public boolean exiteInventarioComissaoVinculadoAoColaborador(Long idColaborador) {

		return this.comissaoDao.exiteInventarioComissaoVinculadoAoColaborador(idColaborador);
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
	 * Método responsável por listar comissões por nome e órgão
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param nome
	 * @param idOrganizacao
	 * @return List<InventarioComissao>
	 */
	@Override
	public List<Comissao> listarComissaoPorNomeEOrganizacao(String nome, Long idOrganizacao) {

		return this.comissaoDao.listarComissaoPorNomeEOrganizacao(nome, idOrganizacao, this.getReferenciaVigente());
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
	 * Método responsável por verificar se contem Inventario vinculado a estrutura
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idEstrutura
	 * 
	 * @return boolean
	 */
	public boolean existeInventarioVinculadaAEstrutura(Long idEstrutura) {

		return this.comissaoDao.existeInventarioVinculadaAEstrutura(idEstrutura);
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
	 * Método responsável por obter referência vigente
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @return Calendar
	 */
	private Calendar getReferenciaVigente() {

		// TODO ÓRGÃO USUÁRIO LOGADO NA NOVA ESTRUTURA AGOSTO/2015
		Usuario usuarioLogado = (Usuario) usuarioService.find(( (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getId());

		return usuarioLogado.getOrganizacao().getDataReferenciaVigente();
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
	 * Método responsável por listar comissões por nome, órgão e domínio
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param nome
	 * @param idOrganizacao
	 * @param idDominio
	 * @return List<Comissao>
	 */
	@Override
	public List<Comissao> listarComissaoPorNomeEOrganizacaoEDominio(String nome, Long idOrganizacao, Long idDominio) {

		return this.comissaoDao.listarComissaoPorNomeEOrganizacaoEDominio(nome, idOrganizacao, idDominio, getReferenciaVigente());
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
	 * Método responsável por settar o atributo codigo.
	 * 
	 * @author luis.camargo
	 * 
	 * @param entity
	 */
	private void configurarCodigo(Comissao entity) {

		Comissao ultimoRegistro = (Comissao) comissaoDao.buscarUltimoRegistroComOrdenadoParametrizada("codigo", entity.getOrganizacao().getId());

		Long sequencial = null;
		if (UtilObjeto.isReferencia(ultimoRegistro)) {
			sequencial = ultimoRegistro.getCodigo() == null ? null : Long.parseLong(ultimoRegistro.getCodigo());
		}
		String codigo = configuracaoParametroSistemaService.gerarNumeroIdentificacao(sequencial, entity.getOrganizacao());

		entity.setCodigo(codigo);
	}
}
