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

public class JsonCalendarSimpleDateDeserializer extends StdScalarDeserializer<Calendar> {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -6298519073936090152L;
	/**
	 * We may know actual expected type; if so, it will be used for instantiation.
	 */
	protected final Class<? extends Calendar> _calendarClass;

	public JsonCalendarSimpleDateDeserializer() {

		this(null);
	}

	public JsonCalendarSimpleDateDeserializer( Class<? extends Calendar> cc ) {

		super(Calendar.class);
		_calendarClass = cc;
	}

	@Override
	public Calendar deserialize(JsonParser jsonparser, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String date = jsonparser.getText();
		Date d;
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
			retorno.set(Calendar.HOUR, 8);
			retorno.set(Calendar.MINUTE, 0);
			retorno.set(Calendar.SECOND, 0);
			return retorno;
		}

		try {
			Calendar retorno = _calendarClass.newInstance();
			retorno.setTimeInMillis(d.getTime());
			retorno.set(Calendar.HOUR, 8);
			retorno.set(Calendar.MINUTE, 0);
			retorno.set(Calendar.SECOND, 0);
			return retorno;
		} catch (Exception e) {
			throw ctxt.instantiationException(_calendarClass, e);
		}
	}

}
