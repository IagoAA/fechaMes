package br.com.centralit.api.service;

import java.util.List;

import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.Internacionalizacao;
import br.com.centralit.framework.model.Modulo;
import br.com.centralit.framework.service.arquitetura.GenericService;

public interface InternacionalizacaoService extends GenericService<Internacionalizacao, Long> {

	List<Internacionalizacao> buscarPorModulo(Modulo moduloAtivo, Dominio idioma);

	Boolean atualizarArquivoPortalJson();

	void salvarLabelsDoPortalJson();
	
	/**
	 * Traduz uma determinada chave de acordo com o idioma informado.
	 * 
	 * @param chave Chave a ser traduzida
	 * @param idioma Idioma a traduzir
	 * @return <code>String</code> contendo o valor da tradução ou a chave passada como parametro se não houver valor de tradução da mesma no banco de dados.
	 */
	public String getTranslate(String chave, Dominio idioma);
	
}
