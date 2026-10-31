package org.example.pedido;

import org.example.cardapio.ItemCardapio;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;

public class GerenciadorPedidos {
    private HashMap<Integer, Pedido> pedidos = new HashMap<>();
    private int contadorPedidos = 0;

    public Pedido criarPedido(String nomeCliente) {
        contadorPedidos++;
        Pedido pedido = new Pedido(contadorPedidos, nomeCliente);
        pedidos.put(pedido.getNumero(), pedido);
        return pedido;
    }

    public HashMap<Integer, Pedido> getPedidos() {
        return pedidos;
    }

    public double calcularTotalDoDia() {

        double total = 0;

        for (Pedido pedido : pedidos.values()) {
           total += pedido.calcularTotal();
        }

        return total;
    }

    public double calcularTotalGorjetas() {
        double total = 0;

        for (Pedido pedido : pedidos.values()) {
            total += pedido.getGorjeta();
        }

        return total;
    }

    public HashSet<String> getCombinacoes() {
        HashSet<String> combinacoes = new HashSet<>();

        for (Pedido pedido : pedidos.values()) {

            for (ItemCardapio item : pedido.getItens()) {

                combinacoes.add(item.getDescricao());
            }
        }

        return combinacoes;
    }


    public HashSet<String> getLanchesBase() {
        HashSet<String> lanchesBase = new HashSet<>();

        for (Pedido pedido : pedidos.values()) {

            for (ItemCardapio item : pedido.getItens()) {

                lanchesBase.add(item.getNomeBase());
            }
        }

        return lanchesBase;
    }

    public Pedido getPedido(int numPedido) {
        return pedidos.get(numPedido);
    }

    public void cancelarPedido(int numPedido){
        pedidos.remove(numPedido);
    }

}