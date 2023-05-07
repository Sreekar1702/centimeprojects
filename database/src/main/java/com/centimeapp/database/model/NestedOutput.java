package com.centimeapp.database.model;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class NestedOutput {
    private Integer id;
    private Integer parentid;
    private String name;
    private String color;

}
