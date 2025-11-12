package com.fittrack.persistence.repository;

import com.fittrack.domain.entity.Meta;
import com.fittrack.persistence.config.DatabaseConnection;

import java.sql.*;
import java.util.*;

public class MetaRepository {

    public void salvar(Meta meta, UUID usuarioId) {
        String sql = "INSERT INTO meta (id, usuario_id, tipo_atividade_id, tipo_meta, valor_meta, recorrencia, periodo_inicio, periodo_fim, concluida) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, meta.getId());
            stmt.setObject(2, usuarioId);
            stmt.setObject(3, meta.getTipoAtividade().getId());
            stmt.setString(4, meta.getTipoMeta().name());
            stmt.setDouble(5, meta.getValorMeta());
            stmt.setString(6, meta.getRecorrencia().name());
            stmt.setDate(7, Date.valueOf(meta.getPeriodoInicio()));
            stmt.setDate(8, Date.valueOf(meta.getPeriodoFim()));
            stmt.setBoolean(9, meta.isConcluidaNoPeriodoAtual());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
