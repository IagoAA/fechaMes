package br.com.centralit.api.service.impl;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.PessoaDao;
import br.com.centralit.api.model.Colaborador;
import br.com.centralit.api.model.Contato;
import br.com.centralit.api.model.Endereco;
import br.com.centralit.api.model.Fornecedor;
import br.com.centralit.api.model.Funcionario;
import br.com.centralit.api.model.OrgaoExterno;
import br.com.centralit.api.model.Parceiro;
import br.com.centralit.api.model.Pessoa;
import br.com.centralit.api.model.PessoaFisica;
import br.com.centralit.api.model.PessoaJuridica;
import br.com.centralit.api.model.Portador;
import br.com.centralit.api.model.Telefone;
import br.com.centralit.api.service.ClienteService;
import br.com.centralit.api.service.ColaboradorService;
import br.com.centralit.api.service.ContatoService;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.EnderecoService;
import br.com.centralit.api.service.FornecedorObservacaoService;
import br.com.centralit.api.service.FornecedorRamoAtividadeService;
import br.com.centralit.api.service.FornecedorService;
import br.com.centralit.api.service.FuncionarioService;
import br.com.centralit.api.service.ModuloService;
import br.com.centralit.api.service.OrgaoExternoService;
import br.com.centralit.api.service.ParceiroService;
import br.com.centralit.api.service.PessoaFisicaService;
import br.com.centralit.api.service.PessoaJuridicaService;
import br.com.centralit.api.service.PessoaService;
import br.com.centralit.api.service.PortadorService;
import br.com.centralit.api.service.SeguradoraService;
import br.com.centralit.api.service.TelefoneService;
import br.com.centralit.api.viewHelper.PessoaVH;
import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilColecao;
import br.com.centralit.framework.util.UtilObjeto;

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
 * <b>Title: </b>PessoaServiceImp
 * </p>
 *
 * <p>
 * <b>Description: </b>
 * </p>
 *
 * @since 28/11/2014 - 11:46:21
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Service("pesssoaService")
public class PessoaServiceImpl extends GenericServiceImpl<Pessoa, Long> implements PessoaService {

	/** Atributo pessoaDao. */
	private PessoaDao pessoaDao;

	/** Atributo pessoaFisicaService. */
	@Autowired
	private PessoaFisicaService pessoaFisicaService;

	/** Atributo pessoaJuridicaDao. */
	@Autowired
	private PessoaJuridicaService pessoaJuridicaService;

	/** Atributo colaboradorService. */
	@Autowired
	private ColaboradorService colaboradorService;

	/** Atributo orgaoExternoService. */
	@Autowired
	private OrgaoExternoService orgaoExternoService;

	/** Atributo portadorService. */
	@Autowired
	private PortadorService portadorService;

	@Autowired
	private FornecedorService fornecedorService;

	/** Atributo dominioService. */
	@Autowired
	private DominioService dominioService;

	/** Atributo parceiroService. */
	@Autowired
	private ParceiroService parceiroService;

	/** Atributo fornceFornecedorObservacaoService. */
	@Autowired
	private FornecedorObservacaoService fornecedorObservacaoService;

	/** Atributo fornecedorRamoAtividadeService. */
	@Autowired
	private FornecedorRamoAtividadeService fornecedorRamoAtividadeService;

	/** Atributo contatoService. */
	@Autowired
	private ContatoService contatoService;

	/** Atributo enderecoService. */
	@Autowired
	private EnderecoService enderecoService;

	/** Atributo telefoneService. */
	@Autowired
	private TelefoneService telefoneService;

	/** Atributo seguradoraService. */
	@Autowired
	private SeguradoraService seguradoraService;

	/** Atributo clienteService. */
	@Autowired
	private ClienteService clienteService;

	/** Atributo funcionarioService. */
	@Autowired
	private FuncionarioService funcionarioService;

	@Autowired
	private ModuloService moduloService;

	@Autowired
	private HttpServletRequest request;

	/** Constante VALIDACAO_PESSOA_JURIDICA_COLABORADOR. */
	private static final String VALIDACAO_PESSOA_JURIDICA_COLABORADOR = "VALIDACAO.ERRO_PESSOA_JURIDICA_COLABORADOR";

