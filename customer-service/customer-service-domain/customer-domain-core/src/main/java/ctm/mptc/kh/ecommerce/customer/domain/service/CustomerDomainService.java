package ctm.mptc.kh.ecommerce.customer.domain.service;

import ctm.mptc.kh.ecommerce.customer.domain.entity.Customer;
import ctm.mptc.kh.ecommerce.customer.domain.event.CustomerCreatedEvent;
import ctm.mptc.kh.ecommerce.customer.domain.event.CustomerDeactivatedEvent;
import ctm.mptc.kh.ecommerce.customer.domain.event.CustomerUpdatedEvent;
import ctm.mptc.kh.ecommerce.domain.valueobject.Email;
import ctm.mptc.kh.ecommerce.domain.valueobject.PhoneNumber;

public interface CustomerDomainService {
    CustomerCreatedEvent validateAndInitiateCustomer(Customer customer);

    CustomerUpdatedEvent updateCustomer(Customer customer, String familyName, String givenName,
                                        Email email, PhoneNumber phoneNumber);

    CustomerDeactivatedEvent deactivateCustomer(Customer customer);
}
