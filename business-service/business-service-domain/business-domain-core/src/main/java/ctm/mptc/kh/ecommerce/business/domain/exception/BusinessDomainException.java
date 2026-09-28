package ctm.mptc.kh.ecommerce.business.domain.exception;

import ctm.mptc.kh.ecommerce.domain.exception.DomainException;

public class BusinessDomainException extends DomainException {
    public BusinessDomainException(String message) {
        super(message);
    }

    public BusinessDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
