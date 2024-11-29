package uz.pdp.simple_crud2.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.simple_crud2.dto.StudentCreateDTO;
import uz.pdp.simple_crud2.entity.Student;

import java.time.LocalDateTime;

@Component
public class StudentMapper {
    public Student toEntity(StudentCreateDTO studentCreateDTO) {
        return Student.builder()
                .name(studentCreateDTO.getName())
                .surname(studentCreateDTO.getSurname())
                .phoneNumber(studentCreateDTO.getPhoneNumber())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public StudentCreateDTO toDto(Student student) {
        return StudentCreateDTO.builder()
                .name(student.getName())
                .surname(student.getSurname())
                .phoneNumber(student.getPhoneNumber())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
