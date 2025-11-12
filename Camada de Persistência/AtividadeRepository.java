package com.fittrack.persistence.repository;

import com.fittrack.domain.entity.Atividade;
import com.fittrack.persistence.config.DatabaseConnection;

import java.sql.*;
import java.util.*;

public class AtividadeRepository {

    public void salvar(Atividade atividade) {
        String sql = "INSERT INTO atividade (id, usuario_id, tipo_atividade_id, data, duracao_minutos, distancia_km, intensidade, calorias_estimadas) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, atividade.getId());
            stmt.setObject(2, atividade.getUsuarioId());
            stmt.setObject(3, atividade.getTipo().getId());
            stmt.setTimestamp(4, Timestamp.valueOf(atividade.getData()));
            stmt.setObject(5, atividade.getDuracaoMinutos());
            stmt.setObject(6, atividade.getDistanciaKm());
            stmt.setString(7, atividade.getIntensidade().name());
            stmt.setObject(8, atividade.getCaloriasEstimadas());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
