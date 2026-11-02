package ctm.mptc.kh.ecommerce.user.exception;

import ctm.mptc.kh.ecommerce.restapi.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class UserHybridDataInconsistencyException extends GlobalException {

    public UserHybridDataInconsistencyException(HttpStatus status, String message) {
        super(status, message);
    }
}
