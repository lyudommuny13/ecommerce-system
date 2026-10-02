package ctm.mptc.kh.ecommerce.business.domain.entity;

import ctm.mptc.kh.ecommerce.domain.entity.BaseEntity;
import ctm.mptc.kh.ecommerce.domain.valueobject.BusinessId;
import ctm.mptc.kh.ecommerce.domain.valueobject.OrderApprovalId;
import ctm.mptc.kh.ecommerce.domain.valueobject.OrderApprovalStatus;
import ctm.mptc.kh.ecommerce.domain.valueobject.OrderId;

public class OrderApproval extends BaseEntity<OrderApprovalId> {
    private final BusinessId businessId;
    private final OrderId orderId;
    private final OrderApprovalStatus orderApprovalStatus;

    private OrderApproval(Builder builder) {
        super.setId(builder.id);
        businessId = builder.businessId;
        orderId = builder.orderId;
        orderApprovalStatus = builder.orderApprovalStatus;
    }

    public BusinessId getBusinessId() {
        return businessId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public OrderApprovalStatus getOrderApprovalStatus() {
        return orderApprovalStatus;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private OrderApprovalId id;
        private BusinessId businessId;
        private OrderId orderId;
        private OrderApprovalStatus orderApprovalStatus;

        private Builder() {
        }

        public Builder id(OrderApprovalId val) {
            id = val;
            return this;
        }

        public Builder businessId(BusinessId val) {
            businessId = val;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder orderApprovalStatus(OrderApprovalStatus val) {
            orderApprovalStatus = val;
            return this;
        }

        public OrderApproval build() {
            return new OrderApproval(this);
        }
    }
}
