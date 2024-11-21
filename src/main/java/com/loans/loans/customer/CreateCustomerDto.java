package com.loans.loans.customer;

import java.math.BigDecimal;

public record CreateCustomerDto (
    String name,
    Integer age,
    BigDecimal income,
    String cpf,
    String location
) {}
