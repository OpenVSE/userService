package br.com.openvse.userService.util;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserDetails {

    public List<GrantedAuthority> getRole();

    public String getPassword();

    public UUID getId();

    public Map<String, ?> getPayload();
}
