package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.EnderecoDao;
import br.com.centralit.api.model.Bairro;
import br.com.centralit.api.model.Endereco;
import br.com.centralit.api.service.BairroService;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.EnderecoService;
import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
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
 * @since 05/12/2014 - 11:11:25
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Service("enderecoService")
public class EnderecoServiceImpl extends GenericServiceImpl<Endereco, Long> implements EnderecoService {

	/** Atributo enderecoDao. */
	private EnderecoDao enderecoDao;

	/** Atributo dominioService. */
	@Autowired
	private DominioService dominioService;

	/** Atributo bairroService. */
	@Autowired
	private BairroService bairroService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param enderecoDao
	 * @param validator
	 */
	@Autowired
	public EnderecoServiceImpl( EnderecoDao enderecoDao, @Qualifier("enderecoValidator") Validator validator ) {

		this.dao = enderecoDao;

		this.enderecoDao = enderecoDao;

		this.validator = validator;

	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">563</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por salvar endereço
	 *
	 * @author iago.almeida
	 *
	 * @param endereco
	 * @return <Endereco>
	 */
	@Override
	public Endereco save(Endereco endereco) {

		endereco.setBairro((Bairro) ( ( endereco.getBairro() != null && endereco.getBairro().getId() != null ) ? this.bairroService.getReference(endereco.getBairro().getId()) : null ));
		endereco.setCidade(( endereco.getBairro() != null && endereco.getBairro().getId() != null ) ? endereco.getBairro().getCidade() : null);
		endereco.setDominioTipoEndereco((Dominio) ( ( endereco.getDominioTipoEndereco() != null && endereco.getDominioTipoEndereco().getId() != null ) ? this.dominioService.getReference(endereco.getDominioTipoEndereco().getId()) : null ));

		// VALIDA CAMPO OBRIGATÓRIO DA ENTIDADE
		if (UtilObjeto.isReferencia(this.validator)) {

			this.validarEntidade(endereco, this.validator);
		}

		Endereco entitySaved = super.save(endereco);

		entitySaved.setCodigo(Long.valueOf(entitySaved.getId().toString().hashCode()).toString());

		return entitySaved;

	}


	/**
	 *
	 * Método responsável por atualizar o endereço
	 *
	 * @author gilberto.nery
	 *
	 * @param endereco
	 * @return <Endereco>
	 *
	 */
	@Override
	public Endereco merge(Endereco endereco) {

		endereco.setBairro((Bairro) ( ( endereco.getBairro() != null && endereco.getBairro().getId() != null ) ? this.bairroService.getReference(endereco.getBairro().getId()) : null ));
		endereco.setCidade(( endereco.getBairro() != null && endereco.getBairro().getId() != null ) ? endereco.getBairro().getCidade() : null);
		endereco.setDominioTipoEndereco((Dominio) ( ( endereco.getDominioTipoEndereco() != null && endereco.getDominioTipoEndereco().getId() != null ) ? this.dominioService.getReference(endereco.getDominioTipoEndereco().getId()) : null ));

		// VALIDA CAMPO OBRIGATÓRIO DA ENTIDADE
		if (UtilObjeto.isReferencia(this.validator)) {

			this.validarEntidade(endereco, this.validator);
		}

		return super.merge(endereco);

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
	 * Método responsável por listar a entidade <code>Endereco</code>
	 *
	 * @author iago.almeida
	 *
	 * @param nome
	 * @return Collection<Endereco>
	 */
	public Collection<Endereco> listarEndereco(String nome) {

		return enderecoDao.listarEndereco(nome);
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
	 * Método responsável por inativar o endereco setando dataInativo.
	 *
	 * @author iago.almeida
	 *
	 * @param enderecoId
	 * @return
	 */
	@Override
	public boolean removeById(Long enderecoId) {

		if(this.enderecoDao.existeEnderecoVinculadoALocalizacao(enderecoId)){

			throw new BusinessException("MSG.MN020", CodigoErro.REGRA_NEGOCIO.getValue());

		}

		return this.dao.removeById(enderecoId);
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
	 * Método responsável por listar os enderecos através do id da pessoa
	 *
	 * @author rogerio.costa
	 *
	 * @param idPessoa
	 *
	 * @return Collection<Parceiro>
	 */
	public Collection<Endereco> findPorIdPessoa(Long idPessoa) {

		return this.enderecoDao.findPorIdPessoa(idPessoa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existeBairroVinculadoAEndereco(Long bairroId) {

		return this.enderecoDao.existeBairroVinculadoAEndereco(bairroId);
	}

}
