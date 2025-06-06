package Financas.Interface;

import Financas.Controle.Interface_Controle_Categoria;
import Financas.Controle.Interface_Controle_Transacao;
import Financas.Estrutura.Estrutura_Transacao;
import Financas.Estrutura.Estrutura_Usuario;
import Financas.Estrutura.Estrutura_Categoria;
import Financas.Util.FormatadorData;
import Financas.Util.GerenciadorFecharFrame;
import javax.swing.text.AbstractDocument;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.List;

public class Interface_Transacao extends JFrame{

    private int idUsuario;
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private Interface_Controle_Transacao controleTransacao;
    private List<Estrutura_Transacao> transacoes;
    private List<Estrutura_Categoria> categoriasUsuario;

    public Interface_Transacao(int idUsuario, Interface_Controle_Transacao controleTransacao, Interface_Controle_Categoria controleCategoria){
        this.idUsuario = idUsuario;
        this.controleTransacao = controleTransacao;
        this.categoriasUsuario = controleCategoria.obterCategoriasPorUsuario(idUsuario);

        setTitle("Gerenciar Transações");
        setSize(715, 450);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel rotuloTitulo = new JLabel("Transações");
        rotuloTitulo.setBounds(310, 20, 150, 25);
        add(rotuloTitulo);

        modeloTabela = new DefaultTableModel(new Object[]{"Descrição", "Valor (R$)", "Data", "Tipo", "Categoria"}, 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        tabela = new JTable(modeloTabela);
        JScrollPane barraRolagem = new JScrollPane(tabela);
        barraRolagem.setBounds(50, 60, 600, 250);
        add(barraRolagem);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(530, 350, 120, 25);
        add(btnAdicionar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(210, 350, 120, 25);
        add(btnEditar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(370, 350, 120, 25);
        add(btnExcluir);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(50, 350, 120, 25);
        add(btnVoltar);

        carregarTransacoes();

        btnAdicionar.addActionListener(e -> cadastrarTransacao());
        btnEditar.addActionListener(e -> alterarTransacao());
        btnExcluir.addActionListener(e -> excluirTransacao());
        btnVoltar.addActionListener(e -> voltarParaPrincipal());

        GerenciadorFecharFrame.adicionarAcaoAoFechar(this, () ->
                new Interface_Principal(idUsuario).setVisible(true)
        );

        setVisible(true);
    }

    private void carregarTransacoes(){
        modeloTabela.setRowCount(0);
        transacoes = controleTransacao.listarTransacoesPorUsuario(idUsuario);

        if(transacoes != null){
            for(Estrutura_Transacao t : transacoes){
                String nomeCategoria = "";
                if(t.getCategoria() != null){
                    int idCat = t.getCategoria().getId();
                    for(Estrutura_Categoria cat : categoriasUsuario){
                        if(cat.getId() == idCat){
                            nomeCategoria = cat.getNomeCategoria();
                            break;
                        }
                    }
                }
                modeloTabela.addRow(new Object[]{
                        t.getDescricao(),
                        t.getValor(),
                        t.getDataTransacao(),
                        t.getTipo(),
                        nomeCategoria
                });
            }
        }
    }

    private void alterarTransacao(){
        int linha = tabela.getSelectedRow();
        if(linha < 0){
            JOptionPane.showMessageDialog(this, "Selecione uma Transação para Alterar");
            return;
        }
        Estrutura_Transacao transacao = transacoes.get(linha);

        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Receita", "Despesa"});
        comboTipo.setSelectedItem(transacao.getTipo());

        JTextField campoDescricao = new JTextField(transacao.getDescricao());
        JTextField campoValor = new JTextField(String.valueOf(transacao.getValor()));
        JFormattedTextField campoData;
        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
            campoData = new JFormattedTextField(mascaraData);
            campoData.setText(transacao.getDataTransacao());
        } catch (ParseException ex) {
            campoValor = new JTextField(String.valueOf(transacao.getValor()));
            campoData = new JFormattedTextField();
            campoData.setText(transacao.getDataTransacao());
        }

        campoValor.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void replaceVirgulaPorPonto(javax.swing.text.Document doc) {
                try {
                    String text = doc.getText(0, doc.getLength());
                    String novo = text.replace(',', '.');
                    if (!text.equals(novo)) {
                        javax.swing.SwingUtilities.invokeLater(() -> {
                            try {
                                doc.remove(0, doc.getLength());
                                doc.insertString(0, novo, null);
                            } catch (Exception ignored) {}
                        });
                    }
                } catch (Exception ignored) {}
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { replaceVirgulaPorPonto(e.getDocument()); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { replaceVirgulaPorPonto(e.getDocument()); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { replaceVirgulaPorPonto(e.getDocument()); }
        });

        JComboBox<String> comboCategoria = new JComboBox<>();
        int indiceSelecionado = -1;
        for(int i = 0; i < categoriasUsuario.size(); i++){
            Estrutura_Categoria cat = categoriasUsuario.get(i);
            comboCategoria.addItem(cat.getNomeCategoria());
            if(transacao.getCategoria() != null && cat.getId() == transacao.getCategoria().getId()){
                indiceSelecionado = i;
            }
        }
        comboCategoria.setSelectedIndex(indiceSelecionado);

        Object[] mensagem = {
                "Tipo:", comboTipo,
                "Descrição:", campoDescricao,
                "Valor:", campoValor,
                "Data (Dia/Mes/Ano):", campoData,
                "Categoria:", comboCategoria
        };

        int result = JOptionPane.showConfirmDialog(this, mensagem, "Alterar Transação", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            String tipo = (String) comboTipo.getSelectedItem();
            String descricao = campoDescricao.getText().trim();
            String valorStr = campoValor.getText().trim();
            String data = campoData.getText().trim();
            int indiceCat = comboCategoria.getSelectedIndex();

            if(tipo != null && !descricao.isEmpty() && !valorStr.isEmpty() && !data.isEmpty() && indiceCat >= 0){
                if(!FormatadorData.validarData(data)){
                    JOptionPane.showMessageDialog(this, "Data Inválida! Use o Formato Dia/Mes/Ano");
                    return;
                }
                try{
                    double valor = Double.parseDouble(valorStr);
                    Estrutura_Categoria categoria = categoriasUsuario.get(indiceCat);

                    Estrutura_Transacao transacaoNova = new Estrutura_Transacao();
                    transacaoNova.setId(transacao.getId());
                    transacaoNova.setTipo(tipo);
                    transacaoNova.setDescricao(descricao);
                    transacaoNova.setValor(valor);
                    transacaoNova.setDataTransacao(data);
                    transacaoNova.setCategoria(categoria);

                    Estrutura_Usuario usuario = new Estrutura_Usuario();
                    usuario.setId(idUsuario);
                    transacaoNova.setUsuario(usuario);

                    boolean sucesso = controleTransacao.editarTransacao(transacaoNova, transacao);
                    if(sucesso){
                        JOptionPane.showMessageDialog(this, "Transação Alterada com Sucesso!");
                        carregarTransacoes();
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Erro ao Alterar Transação");
                    }
                }
                catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(this, "Valor Inválido");
                }
                finally{
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Preencha Todos os campos Corretamente!");
            }
        }
    }

    private void excluirTransacao(){
        int linha = tabela.getSelectedRow();
        if(linha < 0){
            JOptionPane.showMessageDialog(this, "Selecione uma Transação para Excluir");
            return;
        }
        Estrutura_Transacao transacao = transacoes.get(linha);
        int confirma = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja EXCLUIR a Transação?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_OPTION){
            boolean sucesso = controleTransacao.excluirTransacao(transacao);
            if(sucesso){
                JOptionPane.showMessageDialog(this, "Transação Excluída com Sucesso!");
                carregarTransacoes();
            }
            else{
                JOptionPane.showMessageDialog(this, "Erro ao Excluir Transação");
            }
        }
    }

    private void cadastrarTransacao(){
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Receita", "Despesa"});
        JTextField campoDescricao = new JTextField();
        JTextField campoValor = new JTextField();
        JFormattedTextField campoData;
        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
            campoData = new JFormattedTextField(mascaraData);
        } catch (ParseException ex) {
            campoValor = new JTextField();
            campoData = new JFormattedTextField();
        }

        campoValor.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void replaceVirgulaPorPonto(javax.swing.text.Document doc) {
                try {
                    String text = doc.getText(0, doc.getLength());
                    String novo = text.replace(',', '.');
                    if (!text.equals(novo)) {
                        javax.swing.SwingUtilities.invokeLater(() -> {
                            try {
                                doc.remove(0, doc.getLength());
                                doc.insertString(0, novo, null);
                            } catch (Exception ignored) {}
                        });
                    }
                } catch (Exception ignored) {}
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { replaceVirgulaPorPonto(e.getDocument()); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { replaceVirgulaPorPonto(e.getDocument()); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { replaceVirgulaPorPonto(e.getDocument()); }
        });

        JComboBox<String> comboCategoria = new JComboBox<>();
        for(Estrutura_Categoria cat : categoriasUsuario){
            comboCategoria.addItem(cat.getNomeCategoria());
        }
        comboCategoria.setSelectedIndex(-1);

        Object[] mensagem = {
                "Tipo:", comboTipo,
                "Descrição:", campoDescricao,
                "Valor:", campoValor,
                "Data (Dia/Mes/Ano):", campoData,
                "Categoria:", comboCategoria
        };

        int result = JOptionPane.showConfirmDialog(this, mensagem, "Cadastrar Transação", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            String tipo = (String) comboTipo.getSelectedItem();
            String descricao = campoDescricao.getText().trim();
            String valorStr = campoValor.getText().trim();
            String data = campoData.getText().trim();
            int indiceCat = comboCategoria.getSelectedIndex();

            if(tipo != null && !descricao.isEmpty() && !valorStr.isEmpty() && !data.isEmpty() && indiceCat >= 0){
                if(!FormatadorData.validarData(data)){
                    JOptionPane.showMessageDialog(this, "Data Inválida! Use o Formato Dia/Mes/Ano");
                    return;
                }
                try{
                    double valor = Double.parseDouble(valorStr);
                    Estrutura_Categoria categoria = categoriasUsuario.get(indiceCat);

                    Estrutura_Transacao novaTransacao = new Estrutura_Transacao();
                    novaTransacao.setTipo(tipo);
                    novaTransacao.setDescricao(descricao);
                    novaTransacao.setValor(valor);
                    novaTransacao.setDataTransacao(data);
                    novaTransacao.setCategoria(categoria);

                    Estrutura_Usuario usuario = new Estrutura_Usuario();
                    usuario.setId(idUsuario);
                    novaTransacao.setUsuario(usuario);

                    boolean sucesso = controleTransacao.cadastrarTransacao(novaTransacao);
                    if(sucesso){
                        JOptionPane.showMessageDialog(this, "Transação Cadastrada com Sucesso!");
                        carregarTransacoes();
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Erro ao Cadastrar Transação");
                    }
                }
                catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(this, "Valor Inválido");
                }
                finally{
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Preencha Todos os campos Corretamente!");
            }
        }
    }

    private void voltarParaPrincipal(){
        new Interface_Principal(idUsuario).setVisible(true);
        dispose();
    }
}
