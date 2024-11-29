package uz.pdp.simple_crud2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.simple_crud2.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}