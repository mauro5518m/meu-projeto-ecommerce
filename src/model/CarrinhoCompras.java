package model;

import java.util.HashMap;
import java.util.Map;

public class CarrinhoCompras {
    private Map<Produto, Integer> itens = new HashMap<>();

    public void adicionar(Produto p, int qtd) {
        if (p.getQuantidadeEstoque() < qtd) {
            throw new IllegalArgumentException("Sem estoque suficiente!");
        }
        itens.put(p, itens.getOrDefault(p, 0) + qtd);
    }

    public void remover(Produto p) { itens.remove(p); }
    
    public double calcularTotal() {
        return itens.entrySet().stream().mapToDouble(e -> e.getKey().getPreco() * e.getValue()).sum();
    }

    public Map<Produto, Integer> getItens() { return itens; }
    public void limpar() { itens.clear(); }
}