package br.com.centralit.api.dao;

import java.util.Collection;

import br.com.centralit.api.model.FuncionarioAnexo;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;

/**
*
* @since 17/11/2015 - 09:55:57
*
* @version 1.0.0
*
* @author iago
*
*/
public interface FuncionarioAnexoDao extends CitGenericDAO {

	Collection<FuncionarioAnexo> listarAnexos(Long idFuncionario);

}
