package br.com.openvse.userService.auth.dto;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private final String token;

    private final String type;

    public AuthenticationResponse(String token, String type) {
        this.token = token;
        this.type = type;
    }
}
