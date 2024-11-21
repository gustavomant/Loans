package com.loans.loans.customer_loan;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loans.loans.loan.LoanDto;

@RestController
@RequestMapping("/customer-loan")
public class CustomerLoanController {
    @Autowired
    private CustomerLoanService customerLoanService;

    @GetMapping
    public ResponseEntity<List<CustomerLoanDto>> getAllCustomerLoans() {
        List<CustomerLoanDto> customerLoans = customerLoanService.getAllCustomerLoans();
        return ResponseEntity.status(HttpStatus.OK).body(customerLoans);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<LoanDto>> getLoansAvailableToCustomer(
        @PathVariable Long customerId
    ) {
        List<LoanDto> loans = customerLoanService.getLoansAvailableToCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(loans);
    }
    
    @PostMapping
    public ResponseEntity<Object> createCustomerLoan(
        @RequestBody CreateCustomerLoanDto dto
    ) {
        try {
            customerLoanService.createCustomerLoan(dto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception exception) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", exception.getMessage()));
        }
    }
}
