package controller;

import model.Pagamento;
import util.ArquivoUtil;

import java.util.ArrayList;

public class PagamentoController {

    private ArrayList<Pagamento> pagamentos;

    public PagamentoController() {

        pagamentos = new ArrayList<>();
    }

    public void registrarPagamento(Pagamento pagamento) throws Exception {

        for (Pagamento p : pagamentos) {

            if (p.getIdReserva().equals(pagamento.getIdReserva())) {

                throw new Exception("Pagamento duplicado para esta reserva.");
            }
        }

        pagamentos.add(pagamento);

        salvarEmArquivo();
    }

    public void listarPagamentos() {

        if (pagamentos.isEmpty()) {

            System.out.println("Nenhum pagamento encontrado.");
            return;
        }

        for (Pagamento pagamento : pagamentos) {

            pagamento.exibirDados();
            System.out.println("----------------------");
        }
    }

    public void gerarRecibo(String idPagamento) throws Exception {

        for (Pagamento pagamento : pagamentos) {

            if (pagamento.getId().equals(idPagamento)) {

                System.out.println("===== RECIBO =====");
                pagamento.exibirDados();
                System.out.println("==================");

                return;
            }
        }

        throw new Exception("Pagamento não encontrado.");
    }

    public void salvarEmArquivo() {

        StringBuilder json = new StringBuilder();

        json.append("[");

        for (int i = 0; i < pagamentos.size(); i++) {

            json.append(pagamentos.get(i).toJson());

            if (i < pagamentos.size() - 1) {

                json.append(",");
            }
        }

        json.append("]");

        ArquivoUtil.salvar("dados/pagamentos.json", json.toString());
    }

    public void carregarDeArquivo() {

        String conteudo = ArquivoUtil.ler("dados/pagamentos.json");

        if (conteudo == null || conteudo.isEmpty()) {

            return;
        }

        System.out.println("Pagamentos carregados.");
    }
}