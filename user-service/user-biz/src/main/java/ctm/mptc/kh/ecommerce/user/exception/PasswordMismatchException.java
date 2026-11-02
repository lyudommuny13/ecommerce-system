package ctm.mptc.kh.ecommerce.user.exception;

import ctm.mptc.kh.ecommerce.restapi.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class PasswordMismatchException extends GlobalException {
    public PasswordMismatchException(HttpStatus status, String message) {
        super(status, message);
    }
}
