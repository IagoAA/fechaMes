package br.com.centralit.framework.util;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

public class HibernateAwareObjectMapper extends ObjectMapper {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -1113504312489976089L;

	public HibernateAwareObjectMapper() {
		Hibernate4Module hm = new Hibernate4Module();
		hm.configure(Hibernate4Module.Feature.FORCE_LAZY_LOADING, true);

		this.setSerializationInclusion(Include.NON_NULL);
		this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		this.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
		// this.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		this.setDateFormat(sdf);
		this.registerModule(hm);
	}

	public void setPrettyPrint(boolean prettyPrint) {
		configure(SerializationFeature.INDENT_OUTPUT, prettyPrint);
	}
}