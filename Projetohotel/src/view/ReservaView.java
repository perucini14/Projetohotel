package view;

import controller.ReservaController;
import model.Hospede;
import model.Reserva;

import java.util.Scanner;

public class ReservaView {
    private ReservaController reservaController;
    private Scanner scanner;

    public ReservaView(ReservaController reservaController) {
        this.reservaController = reservaController;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n===== MENU RESERVAS =====");
            System.out.println("1. Abrir Nova Reserva");
            System.out.println("2. Realizar Check-in");
            System.out.println("3. Realizar Check-out");
            System.out.println("4. Cancelar Reserva");
            System.out.println("5. Listar Todas as Reservas");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: menuAbrirReserva(); break;
                    case 2: menuCheckin(); break;
                    case 3: menuCheckout(); break;
                    case 4: menuCancelar(); break;
                    case 5: menuListar(); break;
                    case 0: System.out.println("Voltando..."); break;
                    default: System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
    }

    private void menuAbrirReserva() {
        System.out.println("\n--- Nova Reserva ---");
        System.out.print("ID da Reserva: ");
        String id = scanner.nextLine();

        System.out.print("Nome do Hóspede (Simulado): ");
        String nomeHospede = scanner.nextLine();
        Hospede hospedeSimulado = new Hospede(id + "_h", nomeHospede, "000.000.000-00", "", "");

        System.out.print("Número do Quarto: ");
        int numeroQuarto = Integer.parseInt(scanner.nextLine());
        System.out.print("Data de Entrada (DD/MM/AAAA): ");
        String entrada = scanner.nextLine();
        System.out.print("Data de Saída (DD/MM/AAAA): ");
        String saida = scanner.nextLine();

        Reserva nova = new Reserva(id, hospedeSimulado, numeroQuarto, entrada, saida);

        try {
            reservaController.abrirReserva(nova);
            System.out.println("Reserva aberta com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void menuCheckin() {
        System.out.print("\nDigite o ID da reserva para CHECK-IN: ");
        String id = scanner.nextLine();
        try {
            reservaController.fazerCheckin(id);
            System.out.println("Check-in realizado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void menuCheckout() {
        System.out.print("\nDigite o ID da reserva para CHECK-OUT: ");
        String id = scanner.nextLine();
        try {
            reservaController.fazerCheckout(id);
            System.out.println("Check-out realizado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void menuCancelar() {
        System.out.print("\nDigite o ID da reserva para CANCELAR: ");
        String id = scanner.nextLine();
        try {
            reservaController.cancelarReserva(id);
            System.out.println("Reserva cancelada com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void menuListar() {
        System.out.println("\n--- Lista de Reservas ---");
        if (reservaController.getListaReservas().isEmpty()) {
            System.out.println("Nenhuma reserva cadastrada.");
        } else {
            for (Reserva r : reservaController.getListaReservas()) {
                r.exibirDados();
            }
        }
    }
}