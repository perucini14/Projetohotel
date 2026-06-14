package model;

import interfaces.Cadastravel;

public class Hospede implements Cadastravel {

    private String id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    public Hospede(String id, String nome, String cpf, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void exibirDados() {
        System.out.println("--- Hóspede ---");
        System.out.println("ID:       " + id);
        System.out.println("Nome:     " + nome);
        System.out.println("CPF:      " + cpf);
        System.out.println("E-mail:   " + email);
        System.out.println("Telefone: " + telefone);
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    // Converte para linha de texto para salvar no arquivo
    public String paraArquivo() {
        return id + ";" + nome + ";" + cpf + ";" + email + ";" + telefone;
    }

    // Cria um Hospede a partir de uma linha do arquivo
    public static Hospede deArquivo(String linha) {
        String[] partes = linha.split(";");
        return new Hospede(partes[0], partes[1], partes[2], partes[3], partes[4]);
    }
}