package com.fittrack.domain.enums;

/**
 * Intensidade da atividade e MET aproximado para estimativa calórica
 */
public enum Intensidade {
    BAIXA(3.0),
    MODERADA(6.0),
    ALTA(8.0);

    private final double met;

    Intensidade(double met) { this.met = met; }
    public double getMet() { return met; }
}
