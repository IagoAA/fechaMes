package br.com.centralit.framework.util;

import java.io.UnsupportedEncodingException;


/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: Utilitário de String </b></p>
 *
 * <p><b>Description: Alguns utilitários básicos para String</b></p>
 * 
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
 * 	
 * @since 22/01/2015 - 15:13:44
 *
 * @version 1.0.0
 *
 * @author geovane.filho
 *	
 */
public class UtilString {

	/** Atributo DIA_FIM_MES. */
	private static final int DIA_FIM_MES = 31;
	/** Atributo DIA_INICIO_MES. */
	private static final int DIA_INICIO_MES = 1;
	/** Atributo QNT_CASAS_DATA. */
	private static final int QNT_CASAS_DATA_DDMMYYYY = 3;
	private static final int JANEIRO = 1;
	private static final int DEZEMBRO = 12;
	private static final int ANO_INICIAL = 1900;
	private static final int ANO_FINAL = 9999;

	/**
	 * 
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por verificar se uma <code>String</code> está nula ou vazia.
	 *
	 * @author geovane.filho
	 *
	 * @param string <code>String</code> a se verificar.
	 * @return <code>true</code> caso a string esteja nula ou vazia e <code>false</code> caso contrário.
	 */
	public static boolean isNullOrEmpty(String string) {
		return (string == null || string.isEmpty());
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por verificar se a string esta em um formato possivel de ser transformado em data.
	 * Verifica se esta no formato: dd/MM/yyyy
	 *
	 * @author geovane.filho
	 *
	 * @param string <code>String</code> para se verificar
	 * @return <code>true</code> caso esteja em formato possivel de criação de <code>Date</code> e <code>false</code> caso contrário.
	 */
	public static boolean isDateFormatted(String string) {
		boolean retorno = false;
		String[] date = string.split("/");
		if (date.length == QNT_CASAS_DATA_DDMMYYYY) {
			if (isIntegerDayFormatted(date[0]) && isIntegerMonthFormatted(date[1]) && isIntegerYearFormatted(date[2])) {
				retorno = true;
			}
		}
		return retorno;
	}
	
	/**
	 * 
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por verificar se a string é um número possivel de ser um dia do mês
	 * Válido de 1 a 31
	 *
	 * @author geovane.filho
	 *
	 * @param string <code>String</code> a ser verificada
	 * @return <code>true</code> caso seja um número de um dia do mês e <code>false</code> caso contrário.
	 */
	public static boolean isIntegerDayFormatted(String string) {
		boolean retorno = false;
		if (isNumber(string)) {
			int numero = Integer.parseInt(string);
			if (numero >= DIA_INICIO_MES && numero <= DIA_FIM_MES) {
				retorno = true;
			}
		}
		return retorno;
	}
	
	/**
	 * 
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por verificar se a string é um número possivel de ser um mês do ano
	 * 1 para Janeiro ... 12 para Dezembro.
	 *
	 * @author geovane.filho
	 *
	 * @param string <code>String</code> a ser verificada
	 * @return <code>true</code> caso seja um número de mês do ano e <code>false</code> caso contrário.
	 */
	public static boolean isIntegerMonthFormatted(String string) {
		boolean retorno = false;
		if (isNumber(string)) {
			int numero = Integer.parseInt(string);
			if (numero >= JANEIRO && numero <= DEZEMBRO) {
				retorno = true;
			}
		}
		return retorno;
	}
	
	/**
	 * 
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por verificar se a string é um número possivel de ser um ano
	 * Válido de 1900 a 9999.
	 *
	 * @author geovane.filho
	 *
	 * @param string <code>String</code> a ser verificada
	 * @return <code>true</code> caso seja um número de ano e <code>false</code> caso contrário.
	 */
	public static boolean isIntegerYearFormatted(String string) {
		boolean retorno = false;
		if (isNumber(string)) {
			int numero = Integer.parseInt(string);
			if (numero >= ANO_INICIAL && numero <= ANO_FINAL) {
				retorno = true;
			}
		}
		return retorno;
	}
	
	/**
	 * 
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por verificar se a string é um possível número
	 *
	 * @author geovane.filho
	 *
	 * @param string <code>String</code> a ser verificada
	 * @return <code>true</code> caso seja um número de ano e <code>false</code> caso contrário.
	 */
	public static boolean isNumber(String string) {
		boolean retorno = false;
		try {  
	        Long.parseLong(string);
	        retorno = true;
	    } catch (NumberFormatException nfex) {  
	    }
		return retorno;
	}
	
	public static String convertISO88591ToUtf8(String value) throws UnsupportedEncodingException {
		byte[] valueBytes = value.getBytes("ISO-8859-1");
		return new String(valueBytes, "UTF-8");
	}
	
	public static Long hashCodeString(String valor) {

		Long h = 0L;
		if (valor.toCharArray().length > 0) {
			char val[] = valor.toCharArray();

			for (int i = 0; i < valor.toCharArray().length; i++) {
				h = 31 * h + val[i];
			}
		}
		return h;
	}
}
