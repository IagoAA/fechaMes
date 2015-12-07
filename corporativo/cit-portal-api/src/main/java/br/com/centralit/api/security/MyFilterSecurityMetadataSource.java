package br.com.centralit.api.security;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import br.com.centralit.api.service.AccessRoleService;
import br.com.centralit.framework.model.AccessRole;

@Configuration
public class MyFilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

		@Autowired
		private AccessRoleService accessRoleService;

        public List<ConfigAttribute> getAttributes(Object filter) {
        	FilterInvocation filterInvocation = (FilterInvocation) filter;
    		String url = filterInvocation.getRequestUrl();

    		// get the roles for requested page from the property file
    		AccessRole accessRole = accessRoleService.getAccessRoleByUrl(url);
//    		urlPropsValue = props.getProperty(url);

    		StringBuilder rolesStringBuilder = new StringBuilder();
  ////////////////////////////////////////
    		if(accessRole != null){
    			rolesStringBuilder.append(accessRole.getRoles()).append(",");
    		}

    		if (!url.endsWith("/")) {
    			int lastSlashIndex = url.lastIndexOf('/');
    			url = url.substring(0, lastSlashIndex + 1);
    		}

    		String[] urlParts = url.split("/");

    		StringBuilder urlBuilder = new StringBuilder();
    		for (String urlPart : urlParts) {
    			if (urlPart.trim().length() == 0) {
    				continue;
    			}

    			urlBuilder.append("/").append(urlPart);

    			accessRole = accessRoleService.getAccessRoleByUrl(urlBuilder.toString() + "/**");

    			if(accessRole != null){
        			rolesStringBuilder.append(accessRole.getRoles()).append(",");
        		}
    		}

    		if (rolesStringBuilder.toString().endsWith(",")) {
    			rolesStringBuilder.deleteCharAt(rolesStringBuilder.length() - 1);
    		}

    		if (rolesStringBuilder.length() == 0) {
    			return null;
    		}

    		return SecurityConfig.createListFromCommaDelimitedString(rolesStringBuilder.toString());
        }

        public Collection<ConfigAttribute> getAllConfigAttributes() {
                return null;
        }

        public boolean supports(Class<?> clazz) {
                return FilterInvocation.class.isAssignableFrom(clazz);
        }
}