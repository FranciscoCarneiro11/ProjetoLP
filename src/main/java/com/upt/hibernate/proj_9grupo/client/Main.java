package com.upt.hibernate.proj_9grupo.client;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AlunoClient alunoClient = new AlunoClient();
        ProfessorClient professorClient = new ProfessorClient(); 
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("Menu Principal:");
            System.out.println("1. Menu Aluno");
            System.out.println("2. Menu Professor");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    menuAlunos(scanner, alunoClient);
                    break;
                case 2:
                    menuProfessores(scanner, professorClient);
                    break;
                case 3:
                    System.out.println("A sair...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (option != 3);

        scanner.close();
    }

    private static void menuAlunos(Scanner scanner, AlunoClient alunoClient) {
        int option;
        do {
            System.out.println("\nMenu de Alunos:");
            System.out.println("1. Obter um aluno através do ID");
            System.out.println("2. Obter Todos os Alunos");
            System.out.println("3. Criar Novo Aluno");
            System.out.println("4. Atualizar Aluno");
            System.out.println("5. Eliminar Aluno");
            System.out.println("6. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Escreva o ID do aluno: ");
                    Long id = scanner.nextLong();
                    alunoClient.getAlunoById(id);
                    break;
                case 2:
                    alunoClient.getAllAlunos();
                    break;
                case 3:
                    alunoClient.criarAluno();
                    break;
                case 4:
                    System.out.print("Escreva o ID do aluno a ser atualizado: ");
                    Long updateId = scanner.nextLong();
                    alunoClient.updateAluno(updateId);
                    break;
                case 5:
                    System.out.print("Escreva o ID do aluno a ser eliminado: ");
                    Long deleteId = scanner.nextLong();
                    alunoClient.eliminarAluno(deleteId);
                    break;
                case 6:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (option != 6);
    }

    private static void menuProfessores(Scanner scanner, ProfessorClient professorClient) {
        int option;
        do {
            System.out.println("\nMenu de Professores:");
            System.out.println("1. Obter um professor através do ID");
            System.out.println("2. Obter Todos os Professores");
            System.out.println("3. Criar Novo Professor");
            System.out.println("4. Atualizar Professor");
            System.out.println("5. Eliminar Professor");
            System.out.println("6. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Escreva o ID do professor: ");
                    Long id = scanner.nextLong();
                    professorClient.getProfessorById(id);
                    break;
                case 2:
                    professorClient.getAllProfessores();
                    break;
                case 3:
                    professorClient.criarProfessor();
                    break;
                case 4:
                    System.out.print("Escreva o ID do professor a ser atualizado: ");
                    Long updateId = scanner.nextLong();
                    professorClient.updateProfessor(updateId);
                    break;
                case 5:
                    System.out.print("Escreva o ID do professor a ser eliminado: ");
                    Long deleteId = scanner.nextLong();
                    professorClient.eliminarProfessor(deleteId);
                    break;
                case 6:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (option != 6);
    }
}
