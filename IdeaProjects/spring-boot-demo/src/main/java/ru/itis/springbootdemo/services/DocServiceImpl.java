package ru.itis.springbootdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.springbootdemo.dto.DocDto;
import ru.itis.springbootdemo.dto.DocForm;
import ru.itis.springbootdemo.models.Doctors;
import ru.itis.springbootdemo.repositories.DocRepository;

import java.util.List;

import static ru.itis.springbootdemo.dto.DocDto.fromListDocs;

@Component
public class DocServiceImpl implements DocService {
    @Autowired
    private DocRepository docRepository;
    @Autowired
    private FileStorageService fileStorageService;
    @Override
    public List<DocDto> getAllDoc() {
        return fromListDocs(docRepository.findAll(), fileStorageService);
    }

    @Override
    public void addDoc(DocForm form) {
        Doctors newDoc = Doctors.builder()
                .name(form.getName())
                .secondName(form.getSecondname())
                .lastName(form.getLastname())
                .position(form.getPosition())
                .exp(form.getExp())
                .build();
        docRepository.save(newDoc);
    }
}
