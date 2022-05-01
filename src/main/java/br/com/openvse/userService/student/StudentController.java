package br.com.openvse.userService.student;

import br.com.openvse.userService.student.dto.StudentCreateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/self")
    public ResponseEntity<StudentCreateResponse> getSelf(){
        return ResponseEntity.ok(StudentCreateResponse.from(studentService.getSelf()));
    }


}
