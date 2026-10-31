package org.example.app;

import org.example.cardapio.*;
import org.example.pedido.Pedido;

import java.util.*;

public class MenuConsole {

    private Scanner scanner = new Scanner(System.in);

    public void exibirMenu(){
        exibirMensagem("=== RESTAURANTE === \n" +
                "1 - Registrar novo pedido \n" +
                "2 - Listar pedidos do dia \n" +
                "3 - Fechar restaurante \n" +
                "4 - Buscar pedido \n" +
                "5 - Pagar pedido \n" +
                "0 - Sair");
    }

    public int lerOpcao(){
        return lerNumero();
    }

    public void exibirCardapio(){
        exibirMensagem("=== CARDÁPIO === \n" +
                "1 - X-Burger — R$ 16,00 \n" +
                "2 - X-Salada — R$ 18,00 \n" +
                "3 - X-Bacon — R$ 22,00 ");
    }

    public String lerNomeCliente() {
        while (true) {
            System.out.print("Digite o nome do cliente: ");
            String nome = scanner.nextLine();

            if (nome != null && !nome.trim().isEmpty()) {
                return nome.trim();
            }

            exibirMensagem("O nome não pode ser vazio!");
        }
    }

    public int lerOpcaoLanche() {
        System.out.print("Digite qual o lanche desejado: ");

        while (true) {
            int opcao = lerNumero();

            if (opcao >= 1 && opcao <= 3) {
                return opcao;
            }

            exibirMensagem("Opção inválida!");
        }
    }

    public double lerGorjeta(double totalPedido) {
        exibirValor("Total do pedido:", totalPedido);
        exibirMensagem("Deseja deixar gorjeta? (S/N)");
        String resposta = scanner.nextLine().trim();

        while (!resposta.equalsIgnoreCase("S")
                && !resposta.equalsIgnoreCase("N")) {
            System.out.print("Resposta inválida! Digite S ou N: ");
            resposta = scanner.nextLine().trim();
        }

        if (resposta.equalsIgnoreCase("N")) {
            return 0;
        }

        while (true) {
            System.out.print("Valor da gorjeta (R$): ");
            String texto = scanner.nextLine().trim().replace(',', '.');

            try {
                double valor = Double.parseDouble(texto);

                if (valor >= 0) {
                    return valor;
                }
                exibirMensagem("A gorjeta não pode ser negativa!");
            } catch (NumberFormatException e) {
                exibirMensagem("Digite um valor válido! Ex: 5,00");
            }
        }
    }

    public void exibirRecebimentos(int pedidosPagos, double totalRecebido){
        exibirMensagem("Pedidos pagos: " + pedidosPagos);
        exibirValor("Total recebido no caixa:", totalRecebido);
    }

    public void exibirFechamentoDoDia(double total, double gorjetas, HashSet<String> combinacoes, HashSet<String> lanchesBase){
        exibirMensagem("=== Fechamento de Caixa === \n" );
        exibirMensagem("Total de combinações: "+ combinacoes.size());
        for(String combinacao:combinacoes){
            exibirMensagem(combinacao);
        }
        exibirMensagem("Lanches base diferentes: " + lanchesBase.size());
        for (String lancheBase : lanchesBase) {
            exibirMensagem(lancheBase);
        }
        exibirValor("Total de vendas:", total);
        exibirValor("Total de gorjetas:", gorjetas);
        exibirValor("Total geral:", total + gorjetas);
    }

    public boolean desejaAdicionarLanche(){
        exibirMensagem("Deseja adicionar mais um lanche? (S/N)");
        String resposta = scanner.nextLine();

        while (!resposta.equalsIgnoreCase("S")
                && !resposta.equalsIgnoreCase("N")) {
            System.out.print("Resposta inválida! Digite S ou N: ");
            resposta = scanner.nextLine();
        }

        return resposta.equalsIgnoreCase("S");

    }

    public int adicional() {

        exibirMensagem("Deseja algum adicional?");
        exibirMensagem("1 - Cheddar (+ R$ 4,00)");
        exibirMensagem("2 - Bacon extra (+ R$ 6,00)");
        exibirMensagem("0 - Finalizar personalização");

        while (true) {
            int opcao = lerNumero();

            if (opcao >= 0 && opcao <= 2) {
                return opcao;
            }

            exibirMensagem("Opção inválida! Digite 0, 1 ou 2:");
        }
    }

