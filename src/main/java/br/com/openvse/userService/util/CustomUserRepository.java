package br.com.openvse.userService.util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomUserRepository<T extends UserDetails, ID> extends JpaRepository<T, ID> {

    public T findByEmail(String email);
}
