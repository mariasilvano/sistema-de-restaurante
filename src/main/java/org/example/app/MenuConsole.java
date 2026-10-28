package org.example.app;

import org.example.cardapio.ItemCardapio;
import org.example.cardapio.Lanche;
import org.example.pedido.Pedido;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuConsole {

    private Scanner scanner = new Scanner(System.in);

    public void exibirMenu(){
        System.out.println("=== RESTAURANTE === \n" +
                "1 - Registrar novo pedido \n" +
                "2 - Listar pedidos do dia \n" +
                "3 - Fechar restaurante \n" +
                "0 - Sair");
    }

    public int lerOpcao(){
        return scanner.nextInt();
    }

    public void exibirCardapio(){
        System.out.println("=== CARDÁPIO === \n" +
                "1 - X-Burger — R$ 16,00 \n" +
                "2 - X-Salada — R$ 18,00 \n" +
                "3 - X-Bacon — R$ 22,00 ");


    }

    public String lerNomeCliente() {
        scanner.nextLine();
        System.out.print("Digite o nome do cliente: ");
        return scanner.nextLine();
    }


    public int lerOpcaoLanche(){

        System.out.print("Digite qual o lanche desejado: ");
        int opcao =  scanner.nextInt();

        while (opcao < 1 || opcao > 3) {
            System.out.println("Opção inválida! Escolha entre 1 e 3.");
            System.out.print("Escolha um lanche: ");
            opcao = scanner.nextInt();
        }

        scanner.nextLine();
        return  opcao;
    }

    public void exibirFechamentoDoDia(double total){
        System.out.println("=== Fechamento de Caixa === \n" );
        System.out.println("Total: "+ total);
    }

    public boolean desejaAdicionarLanche(){
        System.out.println("Deseja adicionar mais um lanche? (S/N)");
        String resposta = scanner.nextLine();

        while (!resposta.equalsIgnoreCase("S")
                && !resposta.equalsIgnoreCase("N")) {
            System.out.print("Resposta inválida! Digite S ou N: ");
            resposta = scanner.nextLine();
        }

        return resposta.equalsIgnoreCase("S");

    }

    public void exibirPedidos(HashMap<Integer, Pedido> pedidos) {

        for (Map.Entry<Integer, Pedido> entry : pedidos.entrySet()) {
            int id = entry.getKey();
            Pedido pedido = entry.getValue();

            System.out.println("Pedido: " + id);

            for (ItemCardapio itemCardapio : pedido.getItens()) {
                System.out.printf("%s - R$ %.2f%n",
                        itemCardapio.getDescricao(),
                        itemCardapio.getPreco());
            }

            System.out.println("------ Total: ------");
            System.out.printf("R$ %.2f%n%n", pedido.calcularTotal());
        }
    }

}
