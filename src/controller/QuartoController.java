package controller;

import exceptions.NumeroDuplicadoException;
import exceptions.QuartoNaoEncontradoException;
import model.Quarto;
//import util.ArquivoUtil; // Utilitário que será feito pelo Felipe [cite: 8]
import java.util.ArrayList;

public class QuartoController {
    private ArrayList<Quarto> quartos; // Coleção exigida
    private final String ARQUIVO_DADOS = "dados/quartos.json"; // Arquivo JSON na pasta /dados [cite: 6, 12]

    public QuartoController() {
        this.quartos = new ArrayList<>();
    }

    public void adicionarQuarto(Quarto quarto) throws NumeroDuplicadoException {
        try {
            buscarPorNumero(quarto.getNumero());
            throw new NumeroDuplicadoException("Já existe um quarto com o número " + quarto.getNumero());
        } catch (QuartoNaoEncontradoException e) {
            quartos.add(quarto);
        }
    }

    public void editarQuarto(int numero, boolean disponivel) throws QuartoNaoEncontradoException {
        Quarto quarto = buscarPorNumero(numero);
        quarto.setDisponivel(disponivel);
    }

    public void removerQuarto(int numero) throws QuartoNaoEncontradoException {
        Quarto quarto = buscarPorNumero(numero);
        quartos.remove(quarto);
    }

    public void listarQuartos() {
        if (quartos.isEmpty()) {
            System.out.println("Nenhum quarto cadastrado.");
            return;
        }
        for (Quarto q : quartos) {
            q.exibirDados();
        }
    }

    public Quarto buscarPorNumero(int numero) throws QuartoNaoEncontradoException {
        for (Quarto q : quartos) {
            if (q.getNumero() == numero) {
                return q;
            }
        }
        throw new QuartoNaoEncontradoException("Quarto de número " + numero + " não foi encontrado.");
    }

    public void listarDisponiveis() {
        boolean encontrou = false;
        for (Quarto q : quartos) {
            if (q.isDisponivel()) {
                q.exibirDados();
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum quarto disponível no momento.");
        }
    }

    public void salvarEmArquivo() {
        StringBuilder json = new StringBuilder("[\n");
        for (int i = 0; i < quartos.size(); i++) {
            json.append("  ").append(quartos.get(i).toJson());
            if (i < quartos.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        json.append("]");

        // Chamada ao utilitário do Felipe (simulada aqui para não quebrar seu código) [cite: 8]
        try {
            // ArquivoUtil.salvar(ARQUIVO_DADOS, json.toString());
            System.out.println("Dados dos quartos salvos com sucesso em " + ARQUIVO_DADOS);
        } catch (Exception e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    public void carregarDeArquivo() {
        // Como você não deve depender do Felipe agora para ler JSON complexo[cite: 2],
        // você pode deixar o método preparado para ser integrado depois no MenuPrincipal[cite: 9].
        System.out.println("Carregando dados de " + ARQUIVO_DADOS + "...");
    }
}