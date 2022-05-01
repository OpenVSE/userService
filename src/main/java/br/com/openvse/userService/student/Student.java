package br.com.openvse.userService.student;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.openvse.userService.course.Course;
import br.com.openvse.userService.person.Person;
import br.com.openvse.userService.util.UserDetails;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "student")
public class Student extends Person implements UserDetails {
  
    @Column(nullable = false)
    private int semester;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Override
    public List<GrantedAuthority> getRole() {
        return List.of(new SimpleGrantedAuthority("ROLE_STUDENT"));
    }

    @Override
    public Map<String, ?> getPayload() {
        Map<String, Object > map = new HashMap<>();
        map.put("ROLE", "ROLE_STUDENT");
        map.put("SEMESTER", getSemester());
        map.put("COURSE", getCourse().getName());
        return map;
    }
}
