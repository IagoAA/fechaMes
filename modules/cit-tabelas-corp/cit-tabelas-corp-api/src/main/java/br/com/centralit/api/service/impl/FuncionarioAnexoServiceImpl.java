package br.com.centralit.api.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.centralit.api.dao.FuncionarioAnexoDao;
import br.com.centralit.api.model.FuncionarioAnexo;
import br.com.centralit.api.service.FuncionarioAnexoService;
import br.com.centralit.api.service.FuncionarioService;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilColecao;




/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p>
 *
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">595</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
 *
 * @since 16/04/2015 - 10:28:40
 *
 * @version 1.0.0
 *
 * @author juliana.barbosa
 *
 */
@Service("funcionarioAnexoService")
public class FuncionarioAnexoServiceImpl extends GenericServiceImpl<FuncionarioAnexo, Long> implements FuncionarioAnexoService {

	/** Atributo funcionarioService. */
	@Autowired
	private FuncionarioService funcionarioService;

	/** Atributo funcionarioAnexoDao. */
	private FuncionarioAnexoDao funcionarioAnexoDao;

	@Autowired
	public FuncionarioAnexoServiceImpl( FuncionarioAnexoDao funcionarioAnexoDao) {

		this.dao = funcionarioAnexoDao;
		this.funcionarioAnexoDao = funcionarioAnexoDao;
	}

	@Override
	public Collection<FuncionarioAnexo> listarAnexos(Long idFuncionario) {

		Collection<FuncionarioAnexo> listaAnexos = this.funcionarioAnexoDao.listarAnexos(idFuncionario);

		return UtilColecao.isVazio(listaAnexos) ? new ArrayList<FuncionarioAnexo>() : listaAnexos;
	}


	/**
	 *Método responsável por salvar o anexo do funcionarios
	 */
	public void save(MultipartFile file, Long idFuncionario) {

		FuncionarioAnexo funcionario = this.montarDadosFuncionarioAnexo(file, idFuncionario);

		super.save(funcionario);

	}

	private FuncionarioAnexo montarDadosFuncionarioAnexo(MultipartFile file, Long idFuncionario) {

		FuncionarioAnexo funcionarioAnexo = new FuncionarioAnexo();

		funcionarioAnexo.setFuncionario(this.funcionarioService.find(idFuncionario));

		try {

			funcionarioAnexo.setAnexo(file.getBytes());

			funcionarioAnexo.setTamanho(file.getSize());

			funcionarioAnexo.setDescricao(file.getOriginalFilename());

		} catch (IOException e) {

			e.printStackTrace();
		}

		return funcionarioAnexo;
	}

	@Override
	public boolean removeById(Long id) {

		FuncionarioAnexo funcionarioAnexo = this.find(id);

		return remove(funcionarioAnexo);
	}

	@Override
	public boolean remove(FuncionarioAnexo entity) {

		entity.setFuncionarioInativo(entity.getFuncionario());

		entity.setFuncionario(null);

		return super.remove(entity);
	}

	public FuncionarioService getFuncionarioService() {

		return funcionarioService;
	}


	public void setFuncionarioService(FuncionarioService funcionarioService) {

		this.funcionarioService = funcionarioService;
	}


}
