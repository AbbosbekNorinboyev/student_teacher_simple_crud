package uz.pdp.simple_crud2.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import uz.pdp.simple_crud2.dto.ErrorDTO;
import uz.pdp.simple_crud2.dto.ResponseDTO;
import uz.pdp.simple_crud2.dto.TeacherCreateDTO;
import uz.pdp.simple_crud2.entity.Teacher;
import uz.pdp.simple_crud2.exception.ResourceNotFoundException;
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
@Slf4j
public class TeacherServiceImpl implements TeacherService {
    private final TeacherMapper teacherMapper;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final TeacherValidation teacherValidation;

    @Override
    public ResponseDTO<Teacher> createTeacher(@NonNull TeacherCreateDTO teacherCreateDTO) {
        List<ErrorDTO> errors = teacherValidation.validate(teacherCreateDTO);
        if (!errors.isEmpty()) {
            log.info("Teacher validation error");
            return ResponseDTO.<Teacher>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("Validation error")
                    .success(false)
                    .errors(errors)
                    .build();
        }
        Teacher teacher = teacherMapper.toEntity(teacherCreateDTO);
        teacherRepository.save(teacher);
        log.info("Teacher successfully created");
        return ResponseDTO.<Teacher>builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Successfully saved")
                .data(teacher)
                .build();
    }

    @Override
    public ResponseDTO<Teacher> getTeacher(@NonNull Integer id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found: " + id));
        log.info("Teacher successfully found");
        return ResponseDTO.<Teacher>builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("OK")
                .data(teacher)
                .build();
    }

    @Override
    public ResponseDTO<List<Teacher>> getAllTeacher() {
        List<Teacher> teachers = teacherRepository.findAll();
        log.info("Teacher list successfully found");
        return ResponseDTO.<List<Teacher>>builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("OK")
                .data(teachers)
                .build();
    }

    @Override
    public ResponseDTO<TeacherCreateDTO> updateTeacher(@NonNull TeacherCreateDTO teacherCreateDTO,
                                                       @NonNull Integer teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found: " + teacherId));
        teacher.setPhoneNumber(teacherCreateDTO.getPhoneNumber());
        teacher.setSubject(teacherCreateDTO.getSubject());
        teacher.setUpdatedAt(LocalDateTime.now());
        teacherRepository.save(teacher);
        log.info("Teacher successfully updated");
        return ResponseDTO.<TeacherCreateDTO>builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("UPDATED")
                .data(teacherMapper.toDto(teacher))
                .build();
    }

    @Override
    public ResponseDTO<TeacherCreateDTO> deleteTeacher(@NonNull Integer teacherId) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        if (teacherOptional.isPresent()) {
            Teacher teacher = teacherOptional.get();
            studentRepository.deleteStudentsByTeacherId(teacher.getId());
            teacherRepository.delete(teacher);
            log.info("Teacher and Student successfully deleted");
            return ResponseDTO.<TeacherCreateDTO>builder()
                    .code(HttpStatus.OK.value())
                    .success(true)
                    .message("Deleted")
                    .data(teacherMapper.toDto(teacher))
                    .build();
        }
        return ResponseDTO.<TeacherCreateDTO>builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message("Teacher not found")
                .build();
    }
}
