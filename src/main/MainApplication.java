package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import repository.*;
import model.*;
import service.*;

public class MainApplication extends JFrame {
    private EcommerceService service = new EcommerceService();
    private ProdutoRepository prodRepo = new ProdutoRepository();

    // UI Campos de Usuário
    private JTextField txtNome, txtEmail, txtEndereco, txtTelefone;
    private JPasswordField txtSenha;

    // UI Campos de Produto
    private JTextField txtNomeProd, txtPrecoProd, txtEstoqueProd;
    private DefaultTableModel modeloTabela;

    public MainApplication() {
        setTitle("Sistema E-Commerce (Universidade do Mindelo)");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JTabbedPane para separar Usuários de Produtos (Requisito de interface intuitiva)
        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Usuários", criarPainelUsuarios());
        abas.addTab("Produtos", criarPainelProdutos());

        add(abas);
        setLocationRelativeTo(null);
    }

    private JPanel criarPainelUsuarios() {
        JPanel painel = new JPanel(new GridLayout(6, 2, 5, 5));
        txtNome = new JTextField(); txtEmail = new JTextField();
        txtSenha = new JPasswordField(); txtEndereco = new JTextField();
        txtTelefone = new JTextField();
        JButton btnSalvar = new JButton("Registar Utilizador");

        painel.add(new JLabel("Nome:")); painel.add(txtNome);
        painel.add(new JLabel("Email:")); painel.add(txtEmail);
        painel.add(new JLabel("Senha:")); painel.add(txtSenha);
        painel.add(new JLabel("Endereço:")); painel.add(txtEndereco);
        painel.add(new JLabel("Telefone:")); painel.add(txtTelefone);
        painel.add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            service.registarUsuario(new Usuario(txtNome.getText(), txtEmail.getText(),
                    new String(txtSenha.getPassword()), txtEndereco.getText(), txtTelefone.getText()));
            JOptionPane.showMessageDialog(this, "Usuário registado com sucesso!");
        });
        return painel;
    }

    private JPanel criarPainelProdutos() {
        JPanel painel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(4, 2));

        txtNomeProd = new JTextField(); txtPrecoProd = new JTextField();
        txtEstoqueProd = new JTextField();
        JButton btnAdd = new JButton("Adicionar Produto");

        form.add(new JLabel("Nome:")); form.add(txtNomeProd);
        form.add(new JLabel("Preço:")); form.add(txtPrecoProd);
        form.add(new JLabel("Estoque:")); form.add(txtEstoqueProd);
        form.add(btnAdd);

        modeloTabela = new DefaultTableModel(new Object[]{"ID", "Nome", "Preço", "Estoque"}, 0);
        JTable tabela = new JTable(modeloTabela);

        btnAdd.addActionListener(e -> {
            Produto p = new Produto(0, txtNomeProd.getText(), "Desc",
                    Double.parseDouble(txtPrecoProd.getText()), "Geral",
                    Integer.parseInt(txtEstoqueProd.getText()));
            prodRepo.salvar(p);
            carregarTabelaProdutos(); // Atualiza a lista visualmente
            JOptionPane.showMessageDialog(this, "Produto adicionado!");
        });

        painel.add(form, BorderLayout.NORTH);
        painel.add(new JScrollPane(tabela), BorderLayout.CENTER);
        carregarTabelaProdutos();
        return painel;
    }

    private void carregarTabelaProdutos() {
        modeloTabela.setRowCount(0);
        for (Produto p : prodRepo.listarTodos()) {
            modeloTabela.addRow(new Object[]{p.getId(), p.getNome(), p.getPreco(), p.getQuantidadeEstoque()});
        }
    }

    public static void main(String[] args) {
        // Inicialização do Banco (o código de criação das tabelas continua igual)
        SwingUtilities.invokeLater(() -> new MainApplication().setVisible(true));
    }
}