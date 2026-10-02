package ctm.mptc.kh.ecommerce.payment.domain.exception;

import ctm.mptc.kh.ecommerce.domain.exception.DomainException;

public class PaymentDomainException extends DomainException {
    public PaymentDomainException(String message) {
        super(message);
    }

    public PaymentDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
