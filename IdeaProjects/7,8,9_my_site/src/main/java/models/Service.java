package models;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Service {
    private Long id;
    private String name;
    private String cost;
    private String date;
}