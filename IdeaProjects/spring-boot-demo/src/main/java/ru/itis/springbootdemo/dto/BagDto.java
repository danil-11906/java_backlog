package ru.itis.springbootdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.springbootdemo.models.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BagDto {
    private String name;

    public static BagDto of(Service service) {
        return BagDto.builder()
                .name(service.getName())
                .build();
    }

    public static List<BagDto> from(List<Service> services) {
        return services.stream()
                .map(BagDto::of)
                .collect(Collectors.toList());
    }
}
