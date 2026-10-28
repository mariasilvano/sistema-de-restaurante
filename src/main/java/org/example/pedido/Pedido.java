package org.example.pedido;

import org.example.cardapio.ItemCardapio;
import org.example.cardapio.Lanche;
import java.util.ArrayList;

public class Pedido {
    private int numero;
    private String nomeCliente;
    private ArrayList<ItemCardapio> itensCardapio = new ArrayList<>();

    public Pedido(int numero, String nomeCliente) {
        this.numero = numero;
        setNomeCliente(nomeCliente);
    }

    public int getNumero() {
        return numero;
    }

    public void setNmero(int numero) {
        this.numero = numero;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        if(nomeCliente.trim().isEmpty() || nomeCliente == null){
            throw new IllegalArgumentException("Nome do cliente não pode ser nulo ou vazio!");
        }else {
            this.nomeCliente = nomeCliente;
        }
    }

    public ArrayList<ItemCardapio> getLanches() {
        return itensCardapio;
    }

    public void setLanches(ArrayList<ItemCardapio> lanches) {
        this.itensCardapio = lanches;
    }

    public void adicionarItem(ItemCardapio itemCardapio){
        itensCardapio.add(itemCardapio);
    }

    public ArrayList<ItemCardapio> getItens(){
        return itensCardapio;
    }

    public double calcularTotal(){

        double total = 0;

        for (ItemCardapio itemCardapio : itensCardapio){
            total += itemCardapio.getPreco();
        }
        return  total;
    }
}
