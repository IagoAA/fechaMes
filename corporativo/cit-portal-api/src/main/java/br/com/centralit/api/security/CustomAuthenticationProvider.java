package br.com.centralit.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import br.com.centralit.api.service.UsuarioService;
import br.com.centralit.framework.model.Usuario;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UsuarioService usuarioService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    		String username = authentication.getName();
	        String password = authentication.getCredentials().toString();

	        Usuario usuario = (Usuario) usuarioService.loadUserByUsernamePassword(username, password);

	        if (usuario != null ) {
	            Authentication auth = new UsernamePasswordAuthenticationToken(usuario, password, usuario.getAuthorities());

	            return auth;
	        } else {
	            return null;
	        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}