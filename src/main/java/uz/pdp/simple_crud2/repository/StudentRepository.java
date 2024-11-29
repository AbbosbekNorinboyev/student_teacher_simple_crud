package uz.pdp.simple_crud2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.simple_crud2.entity.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query(value = "select * from student as s where s.id=?1 and id=?2 ", nativeQuery = true)
    Optional<Student> getByStudentIdAndTeacherId(Integer studentId, Integer teacherId);

    @Query(value = "delete from student as t where t.id=?1 ", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteStudentsByTeacherId(Integer teacherId);
}