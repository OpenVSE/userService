package br.com.openvse.userService.auth.dto;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@Data
public class AuthenticationRequest {

    private String email;

    private String password;

    public UsernamePasswordAuthenticationToken toToken(){
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
