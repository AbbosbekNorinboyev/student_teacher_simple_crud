package uz.pdp.simple_crud2.service;

import org.springframework.lang.NonNull;
import uz.pdp.simple_crud2.dto.ResponseDTO;
import uz.pdp.simple_crud2.dto.TeacherCreateDTO;
import uz.pdp.simple_crud2.entity.Teacher;

import java.util.List;

public interface TeacherService {
    ResponseDTO<Teacher> createTeacher(@NonNull TeacherCreateDTO teacherCreateDTO);

    ResponseDTO<Teacher> getTeacher(@NonNull Integer id);

    ResponseDTO<List<Teacher>> getAllTeacher();

    ResponseDTO<TeacherCreateDTO> updateTeacher(@NonNull TeacherCreateDTO teacherCreateDTO,
                                       @NonNull Integer teacherId);

    ResponseDTO<TeacherCreateDTO> deleteTeacher(@NonNull Integer teacherId);
}
