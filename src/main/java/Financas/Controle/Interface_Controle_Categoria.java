package Financas.Controle;

import Financas.Estrutura.Estrutura_Categoria;

import java.util.List;

public interface Interface_Controle_Categoria{

    List<Estrutura_Categoria> obterCategoriasPorUsuario(int idUsuario);
    boolean alterarCategoria(int idCategoria, String novoNome, String novaData);
    boolean excluirCategoria(int idCategoria);
    void atualizarTotaisCategoria(int idCategoria, String tipo, double valor);
    boolean cadastrarCategoria(int idUsuario, String nome, String dataCriacao);

}
