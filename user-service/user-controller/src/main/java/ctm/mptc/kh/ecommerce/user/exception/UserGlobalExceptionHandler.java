package ctm.mptc.kh.ecommerce.user.exception;

import ctm.mptc.kh.ecommerce.restapi.dto.RestApiErrorResponse;
import ctm.mptc.kh.ecommerce.restapi.exception.GlobalException;
import ctm.mptc.kh.ecommerce.restapi.exception.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserGlobalExceptionHandler extends GlobalExceptionHandler
{
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<RestApiErrorResponse<?>> handleGlobalException(GlobalException e) {
        return ResponseEntity.status(e.getStatus())
                .body(RestApiErrorResponse.builder()
                        .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .message(e.getMessage())
                        .build());
    }
}
