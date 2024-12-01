import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Produto {
    private String nome;
    private int quantidade;

    public Produto(String nome, int quantidade) {
        this.nome = nome.replace(" ", "_");
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public boolean emEstadoCritico(int limiteCritico) {
        return quantidade < limiteCritico;
    }

    @Override
    public String toString() {
        return nome + ", Quantidade: " + quantidade + (emEstadoCritico(5) ? " (CRÍTICO)" : "");
    }
}

public class Main {
    private static final int LIMITE_CRITICO = 5;
    private static ArrayList<Produto> estoque = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void adicionarProduto() {
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a quantidade do produto: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.next();
        }
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        estoque.add(new Produto(nome, quantidade));
        System.out.println("Produto '" + nome.replace(" ", "_") + "' adicionado com sucesso!");
    }

    public static void verificarEstadoCritico() {
        System.out.println("\nProdutos em estado crítico (quantidade abaixo de " + LIMITE_CRITICO + "):");
        boolean encontrou = false;

        for (Produto produto : estoque) {
            if (produto.emEstadoCritico(LIMITE_CRITICO)) {
                System.out.println(produto);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum produto em estado crítico.");
        }
    }

    public static void gerarRelatorio() {
        System.out.println("\nRelatório do Estoque:");
        System.out.println("---------------------");

        for (Produto produto : estoque) {
            System.out.println(produto);
        }
    }

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Exibir Produtos em Estado Crítico");
            System.out.println("3. Gerar Relatório do Estoque");
            System.out.println("4. Sair");
            System.out.print("Escolha: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        adicionarProduto();
                        break;
                    case 2:
                        verificarEstadoCritico();
                        break;
                    case 3:
                        gerarRelatorio();
                        break;
                    case 4:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida! Por favor, escolha uma opção entre 1 e 4.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next();
                opcao = -1;
            }
        } while (opcao != 4);
    }
}
