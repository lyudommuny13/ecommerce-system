package ctm.mptc.kh.ecommerce.order.domain.exception;

import ctm.mptc.kh.ecommerce.domain.exception.DomainException;

public class OrderDomainException extends DomainException {
    public OrderDomainException(String message) {
        super(message);
    }

    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
