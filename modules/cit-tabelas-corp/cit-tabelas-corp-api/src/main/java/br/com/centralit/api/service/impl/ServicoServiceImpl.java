package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.ServicoDao;
import br.com.centralit.api.model.Servico;
import br.com.centralit.api.service.ServicoService;
import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilObjeto;

@Service("servicoService")
public class ServicoServiceImpl extends GenericServiceImpl<Servico, Long> implements ServicoService {

	/** Atributo pessoaDao. */
	private ServicoDao servicoDao;

	@Autowired
	public ServicoServiceImpl(ServicoDao servicoDao, @Qualifier("servicoValidator") Validator validator) {
		this.dao = servicoDao;
		this.servicoDao = servicoDao;
		this.validator = validator;
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por listar a entidade<code>Servico</code>
	 *
	 * @author iago.almeida
	 *
	 * @param nome
	 * @return Collection<Servico>
	 */
	public Collection<Servico> listarServico(String nome) {

		return this.servicoDao.listarServico(nome);
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">563</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por salvar servico
	 *
	 * @author iago.almeida
	 *
	 * @param servico
	 * @return <Servico>
	 */
	@Override
	public Servico save(Servico servico) {

		// VALIDA CAMPO OBRIGATÓRIO DA ENTIDADE
		if (UtilObjeto.isReferencia(this.validator)) {

			this.validarEntidade(servico, this.validator);
		}

		validarExistenciaServicoMesmoNome(servico);

		// VERIFICA SE O SERVICO JÁ TEM CÓDIGO, SENÃO GERA E SALVA ENTIDADE
		if(servico.getCodigo() != null){

			return super.save(servico);

		}else{

			Servico entitySaved = super.save(servico);

			entitySaved.setCodigo(Long.valueOf(entitySaved.getId().toString().hashCode()).toString());

			return entitySaved;

		}
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por inativar o servico setando dataInativo.
	 *
	 * @author iago.almeida
	 *
	 * @param idServico
	 * @return
	 */
/*	@Override
	public boolean removeById(Long idServico) {

		if(this.regiaoService.existeRegiaoVinculadoAoServico(idServico)){
			throw new BusinessException("MSG.MN020", CodigoErro.REGRA_NEGOCIO.getValue());
		}

		return this.dao.removeById(idServico);
	}*/


	/**
	 *
	 * Método responsável por por listar a entidade <code>Servico</code> através do nome e organizacao
	 *
	 * @author iago
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @return
	 */
	public Collection<Servico> findPorNomeAndOrganizacao(String nome, Long organizacaoId) {

		return this.servicoDao.findPorNomeAndOrganizacao(nome, organizacaoId);
	}

	/**
	 * Método responsável por atualizar país
	 *
	 * @author iago.almeida
	 *
	 * @param servico
	 * @return <Servico>
	 */
	@Override
	public Servico merge(Servico entity) {
		validarExistenciaServicoMesmoNome(entity);
		return super.merge(entity);
	}

	/**
	 * Método responsável por verificar se existe um país já cadastrado com o mesmo nome.
	 *
	 * @author iago.almeida
	 *
	 * @param servico
	 * @throws BusinessException
	 */
	private void validarExistenciaServicoMesmoNome(Servico servico) {

		boolean hasServicoMesmoNome = this.servicoDao.existeServicoMesmoNome(servico);

		if (hasServicoMesmoNome) {
			throw new BusinessException("MSG.SERVICO_DUPLICADO", CodigoErro.REGRA_NEGOCIO.getValue());
		}
	}

}
