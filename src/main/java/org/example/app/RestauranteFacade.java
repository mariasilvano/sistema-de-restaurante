package org.example.app;

import org.example.cardapio.*;
import org.example.pedido.GerenciadorPedidos;
import org.example.pedido.Pedido;
import org.example.financeiro.Caixa;
import org.example.financeiro.Transacao;
import java.util.HashMap;
import java.util.HashSet;


public class RestauranteFacade {
    GerenciadorPedidos gerenciadorPedidos = new GerenciadorPedidos();
    private Caixa caixa = Caixa.getInstancia();

    public Pedido criarPedido(String nomeCliente){
        return gerenciadorPedidos.criarPedido(nomeCliente);
    }

    public Lanche criarLanche(int lanche){
        switch (lanche){
            case 1:
                 return LancheFactory.xBurger();
            case 2:
                return LancheFactory.xSalada();
            case 3:
                return LancheFactory.xBacon();
            default:
                throw new IllegalArgumentException(
                        "Opção de lanche inválida: " + lanche
                );
        }

    }

    public void adicionarLanche(Pedido pedido, ItemCardapio lanche){
        pedido.adicionarItem(lanche);
    }

    public void removerLanche(Pedido pedido, int numItem){
        pedido.removerItem(numItem);
    }

    public void cancelarPedido(int numpedido){
        gerenciadorPedidos.cancelarPedido(numpedido);
        caixa.removerTransacao(numpedido);
    }

    public HashMap<Integer, Pedido> getPedidos() {
        return gerenciadorPedidos.getPedidos();
    }

    public double getTotalDia(){
        return gerenciadorPedidos.calcularTotalDoDia();
    }

    public void registrarGorjeta(Pedido pedido, double valor){
        pedido.setGorjeta(valor);
    }

    public double getTotalGorjetas(){
        return gerenciadorPedidos.calcularTotalGorjetas();
    }

    // Devolve false se o pedido já estava pago.
    public boolean pagarPedido(Pedido pedido){
        double valor = pedido.calcularTotal() + pedido.getGorjeta();
        return caixa.registrar(new Transacao(pedido.getNumero(), valor));
    }

    public int getQuantidadePedidosPagos(){
        return caixa.getQuantidadeTransacoes();
    }

    public double getTotalRecebido(){
        return caixa.calcularFechamento();
    }

    public HashSet<String> getLanchesBase(){
        return gerenciadorPedidos.getLanchesBase();
    }

    public HashSet<String> getCombinacoes(){
        return gerenciadorPedidos.getCombinacoes();
    }

    public ItemCardapio adicionarAdicional(ItemCardapio lanche, boolean adicionalCheddar, boolean adicionalBacon) {

        if(adicionalCheddar){
            lanche = new ComCheddar(lanche);
        }

        if(adicionalBacon){
            lanche = new ComBaconExtra(lanche);
        }

        return lanche;

    }

    public Pedido getPedido(int idPedido){
        return gerenciadorPedidos.getPedido(idPedido);
    }

}
