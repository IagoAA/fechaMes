package br.com.centralit.framework.json;

import java.io.IOException;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.Assert;

import br.com.centralit.framework.util.HibernateAwareObjectMapper;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;

public class CustomMappingJackson2 extends MappingJackson2HttpMessageConverter {

private HibernateAwareObjectMapper objectMapper = new HibernateAwareObjectMapper();
private boolean prefixJson;

@Override
protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
    JsonGenerator jsonGenerator = this.objectMapper.getFactory().createJsonGenerator(outputMessage.getBody(), encoding);
    try {
        if (this.prefixJson) {
            jsonGenerator.writeRaw("{} && ");
        }
        if(object instanceof ResponseBodyWrapper){
            ResponseBodyWrapper responseBody = (ResponseBodyWrapper) object;
            this.objectMapper.writerWithView(responseBody.getView()).writeValue(jsonGenerator, responseBody.getObject());
        }else{
            this.objectMapper.writeValue(jsonGenerator, object);
        }
    }
    catch (IOException ex) {
        throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
    }
}


@Override
protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
	return super.readInternal(clazz, inputMessage);
}


public void setObjectMapper(HibernateAwareObjectMapper objectMapper) {
    Assert.notNull(objectMapper, "ObjectMapper must not be null");
    super.setObjectMapper(objectMapper);
}

public void setPrefixJson(boolean prefixJson) {
    this.prefixJson = prefixJson;
    super.setPrefixJson(prefixJson);
}
}