package uz.pdp.simple_crud2.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.simple_crud2.dto.ErrorDTO;
import uz.pdp.simple_crud2.dto.StudentCreateDTO;
import uz.pdp.simple_crud2.dto.TeacherCreateDTO;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentValidation {
    public List<ErrorDTO> validate(StudentCreateDTO studentCreateDTO) {
        List<ErrorDTO> errors = new ArrayList<>();
        if (studentCreateDTO.getPhoneNumber().length() != 13) {
            errors.add(new ErrorDTO("phoneNumber", "phoneNumber invalid"));
        }
        return errors;
    }
}
