package ru.itis.springbootdemo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.springbootdemo.models.Doctors;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.repositories.FilesInfoRepository;
import ru.itis.springbootdemo.services.FileStorageService;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocDto {

    private Long id;
    private String name;
    private String secondname;
    private String lastname;
    private String position;
    private String exp;
    private String image;

    public static DocDto fromDocs(Doctors doctors, FileStorageService fileStorageService) {
        return DocDto.builder()
                .id(doctors.getId())
                .name(doctors.getName())
                .secondname(doctors.getSecondName())
                .lastname(doctors.getLastName())
                .position(doctors.getPosition())
                .exp(doctors.getExp())
                .image(fileStorageService.findStorageFile(doctors.getId()))
                .build();
    }

    public static List<DocDto> fromListDocs(List<Doctors> doctors, FileStorageService fileStorageService) {
        List<DocDto> list = new ArrayList<>();
        for (int i = 0; i < doctors.size(); i++) {
            list.add(fromDocs(doctors.get(i), fileStorageService));
        }
        return list;
    }
}
