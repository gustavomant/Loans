package com.loans.loans.customer;

import jakarta.persistence.Embeddable;

@Embeddable
public class Cpf {
    private String value;

    public Cpf() {}

    public Cpf(String value) {
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid CPF: " + value);
        }
        this.value = clean(value);
    }

    private boolean isValid(String cpf) {
        return cpf != null && cpf.matches("\\d{11}");
    }

    private String clean(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cpf cpf = (Cpf) o;
        return value.equals(cpf.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }
}
