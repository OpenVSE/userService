package br.com.openvse.userService.auth;


import br.com.openvse.userService.admin.AdminRepository;
import br.com.openvse.userService.auth.dto.AuthenticationRequest;
import br.com.openvse.userService.auth.dto.EntityType;
import br.com.openvse.userService.professor.ProfessorRepository;
import br.com.openvse.userService.student.StudentRepository;
import br.com.openvse.userService.util.CustomUserRepository;
import br.com.openvse.userService.util.UserDetails;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;


@Service
public class AuthenticationService {

    @Value("${app.jwt.issuer}")
    private String issuer;

    @Value("${app.jwt.expiration}")
    private Long expirationTime;

    @Value("${app.jwt.secret}")
    private String secret;

    private final BCryptPasswordEncoder encoder;

    private final AdminRepository adminRepository;

    private final StudentRepository studentRepository;

    private final ProfessorRepository professorRepository;

    public AuthenticationService(BCryptPasswordEncoder encoder, AdminRepository adminRepository, StudentRepository studentRepository, ProfessorRepository professorRepository) {
        this.encoder = encoder;
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
    }


    public String generateToken(EntityType type, AuthenticationRequest request){
        CustomUserRepository<?, UUID> repository = getRepository(type);

        UserDetails details = repository.findByEmail(request.getEmail());

        if(details != null && encoder.matches(request.getPassword(), details.getPassword())){
            return generateToken(details);
        }

        throw new IllegalArgumentException("No user with this email and/or password");
    }

    public static UUID getUserId(){
        SecurityContext context = SecurityContextHolder.getContext();
        DecodedJWT jwt = (DecodedJWT) context.getAuthentication().getPrincipal();

        return UUID.fromString(jwt.getSubject());
    }

    private CustomUserRepository<?, UUID> getRepository(EntityType type) {
        CustomUserRepository<?, UUID> repository = null;

        switch (type){
            case ADMIN:
                repository = adminRepository;
                break;
            case STUDENT:
                repository = studentRepository;
                break;
            case PROFESSOR:
                repository = professorRepository;
                break;
        }

        return repository;
    }

    private String generateToken(UserDetails details){
        LocalDateTime dateTime = LocalDateTime.now().plus(Duration.ofMinutes(expirationTime));
        Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        return JWT.create().withIssuer(issuer).withSubject(details.getId().toString()).withPayload(details.getPayload()).withExpiresAt(date).sign(Algorithm.HMAC256(secret));
    }
}
