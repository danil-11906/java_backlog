package interfaces;

import models.FileInfo;

import java.util.List;

public interface FilesRepository {
    void save(FileInfo entity);
    FileInfo findById(Long id);
    List<FileInfo> findAll();
}

