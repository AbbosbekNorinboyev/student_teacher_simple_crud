package uz.pdp.simple_crud2.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import uz.pdp.simple_crud2.dto.ErrorDTO;
import uz.pdp.simple_crud2.dto.ResponseDTO;
import uz.pdp.simple_crud2.dto.StudentCreateDTO;
import uz.pdp.simple_crud2.dto.TeacherCreateDTO;
import uz.pdp.simple_crud2.entity.Student;
import uz.pdp.simple_crud2.entity.Teacher;
import uz.pdp.simple_crud2.exception.ResourceNotFoundException;
import uz.pdp.simple_crud2.mapper.StudentMapper;
import uz.pdp.simple_crud2.repository.StudentRepository;
import uz.pdp.simple_crud2.repository.TeacherRepository;
import uz.pdp.simple_crud2.service.StudentService;
import uz.pdp.simple_crud2.validation.StudentValidation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final StudentValidation studentValidation;

    @Override
    public ResponseDTO<Student> createStudent(@NonNull StudentCreateDTO studentCreateDTO,
                                              @NonNull Integer teacherId) {
        List<ErrorDTO> errors = studentValidation.validate(studentCreateDTO);
        if (!errors.isEmpty()){
            return ResponseDTO.<Student>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("Validation error")
                    .success(false)
                    .errors(errors)
                    .build();
        }
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found: " + teacherId));
        Student student = studentMapper.toEntity(studentCreateDTO);
        student.setTeacher(teacher);
        studentRepository.save(student);
        return ResponseDTO.<Student>builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Successfully saved")
                .data(student)
                .build();
    }

    @Override
    public ResponseDTO<Student> getStudent(@NonNull Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found: " + id));
        System.out.println("student = " + student);
        return ResponseDTO.<Student>builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("OK")
                .data(student)
                .build();
    }

    @Override
    public ResponseDTO<List<Student>> getAllStudent() {
        List<Student> students = studentRepository.findAll();
        return ResponseDTO.<List<Student>>builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("OK")
                .data(students)
                .build();
    }

    @Override
    public ResponseEntity<ResponseDTO<StudentCreateDTO>> updateStudent(@NonNull StudentCreateDTO studentCreateDTO,
                                                                       @NonNull Integer studentId,
                                                                       @NonNull Integer teacherId) {
        Optional<Teacher> optional = teacherRepository.findById(teacherId);
        if (optional.isEmpty()) {
            ResponseEntity.badRequest().body(ResponseDTO.<TeacherCreateDTO>builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .message("Teacher not found")
                    .build());
        }
        Teacher teacher = optional.get();
        Optional<Student> optionalStudent = studentRepository.getByStudentIdAndTeacherId(studentId, teacher.getId());
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setPhoneNumber(studentCreateDTO.getPhoneNumber());
            student.setUpdatedAt(LocalDateTime.now());
            student.setTeacher(teacher);
            studentRepository.save(student);
            return ResponseEntity.ok().body(
                    ResponseDTO.<StudentCreateDTO>builder()
                            .code(HttpStatus.OK.value())
                            .success(true)
                            .message("UPDATED")
                            .build());
        }

        return ResponseEntity.badRequest().body(
                ResponseDTO.<StudentCreateDTO>builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message("Student not found")
                        .build());
    }
}
