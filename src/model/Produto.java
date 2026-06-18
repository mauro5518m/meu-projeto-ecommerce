package model;

public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private String categoria;
    private int quantidadeEstoque;

    public Produto(int id, String nome, String descricao, double preco, String categoria, int quantidadeEstoque) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public String getDescricao() { return descricao; }
    public String getCategoria() { return categoria; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    
    public void deduzirEstoque(int qtd) {
        if (qtd > this.quantidadeEstoque) {
            throw new IllegalArgumentException("Estoque insuficiente!");
        }
        this.quantidadeEstoque -= qtd;
    }
}