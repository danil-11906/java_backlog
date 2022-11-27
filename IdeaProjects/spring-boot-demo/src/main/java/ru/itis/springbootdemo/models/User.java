package ru.itis.springbootdemo.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import javax.persistence.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class  User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String email;
    private String password;

    @Enumerated(value = EnumType.STRING)
    private  StateActive stateActive;

    @Enumerated(value = EnumType.STRING)
    private StateConfirmed stateConfirmed;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public enum Role {
        USER,ADMIN
    }

    private String phone;

    private String confirmCode;

    public boolean isActive() {
        return this.stateActive == StateActive.ACTIVE;
    }

    public boolean isBanned() {
        return this.stateActive == StateActive.BANNED;
    }

    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }
}
