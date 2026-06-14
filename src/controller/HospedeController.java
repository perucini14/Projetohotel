package controller;

import model.Hospede;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HospedeController {

    private ArrayList<Hospede> hospedes = new ArrayList<>();
    private static final String ARQUIVO = "dados/hospedes.txt";

    public HospedeController() {
        carregarDeArquivo();
    }

    

    public void adicionar(String nome, String cpf, String email, String telefone) {
        for (Hospede h : hospedes) {
            if (h.getCpf().equals(cpf)) {
                throw new IllegalArgumentException("CPF já cadastrado: " + cpf);
            }
        }
        String id = UUID.randomUUID().toString().substring(0, 8);
        hospedes.add(new Hospede(id, nome, cpf, email, telefone));
        salvarEmArquivo();
        System.out.println("Hóspede cadastrado com sucesso!");
    }

    public void editar(String id, String novoNome, String novoEmail, String novoTelefone) {
        Hospede h = buscarPorId(id);
        h.setNome(novoNome);
        h.setEmail(novoEmail);
        h.setTelefone(novoTelefone);
        salvarEmArquivo();
        System.out.println("Hóspede atualizado com sucesso!");
    }

    public void remover(String id) {
        Hospede h = buscarPorId(id);
        hospedes.remove(h);
        salvarEmArquivo();
        System.out.println("Hóspede removido com sucesso!");
    }

    public List<Hospede> listarTodos() {
        return hospedes;
    }

    public Hospede buscarPorCpf(String cpf) {
        for (Hospede h : hospedes) {
            if (h.getCpf().equals(cpf)) {
                return h;
            }
        }
        throw new IllegalArgumentException("Hóspede não encontrado com CPF: " + cpf);
    }

    public Hospede buscarPorId(String id) {
        for (Hospede h : hospedes) {
            if (h.getId().equals(id)) {
                return h;
            }
        }
        throw new IllegalArgumentException("Hóspede não encontrado com ID: " + id);
    }

    

    public void salvarEmArquivo() {
        try {
            new File("dados").mkdirs();
            BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO));
            for (Hospede h : hospedes) {
                writer.write(h.paraArquivo());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar hóspedes: " + e.getMessage());
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
                    hospedes.add(Hospede.deArquivo(linha));
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Erro ao carregar hóspedes: " + e.getMessage());
        }
    }
}
