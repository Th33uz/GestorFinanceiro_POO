package Financas.Interface;

import Financas.Controle.Interface_Controle_Historico;
import Financas.Estrutura.Estrutura_Categoria;
import Financas.Estrutura.Estrutura_Transacao;
import Financas.Estrutura.Estrutura_Usuario;
import Financas.Util.GerenciadorFecharFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Interface_Historico extends JFrame{

    private int idUsuario;
    private Interface_Controle_Historico controleHistorico;

    private JTable tabelaHistorico;
    private DefaultTableModel modeloTabela;
    private JLabel lblSaldo;
    private JButton btnVoltar;

    public Interface_Historico(int idUsuario, Interface_Controle_Historico controleHistorico){
        this.idUsuario = idUsuario;
        this.controleHistorico = controleHistorico;

        setTitle("Histórico de Categorias");
        setSize(800,465);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        GerenciadorFecharFrame.adicionarAcaoAoFechar(this, () ->
                new Interface_Principal(idUsuario).setVisible(true)
        );

        inicializarComponentes();
        carregarHistorico();

        setVisible(true);
    }

    private void inicializarComponentes(){
        modeloTabela = new DefaultTableModel(
                new Object[]{"Categoria","Receitas (QTD)","Despesas (QTD)","Total Receita (R$)","Total Despesa (R$)"}, 0
        );

        tabelaHistorico = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaHistorico);
        scrollPane.setBounds(50,50,700,300);
        add(scrollPane);

        lblSaldo = new JLabel("Saldo Total: R$ 0.00");
        lblSaldo.setBounds(50,370,500,25);
        lblSaldo.setFont(new Font("Arial", Font.BOLD, 15));
        lblSaldo.setOpaque(true);
        lblSaldo.setForeground(new Color(0,128,0));
        lblSaldo.setHorizontalAlignment(SwingConstants.LEFT);
        add(lblSaldo);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(580,370,150,25);
        add(btnVoltar);

        btnVoltar.addActionListener(e -> voltar());
    }

    private void carregarHistorico(){
        modeloTabela.setRowCount(0);

        List<Estrutura_Categoria> categorias = controleHistorico.obterCategorias(idUsuario);
        List<Estrutura_Transacao> transacoes = controleHistorico.obterTransacoes(idUsuario);
        Estrutura_Usuario usuario = controleHistorico.obterUsuario(idUsuario);

        double saldoTotal = 0.0;

        for(Estrutura_Categoria cat : categorias){
            int qtdReceitas = 0;
            int qtdDespesas = 0;
            double totalReceita = 0.0;
            double totalDespesa = 0.0;

            for(Estrutura_Transacao trans : transacoes){
                if(trans.getCategoria() != null && trans.getCategoria().getId() == cat.getId()){
                    if("Receita".equalsIgnoreCase(trans.getTipo())){
                        qtdReceitas++;
                        totalReceita += trans.getValor();
                    }
                    else if("Despesa".equalsIgnoreCase(trans.getTipo())){
                        qtdDespesas++;
                        totalDespesa += trans.getValor();
                    }
                }
            }

            saldoTotal += totalReceita - totalDespesa;

            modeloTabela.addRow(new Object[]{
                    cat.getNomeCategoria(),
                    qtdReceitas,
                    qtdDespesas,
                    String.format("R$ %.2f", totalReceita),
                    String.format("R$ %.2f", totalDespesa)
            });
        }

        lblSaldo.setText("Saldo Total: R$ " + String.format("%.2f", saldoTotal));
        if(saldoTotal >= 0){
            lblSaldo.setForeground(new Color(0,128,0));
        }
        else{
            lblSaldo.setForeground(new Color(153,0,0));
        }
    }

    private void voltar(){
        new Interface_Principal(idUsuario).setVisible(true);
        dispose();
    }
}
