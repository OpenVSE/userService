package br.com.openvse.userService.auth;

import br.com.openvse.userService.auth.dto.AuthenticationRequest;
import br.com.openvse.userService.auth.dto.AuthenticationResponse;
import br.com.openvse.userService.auth.dto.EntityType;
import br.com.openvse.userService.auth.dto.EntityTypeConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/{type}")
    public ResponseEntity<AuthenticationResponse> generateToken(@PathVariable EntityType type, @RequestBody @Valid AuthenticationRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(new AuthenticationResponse(authenticationService.generateToken(type, request), "Bearer"));
    }

    @InitBinder
    public void initBinder(final WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(EntityType.class, new EntityTypeConverter());
    }
}
