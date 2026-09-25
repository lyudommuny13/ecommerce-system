package ctm.mptc.kh.ecommerce.order.domain.mapper;

import ctm.mptc.kh.ecommerce.order.domain.dto.CommandOrderItem;
import ctm.mptc.kh.ecommerce.order.domain.dto.CreateOrderCommand;
import ctm.mptc.kh.ecommerce.order.domain.entity.Order;
import ctm.mptc.kh.ecommerce.order.domain.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDomainMapper {

    @Mapping(source = "customerId", target = "customerId.value")
    @Mapping(source = "businessId", target = "businessId.value")
    @Mapping(source = "price", target = "price.amount")
//    @Mapping(source = "orderAddress", target = "deliveryAddress")
    Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand);

    @Mapping(source = "productId", target = "product.id.value")
    @Mapping(source = "price", target = "price.amount")
    @Mapping(source = "subTotal", target = "subTotal.amount")
    OrderItem commandOrderItemToOrderItem(CommandOrderItem commandOrderItem);
}
