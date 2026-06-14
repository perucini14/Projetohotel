package controller;

import model.Funcionario;
import model.Funcionario.Cargo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FuncionarioController {

    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private static final String ARQUIVO = "dados/funcionarios.txt";

    public FuncionarioController() {
        carregarDeArquivo();
    }

    

    public void adicionar(String nome, String matricula, Cargo cargo, String senha) {
        for (Funcionario f : funcionarios) {
            if (f.getMatricula().equals(matricula)) {
                throw new IllegalArgumentException("Matrícula já cadastrada: " + matricula);
            }
        }
        String id = UUID.randomUUID().toString().substring(0, 8);
        funcionarios.add(new Funcionario(id, nome, matricula, cargo, senha));
        salvarEmArquivo();
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    public void editar(String id, String novoNome, Cargo novoCargo, String novaSenha) {
        Funcionario f = buscarPorId(id);
        f.setNome(novoNome);
        f.setCargo(novoCargo);
        f.setSenha(novaSenha);
        salvarEmArquivo();
        System.out.println("Funcionário atualizado com sucesso!");
    }

    public void remover(String id) {
        Funcionario f = buscarPorId(id);
        funcionarios.remove(f);
        salvarEmArquivo();
        System.out.println("Funcionário removido com sucesso!");
    }

    public List<Funcionario> listarTodos() {
        return funcionarios;
    }

    public Funcionario autenticar(String matricula, String senha) {
        for (Funcionario f : funcionarios) {
            if (f.getMatricula().equals(matricula) && f.getSenha().equals(senha)) {
                return f;
            }
        }
        throw new IllegalArgumentException("Matrícula ou senha incorretos.");
    }

    public Funcionario buscarPorId(String id) {
        for (Funcionario f : funcionarios) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        throw new IllegalArgumentException("Funcionário não encontrado com ID: " + id);
    }

    

    public void salvarEmArquivo() {
        try {
            new File("dados").mkdirs();
            BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO));
            for (Funcionario f : funcionarios) {
                writer.write(f.paraArquivo());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar funcionários: " + e.getMessage());
        }
    }

    public void carregarDeArquivo() {
        try {
            File arquivo = new File(ARQUIVO);
            if (!arquivo.exists()) return;
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (!linha.isBlank()) {
                    funcionarios.add(Funcionario.deArquivo(linha));
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Erro ao carregar funcionários: " + e.getMessage());
        }
    }
}
