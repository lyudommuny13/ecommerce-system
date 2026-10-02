package ctm.mptc.kh.ecommerce.customer.domain.usecase;

import ctm.mptc.kh.ecommerce.customer.domain.dto.UpdateCustomerCommand;
import ctm.mptc.kh.ecommerce.customer.domain.dto.UpdateCustomerResult;
import ctm.mptc.kh.ecommerce.customer.domain.entity.Customer;
import ctm.mptc.kh.ecommerce.customer.domain.event.CustomerUpdatedEvent;
import ctm.mptc.kh.ecommerce.customer.domain.exception.CustomerAlreadyExistsException;
import ctm.mptc.kh.ecommerce.customer.domain.exception.CustomerNotFoundException;
import ctm.mptc.kh.ecommerce.customer.domain.mapper.CustomerDataMapper;
import ctm.mptc.kh.ecommerce.customer.domain.port.ouput.CustomerRepository;
import ctm.mptc.kh.ecommerce.customer.domain.service.CustomerDomainService;
import ctm.mptc.kh.ecommerce.domain.valueobject.CustomerId;
import ctm.mptc.kh.ecommerce.domain.valueobject.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
@Slf4j
public class UpdateCustomerUseCase {
    private final CustomerDomainService customerDomainService;
    private final CustomerRepository customerRepository;
    private final CustomerDataMapper customerDataMapper;

    @Transactional
    public UpdateCustomerResult execute(UpdateCustomerCommand updateCustomerCommand) {
        log.info("Execute UpdateCustomerUseCase : {}", updateCustomerCommand);

        Customer customer = customerRepository.findById(new CustomerId(updateCustomerCommand.customerId()))
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer not found: " + updateCustomerCommand.customerId()));

        Email newEmail = new Email(updateCustomerCommand.email());
        if (!newEmail.equals(customer.getEmail()) && customerRepository.existsByEmail(newEmail.value())) {
            throw new CustomerAlreadyExistsException("Email already exists");
        }

        CustomerUpdatedEvent customerUpdatedEvent = customerDomainService.updateCustomer(customer,
                updateCustomerCommand.familyName(),
                updateCustomerCommand.givenName(),
                newEmail,
                customerDataMapper.toPhoneNumber(updateCustomerCommand.phoneNumber()));
        Customer savedCustomer = customerRepository.save(customer);

        log.info("Customer updated with id: {} at {}",
                savedCustomer.getId().value(), customerUpdatedEvent.getUpdatedAt());
        return new UpdateCustomerResult(savedCustomer.getId().value());
    }
}
