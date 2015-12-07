package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.RegiaoDao;
import br.com.centralit.api.model.Pais;
import br.com.centralit.api.model.Regiao;
import br.com.centralit.api.service.EstadoService;
import br.com.centralit.api.service.RegiaoService;
import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.CodigoErro;
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
 * @since 03/01/2015 - 15:37:49
 * 
 * @version 1.0.0
 * 
 * @author iago.almeida
 * 
 */
@Service("regiaoService")
public class RegiaoServiceImpl extends GenericServiceImpl<Regiao, Long> implements RegiaoService {

	/** Atributo regiaoDao. */
	private RegiaoDao regiaoDao;

	/** Atributo estadoService. */
	@Autowired
	private EstadoService estadoService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param regiaoDao
	 * @param validator
	 */
	@Autowired
	public RegiaoServiceImpl( RegiaoDao regiaoDao, @Qualifier("regiaoValidator") Validator validator ) {

		this.dao = regiaoDao;

		this.regiaoDao = regiaoDao;

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
	public Regiao save(Regiao regiao) {

		// VALIDA CAMPO OBRIGATÓRIO DA ENTIDADE
		if (UtilObjeto.isReferencia(this.validator)) {

			this.validarEntidade(regiao, this.validator);
		}

		// VERIFICA SE O REGIÃO JÁ TEM CÓDIGO, SENÃO GERA E SALVA ENTIDADE
		if (regiao.getCodigo() != null) {

			return super.save(regiao);

		} else {

			Regiao entitySaved = super.save(regiao);

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
	 * Método responsável por listar a entidade<code>Regiao</code>
	 * 
	 * @author rogerio.costa
	 * 
	 * @param pais
	 * @param nome
	 * @return Collection<Regiao>
	 */
	public Collection<Regiao> listarRegiao(Pais pais, String nome) {

		return this.regiaoDao.listarRegiao(pais, nome);
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
	 * @param idRegiao
	 * @return
	 */
	@Override
	public boolean removeById(Long idRegiao) {

		if (this.estadoService.existeEstadoVinculadoARegiao(idRegiao)) {
			throw new BusinessException("MSG.MN020", CodigoErro.REGRA_NEGOCIO.getValue());
		}

		return this.dao.removeById(idRegiao);
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
	 * Método responsável por atualizar regiao
	 * 
	 * @author iago.almeida
	 * 
	 * @param entity
	 * @return <Regiao>
	 */
	@Override
	public Regiao merge(Regiao entity) {

		Regiao regiao = (Regiao) regiaoDao.getReference(entity.getId());
		entity.setListaEstado(regiao.getListaEstado());
		return super.merge(entity);
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
	 * Método responsável por verificar se o pais a ser excluido é utilizado por regiao
	 * 
	 * @author iago.almeida
	 * 
	 * @return boolean
	 */
	@Override
	public boolean existeRegiaoVinculadoAoPais(Long idPais) {

		return this.regiaoDao.existeRegiaoVinculadoAoPais(idPais);
	}
}
