package br.com.centralit.framework.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governan&ccedil;a Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p>
 *
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de neg&oacute;cio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
 *
 * @since 21/07/2015 - 10:34:55
 *
 * @version 1.0.0
 *
 * @author ciro.junior (<a href="mailto:ciro.junior@centralit.com.br">ciro.junior@centralit.com.br</a>)
 *
 */
public final class UtilDataBase {

	private static final Logger LOG = Logger.getLogger(UtilDataBase.class);

	/**
	 * Databases mapeados: PostgreSQL, SQL Server, Oracle, DB2, MySql e Default (qualquer um que n&atilde;o seja os mapeados);
	 *
	 * */
	public enum Databases {
		POSTGRESQL("postgresql"),
		SQL_SERVER("sqlserver"),
		ORACLE("oracle"),
		DB2("db2"),
		MYSQL("mysql"),
		DEFAULT("default");

		private String label;

		private Databases(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}
	@Autowired
	public static DataSource citDataSource;

	/**
	 *
	 * Retorna as meta informa&ccedil;&otilde;es de uma conex&atilde;o a partir do entityManager atual.
	 *
	 * @author ciro.junior (<a href="mailto:ciro.junior@centralit.com.br">ciro.junior@centralit.com.br</a>)
	 *
	 * @param entityManager
	 * @return entityManagerProperties
	 * @throws SQLException
	 */
	public static Map<String, Object> getDataBaseInformation(EntityManager entityManager) throws SQLException{

		Map<String, Object> entityManagerProperties = entityManager.getEntityManagerFactory().getProperties();
		return entityManagerProperties;
	}

	/**
	 * Obt&eacute;m as meta-informa&ccedil;&otilde;es de uma conex&atilde;o com o banco de dados a partir do atual data source do m&oacute;dulo
	 * @return {@link DatabaseMetaData}
	 * */
	public static DatabaseMetaData getDataBaseInformation (Connection connection) {
		DatabaseMetaData metaData = null;
		try {
			metaData = connection.getMetaData();
		} catch (SQLException sqle) {
			LOG.info("Ocorreu uma falha ao tentar obter as informa\u00e7\u00f5es da conex\u00e3o baseado na conex\u00e3o passada como "
					+ "par\u00e2mentro. Veja a exce\u00e7\u00e3o" + "para entender a causa:" + sqle.getCause());
		}
		return metaData;
	}

	/**
	 * Obt&eacute;m o nome do banco de dados a partir de uma {@link javax.persistence.EntityManager}
	 * @return {@link UtilDataBase.Databases}
	 * */
	public static Databases getDataBaseName(EntityManager entityManager) {

		Map<String, Object> databaseProperties = new HashMap<String, Object>();
		try {
			databaseProperties = getDataBaseInformation(entityManager);
			String databaseDialect = (String) databaseProperties.get("hibernate.dialect");
			if(databaseDialect.toLowerCase().contains(Databases.POSTGRESQL.getLabel())){
				return Databases.POSTGRESQL;
			} else if (databaseDialect.toLowerCase().contains(Databases.SQL_SERVER.getLabel())){
				return Databases.SQL_SERVER;
			} else if (databaseDialect.toLowerCase().contains(Databases.ORACLE.getLabel())){
				return Databases.ORACLE;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Databases.DEFAULT;
	}

	/**
	 * Obt&eacute;m o nome do banco de dados a partir de uma {@link java.sql.Connection}
	 * @return {@link UtilDataBase.Databases}
	 * */
	public static Databases getDataBaseName (Connection connection) {

		DatabaseMetaData metaData = getDataBaseInformation(connection);
		try {
			UtilDataBase.closeResources(connection);
			return resolveDataBaseName(metaData.getDatabaseProductName());
		} catch (SQLException sqle) {
			LOG.info("N\u00e3o consegui obter o nome do banco de dados o qual estou conectado. Veja a exce\u00e7\u00e3o para entender a "
					+ "causa:" + sqle.getCause());
		}
		return Databases.DEFAULT;
	}

	/**
	 * Resolve o nome do banco de dados a partir de uma String
	 * @return {@link UtilDataBase.Databases}
	 * */
	public static Databases resolveDataBaseName(String name){
		if(name.equalsIgnoreCase(Databases.POSTGRESQL.getLabel())){
			return Databases.POSTGRESQL;
		} else if (name.equalsIgnoreCase(Databases.SQL_SERVER.getLabel())){
			return Databases.SQL_SERVER;
		} else if (name.equalsIgnoreCase(Databases.ORACLE.getLabel())){
			return Databases.ORACLE;
		}
		return Databases.DEFAULT;
	}

	/**
	 * Obt&eacute;m conex&atilde;o ao banco de dados usando o atual data souce do m&oacute;dulo.
	 * @return {@link java.sql.Connection}
	 * */
	public static Connection getConnection(){
		Connection conn = null;
		try {
			return citDataSource.getConnection();
		} catch (SQLException sqle) {
			LOG.error("N\u00e3o foi poss\u00edvel obter conex\u00e3o com o dataSource do m\u00f3dulo. Veja a exceção para identificar as "
					+ "causas: " + sqle.getCause());
		}
		return conn;
	}

	/**
	 * Encerra os recursos java.sql.Connection, java.io.InputStream , java.io.ByteArrayOutputStream
	 * @param resources [connection, inputStream , byteArrayOutputStream]
	 * */
	public static void closeResources(Object ... resources) {
		try {
			for (Object obj : resources){
				if (obj instanceof Connection){
					Connection conn = (Connection) obj;
					if (conn != null){
						conn.close();
					}
				} else if (obj instanceof InputStream) {
						InputStream input = (InputStream) obj;
						if(input != null){
							input.close();
						}
				} else if (obj instanceof ByteArrayOutputStream) {
					ByteArrayOutputStream baos = (ByteArrayOutputStream) obj;
					if(baos != null){
						baos.close();
					}
				}
			}
		} catch (SQLException sqle) {
			LOG.error("N\u00e3o consegui encerrar os recursos de conex\u00e3o ao banco de dados. Veja a exce\u00e7\u00e3o para identificar as causas. " + sqle.getCause());
		} catch (IOException ioe) {
			LOG.error("N\u00e3o consegui encerrar alguns dos recursos de leitura de arquivos. Veja a exce\u00e7\u00e3o para identificar as causas. " + ioe.getCause());
		}
	}

}
