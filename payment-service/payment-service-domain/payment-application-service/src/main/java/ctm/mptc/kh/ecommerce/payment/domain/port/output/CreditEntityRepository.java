package ctm.mptc.kh.ecommerce.payment.domain.port.output;

import ctm.mptc.kh.ecommerce.domain.valueobject.CustomerId;
import ctm.mptc.kh.ecommerce.payment.domain.entity.CreditEntry;

public interface CreditEntityRepository {
    CreditEntry findByCustomerId(CustomerId customerId);

    CreditEntry save(CreditEntry creditEntry);
}
