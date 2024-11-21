package com.loans.loans.loan;

public record CreateLoanDto (
    LoanType type,
    Double interestRate
) {}
