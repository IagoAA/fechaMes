package br.com.centralit.api.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.model.Colaborador;
import br.com.centralit.api.model.Contato;
import br.com.centralit.api.model.Endereco;
import br.com.centralit.api.model.Funcao;
import br.com.centralit.api.model.Pessoa;
import br.com.centralit.api.model.PessoaFisica;
import br.com.centralit.api.model.Telefone;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.FuncaoService;
import br.com.centralit.api.service.ImportacaoDadosService;
import br.com.centralit.api.service.OrganizacaoService;
import br.com.centralit.api.service.PessoaFisicaService;
import br.com.centralit.api.service.PessoaService;
import br.com.centralit.api.service.UsuarioService;
import br.com.centralit.api.viewHelper.PessoaVH;
import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.Organizacao;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.model.UsuarioOrganizacaoItem;
import br.com.centralit.framework.util.UtilObjeto;
import br.com.centralit.framework.util.UtilString;

/**
 * <p>
 * <img src="http://centralit.com.br/images/logo_central.png">
 * </p>
 *
 * <p>
 * <b>Company: </b> Central IT - Governança Corporativa -
 * </p>
 *
 * <p>
 * <b>Title: </b>
 * </p>
 *
 * <p>
 * <b>Description: </b>
 * </p>
 *
 * <p>
 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
 * </p>
 *
 * <p>
 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
 * </p>
 *
 * @since 02/01/2015 - 13:59:39
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Service("importacaoDadosService")
public class ImportacaoDadosServiceImpl implements ImportacaoDadosService {

	@Autowired
	private FuncaoService funcaoService;

	@Autowired
	private OrganizacaoService organizacaoService;

	/** Atributo pessoaFisicaService. */
	@Autowired
	private PessoaFisicaService pessoaFisicaService;

	/** Atributo dominioService. */
	@Autowired
	private DominioService dominioService;

	@Autowired
	private UsuarioService usuarioService;

	/** Atributo pessoaService. */
	@Autowired
	private PessoaService pessoaService;

	@Override
	public Pessoa importaColaborador(PessoaVH pessoaVH) {
		try {
			List<Organizacao> organizacoes = organizacaoService.findAll();

			PessoaFisica pessoaFisica = this.pessoaFisicaService.findPorCPFAndOrganizacao(pessoaVH.getPessoa().getPessoaFisica().getCpf(), organizacoes.get(0).getId());

			if (UtilObjeto.isReferencia(pessoaFisica)) {
				return this.sincronizaColaborador(pessoaVH, pessoaFisica);
			}else{
				List<Dominio> dominiosTipoParceiros = new ArrayList<Dominio>();
				dominiosTipoParceiros.add(dominioService.findByChaveAndCodigo(Dominio.TIPO_PARCEIRO, Dominio.TIPO_PARCEIRO_COLABORADOR));
				pessoaVH.setDominiosTipoParceiroSelecionados(dominiosTipoParceiros);

				Pessoa pessoa = pessoaVH.getPessoa();
				pessoaFisica = pessoa.getPessoaFisica();

				Dominio dominio = pessoaFisica.getDominioSexo();
				dominio = dominioService.findByChaveAndNome(Dominio.TIPO_SEXO, dominio.getNome());
				pessoaFisica.setDominioSexo(dominio);

				dominio = pessoaFisica.getDominioEstadoCivil();
				dominio = dominioService.findByChaveAndNome(Dominio.TIPO_ESTADO_CIVIL, dominio.getNome());
				pessoaFisica.setDominioEstadoCivil(dominio);

				pessoa.setDominioPessoa(dominioService.findByChaveAndCodigo(Dominio.TIPO_PESSOA, Dominio.TIPO_PESSOA_FISICA));
				pessoa.setOrganizacao(organizacoes.get(0));
				this.sincronizaUsuario(pessoa);

				Colaborador colaborador = pessoaVH.getColaborador();
				colaborador.setOrganizacao(organizacoes.get(0));
				this.sincronizaFuncao(colaborador);

				if (pessoa.getEnderecos() == null) {
					pessoa.setEnderecos(new ArrayList<Endereco>());
				}
				if (pessoa.getTelefones() == null){
					pessoa.setTelefones(new ArrayList<Telefone>());
				}
				if (pessoa.getContatos() == null) {
					pessoa.setContatos(new ArrayList<Contato>());
				}
				return pessoaService.save(pessoaVH);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage(), CodigoErro.REGRA_NEGOCIO.getValue());
		}
	}

	private Pessoa sincronizaColaborador(PessoaVH pessoaVH, PessoaFisica pessoaFisica) {
		return pessoaVH.getPessoa();
	}

	private void sincronizaUsuario(Pessoa pessoa) {
		Usuario usuario = null;
		if (UtilObjeto.isReferencia(pessoa.getUsuario()) && !UtilString.isNullOrEmpty(pessoa.getUsuario().getUsername())) {
			List<Usuario> usuarios = usuarioService.findByUsername(pessoa.getUsuario().getUsername());
			if (usuarios != null) {
				for (Usuario usr : usuarios) {
					if (usr.isActive()) {
						usuario = usr;
						break;
					}
				}
			}

			if (!UtilObjeto.isReferencia(usuario)) {
				usuario = pessoa.getUsuario();
				usuario.setOrganizacao(pessoa.getOrganizacao());
				usuario.setPassword("1");
				usuario.setPasswordMobile("1");

				if (UtilString.isNullOrEmpty(usuario.getEmail())) {
					usuario.setEmail(pessoa.getEmail());
				}
				if (UtilString.isNullOrEmpty(usuario.getEmail())) {
					usuario.setEmail(" ");
				}

				UsuarioOrganizacaoItem usuarioOrganizacaoItem = new UsuarioOrganizacaoItem(pessoa.getOrganizacao(), usuario);
				List<UsuarioOrganizacaoItem> organizacoes = new ArrayList<UsuarioOrganizacaoItem>();
				organizacoes.add(usuarioOrganizacaoItem);
				usuario.setOrganizacoes(organizacoes);
				usuario.setContaBloqueada(false);
				usuario.setContaExpirada(false);
				usuario.setCredencialExpirada(false);
				usuario.setContaHabilitada(true);
				usuario.setSempreNovaAba(true);

				usuario = usuarioService.save(pessoa.getUsuario());
			}

		}
		pessoa.setUsuario(usuario);
	}

	private void sincronizaFuncao(Colaborador colaborador) {
		Funcao funcao = null;
		if (UtilObjeto.isReferencia(colaborador.getFuncao()) && !UtilString.isNullOrEmpty(colaborador.getFuncao().getNome())) {
			Collection<Funcao> funcoes = funcaoService.findPorNomeEOrganizacao(colaborador.getFuncao().getNome(), colaborador.getOrganizacao().getId());
			if (funcoes != null) {
				for (Funcao func : funcoes) {
					if (func.isActive()) {
						funcao = func;
						break;
					}
				}
			}

			if (!UtilObjeto.isReferencia(funcao)) {
				funcao = colaborador.getFuncao();
				funcao.setOrganizacao(colaborador.getOrganizacao());
				funcao = funcaoService.save(colaborador.getFuncao());
			}
		}
		colaborador.setFuncao(funcao);
	}
}
