package uz.pdp.simple_crud2.service;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import uz.pdp.simple_crud2.dto.ResponseDTO;
import uz.pdp.simple_crud2.dto.StudentCreateDTO;
import uz.pdp.simple_crud2.entity.Student;

import java.util.List;

public interface StudentService {

    ResponseDTO<Student> createStudent(@NonNull StudentCreateDTO studentCreateDTO,
                                       @NonNull Integer teacherId);

    ResponseDTO<Student> getStudent(@NonNull Integer id);

    ResponseDTO<List<Student>> getAllStudent();

    ResponseEntity<ResponseDTO<StudentCreateDTO>> updateStudent(@NonNull StudentCreateDTO studentCreateDTO,
                                                                @NonNull Integer studentId,
                                                                @NonNull Integer teacherId);
}
