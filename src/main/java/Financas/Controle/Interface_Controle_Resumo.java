package Financas.Controle;

import Financas.Estrutura.Estrutura_Categoria;
import Financas.Estrutura.Estrutura_Transacao;

import java.util.List;

public interface Interface_Controle_Resumo{

    List<Estrutura_Categoria> obterCategorias(int idUsuario);
    List<Estrutura_Transacao> obterTransacoes(int idUsuario);
    List<Estrutura_Transacao> filtrarTransacoes(List<Estrutura_Transacao> transacoes, Estrutura_Categoria categoriaSelecionada, String tipoSelecionado, String dataInicioStr, String dataFimStr);
    double calcularTotal(List<Estrutura_Transacao> transacoes, String tipo);

}