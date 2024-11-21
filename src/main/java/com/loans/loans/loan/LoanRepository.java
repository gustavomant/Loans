package com.loans.loans.loan;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findByType(LoanType type);
}
