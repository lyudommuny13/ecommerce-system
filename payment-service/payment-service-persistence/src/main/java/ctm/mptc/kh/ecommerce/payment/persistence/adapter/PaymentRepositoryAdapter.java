package ctm.mptc.kh.ecommerce.payment.persistence.adapter;

import ctm.mptc.kh.ecommerce.payment.domain.entity.Payment;
import ctm.mptc.kh.ecommerce.payment.domain.port.output.PaymentRepository;
import ctm.mptc.kh.ecommerce.payment.persistence.entity.PaymentEntity;
import ctm.mptc.kh.ecommerce.payment.persistence.mapper.PaymentPersistenceMapper;
import ctm.mptc.kh.ecommerce.payment.persistence.repository.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryAdapter implements PaymentRepository {
    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentPersistenceMapper paymentPersistenceMapper;

    @Override
    public Payment savePayment(Payment payment) {
        PaymentEntity paymentEntity = paymentPersistenceMapper.paymentToPaymentEntity(payment);
        PaymentEntity savedPaymentEntity = paymentJpaRepository.save(paymentEntity);
        return paymentPersistenceMapper.paymentEntityToPayment(savedPaymentEntity);
    }
}
