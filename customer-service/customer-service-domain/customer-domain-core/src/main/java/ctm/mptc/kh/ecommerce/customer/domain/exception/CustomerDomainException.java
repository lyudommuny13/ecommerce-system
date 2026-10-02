package ctm.mptc.kh.ecommerce.customer.domain.exception;

import ctm.mptc.kh.ecommerce.domain.exception.DomainException;

public class CustomerDomainException extends DomainException {
    public CustomerDomainException(String message) {
        super(message);
    }

    public CustomerDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
