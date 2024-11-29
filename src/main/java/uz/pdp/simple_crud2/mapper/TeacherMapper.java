package uz.pdp.simple_crud2.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.simple_crud2.dto.TeacherCreateDTO;
import uz.pdp.simple_crud2.entity.Teacher;

import java.time.LocalDateTime;

@Component
public class TeacherMapper {
    public Teacher toEntity(TeacherCreateDTO teacherCreateDTO) {
        return Teacher.builder()
                .fullName(teacherCreateDTO.getFullName())
                .phoneNumber(teacherCreateDTO.getPhoneNumber())
                .subject(teacherCreateDTO.getSubject())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public TeacherCreateDTO toDto(Teacher teacher) {
        return TeacherCreateDTO.builder()
                .fullName(teacher.getFullName())
                .phoneNumber(teacher.getPhoneNumber())
                .subject(teacher.getSubject())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
