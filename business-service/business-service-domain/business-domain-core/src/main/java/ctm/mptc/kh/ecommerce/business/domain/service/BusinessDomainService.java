package ctm.mptc.kh.ecommerce.business.domain.service;

import ctm.mptc.kh.ecommerce.business.domain.entity.Business;
import ctm.mptc.kh.ecommerce.business.domain.event.OrderApprovalEvent;

import java.util.List;

public interface BusinessDomainService {
    OrderApprovalEvent validateOrder(Business business, List<String> failureMessages);
}
