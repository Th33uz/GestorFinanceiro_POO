package Financas.Interface;

import Financas.Controle.Interface_Controle_Categoria;
import Financas.Estrutura.Estrutura_Categoria;
import Financas.Util.FormatadorData;
import Financas.Util.GerenciadorFecharFrame;
import Financas.Controle.Controle_Transacao;
import Financas.Estrutura.Estrutura_Transacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.List;

public class Interface_Categoria extends JFrame{

    private int idUsuario;
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private Interface_Controle_Categoria controleCategoria;
    private List<Estrutura_Categoria> categorias;

    public Interface_Categoria(int idUsuario, Interface_Controle_Categoria controleCategoria){
        this.idUsuario = idUsuario;
        this.controleCategoria = controleCategoria;

        setTitle("Gerenciar Categorias");
        setSize(685,380);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        GerenciadorFecharFrame.adicionarAcaoAoFechar(this, () ->
                new Interface_Principal(idUsuario).setVisible(true)
        );


        JLabel rotuloTitulo = new JLabel("Categorias");
        rotuloTitulo.setBounds(300,20,150,25);
        add(rotuloTitulo);

        modeloTabela = new DefaultTableModel(new Object[]{"Nome","Data Criação"}, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane barraRolagem = new JScrollPane(tabela);
        barraRolagem.setBounds(50,60,570,200);
        add(barraRolagem);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(500,280,120,25);
        add(btnCadastrar);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(200,280,120,25);
        add(btnAlterar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(350,280,120,25);
        add(btnExcluir);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(50,280,120,25);
        add(btnVoltar);

        btnAlterar.addActionListener(e -> alterarCategoria());
        btnExcluir.addActionListener(e -> excluirCategoria());
        btnCadastrar.addActionListener(e -> cadastrarCategoria());
        btnVoltar.addActionListener(e -> voltarParaPrincipal());

        carregarCategorias();

        setVisible(true);

    }

    private void carregarCategorias(){
        modeloTabela.setRowCount(0);
        categorias = controleCategoria.obterCategoriasPorUsuario(idUsuario);

        for(Estrutura_Categoria cat : categorias){
            modeloTabela.addRow(new Object[]{
                    cat.getNomeCategoria(),
                    cat.getDataCriacaoCategoria()
            });
        }
    }

    private void alterarCategoria(){
        int linha = tabela.getSelectedRow();
        if(linha >= 0){
            Estrutura_Categoria cat = categorias.get(linha);

            JTextField campoNome = new JTextField(cat.getNomeCategoria());
            //JTextField campoData = new JTextField(cat.getDataCriacaoCategoria());

            JFormattedTextField campoData;
            try {
                MaskFormatter mascaraData = new MaskFormatter("##/##/####");
                mascaraData.setPlaceholderCharacter('_');
                campoData = new JFormattedTextField(mascaraData);
                campoData.setText(cat.getDataCriacaoCategoria());
            } catch (ParseException var28) {
                campoData = new JFormattedTextField();
                campoData.setText(cat.getDataCriacaoCategoria());
            }

            if(campoData.getText() == null || campoData.getText().trim().isEmpty()){
                campoData.setText(FormatadorData.obterDataAtualFormatada());
            }

            JPanel painel = new JPanel(new GridLayout(0,1));
            painel.add(new JLabel("Nome da Categoria:"));
            painel.add(campoNome);
            painel.add(new JLabel("Data de Criação (Dia/Mes/Ano):"));
            painel.add(campoData);


            int result = JOptionPane.showConfirmDialog(this, painel, "Alterar Categoria", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result == JOptionPane.OK_OPTION){
                String novoNome = campoNome.getText().trim();
                String novaData = campoData.getText().trim();

                boolean sucesso = controleCategoria.alterarCategoria(cat.getId(), novoNome, novaData);

                if(sucesso){
                    JOptionPane.showMessageDialog(this, "Categoria Atualizada com Sucesso!");
                    carregarCategorias();
                }
                else{
                    JOptionPane.showMessageDialog(this, "Erro ao Atualizar Categoria");
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Selecione uma Categoria para Alterar");
        }
    }

    private void excluirCategoria(){
        int linha = tabela.getSelectedRow();
        if(linha >= 0){
            Estrutura_Categoria cat = categorias.get(linha);
            // Verifica se há transações associadas
            Controle_Transacao controleTransacao = new Controle_Transacao();
            List<Estrutura_Transacao> transacoes = controleTransacao.listarTransacoesPorCategoria(cat.getId());
            if(transacoes != null && !transacoes.isEmpty()){
                JOptionPane.showMessageDialog(this, "Não é possível excluir a categoria, pois existem transações associadas a ela.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int confirma = JOptionPane.showConfirmDialog(this, "Tem Certeza que deseja EXCLUIR a Categoria?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if(confirma == JOptionPane.YES_OPTION){
                boolean sucesso = controleCategoria.excluirCategoria(cat.getId());

                if(sucesso){
                    JOptionPane.showMessageDialog(this, "Categoria Excluída com Sucesso!");
                    carregarCategorias();
                }
                else{
                    JOptionPane.showMessageDialog(this, "Erro ao Excluir Categoria");
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Selecione uma Categoria para Excluir");
        }
    }

    private void cadastrarCategoria(){
        JTextField campoNome = new JTextField();
        JTextField campoData = new JTextField(FormatadorData.obterDataAtualFormatada());

        JPanel painel = new JPanel(new GridLayout(0,1));
        painel.add(new JLabel("Nome da Categoria:"));
        painel.add(campoNome);
        painel.add(new JLabel("Data de Criação (Dia/Mes/Ano):"));
        painel.add(campoData);

        int result = JOptionPane.showConfirmDialog(this, painel, "Cadastrar Categoria", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(result == JOptionPane.OK_OPTION){
            String nome = campoNome.getText().trim();
            String dataCriacao = campoData.getText().trim();

            boolean sucesso = controleCategoria.cadastrarCategoria(idUsuario, nome, dataCriacao);

            if(sucesso){
                JOptionPane.showMessageDialog(this, "Categoria Cadastrada com Sucesso!");
                carregarCategorias();
            }
            else{
                JOptionPane.showMessageDialog(this, "Preencha Todos os Campos para Cadastrar!");
            }
        }
    }

    private void voltarParaPrincipal(){
        new Interface_Principal(idUsuario).setVisible(true);
        dispose();
    }
}
