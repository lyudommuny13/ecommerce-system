package ctm.mptc.kh.ecommerce.restapi.exception;

import org.springframework.http.HttpStatus;

public class PasswordMismatchException extends GlobalException{
    public PasswordMismatchException(HttpStatus status, String message) {
        super(status, message);
    }
}
