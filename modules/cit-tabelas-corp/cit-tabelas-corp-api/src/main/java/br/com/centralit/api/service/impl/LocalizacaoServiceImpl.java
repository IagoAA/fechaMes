package br.com.centralit.api.service.impl;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.LocalizacaoDao;
import br.com.centralit.api.model.Endereco;
import br.com.centralit.api.model.Localizacao;
import br.com.centralit.api.service.EnderecoService;
import br.com.centralit.api.service.LocalizacaoService;
import br.com.centralit.api.service.ModuloService;
import br.com.centralit.api.service.UsuarioService;
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
 * @since 24/12/2014 - 11:35:12
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *
 */
@Service("localizacaoService")
public class LocalizacaoServiceImpl extends GenericServiceImpl<Localizacao, Long> implements LocalizacaoService {

	/** Atributo localizacaoDao. */
	private LocalizacaoDao localizacaoDao;

	/** Atributo enderecoService. */
	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ModuloService moduloService;

	@Autowired
	private HttpServletRequest request;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param localizacaoDao
	 * @param validator
	 */
	@Autowired
	public LocalizacaoServiceImpl( LocalizacaoDao localizacaoDao, @Qualifier("localizacaoValidator") Validator validator ) {

		this.localizacaoDao = localizacaoDao;

		this.dao = localizacaoDao;

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
	 * Método responsável por salvar localização
	 *
	 * @author iago.almeida
	 *
	 * @param localizacao
	 * @return <Localizacao>
	 */
	@Override
	public Localizacao save(Localizacao entity) {

		Endereco endereco = entity.getEndereco();
		if (!UtilObjeto.isReferencia(endereco.getId())) {
			endereco = enderecoService.save(endereco);
			entity.setEndereco(endereco);
		}

		// VALIDA CAMPO OBRIGATÓRIO DA ENTIDADE
		if (UtilObjeto.isReferencia(this.validator)) {

			this.validarEntidade(entity, this.validator);
		}

		// VERIFICA SE O LOCALIZACAO JÁ TEM CÓDIGO, SENÃO GERA E SALVA ENTIDADE
		if (entity.getCodigo() != null) {

			return super.save(entity);

		} else {

			Localizacao entitySaved = super.save(entity);

			entitySaved.setCodigo(Long.valueOf(entitySaved.getId().toString().hashCode()).toString());

			return entitySaved;

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
	 * Método responsável por listar localizações por nome
	 *
	 * @author iago.almeida
	 *
	 * @param nome
	 * @return
	 */
	@Override
	public Collection<Localizacao> findLocalizacaoPorNome(String nome) {

		return localizacaoDao.findLocalizacaoPorNome(nome);
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
	 * Método responsável por listar Localizacao por nome e id da organizacao
	 *
	 * @author iago.almeida
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @return
	 */
	@Override
	public List<Localizacao> listarLocalizacaoPorOrganizacao(String nome, Long idOrganizacao) {

		return this.localizacaoDao.listarLocalizacaoPorOrganizacao(nome, idOrganizacao);
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
	 * Método responsável por inativar a localizacao setando dataInativo.
	 *
	 * @author iago.almeida
	 *
	 * @param localizacaoId
	 * @return
	 */
	@Override
	public boolean removeById(Long localizacaoId) {

		return this.dao.removeById(localizacaoId);
	}
}
