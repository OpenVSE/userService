package br.com.openvse.userService.seed;

import br.com.openvse.userService.course.Course;
import br.com.openvse.userService.course.CourseRepository;
import br.com.openvse.userService.student.Student;
import br.com.openvse.userService.student.StudentRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seed")
public class SeedController {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final BCryptPasswordEncoder encoder;

    public SeedController(StudentRepository studentRepository, CourseRepository courseRepository, BCryptPasswordEncoder encoder) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.encoder = encoder;
    }


    @GetMapping
    public void populateDatabase(){
        Course course = new Course();
        course.setName("Computação");
        course = courseRepository.save(course);
        Student student = new Student();
        student.setEmail("lucascz37@gmail.com");
        student.setCourse(course);
        student.setSemester(2);
        student.setPassword(encoder.encode("helloguys"));
        studentRepository.save(student);
    }
}
