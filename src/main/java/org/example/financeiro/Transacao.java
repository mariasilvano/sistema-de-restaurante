package org.example.financeiro;

import java.util.Objects;

public class Transacao {
    private final int numeroPedido;
    private final double valor;

    public Transacao(int numeroPedido, double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("O valor da transação não pode ser negativo!");
        }
        this.numeroPedido = numeroPedido;
        this.valor = valor;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao outra = (Transacao) o;
        return numeroPedido == outra.numeroPedido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroPedido);
    }
}
