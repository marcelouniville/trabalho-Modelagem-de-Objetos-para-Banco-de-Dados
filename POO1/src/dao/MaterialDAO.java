package dao;

import entity.Material;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {
    private Connection conexao;

    public MaterialDAO() {
        try {
            this.conexao = ConexaoDB.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Material material) {
        String sql = "INSERT INTO material (id_material, nome_material, quantidade) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, material.getIdMaterial());
            stmt.setString(2, material.getNomeMaterial());
            stmt.setInt(3, material.getQuantidade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Material material) {
        String sql = "UPDATE material SET nome_material = ?, quantidade = ? WHERE id_material = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, material.getNomeMaterial());
            stmt.setInt(2, material.getQuantidade());
            stmt.setInt(3, material.getIdMaterial());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idMaterial) {
        String sql = "DELETE FROM material WHERE id_material = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idMaterial);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Material buscar(int idMaterial) {
        String sql = "SELECT * FROM material WHERE id_material = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idMaterial);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Material(
                    rs.getInt("id_material"),
                    rs.getString("nome_material"),
                    rs.getInt("quantidade")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Material> listar() {
        String sql = "SELECT * FROM material";
        List<Material> materiais = new ArrayList<>();
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                materiais.add(new Material(
                    rs.getInt("id_material"),
                    rs.getString("nome_material"),
                    rs.getInt("quantidade")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materiais;
    }
}
