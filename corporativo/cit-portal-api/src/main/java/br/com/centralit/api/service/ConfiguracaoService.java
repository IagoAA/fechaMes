package br.com.centralit.api.service;

import org.springframework.web.multipart.MultipartFile;

import br.com.centralit.framework.model.Configuracao;
import br.com.centralit.framework.model.Organizacao;
import br.com.centralit.framework.service.arquitetura.GenericService;

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
 * <b>Title: </b>
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
 * @since 24/02/2015 - 14:32:40
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
public interface ConfiguracaoService extends GenericService<Configuracao, Long> {

	public Configuracao uploadLogoPaginaLoginForResource(MultipartFile file, String filePath);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por obter a configuração por organizacao
	 * 
	 * @author renato.jesus
	 * 
	 * @return Configuracao
	 */
	public Configuracao getConfiguracao(Long idOrganizacao);

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
	 * @author renato.jesus
	 * 
	 * @param chave
	 * @return
	 */
	public String getParametro(String chave);
	
	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por fazer upload da imagem de órgão
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param file
	 * @param idOrganizacao
	 * @param idConfiguracao
	 */
	void uploadAnexoImagemOrganizacao(MultipartFile file, Long idOrganizacao, Long idConfiguracao);

	/**
	 * Método responsável por
	 *
	 * @author wilker.machado
	 *
	 * @param idOrganizacaoAnterior
	 * @param organizacaoAtual
	 * @return
	 */
	public Configuracao atualizarOrganizacaoConfiguracao(Long idOrganizacaoAnterior, Organizacao organizacaoAtual);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por recuperar o valor de um determinado parametro das configurações de uma organizacao.
	 * 
	 * @author geovane.filho
	 * 
	 * @param chave "Chave" do parametro a se buscar o valor.
	 * @param idOrganizacao Id da organizacao a se buscar o valor do parametro de sua configuração.
	 * @return
	 */
	public String getParametroByOrganizacao(String chave, Long idOrganizacao);
}
