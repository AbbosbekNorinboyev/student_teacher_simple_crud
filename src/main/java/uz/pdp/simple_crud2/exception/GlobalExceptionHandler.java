package uz.pdp.simple_crud2.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.simple_crud2.dto.ErrorDTO;
import uz.pdp.simple_crud2.dto.ResponseDTO;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ResponseDTO<Void>> exception(MethodArgumentNotValidException e) {
        List<ErrorDTO> errors = e.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> {
                    String field = fieldError.getField();
                    String defaultMessage = fieldError.getDefaultMessage();
                    String rejectedValue = String.valueOf(fieldError.getRejectedValue());
                    return new ErrorDTO(
                            field,
                            String.format("defaultMessage: %s, rejectedValue: %s", defaultMessage, rejectedValue)
                    );
                }).toList();
        return ResponseEntity.badRequest().body(
                ResponseDTO.<Void>builder()
                        .code(-1)
                        .message("Validation error")
                        .errors(errors)
                        .build()
        );
    }
}
