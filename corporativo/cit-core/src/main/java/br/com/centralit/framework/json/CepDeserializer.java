package br.com.centralit.framework.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

public class CepDeserializer extends StdScalarDeserializer<String> {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 4622700012003322687L;

	/**
	 * We may know actual expected type; if so, it will be used for instantiation.
	 */
	protected final Class<? extends String> _StringClass;

	public CepDeserializer() {

		this(null);
	}

	public CepDeserializer( Class<? extends String> cc ) {

		super(String.class);
		_StringClass = cc;
	}

	@Override
	public String deserialize(JsonParser jsonparser, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		String cep = jsonparser.getText();

		return cep.replace("-", "");

	}

}
