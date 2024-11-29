package uz.pdp.simple_crud2.validation;

import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;
import uz.pdp.simple_crud2.dto.ErrorDTO;
import uz.pdp.simple_crud2.dto.StudentCreateDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentValidation {
    ArrayList<ErrorDTO> errors = new ArrayList<>();

    public List<ErrorDTO> errorDTOS(StudentCreateDTO studentCreateDTO) {
        if (StringUtils.isBlank(studentCreateDTO.getName())) {
            errors.add(new ErrorDTO("name", "can not be null or empty"));
        }
        if (StringUtils.isBlank(studentCreateDTO.getPhoneNumber())) {
            errors.add(new ErrorDTO("phoneNumber", "can not be null or empty"));
        }
        return errors;
    }
}
