package br.com.centralit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.WidgetParametroDao;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.WidgetParametroService;
import br.com.centralit.framework.model.WidgetParametro;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;

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
 * @since 20/03/2015 - 11:48:29
 * 
 * @version 1.0.0
 * 
 * @author geovane.filho
 * 
 */
@Service("widgetParametroService")
public class WidgetParametroServiceImpl extends GenericServiceImpl<WidgetParametro, Long> implements WidgetParametroService {

	@Autowired
	private DominioService dominioService;
	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param notificacaoDao
	 */
	@Autowired
	public WidgetParametroServiceImpl( WidgetParametroDao widgetParametroDao ) {

		this.dao = widgetParametroDao;
	}
	
	@Override
	public boolean removeById(Long id){

		//busca o item da saida temporaria
		WidgetParametro widgetParametro = this.find(id);
			
		widgetParametro.setWidgetInativo(widgetParametro.getWidget());
		widgetParametro.setWidget(null);
			
		return super.remove(widgetParametro);
	}
}
