package com.loans.loans.loan;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    public void createLoan(CreateLoanDto dto) {
        Loan loan = Loan.builder()
            .type(dto.type())
            .interestRate(dto.interestRate())
            .build();
        loanRepository.save(loan);
    }

    public List<LoanDto> getAllLoans() {
        return loanRepository
            .findAll()
            .stream()
            .map(loan -> {
                return new LoanDto(loan.getId(), loan.getType(), loan.getInterestRate());
            })
            .collect(Collectors.toList());
    }
}
