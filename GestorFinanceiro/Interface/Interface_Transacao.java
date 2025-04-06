package GestorFinanceiro.Interface;

import GestorFinanceiro.Controle.Controle_Transacao;
import GestorFinanceiro.Controle.Controle_Usuario;
import GestorFinanceiro.Estrutura.Estrutura_Categoria;
import GestorFinanceiro.Estrutura.Estrutura_Transacao;
import GestorFinanceiro.Estrutura.Estrutura_Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Interface_Transacao extends JFrame{
    private Controle_Transacao controleTransacao;
    private Estrutura_Usuario usuario;
    private JTable TabelaTransacoes;
    private DefaultTableModel ModeloTabela;
    private Controle_Usuario controleUsuario;

    public Interface_Transacao(Estrutura_Usuario usuario, Controle_Usuario controleUsuario){
        this.usuario = usuario;
        this.controleUsuario = controleUsuario;
        this.controleTransacao = new Controle_Transacao();

        setTitle("Gerenciar Transações");
        setSize(715, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel RotuloTitulo = new JLabel("Transações");
        RotuloTitulo.setBounds(310, 20, 150, 25);
        add(RotuloTitulo);

        ModeloTabela = new DefaultTableModel(new Object[]{"Descrição", "Valor (R$)", "Data", "Tipo", "Categoria"}, 0);
        TabelaTransacoes = new JTable(ModeloTabela);
        JScrollPane BarraRolagem = new JScrollPane(TabelaTransacoes);
        BarraRolagem.setBounds(50, 60, 600, 250);
        add(BarraRolagem);

        atualizarTabela();

        JButton BotaoAdicionar = new JButton("Adicionar");
        BotaoAdicionar.setBounds(50, 350, 120, 25);
        add(BotaoAdicionar);

        JButton BotaoEditar = new JButton("Editar");
        BotaoEditar.setBounds(210, 350, 120, 25);
        add(BotaoEditar);

        JButton BotaoExcluir = new JButton("Excluir");
        BotaoExcluir.setBounds(370, 350, 120, 25);
        add(BotaoExcluir);

        JButton BotaoVoltar = new JButton("Voltar");
        BotaoVoltar.setBounds(530, 350, 120, 25);
        add(BotaoVoltar);

        BotaoAdicionar.addActionListener(e -> {
            JTextField CampoDescricao = new JTextField();
            JTextField CampoValor = new JTextField();
            JTextField CampoData = new JTextField(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            JComboBox<String> CaixaSelecaoTipo = new JComboBox<>(new String[]{"Receita", "Despesa"});
            JComboBox<String> CaixaSelecaoCategoria = new JComboBox<>(usuario.getCategorias().stream()
                    .map(Estrutura_Categoria::getNome)
                    .toArray(String[]::new));

            Object[] Mensagem = {
                    "Descrição:", CampoDescricao,
                    "Valor:", CampoValor,
                    "Data (Dia/Mês/Ano):", CampoData,
                    "Tipo:", CaixaSelecaoTipo,
                    "Categoria:", CaixaSelecaoCategoria
            };

            int Opcao = JOptionPane.showConfirmDialog(null, Mensagem, "Nova Transação", JOptionPane.OK_CANCEL_OPTION);
            if(Opcao == JOptionPane.CANCEL_OPTION){
                JOptionPane.showMessageDialog(null, "Transação Cancelada!");
            }

            else{
                try{
                    String Descricao = CampoDescricao.getText();
                    if(Descricao.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Preencha o campo Descrição");
                        return;
                    }

                    double Valor = Double.parseDouble(CampoValor.getText());
                    LocalDate Data = LocalDate.parse(CampoData.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    String Tipo = (String) CaixaSelecaoTipo.getSelectedItem();
                    String CategoriaNome = (String) CaixaSelecaoCategoria.getSelectedItem();

                    int IdCategoria = usuario.getCategorias().stream()
                            .filter(cat -> cat.getNome().equals(CategoriaNome))
                            .findFirst()
                            .map(Estrutura_Categoria::getId)
                            .orElseThrow();

                    controleTransacao.adicionarTransacao(usuario, Descricao, Valor, Data, Tipo, IdCategoria);
                    JOptionPane.showMessageDialog(null, "Transação adicionada com Sucesso!");
                    atualizarTabela();
                }

                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Erro ao Adicionar Transação. Verifique os Dados Informados!!!");
                }
            }
        });

        BotaoEditar.addActionListener(e -> {
            int LinhaSelecionada = TabelaTransacoes.getSelectedRow();
            if(LinhaSelecionada == -1){
                JOptionPane.showMessageDialog(null, "Selecione uma Transação para Editar.");
                return;
            }

            String DescricaoAtual = ModeloTabela.getValueAt(LinhaSelecionada, 0).toString();
            Estrutura_Transacao TransacaoAtual = usuario.getTransacoes().stream()
                    .filter(t -> t.getDescricao().equals(DescricaoAtual))
                    .findFirst()
                    .orElseThrow();

            JTextField CampoDescricao = new JTextField(TransacaoAtual.getDescricao());
            JTextField CampoValor = new JTextField(String.valueOf(TransacaoAtual.getValor()));
            JTextField CampoData = new JTextField(TransacaoAtual.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            JComboBox<String> CaixaSelecaoTipo = new JComboBox<>(new String[]{"Receita", "Despesa"});
            CaixaSelecaoTipo.setSelectedItem(TransacaoAtual.getTipo());

            JComboBox<String> CaixaSelecaoCategoria = new JComboBox<>(usuario.getCategorias().stream()
                    .map(Estrutura_Categoria::getNome)
                    .toArray(String[]::new));
            CaixaSelecaoCategoria.setSelectedItem(usuario.getCategorias().stream()
                    .filter(cat -> cat.getId() == TransacaoAtual.getIdCategoria())
                    .map(Estrutura_Categoria::getNome)
                    .findFirst()
                    .orElse(""));

            Object[] Mensagem = {
                    "Descrição:", CampoDescricao,
                    "Valor:", CampoValor,
                    "Data (Dia/Mês/Ano):", CampoData,
                    "Tipo:", CaixaSelecaoTipo,
                    "Categoria:", CaixaSelecaoCategoria
            };

            int Opcao = JOptionPane.showConfirmDialog(null, Mensagem, "Editar Transação", JOptionPane.OK_CANCEL_OPTION);
            if(Opcao == JOptionPane.CANCEL_OPTION){
                JOptionPane.showMessageDialog(null, "Edição Cancelada!");
            }

            else{
                try{
                    String NovaDescricao = CampoDescricao.getText();
                    double NovoValor = Double.parseDouble(CampoValor.getText());
                    LocalDate NovaData = LocalDate.parse(CampoData.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    String NovoTipo = (String) CaixaSelecaoTipo.getSelectedItem();
                    String NovaCategoriaNome = (String) CaixaSelecaoCategoria.getSelectedItem();

                    int NovoIdCategoria = usuario.getCategorias().stream()
                            .filter(cat -> cat.getNome().equals(NovaCategoriaNome))
                            .findFirst()
                            .map(Estrutura_Categoria::getId)
                            .orElseThrow();

                    controleTransacao.editarTransacao(usuario, DescricaoAtual, NovaDescricao, NovoValor, NovaData, NovoTipo, NovoIdCategoria);
                    JOptionPane.showMessageDialog(null, "Transação editada com Sucesso!");
                    atualizarTabela();
                }

                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Erro ao Editar Transação. Verifique os Dados Informados!!!");
                }
            }
        });

        BotaoExcluir.addActionListener(e -> {
            int LinhaSelecionada = TabelaTransacoes.getSelectedRow();
            if(LinhaSelecionada == -1){
                JOptionPane.showMessageDialog(null, "Selecione uma Transação para Excluir.");
                return;
            }

            String Descricao = ModeloTabela.getValueAt(LinhaSelecionada, 0).toString();
            controleTransacao.excluirTransacao(usuario, Descricao);
            JOptionPane.showMessageDialog(null, "Transação excluída com Sucesso!");
            atualizarTabela();
        });

        BotaoVoltar.addActionListener(e -> {
            new Interface_Principal(usuario, controleUsuario);
            dispose();
        });

        setVisible(true);
    }

    private void atualizarTabela(){
        ModeloTabela.setRowCount(0);
        for(Estrutura_Transacao Transacao : usuario.getTransacoes()){
            ModeloTabela.addRow(new Object[]{
                    Transacao.getDescricao(),
                    Transacao.getValor(),
                    Transacao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    Transacao.getTipo(),
                    usuario.getCategorias().stream()
                            .filter(cat -> cat.getId() == Transacao.getIdCategoria())
                            .map(Estrutura_Categoria::getNome)
                            .findFirst()
                            .orElse("N/A")
            });
        }
    }
}

