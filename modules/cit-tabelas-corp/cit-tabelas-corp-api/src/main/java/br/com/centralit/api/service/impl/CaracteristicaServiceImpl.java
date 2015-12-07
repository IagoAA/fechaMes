package br.com.centralit.api.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.CaracteristicaDao;
import br.com.centralit.api.model.Caracteristica;
import br.com.centralit.api.service.CaracteristicaService;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.ModuloService;
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
 * @since 24/12/2014 - 10:52:35
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *
 */
@Service("caracteristicaService")
public class CaracteristicaServiceImpl extends GenericServiceImpl<Caracteristica, Long> implements CaracteristicaService {

	/** Atributo dominioService. */
	@Autowired
	private DominioService dominioService;

	/** Atributo caracteristicaDao. */
	private CaracteristicaDao caracteristicaDao;

	@Autowired
	private ModuloService moduloService;

	@Autowired
	private HttpServletRequest request;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param caracteristicaDao
	 * @param validator
	 */
	@Autowired
	public CaracteristicaServiceImpl( CaracteristicaDao caracteristicaDao, @Qualifier("caracteristicaValidator") Validator validator ) {

		this.dao = caracteristicaDao;

		this.caracteristicaDao = caracteristicaDao;

		this.validator = validator;
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
	 * Método responsável por listar a entidade <code>Caracteristica</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param descricao
	 * @return Collection<Caracteristica>
	 */
	@Override
	public Collection<Caracteristica> listarCaracteristicas(String descricao) {

		Collection<Caracteristica> listaCaracteristica = this.caracteristicaDao.listarCaracteristicas(descricao);

		return UtilColecao.isVazio(listaCaracteristica) ? new ArrayList<Caracteristica>() : listaCaracteristica;
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
	 * Método responsável por salvar caracteristica
	 *
	 * @author iago.almeida
	 *
	 * @param caracteristica
	 * @return <Caracteristica>
	 */
	@Override
	public Caracteristica save(Caracteristica caracteristica) {

		caracteristica.setDominioTipoDado(this.dominioService.findByChaveAndCodigo(Dominio.TIPO_DADO, caracteristica.getDominioTipoDado().getCodigo()));
		caracteristica.setDominioTipoRestricao((Dominio) ( ( caracteristica.getDominioTipoRestricao() != null && caracteristica.getDominioTipoRestricao().getId() != null ) ? this.dominioService.getReference(caracteristica.getDominioTipoRestricao().getId()) : null ));

		// VALIDA CAMPO OBRIGATÓRIO DA ENTIDADE
		if (UtilObjeto.isReferencia(this.validator)) {

			this.validarEntidade(caracteristica, this.validator);
		}

		// VERIFICA SE A CARACTERISTICA JÁ TEM CÓDIGO, SENÃO GERA E SALVA ENTIDADE
		if (caracteristica.getCodigo() != null) {

			return super.save(caracteristica);

		} else {

			Caracteristica entitySaved = super.save(caracteristica);

			entitySaved.setCodigo(Long.valueOf(entitySaved.getId().toString().hashCode()).toString());

			return entitySaved;

		}

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
	 * Método responsável por atualizar caracteristica
	 *
	 * @author geovane.filho
	 *
	 * @param caracteristica
	 * @return <Caracteristica>
	 */
	@Override
	public Caracteristica merge(Caracteristica caracteristica) {

		caracteristica.setDominioTipoDado(this.dominioService.findByChaveAndCodigo(Dominio.TIPO_DADO, caracteristica.getDominioTipoDado().getCodigo()));
		caracteristica.setDominioTipoRestricao((Dominio) ( ( caracteristica.getDominioTipoRestricao() != null && caracteristica.getDominioTipoRestricao().getId() != null ) ? this.dominioService.getReference(caracteristica.getDominioTipoRestricao().getId()) : null ));

		// VALIDA CAMPO OBRIGATÓRIO DA ENTIDADE
		if (UtilObjeto.isReferencia(this.validator)) {

			this.validarEntidade(caracteristica, this.validator);
		}

		// VERIFICA SE A CARACTERISTICA JÁ TEM CÓDIGO, SENÃO GERA E SALVA ENTIDADE
		if (caracteristica.getCodigo() != null) {

			return super.merge(caracteristica);

		} else {

			Caracteristica entitySaved = super.merge(caracteristica);

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
	 * Método responsável por inativar a característica setando dataInativo.
	 *
	 * @author iago.almeida
	 *
	 * @param idCaracteristica
	 * @return
	 */
	@Override
	public boolean removeById(Long idCaracteristica) {

		return this.dao.removeById(idCaracteristica);
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
	 * Método responsável por listar Caracteristicas por nome e id da organizacao
	 *
	 * @author iago.almeida
	 *
	 * @param descricao
	 * @param idOrganizacao
	 * @return
	 */
	@Override
	public List<Caracteristica> listarCaracteristicasPorOrganizacao(String descricao, Long idOrganizacao) {

		return this.caracteristicaDao.listarCaracteristicasPorOrganizacao(descricao, idOrganizacao);
	}
}
