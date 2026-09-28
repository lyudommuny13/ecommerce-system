package ctm.mptc.kh.ecommerce.business.domain.port.output;

import ctm.mptc.kh.ecommerce.business.domain.entity.OrderApproval;

public interface OrderApprovalRepository {
    OrderApproval save(OrderApproval orderApproval);
}
