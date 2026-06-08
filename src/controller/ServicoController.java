package controller;

import model.Servico;
import util.ArquivoUtil;
import java.util.ArrayList;

public class ServicoController {
    private ArrayList<Servico> listaServicos;
    private final String CAMINHO_ARQUIVO = "dados/servicos.json";

    public ServicoController() {
        this.listaServicos = new ArrayList<>();
    }

    public void adicionarServico(Servico servico) {
        listaServicos.add(servico);
        salvarEmArquivo();
    }

    public ArrayList<Servico> listarPorReserva(String idReserva) {
        ArrayList<Servico> servicosDaReserva = new ArrayList<>();
        for (Servico s : listaServicos) {
            if (s.getIdReserva().equalsIgnoreCase(idReserva)) {
                servicosDaReserva.add(s);
            }
        }
        return servicosDaReserva;
    }

    public void salvarEmArquivo() {
        StringBuilder jsonBuilder = new StringBuilder("[\n");
        for (int i = 0; i < listaServicos.size(); i++) {
            jsonBuilder.append("  ").append(listaServicos.get(i).toJson());
            if (i < listaServicos.size() - 1) jsonBuilder.append(",\n");
        }
        jsonBuilder.append("\n]");
        ArquivoUtil.salvar(CAMINHO_ARQUIVO, jsonBuilder.toString());
    }

    public void carregarDeArquivo() {
        try {
            String conteudo = ArquivoUtil.ler(CAMINHO_ARQUIVO);
            System.out.println("Dados de serviços carregados com sucesso!");
        } catch (Exception e) {
            System.out.println("Arquivo de serviços não encontrado. Criando novo banco de dados...");
        }
    }
}