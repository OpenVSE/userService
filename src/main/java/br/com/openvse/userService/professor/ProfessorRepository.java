package br.com.openvse.userService.professor;

import br.com.openvse.userService.util.CustomUserRepository;

import java.util.UUID;

public interface ProfessorRepository extends CustomUserRepository<Professor, UUID> {
}
