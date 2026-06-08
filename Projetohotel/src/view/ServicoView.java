package view;

import controller.ServicoController;
import model.Servico;
import model.TipoServico;

import java.util.ArrayList;
import java.util.Scanner;

public class ServicoView {
    private ServicoController servicoController;
    private Scanner scanner;

    public ServicoView(ServicoController servicoController) {
        this.servicoController = servicoController;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n===== MENU SERVIÇOS ADICIONAIS =====");
            System.out.println("1. Lançar Serviço para Reserva");
            System.out.println("2. Listar Serviços por Reserva");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: menuLancarServico(); break;
                    case 2: menuListarPorReserva(); break;
                    case 0: System.out.println("Voltando..."); break;
                    default: System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
    }

    private void menuLancarServico() {
        System.out.println("\n--- Lançar Serviço ---");
        System.out.print("ID do Serviço: ");
        String id = scanner.nextLine();
        System.out.print("ID da Reserva destino: ");
        String idReserva = scanner.nextLine();

        System.out.println("Selecione o tipo de serviço:");
        System.out.println("1. Frigobar");
        System.out.println("2. Lavanderia");
        System.out.println("3. Room Service");
        System.out.print("Opção: ");
        int tipoOpcao = Integer.parseInt(scanner.nextLine());

        TipoServico tipo = TipoServico.ROOM_SERVICE;
        if (tipoOpcao == 1) tipo = TipoServico.FRIGOBAR;
        else if (tipoOpcao == 2) tipo = TipoServico.LAVANDERIA;

        System.out.print("Valor do Serviço: R$ ");
        double valor = Double.parseDouble(scanner.nextLine());

        Servico novoServico = new Servico(id, idReserva, tipo, valor);
        servicoController.adicionarServico(novoServico);
        System.out.println("Serviço adicionado com sucesso!");
    }

    private void menuListarPorReserva() {
        System.out.print("\nDigite o ID da Reserva para buscar os serviços: ");
        String idReserva = scanner.nextLine();

        ArrayList<Servico> servicos = servicoController.listarPorReserva(idReserva);
        System.out.println("\n--- Serviços Adicionais da Reserva " + idReserva + " ---");
        if (servicos.isEmpty()) {
            System.out.println("Nenhum serviço lançado para esta reserva.");
        } else {
            double total = 0;
            for (Servico s : servicos) {
                s.exibirDados();
                total += s.getValor();
            }
            System.out.println("Total em Serviços: R$ " + total);
        }
    }
}