package br.com.openvse.userService.student.dto;

import br.com.openvse.userService.course.Course;
import br.com.openvse.userService.student.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCreateResponse {

    private String email;

    private boolean registrationConfirmed;

    private int semester;

    private String course;

    public static StudentCreateResponse from(Student student){
        return StudentCreateResponse.builder().email(student.getEmail()).
                registrationConfirmed(student.isRegistrationConfirmed()).
                semester(student.getSemester())
                .course(student.getCourse().getName()).build();
    }
}
