package com.fittrack.persistence.repository;

import com.fittrack.domain.entity.TipoAtividade;
import com.fittrack.persistence.config.DatabaseConnection;

import java.sql.*;
import java.util.*;

public class TipoAtividadeRepository {

    public void salvar(TipoAtividade tipo) {
        String sql = "INSERT INTO tipo_atividade (id, nome) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, tipo.getId());
            stmt.setString(2, tipo.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TipoAtividade> listarTodos() {
        List<TipoAtividade> tipos = new ArrayList<>();
        String sql = "SELECT * FROM tipo_atividade";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tipos.add(new TipoAtividade(rs.getString("nome")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipos;
    }
}
