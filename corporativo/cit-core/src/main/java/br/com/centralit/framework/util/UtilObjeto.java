package br.com.centralit.framework.util;

import java.io.Reader;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

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
 * <b>Title: </b> Classe UtilObjeto
 * </p>
 * 
 * <p>
 * <b>Description: </b> Classe Utililitária <code>UtilObjeto</code>
 * </p>
 * 
 * @since 19/11/2014 - 11:00:16
 * 
 * @version 1.0.0
 * 
 * @author wilker.machado
 * 
 */
public class UtilObjeto {

	/**
	 * Construtor.
	 */
	private UtilObjeto() {

		super();
	}

	/**
	 * Retorna a classe do objeto.
	 * 
	 * @param <T>
	 *            Tipo do objeto da classe.
	 * @param objeto
	 *            Objeto
	 * 
	 * @return Classe
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getClasse(final T objeto) {

		Class<T> classe = null;

		if (isReferencia(objeto)) {

			if (isClasse(objeto)) {

				classe = (Class<T>) objeto;
			} else {

				classe = (Class<T>) objeto.getClass();
			}
		}

		return classe;
	}

	/**
	 * Retorna true se o objeto é uma referência.
	 * 
	 * @param objeto
	 *            Objeto
	 * 
	 * @return true se o objeto é uma referência.
	 */
	public static boolean isReferencia(final Object objeto) {

		return ( objeto != null );
	}

	/**
	 * Retorna true se o objeto é uma referência.
	 * 
	 * @param objeto1
	 *            Objeto
	 * @param objeto2
	 *            Objeto
	 * 
	 * @return true se o objeto é uma referência.
	 */
	public static boolean isReferencia(final Object objeto1, final Object objeto2) {

		return ( isReferencia(objeto1) && isReferencia(objeto2) );
	}

	/**
	 * Retorna true se o objeto é uma referência.
	 * 
	 * @param objeto1
	 *            Objeto
	 * @param objeto2
	 *            Objeto
	 * @param objeto3
	 *            Objeto
	 * 
	 * @return true se o objeto é uma referência.
	 */
	public static boolean isReferencia(final Object objeto1, final Object objeto2, final Object objeto3) {

		return ( isReferencia(objeto1, objeto2) && isReferencia(objeto3) );
	}

	/**
	 * Retorna true se o objeto é uma referência.
	 * 
	 * @param objetos
	 *            Objetos
	 * 
	 * @return true se o objeto é uma referência.
	 */
	public static boolean isReferenciaTodos(final Object... objetos) {

		boolean res = false;

		if (isReferencia(objetos)) {

			res = true;

			for (int idx = 0; idx < objetos.length && ( res == true ); idx++) {

				res = isReferencia(objetos[idx]);
			}
		}

		return res;
	}

	/**
	 * Retorna true se o tipo for uma coleção.
	 * 
	 * @param objeto
	 *            objeto que será validado.
	 * 
	 * @return true se o tipo for uma coleção.
	 */
	public static boolean isColecao(final Object objeto) {

		return ( objeto instanceof Collection );
	}

	/**
	 * Retorna true se o tipo for uma coleção.
	 * 
	 * @param classe
	 *            Classe que será validada.
	 * 
	 * @return true se o tipo for uma coleção.
	 */
	public static boolean isColecao(final Class<?> classe) {

		final Class<?> colecao = Collection.class;

		return ( isReferencia(classe) && colecao.isAssignableFrom(classe) );
	}

	/**
	 * Retorna true se o tipo for um Comparable.
	 * 
	 * @param objeto
	 *            Objeto que será validado.
	 * 
	 * @return true se o tipo for um Comparable.
	 */
	public static boolean isComparable(final Object objeto) {

		return ( objeto instanceof Comparable<?> );
	}

	/**
	 * Retorna true se o tipo for um Comparable.
	 * 
	 * @param classe
	 *            Classe que será validada.
	 * @return true se o tipo for um Comparable.
	 */
	public static boolean isComparable(final Class<?> classe) {

		final Class<?> colecao = Comparable.class;

		return ( isReferencia(classe) && colecao.isAssignableFrom(classe) );
	}

	/**
	 * Retorna true se o tipo for um mapa.
	 * 
	 * @param objeto
	 *            objeto que será validado.
	 * 
	 * @return true se o tipo for um mapa.
	 */
	public static boolean isMapa(final Object objeto) {

		return ( objeto instanceof Map );
	}

	/**
	 * Retorna true se o tipo for um mapa.
	 * 
	 * @param classe
	 *            Classe que será validada.
	 * 
	 * @return true se o tipo for um mapa.
	 */
	public static boolean isMapa(final Class<?> classe) {

		final Class<?> mapa = Map.class;

		return ( isReferencia(classe) && mapa.isAssignableFrom(classe) );
	}

	/**
	 * Retorna true se o objeto for uma classe.
	 * 
	 * @param objeto
	 *            Objeto validado.
	 * 
	 * @return true se o objeto for uma classe.
	 */
	public static boolean isClasse(final Object objeto) {

		return ( isReferencia(objeto) && ( objeto instanceof Class ) );
	}

	/**
	 * Retorna true se o objeto for um integer.
	 * 
	 * @param objeto
	 *            Objeto validado
	 * 
	 * @return true se o objeto for um integer
	 */
	public static boolean isInteger(final Object objeto) {

		return ( isReferencia(objeto) && ( objeto instanceof Integer ) );
	}

