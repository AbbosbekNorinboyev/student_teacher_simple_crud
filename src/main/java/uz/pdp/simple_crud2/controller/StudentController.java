package uz.pdp.simple_crud2.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.simple_crud2.dto.ResponseDTO;
import uz.pdp.simple_crud2.dto.StudentCreateDTO;
import uz.pdp.simple_crud2.entity.Student;
import uz.pdp.simple_crud2.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseDTO<Student> createStudent(@RequestBody @Valid StudentCreateDTO studentCreateDTO,
                                              @RequestParam Integer teacherId) {
        return studentService.createStudent(studentCreateDTO, teacherId);
    }

    @GetMapping("/{id}")
    public ResponseDTO<Student> getStudent(@PathVariable Integer id) {
        return studentService.getStudent(id);
    }

    @GetMapping
    public ResponseDTO<List<Student>> getAllStudent() {
        return studentService.getAllStudent();
    }

    @PutMapping("/{studentId}/{teacherId}")
    public ResponseEntity<ResponseDTO<StudentCreateDTO>> updateStudent(@RequestBody @Valid StudentCreateDTO studentCreateDTO,
                                                                @PathVariable Integer studentId,
                                                                @PathVariable Integer teacherId) {
        return studentService.updateStudent(studentCreateDTO, studentId, teacherId);
    }
}
