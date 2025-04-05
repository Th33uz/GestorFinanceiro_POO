//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package GestorFinanceiro;

import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Interface_Historico extends JFrame {
    private Estrutura_Usuario usuario;
    private Controle_Usuario controleUsuario;
    private JTable TabelaCategorias;
    private DefaultTableModel ModeloTabela;
    private JLabel RotuloSaldoTotal;

    public Interface_Historico(Estrutura_Usuario usuario, Controle_Usuario controleUsuario) {
        this.usuario = usuario;
        this.controleUsuario = controleUsuario;
        this.setTitle("Histórico Geral");
        this.setSize(800, 465);
        this.setDefaultCloseOperation(2);
        this.setLocationRelativeTo((Component)null);
        this.setLayout((LayoutManager)null);
        this.ModeloTabela = new DefaultTableModel(new Object[]{"Categoria", "Receitas (QTD)", "Despesas (QTD)", "Total Receita (R$)", "Total Despesa (R$)"}, 0);
        this.TabelaCategorias = new JTable(this.ModeloTabela);
        JScrollPane BarraRolagem = new JScrollPane(this.TabelaCategorias);
        BarraRolagem.setBounds(50, 50, 700, 300);
        this.add(BarraRolagem);
        this.RotuloSaldoTotal = new JLabel("Saldo TOTAL: R$ 0.00");
        this.RotuloSaldoTotal.setBounds(50, 370, 700, 25);
        this.add(this.RotuloSaldoTotal);
        JButton BotaoVoltar = new JButton("Voltar");
        BotaoVoltar.setBounds(600, 370, 150, 25);
        this.add(BotaoVoltar);

        this.atualizarTabela();
        this.setVisible(true);
    }

    private void atualizarTabela() {
        this.ModeloTabela.setRowCount(0);
        double saldoTotal = (double)0.0F;

        for(Estrutura_Categoria categoria : this.usuario.getCategorias()) {
            double totalReceita = this.usuario.getTransacoes().stream().filter((t) -> t.getIdCategoria() == categoria.getId() && t.getTipo().equalsIgnoreCase("Receita")).mapToDouble((t) -> t.getValor()).sum();
            double totalDespesa = this.usuario.getTransacoes().stream().filter((t) -> t.getIdCategoria() == categoria.getId() && t.getTipo().equalsIgnoreCase("Despesa")).mapToDouble((t) -> t.getValor()).sum();
            saldoTotal += totalReceita - totalDespesa;
            this.ModeloTabela.addRow(new Object[]{categoria.getNome(), this.usuario.getTransacoes().stream().filter((t) -> t.getIdCategoria() == categoria.getId() && t.getTipo().equalsIgnoreCase("Receita")).count(), this.usuario.getTransacoes().stream().filter((t) -> t.getIdCategoria() == categoria.getId() && t.getTipo().equalsIgnoreCase("Despesa")).count(), totalReceita, totalDespesa});
        }

        this.RotuloSaldoTotal.setText("Saldo TOTAL: R$ " + saldoTotal);
    }
}
