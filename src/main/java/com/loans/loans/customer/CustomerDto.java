package com.loans.loans.customer;

public record CustomerDto (
    Long id,
    String name,
    Integer age,
    Cpf cpf,
    Location location
) {}
