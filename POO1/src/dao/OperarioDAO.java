package dao;

import entity.Operario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperarioDAO {
    private Connection conexao;

    public OperarioDAO() {
        try {
            this.conexao = ConexaoDB.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Operario operario) {
        String sql = "INSERT INTO operario (id_operario, nome_operario, funcao) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, operario.getIdOperario());
            stmt.setString(2, operario.getNomeOperario());
            stmt.setString(3, operario.getFuncao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Operario operario) {
        String sql = "UPDATE operario SET nome_operario = ?, funcao = ? WHERE id_operario = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, operario.getNomeOperario());
            stmt.setString(2, operario.getFuncao());
            stmt.setInt(3, operario.getIdOperario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idOperario) {
        String sql = "DELETE FROM operario WHERE id_operario = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idOperario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Operario buscar(int idOperario) {
        String sql = "SELECT * FROM operario WHERE id_operario = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idOperario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Operario(
                    rs.getInt("id_operario"),
                    rs.getString("nome_operario"),
                    rs.getString("funcao")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Operario> listar() {
        String sql = "SELECT * FROM operario";
        List<Operario> operarios = new ArrayList<>();
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                operarios.add(new Operario(
                    rs.getInt("id_operario"),
                    rs.getString("nome_operario"),
                    rs.getString("funcao")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operarios;
    }
}