	/** Constante VINCULACAO_USUARIO. */
	private static final String VINCULACAO_USUARIO = "VALIDACAO.ERRO_VINCULACAO_USUARIO";

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	@Autowired
	public PessoaServiceImpl( PessoaDao pessoaDao, @Qualifier("pessoaValidator") Validator validator ) {

		this.dao = pessoaDao;

		this.pessoaDao = pessoaDao;

		this.validator = validator;

	}

	@Override
	public Pessoa getReference(Long id) {

		Pessoa pessoa = super.getReference(id);

		pessoa.setParceiros(this.parceiroService.findPorIdPessoa(id));

		pessoa.setEnderecos(this.enderecoService.findPorIdPessoa(id));

		pessoa.setContatos(this.contatoService.findPorIdPessoa(id));

		pessoa.setTelefones(this.telefoneService.findPorIdPessoa(id));

		return pessoa;
	}

	/**
	 * Método responsável salvar <code>Pessoa</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param entity
	 */
	@Override
	public Pessoa save(PessoaVH pessoaVH) {

		this.validarEntidade(pessoaVH.getPessoa(), this.validator);

		this.validarUsuario(pessoaVH.getPessoa());

		this.montarVinculos(pessoaVH);

		pessoaVH.setPessoa(super.save(pessoaVH.getPessoa()));

		this.executarSalvarParceiro(pessoaVH);

		pessoaVH.getPessoa().setParceiros(this.getParceiroService().findPorIdPessoa(pessoaVH.getPessoa().getId()));

		return pessoaVH.getPessoa();
	}

