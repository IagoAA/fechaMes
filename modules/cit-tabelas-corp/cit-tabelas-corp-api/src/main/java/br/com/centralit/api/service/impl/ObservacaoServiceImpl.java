package br.com.centralit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.ObservacaoDao;
import br.com.centralit.api.model.Observacao;
import br.com.centralit.api.service.ObservacaoService;
import br.com.centralit.api.service.UsuarioService;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;

@Service("observacaoService")
public class ObservacaoServiceImpl extends GenericServiceImpl<Observacao, Long> implements ObservacaoService {

	/** Atributo usuarioService. */
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	public ObservacaoServiceImpl( ObservacaoDao observacaoDao ) {

		this.dao = observacaoDao;
	}

}
