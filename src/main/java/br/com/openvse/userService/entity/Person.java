package br.com.openvse.userService.entity;

import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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