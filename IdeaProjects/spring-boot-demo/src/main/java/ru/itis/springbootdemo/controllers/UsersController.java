package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.springbootdemo.dto.UserDto;
import ru.itis.springbootdemo.services.UsersService;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public Object getUsersPage(Model model) {
        model.addAttribute("usersList", usersService.getAllUsers());
        return "users_page";
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/usersNode")
    public ResponseEntity<List<UserDto>> getAllServices() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }
}
