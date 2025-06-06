package Financas.Interface;

import Financas.Controle.Controle_Categoria;
import Financas.Controle.Controle_Historico;
import Financas.Controle.Controle_Resumo;
import Financas.Controle.Controle_Transacao;
import Financas.Estrutura.Estrutura_Usuario;
import Financas.Controle.Controle_Usuario;
import Financas.Util.GerenciadorFecharFrame;

import javax.swing.*;

public class Interface_Principal extends JFrame{

    private int idUsuario;
    private Estrutura_Usuario usuario;
    private Controle_Usuario controleUsuario;

    private JButton botaoCategorias;
    private JButton botaoTransacoes;
    private JButton botaoHistorico;
    private JButton botaoResumo;
    private JButton botaoVoltar;

    public Interface_Principal(int idUsuario){
        this.idUsuario = idUsuario;
        this.controleUsuario = new Controle_Usuario();
        this.usuario = controleUsuario.buscarPorId(idUsuario);

        setTitle("Tela Inicial");
        setSize(430,330);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        GerenciadorFecharFrame.adicionarAcaoAoFechar(this, () ->
                new Interface_Login(controleUsuario).setVisible(true)
        );

        JLabel rotuloMensagem = new JLabel("Bem-Vindo(a) - " + (usuario != null ? usuario.getNome() : "Usuário") + "!");
        rotuloMensagem.setBounds(150,20,200,25);
        add(rotuloMensagem);

        botaoCategorias = new JButton("Categorias");
        botaoCategorias.setBounds(150,60,120,25);
        add(botaoCategorias);

        botaoTransacoes = new JButton("Transações");
        botaoTransacoes.setBounds(150,100,120,25);
        add(botaoTransacoes);

        botaoHistorico = new JButton("Histórico");
        botaoHistorico.setBounds(150,140,120,25);
        add(botaoHistorico);

        botaoResumo = new JButton("Resumo");
        botaoResumo.setBounds(150,180,120,25);
        add(botaoResumo);

        botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(150,220,120,25);
        add(botaoVoltar);

        botaoCategorias.addActionListener(e -> {
            new Interface_Categoria(idUsuario, new Controle_Categoria()).setVisible(true);
            dispose();
        });

        botaoTransacoes.addActionListener(e -> {
            new Interface_Transacao(idUsuario, new Controle_Transacao(), new Controle_Categoria()).setVisible(true);
            dispose();
        });

        botaoHistorico.addActionListener(e -> {
            new Interface_Historico(idUsuario, new Controle_Historico()).setVisible(true);
            dispose();
        });

        botaoResumo.addActionListener(e -> {
            new Interface_Resumo(idUsuario, new Controle_Resumo()).setVisible(true);
            dispose();
        });

        botaoVoltar.addActionListener(e -> {
            new Interface_Login(new Controle_Usuario()).setVisible(true);
            dispose();
        });

        setVisible(true);
    }
}
