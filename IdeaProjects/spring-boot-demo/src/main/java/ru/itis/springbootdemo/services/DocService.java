package ru.itis.springbootdemo.services;

import ru.itis.springbootdemo.dto.DocDto;
import ru.itis.springbootdemo.dto.DocForm;

import java.util.List;

public interface DocService {
    List<DocDto> getAllDoc();
    void addDoc(DocForm form);
}
