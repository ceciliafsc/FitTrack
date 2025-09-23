package com.fittrack.domain.entity;

import com.fittrack.domain.enums.Recorrencia;
import com.fittrack.domain.enums.TipoMeta;
import com.fittrack.domain.exception.DomainException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Entidade Meta (Meta recorrente)
 */
public class Meta {
    private final UUID id;
    private final TipoAtividade tipoAtividade;
    private final TipoMeta tipoMeta; // DISTANCIA, TEMPO, FREQUENCIA, CALORIAS
    private final double valorMeta; // valor da meta (km, minutos, vezes ou kcal)
    private final Recorrencia recorrencia;
    private LocalDate periodoInicio; // data de início do período atual
    private LocalDate periodoFim; // data de fim do período atual
    private boolean concluidaNoPeriodoAtual = false;

    public Meta(TipoAtividade tipoAtividade, TipoMeta tipoMeta, double valorMeta, Recorrencia recorrencia, LocalDate periodoInicio) {
        if (tipoAtividade == null || tipoMeta == null || recorrencia == null)
            throw new DomainException("Dados da meta incompletos.");
        if (valorMeta <= 0) throw new DomainException("Valor da meta deve ser maior que zero.");
        this.id = UUID.randomUUID();
        this.tipoAtividade = tipoAtividade;
        this.tipoMeta = tipoMeta;
        this.valorMeta = valorMeta;
        this.recorrencia = recorrencia;
        this.periodoInicio = periodoInicio;
        this.periodoFim = recorrencia.calcularFimDoPeriodo(periodoInicio);
    }

    public UUID getId() { return id; }
    public TipoAtividade getTipoAtividade() { return tipoAtividade; }
    public TipoMeta getTipoMeta() { return tipoMeta; }
    public double getValorMeta() { return valorMeta; }
    public Recorrencia getRecorrencia() { return recorrencia; }
    public LocalDate getPeriodoInicio() { return periodoInicio; }
    public LocalDate getPeriodoFim() { return periodoFim; }
    public boolean isConcluidaNoPeriodoAtual() { return concluidaNoPeriodoAtual; }

    /**
     * Atualiza o progresso com base nas atividades do usuário e marca concluída se atingiu a meta.
     */
    public void atualizarProgresso(List<Atividade> atividadesUsuario) {
        double progresso = 0.0;

        // filtra atividades do tipo e dentro do período
        for (Atividade a : atividadesUsuario) {
            LocalDate data = a.getData().toLocalDate();
            if (a.getTipo().getNome().equalsIgnoreCase(tipoAtividade.getNome())
                    && ( !data.isBefore(periodoInicio) && !data.isAfter(periodoFim) )) {
                switch (tipoMeta) {
                    case DISTANCIA:
                        progresso += a.getDistanciaKm() == null ? 0.0 : a.getDistanciaKm();
                        break;
                    case TEMPO:
                        progresso += a.getDuracaoMinutos() == null ? 0.0 : a.getDuracaoMinutos();
                        break;
                    case FREQUENCIA:
                        progresso += 1; // cada atividade conta como 1
                        break;
                    case CALORIAS:
                        progresso += a.getCaloriasEstimadas() == null ? 0.0 : a.getCaloriasEstimadas();
                        break;
                }
            }
        }

        if (progresso >= valorMeta) {
            this.concluidaNoPeriodoAtual = true;
        }
    }

    /**
     * Marca manualmente como concluída
     */
    public void marcarConcluida() {
        this.concluidaNoPeriodoAtual = true;
    }

    /**
     * Reset para novo período (chamar no início do novo período)
     */
    public void resetParaNovoPeriodo(LocalDate novoInicio) {
        this.periodoInicio = novoInicio;
        this.periodoFim = this.recorrencia.calcularFimDoPeriodo(novoInicio);
        this.concluidaNoPeriodoAtual = false;
    }
}
