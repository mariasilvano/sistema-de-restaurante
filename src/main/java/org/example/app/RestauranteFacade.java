package org.example.app;

import org.example.cardapio.Lanche;
import org.example.pedido.GerenciadorPedidos;
import org.example.pedido.Pedido;

import java.util.HashMap;
import java.util.Map;

public class RestauranteFacade {
    GerenciadorPedidos gerenciadorPedidos = new GerenciadorPedidos();

    public Pedido criarPedido(String nomeCliente){
        return gerenciadorPedidos.criarPedido(nomeCliente);
    }

    public Lanche criarLanche(int lanche){
        switch (lanche){
            case 1:
                 return new Lanche("X-Burger", 16.00);
            case 2:
                return new Lanche("X-Salada",18.00);
            case 3:
                return new Lanche("X-Bacon", 22.00);
            default:
                throw new IllegalArgumentException(
                        "Opção de lanche inválida: " + lanche
                );
        }
    }

    public void adicionarLanche(Pedido pedido, Lanche lanche){
        pedido.adicionarItem(lanche);
    }

    public HashMap<Integer, Pedido> getPedidos() {
        return gerenciadorPedidos.getPedidos();
    }

    public double getTotalDia(){
        return gerenciadorPedidos.caclularTotalDoDia();
    }

}
