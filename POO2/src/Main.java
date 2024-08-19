import dao.AutorDAO;
import entity.Autor;
import java.sql.SQLException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        AutorDAO dao = new AutorDAO();

        try {
            Optional<Autor> opt = dao.obterPeloId(30);

            // Verifica se o autor foi encontrado
            if (opt.isPresent()) {
                Autor autor = opt.get();
                System.out.println("Autor encontrado:");
                System.out.println("ID: " + autor.getIdAutor());
                System.out.println("Nome: " + autor.getNome());
                System.out.println("Nacionalidade: " + autor.getNacionalidade());
            } else {
                System.out.println("Autor com ID 30 não encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }
}
