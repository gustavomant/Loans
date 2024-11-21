package com.loans.loans.customer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    
    public void createCustomer(CreateCustomerDto dto) {
        Cpf cpf = new Cpf(dto.cpf());
        Location location = new Location(dto.location());

        Customer customer = Customer.builder()
            .name(dto.name())
            .age(dto.age())
            .income(dto.income())
            .cpf(cpf)
            .location(location)
            .build();

        customerRepository.save(customer);
    }

    public List<CustomerDto> getAllCustomers() {
        return customerRepository
            .findAll()
            .stream()
            .map(customer -> {
                return new CustomerDto(
                    customer.getId(),
                    customer.getName(),
                    customer.getAge(),
                    customer.getCpf(),
                    customer.getLocation()    
                );
            })
            .collect(Collectors.toList());
    }
}