	/**
	 * Retorna true se o objeto for um long.
	 * 
	 * @param objeto
	 *            Objeto validado
	 * 
	 * @return true se o objeto for um long
	 */
	public static boolean isLong(final Object objeto) {

		return ( isReferencia(objeto) && ( objeto instanceof Long ) );
	}

	/**
	 * Retorna true se o objeto for um Boolean.
	 * 
	 * @param objeto
	 *            Objeto validado
	 * 
	 * @return true se o objeto for um Boolean
	 */
	public static boolean isBoolean(final Object objeto) {

		return ( isReferencia(objeto) && ( objeto instanceof Boolean ) );
	}

	/**
	 * Retorna true se o objeto for um Byte.
	 * 
	 * @param objeto
	 *            Objeto validado
	 * 
	 * @return true se o objeto for um Byte
	 */
	public static boolean isByte(final Object objeto) {

		return ( isReferencia(objeto) && ( objeto instanceof Byte ) );
	}

	/**
	 * Retorna true se o objeto for um Short.
	 * 
	 * @param objeto
	 *            Objeto validado
	 * 
	 * @return true se o objeto for um Short
	 */
	public static boolean isShort(final Object objeto) {

		return ( isReferencia(objeto) && ( objeto instanceof Short ) );
	}

	/**
	 * Retorna true se o objeto for um Character.
	 * 
	 * @param objeto
	 *            Objeto validado
	 * 
	 * @return true se o objeto for um Character
	 */
	public static boolean isCharacter(final Object objeto) {

		return ( isReferencia(objeto) && ( objeto instanceof Character ) );
	}

	/**
	 * Retorna true se o objeto for um Float.
	 * 
	 * @param objeto
	 *            Objeto validado
	 * 
	 * @return true se o objeto for um Float
	 */
	public static boolean isFloat(final Object objeto) {

		return ( isReferencia(objeto) && ( objeto instanceof Float ) );
	}
	
	/**
	 * Retorna true se o objeto for um Number.
	 * 
	 * @param objeto
	 *            Objeto validado
	 * 
	 * @return true se o objeto for um Number
	 */
	public static boolean isNumber(final Object objeto) {

		return ( isReferencia(objeto) && ( objeto instanceof Number ) );
	}

	/**
	 * Retorna true se o objeto for um Double.
	 * 
	 * @param objeto
	 *            Objeto validado
	 * 
	 * @return true se o objeto for um Double
	 */
	public static boolean isDouble(final Object objeto) {

		return ( isReferencia(objeto) && ( objeto instanceof Double ) );
	}

	/**
	 * Retorna true se o objeto for um String.
	 * 
	 * @param objeto
	 *            Objeto validado
	 * 
	 * @return true se o objeto for um String
	 */
	public static boolean isString(final Object objeto) {

		return ( isReferencia(objeto) && ( objeto instanceof String ) );
	}

	/**
	 * Retorna true se o objeto for um Date.
	 * 
	 * @param objeto
	 *            Objeto validado
	 * 
	 * @return true se o objeto for um Date
	 */
	public static boolean isDate(final Object objeto) {

		return ( isReferencia(objeto) && ( objeto instanceof Date ) );
	}

	/**
	 * Retorna true se o objeto for um java.sql.Date.
	 * 
	 * @param objeto
	 *            Objeto validado
	 * 
	 * @return true se o objeto for um java.sql.Date
	 */
	public static boolean isSqlDate(final Object objeto) {

		return ( isReferencia(objeto) && ( objeto instanceof java.sql.Date ) );
	}

	/**
	 * Retorna true se o objeto for um Time.
	 * 
	 * @param objeto
	 *            Objeto validado
	 * 
	 * @return true se o objeto for um Time
	 */
	public static boolean isTime(final Object objeto) {

		return ( isReferencia(objeto) && ( objeto instanceof Time ) );
	}

	/**
	 * Retorna true se o objeto for um Timestamp.
	 * 
	 * @param objeto
	 *            Objeto validado
	 * 
	 * @return true se o objeto for um Timestamp
	 */
	public static boolean isTimestamp(final Object objeto) {

		return ( isReferencia(objeto) && ( objeto instanceof Timestamp ) );
	}

	/**
	 * Retorna true se o objeto for um Reader.
	 * 
	 * @param objeto
	 *            Objeto validado
	 * 
	 * @return true se o objeto for um Reader
	 */
	public static boolean isReader(final Object objeto) {

		return ( isReferencia(objeto) && ( objeto instanceof Reader ) );
	}

	/**
	 * Retorna true se o objeto for um big decimal
	 * 
	 * @param objeto
	 *            Objeto validado.
	 * 
	 * @return true se o objeto for um big decimal
	 */
	public static boolean isBigDecimal(final Object objeto) {

		return ( objeto instanceof BigDecimal );
	}

	/**
	 * Retorna true se o objeto for do tipo informado.
	 * 
	 * @param objeto
	 *            Objeto validado.
	 * @param tipo
	 *            Tipo desejado
	 * 
	 * @return true se o objeto for do tipo informado.
	 */
	public static boolean isObjetoDoTipo(final Object objeto, Class<?> tipo) {

		boolean res = false;

		if (isReferencia(objeto, tipo)) {

			Class<?> classe = getClasse(objeto);

			res = tipo.isAssignableFrom(classe);
		}

		return res;
	}
}
