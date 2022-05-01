package br.com.openvse.userService.student;

import br.com.openvse.userService.auth.AuthenticationService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentService) {
        this.studentRepository = studentService;
    }

    protected Student getSelf(){
       return studentRepository.findById(AuthenticationService.getUserId()).orElseThrow(IllegalArgumentException::new);
    }
}
