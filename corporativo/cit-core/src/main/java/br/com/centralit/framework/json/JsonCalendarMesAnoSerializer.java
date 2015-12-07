package br.com.centralit.framework.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class JsonCalendarMesAnoSerializer extends JsonSerializer<Calendar> {

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");

	@Override
	public void serialize(Calendar calendar, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {

		String formattedDate = dateFormat.format(calendar.getTime());

		gen.writeString(formattedDate);
	}

}
