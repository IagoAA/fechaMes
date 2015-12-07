package br.com.centralit.framework.util;

import java.util.Arrays;
import java.util.List;


public class Util {
	/**
	 * Método que retorna a mascara do telefone de acordo com ddd
	 *
	 * @param value
	 * @return String mask
	 */

	public static final List dddsNonoDigito = Arrays.asList("11", "12", "13", "14", "15", "16", "17", "18", "19", "21", "22", "24", "27", "28");

	public static String getMaskTelefone(String ddd) {
		if(dddsNonoDigito.contains(ddd)){
			return "99999-9999";
		}else{
			return "9999-9999";
		}
	}

	public static String getNomeMes(int mes) {
		switch (mes) {
			case 1:
				return "Janeiro";
			case 2:
				return "Fevereiro";
			case 3:
				return "Março";
			case 4:
				return "Abril";
			case 5:
				return "Maio";
			case 6:
				return "Junho";
			case 7:
				return "Julho";
			case 8:
				return "Agosto";
			case 9:
				return "Setembro";
			case 10:
				return "Outubro";
			case 11:
				return "Novembro";
			case 12:
				return "Dezembro";
		default:
			return "";
		}
	}

	public static String concatIdsArray(int[] ids) {
		StringBuilder idsConcat = new StringBuilder();

		if(ids != null){
			idsConcat = new StringBuilder();
			for (int n : ids) {
			    if (idsConcat.length() > 0) idsConcat.append(',');
			    	idsConcat.append(n);
			}
		}

		return idsConcat.toString();
	}

}