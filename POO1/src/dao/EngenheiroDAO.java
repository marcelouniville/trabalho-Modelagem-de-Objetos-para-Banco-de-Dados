package dao;

import entity.Engenheiro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EngenheiroDAO {
    private Connection conexao;

    public EngenheiroDAO() {
        try {
            this.conexao = ConexaoDB.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Engenheiro engenheiro) {
        String sql = "INSERT INTO engenheiro (id_engenheiro, nome_engenheiro, especialidade) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, engenheiro.getIdEngenheiro());
            stmt.setString(2, engenheiro.getNomeEngenheiro());
            stmt.setString(3, engenheiro.getEspecialidade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Engenheiro engenheiro) {
        String sql = "UPDATE engenheiro SET nome_engenheiro = ?, especialidade = ? WHERE id_engenheiro = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, engenheiro.getNomeEngenheiro());
            stmt.setString(2, engenheiro.getEspecialidade());
            stmt.setInt(3, engenheiro.getIdEngenheiro());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idEngenheiro) {
        String sql = "DELETE FROM engenheiro WHERE id_engenheiro = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idEngenheiro);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Engenheiro buscar(int idEngenheiro) {
        String sql = "SELECT * FROM engenheiro WHERE id_engenheiro = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idEngenheiro);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Engenheiro(
                    rs.getInt("id_engenheiro"),
                    rs.getString("nome_engenheiro"),
                    rs.getString("especialidade")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Engenheiro> listar() {
        String sql = "SELECT * FROM engenheiro";
        List<Engenheiro> engenheiros = new ArrayList<>();
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                engenheiros.add(new Engenheiro(
                    rs.getInt("id_engenheiro"),
                    rs.getString("nome_engenheiro"),
                    rs.getString("especialidade")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return engenheiros;
    }
}
