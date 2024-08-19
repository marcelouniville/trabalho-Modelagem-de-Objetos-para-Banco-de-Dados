import entity.Engenheiro;
import entity.Equipamento;
import entity.Material;
import entity.Operario;
import entity.Projeto;

import java.util.List;

import dao.EngenheiroDAO;
import dao.OperarioDAO;
import dao.ProjetoDAO;
import dao.MaterialDAO;
import dao.EquipamentoDAO;

public class Main {
    public static void main(String[] args) {

        Projeto projeto = new Projeto(1, "Construção de Ponte", "Rio de Janeiro", "2024-01-01", "2024-12-31");
        Engenheiro engenheiro = new Engenheiro(1, "Carlos Silva", "Civil");
        Operario operario = new Operario(1, "José Santos", "Pedreiro");
        Equipamento equipamento = new Equipamento(1, "Betoneira", "Pesado");
        Material material = new Material(1, "Cimento", 500);

        ProjetoDAO projetoDAO = new ProjetoDAO();
        EngenheiroDAO engenheiroDAO = new EngenheiroDAO();
        OperarioDAO operarioDAO = new OperarioDAO();
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
        MaterialDAO materialDAO = new MaterialDAO();

        Projeto projetoBuscado = projetoDAO.buscar(1);
        if (projetoBuscado != null) {
            System.out.println("Projeto encontrado: " + projetoBuscado.getNomeProjeto());
        } else {
            System.out.println("Projeto não encontrado.");
        }

        List<Projeto> projetos = projetoDAO.listar();
        for (Projeto p : projetos) {
            System.out.println("Projeto: " + p.getNomeProjeto());
        }

        List<Engenheiro> engenheirosNoProjeto = projetoDAO.listarEngenheirosPorProjeto(1);
        for (Engenheiro e : engenheirosNoProjeto) {
            System.out.println("Engenheiro no Projeto: " + e.getNomeEngenheiro());
        }

        List<Operario> operarios = operarioDAO.listar();
        for (Operario o : operarios) {
            System.out.println("Operário: " + o.getNomeOperario());
        }

        projeto.setLocal("Rio de Janeiro");
        projetoDAO.atualizar(projeto);

        materialDAO.excluir(2);

        List<Material> materiais = materialDAO.listar();
        for (Material m : materiais) {
            System.out.println("Material: " + m.getNomeMaterial() + ", Quantidade: " + m.getQuantidade());
        }
    }
}
