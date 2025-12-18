package org.example.demo_spring_data_jpa.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.demo_spring_data_jpa.entity.ClassCG;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
//    @NotEmpty(message = "Require input !")
//    @Pattern(regexp = "^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$", message = "Not match!")
    private String name;
    private boolean gender;
    private ClassCG classCG;
}
