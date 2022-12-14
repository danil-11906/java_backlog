package ru.itis.springbootdemo.services;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.springbootdemo.models.FileInfo;
import ru.itis.springbootdemo.repositories.FilesInfoRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    private FilesInfoRepository filesInfoRepository;

    @Value("${storage.path}")
    private String storagePath;

    @Override
    public String saveFile(MultipartFile uploadingFile) {
        String storageName = UUID.randomUUID().toString() + "." +
                FilenameUtils.getExtension(uploadingFile.getOriginalFilename());
        FileInfo file = FileInfo.builder()
                .type(uploadingFile.getContentType())
                .originalFileName(uploadingFile.getOriginalFilename())
                .storageFileName(storageName)
                .size(uploadingFile.getSize())
                .url(storagePath + "\\" + storageName)
                .build();

        try {
            Files.copy(uploadingFile.getInputStream(), Paths.get(storagePath, storageName));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        filesInfoRepository.save(file);
        return file.getStorageFileName();
    }

    @Override
    public void writerFileToResponse(String fileName, HttpServletResponse response) {
        FileInfo fileInfo = filesInfoRepository.findByAndStorageFileName(fileName);
        response.setContentType(fileInfo.getType());
        try {
            IOUtils.copy(new FileInputStream(fileInfo.getUrl()), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String findStorageFile(Long id) {
        return filesInfoRepository.findById(id).get().getStorageFileName();
    }


}
