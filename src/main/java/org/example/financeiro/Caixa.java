package org.example.financeiro;

import java.util.HashSet;

public class Caixa {
    private static final Caixa instancia = new Caixa();

    private final HashSet<Transacao> transacoes = new HashSet<>();

    private Caixa() {
    }

    public static Caixa getInstancia() {
        return instancia;
    }

    public boolean registrar(Transacao transacao) {
        return transacoes.add(transacao);
    }

    public void removerTransacao(int numeroPedido) {
        transacoes.removeIf(t -> t.getNumeroPedido() == numeroPedido);
    }

    public int getQuantidadeTransacoes() {
        return transacoes.size();
    }

    public double calcularFechamento() {
        double total = 0;

        for (Transacao t : transacoes) {
            total += t.getValor();
        }

        return total;
    }
}
