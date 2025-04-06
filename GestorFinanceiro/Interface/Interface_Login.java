package GestorFinanceiro.Interface;

import GestorFinanceiro.Controle.Controle_Usuario;
import GestorFinanceiro.Estrutura.Estrutura_Usuario;

import javax.swing.*;

public class Interface_Login extends JFrame{
    private JTextField CaixaTextoLogin;
    private JPasswordField CaixaTextoSenha;
    private JButton BotaoEntrar, BotaoCadastrar;
    private Controle_Usuario controleUsuario;

    public Interface_Login(Controle_Usuario controleUsuario){
        this.controleUsuario = controleUsuario;

        setTitle("Login");
        setSize(290, 190);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel RotuloLogin = new JLabel("Login:");
        RotuloLogin.setBounds(20, 20, 80, 25);
        add(RotuloLogin);

        CaixaTextoLogin = new JTextField();
        CaixaTextoLogin.setBounds(100, 20, 150, 25);
        add(CaixaTextoLogin);

        JLabel RotuloSenha = new JLabel("Senha:");
        RotuloSenha.setBounds(20, 60, 80, 25);
        add(RotuloSenha);

        CaixaTextoSenha = new JPasswordField();
        CaixaTextoSenha.setBounds(100, 60, 150, 25);
        add(CaixaTextoSenha);

        BotaoEntrar = new JButton("Entrar");
        BotaoEntrar.setBounds(170, 100, 80, 25);
        add(BotaoEntrar);

        BotaoCadastrar = new JButton("Cadastrar");
        BotaoCadastrar.setBounds(50, 100, 95, 25);
        add(BotaoCadastrar);

        BotaoEntrar.addActionListener(e -> {
            String login = CaixaTextoLogin.getText();
            String senha = String.valueOf(CaixaTextoSenha.getPassword());

            Estrutura_Usuario usuario = controleUsuario.validarLogin(login, senha);
            if(usuario == null){
                JOptionPane.showMessageDialog(null, "Login ou Senha Inválidos, Tente Novamente!");
            }

            else{
                JOptionPane.showMessageDialog(null, "Bem-vindo(a) - " + usuario.getNome());
                new Interface_Principal(usuario, controleUsuario);
                dispose();
            }
        });

        BotaoCadastrar.addActionListener(e -> {
            new Interface_Cadastro(controleUsuario);
            dispose();
        });

        setVisible(true);
    }
}
