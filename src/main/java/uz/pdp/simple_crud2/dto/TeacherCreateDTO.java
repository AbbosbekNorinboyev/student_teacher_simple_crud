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
public class TeacherCreateDTO {
    @NotBlank(message = "fullName can not be null or empty")
    private String fullName;
    @NotBlank(message = "phoneNumber can not be null or empty")
    private String phoneNumber;
    @NotBlank(message = "subject can not be null or empty")
    private String subject;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
