package com.loans.loans.customer_loan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loans.loans.customer.Customer;
import com.loans.loans.customer.CustomerRepository;
import com.loans.loans.loan.Loan;
import com.loans.loans.loan.LoanDto;
import com.loans.loans.loan.LoanRepository;
import com.loans.loans.loan.LoanType;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerLoanService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CustomerLoanRepository customerLoanRepository;

    public List<CustomerLoanDto> getAllCustomerLoans() {
        return customerLoanRepository.findAll()
            .stream()
            .map(customerLoan -> {
                return new CustomerLoanDto(
                    customerLoan.getCustomer().getId(),
                    customerLoan.getLoan().getId(), 
                    customerLoan.getAmount(), 
                    customerLoan.getStatus()
                );
            })
            .collect(Collectors.toList());
    }

    public List<LoanDto> getLoansAvailableToCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        
        return loanRepository.findAll()
            .stream()
            .filter(loan -> {
                return this.isLoanAvailableToCustomer(customer, loan);
            })
            .map(loan -> {
                return new LoanDto(loan.getId(), loan.getType(), loan.getInterestRate());
            })
            .collect(Collectors.toList());
        
    }

    public void createCustomerLoan(CreateCustomerLoanDto dto) {
        Customer customer = customerRepository.findById(dto.customerId())
            .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        Loan loan = loanRepository.findById(dto.loanId())
            .orElseThrow(() -> new EntityNotFoundException("Loan not found"));

        if (!isLoanAvailableToCustomer(customer, loan)) {
            throw new IllegalArgumentException("This loan is not available.");
        }

        CustomerLoan customerLoan = CustomerLoan.builder()
            .customer(customer)
            .loan(loan)
            .amount(dto.amount())
            .issuedDate(LocalDate.now())
            .status(LoanStatus.PENDING)
            .build();
        
        customerLoanRepository.save(customerLoan);
    }

    private boolean isLoanAvailableToCustomer(Customer customer, Loan loan) {
        if (loan.getType() == LoanType.CONSIGNMENT && customer.getIncome().compareTo(BigDecimal.valueOf(5000)) < 0) {
            return false;
        }

        if (
            loan.getType() == LoanType.GUARANTEED &&
            customer.getIncome().compareTo(BigDecimal.valueOf(3000)) > 0 &&
            customer.getIncome().compareTo(BigDecimal.valueOf(5000)) < 0
        ) {
            if (customer.getAge() >= 30 || !"SP".equals(customer.getLocation().getValue())) {
                return false;
            }
        }

        if (
            loan.getType() == LoanType.PERSONAL &&
            customer.getIncome().compareTo(BigDecimal.valueOf(3000)) > 0 &&
            customer.getIncome().compareTo(BigDecimal.valueOf(5000)) < 0
        ) {
            if (customer.getAge() >= 30 || !"SP".equals(customer.getLocation().getValue())) {
                return false;
            }
        }

        return true;
    }
}
