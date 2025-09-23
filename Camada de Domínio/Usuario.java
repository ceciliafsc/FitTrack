package com.fittrack.domain.entity;

import com.fittrack.domain.exception.DomainException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Entidade Usuario - camada de domínio
 */
public class Usuario {
    private final UUID id;
    private String nome;
    private String email;
    private String senhaHash; 
    private Integer idade;
    private Double pesoKg;
    private Double alturaM;
    private String sexo; // "M", "F", "Outro" 
    private final List<Atividade> atividades = new ArrayList<>();
    private final List<Meta> metas = new ArrayList<>();
    private NotificacaoConfiguracao notificacaoConfiguracao = new NotificacaoConfiguracao();

    // Construtor
    public Usuario(String nome, String email, String senhaHash) {
        if (nome == null || nome.isBlank() || email == null || email.isBlank() || senhaHash == null)
            throw new DomainException("Nome, email e senha são obrigatórios.");
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
        this.senhaHash = senhaHash;
    }

    // getters e setters
    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) throw new DomainException("Nome inválido.");
        this.nome = nome;
    }
    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email == null || email.isBlank()) throw new DomainException("Email inválido.");
        this.email = email;
    }
    public String getSenhaHash() { return senhaHash; }
    public void setSenhaHash(String senhaHash) {
        if (senhaHash == null || senhaHash.length() < 8) throw new DomainException("Senha inválida.");
        this.senhaHash = senhaHash;
    }
    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }
    public Double getPesoKg() { return pesoKg; }
    public void setPesoKg(Double pesoKg) { this.pesoKg = pesoKg; }
    public Double getAlturaM() { return alturaM; }
    public void setAlturaM(Double alturaM) { this.alturaM = alturaM; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public List<Atividade> getAtividades() { return Collections.unmodifiableList(atividades); }
    public List<Meta> getMetas() { return Collections.unmodifiableList(metas); }
    public NotificacaoConfiguracao getNotificacaoConfiguracao() { return notificacaoConfiguracao; }
    public void setNotificacaoConfiguracao(NotificacaoConfiguracao notificacaoConfiguracao) {
        this.notificacaoConfiguracao = notificacaoConfiguracao;
    }

    // métodos de negócio
    public void adicionarAtividade(Atividade atividade) {
        if (atividade == null) throw new DomainException("Atividade nula.");
        atividades.add(atividade);
    }

    public boolean removerAtividade(UUID atividadeId) {
        return atividades.removeIf(a -> a.getId().equals(atividadeId));
    }

    public List<Atividade> listarAtividadesPorTipo(String tipoNome) {
        return atividades.stream()
                .filter(a -> a.getTipo().getNome().equalsIgnoreCase(tipoNome))
                .sorted(Comparator.comparing(Atividade::getData).reversed())
                .collect(Collectors.toList());
    }

    public double calcularDistanciaTotalPorTipo(String tipoNome) {
        return atividades.stream()
                .filter(a -> a.getTipo().getNome().equalsIgnoreCase(tipoNome))
                .mapToDouble(a -> Optional.ofNullable(a.getDistanciaKm()).orElse(0.0))
                .sum();
    }

    public void adicionarMeta(Meta meta) {
        if (meta == null) throw new DomainException("Meta nula.");
        metas.add(meta);
    }

    public boolean removerMeta(UUID metaId) {
        return metas.removeIf(m -> m.getId().equals(metaId));
    }

    /**
     * Atualiza progresso das metas do usuário (verifica atividades recentes e marca metas concluídas se atingidas)
     */
    public void atualizarProgressoMetas() {
        for (Meta meta : metas) {
            meta.atualizarProgresso(this.atividades);
        }
    }
}

