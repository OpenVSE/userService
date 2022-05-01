package br.com.openvse.userService.professor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.openvse.userService.course.Course;
import br.com.openvse.userService.person.Person;
import br.com.openvse.userService.util.UserDetails;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "professor")
public class Professor extends Person implements UserDetails {

    @ManyToMany(mappedBy = "professors")
    private Set<Course> course;

    @Override
    public List<GrantedAuthority> getRole() {
      return List.of(new SimpleGrantedAuthority("ROLE_PROFESSOR"));
    }

    @Override
    public Map<String, ?> getPayload() {
        Map<String, Object > map = new HashMap<>();
        map.put("ROLE", "ROLE_PROFESSOR");
        map.put("COURSE", getCourse().stream().map(Course::getName));
        return map;
    }
}
