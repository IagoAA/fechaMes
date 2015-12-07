package br.com.centralit.framework.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b> UtilColecao</p>
 *
 * <p><b>Description: </b> Classe utilitaria para validações de Collections</p>
 *
 * @since 26/11/2014 - 11:24:38
 *
 * @version 1.0.0
 *
 * @author wilker.machado
 *
 */
public class UtilColecao {

	/**
     * Construtor.
     */
    private UtilColecao() {

            super();
    }

    /**
     * Ordena os objetos da coleção. Os objetos deverão implementar Comparable para que a ordenação seja feita.
     *
     * @param <T>
     *            Tipo do objeto que a coleção possui.
     *
     * @param colecao
     *            Coleção de objetos.
     *
     * @return Coleção ordenada.
     *
     * @see Comparable
     */
    public static <T extends Comparable<? super T>> Collection<T> ordenar(final Collection<T> colecao) {

            List<T> resultado = null;

            if (isReferencia(colecao)) {

                    resultado = new ArrayList<T>(colecao);

                    Collections.sort(resultado);
            }

            return resultado;
    }

    /**
     * Ordena os objetos da coleção com base no Comparator passado por parámetro.
     *
     * @param <T>
     *            Tipo do objeto que a coleção possui.
     * @param colecao
     *            Coleção de objetos.
     * @param comparator
     *            Comparator.
     *
     * @return Coleção ordenada
     *
     * @see Comparator
     */
    public static <T> List<T> ordenar(final List<T> colecao, final Comparator<? super T> comparator) {

            if (colecao != null && comparator != null) {

                    Collections.sort(colecao, comparator);
            }

            return colecao;
    }

    /**
     * Recupera o elemento do índice solicitado.
     *
     * @param <T>
     *            Tipo do objeto da lista.
     * @param lista
     *            Lista de objetos.
     * @param indice
     *            índice desejado.
     *
     * @return Objeto do índice
     */
    public static <T> T getElementoDoIndice(final List<T> lista, final int indice) {

            T resultado = null;

            if (!isVazio(lista) && indice >= 0 && indice < lista.size()) {

                    resultado = lista.get(indice);
            }

            return resultado;
    }

    /**
     * Recupera o elemento do índice solicitado.
     *
     * @param <T>
     *            Tipo do objeto da lista.
     * @param colecao
     *            Coleção de objetos.
     * @param indice
     *            índice desejado.
     *
     * @return Objeto do índice
     */
    public static <T> T getElementoDoIndice(final Collection<T> colecao, final int indice) {

            final List<T> lista = new ArrayList<T>(colecao);

            return getElementoDoIndice(lista, indice);
    }

    /**
     * Recupera o elemento do índice solicitado.
     *
     * @param <T>
     *            Tipo do objeto da lista.
     * @param colecao
     *            Coleção de objetos.
     *
     * @return Objeto do índice
     */
    public static <T> T getElementoDoUltimoIndice(final Collection<T> colecao) {

            final List<T> lista = new ArrayList<T>(colecao);

            final int indice = ( lista.size() - 1 );

            return getElementoDoIndice(lista, indice);
    }

    /**
     * Retorna o tamanho da coleção.
     *
     * @param colecao
     *            Coleção
     *
     * @return tamanho da coleção
     */
    public static int getTamanho(final Collection<?> colecao) {

            int resultado = 0;

            if (isReferencia(colecao)) {

                    resultado = colecao.size();
            }

            return resultado;
    }

    /**
     * Retorna true se a coleção estiver vazia.
     *
     * @param colecao
     *            Collection
     *
     * @return true se a coleção estiver vazia.
     */
    public static boolean isVazio(final Collection<?> colecao) {

            return ( getTamanho(colecao) == 0 );
    }

    /**
     * Retorna true se o objeto tiver referência.
     *
     * @param objeto
     *            Objeto validado
     *
     * @return true se o objeto tiver referência.
     */
    private static boolean isReferencia(final Object objeto) {

            return ( objeto != null );
    }
}
