package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Pedido {
    private int id;
    private Usuario cliente;
    private Map<Produto, Integer> itens;
    private double total;
    private Date data;
    private String status;

    public Pedido(int id, Usuario cliente, CarrinhoCompras carrinho) {
        this.id = id;
        this.cliente = cliente;
        this.itens = new HashMap<>(carrinho.getItens());
        this.total = carrinho.calcularTotal();
        this.data = new Date();
        this.status = "CONCLUÍDO";
        this.itens.forEach((p, qtd) -> p.deduzirEstoque(qtd));
    }

    public int getId() { return id; }
    public Usuario getCliente() { return cliente; }
    public double getTotal() { return total; }
    public String getStatus() { return status; }
}