package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.FuncaoDao;
import br.com.centralit.api.model.Funcao;
import br.com.centralit.api.service.ColaboradorService;
import br.com.centralit.api.service.FuncaoService;
import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilObjeto;

@Service("funcaoService")
public class FuncaoServiceImpl extends GenericServiceImpl<Funcao, Long> implements FuncaoService {

    private FuncaoDao funcaoDao;
    
	private static final String FUNCAO_CODIGO_IGUAL = "VALIDACAO.FUNCAO_CODIGO_IGUAL";
	private static final String FUNCAO_NOME_IGUAL = "VALIDACAO.FUNCAO_NOME_IGUAL";	
	
	/** Atributo alcadaService. */
	@Autowired
	private ColaboradorService colaboradorService;	

    @Autowired
	public FuncaoServiceImpl(FuncaoDao funcaoDao,  @Qualifier("funcaoValidator") Validator validator) {
    	this.dao = funcaoDao;
        this.funcaoDao = funcaoDao;
    	this.validator = validator;
	}
    
	public Collection<Funcao> findPorNomeEOrganizacao(String nome, Long organizacaoId) {

		return this.funcaoDao.findPorNomeEOrganizacao(nome, organizacaoId);
	}    
	
	@Override
	public Funcao save(Funcao funcao) {

		// VALIDA CAMPO OBRIGATÓRIO DA ENTIDADE
		if (UtilObjeto.isReferencia(this.validator)) {
			this.validarEntidade(funcao, this.validator);
		}
		
		this.verificaCodigoIgual(funcao);
		this.verificaNomeIgual(funcao);

		// SALVA A FUNCAO
		return super.save(funcao);
	}	
	
	@Override
	public Funcao merge(Funcao funcao) {

		// VALIDA CAMPO OBRIGATÓRIO DA ENTIDADE
		if (UtilObjeto.isReferencia(this.validator)) {
			this.validarEntidade(funcao, this.validator);
		}
		

		this.verificaCodigoIgual(funcao);
		this.verificaNomeIgual(funcao);

		// SALVA A FUNCAO
		return super.merge(funcao);
	}	
	
	private void verificaCodigoIgual(Funcao funcao){
		
		Long idOrganizacao = ( (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getOrganizacao().getId();
		// Verifica se o codigo ja existe
		if (UtilObjeto.isReferencia(funcao) && UtilObjeto.isReferencia(funcao.getId())) {			

			Funcao funcaoBase = this.getReference(funcao.getId());

			if (!funcao.getCodigo().equals(funcaoBase.getCodigo()) && this.funcaoDao.verificaSeCodigoEUnico(funcao.getCodigo(), idOrganizacao)) {

				throw new BusinessException(FUNCAO_CODIGO_IGUAL, CodigoErro.REGRA_NEGOCIO.getValue());
			} 
		} else if (UtilObjeto.isReferencia(funcao) && this.funcaoDao.verificaSeCodigoEUnico(funcao.getCodigo(), idOrganizacao)) {			

			throw new BusinessException(FUNCAO_CODIGO_IGUAL, CodigoErro.REGRA_NEGOCIO.getValue());
		} 		
	}
	
	private void verificaNomeIgual(Funcao funcao){
		Long idOrganizacao = ( (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getOrganizacao().getId();
		// Verifica se o nome ja existe
		if (UtilObjeto.isReferencia(funcao) && UtilObjeto.isReferencia(funcao.getId())) {

			Funcao funcaoBase = this.getReference(funcao.getId());

			if (!funcao.getNome().equalsIgnoreCase(funcaoBase.getNome()) && this.funcaoDao.verificaSeNomeEUnico(funcao.getNome(), idOrganizacao)) {

				throw new BusinessException(FUNCAO_NOME_IGUAL, CodigoErro.REGRA_NEGOCIO.getValue());
			} 
		} else if (UtilObjeto.isReferencia(funcao) && this.funcaoDao.verificaSeNomeEUnico(funcao.getNome(), idOrganizacao)) {

			throw new BusinessException(FUNCAO_NOME_IGUAL, CodigoErro.REGRA_NEGOCIO.getValue());
		} 		
	}	
	
	@Override
	public boolean removeById(Long idFuncao) {

		if (this.colaboradorService.existeFuncaoVinculadaAColaborador(idFuncao)) {
			throw new BusinessException("MSG.FUNCAO_VINCULADA_COLABORADOR", CodigoErro.REGRA_NEGOCIO.getValue());
		}

		return this.funcaoDao.removeById(idFuncao);
	}	
}
