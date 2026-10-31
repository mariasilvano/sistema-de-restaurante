package org.example.cardapio;

public class ComBaconExtra extends ItemCardapioDecorator {

    public ComBaconExtra(ItemCardapio itemCardapio) {
        super(itemCardapio);
    }

    @Override
    public String getDescricao() {
        return itemCardapioEmbrulhado.getDescricao() + " + bacon extra";
    }

    @Override
    public double getPreco() {
        return itemCardapioEmbrulhado.getPreco() + 6.00;
    }

}