    public void exibirPedidos(HashMap<Integer, Pedido> pedidos) {

        if (pedidos.isEmpty()){
            exibirMensagem("Não há pedidos!");
            return;
        }
        for (Map.Entry<Integer, Pedido> entry : pedidos.entrySet()) {
            int id = entry.getKey();
            Pedido pedido = entry.getValue();

            exibirMensagem("Pedido: " + id);

            for (ItemCardapio itemCardapio : pedido.getItens()) {
                exibirValor(itemCardapio.getDescricao() + " -", itemCardapio.getPreco());
            }

            exibirMensagem("------ Total: ------");
            System.out.printf("R$ %.2f%n%n", pedido.calcularTotal());
        }
    }


    public void desejaRetirar(Lanche lanche, int opcaoLanche) {
        List<String> ingredientes = LancheFactory.getIngredientes(opcaoLanche);
        List<String> ingredientesCopia = new ArrayList<>(ingredientes);

        exibirMensagem("Ingredientes:");

        for (String ingrediente : ingredientes) {
            exibirMensagem("- " + ingrediente);
        }

        boolean continuar = true;

        while(continuar) {

            exibirMensagem("Deseja retirar algum ingrediente?");
            exibirMensagem("Digite o nome do ingrediente ou N para não retirar mais nenhum:");

            String ingrediente = scanner.nextLine();

            if (ingrediente.equalsIgnoreCase("n")) {
                continuar = false;

            }else if (ingredientesCopia.contains(ingrediente.toLowerCase())) {

                switch (ingrediente.toLowerCase()) {
                    case "queijo" -> lanche.setQueijo(false);
                    case "alface" -> lanche.setAlface(false);
                    case "tomate" -> lanche.setTomate(false);
                    case "cebola" -> lanche.setCebola(false);

                    default -> exibirMensagem("Ingrediente inválido!");
                }


            }else{
                exibirMensagem("Ingrediente inválido ou indisponível!");
            }

            if (ingredientesCopia.isEmpty()) {
                continuar = false;
            }

        }
    }

    public int getPedido() {
        exibirMensagem("Digite o número do pedido: ");
        return lerNumero();
    }

    public void exibirMensagem(String mensagem){
        System.out.println(mensagem);
    }

    public void exibirPedido(Pedido pedido){
        exibirMensagem("=== PEDIDO ===");
        exibirMensagem("Número: " + pedido.getNumero());
        exibirMensagem("Cliente: " + pedido.getNomeCliente());

        ArrayList<ItemCardapio> itens = pedido.getItens();
        exibirMensagem("Itens:");
        for (int i = 0; i < itens.size(); i++) {
            ItemCardapio item = itens.get(i);

            exibirValor(i + 1 + " - " + item.getDescricao() + " -", item.getPreco());
        }
    }

    public int menuModificarPedido() {
        System.out.println("=== MODIFICAR PEDIDO ===");
        System.out.println("1 - Adicionar lanche");
        System.out.println("2 - Remover lanche");
        System.out.println("3 - Cancelar pedido");
        System.out.println("0 - Voltar");

        return lerNumero();
    }

    private int lerNumero() {
        while (true) {
            try {
                int numero = scanner.nextInt();
                scanner.nextLine();
                return numero;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                exibirMensagem("Digite um número válido!");
            }
        }
    }

    public int lerItemParaRemover(Pedido pedido){
        exibirMensagem("Itens do pedido:");

        ArrayList<ItemCardapio> itens = pedido.getItens();

        for (int i = 0; i < itens.size(); i++) {
            ItemCardapio item = itens.get(i);

            System.out.printf("%d - %s - R$ %.2f%n",
                    i + 1,
                    item.getDescricao(),
                    item.getPreco());
        }

        exibirMensagem("Digite o número do item que deseja remover:");

        return lerNumero();

    }

    public boolean confirmaCancelamento(int numPedido){
        exibirMensagem("Deseja realmente cancelar o pedido "+ numPedido+"? (S/N)");
        String resposta = scanner.nextLine();

        while (!resposta.equalsIgnoreCase("S")
                && !resposta.equalsIgnoreCase("N")) {
            System.out.print("Resposta inválida! Digite S ou N: ");
            resposta = scanner.nextLine();
        }

        return resposta.equalsIgnoreCase("S");
    }

    private void exibirValor(String mensagem, double valor) {
        System.out.printf(mensagem + " R$ %.2f%n", valor);
    }

}
