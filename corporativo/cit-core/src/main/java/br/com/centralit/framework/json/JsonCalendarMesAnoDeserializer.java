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

public class JsonCalendarMesAnoDeserializer extends StdScalarDeserializer<Calendar> {

    /** Atributo serialVersionUID. */
	private static final long serialVersionUID = -7404508738751851016L;
	/**
     * We may know actual expected type; if so, it will be
     * used for instantiation.
     */
    protected final Class<? extends Calendar> _calendarClass;

    public JsonCalendarMesAnoDeserializer() { this(null); }
    public JsonCalendarMesAnoDeserializer(Class<? extends Calendar> cc) {
        super(Calendar.class);
        _calendarClass = cc;
    }

	@Override
    public Calendar deserialize(JsonParser jsonparser, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		SimpleDateFormat format = new SimpleDateFormat("MM/yyyy");
        String date = jsonparser.getText();
        Date d;
        try {
        	d = format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if (d == null) {
            return null;
        }

        if (_calendarClass == null) {
            return ctxt.constructCalendar(d);
        }

        try {
            Calendar c = _calendarClass.newInstance();
            c.setTimeInMillis(d.getTime());
            return c;
        } catch (Exception e) {
            throw ctxt.instantiationException(_calendarClass, e);
        }
    }

}