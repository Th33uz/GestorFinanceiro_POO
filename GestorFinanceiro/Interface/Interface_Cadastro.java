package GestorFinanceiro.Interface;

import GestorFinanceiro.Controle.Controle_Usuario;
import GestorFinanceiro.Estrutura.Estrutura_Usuario;

import javax.swing.*;

public class Interface_Cadastro extends JFrame{
    private JTextField CaixaTextoNome, CaixaTextoLogin;
    private JPasswordField CaixaTextoSenha;
    private JButton BotaoSalvar, BotaoVoltar;
    private Controle_Usuario controleUsuario;

    public Interface_Cadastro(Controle_Usuario controleUsuario){
        this.controleUsuario = controleUsuario;

        setTitle("Tela de Cadastro");
        setSize(290, 240);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel RotuloNome = new JLabel("Nome:");
        RotuloNome.setBounds(20, 20, 80, 25);
        add(RotuloNome);

        CaixaTextoNome = new JTextField();
        CaixaTextoNome.setBounds(100, 20, 150, 25);
        add(CaixaTextoNome);

        JLabel RotuloLogin = new JLabel("Login:");
        RotuloLogin.setBounds(20, 60, 80, 25);
        add(RotuloLogin);

        CaixaTextoLogin = new JTextField();
        CaixaTextoLogin.setBounds(100, 60, 150, 25);
        add(CaixaTextoLogin);

        JLabel RotuloSenha = new JLabel("Senha:");
        RotuloSenha.setBounds(20, 100, 80, 25);
        add(RotuloSenha);

        CaixaTextoSenha = new JPasswordField();
        CaixaTextoSenha.setBounds(100, 100, 150, 25);
        add(CaixaTextoSenha);

        BotaoSalvar = new JButton("Salvar");
        BotaoSalvar.setBounds(170, 150, 80, 25);
        add(BotaoSalvar);

        BotaoVoltar = new JButton("Voltar");
        BotaoVoltar.setBounds(30, 150, 80, 25);
        add(BotaoVoltar);

        BotaoSalvar.addActionListener(e -> {
            String nome = CaixaTextoNome.getText();
            String login = CaixaTextoLogin.getText();
            String senha = String.valueOf(CaixaTextoSenha.getPassword());

            if(nome.isEmpty() || login.isEmpty() || senha.isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            }

            else{
                int id = controleUsuario.getUsuarios().size() + 1;
                Estrutura_Usuario novoUsuario = new Estrutura_Usuario(id, nome, login, senha);
                controleUsuario.adicionarUsuario(novoUsuario);
                JOptionPane.showMessageDialog(null, "Usuário Cadastrado com Sucesso!");
                new Interface_Login(controleUsuario);
                dispose();
            }
        });

        BotaoVoltar.addActionListener(e -> {
            new Interface_Login(controleUsuario);
            dispose();
        });

        setVisible(true);
    }
}
