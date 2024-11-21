package com.loans.loans.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.loans.loans.loan.Loan;
import com.loans.loans.loan.LoanRepository;
import com.loans.loans.loan.LoanType;

@Configuration
public class LoanSetup {
    @Autowired
    private LoanRepository loanRepository;

    @Bean
    CommandLineRunner initLoan() {
        return args -> {
            if(loanRepository.findByType(LoanType.PERSONAL).isEmpty()) {
                Loan loan = Loan.builder()
                    .type(LoanType.PERSONAL)
                    .interestRate(Double.valueOf(4))
                    .build();
                loanRepository.save(loan);
            }

            if(loanRepository.findByType(LoanType.PERSONAL).isEmpty()) {
                Loan loan = Loan.builder()
                    .type(LoanType.PERSONAL)
                    .interestRate(Double.valueOf(4))
                    .build();
                loanRepository.save(loan);
            }

            if(loanRepository.findByType(LoanType.GUARANTEED).isEmpty()) {
                Loan loan = Loan.builder()
                    .type(LoanType.GUARANTEED)
                    .interestRate(Double.valueOf(3))
                    .build();
                loanRepository.save(loan);
            }

            if(loanRepository.findByType(LoanType.CONSIGNMENT).isEmpty()) {
                Loan loan = Loan.builder()
                    .type(LoanType.CONSIGNMENT)
                    .interestRate(Double.valueOf(2))
                    .build();
                loanRepository.save(loan);
            }
        };
    }
}
