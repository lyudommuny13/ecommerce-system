package ctm.mptc.kh.ecommerce.payment.domain.dto;

import ctm.mptc.kh.ecommerce.domain.valueobject.PaymentStatus;

import java.util.UUID;

public record CreatePaymentResult(
        UUID paymentId,
        PaymentStatus paymentStatus
) {
}
