package br.com.centralit.framework.util;

import java.sql.Types;

import org.hibernate.dialect.SQLServer2005Dialect;

/**
 * <p>
 * <img src="http://centralit.com.br/images/logo_central.png">
 * </p>
 *
 * <p>
 * <b>Company: </b> Central IT - Governan&ccedil;a Corporativa -
 * </p>
 *
 * <p>
 * <b>Description: </b>
 * </p>
 * Dialeto customizado para SQL Server que visa resolver problemas de mapeamentos para tipos b&aacute;sicos que o dialeto padr&atilde;o do
 * hibernate gera. Com este dialeto customizado os mapeamentos s&atilde;o feitos usando os tipos mais atuais recomendados pelo SQL Server.
 * Indicado uso com vers&atilde;o apartir de SQL Server 2005.
 * @since 27/07/2015 - 17:41:11
 *
 * @version 1.0.0
 *
 * @author ciro.junior (<a href="mailto:ciro.junior@centralit.com.br">ciro.junior@centralit.com.br</a>)
 *
 */
public class SQLServerCustomDialect extends SQLServer2005Dialect {

	private static final int MAX_LENGTH = 8000;

	public SQLServerCustomDialect() {

		super();
		//java.sql.Types
		registerColumnType(Types.VARBINARY, "varbinary(max)");
		//Workarround para solucionar problema de varbinary(255) gerado pelo hibernate por padrao ao inves do maximo. Que o grande compilador
		// divino me perdoe.
		registerColumnType(Types.VARBINARY, MAX_LENGTH, "varbinary(max)");
	}
}
