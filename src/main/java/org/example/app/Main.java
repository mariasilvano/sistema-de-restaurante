package org.example.app;


import org.example.cardapio.Lanche;
import org.example.pedido.Pedido;

public class Main {
    static void main() {

        int opcao = -1;
        String nome;
        MenuConsole menu = new MenuConsole();
        RestauranteFacade restaurante = new RestauranteFacade();


        while (opcao != 0){
            menu.exibirMenu();
            opcao = menu.lerOpcao();

            switch(opcao){
                case 1:

                    nome = menu.lerNomeCliente();
                    Pedido pedido = restaurante.criarPedido(nome);
                    menu.exibirCardapio();
                    boolean adicionarMais;

                    do {
                        int opcaoLanche = menu.lerOpcaoLanche();
                        Lanche lanche = restaurante.criarLanche(opcaoLanche);
                        restaurante.adicionarLanche(pedido, lanche);
                        adicionarMais = menu.desejaAdicionarLanche();

                    }while(adicionarMais);
                    break;


                case 2:
                    //2 - Listar pedidos do dia
                    menu.exibirPedidos(restaurante.getPedidos());
                    break;
                case 3:
                    //3 - Fechar restaurante
                    double total = restaurante.getTotalDia();
                    menu.exibirFechamentoDoDia(total);
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
