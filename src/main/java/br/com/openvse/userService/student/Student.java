package br.com.openvse.userService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student extends Person {
  
  @Column(nullable = false)
  private int semester;

  @ManyToOne
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;
}
