package Financas.Interface;

import Financas.Controle.Interface_Controle_Usuario;
import Financas.Estrutura.Estrutura_Usuario;

import javax.swing.*;

public class Interface_Login extends JFrame{
    private JTextField CaixaTextoLogin;
    private JPasswordField CaixaTextoSenha;
    private JButton BotaoEntrar, BotaoCadastrar;
    private Interface_Controle_Usuario controleUsuario;

    public Interface_Login(Interface_Controle_Usuario controleUsuario){
        this.controleUsuario = controleUsuario;

        setTitle("Login");
        setSize(290,190);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel RotuloLogin = new JLabel("Login:");
        RotuloLogin.setBounds(20,20,80,25);
        add(RotuloLogin);

        CaixaTextoLogin = new JTextField();
        CaixaTextoLogin.setBounds(100,20,150,25);
        add(CaixaTextoLogin);

        JLabel RotuloSenha = new JLabel("Senha:");
        RotuloSenha.setBounds(20,60,80,25);
        add(RotuloSenha);

        CaixaTextoSenha = new JPasswordField();
        CaixaTextoSenha.setBounds(100,60,150,25);
        add(CaixaTextoSenha);

        BotaoEntrar = new JButton("Entrar");
        BotaoEntrar.setBounds(170,100,80,25);
        add(BotaoEntrar);

        BotaoCadastrar = new JButton("Cadastrar");
        BotaoCadastrar.setBounds(50,100,95,25);
        add(BotaoCadastrar);

        BotaoEntrar.addActionListener(e -> autenticarUsuario());
        BotaoCadastrar.addActionListener(e -> abrirCadastro());

        setVisible(true);
    }

    private void autenticarUsuario(){
        String login = CaixaTextoLogin.getText();
        String senha = String.valueOf(CaixaTextoSenha.getPassword());

        if(login.isEmpty() && senha.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha os Campos de Login e Senha!");
        }
        else if(login.isEmpty() || senha.isEmpty()){
            JOptionPane.showMessageDialog(this, "Todos os Campos devem ser preenchidos!");
        }
        else{
            Estrutura_Usuario usuario = controleUsuario.autenticar(login, senha);

            if(usuario != null){
                JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");
                new Interface_Principal(usuario.getId()).setVisible(true);
                dispose();
            }
            else{
                JOptionPane.showMessageDialog(this, "Login ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void abrirCadastro(){
        new Interface_Cadastro(controleUsuario).setVisible(true);
        dispose();
    }
}
