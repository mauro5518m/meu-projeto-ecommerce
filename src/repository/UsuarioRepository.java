package repository;

import model.Usuario;
import java.sql.*;
import java.util.List;

public class UsuarioRepository {
    public void salvar(Usuario u) {
        String sql = "INSERT INTO usuario (nome, email, senha, endereco, telefone) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenha());
            stmt.setString(4, u.getEndereco());
            stmt.setString(5, u.getTelefone());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void atualizar(Usuario u) {
        String sql = "UPDATE usuario SET nome=?, email=?, senha=?, endereco=?, telefone=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenha());
            stmt.setString(4, u.getEndereco());
            stmt.setString(5, u.getTelefone());
            stmt.setInt(6, u.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<Usuario> listarTodos() {

        return List.of();
    }
}