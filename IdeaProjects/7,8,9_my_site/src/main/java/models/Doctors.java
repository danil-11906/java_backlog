package models;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Doctors {
    private Long id;
    private String name;
    private String secondName;
    private String lastName;
    private String position;
    private String exp;
}
