package br.com.centralit.api.service;

import org.springframework.web.multipart.MultipartFile;

import br.com.centralit.api.model.Anexo;
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
 * <b>Title: AnexoService</b>
 * </p>
 * 
 * <p>
 * <b>Description: </b>
 * </p>
 * 
 * @since 07/01/2015 - 11:30:29
 * 
 * @version 1.0.0
 * 
 * @author wilker.machado
 * 
 */
public interface AnexoService extends GenericService<Anexo, Long> {

	/**
	 * Método responsável por salvar os bytes ao anexo
	 * 
	 * @author wilker.machado
	 * 
	 * @param file
	 * @param idAnexo
	 */
	void saveAnexo(MultipartFile file, Long idAnexo);
}
