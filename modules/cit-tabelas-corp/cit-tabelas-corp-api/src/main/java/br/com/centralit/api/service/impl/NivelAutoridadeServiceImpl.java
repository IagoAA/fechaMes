package br.com.centralit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.NivelAutoridadeDao;
import br.com.centralit.api.model.NivelAutoridade;
import br.com.centralit.api.service.NivelAutoridadeGrupoService;
import br.com.centralit.api.service.NivelAutoridadeService;
import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.Usuario;
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
 * @since 22/06/2015 - 16:00:09
 *
 * @version 1.0.0
 *
 * @author lucas.ribeiro - (<a href="mailto:lucas.ribeiro@centralit.com.br">lucas.ribeiro@centralit.com.br</a>)
 *
 */
@Service("nivelAutoridadeService")
public class NivelAutoridadeServiceImpl extends GenericServiceImpl<NivelAutoridade, Long> implements NivelAutoridadeService {

	/** Constante VALIDACAO_NOME_IGUAL. */
	private static final String VALIDACAO_NOME_IGUAL = "VALIDACAO.NIVEL_AUTORIDADE_NOME_IGUAL";

	private static final String VALIDACAO_HIERARQUIA = "MSG.HIERARQUIA_UNICA";

	/** Atributo nivelAutoridadegrupoService. */
	@Autowired
	private NivelAutoridadeGrupoService nivelAutoridadegrupoService;

	private NivelAutoridadeDao nivelAutoridadeDao;

	@Autowired
	public NivelAutoridadeServiceImpl( NivelAutoridadeDao nivelAutoridadeDao, @Qualifier("nivelAutoridadeValidator") Validator validator ) {

		this.dao = nivelAutoridadeDao;
		this.nivelAutoridadeDao = nivelAutoridadeDao;
		this.validator = validator;
	}

	/**
     *
     */
	@Override
	public NivelAutoridade save(NivelAutoridade nivelAutoridade) {

		// VALIDA CAMPO OBRIGATÓRIO DA ENTIDADE
		if (UtilObjeto.isReferencia(this.validator)) {
			this.validarEntidade(nivelAutoridade, this.validator);
		}

		this.removeEspacoInicioEFimNome(nivelAutoridade);

		// MONTA OS DADOS DO OBJETO NIVELAUTORIDADE
		this.nivelAutoridadegrupoService.montarGrupos(nivelAutoridade);

		// VERIFICA SE O NOME OU HIERARQUIA JA ESTAO CADASTRADOS
		this.validacoesNivelAutoridade(nivelAutoridade);

		// SALVA A NIVEL AUTORIDADE
		return super.save(nivelAutoridade);
	}

	@Override
	public NivelAutoridade merge(NivelAutoridade nivelAutoridade) {

		// VALIDA CAMPO OBRIGATÓRIO DA ENTIDADE
		if (UtilObjeto.isReferencia(this.validator)) {
			this.validarEntidade(nivelAutoridade, this.validator);
		}

		this.removeEspacoInicioEFimNome(nivelAutoridade);

		// MONTA OS DADOS DO OBJETO NIVELAUTORIDADE
		this.nivelAutoridadegrupoService.montarGrupos(nivelAutoridade);

		// VERIFICA SE O NOME OU HIERARQUIA JA ESTAO CADASTRADOS
		this.validacoesNivelAutoridade(nivelAutoridade);

		// SALVA A NIVEL AUTORIDADE
		return super.merge(nivelAutoridade);
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
	 * @author renato.jesus
	 *
	 * @param nivelAutoridade
	 */
	private void removeEspacoInicioEFimNome(NivelAutoridade nivelAutoridade) {

		if (UtilObjeto.isReferencia(nivelAutoridade.getNome())) {
			nivelAutoridade.setNome(nivelAutoridade.getNome().trim());
		}
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">594</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por validar se o nome do nivel de autoridade ja foi cadastrado e se a hierarquia ja foi cadastrada para algum outro nivel de autoridade
	 *
	 * @author renato.jesus, juliana.barbosa
	 *
	 * @param nivelAutoridade
	 */
	private void validacoesNivelAutoridade(NivelAutoridade nivelAutoridade) {

		Long idOrganizacao = ( (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getOrganizacao().getId();

		// Verifica se o nome ou a hierarquia do nivel autoridade ja estao cadastrados
		if (UtilObjeto.isReferencia(nivelAutoridade) && UtilObjeto.isReferencia(nivelAutoridade.getId())) {

			NivelAutoridade nivelAutoridadeBase = this.getReference(nivelAutoridade.getId());

			if (!nivelAutoridade.getNome().equalsIgnoreCase(nivelAutoridadeBase.getNome()) && this.nivelAutoridadeDao.verificaSeNomeIsUnico(nivelAutoridade.getNome(), idOrganizacao)) {

				throw new BusinessException(VALIDACAO_NOME_IGUAL, CodigoErro.REGRA_NEGOCIO.getValue());
			} else if (!nivelAutoridade.getHierarquia().equals(nivelAutoridadeBase.getHierarquia()) && this.nivelAutoridadeDao.existeHierarquiaNivelAutoridade(nivelAutoridade.getHierarquia(), idOrganizacao)) {
				throw new BusinessException(VALIDACAO_HIERARQUIA, CodigoErro.REGRA_NEGOCIO.getValue());
			}

		} else if (UtilObjeto.isReferencia(nivelAutoridade) && this.nivelAutoridadeDao.verificaSeNomeIsUnico(nivelAutoridade.getNome(), idOrganizacao)) {

			throw new BusinessException(VALIDACAO_NOME_IGUAL, CodigoErro.REGRA_NEGOCIO.getValue());
		} else if (UtilObjeto.isReferencia(nivelAutoridade) && this.nivelAutoridadeDao.existeHierarquiaNivelAutoridade(nivelAutoridade.getHierarquia(), idOrganizacao)) {
			throw new BusinessException(VALIDACAO_HIERARQUIA, CodigoErro.REGRA_NEGOCIO.getValue());
		}

	}

	@Override
	public boolean removeById(Long idNivelAutoridade) {

		return this.nivelAutoridadeDao.removeById(idNivelAutoridade);
	}

}
