package org.example.cardapio;

public class ComCheddar extends ItemCardapioDecorator {

    public ComCheddar(ItemCardapio itemCardapio) {
        super(itemCardapio);
    }

    @Override
    public String getDescricao() {
        return itemCardapioEmbrulhado.getDescricao() + " + cheddar";
    }

    @Override
    public double getPreco() {
        return itemCardapioEmbrulhado.getPreco() + 4.00;
    }
}
