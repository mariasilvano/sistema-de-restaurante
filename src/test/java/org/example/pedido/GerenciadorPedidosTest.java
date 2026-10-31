package org.example.pedido;

import org.example.cardapio.ComCheddar;
import org.example.cardapio.LancheFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GerenciadorPedidosTest {

    @Test
    void descricoesRepetidasContamComoUmaCombinacao() {
        GerenciadorPedidos gerenciador = new GerenciadorPedidos();

        Pedido p1 = gerenciador.criarPedido("Ana");
        Pedido p2 = gerenciador.criarPedido("Bia");
        p1.adicionarItem(LancheFactory.xBacon());
        p2.adicionarItem(LancheFactory.xBacon());

        assertEquals(1, gerenciador.getCombinacoes().size());
    }

    @Test
    void fechamentoSomaTodosOsPedidos() {
        GerenciadorPedidos gerenciador = new GerenciadorPedidos();

        Pedido p1 = gerenciador.criarPedido("Ana");
        p1.adicionarItem(LancheFactory.xBurger());  // 16
        p1.adicionarItem(LancheFactory.xSalada());  // 18

        Pedido p2 = gerenciador.criarPedido("Bia");
        p2.adicionarItem(new ComCheddar(LancheFactory.xBacon()));  // 26

        assertEquals(60.0, gerenciador.calcularTotalDoDia(), 0.001);
    }

    @Test
    void cancelarPedidoRetiraSeuValorDoTotal() {
        GerenciadorPedidos gerenciador = new GerenciadorPedidos();

        Pedido p1 = gerenciador.criarPedido("Ana");
        p1.adicionarItem(LancheFactory.xBurger());  // 16

        Pedido p2 = gerenciador.criarPedido("Bia");
        p2.adicionarItem(LancheFactory.xBacon());   // 22

        gerenciador.cancelarPedido(p1.getNumero());

        assertEquals(22.0, gerenciador.calcularTotalDoDia(), 0.001);
        assertNull(gerenciador.getPedido(p1.getNumero()));
    }
}
