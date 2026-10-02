package ctm.mptc.kh.ecommerce.payment.domain.service;

import ctm.mptc.kh.ecommerce.domain.valueobject.PaymentStatus;
import ctm.mptc.kh.ecommerce.payment.domain.entity.CreditEntry;
import ctm.mptc.kh.ecommerce.payment.domain.entity.CreditHistory;
import ctm.mptc.kh.ecommerce.payment.domain.entity.Payment;

public interface PaymentDomainService {
    CreditHistory validateAndInitiatePayment(Payment payment, CreditEntry creditEntry);

    void updatePaymentStatus(Payment payment, PaymentStatus newPaymentStatus);
}
