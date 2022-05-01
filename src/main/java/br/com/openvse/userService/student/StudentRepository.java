package br.com.openvse.userService.student;

import br.com.openvse.userService.util.CustomUserRepository;

import java.util.UUID;

public interface StudentRepository extends CustomUserRepository<Student, UUID> {
}
