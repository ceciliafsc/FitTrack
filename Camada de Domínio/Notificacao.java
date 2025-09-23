package com.fittrack.domain.entity;

/**
 * Configurações de Notificação do usuário
 */
public class NotificacaoConfiguracao {
    private boolean lembretesAtivos = true;
    private int lembreteHoraDoDia = 8; // ex: 8h
    private int frequenciaPorSemana = 3;

    public NotificacaoConfiguracao() {}

    public boolean isLembretesAtivos() { return lembretesAtivos; }
    public void setLembretesAtivos(boolean lembretesAtivos) { this.lembretesAtivos = lembretesAtivos; }
    public int getLembreteHoraDoDia() { return lembreteHoraDoDia; }
    public void setLembreteHoraDoDia(int lembreteHoraDoDia) { this.lembreteHoraDoDia = lembreteHoraDoDia; }
    public int getFrequenciaPorSemana() { return frequenciaPorSemana; }
    public void setFrequenciaPorSemana(int frequenciaPorSemana) { this.frequenciaPorSemana = frequenciaPorSemana; }
}
