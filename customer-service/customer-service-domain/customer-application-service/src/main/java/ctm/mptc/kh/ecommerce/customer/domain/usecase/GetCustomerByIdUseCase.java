package ctm.mptc.kh.ecommerce.customer.domain.usecase;

import ctm.mptc.kh.ecommerce.customer.domain.dto.CustomerResult;
import ctm.mptc.kh.ecommerce.customer.domain.exception.CustomerNotFoundException;
import ctm.mptc.kh.ecommerce.customer.domain.mapper.CustomerDataMapper;
import ctm.mptc.kh.ecommerce.customer.domain.port.ouput.CustomerRepository;
import ctm.mptc.kh.ecommerce.domain.valueobject.CustomerId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetCustomerByIdUseCase {

    private final CustomerRepository customerRepository;
    private final CustomerDataMapper customerDataMapper;

    public CustomerResult execute(UUID customerId) {
        log.info("Execute GetCustomerByIdUseCase : {}", customerId);
        return customerRepository.findById(new CustomerId(customerId))
                .map(customerDataMapper::toCustomerResult)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + customerId));
    }
}
