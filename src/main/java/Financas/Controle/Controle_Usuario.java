package Financas.Controle;

import Financas.DAO.Interface_DAO_Usuario;
import Financas.DAO.DAO_Usuario;
import Financas.Estrutura.Estrutura_Usuario;

public class Controle_Usuario implements Interface_Controle_Usuario{

    private Interface_DAO_Usuario dao = new DAO_Usuario();

    public boolean cadastrarUsuario(String nome, String login, String senha){
        Estrutura_Usuario existente = dao.buscarPorLogin(login);

        if(existente != null){
            return false;
        }

        Estrutura_Usuario novo = new Estrutura_Usuario();
        novo.setNome(nome);
        novo.setLogin(login);
        novo.setSenha(senha);
        novo.setHistoricoValorTotal(0.0);

        return dao.salvarUsuario(novo);
    }

    public Estrutura_Usuario autenticar(String login, String senha){
        Estrutura_Usuario usuario = dao.buscarPorLogin(login);

        if(usuario != null && usuario.getSenha().equals(senha)){
            return usuario;
        }

        return null;
    }

    public void atualizarTotalUsuario(int idUsuario, String tipoAntigo, double valorAntigo, String tipoNovo, double valorNovo){
        Estrutura_Usuario usuario = dao.buscarPorId(idUsuario);

        if(usuario != null){
            double totalAtual = usuario.getHistoricoValorTotal();

            if("Receita".equalsIgnoreCase(tipoAntigo)){
                totalAtual -= valorAntigo;
            }
            else if("Despesa".equalsIgnoreCase(tipoAntigo)){
                totalAtual += valorAntigo;
            }

            if("Receita".equalsIgnoreCase(tipoNovo)){
                totalAtual += valorNovo;
            }
            else if("Despesa".equalsIgnoreCase(tipoNovo)){
                totalAtual -= valorNovo;
            }

            usuario.setHistoricoValorTotal(totalAtual);

            dao.atualizarUsuario(usuario);
        }
    }

    public Estrutura_Usuario buscarPorId(int id){
        return dao.buscarPorId(id);
    }
}
