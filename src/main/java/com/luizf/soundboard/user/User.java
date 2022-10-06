package com.luizf.soundboard.user;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_")
public class User {
   @Id
   @Column(name = "user_id")
   @SequenceGenerator(name = "user_seq", sequenceName = "user__user_id_seq", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
   private Long id;
   @Column(name = "first_name")
   private String name;
   private String email;
   @Column(name = "password_")
   private String password;
}
