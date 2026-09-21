package ctm.mptc.kh.ecommerce.order.domain.usecase;

import ctm.mptc.kh.ecommerce.domain.valueobject.BusinessId;
import ctm.mptc.kh.ecommerce.domain.valueobject.Money;
import ctm.mptc.kh.ecommerce.domain.valueobject.ProductId;
import ctm.mptc.kh.ecommerce.order.domain.dto.CreateOrderCommand;
import ctm.mptc.kh.ecommerce.order.domain.dto.CreateOrderResult;
import ctm.mptc.kh.ecommerce.order.domain.entity.Business;
import ctm.mptc.kh.ecommerce.order.domain.entity.Product;
import ctm.mptc.kh.ecommerce.order.domain.exception.OrderDomainException;
import ctm.mptc.kh.ecommerce.order.domain.port.output.BusinessRepository;
import ctm.mptc.kh.ecommerce.order.domain.port.output.CustomerRepository;
import ctm.mptc.kh.ecommerce.order.domain.port.output.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j // logging
@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final BusinessRepository businessRepository;

    public CreateOrderResult execute(CreateOrderCommand createOrderCommand) {
        log.info("Executing CreateOrderUseCase: {}", createOrderCommand);

        // Validate customer
        customerRepository.findCustomer(createOrderCommand.customerId())
                .orElseThrow(() -> new OrderDomainException("Could not find customer with ID: {}" + createOrderCommand.customerId()));

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
                .orElseThrow(() -> new OrderDomainException("could not find business with ID: {}" + createOrderCommand.businessId()));

        log.info("Business found with ID: {}", business);
        return new CreateOrderResult(UUID.randomUUID());
    }
}