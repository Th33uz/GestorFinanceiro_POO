package Financas.Interface;

import Financas.Controle.Interface_Controle_Usuario;
import Financas.Util.GerenciadorFecharFrame;

import javax.swing.*;

public class Interface_Cadastro extends JFrame{
    private JTextField CaixaTextoNome;
    private JTextField CaixaTextoLogin;
    private JPasswordField CaixaTextoSenha;
    private JButton BotaoSalvar;
    private JButton BotaoVoltar;
    private Interface_Controle_Usuario controleUsuario;

    public Interface_Cadastro(Interface_Controle_Usuario controleUsuario){
        this.controleUsuario = controleUsuario;

        setTitle("Tela de Cadastro");
        setSize(290,240);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        GerenciadorFecharFrame.adicionarAcaoAoFechar(this, () -> {
            new Interface_Login(controleUsuario).setVisible(true);
        });

        JLabel RotuloNome = new JLabel("Nome:");
        RotuloNome.setBounds(20,20,80,25);
        add(RotuloNome);

        CaixaTextoNome = new JTextField();
        CaixaTextoNome.setBounds(100,20,150,25);
        add(CaixaTextoNome);

        JLabel RotuloLogin = new JLabel("Login:");
        RotuloLogin.setBounds(20,60,80,25);
        add(RotuloLogin);

        CaixaTextoLogin = new JTextField();
        CaixaTextoLogin.setBounds(100,60,150,25);
        add(CaixaTextoLogin);

        JLabel RotuloSenha = new JLabel("Senha:");
        RotuloSenha.setBounds(20,100,80,25);
        add(RotuloSenha);

        CaixaTextoSenha = new JPasswordField();
        CaixaTextoSenha.setBounds(100,100,150,25);
        add(CaixaTextoSenha);

        BotaoSalvar = new JButton("Salvar");
        BotaoSalvar.setBounds(170,150,80,25);
        add(BotaoSalvar);

        BotaoVoltar = new JButton("Voltar");
        BotaoVoltar.setBounds(30,150,80,25);
        add(BotaoVoltar);

        BotaoSalvar.addActionListener(e -> {
            String nome = CaixaTextoNome.getText();
            String login = CaixaTextoLogin.getText();
            String senha = String.valueOf(CaixaTextoSenha.getPassword());

            if(nome.isEmpty()){
                JOptionPane.showMessageDialog(null,"Preencha todos os campos!");
            }
            else if(login.isEmpty()){
                JOptionPane.showMessageDialog(null,"Preencha todos os campos!");
            }
            else if(senha.isEmpty()){
                JOptionPane.showMessageDialog(null,"Preencha todos os campos!");
            }
            else{
                boolean sucesso = controleUsuario.cadastrarUsuario(nome,login,senha);

                if(sucesso){
                    JOptionPane.showMessageDialog(null,"Usuário Cadastrado com Sucesso!");
                    new Interface_Login(controleUsuario).setVisible(true);
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Erro: O Login já está Cadastrado!");
                }
            }
        });

        BotaoVoltar.addActionListener(e -> {
            new Interface_Login(controleUsuario).setVisible(true);
            dispose();
        });
    }
}
