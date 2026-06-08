package view;

import controller.PagamentoController;
import model.Pagamento;

import java.util.Scanner;

public class PagamentoView {

    private PagamentoController controller;
    private Scanner scanner;

    public PagamentoView(PagamentoController controller) {

        this.controller = controller;
        scanner = new Scanner(System.in);
    }

    public void menu() {

        int opcao;

        do {

            System.out.println("\n===== PAGAMENTOS =====");
            System.out.println("1 - Registrar pagamento");
            System.out.println("2 - Gerar recibo");
            System.out.println("3 - Listar pagamentos");
            System.out.println("0 - Voltar");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    registrarPagamento();
                    break;

                case 2:
                    gerarRecibo();
                    break;

                case 3:
                    controller.listarPagamentos();
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void registrarPagamento() {

        try {

            System.out.print("ID do pagamento: ");
            String id = scanner.nextLine();

            System.out.print("ID da reserva: ");
            String idReserva = scanner.nextLine();

            System.out.print("Valor total: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Forma de pagamento:");
            System.out.println("1 - DINHEIRO");
            System.out.println("2 - CARTAO");
            System.out.println("3 - PIX");

            int forma = scanner.nextInt();
            scanner.nextLine();

            Pagamento.FormaPagamento formaPagamento;

            switch (forma) {

                case 1:
                    formaPagamento = Pagamento.FormaPagamento.DINHEIRO;
                    break;

                case 2:
                    formaPagamento = Pagamento.FormaPagamento.CARTAO;
                    break;

                case 3:
                    formaPagamento = Pagamento.FormaPagamento.PIX;
                    break;

                default:
                    System.out.println("Forma inválida.");
                    return;
            }

            Pagamento pagamento = new Pagamento(
                    id,
                    idReserva,
                    valor,
                    formaPagamento,
                    Pagamento.StatusPagamento.PAGO
            );

            controller.registrarPagamento(pagamento);

            System.out.println("Pagamento registrado.");

        } catch (Exception e) {

            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void gerarRecibo() {

        try {

            System.out.print("Digite o ID do pagamento: ");
            String id = scanner.nextLine();

            controller.gerarRecibo(id);

        } catch (Exception e) {

            System.out.println("Erro: " + e.getMessage());
        }
    }
}