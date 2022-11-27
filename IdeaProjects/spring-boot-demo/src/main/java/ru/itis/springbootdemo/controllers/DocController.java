package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.springbootdemo.dto.DocDto;
import ru.itis.springbootdemo.dto.DocForm;
import ru.itis.springbootdemo.dto.UserDto;
import ru.itis.springbootdemo.services.DocService;

import java.util.List;

@Controller
public class DocController {

    @Autowired
    private DocService docService;

    @GetMapping("/doctors")
    public String getDocPage(Model model) {
        model.addAttribute("docList", docService.getAllDoc());
        return "doctors";
    }

    @GetMapping("/addDoc")
    public String getDocPage() {
        return "add_doc";
    }

    @PostMapping("/addDoc")
    public String addDoc(DocForm form) {
        docService.addDoc(form);
        return "redirect:/doctors";
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/docNode")
    public ResponseEntity<List<DocDto>> getAllDoctors() {
        return ResponseEntity.ok(docService.getAllDoc());
    }


}
