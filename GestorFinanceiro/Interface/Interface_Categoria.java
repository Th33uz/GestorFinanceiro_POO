package GestorFinanceiro.Interface;

import GestorFinanceiro.Controle.Controle_Categoria;
import GestorFinanceiro.Controle.Controle_Usuario;
import GestorFinanceiro.Estrutura.Estrutura_Categoria;
import GestorFinanceiro.Estrutura.Estrutura_Usuario;

import java.awt.Component;
import java.awt.LayoutManager;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Interface_Categoria extends JFrame {
    private Controle_Categoria controleCategoria;
    private Estrutura_Usuario usuario;
    private JTable TabelaCategorias;
    private DefaultTableModel ModeloTabela;
    private Controle_Usuario controleUsuario;

    public Interface_Categoria(Estrutura_Usuario usuario, Controle_Usuario controleUsuario) {
        this.usuario = usuario;
        this.controleUsuario = controleUsuario;
        this.controleCategoria = new Controle_Categoria();
        this.setTitle("Gerenciar Categorias");
        this.setSize(685, 380);
        this.setDefaultCloseOperation(2);
        this.setLocationRelativeTo((Component)null);
        this.setLayout((LayoutManager)null);
        JLabel RotuloTitulo = new JLabel("Categorias");
        RotuloTitulo.setBounds(300, 20, 150, 25);
        this.add(RotuloTitulo);
        this.ModeloTabela = new DefaultTableModel(new Object[]{"ID", "Nome", "Data de Criação"}, 0);
        this.TabelaCategorias = new JTable(this.ModeloTabela);
        JScrollPane BarraRolagem = new JScrollPane(this.TabelaCategorias);
        BarraRolagem.setBounds(50, 60, 570, 200);
        this.add(BarraRolagem);
        this.atualizarTabela();
        JButton BotaoAdicionar = new JButton("Adicionar");
        BotaoAdicionar.setBounds(50, 280, 120, 25);
        this.add(BotaoAdicionar);
        JButton BotaoEditar = new JButton("Editar");
        BotaoEditar.setBounds(200, 280, 120, 25);
        this.add(BotaoEditar);
        JButton BotaoExcluir = new JButton("Excluir");
        BotaoExcluir.setBounds(350, 280, 120, 25);
        this.add(BotaoExcluir);
        JButton BotaoVoltar = new JButton("Voltar");
        BotaoVoltar.setBounds(500, 280, 120, 25);
        this.add(BotaoVoltar);
        BotaoAdicionar.addActionListener((e) -> {
            String nomeCategoria = JOptionPane.showInputDialog("Digite o Nome da nova Categoria:");
            if (nomeCategoria != null && !nomeCategoria.isEmpty()) {
                int novoId = usuario.getCategorias().size() + 1;
                this.controleCategoria.adicionarCategoria(usuario, novoId, nomeCategoria);
                JOptionPane.showMessageDialog((Component)null, "Categoria Adicionada com Sucesso!");
                this.atualizarTabela();
            } else {
                JOptionPane.showMessageDialog((Component)null, "Nenhum Nome foi Digitado, a Categoria NÃO pode ficar sem um Nome!");
            }

        });
        BotaoEditar.addActionListener((e) -> {
            int linhaSelecionada = this.TabelaCategorias.getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog((Component)null, "Selecione uma Categoria para Editar");
            } else {
                int idCategoria = Integer.parseInt(this.ModeloTabela.getValueAt(linhaSelecionada, 0).toString());
                String nomeAtual = this.ModeloTabela.getValueAt(linhaSelecionada, 1).toString();
                String novoNome = JOptionPane.showInputDialog("Novo nome da Categoria:", nomeAtual);
                if (novoNome != null && !novoNome.isEmpty()) {
                    this.controleCategoria.editarCategoria(usuario, idCategoria, novoNome);
                    JOptionPane.showMessageDialog((Component)null, "Categoria editada com Sucesso!");
                    this.atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog((Component)null, "Nenhum Nome foi Digitado, a Categoria NÃO pode ficar sem um Nome!");
                }

            }
        });
        BotaoExcluir.addActionListener((e) -> {
            int linhaSelecionada = this.TabelaCategorias.getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog((Component)null, "Selecione uma Categoria para Excluir.");
            } else {
                int idCategoria = Integer.parseInt(this.ModeloTabela.getValueAt(linhaSelecionada, 0).toString());
                this.controleCategoria.excluirCategoria(usuario, idCategoria);
                JOptionPane.showMessageDialog((Component)null, "Categoria excluída com Sucesso!");
                this.atualizarTabela();
            }
        });
        BotaoVoltar.addActionListener(e -> {
            new Interface_Principal(usuario, controleUsuario);
            dispose();
        });
        this.setVisible(true);
    }

    private void atualizarTabela() {
        this.ModeloTabela.setRowCount(0);
        List<Estrutura_Categoria> categorias = this.usuario.getCategorias();
        DateTimeFormatter Formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for(Estrutura_Categoria categoria : categorias) {
            this.ModeloTabela.addRow(new Object[]{categoria.getId(), categoria.getNome(), categoria.getDataCriacao().format(Formatador)});
        }

    }
}
