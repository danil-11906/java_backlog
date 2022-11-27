package ru.itis.springbootdemo.dto;

import lombok.Data;

@Data
public class UserForm {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
}
