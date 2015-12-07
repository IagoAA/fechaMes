package br.com.centralit.api.dao.impl;

import br.com.centralit.api.dao.ObservacaoDao;
import br.com.centralit.api.model.Observacao;

import org.springframework.stereotype.Repository;

import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;

@Repository("observacaoDao")
public class ObservacaoDaoHibernate extends CitGenericDAOImpl implements ObservacaoDao {
	public ObservacaoDaoHibernate() {
		super(Observacao.class);
	}
}
