package br.com.openvse.userService.admin;

import br.com.openvse.userService.util.CustomUserRepository;

import java.util.UUID;

public interface AdminRepository extends CustomUserRepository<Admin, UUID> {
}
