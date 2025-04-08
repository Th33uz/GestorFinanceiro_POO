package GestorFinanceiro.Interface;

import GestorFinanceiro.Estrutura.Estrutura_Transacao;
import GestorFinanceiro.Estrutura.Estrutura_Usuario;
import GestorFinanceiro.Controle.Controle_Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class Interface_Resumo extends JFrame{
    private Estrutura_Usuario usuario;
    private Controle_Usuario controleUsuario;
    private JTable TabelaTransacoes;
    private DefaultTableModel ModeloTabela;
    private JLabel RotuloTotalDespesas, RotuloTotalReceitas;
    private JComboBox<String> CaixaSelecaoCategoria, CaixaSelecaoTipo;
    private JTextField CaixaTextoDataInicio, CaixaTextoDataFim;

    public Interface_Resumo(Estrutura_Usuario usuario, Controle_Usuario controleUsuario){
        this.usuario = usuario;
        this.controleUsuario = controleUsuario;

        setTitle("Resumo de Transações");
        setSize(965, 510);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        ModeloTabela = new DefaultTableModel(new Object[]{"Data", "Descrição", "Categoria", "Tipo", "Valor (R$)"}, 0);
        TabelaTransacoes = new JTable(ModeloTabela);
        JScrollPane BarraRolagem = new JScrollPane(TabelaTransacoes);
        BarraRolagem.setBounds(50, 100, 870, 300);
        add(BarraRolagem);

        RotuloTotalDespesas = new JLabel("Total Despesas: R$ 0.00");
        RotuloTotalDespesas.setBounds(50, 420, 200, 25);
        add(RotuloTotalDespesas);

        RotuloTotalReceitas = new JLabel("Total Receitas: R$ 0.00");
        RotuloTotalReceitas.setBounds(300, 420, 200, 25);
        add(RotuloTotalReceitas);

        JLabel RotuloFiltroCategoria = new JLabel("Categoria:");
        RotuloFiltroCategoria.setBounds(50, 20, 100, 25);
        add(RotuloFiltroCategoria);

        CaixaSelecaoCategoria = new JComboBox<>(usuario.getCategorias().stream()
                .map(c -> c.getNome())
                .toArray(String[]::new));
        CaixaSelecaoCategoria.insertItemAt("Todas", 0);
        CaixaSelecaoCategoria.setSelectedIndex(0);
        CaixaSelecaoCategoria.setBounds(120, 20, 150, 25);
        add(CaixaSelecaoCategoria);

        JLabel RotuloFiltroTipo = new JLabel("Tipo:");
        RotuloFiltroTipo.setBounds(300, 20, 50, 25);
        add(RotuloFiltroTipo);

        CaixaSelecaoTipo = new JComboBox<>(new String[]{"Todos", "Receita", "Despesa"});
        CaixaSelecaoTipo.setBounds(350, 20, 150, 25);
        add(CaixaSelecaoTipo);

        JLabel RotuloFiltroDataInicio = new JLabel("Data Início:");
        RotuloFiltroDataInicio.setBounds(540, 20, 80, 25);
        add(RotuloFiltroDataInicio);

        CaixaTextoDataInicio = new JTextField();
        CaixaTextoDataInicio.setBounds(620, 20, 110, 25);
        add(CaixaTextoDataInicio);

        JLabel RotuloFiltroDataFim = new JLabel("Data Fim:");
        RotuloFiltroDataFim.setBounds(740, 20, 80, 25);
        add(RotuloFiltroDataFim);

        CaixaTextoDataFim = new JTextField();
        CaixaTextoDataFim.setBounds(810, 20, 110, 25);
        add(CaixaTextoDataFim);

        JButton BotaoFiltrar = new JButton("Filtrar");
        BotaoFiltrar.setBounds(770, 60, 150, 25);
        add(BotaoFiltrar);

        BotaoFiltrar.addActionListener(e -> aplicarFiltros());

        JButton BotaoVoltar = new JButton("Voltar");
        BotaoVoltar.setBounds(770, 420, 150, 25);
        add(BotaoVoltar);

        BotaoVoltar.addActionListener(e -> {
            new Interface_Principal(usuario, controleUsuario);
            dispose();
        });

        atualizarTabela(usuario.getTransacoes());
        setVisible(true);
    }

    private void atualizarTabela(List<Estrutura_Transacao> transacoes){
        ModeloTabela.setRowCount(0);
        double totalDespesas = 0, totalReceitas = 0;

        for(Estrutura_Transacao transacao : transacoes){
            ModeloTabela.addRow(new Object[]{
                    transacao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    transacao.getDescricao(),
                    usuario.getCategorias().stream()
                            .filter(c -> c.getId() == transacao.getIdCategoria())
                            .map(c -> c.getNome())
                            .findFirst()
                            .orElse("N/A"),
                    transacao.getTipo(),
                    transacao.getValor()
            });

            if(transacao.getTipo().equalsIgnoreCase("Despesa")){
                totalDespesas += transacao.getValor();
            }

            else{
                totalReceitas += transacao.getValor();
            }
        }

        RotuloTotalDespesas.setText("Total Despesas: R$ " + totalDespesas);
        RotuloTotalReceitas.setText("Total Receitas: R$ " + totalReceitas);
    }

    private void aplicarFiltros(){

        String categoriaSelecionada = (String) CaixaSelecaoCategoria.getSelectedItem();
        String tipoSelecionado = (String) CaixaSelecaoTipo.getSelectedItem();
        String dataInicioTexto = CaixaTextoDataInicio.getText();
        String dataFimTexto = CaixaTextoDataFim.getText();

        LocalDate dataInicio = dataInicioTexto.isEmpty() ? null : LocalDate.parse(dataInicioTexto, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate dataFim = dataFimTexto.isEmpty() ? null : LocalDate.parse(dataFimTexto, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        List<Estrutura_Transacao> transacoesFiltradas = usuario.getTransacoes().stream()
                .filter(t -> (categoriaSelecionada.equals("Todas") ||
                        usuario.getCategorias().stream()
                                .filter(c -> c.getId() == t.getIdCategoria())
                                .map(c -> c.getNome())
                                .findFirst()
                                .orElse("")
                                .equals(categoriaSelecionada)))
                .filter(t -> (tipoSelecionado.equals("Todos") || t.getTipo().equalsIgnoreCase(tipoSelecionado)))
                .filter(t -> (dataInicio == null || !t.getData().isBefore(dataInicio)))
                .filter(t -> (dataFim == null || !t.getData().isAfter(dataFim)))
                .collect(Collectors.toList());

        atualizarTabela(transacoesFiltradas);
    }
}
