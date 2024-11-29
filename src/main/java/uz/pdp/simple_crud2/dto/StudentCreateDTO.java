package uz.pdp.simple_crud2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class StudentCreateDTO {
    @NotBlank(message = "name can not be null or empty")
    private String name;
    private String surname;
    @NotBlank(message = "phoneNumber can not be null or empty")
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
