package view;

import controller.HospedeController;
import model.Hospede;

import java.util.List;
import java.util.Scanner;

public class HospedeView {

    private HospedeController controller;
    private Scanner scanner;

    public HospedeView(HospedeController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n===== MENU HÓSPEDES =====");
            System.out.println("1. Cadastrar hóspede");
            System.out.println("2. Editar hóspede");
            System.out.println("3. Remover hóspede");
            System.out.println("4. Buscar por CPF");
            System.out.println("5. Listar todos");
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
                case 4 -> buscarPorCpf();
                case 5 -> listarTodos();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrar() {
        System.out.println("\n-- Cadastrar Hóspede --");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        try {
            controller.adicionar(nome, cpf, email, telefone);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void editar() {
        System.out.println("\n-- Editar Hóspede --");
        System.out.print("ID do hóspede: ");
        String id = scanner.nextLine();
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo e-mail: ");
        String email = scanner.nextLine();
        System.out.print("Novo telefone: ");
        String telefone = scanner.nextLine();

        try {
            controller.editar(id, nome, email, telefone);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void remover() {
        System.out.println("\n-- Remover Hóspede --");
        System.out.print("ID do hóspede: ");
        String id = scanner.nextLine();

        try {
            controller.remover(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void buscarPorCpf() {
        System.out.println("\n-- Buscar por CPF --");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        try {
            Hospede h = controller.buscarPorCpf(cpf);
            h.exibirDados();
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarTodos() {
        List<Hospede> lista = controller.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum hóspede cadastrado.");
            return;
        }
        System.out.println("\n-- Lista de Hóspedes --");
        for (Hospede h : lista) {
            h.exibirDados();
            System.out.println();
        }
    }
}