package ctm.mptc.kh.ecommerce.business.domain.event;

import ctm.mptc.kh.ecommerce.business.domain.entity.OrderApproval;
import ctm.mptc.kh.ecommerce.domain.event.DomainEvent;
import ctm.mptc.kh.ecommerce.domain.valueobject.BusinessId;

import java.time.ZonedDateTime;
import java.util.List;

public class OrderApprovalEvent implements DomainEvent<OrderApproval> {
    private final OrderApproval orderApproval;
    private final BusinessId businessId;
    private final List<String> failureMessages;
    private final ZonedDateTime createdAt;

    public OrderApprovalEvent(OrderApproval orderApproval, BusinessId businessId, List<String> failureMessages, ZonedDateTime createdAt) {
        this.orderApproval = orderApproval;
        this.businessId = businessId;
        this.failureMessages = failureMessages;
        this.createdAt = createdAt;
    }

    public OrderApproval getOrderApproval() {
        return orderApproval;
    }

    public BusinessId getBusinessId() {
        return businessId;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
