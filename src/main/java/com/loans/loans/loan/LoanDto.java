package com.loans.loans.loan;

public record LoanDto (
    Long id,
    LoanType type,
    Double interestRate
) {}
