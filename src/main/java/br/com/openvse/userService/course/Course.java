package br.com.openvse.userService.course;

import java.util.Set;
import java.util.UUID;

import javax.persistence.*;

import br.com.openvse.userService.professor.Professor;
import br.com.openvse.userService.student.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 100, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<Student> students;

    @ManyToMany
    @JoinTable(name = "course_professor")
    private Set<Professor> professors;

}
