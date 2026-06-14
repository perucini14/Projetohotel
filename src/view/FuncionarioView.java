package view;

import controller.FuncionarioController;
import model.Funcionario;
import model.Funcionario.Cargo;

import java.util.List;
import java.util.Scanner;

public class FuncionarioView {

    private FuncionarioController controller;
    private Scanner scanner;

    public FuncionarioView(FuncionarioController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n===== MENU FUNCIONÁRIOS =====");
            System.out.println("1. Cadastrar funcionário");
            System.out.println("2. Editar funcionário");
            System.out.println("3. Remover funcionário");
            System.out.println("4. Listar todos");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> editar();
                case 3 -> remover();
                case 4 -> listarTodos();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrar() {
        System.out.println("\n-- Cadastrar Funcionário --");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Cargo cargo = selecionarCargo();
        if (cargo == null) return;

        try {
            controller.adicionar(nome, matricula, cargo, senha);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void editar() {
        System.out.println("\n-- Editar Funcionário --");
        System.out.print("ID do funcionário: ");
        String id = scanner.nextLine();
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Nova senha: ");
        String senha = scanner.nextLine();

        Cargo cargo = selecionarCargo();
        if (cargo == null) return;

        try {
            controller.editar(id, nome, cargo, senha);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void remover() {
        System.out.println("\n-- Remover Funcionário --");
        System.out.print("ID do funcionário: ");
        String id = scanner.nextLine();

        try {
            controller.remover(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarTodos() {
        List<Funcionario> lista = controller.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }
        System.out.println("\n-- Lista de Funcionários --");
        for (Funcionario f : lista) {
            f.exibirDados();
            System.out.println();
        }
    }

    private Cargo selecionarCargo() {
        System.out.println("Cargo: 1-RECEPCIONISTA  2-GERENTE  3-ADMIN");
        System.out.print("Opção: ");
        try {
            int op = Integer.parseInt(scanner.nextLine().trim());
            return switch (op) {
                case 1 -> Cargo.RECEPCIONISTA;
                case 2 -> Cargo.GERENTE;
                case 3 -> Cargo.ADMIN;
                default -> {
                    System.out.println("Cargo inválido.");
                    yield null;
                }
            };
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            return null;
        }
    }
}