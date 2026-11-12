package ctm.mptc.kh.ecommerce.customer.domain.usecase;

import ctm.mptc.kh.ecommerce.customer.client.dto.UserResponse;
import ctm.mptc.kh.ecommerce.customer.client.exception.UserServiceUnavailableException;
import ctm.mptc.kh.ecommerce.customer.client.feign.UserFeignClient;
import ctm.mptc.kh.ecommerce.customer.domain.dto.CreateCustomerCommand;
import ctm.mptc.kh.ecommerce.customer.domain.dto.CreateCustomerResult;
import ctm.mptc.kh.ecommerce.customer.domain.entity.Customer;
import ctm.mptc.kh.ecommerce.customer.domain.event.CustomerCreatedEvent;
import ctm.mptc.kh.ecommerce.customer.domain.exception.CustomerAlreadyExistsException;
import ctm.mptc.kh.ecommerce.customer.domain.exception.CustomerDomainException;
import ctm.mptc.kh.ecommerce.customer.domain.mapper.CustomerDataMapper;
import ctm.mptc.kh.ecommerce.customer.domain.port.ouput.CustomerRepository;
import ctm.mptc.kh.ecommerce.customer.domain.service.CustomerDomainService;
import feign.FeignException;
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
    private final UserFeignClient userFeignClient;

    @Transactional // everything below runs in one DB transaction: if anything fails, nothing is saved
    public CreateCustomerResult execute(CreateCustomerCommand createCustomerCommand) {
        log.info("Execute CreateCustomerUseCase : {}", createCustomerCommand);

        // ACL - verify user with use-case
        try {
            UserResponse userResponse = userFeignClient.getById(createCustomerCommand.userId());
            log.info("User found: {}", userResponse);
        } catch (FeignException.NotFound e) {
            throw new CustomerDomainException("User not found");
        } catch (UserServiceUnavailableException e) {
            throw new CustomerDomainException(e.getMessage());
        }

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
