package br.com.centralit.framework.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class MoneySerializer extends JsonSerializer<BigDecimal> {
	private static final int SCALE = 2;

    @Override
    public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        // put your desired money style here
    	NumberFormat formatter = NumberFormat.getInstance(new Locale("pt", "BR"));
		formatter.setMinimumFractionDigits(SCALE);
		String numberFormated = "";

		value = value.setScale(SCALE, BigDecimal.ROUND_CEILING);
		numberFormated = formatter.format(value.doubleValue());

		jgen.writeString(numberFormated);
    }
}