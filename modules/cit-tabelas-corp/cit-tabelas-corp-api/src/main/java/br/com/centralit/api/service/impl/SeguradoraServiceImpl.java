package br.com.centralit.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.SeguradoraDao;
import br.com.centralit.api.model.Seguradora;
import br.com.centralit.api.service.ClasseParceiroService;
import br.com.centralit.api.service.SeguradoraService;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;


@Service("seguradoraService")
public class SeguradoraServiceImpl extends GenericServiceImpl<Seguradora, Long> implements SeguradoraService {

	/** Atributo seguradoraDao. */
	private final SeguradoraDao seguradoraDao;
	
	/** Atributo usuarioService. */
	@Autowired
	private ClasseParceiroService classeParceiroService;	

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	@Autowired
	public SeguradoraServiceImpl( final SeguradoraDao seguradoraDao ) {

		this.dao = seguradoraDao;

		this.seguradoraDao = seguradoraDao;
	}

	@Override
	public Seguradora save(final Seguradora entity) {

		entity.setClasseParceiro(this.getClasseParceiroService().obterPorCodigoDominioTipParceiro(Dominio.TIPO_PARCEIRO_SEGURADORA));

		return super.save(entity);
	}
	
	@Override
	public Seguradora merge(Seguradora entity) {
		
		return super.merge(entity);
	}
	

	@Override
	public List<Seguradora> listarSeguradorasPorNomeOrganizacao(final String nome, Long idOrganizacao) {
		
		return getSeguradoraDao().listarSeguradorasPorNomeOrganizacao(nome, idOrganizacao);
	}


	
	public ClasseParceiroService getClasseParceiroService() {
	
		return classeParceiroService;
	}

	
	public void setClasseParceiroService(ClasseParceiroService classeParceiroService) {
	
		this.classeParceiroService = classeParceiroService;
	}

	
	public SeguradoraDao getSeguradoraDao() {
	
		return seguradoraDao;
	}

	

}
