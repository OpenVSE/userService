package br.com.openvse.userService.person;

import java.util.UUID;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 320, unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ColumnDefault(value = "false")
    private boolean registrationConfirmed;
}