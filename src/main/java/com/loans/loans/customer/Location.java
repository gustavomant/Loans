package com.loans.loans.customer;

import java.util.Set;

import jakarta.persistence.Embeddable;

import java.util.HashSet;

@Embeddable
public class Location {
    private static final Set<String> VALID_STATES = new HashSet<>();

    static {
        VALID_STATES.add("AC"); // Acre
        VALID_STATES.add("AL"); // Alagoas
        VALID_STATES.add("AP"); // Amapá
        VALID_STATES.add("AM"); // Amazonas
        VALID_STATES.add("BA"); // Bahia
        VALID_STATES.add("CE"); // Ceará
        VALID_STATES.add("DF"); // Distrito Federal
        VALID_STATES.add("ES"); // Espírito Santo
        VALID_STATES.add("GO"); // Goiás
        VALID_STATES.add("MA"); // Maranhão
        VALID_STATES.add("MT"); // Mato Grosso
        VALID_STATES.add("MS"); // Mato Grosso do Sul
        VALID_STATES.add("MG"); // Minas Gerais
        VALID_STATES.add("PA"); // Pará
        VALID_STATES.add("PB"); // Paraíba
        VALID_STATES.add("PR"); // Paraná
        VALID_STATES.add("PE"); // Pernambuco
        VALID_STATES.add("PI"); // Piauí
        VALID_STATES.add("RJ"); // Rio de Janeiro
        VALID_STATES.add("RN"); // Rio Grande do Norte
        VALID_STATES.add("RS"); // Rio Grande do Sul
        VALID_STATES.add("RO"); // Rondônia
        VALID_STATES.add("RR"); // Roraima
        VALID_STATES.add("SC"); // Santa Catarina
        VALID_STATES.add("SP"); // São Paulo
        VALID_STATES.add("SE"); // Sergipe
        VALID_STATES.add("TO"); // Tocantins
    }

    private String value;

    public Location() {}

    public Location(String value) {
        if (value == null || !VALID_STATES.contains(value.toUpperCase())) {
            throw new IllegalArgumentException("Invalid location: " + value);
        }
        this.value = value.toUpperCase();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return value.equals(location.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }

    public static boolean isValid(String value) {
        if (value == null || !VALID_STATES.contains(value.toUpperCase())) {
            return false;
        }

        return true;
    }
}
