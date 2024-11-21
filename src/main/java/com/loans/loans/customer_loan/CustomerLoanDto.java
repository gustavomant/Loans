package com.loans.loans.customer_loan;

import java.math.BigDecimal;

public record CustomerLoanDto (
    Long customerId,
    Long loanId,
    BigDecimal amount,
    LoanStatus loanStatus
) {}
