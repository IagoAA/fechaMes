package br.com.centralit.api.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.userdetails.AbstractCasAssertionUserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import br.com.centralit.api.service.OrganizacaoService;
import br.com.centralit.api.service.PrivilegioService;
import br.com.centralit.api.service.UsuarioService;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.model.UsuarioOrganizacaoItem;
import br.com.centralit.framework.model.UsuarioPrivilegio;
import br.com.centralit.framework.util.UtilObjeto;

@Component
public class GrantedAuthorityFromAssertionAttributesUserDetailsService extends AbstractCasAssertionUserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private OrganizacaoService organizacaoService;

	@Autowired
	private PrivilegioService privilegioService;

	 private static final String NON_EXISTENT_PASSWORD_VALUE = "NO_PASSWORD";

	    private String[] attributes;

	    private boolean convertToUpperCase = true;

	    public GrantedAuthorityFromAssertionAttributesUserDetailsService() {
	    	super();
	    }

	    public GrantedAuthorityFromAssertionAttributesUserDetailsService(final String[] attributes) {
	        Assert.notNull(attributes, "attributes cannot be null.");
	        Assert.isTrue(attributes.length > 0, "At least one attribute is required to retrieve roles from.");
	        this.attributes = attributes;
	    }

	    @SuppressWarnings("unchecked")
	    @Override
	    protected UserDetails loadUserDetails(final Assertion assertion) {
	        final List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

	        grantedAuthorities.add(new GrantedAuthorityImpl("ROLE_USER"));
	        for (final String attribute : this.attributes) {
	            final Object value = assertion.getPrincipal().getAttributes().get(attribute);

	            if (value == null) {
	                continue;
	            }

	            if (value instanceof List) {
	                final List list = (List) value;

	                for (final Object o : list) {
	                    grantedAuthorities.add(new GrantedAuthorityImpl(this.convertToUpperCase ? o.toString().toUpperCase() : o.toString()));
	                }

	            } else {
	                grantedAuthorities.add(new GrantedAuthorityImpl(this.convertToUpperCase ? value.toString().toUpperCase() : value.toString()));
	            }

	        }

	        Usuario usuario = usuarioService.buscaUsuarioByUsername(assertion.getPrincipal().getName());
	        // VERIFICA SE JÁ EXISTE UM USUÁRIO PARA ESSE UID, CASO NÃO EXISTA CRIA UM USUÁRIO
	        if(UtilObjeto.isReferencia(usuario)){
	        	return usuario;
	        }else{
	        	// CRIA O USUARIO E INICIALIZA AS LISTAS OBRIGATORIAS
	        	usuario = new Usuario();
	        	usuario.setOrganizacoes(new ArrayList<UsuarioOrganizacaoItem>(1));
	        	usuario.setUsuarioPrivilegios(new HashSet<UsuarioPrivilegio>());

	        	usuario.setUsername(assertion.getPrincipal().getName());
	        	usuario.setPassword(assertion.getPrincipal().getName());
	        	usuario.setEmail(assertion.getPrincipal().getName());
	        	usuario.setContaHabilitada(true);
	        	usuario.setContaBloqueada(false);
	        	usuario.setContaExpirada(false);
	        	usuario.setCredencialExpirada(false);
	        	usuario.setSempreNovaAba(true);
	        	usuario.setOrganizacao(organizacaoService.find(1L));
	        	usuario.addPrivilegio(privilegioService.find(1L));
	        	UsuarioOrganizacaoItem usuarioOrganizacaoItem = new UsuarioOrganizacaoItem(usuario.getOrganizacao(), usuario);
	        	usuario.getOrganizacoes().add(usuarioOrganizacaoItem);

	        	usuario = usuarioService.save(usuario);

	        	return usuario;
	        }
	    }

	    /**
	     * Converts the returned attribute values to uppercase values.
	     *
	     * @param convertToUpperCase true if it should convert, false otherwise.
	     */
	    public void setConvertToUpperCase(final boolean convertToUpperCase) {
	        this.convertToUpperCase = convertToUpperCase;
	    }
}