package Financas.Controle;

import Financas.Estrutura.Estrutura_Categoria;
import Financas.Estrutura.Estrutura_Transacao;
import Financas.Estrutura.Estrutura_Usuario;

import java.util.List;

public interface Interface_Controle_Historico{

    List<Estrutura_Categoria> obterCategorias(int idUsuario);
    List<Estrutura_Transacao> obterTransacoes(int idUsuario);
    Estrutura_Usuario obterUsuario(int idUsuario);

}
