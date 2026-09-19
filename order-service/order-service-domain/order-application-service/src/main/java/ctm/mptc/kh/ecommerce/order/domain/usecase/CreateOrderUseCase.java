package ctm.mptc.kh.ecommerce.order.domain.usecase;

import ctm.mptc.kh.ecommerce.order.domain.dto.CreateOrderCommand;
import ctm.mptc.kh.ecommerce.order.domain.dto.CreateOrderResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class CreateOrderUseCase {
    public CreateOrderResult execute(CreateOrderCommand createOrderCommand) {
        log.info("Executing CreateOrderUseCase: {}", createOrderCommand);

        // Validate customer

        return new CreateOrderResult(UUID.randomUUID());
    }
}