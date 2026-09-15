package ctm.mptc.kh.ecommerce.restapi.exception;

import ctm.mptc.kh.ecommerce.restapi.dto.FieldErrorResponse;
import ctm.mptc.kh.ecommerce.restapi.dto.RestApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    // Genric ? : Not recommended
    public RestApiErrorResponse<?> handleException(
            MethodArgumentNotValidException e
    ) {
        return RestApiErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.name())
                .message("Data validation failed")
                .detail(extractFieldErrors(e.getFieldErrors()))
                .build();
    }

    private List<FieldErrorResponse> extractFieldErrors(
            List<FieldError> fieldErrors
    ) {
        return fieldErrors.stream()
                .map(fieldError -> new FieldErrorResponse(
                        fieldError.getField(),
                        fieldError.getCode(),
                        fieldError.getDefaultMessage()
                ))
                .toList();
    }
}
