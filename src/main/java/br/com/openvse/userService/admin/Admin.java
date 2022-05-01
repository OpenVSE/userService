package br.com.openvse.userService.admin;

import br.com.openvse.userService.course.Course;
import br.com.openvse.userService.person.Person;
import br.com.openvse.userService.util.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "admin")
public class Admin extends Person implements UserDetails {
    @Override
    public List<GrantedAuthority> getRole() {
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public Map<String, ?> getPayload() {
        Map<String, Object > map = new HashMap<>();
        map.put("ROLE", "ROLE_ADMIN");
        return map;
    }
}
