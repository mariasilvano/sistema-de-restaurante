package org.example.cardapio;

public abstract class ItemCardapioDecorator implements ItemCardapio {

    protected ItemCardapio itemCardapioEmbrulhado;

    public ItemCardapioDecorator(ItemCardapio itemCardapio) {
        this.itemCardapioEmbrulhado = itemCardapio;
    }

    @Override
    public String getNomeBase() {
        return itemCardapioEmbrulhado.getNomeBase();
    }
}
