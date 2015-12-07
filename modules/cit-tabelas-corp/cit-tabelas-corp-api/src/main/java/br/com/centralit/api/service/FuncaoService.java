package br.com.centralit.api.service;

import java.util.Collection;

import br.com.centralit.api.model.Funcao;
import br.com.centralit.framework.service.arquitetura.GenericService;

public interface FuncaoService extends GenericService<Funcao, Long> {
	Collection<Funcao> findPorNomeEOrganizacao(String nome, Long organizacaoId);
}
