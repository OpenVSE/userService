package br.com.openvse.userService.repository;

import br.com.openvse.userService.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfessorRepository extends JpaRepository<Professor, UUID> {
}
