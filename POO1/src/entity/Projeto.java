package entity;

import java.util.List;

public class Projeto {
    private int idProjeto;
    private String nomeProjeto;
    private String local;
    private String dataInicio;
    private String dataTermino;

    private List<Engenheiro> engenheiros;
    private List<Operario> operarios;
    private List<Equipamento> equipamentos;
    private List<Material> materiais;

    public Projeto(int idProjeto, String nomeProjeto, String local, String dataInicio, String dataTermino) {
        this.idProjeto = idProjeto;
        this.nomeProjeto = nomeProjeto;
        this.local = local;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }

    public List<Engenheiro> getEngenheiros() {
        return engenheiros;
    }

    public void setEngenheiros(List<Engenheiro> engenheiros) {
        this.engenheiros = engenheiros;
    }

    public List<Operario> getOperarios() {
        return operarios;
    }

    public void setOperarios(List<Operario> operarios) {
        this.operarios = operarios;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public List<Material> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<Material> materiais) {
        this.materiais = materiais;
    }
}
