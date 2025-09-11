package com.fittrack.domain.entity;

import java.util.Objects;
import java.util.UUID;

/**
 * Tipo de Atividade (ex: Corrida, Caminhada, Ciclismo, Yoga)
 */
public class TipoAtividade {
    private final UUID id;
    private final String nome;

    public TipoAtividade(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome do tipo de atividade obrigatório.");
        this.id = UUID.randomUUID();
        this.nome = nome;
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoAtividade)) return false;
        TipoAtividade that = (TipoAtividade) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
