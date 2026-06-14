import controller.*;
import view.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        FuncionarioController funcionarioController = new FuncionarioController();
        HospedeController hospedeController = new HospedeController();
        QuartoController quartoController = new QuartoController();
        ReservaController reservaController = new ReservaController();
        ServicoController servicoController = new ServicoController();
        PagamentoController pagamentoController = new PagamentoController();


        pagamentoController.carregarDeArquivo();
        quartoController.carregarDeArquivo();
        reservaController.carregarDeArquivo();
        servicoController.carregarDeArquivo();

        FuncionarioView funcionarioView = new FuncionarioView(funcionarioController, scanner);
        HospedeView hospedeView = new HospedeView(hospedeController, scanner);
        QuartoView quartoView = new QuartoView(quartoController);
        ReservaView reservaView = new ReservaView(reservaController);
        ServicoView servicoView = new ServicoView(servicoController);
        PagamentoView pagamentoView = new PagamentoView(pagamentoController);

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n===== SISTEMA DE GERENCIAMENTO DE HOTEL =====");
            System.out.println("1. Módulo de Hóspedes");
            System.out.println("2. Módulo de Funcionários");
            System.out.println("3. Módulo de Quartos");
            System.out.println("4. Módulo de Reservas");
            System.out.println("5. Módulo de Serviços Adicionais");
            System.out.println("6. Módulo de Pagamentos");
            System.out.println("0. Sair do Sistema");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1 -> hospedeView.exibirMenu();
                case 2 -> funcionarioView.exibirMenu();
                case 3 -> quartoView.exibirMenu();
                case 4 -> reservaView.exibirMenu();
                case 5 -> servicoView.exibirMenu();
                case 6 -> pagamentoView.menu(); // PagamentoView usa o método menu()
                case 0 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }
}