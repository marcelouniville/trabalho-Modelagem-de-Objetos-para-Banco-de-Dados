package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entity.Livro; 

public class LivroDAO {

    public void inserir(Livro livro) throws SQLException {
        String sql = "INSERT INTO Livro (ID_Livro, Titulo, Ano_Publicacao, ID_Autor) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, livro.getIdLivro());
            stmt.setString(2, livro.getTitulo());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.setInt(4, livro.getIdAutor());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Livro livro) throws SQLException {
        String sql = "UPDATE Livro SET Titulo = ?, Ano_Publicacao = ?, ID_Autor = ? WHERE ID_Livro = ?";
        try (Connection conn = ConexaoDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setInt(2, livro.getAnoPublicacao());
            stmt.setInt(3, livro.getIdAutor());
            stmt.setInt(4, livro.getIdLivro());
            stmt.executeUpdate();
        }
    }

    public void excluir(int idLivro) throws SQLException {
        String sql = "DELETE FROM Livro WHERE ID_Livro = ?";
        try (Connection conn = ConexaoDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLivro);
            stmt.executeUpdate();
        }
    }

    public List<Livro> listarTodos() throws SQLException {
        String sql = "SELECT * FROM Livro";
        List<Livro> livros = new ArrayList<>();

        try (Connection conn = ConexaoDB.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Livro livro = new Livro(
                    rs.getInt("ID_Livro"),
                    rs.getString("Titulo"),
                    rs.getInt("Ano_Publicacao"),
                    rs.getInt("ID_Autor")
                );
                livros.add(livro);
            }
        }

        return livros;
    }

    public List<Livro> listarPorAutor(int idAutor) throws SQLException {
        String sql = "SELECT * FROM Livro WHERE ID_Autor = ?";
        List<Livro> livros = new ArrayList<>();

        try (Connection conn = ConexaoDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAutor);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Livro livro = new Livro(
                        rs.getInt("ID_Livro"),
                        rs.getString("Titulo"),
                        rs.getInt("Ano_Publicacao"),
                        rs.getInt("ID_Autor")
                    );
                    livros.add(livro);
                }
            }
        }

        return livros;
    }
}
