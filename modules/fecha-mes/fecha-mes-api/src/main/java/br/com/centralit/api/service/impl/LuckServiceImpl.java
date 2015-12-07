package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.LuckDao;
import br.com.centralit.api.model.Luck;
import br.com.centralit.api.service.LuckService;
import br.com.centralit.api.service.CaixaService;
import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilObjeto;


/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p>
 *
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
 *
 * @since 26/11/2015 - 10:10:25
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *
 */

@Service("luckService")
public class LuckServiceImpl extends GenericServiceImpl<Luck, Long> implements LuckService{

	/** Atributo luckDao. */
	private LuckDao luckDao;

	/** Atributo regiaoService. */
	@Autowired
	private CaixaService caixaService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * @param luckDao
	 * @param validator
	 */
	@Autowired
	public LuckServiceImpl(LuckDao luckDao, @Qualifier("luckValidator") Validator validator) {

		this.luckDao = luckDao;

		this.dao = luckDao;

		this.validator = validator;

	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por listar a entidade<code>Luck</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param nome
	 * @return Collection<Luck>
	 */
	public Collection<Luck> listarLuck(String nome) {

		return this.luckDao.listarLuck(nome);
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">563</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por salvar luck
	 *
	 * @author iago.almeida
	 *
	 * @param luck
	 * @return <Luck>
	 */
	@Override
	public Luck save(Luck luck) {

		// VALIDA CAMPO OBRIGATÓRIO DA ENTIDADE
		if (UtilObjeto.isReferencia(this.validator)) {

			this.validarEntidade(luck, this.validator);
		}

		validarExistenciaLuckMesmoNome(luck);

		// VERIFICA SE O LUCK JÁ TEM CÓDIGO, SENÃO GERA E SALVA ENTIDADE
		if(luck.getCodigo() != null){

			return super.save(luck);

		}else{

			Luck entitySaved = super.save(luck);

			entitySaved.setCodigo(Long.valueOf(entitySaved.getId().toString().hashCode()).toString());

			return entitySaved;

		}
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por inativar o luck setando dataInativo.
	 *
	 * @author iago.almeida
	 *
	 * @param idLuck
	 * @return
	 */
	@Override
	public boolean removeById(Long idLuck) {

		if(this.caixaService.existeCaixaVinculadoAoLuck(idLuck)){
			throw new BusinessException("MSG.MN020", CodigoErro.REGRA_NEGOCIO.getValue());
		}

		return this.dao.removeById(idLuck);
	}

	/**
	 * Método responsável por atualizar país
	 *
	 * @author iago
	 *
	 * @param luck
	 * @return <Luck>
	 */
	@Override
	public Luck merge(Luck entity) {
		validarExistenciaLuckMesmoNome(entity);
		return super.merge(entity);
	}

	/**
	 * Método responsável por verificar se existe um país já cadastrado com o mesmo nome.
	 *
	 * @author oagp
	 *
	 * @param luck
	 * @throws BusinessException
	 */
	private void validarExistenciaLuckMesmoNome(Luck luck) {
		boolean hasLuckMesmoNome = this.luckDao.existeLuckMesmoNome(luck);

		if (hasLuckMesmoNome) {
			throw new BusinessException("MSG.PAIS_DUPLICADO", CodigoErro.REGRA_NEGOCIO.getValue());
		}
	}

}
