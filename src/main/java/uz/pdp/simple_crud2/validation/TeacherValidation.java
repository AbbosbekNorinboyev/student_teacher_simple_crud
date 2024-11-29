package uz.pdp.simple_crud2.validation;

import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;
import uz.pdp.simple_crud2.dto.ErrorDTO;
import uz.pdp.simple_crud2.dto.TeacherCreateDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherValidation {
    public List<ErrorDTO> errorDTOS(TeacherCreateDTO teacherCreateDTO) {
        ArrayList<ErrorDTO> errors = new ArrayList<>();
        if (StringUtils.isBlank(teacherCreateDTO.getFullName())) {
            errors.add(new ErrorDTO("fullName", "can not be null or empty"));
        }
        if (StringUtils.isBlank(teacherCreateDTO.getPhoneNumber())) {
            errors.add(new ErrorDTO("phoneNumber", "can not be null or empty"));
        }
        if (StringUtils.isBlank(teacherCreateDTO.getSubject())) {
            errors.add(new ErrorDTO("subject", "can not be null or empty"));
        }
        return errors;
    }
}
