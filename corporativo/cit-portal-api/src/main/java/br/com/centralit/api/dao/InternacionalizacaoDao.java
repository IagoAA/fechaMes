package br.com.centralit.api.dao;

import java.util.List;

import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.Internacionalizacao;
import br.com.centralit.framework.model.Modulo;

public interface InternacionalizacaoDao extends CitGenericDAO {

	boolean existeChave(Internacionalizacao entity);

	boolean existeValor(Internacionalizacao entity);

	List<Internacionalizacao> buscarPorModulo(Modulo moduloAtivo, Dominio idioma);

	/**
	 * Traduz uma determinada chave de acordo com o idioma informado.
	 *
	 * @param chave Chave a ser traduzida
	 * @param idioma Idioma a traduzir
	 * @return <code>String</code> contendo o valor da tradução ou a chave passada como parametro se não houver valor de tradução da mesma no banco de dados.
	 */
	public String getTranslate(String chave, Dominio idioma);

	/**
	 * @author gilberto.nery
	 * @data 09/09/2015
	 *
	 * Consulta a entidade Internacionalizacao por chave e idioma
	 *
	 * @param entity
	 * @return
	 */
	public Internacionalizacao consultaInternacionalizacao (Internacionalizacao entity);

}
