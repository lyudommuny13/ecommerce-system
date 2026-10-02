package ctm.mptc.kh.ecommerce.customer.domain.usecase;

import ctm.mptc.kh.ecommerce.customer.domain.dto.CreateCustomerCommand;
import ctm.mptc.kh.ecommerce.customer.domain.dto.CreateCustomerResult;
import ctm.mptc.kh.ecommerce.customer.domain.entity.Customer;
import ctm.mptc.kh.ecommerce.customer.domain.event.CustomerCreatedEvent;
import ctm.mptc.kh.ecommerce.customer.domain.exception.CustomerAlreadyExistsException;
import ctm.mptc.kh.ecommerce.customer.domain.mapper.CustomerDataMapper;
import ctm.mptc.kh.ecommerce.customer.domain.port.ouput.CustomerRepository;
import ctm.mptc.kh.ecommerce.customer.domain.service.CustomerDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateCustomerUseCase {
    private final CustomerDomainService customerDomainService; // business rules (from customer-domain-core)
    private final CustomerRepository customerRepository;
    private final CustomerDataMapper customerDataMapper;

    @Transactional // everything below runs in one DB transaction: if anything fails, nothing is saved
    public CreateCustomerResult execute(CreateCustomerCommand createCustomerCommand) {
        log.info("Execute CreateCustomerUseCase : {}", createCustomerCommand);

        if (customerRepository.existsByUsername(createCustomerCommand.username())) {
            throw new CustomerAlreadyExistsException("Username already exists");
        }
        if (customerRepository.existsByEmail(createCustomerCommand.email())) {
            throw new CustomerAlreadyExistsException("Email already exists");
        }

        Customer customer = customerDataMapper.createCustomerCommandToCustomer(createCustomerCommand);

        CustomerCreatedEvent customerCreatedEvent = customerDomainService.validateAndInitiateCustomer(customer);

        Customer savedCustomer = customerRepository.save(customer);

        log.info("Customer created with id: {} at {}",
                savedCustomer.getId().value(), customerCreatedEvent.getCreatedAt());

        return new CreateCustomerResult(savedCustomer.getId().value());
    }
}
