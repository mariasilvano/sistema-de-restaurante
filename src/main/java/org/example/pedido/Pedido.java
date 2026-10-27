package org.example.pedido;

import org.example.cardapio.Lanche;
import java.util.ArrayList;

public class Pedido {
    private int numero;
    private String nomeCliente;
    private ArrayList<Lanche> lanches = new ArrayList<>();

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

    public ArrayList<Lanche> getLanches() {
        return lanches;
    }

    public void setLanches(ArrayList<Lanche> lanches) {
        this.lanches = lanches;
    }

    public void adicionarItem(Lanche lanche){
        lanches.add(lanche);
    }

    public ArrayList<Lanche> getItens(){
        return lanches;
    }

    public double calcularTotal(){

        double total = 0;

        for (Lanche lanche : lanches){
            total += lanche.getPreco();
        }
        return  total;
    }
}
