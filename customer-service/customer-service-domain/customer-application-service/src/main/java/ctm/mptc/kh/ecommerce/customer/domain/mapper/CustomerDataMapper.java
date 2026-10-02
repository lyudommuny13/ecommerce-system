package ctm.mptc.kh.ecommerce.customer.domain.mapper;

import ctm.mptc.kh.ecommerce.customer.domain.dto.CreateCustomerCommand;
import ctm.mptc.kh.ecommerce.customer.domain.entity.Customer;
import ctm.mptc.kh.ecommerce.domain.valueobject.Email;
import ctm.mptc.kh.ecommerce.domain.valueobject.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataMapper {
    // id and status are not set here: the domain sets them in initiateCustomer()
    public Customer createCustomerCommandToCustomer(CreateCustomerCommand createCustomerCommand) {
        return Customer.builder()
                .username(createCustomerCommand.username())
                .familyName(createCustomerCommand.familyName())
                .givenName(createCustomerCommand.givenName())
                .email(new Email(createCustomerCommand.email()))
                .phoneNumber(toPhoneNumber(createCustomerCommand.phoneNumber()))
                .build();
    }

    public PhoneNumber toPhoneNumber(String phoneNumber) {
        return phoneNumber == null ? null : new PhoneNumber(phoneNumber);
    }
}
