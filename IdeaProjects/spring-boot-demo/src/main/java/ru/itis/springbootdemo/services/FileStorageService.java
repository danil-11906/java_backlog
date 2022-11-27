package ru.itis.springbootdemo.services;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileStorageService {
    String saveFile (MultipartFile file);
    void writerFileToResponse(String fileName, HttpServletResponse response);
    String findStorageFile (Long id);
}
