package ctm.mptc.kh.ecommerce.payment.domain.port.output;

import ctm.mptc.kh.ecommerce.payment.domain.entity.CreditHistory;

public interface CreditHistoryRepository {
    CreditHistory save(CreditHistory creditHistory);
}
