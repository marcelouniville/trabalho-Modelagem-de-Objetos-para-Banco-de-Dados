package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import entity.Autor; 

public class AutorDAO {

    public void inserir(Autor autor) throws SQLException {
        String sql = "INSERT INTO Autor (ID_Autor, Nome, Nacionalidade) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, autor.getIdAutor());
            stmt.setString(2, autor.getNome());
            stmt.setString(3, autor.getNacionalidade());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Autor autor) throws SQLException {
        String sql = "UPDATE Autor SET Nome = ?, Nacionalidade = ? WHERE ID_Autor = ?";
        try (Connection conn = ConexaoDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
            stmt.setInt(3, autor.getIdAutor());
            stmt.executeUpdate();
        }
    }

    public void excluir(int idAutor) throws SQLException {
        String sql = "DELETE FROM Autor WHERE ID_Autor = ?";
        try (Connection conn = ConexaoDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAutor);
            stmt.executeUpdate();
        }
    }

    public List<Autor> listarTodos() throws SQLException {
        String sql = "SELECT * FROM Autor";
        List<Autor> autores = new ArrayList<>();

        try (Connection conn = ConexaoDB.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Autor autor = new Autor(
                    rs.getInt("ID_Autor"),
                    rs.getString("Nome"),
                    rs.getString("Nacionalidade")
                );
                autores.add(autor);
            }
        }

        return autores;
    }

    public Optional<Autor> obterPeloId(int idAutor) throws SQLException {
        String sql = "SELECT * FROM Autor WHERE ID_Autor = ?";
        try (Connection conn = ConexaoDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAutor);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Autor autor = new Autor(
                        rs.getInt("ID_Autor"),
                        rs.getString("Nome"),
                        rs.getString("Nacionalidade")
                    );
                    return Optional.of(autor);
                }
            }
        }
        return Optional.empty();
    }
}
