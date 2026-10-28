package org.example.cardapio;

public class Lanche implements ItemCardapio {
    private String descricao;
    private double preco;

    public Lanche(String descricao, double preco){
        setDescricao(descricao);
        setPreco(preco);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if(descricao.trim().isEmpty() || descricao == null){
            throw new IllegalArgumentException("Nome do lanche mão deve ser nulo nem vazio");
        } else{
            this.descricao = descricao;
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
