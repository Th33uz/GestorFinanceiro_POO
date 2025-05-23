package GestorFinanceiro.Interface;

import GestorFinanceiro.Controle.Controle_Usuario;
import GestorFinanceiro.Estrutura.Estrutura_Usuario;

import javax.swing.*;

public class Interface_Principal extends JFrame{
    private JButton BotaoCategorias, BotaoTransacoes, BotaoHistorico, BotaoResumo, BotaoVoltar;
    private Estrutura_Usuario usuario;
    private Controle_Usuario controleUsuario;

    public Interface_Principal(Estrutura_Usuario usuario, Controle_Usuario controleUsuario){
        this.usuario = usuario;
        this.controleUsuario = controleUsuario;

        setTitle("Tela Inicial");
        setSize(420, 330);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel RotuloMensagem = new JLabel("Bem-vindo(a) - " + usuario.getNome());
        RotuloMensagem.setBounds(150, 20, 200, 25);
        add(RotuloMensagem);

        BotaoCategorias = new JButton("Categorias");
        BotaoCategorias.setBounds(150, 60, 120, 25);
        add(BotaoCategorias);

        BotaoTransacoes = new JButton("Transações");
        BotaoTransacoes.setBounds(150, 100, 120, 25);
        add(BotaoTransacoes);

        BotaoHistorico = new JButton("Histórico");
        BotaoHistorico.setBounds(150, 140, 120, 25);
        add(BotaoHistorico);

        BotaoResumo = new JButton("Resumo");
        BotaoResumo.setBounds(150, 180, 120, 25);
        add(BotaoResumo);

        BotaoVoltar = new JButton("Voltar");
        BotaoVoltar.setBounds(150, 220, 120, 25);
        add(BotaoVoltar);

        BotaoCategorias.addActionListener(e -> {
            new Interface_Categoria(usuario, controleUsuario);
            dispose();
        });

        BotaoTransacoes.addActionListener(e -> {
            new Interface_Transacao(usuario, controleUsuario);
            dispose();
        });

        BotaoHistorico.addActionListener(e -> {
            new Interface_Historico(usuario, controleUsuario);
            dispose();
        });

        BotaoResumo.addActionListener(e -> {
            new Interface_Resumo(usuario, controleUsuario);
            dispose();
        });

        BotaoVoltar.addActionListener(e -> {
            new Interface_Login(controleUsuario);
            dispose();
        });

        setVisible(true);
    }
}
