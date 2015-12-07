package br.com.centralit.api.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.centralit.framework.model.Usuario;

/**
 * Utility methods for working with Spring Security.
 */
public class SecurityUtil {

    public static Usuario getLoggedInUser() {
        Usuario user = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth;
        if (securityContext != null) {
            auth = securityContext.getAuthentication();
            if (auth != null) {
                Object principal = auth.getPrincipal();
                if (principal instanceof Usuario) {
                	Usuario authUser = (Usuario) principal;
                    user = authUser;
                }
            }
        }
        return user;
    }

    public static Authentication signInUser(Usuario user) {
        List<GrantedAuthority> roles = getRoles(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), roles);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    public static List<GrantedAuthority> getRoles(Usuario user) {
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new GrantedAuthorityImpl("ROLE_USER"));
        return roles;
    }
}
