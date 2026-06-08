package view;

import controller.PagamentoController;

import java.util.Scanner;

public class MenuPrincipal {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        PagamentoController pagamentoController = new PagamentoController();

        pagamentoController.carregarDeArquivo();

        PagamentoView pagamentoView = new PagamentoView(pagamentoController);

        int opcao;

        do {

            System.out.println("\n===== SISTEMA HOTEL =====");
            System.out.println("1 - Pagamentos");
            System.out.println("0 - Sair");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    pagamentoView.menu();
                    break;

                case 0:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}