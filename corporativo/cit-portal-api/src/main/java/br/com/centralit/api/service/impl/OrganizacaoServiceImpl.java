package br.com.centralit.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.OrganizacaoDao;
import br.com.centralit.api.service.ConfiguracaoParametroSistemaService;
import br.com.centralit.api.service.ConfiguracaoService;
import br.com.centralit.api.service.OrganizacaoService;
import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.Configuracao;
import br.com.centralit.framework.model.ConfiguracaoParametroSistema;
import br.com.centralit.framework.model.Organizacao;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilObjeto;

@Service("organizacaoService")
public class OrganizacaoServiceImpl extends GenericServiceImpl<Organizacao, Long> implements OrganizacaoService {

	private OrganizacaoDao organizacaoDao;
	
	/** Atributo configuracaoService. */
	@Autowired
	private ConfiguracaoService configuracaoService;
	
	/** Atributo configuracaoParametroSistemaService. */
	@Autowired
	private ConfiguracaoParametroSistemaService configuracaoParametroSistemaService;		

    @Autowired
	public OrganizacaoServiceImpl(OrganizacaoDao organizacaoDao) {

    	this.dao = organizacaoDao;
    	this.organizacaoDao = organizacaoDao;
	}

    /**
	 * {@inheritDoc}
	 */
	@Override
	public List<Organizacao> listarOrganizacaoPorNome(String nome) {

		return organizacaoDao.listarOrganizacaoPorNome(nome);
	}
	
	private void validaPeriodoDataInicioDataFim(Organizacao organizacao){
		if (UtilObjeto.isReferencia(organizacao) && UtilObjeto.isReferencia(organizacao.getDataFim())){
			if (organizacao.getDataFim().before(organizacao.getDataInicio())){
				throw new BusinessException("VALIDACAO.CAMPO_DATA_INICIO_FIM", CodigoErro.REGRA_NEGOCIO.getValue());
			}
		}
	}
	
	private void validaCodigoUnico(Organizacao organizacao){
		
		if (UtilObjeto.isReferencia(organizacao) && UtilObjeto.isReferencia(organizacao.getId())) {
			Organizacao organizacaoOld = this.getReference(organizacao.getId());
			
			if (!organizacao.getCodigo().equalsIgnoreCase(organizacaoOld.getCodigo()) && this.organizacaoDao.existeCodigoIgual(organizacao.getCodigo())) {		
				throw new BusinessException("VALIDACAO.EXISTE_ORGANIZACAO_COM_MESMO_CODIGO", CodigoErro.REGRA_NEGOCIO.getValue());
			}
		}else if (UtilObjeto.isReferencia(organizacao) && this.organizacaoDao.existeCodigoIgual(organizacao.getCodigo())){
			throw new BusinessException("VALIDACAO.EXISTE_ORGANIZACAO_COM_MESMO_CODIGO", CodigoErro.REGRA_NEGOCIO.getValue());
		}
	}
	
	@Override
	public Organizacao save(Organizacao organizacao) {		
		
		validaPeriodoDataInicioDataFim(organizacao);
		validaCodigoUnico(organizacao);
		
		// SALVA A ORGANIZACAO
		organizacao = super.save(organizacao);
				
		//Quando uma organizacao e salva e necessario criar a configuracao para ela
		CriarConfiguracaoOrganizacao(organizacao);		
		
		return organizacao;
		
	}
	
	@Override
	public Organizacao merge(Organizacao organizacao) {
		validaPeriodoDataInicioDataFim(organizacao);
		validaCodigoUnico(organizacao);	
		
		// SALVA A ORGANIZACAO
		return super.merge(organizacao);		
	
	}
	
	private void CriarConfiguracaoOrganizacao(Organizacao organizacao){		
		
		Configuracao configuracao = new Configuracao();
		configuracao.setOrganizacao(organizacao);		
		
		List<ConfiguracaoParametroSistema> configuracaoParametroSistema = new ArrayList<ConfiguracaoParametroSistema>(); 
		configuracaoParametroSistema.add(new ConfiguracaoParametroSistema("EXECUTAR_BASE_INICIAL", "false", configuracao));
		configuracaoParametroSistema.add(new ConfiguracaoParametroSistema("EXECUTAR_SCRIPT_MENU", "false", configuracao));
		configuracaoParametroSistema.add(new ConfiguracaoParametroSistema("EXECUTAR_DOMINIOS", "false", configuracao));
		configuracaoParametroSistema.add(new ConfiguracaoParametroSistema("EXECUTAR_DEFAULT_FILE", "false", configuracao));
		configuracaoParametroSistema.add(new ConfiguracaoParametroSistema("EXECUTAR_MODULO", "false", configuracao));
		configuracaoParametroSistema.add(new ConfiguracaoParametroSistema("EXECUTAR_INTERNACIONALIZACAO", "true", configuracao));

		configuracaoParametroSistema.add(new ConfiguracaoParametroSistema("PATRIMONIO_MASCARA_NUMERO_PATRIMONIO", "9999999999", configuracao));
		configuracaoParametroSistema.add(new ConfiguracaoParametroSistema("TIPO_AVALIACAO_MONETARIA_ESTOQUE", "2", configuracao));
		configuracaoParametroSistema.add(new ConfiguracaoParametroSistema("RELATORIO_PRIMEIRO_TITULO", "Defina o Primeiro título", configuracao));
		configuracaoParametroSistema.add(new ConfiguracaoParametroSistema("RELATORIO_SEGUNDO_TITULO", "Defina o Segundo título", configuracao));
		configuracaoParametroSistema.add(new ConfiguracaoParametroSistema("RELATORIO_TERCEIRO_TITULO", "Defina o Terceiro título", configuracao));
		configuracaoParametroSistema.add(new ConfiguracaoParametroSistema("MASCARA_NUMERO_IDENTIFICACAO", "AAAA999999", configuracao));
		
		configuracao.setParametros(configuracaoParametroSistema);
		
		configuracao = configuracaoService.save(configuracao);
		
	}
}
