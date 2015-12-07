package br.com.centralit.api.framework.json;

import br.com.centralit.framework.json.Views;

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
 * @since 02/06/2015 - 18:16:53
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
public class ViewsTabelasCorp extends Views {

	public static class NivelAutoridadeView extends Views.GrupoEditView  {};
	
	public static class CentroResultadoEditView extends ColaboradorAutoCompleteView {};
	
	public static class CentroResultadoAutoCompleteView extends GenericView {};	

	public static class CentroResultadoResponsavelTodosResponsaveis extends GenericView {};
	
	public static class CentroResultadoDelegacaoResponsavel extends GenericView {};	

	public static class CentroResultadoDelegacaoTodasDelegacoes extends GenericView {};
	
	public static class FuncaoView extends GenericView {};
	
	public static class CentroResultadoSubstituicao extends GenericView {};
	
	public static class SolicitacaoAlcadaView extends GenericView {};
	

}
