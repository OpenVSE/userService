package br.com.openvse.userService.config.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Date;


public class JWTAuthenticationProvider implements AuthenticationProvider {

    private final String secret;

    public JWTAuthenticationProvider(String secret) {
        this.secret = secret;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getPrincipal();
        DecodedJWT jwt;

        if(!token.startsWith("Bearer ")){
            throw new BadCredentialsException("Invalid JWT token");
        }

        try {
            jwt = JWT.require(Algorithm.HMAC256(secret)).build().verify(token.substring(7));
            if(jwt.getExpiresAt().compareTo(new Date()) < 0 ){
                throw new CredentialsExpiredException("Expired token");
            }
        }catch (JWTVerificationException e){
            throw new BadCredentialsException("Invalid JWT token",e);
        }

        JWTAuthenticationToken verifiedToken = new JWTAuthenticationToken(jwt);
        verifiedToken.setAuthenticated(true);
        verifiedToken.setDetails(authentication.getDetails());


        return verifiedToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
