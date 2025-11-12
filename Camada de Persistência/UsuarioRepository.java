

import com.fittrack.domain.entity.Usuario;
import com.fittrack.persistence.config.DatabaseConnection;

import java.sql.*;
import java.util.*;

public class UsuarioRepository {

    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuario (id, nome, email, senha_hash, idade, peso_kg, altura_m, sexo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, usuario.getId());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenhaHash());
            stmt.setObject(5, usuario.getIdade());
            stmt.setObject(6, usuario.getPesoKg());
            stmt.setObject(7, usuario.getAlturaM());
            stmt.setString(8, usuario.getSexo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuario WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha_hash")
                );
                u.setIdade(rs.getInt("idade"));
                u.setPesoKg(rs.getDouble("peso_kg"));
                u.setAlturaM(rs.getDouble("altura_m"));
                u.setSexo(rs.getString("sexo"));
                return Optional.of(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha_hash")
                );
                u.setIdade(rs.getInt("idade"));
                u.setPesoKg(rs.getDouble("peso_kg"));
                u.setAlturaM(rs.getDouble("altura_m"));
                u.setSexo(rs.getString("sexo"));
                usuarios.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}
