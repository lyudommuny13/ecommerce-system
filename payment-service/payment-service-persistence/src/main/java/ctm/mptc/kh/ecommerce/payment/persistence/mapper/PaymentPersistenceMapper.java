package ctm.mptc.kh.ecommerce.payment.persistence.mapper;

import ctm.mptc.kh.ecommerce.payment.domain.entity.Payment;
import ctm.mptc.kh.ecommerce.payment.persistence.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentPersistenceMapper {
    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "customerId.value", target = "customerId")
    @Mapping(source = "orderId.value", target = "orderId")
    @Mapping(source = "price.amount", target = "price")
    PaymentEntity paymentToPaymentEntity(Payment payment);

    @Mapping(target = "id.value", source = "id")
    @Mapping(target = "customerId.value", source = "customerId")
    @Mapping(target = "orderId.value", source = "orderId")
    @Mapping(target = "price.amount", source = "price")
    Payment paymentEntityToPayment(PaymentEntity paymentEntity);
}
