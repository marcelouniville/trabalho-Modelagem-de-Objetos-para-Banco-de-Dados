package dao;

import entity.Equipamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoDAO {
    private Connection conexao;

    public EquipamentoDAO() {
        try {
            this.conexao = ConexaoDB.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Equipamento equipamento) {
        String sql = "INSERT INTO equipamento (id_equipamento, nome_equipamento, tipo) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, equipamento.getIdEquipamento());
            stmt.setString(2, equipamento.getNomeEquipamento());
            stmt.setString(3, equipamento.getTipo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Equipamento equipamento) {
        String sql = "UPDATE equipamento SET nome_equipamento = ?, tipo = ? WHERE id_equipamento = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, equipamento.getNomeEquipamento());
            stmt.setString(2, equipamento.getTipo());
            stmt.setInt(3, equipamento.getIdEquipamento());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idEquipamento) {
        String sql = "DELETE FROM equipamento WHERE id_equipamento = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idEquipamento);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Equipamento buscar(int idEquipamento) {
        String sql = "SELECT * FROM equipamento WHERE id_equipamento = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idEquipamento);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Equipamento(
                    rs.getInt("id_equipamento"),
                    rs.getString("nome_equipamento"),
                    rs.getString("tipo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Equipamento> listar() {
        String sql = "SELECT * FROM equipamento";
        List<Equipamento> equipamentos = new ArrayList<>();
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                equipamentos.add(new Equipamento(
                    rs.getInt("id_equipamento"),
                    rs.getString("nome_equipamento"),
                    rs.getString("tipo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipamentos;
    }
}
