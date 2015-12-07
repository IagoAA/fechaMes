package br.com.centralit.framework.json;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

public class MoneyDeserializer extends StdScalarDeserializer<BigDecimal> {

	public MoneyDeserializer() {
		super(BigDecimal.class);
	}

	 @Override
     public BigDecimal deserialize(JsonParser jp, DeserializationContext ctxt)
         throws IOException, JsonProcessingException
     {
         JsonToken t = jp.getCurrentToken();
         if (t == JsonToken.VALUE_NUMBER_INT || t == JsonToken.VALUE_NUMBER_FLOAT) {
             return jp.getDecimalValue();
         }
         // String is ok too, can easily convert
         if (t == JsonToken.VALUE_STRING) { // let's do implicit re-parse
             String text = jp.getText().trim();
             if (text.length() == 0) {
                 return null;
             }
             if(text.contains(",")){
            	 text = text.replaceAll("\\.", "").replaceAll(",", ".");
     		 }
             try {
                 return new BigDecimal(text);
             } catch (IllegalArgumentException iae) {
                 throw ctxt.weirdStringException(text, _valueClass, "not a valid representation");
             }
         }
         // Otherwise, no can do:
         throw ctxt.mappingException(_valueClass, t);
     }
	 
	private static final long serialVersionUID = -4871106611601003513L;

}