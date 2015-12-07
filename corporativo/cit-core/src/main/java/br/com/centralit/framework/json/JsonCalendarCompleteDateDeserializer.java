package br.com.centralit.framework.json;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

/**
 * 
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: JsonCalendarCompleteDateDeserializer </b></p>
 *
 * <p><b>Description: Deserializa uma data completa com hora, minutos e segundos atual </b></p>
 * 
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
 * 	
 * @since 13/02/2015 - 09:35:28
 *
 * @version 1.0.0
 *
 * @author geovane.filho
 *
 */
public class JsonCalendarCompleteDateDeserializer extends StdScalarDeserializer<Calendar> {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -6298519073936090152L;
	/**
	 * We may know actual expected type; if so, it will be used for instantiation.
	 */
	protected final Class<? extends Calendar> _calendarClass;

	public JsonCalendarCompleteDateDeserializer() {

		this(null);
	}

	public JsonCalendarCompleteDateDeserializer( Class<? extends Calendar> cc ) {

		super(Calendar.class);
		_calendarClass = cc;
	}

	@Override
	public Calendar deserialize(JsonParser jsonparser, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String date = jsonparser.getText();
		Date d;
		Calendar dataAtual = Calendar.getInstance();
		try {
			d = format.parse(date);
		} catch (ParseException e) {

			try {
				d = format1.parse(date);
			} catch (ParseException e1) {
				d = null;
			}
		}

		if (d == null) {
			return null;
		}

		if (_calendarClass == null) {
			Calendar retorno = ctxt.constructCalendar(d);
			retorno.set(Calendar.HOUR, dataAtual.get(Calendar.HOUR));
			retorno.set(Calendar.MINUTE, dataAtual.get(Calendar.MINUTE));
			retorno.set(Calendar.SECOND, dataAtual.get(Calendar.SECOND));
			return retorno;
		}

		try {
			Calendar retorno = _calendarClass.newInstance();
			retorno.setTimeInMillis(d.getTime());
			retorno.set(Calendar.HOUR, dataAtual.get(Calendar.HOUR));
			retorno.set(Calendar.MINUTE, dataAtual.get(Calendar.MINUTE));
			retorno.set(Calendar.SECOND, dataAtual.get(Calendar.SECOND));
			return retorno;
		} catch (Exception e) {
			throw ctxt.instantiationException(_calendarClass, e);
		}
	}

}
