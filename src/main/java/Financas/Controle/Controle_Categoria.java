package Financas.Controle;

import Financas.DAO.Interface_DAO_Categoria;
import Financas.DAO.DAO_Categoria;
import Financas.Estrutura.Estrutura_Categoria;
import Financas.Estrutura.Estrutura_Usuario;

import java.util.List;

public class Controle_Categoria implements Interface_Controle_Categoria{

    private Interface_DAO_Categoria daoCategoria = new DAO_Categoria();

    public List<Estrutura_Categoria> obterCategoriasPorUsuario(int idUsuario){
        return daoCategoria.listarCategoriasPorUsuario(idUsuario);
    }

    public boolean alterarCategoria(int idCategoria, String novoNome, String novaData){

        Estrutura_Categoria categoria = daoCategoria.buscarCategoriaPorId(idCategoria);

        if(categoria != null){
            categoria.setNomeCategoria(novoNome);
            categoria.setDataCriacaoCategoria(novaData);
            daoCategoria.alterarCategoria(categoria);
            return true;
        }
        return false;
    }

    public boolean excluirCategoria(int idCategoria){
        return daoCategoria.excluirCategoria(idCategoria);
    }

    public void atualizarTotaisCategoria(int idCategoria, String tipo, double valor){

        Estrutura_Categoria categoria = daoCategoria.buscarCategoriaPorId(idCategoria);

        if(categoria != null){
            if("Receita".equalsIgnoreCase(tipo)){
                categoria.setValorTotalReceita(categoria.getValorTotalReceita() + valor);
            }
            else if("Despesa".equalsIgnoreCase(tipo)){
                categoria.setValorTotalDespesa(categoria.getValorTotalDespesa() + valor);
            }
            daoCategoria.alterarCategoria(categoria);
        }
    }

    public boolean cadastrarCategoria(int idUsuario, String nome, String dataCriacao){

        if(nome == null || nome.trim().isEmpty() || dataCriacao == null || dataCriacao.trim().isEmpty()){
            return false;
        }

        Estrutura_Categoria novaCategoria = new Estrutura_Categoria();
        novaCategoria.setNomeCategoria(nome);
        novaCategoria.setDataCriacaoCategoria(dataCriacao);
        novaCategoria.setValorTotalDespesa(0.0);
        novaCategoria.setValorTotalReceita(0.0);

        Estrutura_Usuario usuario = new Estrutura_Usuario();
        usuario.setId(idUsuario);
        novaCategoria.setUsuario(usuario);

        daoCategoria.cadastrarCategoria(novaCategoria);
        return true;
    }
}
