package br.com.centralit.api.service;

import java.util.Collection;

import org.springframework.web.multipart.MultipartFile;

import br.com.centralit.api.model.FuncionarioAnexo;
import br.com.centralit.framework.service.arquitetura.GenericService;

/**
*
* @since 17/11/2015 - 09:55:57
*
* @version 1.0.0
*
* @author iago
*
*/
public interface FuncionarioAnexoService extends GenericService<FuncionarioAnexo, Long> {

	/**
	 * Método responsável por salvar os anexos do funcionario
	 *
	 * @author iago
	 *
	 * @param file
	 * @param idFuncionario
	 */
	void save(MultipartFile file, Long idFuncionario);

	Collection<FuncionarioAnexo> listarAnexos(Long idFuncionario);
}
