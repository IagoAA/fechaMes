package br.com.centralit.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.SeguradoraDao;
import br.com.centralit.api.model.Seguradora;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.Dominio;

import com.googlecode.genericdao.search.Sort;


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
 * @since 14/04/2015 - 10:02:27
 *
 * @version 1.0.0
 *
 * @author juliana.barbosa
 *	
 */
@Repository("seguradoraDao")
public class SeguradoraDaoHibernate extends CitGenericDAOImpl implements SeguradoraDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public SeguradoraDaoHibernate() {

		super(Seguradora.class);
	}

	@Override
	public List<Seguradora> listarSeguradorasPorNomeOrganizacao(String nome, Long idOrganizacao) {

		SearchSeven search = new SearchSeven();		
		
		search.addFilterEqual("pessoa.organizacao.id", idOrganizacao);

		search.addFilterEqual("classeParceiro.dominioTipoParceiro.codigo", Dominio.TIPO_PARCEIRO_SEGURADORA);		
		
		search.addFilterILike("pessoa.nome", "%" + nome + "%");

		search.addSort(Sort.asc("pessoa.nome"));

		search.setMaxResults(10);

		return this.search(search, Seguradora.class);
	}

}
