package view;

import exceptions.NumeroDuplicadoException;
import controller.QuartoController;
import exceptions.QuartoNaoEncontradoException;
import model.QuartoStandard;
import model.QuartoLuxo;
import model.Suite;

import java.util.Scanner;

public class QuartoView {
    private QuartoController controller;
    private Scanner scanner;

    public QuartoView(QuartoController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao = 0;
        do {
            System.out.println("\n--- Gerenciamento de Quartos ---");
            System.out.println("1. Cadastrar Quarto");
            System.out.println("2. Editar Quarto (Disponibilidade)");
            System.out.println("3. Remover Quarto");
            System.out.println("4. Listar Todos os Quartos");
            System.out.println("5. Listar Quartos Disponíveis");
            System.out.println("6. Salvar Dados");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                processarOpcao(opcao);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        } while (opcao != 0);
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarQuarto();
                break;
            case 2:
                editarQuarto();
                break;
            case 3:
                removerQuarto();
                break;
            case 4:
                controller.listarQuartos();
                break;
            case 5:
                controller.listarDisponiveis();
                break;
            case 6:
                controller.salvarEmArquivo();
                break;
            case 0:
                System.out.println("Voltando ao menu principal...");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void cadastrarQuarto() {
        try {
            System.out.print("Número do quarto: ");
            int numero = Integer.parseInt(scanner.nextLine());

            System.out.print("Andar: ");
            int andar = Integer.parseInt(scanner.nextLine());

            System.out.println("Tipo de Quarto:");
            System.out.println("1. Standard");
            System.out.println("2. Luxo");
            System.out.println("3. Suite");
            System.out.print("Escolha o tipo: ");
            int tipo = Integer.parseInt(scanner.nextLine());

            switch (tipo) {
                case 1:
                    controller.adicionarQuarto(new QuartoStandard(numero, andar));
                    break;
                case 2:
                    controller.adicionarQuarto(new QuartoLuxo(numero, andar));
                    break;
                case 3:
                    controller.adicionarQuarto(new Suite(numero, andar));
                    break;
                default:
                    System.out.println("Tipo inválido. Cadastro cancelado.");
                    return;
            }
            System.out.println("Quarto cadastrado com sucesso!");
        } catch (NumeroDuplicadoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro: Entrada inválida. Use apenas números.");
        }
    }

    private void editarQuarto() {
        try {
            System.out.print("Informe o número do quarto para editar: ");
            int numero = Integer.parseInt(scanner.nextLine());

            System.out.print("O quarto está disponível? (S/N): ");
            String disp = scanner.nextLine().toUpperCase();
            boolean disponivel = disp.equals("S");

            controller.editarQuarto(numero, disponivel);
            System.out.println("Quarto atualizado com sucesso!");
        } catch (QuartoNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro: Entrada inválida.");
        }
    }

    private void removerQuarto() {
        try {
            System.out.print("Informe o número do quarto para remover: ");
            int numero = Integer.parseInt(scanner.nextLine());

            controller.removerQuarto(numero);
            System.out.println("Quarto removido com sucesso!");
        } catch (QuartoNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro: Entrada inválida.");
        }
    }
}