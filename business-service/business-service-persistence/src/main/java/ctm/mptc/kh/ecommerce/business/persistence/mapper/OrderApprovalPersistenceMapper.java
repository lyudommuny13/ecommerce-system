package ctm.mptc.kh.ecommerce.business.persistence.mapper;

import ctm.mptc.kh.ecommerce.business.domain.entity.OrderApproval;
import ctm.mptc.kh.ecommerce.business.persistence.entity.OrderApprovalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderApprovalPersistenceMapper {

    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "businessId.value", target = "businessId")
    @Mapping(source = "orderId.value", target = "orderId")
    @Mapping(source = "orderApprovalStatus", target = "status")
    OrderApprovalEntity orderApprovalToOrderApprovalEntity(OrderApproval orderApproval);

    @Mapping(target = "id.value", source = "id")
    @Mapping(target = "businessId.value", source = "businessId")
    @Mapping(target = "orderId.value", source = "orderId")
    @Mapping(target = "orderApprovalStatus", source = "status")
    OrderApproval orderApprovalEntityToOrderApproval(OrderApprovalEntity orderApprovalEntity);
}
