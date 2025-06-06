package Financas.Interface;

import Financas.Estrutura.Estrutura_Categoria;
import Financas.Estrutura.Estrutura_Transacao;
import Financas.Controle.Interface_Controle_Resumo;
import Financas.Util.FormatadorData;
import Financas.Util.GerenciadorFecharFrame;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.List;

public class Interface_Resumo extends JFrame{

    private int idUsuario;
    private Interface_Controle_Resumo interfaceControleResumo;

    private JComboBox<Estrutura_Categoria> comboCategoria;
    private JComboBox<String> comboTipo;
    private JTextField campoDataInicio;
    private JTextField campoDataFim;
    private JTable tabelaTransacoes;
    private DefaultTableModel modeloTabela;
    private JLabel labelTotalReceita;
    private JLabel labelTotalDespesa;
    private JButton btnFiltrar;
    private JButton btnVoltar;

    public Interface_Resumo(int idUsuario, Interface_Controle_Resumo interfaceControleResumo){
        this.idUsuario = idUsuario;
        this.interfaceControleResumo = interfaceControleResumo;

        setTitle("Resumo de Transações");
        setSize(965,510);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        GerenciadorFecharFrame.adicionarAcaoAoFechar(this, () ->
                new Interface_Principal(idUsuario).setVisible(true)
        );

        inicializarComponentes();
        carregarCategorias();
        carregarTransacoes();

        setVisible(true);
    }

    private void inicializarComponentes(){
        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setBounds(50,20,100,25);
        add(lblCategoria);

        comboCategoria = new JComboBox<>();
        comboCategoria.setBounds(120,20,150,25);
        comboCategoria.setRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus){

                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if(value instanceof Estrutura_Categoria){
                    setText(((Estrutura_Categoria) value).getNomeCategoria());
                } else{
                    setText("Todos");
                }
                return this;
            }
        });
        comboCategoria.addItem(null);
        add(comboCategoria);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(300,20,50,25);
        add(lblTipo);

        comboTipo = new JComboBox<>(new String[]{"Todos", "Receita", "Despesa"});
        comboTipo.setBounds(350,20,150,25);
        add(comboTipo);

        JLabel lblDataInicio = new JLabel("Data Início:");
        lblDataInicio.setBounds(540,20,80,25);
        add(lblDataInicio);

        campoDataInicio = new JFormattedTextField();

        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
            campoDataInicio = new JFormattedTextField(mascaraData);
        } catch (ParseException ex) {
            campoDataInicio = new JFormattedTextField();
        }

        campoDataInicio.setBounds(620,20,110,25);
        add(campoDataInicio);

        JLabel lblDataFim = new JLabel("Data Fim:");
        lblDataFim.setBounds(740,20,80,25);
        add(lblDataFim);

        campoDataFim = new JFormattedTextField();;

        try {
            MaskFormatter MascaraData = new MaskFormatter("##/##/####");
            MascaraData.setPlaceholderCharacter('_');
            campoDataFim = new JFormattedTextField(MascaraData);
        } catch (ParseException ex) {
            campoDataFim = new JFormattedTextField();
        }

        campoDataFim.setBounds(810,20,110,25);
        add(campoDataFim);

        btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(770,60,150,25);
        add(btnFiltrar);

        modeloTabela = new DefaultTableModel(new String[]{"Descrição", "Valor (R$)", "Tipo", "Data", "Categoria"}, 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tabelaTransacoes = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaTransacoes);
        scrollPane.setBounds(50,100,870,300);
        add(scrollPane);

        labelTotalReceita = new JLabel("Total Receita: R$ 0,00");
        labelTotalReceita.setBounds(50,420,200,25);
        labelTotalReceita.setForeground(new Color(0,128,0));
        labelTotalReceita.setFont(labelTotalReceita.getFont().deriveFont(Font.BOLD, 13f));
        labelTotalReceita.setOpaque(true);
        labelTotalReceita.setHorizontalAlignment(SwingConstants.LEFT);
        add(labelTotalReceita);

        labelTotalDespesa = new JLabel("Total Despesa: R$ 0,00");
        labelTotalDespesa.setBounds(270,420,200,25);
        labelTotalDespesa.setForeground(new Color(153,0,0));
        labelTotalDespesa.setFont(labelTotalDespesa.getFont().deriveFont(Font.BOLD, 13f));
        labelTotalDespesa.setOpaque(true);
        labelTotalDespesa.setHorizontalAlignment(SwingConstants.LEFT);
        add(labelTotalDespesa);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(770,420,150,25);
        add(btnVoltar);

        btnFiltrar.addActionListener(e -> filtrarTransacoes());
        btnVoltar.addActionListener(e -> voltarParaPrincipal());
    }

    private void carregarCategorias(){
        List<Estrutura_Categoria> categorias = interfaceControleResumo.obterCategorias(idUsuario);

        if(categorias != null){
            for(Estrutura_Categoria categoria : categorias){
                comboCategoria.addItem(categoria);
            }
        }
    }

    private void carregarTransacoes(){
        List<Estrutura_Transacao> transacoes = interfaceControleResumo.obterTransacoes(idUsuario);
        preencherTabelaECalcularTotais(transacoes);
    }

    private void filtrarTransacoes(){
        String dataInicioStr = campoDataInicio.getText().trim();
        String dataFimStr = campoDataFim.getText().trim();

        if(!dataInicioStr.isEmpty() && !FormatadorData.validarData(dataInicioStr)){
            JOptionPane.showMessageDialog(this, "Data de Início Inválida! Use o Formato (Dia/Mes/Ano)",
                    "Erro de Data", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(!dataFimStr.isEmpty() && !FormatadorData.validarData(dataFimStr)){
            JOptionPane.showMessageDialog(this, "Data de Fim Inválida! Use o Formato (Dia/Mes/Ano)",
                    "Erro de Data", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Estrutura_Categoria categoriaSelecionada = (Estrutura_Categoria) comboCategoria.getSelectedItem();
        String tipoSelecionado = (String) comboTipo.getSelectedItem();

        List<Estrutura_Transacao> transacoes = interfaceControleResumo.obterTransacoes(idUsuario);
        List<Estrutura_Transacao> filtradas = interfaceControleResumo.filtrarTransacoes(transacoes, categoriaSelecionada, tipoSelecionado, dataInicioStr, dataFimStr);

        preencherTabelaECalcularTotais(filtradas);
    }

    private void preencherTabelaECalcularTotais(List<Estrutura_Transacao> transacoes){
        modeloTabela.setRowCount(0);

        double totalReceita = interfaceControleResumo.calcularTotal(transacoes, "Receita");
        double totalDespesa = interfaceControleResumo.calcularTotal(transacoes, "Despesa");

        for(Estrutura_Transacao t : transacoes){
            modeloTabela.addRow(new Object[]{
                    t.getDescricao(),
                    String.format("R$ %.2f", t.getValor()),
                    t.getTipo(),
                    t.getDataTransacao(),
                    t.getCategoria() != null ? t.getCategoria().getNomeCategoria() : ""
            });
        }

        labelTotalReceita.setText(String.format("Total Receita: R$ %.2f", totalReceita));
        labelTotalDespesa.setText(String.format("Total Despesa: R$ %.2f", totalDespesa));
    }

    private void voltarParaPrincipal(){
        new Interface_Principal(idUsuario).setVisible(true);
        dispose();
    }
}
