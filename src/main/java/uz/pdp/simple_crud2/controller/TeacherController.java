package uz.pdp.simple_crud2.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.simple_crud2.dto.ResponseDTO;
import uz.pdp.simple_crud2.dto.TeacherCreateDTO;
import uz.pdp.simple_crud2.entity.Teacher;
import uz.pdp.simple_crud2.service.impl.TeacherServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherServiceImpl teacherService;

    @PostMapping
    ResponseDTO<Teacher> createTeacher(@RequestBody @Valid TeacherCreateDTO teacherCreateDTO) {
        return teacherService.createTeacher(teacherCreateDTO);
    }

    @GetMapping("/{id}")
    ResponseDTO<Teacher> getTeacher(@PathVariable Integer id) {
        return teacherService.getTeacher(id);
    }

    @GetMapping
    ResponseDTO<List<Teacher>> getAllTeacher() {
        return teacherService.getAllTeacher();
    }

    @PutMapping("/{teacherId}")
    ResponseDTO<TeacherCreateDTO> updateTeacher(@RequestBody @Valid TeacherCreateDTO teacherCreateDTO,
                                       @PathVariable Integer teacherId) {
        return teacherService.updateTeacher(teacherCreateDTO, teacherId);
    }

    @DeleteMapping("/{teacherId}")
    ResponseDTO<TeacherCreateDTO> deleteTeacher(@PathVariable Integer teacherId) {
        return teacherService.deleteTeacher(teacherId);
    }
}
