package ctm.mptc.kh.ecommerce.order.domain.usecase;

import ctm.mptc.kh.ecommerce.domain.valueobject.BusinessId;
import ctm.mptc.kh.ecommerce.domain.valueobject.Money;
import ctm.mptc.kh.ecommerce.domain.valueobject.ProductId;
import ctm.mptc.kh.ecommerce.order.domain.dto.CreateOrderCommand;
import ctm.mptc.kh.ecommerce.order.domain.dto.CreateOrderResult;
import ctm.mptc.kh.ecommerce.order.domain.entity.Business;
import ctm.mptc.kh.ecommerce.order.domain.entity.Order;
import ctm.mptc.kh.ecommerce.order.domain.entity.Product;
import ctm.mptc.kh.ecommerce.order.domain.event.OrderCreatedEvent;
import ctm.mptc.kh.ecommerce.order.domain.exception.OrderDomainException;
import ctm.mptc.kh.ecommerce.order.domain.mapper.OrderDomainMapper;
import ctm.mptc.kh.ecommerce.order.domain.port.output.BusinessRepository;
import ctm.mptc.kh.ecommerce.order.domain.port.output.CustomerRepository;
import ctm.mptc.kh.ecommerce.order.domain.port.output.OrderRepository;
import ctm.mptc.kh.ecommerce.order.domain.service.OrderDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j // logging
@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final OrderDomainService orderDomainService;
    private final OrderDomainMapper orderDomainMapper;

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final BusinessRepository businessRepository;

//    @Transactional
    public CreateOrderResult execute(CreateOrderCommand createOrderCommand) {
        log.info("Executing CreateOrderUseCase: {}", createOrderCommand);

        // Validate customer
        customerRepository.findCustomer(createOrderCommand.customerId())
                .orElseThrow(() -> new OrderDomainException("Could not find customer with ID: " + createOrderCommand.customerId()));

        // Validate business
        List<Product> products = createOrderCommand.items()
                .stream()
                .map(commandOrderItem -> Product.builder()
                        .id(new ProductId(commandOrderItem.productId()))
                        .price(new Money(commandOrderItem.price()))
                        .build()
                )
                .toList();

        Business business = Business.builder()
                .id(new BusinessId(createOrderCommand.businessId()))
                .products(products)
                .build();

        business = businessRepository.findBusiness(business)
                .orElseThrow(() -> new OrderDomainException("could not find business with ID: " + createOrderCommand.businessId()));

        log.info("Business found with ID: {}", business);

        // Invoke Order Domain Logic
        Order order = orderDomainMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, business);

        log.info("Order created event: {}", orderCreatedEvent.getOrder().getId());

        // Save Order into Database
        Order saveOrder = orderRepository.saveOrder(order);
        if (saveOrder == null) {
            throw new OrderDomainException("Could not save order into database");
        }

        return new CreateOrderResult(order.getId().value());
    }
}