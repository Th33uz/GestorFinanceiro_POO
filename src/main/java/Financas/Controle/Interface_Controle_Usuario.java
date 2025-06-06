package Financas.Controle;

import Financas.Estrutura.Estrutura_Usuario;

public interface Interface_Controle_Usuario{

    boolean cadastrarUsuario(String nome, String login, String senha);
    Estrutura_Usuario autenticar(String login, String senha);
    void atualizarTotalUsuario(int idUsuario, String tipoAntigo, double valorAntigo, String tipoNovo, double valorNovo);
    Estrutura_Usuario buscarPorId(int id);

}
