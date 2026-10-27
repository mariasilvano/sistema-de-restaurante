package org.example.pedido;

import java.util.HashMap;
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

    public Pedido buscarPedido(int numero){
        if (pedidos.containsKey(numero)){
            return pedidos.get(numero);
        }else{
            throw new NoSuchElementException("Pedido não encontrado");
        }
    }

    public HashMap<Integer, Pedido> getPedidos() {
        return pedidos;
    }

    public double caclularTotalDoDia() {

        double total = 0;

        for (Pedido pedido : pedidos.values()) {
           total += pedido.calcularTotal();
        }

        return total;
    }
}