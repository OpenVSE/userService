package br.com.openvse.userService.config.security.util;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class JWTAuthenticationToken extends AbstractAuthenticationToken {


    private final Object token;
    /**
     * Creates a token to be verified by the JWTAuthenticationProvider.
     *
     * @param token the string representing the token.
     */
    public JWTAuthenticationToken(String token) {
        super(null);
        this.token = token;
    }

    /**
     * Creates a token to verified by the JWTAuthenticationProvider.
     *
     * @param token the decoded token, should only be used by the authentication provider.
     */
    public JWTAuthenticationToken(DecodedJWT token) {
        super(List.of(new SimpleGrantedAuthority(token.getClaim("ROLE").asString())));
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
}
