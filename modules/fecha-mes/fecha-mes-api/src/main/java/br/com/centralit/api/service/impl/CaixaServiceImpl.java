package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.CaixaDao;
import br.com.centralit.api.model.Caixa;
import br.com.centralit.api.model.Luck;
import br.com.centralit.api.service.CaixaService;
import br.com.centralit.api.service.EstadoService;
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
 * @since 27/11/2015 - 15:37:49
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *
 */
@Service("caixaService")
public class CaixaServiceImpl extends GenericServiceImpl<Caixa, Long> implements CaixaService {

	/** Atributo caixaDao. */
	private CaixaDao caixaDao;

	/** Atributo estadoService. */
	@Autowired
	private EstadoService estadoService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param caixaDao
	 * @param validator
	 */
	@Autowired
	public CaixaServiceImpl( CaixaDao caixaDao, @Qualifier("caixaValidator") Validator validator ) {

		this.dao = caixaDao;

		this.caixaDao = caixaDao;

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
	 * Método responsável por salvar região
	 *
	 * @author iago.almeida
	 *
	 * @param pais
	 * @return <Pais>
	 */
	@Override
	public Caixa save(Caixa caixa) {

		// VALIDA CAMPO OBRIGATÓRIO DA ENTIDADE
		if (UtilObjeto.isReferencia(this.validator)) {

			this.validarEntidade(caixa, this.validator);
		}

		// VERIFICA SE O REGIÃO JÁ TEM CÓDIGO, SENÃO GERA E SALVA ENTIDADE
		if (caixa.getCodigo() != null) {

			return super.save(caixa);

		} else {

			Caixa entitySaved = super.save(caixa);

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
	 * Método responsável por listar a entidade<code>Caixa</code>
	 *
	 * @author iago
	 *
	 * @param luck
	 * @param nome
	 * @return Collection<Caixa>
	 */
	public Collection<Caixa> listarCaixa(Luck luck, String nome) {

		return this.caixaDao.listarCaixa(luck, nome);
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
	 * Método responsável por inativar a região setando dataInativo.
	 *
	 * @author iago.almeida
	 *
	 * @param idCaixa
	 * @return
	 */
	@Override
	public boolean removeById(Long idCaixa) {

		return this.dao.removeById(idCaixa);
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
	 * Método responsável por verificar se o luck a ser excluido é utilizado por caixa
	 *
	 * @author iago.almeida
	 *
	 * @return boolean
	 */
	@Override
	public boolean existeCaixaVinculadoAoLuck(Long idLuck) {

		return this.caixaDao.existeCaixaVinculadoAoLuck(idLuck);
	}
}
