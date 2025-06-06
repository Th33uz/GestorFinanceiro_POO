package Financas.DAO;

import Financas.Estrutura.Estrutura_Usuario;

public interface Interface_DAO_Usuario{

    boolean salvarUsuario(Estrutura_Usuario usuario);
    Estrutura_Usuario buscarPorLogin(String login);
    Estrutura_Usuario buscarPorId(int id);
    boolean atualizarUsuario(Estrutura_Usuario usuario);

}
