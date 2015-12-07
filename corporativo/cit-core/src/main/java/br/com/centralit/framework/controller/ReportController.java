package br.com.centralit.framework.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.type.BandTypeEnum;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.centralit.framework.model.arquitetura.PersistentObject;
import br.com.centralit.framework.service.arquitetura.GenericService;

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
 * @since 30/06/2015 - 16:05:01
 *
 * @version 1.0.0
 *
 *
 * @param <T>
 */
public class ReportController<T extends PersistentObject> extends GenericController<T> {

	Logger log = Logger.getLogger(ReportController.class);

	public ReportController() {
		super();
	}

	public ReportController( GenericService<T, Long> genericService ) {

		this.genericService = genericService;
	}

	@Autowired
	private DataSource citDataSource;

	public static final String MEDIA_TYPE_EXCEL = "application/vnd.ms-excel";
	public static final String MEDIA_TYPE_PDF = "application/pdf";
	public static final String EXTENSION_TYPE_EXCEL = "xls";
	public static final String EXTENSION_TYPE_PDF = "pdf";

	public HttpServletResponse export(boolean download, String type, JasperPrint jp, HttpServletResponse response, ByteArrayOutputStream baos,
										String reportName) {

		if (StringUtils.isBlank(reportName) || StringUtils.isEmpty(reportName) || reportName == null){
			reportName = "Citsmart-GRP - Relatorio";
		}

		String newReportName = (reportName + new SimpleDateFormat(" yyyyMMddHHmmss").format(new Date())).replaceAll(" ", "_");

		if (type.equalsIgnoreCase(EXTENSION_TYPE_EXCEL)) {
			// Export to output stream
			exportXls(jp, baos);

			// Set our response properties
			// Here you can declare a custom filename
			String fileName = newReportName + ".xls";

			if(download)
				response.setHeader("Content-Disposition", "attachment; filename="+ fileName);
			else
				response.setHeader("Content-Disposition", "inline; filename="+ fileName);

			// Set content type
			response.setContentType(MEDIA_TYPE_EXCEL);
			response.setContentLength(baos.size());

			return response;
		}else if (type.equalsIgnoreCase(EXTENSION_TYPE_PDF)) {
			// Export to output stream
			exportPdf(jp, baos);

			// Set our response properties
			// Here you can declare a custom filename
			String fileName = newReportName + ".pdf";

			if(download)
				response.setHeader("Content-Disposition", "attachment; filename="+ fileName);
			else
				response.setHeader("Content-Disposition", "inline; filename="+ fileName);

			// Set content type
			response.setContentType(MEDIA_TYPE_PDF);
			response.setContentLength(baos.size());

			return response;

		}

		throw new RuntimeException("No type set for type " + type);
	}

	/**
	* Writes the report to the output stream
	*/
	public void write(HttpServletResponse response, ByteArrayOutputStream baos) {

		try {
			// Retrieve output stream
			ServletOutputStream outputStream = response.getOutputStream();
			// Write to output stream
			baos.writeTo(outputStream);
			// Flush the stream
			outputStream.flush();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void exportXls(JasperPrint jp, ByteArrayOutputStream baos) {
		// Create a JRXlsExporter instance
		JRXlsExporter exporter = new JRXlsExporter();

		// Here we assign the parameters jp and baos to the exporter
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

		// Excel specific parameters
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);

		try {
			exporter.exportReport();

		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}

	public void exportPdf(JasperPrint jp, ByteArrayOutputStream baos) {
		// Create a JRXlsExporter instance
		JRPdfExporter exporter = new JRPdfExporter();

		// Here we assign the parameters jp and baos to the exporter
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

		try {
			exporter.exportReport();

		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Retorna uma conex&atilde;o obtida atrav&eacute;s de um datasource para as classes filhas.
	 * */
	public Connection getConnection(){
		Connection conn = null;
		try {
			return citDataSource.getConnection();
		} catch (SQLException sqle) {
			log.error("N\u00e3o foi poss\u00edvel obter conex\u00e3o com o dataSource do m\u00f3dulo. Veja a exceção para identificar as causas: " + sqle.getCause());
		}
		return conn;
	}

	/**
	 * Encerra os recursos usados para obter os relat&oacute;rios.
	 * @deprecated substituido por {@link UtilDataBase.closeResources()}
	 * */
	public void closeResources(Connection conn, InputStream input, InputStream inputSub, ByteArrayOutputStream baos) {
		try {
			if (conn != null){
				conn.close();
			}
			if(input != null){
				input.close();
			}
			if(inputSub != null){
				inputSub.close();
			}
			if(baos != null){
				baos.close();
			}
		} catch (SQLException sqle) {
			log.error("Não consegui encerrar os recursos de conexão ao banco de dados. Veja a exceção para identificar as causas. " + sqle.getCause());
		} catch (IOException ioe) {
			log.error("Não consegui encerrar alguns dos recursos de leitura de arquivos. Veja a exceção para identificar as causas. " + ioe.getCause());
		}
	}

	protected Boolean verificarRelatorioVazio(JasperPrint jasperPrint) {

		Boolean mostrarRelatorio = Boolean.TRUE;

		for(JRPrintPage jrPrintPage : jasperPrint.getPages()){

			for(JRPrintElement jrPrintElement : jrPrintPage.getElements()){

				if(jrPrintElement.getOrigin().getBandTypeValue().equals(BandTypeEnum.NO_DATA)){

					mostrarRelatorio = Boolean.FALSE;

				}
			}
		}

		return mostrarRelatorio;
	}

	/**
	 * Responsavel por verificar a presenca de dados no relatorio
	 *
	 * */
	public void gerarRelatorio (boolean download, String type, JasperPrint jasperPrint, HttpServletResponse response, ByteArrayOutputStream baos, String reportName){

		if(verificarRelatorioVazio(jasperPrint)){

			// Create an output byte stream where data will be written
			baos = new ByteArrayOutputStream();

			// Export report
			this.export(download, type, jasperPrint, response, baos, reportName);

			// Write to reponse stream
			this.write(response, baos);

		} else {

				response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);

		}
	}
}
