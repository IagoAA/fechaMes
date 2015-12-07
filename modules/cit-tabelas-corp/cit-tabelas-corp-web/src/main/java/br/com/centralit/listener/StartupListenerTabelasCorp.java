package br.com.centralit.listener;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.centralit.api.model.ClasseParceiro;
import br.com.centralit.api.service.ClasseParceiroService;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.EstruturaOrganizacionalService;
import br.com.centralit.framework.model.Dominio;


/**
 *
 * @since 11/09/2015 - 11:09:14
 *
 * @version 1.0.0
 *
 * @author iago
 *
 */
@Component
public class StartupListenerTabelasCorp implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private EstruturaOrganizacionalService estruturaOrganizacionalService;

	@Autowired
	private DominioService dominioService;

	@Autowired
	private ClasseParceiroService classeParceiroService;

	/**
	 *
	 */
	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		criarDominiosTabelasCorp();
		criarClasseParceiro();
	}


	/**
	 * Método criado para criar todas as classes-parceiro
	 */
	private void criarClasseParceiro() {

		Dominio dominioParceiroColaborador = dominioService.findByChaveAndCodigo(Dominio.TIPO_PARCEIRO, Dominio.TIPO_PARCEIRO_COLABORADOR);
		Dominio dominioParceiroOrgaoExterno = dominioService.findByChaveAndCodigo(Dominio.TIPO_PARCEIRO, Dominio.TIPO_PARCEIRO_ORGAO_EXTERNO);
		Dominio dominioParceiroPortador = dominioService.findByChaveAndCodigo(Dominio.TIPO_PARCEIRO, Dominio.TIPO_PARCEIRO_PORTADOR);
		Dominio dominioParceiroFornecedor = dominioService.findByChaveAndCodigo(Dominio.TIPO_PARCEIRO, Dominio.TIPO_PARCEIRO_FORNECEDOR);
		Dominio dominioParceiroSeguradora = dominioService.findByChaveAndCodigo(Dominio.TIPO_PARCEIRO, Dominio.TIPO_PARCEIRO_SEGURADORA);
		Dominio dominioParceiroCliente = dominioService.findByChaveAndCodigo(Dominio.TIPO_PARCEIRO, Dominio.TIPO_PARCEIRO_CLIENTE);
		Dominio dominioParceiroFuncionario = dominioService.findByChaveAndCodigo(Dominio.TIPO_PARCEIRO, Dominio.TIPO_PARCEIRO_FUNCIONARIO);

		classeParceiroService.saveIfNotExist(new ClasseParceiro(dominioParceiroColaborador));
		classeParceiroService.saveIfNotExist(new ClasseParceiro(dominioParceiroOrgaoExterno));
		classeParceiroService.saveIfNotExist(new ClasseParceiro(dominioParceiroPortador));
		classeParceiroService.saveIfNotExist(new ClasseParceiro(dominioParceiroFornecedor));
		classeParceiroService.saveIfNotExist(new ClasseParceiro(dominioParceiroSeguradora));
		classeParceiroService.saveIfNotExist(new ClasseParceiro(dominioParceiroCliente));
		classeParceiroService.saveIfNotExist(new ClasseParceiro(dominioParceiroFuncionario));

	}


	/**
	 *
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por criar os dominios referentes ao modulo de tabelas corp
	 *
	 * @author gilberto.nery
	 *
	 */
	protected void criarDominiosTabelasCorp() {

		List<Dominio> list = new ArrayList<Dominio>();

		list.add(new Dominio("tipoParceiro", "Colaborador", "COLABORADOR", 1L));
		list.add(new Dominio("tipoParceiro", "Orgão externo", "ORGAO_EXTERNO", 2L));
		list.add(new Dominio("tipoParceiro", "Portador", "PORTADOR", 3L));
		list.add(new Dominio("tipoParceiro", "Fornecedor", "FORNECEDOR", 4L));
		list.add(new Dominio("tipoParceiro", "Seguradora", "SEGURADORA", 5L));
		list.add(new Dominio("tipoParceiro", "Cliente", "CLIENTE", 6L));
		list.add(new Dominio("tipoParceiro", "Funcionario", "FUNCIONARIO", 7L));

		this.dominioService.saveListIfNotExist(list);

	}

}