	/**
	 * Método responsável atualizar <code>Pessoa</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param entity
	 */
	@Override
	public Pessoa merge(PessoaVH pessoaVH) {

		this.validarEntidade(pessoaVH.getPessoa(), this.validator);

		this.validarUsuario(pessoaVH.getPessoa());

		this.montarVinculos(pessoaVH);

		pessoaVH.setPessoa(super.merge(pessoaVH.getPessoa()));

		this.executarSalvarParceiro(pessoaVH);

		pessoaVH.getPessoa().setParceiros(this.getParceiroService().findPorIdPessoa(pessoaVH.getPessoa().getId()));

		return pessoaVH.getPessoa();
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por validar o relacionamento entre Pessoa e Usuario
	 *
	 * @author carlos.alberto
	 *
	 * @param pessoaVH
	 */
	private void validarUsuario(Pessoa pessoa) {

		if (UtilObjeto.isReferencia(pessoa.getUsuario())) {
			List<Pessoa> pessoas = this.pessoaDao.findByUsuario(pessoa);
			if (pessoas != null && pessoas.size() > 0) {
				throw new BusinessException(VINCULACAO_USUARIO, CodigoErro.REGRA_NEGOCIO.getValue());
			}
		}
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por redirecionar os parceiros selecionados para seu determinado service
	 *
	 * @author rogerio.costa
	 *
	 * @param pessoaVH
	 */
	private void executarSalvarParceiro(PessoaVH pessoaVH) {

		// Verifica se a lista de dominioTipo parceiro não está vazia
		if (!UtilColecao.isVazio(pessoaVH.getDominiosTipoParceiroSelecionados())) {

			// Percorre a lista de dominioTipoParceiro para encontrar o tipo de dominio selecionado pelo usuario.
			for (Dominio dominioTipoParceiro : pessoaVH.getDominiosTipoParceiroSelecionados()) {

				// Verifica se o dominio selecionado é de parceiro colaborador
				if (dominioTipoParceiro.getCodigo().equals(Dominio.TIPO_PARCEIRO_COLABORADOR)) {

					pessoaVH.getColaborador().setPessoa(pessoaVH.getPessoa());

					if (UtilObjeto.isReferencia(pessoaVH.getColaborador().getId())) {

						this.getColaboradorService().merge(pessoaVH.getColaborador());

					} else {

						this.getColaboradorService().save(pessoaVH.getColaborador());
					}

					// Verifica se o dominio selecionado é de parceiro orgão externo
				} else if (dominioTipoParceiro.getCodigo().equals(Dominio.TIPO_PARCEIRO_ORGAO_EXTERNO)) {

					pessoaVH.getOrgaoExterno().setPessoa(pessoaVH.getPessoa());

					if (UtilObjeto.isReferencia(pessoaVH.getOrgaoExterno().getId())) {

						this.getOrgaoExternoService().merge(pessoaVH.getOrgaoExterno());
					} else {

						this.getOrgaoExternoService().save(pessoaVH.getOrgaoExterno());
					}

					// Verifica se o dominio selecionado é o portador
				} else if (dominioTipoParceiro.getCodigo().equals(Dominio.TIPO_PARCEIRO_PORTADOR)) {

					pessoaVH.getPortador().setPessoa(pessoaVH.getPessoa());

					if (UtilObjeto.isReferencia(pessoaVH.getPortador().getId())) {

						this.getPortadorService().merge(pessoaVH.getPortador());

					} else {

						this.getPortadorService().save(pessoaVH.getPortador());
					}

					// Verifica se o dominio selecionado é de parceiro fornecedor
				} else if (dominioTipoParceiro.getCodigo().equals(Dominio.TIPO_PARCEIRO_FORNECEDOR)) {

					pessoaVH.getFornecedor().setPessoa(pessoaVH.getPessoa());

					if (UtilObjeto.isReferencia(pessoaVH.getFornecedor().getId())) {

						this.getFornecedorService().merge(pessoaVH.getFornecedor());

					} else {

						this.getFornecedorService().save(pessoaVH.getFornecedor());
					}

					// Verifica se o dominio selecionado é de parceiro Seguradora
				} else if (dominioTipoParceiro.getCodigo().equals(Dominio.TIPO_PARCEIRO_SEGURADORA)) {

					pessoaVH.getSeguradora().setPessoa(pessoaVH.getPessoa());

					if (UtilObjeto.isReferencia(pessoaVH.getSeguradora().getId())) {

						this.getSeguradoraService().merge(pessoaVH.getSeguradora());

					} else {

						this.getSeguradoraService().save(pessoaVH.getSeguradora());
					}

					// Verifica se o dominio selecionado é de parceiro Cliente
				}else if (dominioTipoParceiro.getCodigo().equals(Dominio.TIPO_PARCEIRO_CLIENTE)) {

					pessoaVH.getCliente().setPessoa(pessoaVH.getPessoa());

					if (UtilObjeto.isReferencia(pessoaVH.getCliente().getId())) {

						this.getClienteService().merge(pessoaVH.getCliente());

					} else {

						this.getClienteService().save(pessoaVH.getCliente());
					}

					// Verifica se o dominio selecionado é de parceiro Funcionario
				}else if (dominioTipoParceiro.getCodigo().equals(Dominio.TIPO_PARCEIRO_FUNCIONARIO)) {

					pessoaVH.getFuncionario().setPessoa(pessoaVH.getPessoa());

					if (UtilObjeto.isReferencia(pessoaVH.getFuncionario().getId())) {

						this.getFuncionarioService().merge(pessoaVH.getFuncionario());

					} else {

						this.getFuncionarioService().save(pessoaVH.getFuncionario());
					}

				}

			}

		}

	}

	/**
	 * Método responsável por montar os vinculos da pessoa
	 *
	 * @author rogerio.costa
	 *
	 * @param entity
	 */
	private void montarVinculos(PessoaVH pessoaVH) {

		this.montarVinculoTipoPessoa(pessoaVH);

		// Verifica se o id da pessoa não é null
		if (UtilObjeto.isReferencia(pessoaVH.getPessoa().getId())) {

			pessoaVH.getPessoa().setParceiros(this.getParceiroService().findPorIdPessoa(pessoaVH.getPessoa().getId()));
		}

		// Percorre a lista de telefone para setar a referencia da pessoa
		for (Telefone telefone : pessoaVH.getPessoa().getTelefones()) {

			telefone.setPessoa(pessoaVH.getPessoa());

		}
		// Percorre a lista de endereco para setar a referencia da pessoa
		for (Endereco endereco : pessoaVH.getPessoa().getEnderecos()) {

			endereco.setPessoa(pessoaVH.getPessoa());

		}
		// Percorre a lista de contatos para setar a referencia de pessoa
		for (Contato contato : pessoaVH.getPessoa().getContatos()) {

			contato.setPessoa(pessoaVH.getPessoa());

		}
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por
	 *
	 * @author rogerio.costa
	 *
	 * @param entity
	 */
	private void montarVinculoTipoPessoa(PessoaVH pessoaVH) {

		// Verifica se o codigo do dominioPessoa não é null
		if (UtilObjeto.isReferencia(pessoaVH.getPessoa().getDominioPessoa().getCodigo())) {

			pessoaVH.getPessoa().setDominioPessoa(this.getDominioService().findByChaveAndCodigo(Dominio.TIPO_PESSOA, pessoaVH.getPessoa().getDominioPessoa().getCodigo()));

			// Verifica se o dominioTipoPessoa selecionado é do tipo PessoaFisica.
			if (pessoaVH.getPessoa().getDominioPessoa().getCodigo().equals(Dominio.TIPO_PESSOA_FISICA)) {

				this.resolveTransientePessoaFisica(pessoaVH);

			} else {

				this.resolveTransientePessoaJuridica(pessoaVH);

			}
		}
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por
	 *
	 * @author rogerio.costa
	 *
	 * @param entity
	 */
	private void resolveTransientePessoaFisica(PessoaVH pessoaVH) {

		pessoaVH.getPessoa().setPessoaJuridica(null);

		pessoaVH.getPessoa().getPessoaFisica().setPessoa(pessoaVH.getPessoa());

		// Verifica se o dominio sexo foi selecionado
		if (UtilObjeto.isReferencia(pessoaVH.getPessoa().getPessoaFisica().getDominioSexo()) && UtilObjeto.isReferencia(pessoaVH.getPessoa().getPessoaFisica().getDominioSexo().getId())) {

			pessoaVH.getPessoa().getPessoaFisica().setDominioSexo(this.dominioService.getReference(pessoaVH.getPessoa().getPessoaFisica().getDominioSexo().getId()));
		}
		// Verifica se o dominio estado civil foi selecionado
		if (UtilObjeto.isReferencia(pessoaVH.getPessoa().getPessoaFisica().getDominioEstadoCivil()) && UtilObjeto.isReferencia(pessoaVH.getPessoa().getPessoaFisica().getDominioEstadoCivil().getId())) {

			pessoaVH.getPessoa().getPessoaFisica().setDominioEstadoCivil(this.dominioService.getReference(pessoaVH.getPessoa().getPessoaFisica().getDominioEstadoCivil().getId()));
		}

		pessoaVH.getPessoa().setPessoaFisica(UtilObjeto.isReferencia(pessoaVH.getPessoa().getPessoaFisica()) ? pessoaVH.getPessoa().getPessoaFisica() : new PessoaFisica());

		this.pessoaFisicaService.validarDados(pessoaVH.getPessoa().getPessoaFisica());

	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por
	 *
	 * @author rogerio.costa
	 *
	 * @param entity
	 */
	private void resolveTransientePessoaJuridica(PessoaVH pessoaVH) {

		pessoaVH.getPessoa().setPessoaFisica(null);

		// Verifica se a pessoaJuridica contem um parceiro do tipo colaborador, se existir lança uma BusinessException para o usuario.
		if (this.isContemParceiroColaborador(pessoaVH)) {

			throw new BusinessException(VALIDACAO_PESSOA_JURIDICA_COLABORADOR, CodigoErro.REGRA_NEGOCIO.getValue());

		}
		// Verifica se a abrangencia foi selecionado
		if (UtilObjeto.isReferencia(pessoaVH.getPessoa().getPessoaJuridica().getDominioAbrangencia()) && UtilObjeto.isReferencia(pessoaVH.getPessoa().getPessoaJuridica().getDominioAbrangencia().getId())) {

			pessoaVH.getPessoa().getPessoaJuridica().setDominioAbrangencia(this.getDominioService().getReference(pessoaVH.getPessoa().getPessoaJuridica().getDominioAbrangencia().getId()));

		}

		// Verifica se o porte foi selecionado
		if (UtilObjeto.isReferencia(pessoaVH.getPessoa().getPessoaJuridica().getDominioPorte()) && UtilObjeto.isReferencia(pessoaVH.getPessoa().getPessoaJuridica().getDominioPorte().getId())) {

			pessoaVH.getPessoa().getPessoaJuridica().setDominioPorte(this.getDominioService().getReference(pessoaVH.getPessoa().getPessoaJuridica().getDominioPorte().getId()));
		}

		pessoaVH.getPessoa().getPessoaJuridica().setPessoa(pessoaVH.getPessoa());

		pessoaVH.getPessoa().setPessoaJuridica(UtilObjeto.isReferencia(pessoaVH.getPessoa().getPessoaJuridica()) ? pessoaVH.getPessoa().getPessoaJuridica() : new PessoaJuridica());

		this.pessoaJuridicaService.validarDados(pessoaVH.getPessoa().getPessoaJuridica());
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por
	 *
	 * @author rogerio.costa
	 *
	 * @param pessoaVH
	 * @return
	 */
	private boolean isContemParceiroColaborador(PessoaVH pessoaVH) {

		// Verifica se a lista de dominioTipoParceiro não está vazia
		if (!UtilColecao.isVazio(pessoaVH.getDominiosTipoParceiroSelecionados())) {

			// Percorre a lista de dominioParceiro
			for (Dominio dominioParceiroSelecionado : pessoaVH.getDominiosTipoParceiroSelecionados()) {

				// Verifica se o dominioTipoParceiro é um colaborador
				if (Dominio.TIPO_PARCEIRO_COLABORADOR.equals(dominioParceiroSelecionado.getCodigo())) {

					return true;

				}
			}

		}

		return false;

	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por remover pessoa
	 *
	 * Juliana: Alterei o método para que excluisse tambem os parceiros relacionados aquela pessoa, pois eles continuavam aparecendo para serem usados no sistema mesmo apos excluir a pessoa.
	 *
	 *
	 * Luís César: Alteração realizada para excluir pessoa física ou jurídica mesmo não tendo parceiros vinculados.
	 *
	 * @author rogerio.costa, juliana.barbosa, luis.camargo
	 *
	 * @param id
	 *
	 * @return boolean
	 */
	public boolean removeById(Long id) {

		Pessoa pessoa = (Pessoa) this.getPessoaDao().getReference(id);

		this.verificarRegraRemocaoPessoa(id);

		if (!UtilColecao.isVazio(pessoa.getParceiros())) {

			for (Parceiro parceiro : pessoa.getParceiros()) {

				this.parceiroService.removeById(parceiro.getId());
			}
		}

		if (UtilObjeto.isReferencia(pessoa.getPessoaFisica())) {

			this.pessoaFisicaService.remove(pessoa.getPessoaFisica());
		} else {

			this.pessoaJuridicaService.remove(pessoa.getPessoaJuridica());
		}

		return super.removeById(id);
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por
	 *
	 * @author rogerio.costa
	 *
	 * @param pessoaVH
	 */
	public void verificarRegraRemocaoPessoa(Long id) {

		Pessoa pessoa = (Pessoa) this.getPessoaDao().getReference(id);

//		Modulo moduloPatrimonio = moduloService.moduloEstaAtivo(ClientRest.MODULO_PATRIMONIO);
//
//		ClientRest clientRestPatrimonio = new ClientRest(request, moduloPatrimonio);
//
//		clientRestPatrimonio.addParametro("id", id.toString());

		// Percorre a lista de Parceiros
		for (Parceiro parceiro : pessoa.getParceiros()) {

			// Verifica se o parceiro é um colaborador
			if (parceiro instanceof Colaborador) {

//				if (UtilObjeto.isReferencia(moduloPatrimonio)) {
//
//					this.validarExisteVinculoColaboradorPatrimonio(clientRestPatrimonio);
//
//				}

				// Verifica se o parceiro é um orgãoExterno
			} else if (parceiro instanceof OrgaoExterno) {

				// Verifica se o parceiro é um portator
			} else if (parceiro instanceof Portador) {

				// Verifica se o parceiro é um fornecedor
			} else if (parceiro instanceof Fornecedor) {

			}

		}

	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por remover a entidade <code>Colaborador</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param id
	 *
	 * @return boolean
	 */
	public boolean removeColaborador(Long id) {

		return this.getColaboradorService().removeById(id);
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por remover a entidade <code>OrgaoExterno</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param id
	 *
	 * @return boolean
	 */
	public boolean removeOrgaoExterno(Long id) {

		return this.getOrgaoExternoService().removeById(id);
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por remover a entidade <code>Portador</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param id
	 *
	 * @return boolean
	 */
	public boolean removePortador(Long id) {

		return this.getPortadorService().removeById(id);
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por remover a entidade <code>Fornecedor</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param id
	 *
	 * @return boolean
	 */
	public boolean removeFornecedor(Long id) {

		return this.getFornecedorService().removeById(id);

	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">595</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por remover um parceiro seguradora
	 *
	 * @author juliana.barbosa
	 *
	 * @param id
	 * @return
	 */
	public boolean removeSeguradora(Long id) {

		return this.getSeguradoraService().removeById(id);

	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por remover a entidade <code>Cliente</code>
	 *
	 * @author iago.almeida
	 *
	 * @param id
	 *
	 * @return boolean
	 */
	public boolean removeCliente(Long id) {

		return this.getClienteService().removeById(id);

	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por remover a entidade <code>Funcionario</code>
	 *
	 * @author iago.almeida
	 *
	 * @param id
	 *
	 * @return boolean
	 */
	public boolean removeFuncionario(Long id) {

		return this.getFuncionarioService().removeById(id);

	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por remover a entidade<code>FornecedorObservacao</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param id
	 *
	 * @return boolean
	 */
	public boolean removeFornecedorObservacao(Long id) {

		return this.getFornecedorObservacaoService().removeById(id);

	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por
	 *
	 * @author rogerio.costa
	 *
	 * @param id
	 * @return
	 */
	public boolean removeFornecedorRamosAtividade(Long id) {

		return this.getFornecedorRamoAtividadeService().removeById(id);
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por
	 *
	 * @author rogerio.costa
	 *
	 * @param id
	 * @return
	 */
	public boolean removeContato(Long id) {

		return this.getContatoService().removeById(id);
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por listar a entidade <code>Colaborador</code> através do nome
	 *
	 * @author rogerio.costa
	 *
	 * @param nome
	 *
	 * @return Collection<Colaborador>
	 */
	public Collection<Colaborador> findColaboradorPorNome(String nome) {

		return this.getColaboradorService().findPorNome(nome);
	}

	/**
	 * Método responsável por buscar funcionario por nome <code>Funcionario</code>
	 *
	 * @author iago.almeida
	 *
	 * @param nome
	 *
	 * @return Collection<Funcionario>
	 */
	public Collection<Funcionario> findFuncionarioPorNome(String nome) {

		return this.getFuncionarioService().findPorNome(nome);
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por listar a entidade <code>Colaborador</code> através do nome e organizacao
	 *
	 * @author rogerio.costa
	 *
	 * @param nome
	 *
	 * @return Collection<Colaborador>
	 */
	@Override
	public Collection<Colaborador> findColaboradorPorNomeAndOrganizacao(String nome, Long idOrganizacao) {

		return this.getColaboradorService().findPorNomeAndOrganizacao(nome, idOrganizacao);

	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por listar colaboradores por nome na organização do usuário logado
	 *
	 * @author luis.camargo
	 *
	 * @param nome
	 *
	 * @return Collection<Colaborador>
	 */
	@Override
	public Collection<Colaborador> findColaboradorPorNomeNaOrganizacaoUsuarioLogado(String nome) {

		return this.findColaboradorPorNomeAndOrganizacao(nome, getUsuario().getOrganizacao().getId());
	}

	/**
	 * Método responsável por listar funcionarios por nome na organização do usuário logado
	 *
	 * @author iago
	 *
	 * @param nome
	 *
	 * @return Collection<Funcionario>
	 */
	@Override
	public Collection<Funcionario> findFuncionarioPorNomeNaOrganizacaoUsuarioLogado(String nome) {

		return this.getFuncionarioService().findPorNomeAndOrganizacao(nome, getUsuario().getOrganizacao().getId());
	}

	/**
	 *
	 * Método responsável por buscar uma pessoa que seja um usuário no sistema
	 *
	 * @author iago.almeida
	 *
	 * @param nome
	 * @return Collection<Colaborador>
	 */
	@Override
	public Collection<Colaborador> findPessoaColaboradorUsuarioPorNome(String nome) {

		return this.getColaboradorService().findPessoaColaboradorUsuarioPorNome(nome);
	}

	/**
	 * Retorna o valor do atributo <code>pessoaDao</code>
	 *
	 * @return <code>PessoaDao</code>
	 */
	public PessoaDao getPessoaDao() {

		return pessoaDao;
	}

	/**
	 * Retorna o valor do atributo <code>pessoaJuridicaService</code>
	 *
	 * @return <code>PessoaJuridicaService</code>
	 */
	public PessoaJuridicaService getPessoaJuridicaService() {

		return pessoaJuridicaService;
	}

	/**
	 * Retorna o valor do atributo <code>pessoaFisicaService</code>
	 *
	 * @return <code>PessoaFisicaService</code>
	 */
	public PessoaFisicaService getPessoaFisicaService() {

		return pessoaFisicaService;
	}

	/**
	 * Retorna o valor do atributo <code>colaboradorService</code>
	 *
	 * @return <code>ColaboradorService</code>
	 */
	public ColaboradorService getColaboradorService() {

		return colaboradorService;
	}

	/**
	 * Retorna o valor do atributo <code>orgaoExternoService</code>
	 *
	 * @return <code>OrgaoExternoService</code>
	 */
	public OrgaoExternoService getOrgaoExternoService() {

		return orgaoExternoService;
	}

	/**
	 * Retorna o valor do atributo <code>portadorService</code>
	 *
	 * @return <code>PortadorService</code>
	 */
	public PortadorService getPortadorService() {

		return portadorService;
	}

	/**
	 * Retorna o valor do atributo <code>fornecedorService</code>
	 *
	 * @return <code>FornecedorService</code>
	 */
	public FornecedorService getFornecedorService() {

		return fornecedorService;
	}

	/**
	 * Retorna o valor do atributo <code>dominioService</code>
	 *
	 * @return <code>DominioService</code>
	 */
	public DominioService getDominioService() {

		return dominioService;
	}

	/**
	 * Retorna o valor do atributo <code>parceiroService</code>
	 *
	 * @return <code>ParceiroService</code>
	 */
	public ParceiroService getParceiroService() {

		return parceiroService;
	}

	/**
	 * Retorna o valor do atributo <code>fornecedorObservacaoService</code>
	 *
	 * @return <code>FornecedorObservacaoService</code>
	 */
	public FornecedorObservacaoService getFornecedorObservacaoService() {

		return fornecedorObservacaoService;
	}

	/**
	 * Retorna o valor do atributo <code>fornecedorRamoAtividadeService</code>
	 *
	 * @return <code>FornecedorRamoAtividadeService</code>
	 */
	public FornecedorRamoAtividadeService getFornecedorRamoAtividadeService() {

		return fornecedorRamoAtividadeService;
	}

	/**
	 * Retorna o valor do atributo <code>contatoService</code>
	 *
	 * @return <code>ContatoService</code>
	 */
	public ContatoService getContatoService() {

		return contatoService;
	}

	public SeguradoraService getSeguradoraService() {

		return seguradoraService;
	}

	public void setSeguradoraService(SeguradoraService seguradoraService) {

		this.seguradoraService = seguradoraService;
	}

	public ClienteService getClienteService() {

		return clienteService;
	}

	public FuncionarioService getFuncionarioService() {

		return funcionarioService;
	}

	@Override
	public Pessoa bloquearPessoa(Pessoa pessoa) {

		Pessoa pessoaBloqueio = (Pessoa) this.getPessoaDao().getReference(pessoa.getId());

		pessoaBloqueio.setDataBloqueio(pessoa.getDataBloqueio());

		return super.merge(pessoaBloqueio);

	}
}
