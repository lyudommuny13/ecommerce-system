package ctm.mptc.kh.ecommerce.payment.domain.port.output;

import ctm.mptc.kh.ecommerce.payment.domain.entity.Payment;

public interface PaymentRepository {
    Payment savePayment(Payment payment);
}
