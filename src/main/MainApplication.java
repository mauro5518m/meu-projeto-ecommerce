package main;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import repository.DatabaseConnection;
import service.EcommerceService;
import model.Usuario;

public class MainApplication extends JFrame {
    private EcommerceService service = new EcommerceService();
    private JTextArea areaLog;

    // Campos do formulário
    private JTextField txtNome;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JButton btnSalvar;
    private JButton btnListar;

    public MainApplication() {
        setTitle("Sistema de E-Commerce (SQLite)");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel Superior com o Formulário de Entrada
        JPanel painelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painelFormulario.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelFormulario.add(txtNome);

        painelFormulario.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        painelFormulario.add(txtEmail);

        painelFormulario.add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        painelFormulario.add(txtSenha);

        btnSalvar = new JButton("Registar Utilizador");
        btnListar = new JButton("Listar no Banco");
        painelFormulario.add(btnSalvar);
        painelFormulario.add(btnListar);

        add(painelFormulario, BorderLayout.NORTH);

        // Área de logs central para exibir os resultados do SQLite
        areaLog = new JTextArea();
        areaLog.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaLog);
        add(scroll, BorderLayout.CENTER);

        // --- Ações dos Botões ---

        // Ação de Salvar
        btnSalvar.addActionListener(e -> {
            String nome = txtNome.getText();
            String email = txtEmail.getText();
            String senha = new String(txtSenha.getPassword());

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                areaLog.append("Erro: Preencha todos os campos antes de salvar!\n");
                return;
            }

            Usuario novo = new Usuario(nome, email, senha);
            service.registarUsuario(novo);

            areaLog.append("Sucesso: '" + nome + "' foi inserido no SQLite!\n");

            // Limpa os campos
            txtNome.setText("");
            txtEmail.setText("");
            txtSenha.setText("");
        });

        // Ação de Listar
        btnListar.addActionListener(e -> {
            areaLog.append("\n--- Consultando Utilizadores no SQLite ---\n");
            List<Usuario> usuarios = service.obterTodosUsuarios();

            if (usuarios.isEmpty()) {
                areaLog.append("Nenhum utilizador encontrado na base de dados.\n");
            } else {
                for (Usuario u : usuarios) {
                    areaLog.append("ID: " + u.getId() + " | Nome: " + u.getNome() + " | Email: " + u.getEmail() + "\n");
                }
            }
            areaLog.append("-------------------------------------------\n");
        });

        areaLog.append("Interface Gráfica e Banco de Dados prontos!\n");
    }

    public static void main(String[] args) {
        // Inicializa as tabelas do Banco de Dados SQLite
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE IF NOT EXISTS usuario (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT NOT NULL, " +
                    "email TEXT UNIQUE NOT NULL, " +
                    "senha TEXT NOT NULL);");

            stmt.execute("CREATE TABLE IF NOT EXISTS produto (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT NOT NULL, " +
                    "preco REAL NOT NULL);");

            System.out.println("Tabelas verificadas/criadas com sucesso no SQLite!");

        } catch (Exception e) {
            System.err.println("Erro ao inicializar o banco de dados SQLite:");
            e.printStackTrace();
        }

        // Abre a Janela Swing
        SwingUtilities.invokeLater(() -> {
            new MainApplication().setVisible(true);
        });
    }
}