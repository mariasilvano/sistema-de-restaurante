package org.example.pedido;

import org.example.cardapio.ItemCardapio;
import java.util.ArrayList;

public class Pedido {
    private int numero;
    private String nomeCliente;
    private ArrayList<ItemCardapio> itensCardapio = new ArrayList<>();
    private double gorjeta = 0;

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
        if(nomeCliente == null || nomeCliente.trim().isEmpty()){
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

    public void removerItem(int numItem){
        if (numItem < 0 || numItem >= itensCardapio.size()) {
            throw new IllegalArgumentException("Item inválido!");
        }
        itensCardapio.remove(numItem);
    }

    public ArrayList<ItemCardapio> getItens(){
        return itensCardapio;
    }

    public double getGorjeta() {
        return gorjeta;
    }

    public void setGorjeta(double gorjeta) {
        if (gorjeta < 0) {
            throw new IllegalArgumentException("A gorjeta não pode ser negativa!");
        }
        this.gorjeta = gorjeta;
    }

    public double calcularTotal(){

        double total = 0;

        for (ItemCardapio itemCardapio : itensCardapio){
            total += itemCardapio.getPreco();
        }
        return  total;
    }
}
