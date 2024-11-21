package com.loans.loans.customer_loan;

import java.math.BigDecimal;

public record CreateCustomerLoanDto (
    Long customerId,
    Long loanId,
    BigDecimal amount
) {}
