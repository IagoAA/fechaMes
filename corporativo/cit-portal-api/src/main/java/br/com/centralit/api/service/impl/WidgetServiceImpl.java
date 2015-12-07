package br.com.centralit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.WidgetDao;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.WidgetService;
import br.com.centralit.framework.model.Widget;
import br.com.centralit.framework.model.WidgetParametro;
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
 * @since 10/03/2015 - 11:48:29
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
@Service("widgetService")
public class WidgetServiceImpl extends GenericServiceImpl<Widget, Long> implements WidgetService {

	/** Atributo widgetDao. */
	private WidgetDao widgetDao;
	
	@Autowired
	private DominioService dominioService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param notificacaoDao
	 */
	@Autowired
	public WidgetServiceImpl( WidgetDao widgetDao ) {

		this.dao = widgetDao;

		this.widgetDao = widgetDao;
	}

	@Override
	public Widget save(Widget widget) {

		atualizaAtributos(widget);
		// Resolve transient dos parametros
		atualizaParametro(widget);

		return super.save(widget);
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por atualizar (merge) dos atributos do widget
	 *
	 * @author geovane.filho
	 *
	 * @param widget
	 */
	private void atualizaAtributos(Widget widget) {

		if (widget.getTipoComponente() != null) {
			widget.setTipoComponente(dominioService.find(widget.getTipoComponente().getId()));
		}
		
	}

	@Override
	public Widget merge(Widget widget) {

		atualizaAtributos(widget);
		// Resolve transient dos parametros
		atualizaParametro(widget);

		return super.merge(widget);
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
	 * @param widget
	 */
	private void atualizaParametro(Widget widget) {

		if (UtilObjeto.isReferencia(widget.getParametros()) && !widget.getParametros().isEmpty()) {
			for (WidgetParametro parametro : widget.getParametros()) {
				parametro.setTipoWidgetParametroDominio(dominioService.find(parametro.getTipoWidgetParametroDominio().getId()));
				
				if (parametro.getDominioDefault() != null) {
					parametro.setDominioDefault(dominioService.find(parametro.getDominioDefault().getId()));
				}
				parametro.setWidget(widget);
			}
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
	 * Método responsável por obter o widget através do id do tipoComponente
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idTipoComponente
	 * @return Widget
	 */
	public Widget findPorTipo(Long idTipoComponente) {

		return this.widgetDao.findPorTipo(idTipoComponente);
	}
}
