package uz.pdp.simple_crud2.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import uz.pdp.simple_crud2.dto.ErrorDTO;
import uz.pdp.simple_crud2.dto.ResponseDTO;
import uz.pdp.simple_crud2.dto.TeacherCreateDTO;
import uz.pdp.simple_crud2.entity.Student;
import uz.pdp.simple_crud2.entity.Teacher;
import uz.pdp.simple_crud2.mapper.TeacherMapper;
import uz.pdp.simple_crud2.repository.StudentRepository;
import uz.pdp.simple_crud2.repository.TeacherRepository;
import uz.pdp.simple_crud2.service.TeacherService;
import uz.pdp.simple_crud2.validation.TeacherValidation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherMapper teacherMapper;
    private final TeacherRepository teacherRepository;
    private final TeacherValidation teacherValidation;
    private final StudentRepository studentRepository;

    @Override
    public ResponseDTO<Teacher> createTeacher(@NonNull TeacherCreateDTO teacherCreateDTO) {
        Teacher teacher = teacherMapper.toEntity(teacherCreateDTO);
        teacherRepository.save(teacher);
        return ResponseDTO.<Teacher>builder()
                .code(200)
                .success(true)
                .message("Successfully saved")
                .data(teacher)
                .build();
    }

    @Override
    public ResponseDTO<Teacher> getTeacher(@NonNull Integer id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found: " + id));
        return ResponseDTO.<Teacher>builder()
                .code(200)
                .success(true)
                .message("OK")
                .data(teacher)
                .build();
    }

    @Override
    public ResponseDTO<List<Teacher>> getAllTeacher() {
        List<Teacher> teachers = teacherRepository.findAll();
        return ResponseDTO.<List<Teacher>>builder()
                .code(200)
                .success(true)
                .message("OK")
                .data(teachers)
                .build();
    }

    @Override
    public ResponseDTO<TeacherCreateDTO> updateTeacher(@NonNull TeacherCreateDTO teacherCreateDTO,
                                                       @NonNull Integer teacherId) {
        List<ErrorDTO> errors = teacherValidation.errorDTOS(teacherCreateDTO);
        if (!errors.isEmpty()) {
            return ResponseDTO.<TeacherCreateDTO>builder()
                    .code(-1)
                    .message("Student validation error")
                    .build();
        }

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found: " + teacherId));
        teacher.setPhoneNumber(teacherCreateDTO.getPhoneNumber());
        teacher.setSubject(teacherCreateDTO.getSubject());
        teacher.setUpdatedAt(LocalDateTime.now());
        teacherRepository.save(teacher);

        return ResponseDTO.<TeacherCreateDTO>builder()
                .code(200)
                .success(true)
                .message("UPDATED")
                .data(teacherMapper.toDto(teacher))
                .build();
    }

    @Override
    public ResponseDTO<TeacherCreateDTO> deleteTeacher(@NonNull Integer teacherId) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        System.out.println("teacherOptional = " + teacherOptional);
        if (teacherOptional.isPresent()) {
            Teacher teacher = teacherOptional.get();
            studentRepository.deleteStudentsByTeacherId(teacher.getId());
            teacherRepository.delete(teacher);

            return ResponseDTO.<TeacherCreateDTO>builder()
                    .success(true)
                    .message("Deleted")
                    .data(teacherMapper.toDto(teacher))
                    .build();
        }
        return ResponseDTO.<TeacherCreateDTO>builder()
                .message("Teacher not found")
                .build();
    }
}
