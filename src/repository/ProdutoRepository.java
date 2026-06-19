package repository;

import model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    public void salvar(Produto p) {
        String sql = "INSERT INTO produtos (nome, descricao, preco, categoria, quantidade_estoque) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setDouble(3, p.getPreco());
            stmt.setString(4, p.getCategoria());
            stmt.setInt(5, p.getQuantidadeEstoque());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void atualizar(Produto p) {
        String sql = "UPDATE produtos SET nome=?, descricao=?, preco=?, categoria=?, quantidade_estoque=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setDouble(3, p.getPreco());
            stmt.setString(4, p.getCategoria());
            stmt.setInt(5, p.getQuantidadeEstoque());
            stmt.setInt(6, p.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public Iterable<? extends Produto> listarTodos() {
        return List.of();}
}