package model;

import interfaces.Cadastravel;

public class Funcionario implements Cadastravel {

    public enum Cargo {
        RECEPCIONISTA, GERENTE, ADMIN
    }

    private String id;
    private String nome;
    private String matricula;
    private Cargo cargo;
    private String senha;

    public Funcionario(String id, String nome, String matricula, Cargo cargo, String senha) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.cargo = cargo;
        this.senha = senha;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void exibirDados() {
        System.out.println("--- Funcionário ---");
        System.out.println("ID:        " + id);
        System.out.println("Nome:      " + nome);
        System.out.println("Matrícula: " + matricula);
        System.out.println("Cargo:     " + cargo);
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public Cargo getCargo() { return cargo; }
    public void setCargo(Cargo cargo) { this.cargo = cargo; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    // Converte para linha de texto para salvar no arquivo
    public String paraArquivo() {
        return id + ";" + nome + ";" + matricula + ";" + cargo.name() + ";" + senha;
    }

    // Cria um Funcionario a partir de uma linha do arquivo
    public static Funcionario deArquivo(String linha) {
        String[] partes = linha.split(";");
        return new Funcionario(partes[0], partes[1], partes[2], Cargo.valueOf(partes[3]), partes[4]);
    }
}