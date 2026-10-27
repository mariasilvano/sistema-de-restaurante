package org.example.cardapio;

public class Lanche {
    private String nome;
    private double preco;

    public Lanche(String nome, double preco){
        setNome(nome);
        setPreco(preco);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome.trim().isEmpty() || nome == null){
            throw new IllegalArgumentException("Nome do lanche mão deve ser nulo nem vazio");
        } else{
            this.nome = nome;
        }
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if(preco < 0){
            throw new IllegalArgumentException("Preço do lanche mão deve ser negativo");
        }else {
            this.preco = preco;
        }
    }
}
