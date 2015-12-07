package br.com.centralit.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.UsuarioOrganizacaoItemDao;
import br.com.centralit.api.service.UsuarioOrganizacaoItemService;
import br.com.centralit.framework.model.UsuarioOrganizacaoItem;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;

@Service("usuarioOrganizacaoItemService")
public class UsuarioOrganizacaoItemServiceImpl extends GenericServiceImpl<UsuarioOrganizacaoItem, Long> implements UsuarioOrganizacaoItemService {

	private UsuarioOrganizacaoItemDao usuarioOrganizacaoItemDao;

    @Autowired
	public UsuarioOrganizacaoItemServiceImpl(UsuarioOrganizacaoItemDao usuarioOrganizacaoItemDao) {

    	this.dao = usuarioOrganizacaoItemDao;

    	this.usuarioOrganizacaoItemDao = usuarioOrganizacaoItemDao;
	}


    /**
     * {@inheritDoc}
     */
	@Override
	public List<UsuarioOrganizacaoItem> buscaOrganizacoesAtivasPorIdUsuario(Long idUsuario) {

		return usuarioOrganizacaoItemDao.buscaOrganizacoesAtivasPorIdUsuario(idUsuario);
	}
}
