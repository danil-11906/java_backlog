package models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder

public class User {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
}