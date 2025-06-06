package Financas.DAO;

import Financas.Estrutura.Estrutura_Categoria;
import java.util.List;

public interface Interface_DAO_Categoria{

    List<Estrutura_Categoria> listarCategoriasPorUsuario(int idUsuario);
    void alterarCategoria(Estrutura_Categoria categoria);
    boolean excluirCategoria(int idCategoria);
    void cadastrarCategoria(Estrutura_Categoria categoria);
    Estrutura_Categoria buscarCategoriaPorId(int idCategoria);

}
