package org.example.app;


import org.example.cardapio.ItemCardapio;
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

                    boolean adicionarMais;

                    do {
                        ItemCardapio item = montarLanche(menu, restaurante);

                        restaurante.adicionarLanche(pedido, item);

                        adicionarMais = menu.desejaAdicionarLanche();

                    } while (adicionarMais);

                    menu.exibirPedido(pedido);

                    double gorjeta = menu.lerGorjeta(pedido.calcularTotal());
                    restaurante.registrarGorjeta(pedido, gorjeta);
                    break;
                case 2:
                    //2 - Listar pedidos do dia
                    menu.exibirPedidos(restaurante.getPedidos());
                    break;
                case 3:
                    //3 - Fechar restaurante
                    double total = restaurante.getTotalDia();
                    menu.exibirFechamentoDoDia(total, restaurante.getTotalGorjetas(), restaurante.getCombinacoes(), restaurante.getLanchesBase());
                    menu.exibirRecebimentos(restaurante.getQuantidadePedidosPagos(), restaurante.getTotalRecebido());
                    break;

                case 4:
                    //Buscar pedido pelo número
                   int numPedido =  menu.getPedido();
                   pedido = restaurante.getPedido(numPedido);

                    if (pedido == null) {
                        menu.exibirMensagem("Pedido não encontrado!");
                    } else {
                       menu.exibirPedido(pedido);

                       int opcaoAlterar;

                        do {
                            opcaoAlterar = menu.menuModificarPedido();

                            switch (opcaoAlterar) {
                                case 1:
                                    // Adicionar lanche
                                    do {
                                        ItemCardapio item = montarLanche(menu, restaurante);

                                        restaurante.adicionarLanche(pedido, item);

                                        adicionarMais = menu.desejaAdicionarLanche();

                                    } while (adicionarMais);
                                    break;

                                case 2:
                                    // Remover lanche
                                    try {
                                        int numeroItem = menu.lerItemParaRemover(pedido);

                                        restaurante.removerLanche(pedido, numeroItem - 1);

                                        menu.exibirMensagem("Item removido com sucesso!");

                                    } catch (IllegalArgumentException e) {
                                        menu.exibirMensagem(e.getMessage());
                                    }
                                    break;

                                case 3:
                                    // Cancelar pedido

                                    if (menu.confirmaCancelamento(numPedido)){
                                        restaurante.cancelarPedido(numPedido);
                                        menu.exibirMensagem("Pedido cancelado!");
                                        opcaoAlterar = 0;
                                    }
                                    break;

                                case 0:
                                    // Voltar
                                    menu.exibirMensagem("Voltando ao menu principal...");
                                    break;
                                default:
                                    menu.exibirMensagem("Opção inválida!");
                                    break;
                            }

                        } while (opcaoAlterar != 0);

                    }
                    break;

                case 5:
                    //Pagar pedido
                    Pedido pedidoPagar = restaurante.getPedido(menu.getPedido());

                    if (pedidoPagar == null) {
                        menu.exibirMensagem("Pedido não encontrado!");
                    } else if (restaurante.pagarPedido(pedidoPagar)) {
                        menu.exibirMensagem("Pedido pago!");
                    } else {
                        menu.exibirMensagem("Este pedido já foi pago!");
                    }
                    break;

                case 0:
                    menu.exibirMensagem("Encerrando Sistema...");
                    break;
                default:
                    menu.exibirMensagem("Opção inválida!");
                    break;
            }
        }
    }

    private static  ItemCardapio montarLanche(MenuConsole menu, RestauranteFacade restaurante) {

        boolean adicionalCheddar = false, adicionalBacon = false;

            menu.exibirCardapio();

            int opcaoLanche = menu.lerOpcaoLanche();

            Lanche lanche = restaurante.criarLanche(opcaoLanche);

            menu.desejaRetirar(lanche, opcaoLanche);

            ItemCardapio item = lanche;

            int opcaoAdicional = -1;

            while (opcaoAdicional != 0) {

                if (adicionalCheddar && adicionalBacon){
                    break;
                }

                opcaoAdicional = menu.adicional();

                if (opcaoAdicional == 1){
                    adicionalCheddar = true;
                } else if (opcaoAdicional == 2) {
                    adicionalBacon = true;
                }
            }

        if (adicionalCheddar || adicionalBacon) {
            item = restaurante.adicionarAdicional(item, adicionalCheddar, adicionalBacon);
        }

        return item;
    }
}
