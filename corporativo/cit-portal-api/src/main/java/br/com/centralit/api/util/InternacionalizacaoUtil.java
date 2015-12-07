/**
 *
 */
package br.com.centralit.api.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.centralit.framework.util.UtilObjeto;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author geovane.filho
 *
 */
public class InternacionalizacaoUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String portal1 = "D:\\portal.json";
		String portal2 = "D:\\portal2.json";
		String portalResultado = "D:\\portalResultado.json";

		Map<String, Object> portalObject1 = getMap(portal1);
		Map<String, Object> portalObject2 = getMap(portal2);

		Map<String, String> portalChaves1 =  converterMapEmObjeto(portalObject1, "", portal1);
		Map<String, String> portalChaves2 =  converterMapEmObjeto(portalObject2, "", portal2);

		for (String chave : portalChaves2.keySet()) {
			if (portalChaves1.get(chave) != null && !portalChaves1.get(chave).equals(portalChaves2.get(chave))) {
				throw new IllegalArgumentException("CONFLITO: A chave: '" + chave + "' existem nos dois arquivos com valores diferentes!");
			} else {
				portalChaves1.put(chave, portalChaves2.get(chave));
			}
		}

		Map<String, Object> resultado = ConverteListaEmMapaParaJson(portalChaves1);

		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File(portalResultado), resultado);
		} catch (Exception e) {
		}

		System.out.println(resultado);
	}

	@SuppressWarnings("unchecked")
	private static Map<String, Object> getMap(String caminhoJson) {
		try {
			ObjectMapper mapper = new ObjectMapper();

			Map<String, Object> restMap = mapper.readValue(new FileReader(caminhoJson), Map.class);

			return restMap;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *
	 * Método responsável por
	 *
	 * @author wilker
	 *
	 * @param restMap
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Map<String, String> converterMapEmObjeto(Map<String, Object> restMap, String str, String portal) {

		Map<String, String> map = new HashMap<String, String>();

		if(UtilObjeto.isReferencia(restMap)){
			for (String chavePrimaria : restMap.keySet()) {
				String inicioLabel;

				if (!str.trim().isEmpty()) {
					inicioLabel = str + "." + chavePrimaria;
				} else {
					inicioLabel = chavePrimaria;
				}
				if (restMap.get(chavePrimaria) instanceof String) {
					if (map.get(inicioLabel) != null) {
						throw new IllegalArgumentException("CONFLITO: Existem duas chaves: '" + inicioLabel + "' no " + portal);
					} else {
						map.put(inicioLabel, (String) restMap.get(chavePrimaria));
					}
				} else {
					map.putAll(converterMapEmObjeto((Map<String, Object>) restMap.get(chavePrimaria), inicioLabel, portal));
				}
			}
		}

		return map;
	}

	@SuppressWarnings("unchecked")
	private static Map<String, Object> ConverteListaEmMapaParaJson(Map<String, String> result) {

		Map<String, Object> internacionalizacaoMap = new HashMap<String, Object>();

		for (String chave : result.keySet()) {

			String[] chaves = chave.split("\\.");

			if (chaves.length == 1) {

				internacionalizacaoMap.put(chaves[0].trim(), result.get(chave));

			} else if (chaves.length == 2) {

				if (internacionalizacaoMap.containsKey(chaves[0].trim())) {

					( (Map<String, Object>) internacionalizacaoMap.get(chaves[0].trim()) ).put(chaves[1], result.get(chave));

				} else {

					Map<String, Object> valores = new HashMap<String, Object>();

					valores.put(chaves[1].trim(), result.get(chave));

					internacionalizacaoMap.put(chaves[0].trim(), valores);

				}

			} else if (chaves.length == 3) {

				if (internacionalizacaoMap.containsKey(chaves[0].trim())) {

					if (( (Map<String, Object>) internacionalizacaoMap.get(chaves[0].trim()) ).containsKey(chaves[1].trim())) {

						( (Map<String, Object>) ( (Map<String, Object>) internacionalizacaoMap.get(chaves[0].trim()) ).get(chaves[1].trim()) ).put(chaves[2].trim(), result.get(chave));

					} else {

						Map<String, Object> valores2 = new HashMap<String, Object>();

						valores2.put(chaves[2].trim(), result.get(chave));

						( (Map<String, Object>) internacionalizacaoMap.get(chaves[0].trim()) ).put(chaves[1].trim(), valores2);
					}

				} else {

					Map<String, Object> valores = new HashMap<String, Object>();

					Map<String, Object> valores2 = new HashMap<String, Object>();

					valores2.put(chaves[2].trim(), result.get(chave));

					valores.put(chaves[1].trim(), valores2);

					internacionalizacaoMap.put(chaves[0].trim(), valores);
				}
			}

		}

		return internacionalizacaoMap;
	}
}
