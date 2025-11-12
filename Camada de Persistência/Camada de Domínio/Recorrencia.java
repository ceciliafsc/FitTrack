package com.fittrack.domain.enums;

import java.time.LocalDate;

/**
 * Períodos de recorrência e cálculo de fim de período
 */
public enum Recorrencia {
    DIARIA {
        @Override
        public LocalDate calcularFimDoPeriodo(LocalDate inicio) {
            return inicio;
        }
    },
    SEMANAL {
        @Override
        public LocalDate calcularFimDoPeriodo(LocalDate inicio) {
            return inicio.plusDays(6);
        }
    },
    MENSAL {
        @Override
        public LocalDate calcularFimDoPeriodo(LocalDate inicio) {
            return inicio.plusMonths(1).minusDays(1);
        }
    };

    public abstract LocalDate calcularFimDoPeriodo(LocalDate inicio);
}
