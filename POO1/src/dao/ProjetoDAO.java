package dao;

import entity.Engenheiro;
import entity.Projeto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {
    private Connection conexao;

    public ProjetoDAO() {
        try {
            this.conexao = ConexaoDB.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Projeto projeto) {
        String sql = "INSERT INTO projeto (id_projeto, nome_projeto, local, data_inicio, data_termino) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, projeto.getIdProjeto());
            stmt.setString(2, projeto.getNomeProjeto());
            stmt.setString(3, projeto.getLocal());
            stmt.setString(4, projeto.getDataInicio());
            stmt.setString(5, projeto.getDataTermino());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Projeto projeto) {
        String sql = "UPDATE projeto SET nome_projeto = ?, local = ?, data_inicio = ?, data_termino = ? WHERE id_projeto = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNomeProjeto());
            stmt.setString(2, projeto.getLocal());
            stmt.setString(3, projeto.getDataInicio());
            stmt.setString(4, projeto.getDataTermino());
            stmt.setInt(5, projeto.getIdProjeto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idProjeto) {
        String sql = "DELETE FROM projeto WHERE id_projeto = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Projeto buscar(int idProjeto) {
        String sql = "SELECT * FROM projeto WHERE id_projeto = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Projeto(
                    rs.getInt("id_projeto"),
                    rs.getString("nome_projeto"),
                    rs.getString("local"),
                    rs.getString("data_inicio"),
                    rs.getString("data_termino")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Projeto> listar() {
        String sql = "SELECT * FROM projeto";
        List<Projeto> projetos = new ArrayList<>();
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                projetos.add(new Projeto(
                    rs.getInt("id_projeto"),
                    rs.getString("nome_projeto"),
                    rs.getString("local"),
                    rs.getString("data_inicio"),
                    rs.getString("data_termino")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projetos;
    }
    public List<Engenheiro> listarEngenheirosPorProjeto(int idProjeto) {
        String sql = "SELECT e.id_engenheiro, e.nome_engenheiro, e.especialidade FROM engenheiro e " +
                     "JOIN projeto_engenheiro pe ON e.id_engenheiro = pe.id_engenheiro " +
                     "WHERE pe.id_projeto = ?";
        List<Engenheiro> engenheiros = new ArrayList<>();
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
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
