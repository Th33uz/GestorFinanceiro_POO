package Financas.Controle;

import Financas.Estrutura.Estrutura_Categoria;
import Financas.Estrutura.Estrutura_Transacao;
import Financas.Estrutura.Estrutura_Usuario;

import java.util.List;

public class Controle_Historico implements Interface_Controle_Historico{

    private Controle_Categoria controleCategoria = new Controle_Categoria();
    private Controle_Transacao controleTransacao = new Controle_Transacao();
    private Controle_Usuario controleUsuario = new Controle_Usuario();

    @Override
    public List<Estrutura_Categoria> obterCategorias(int idUsuario){
        return controleCategoria.obterCategoriasPorUsuario(idUsuario);
    }

    @Override
    public List<Estrutura_Transacao> obterTransacoes(int idUsuario){
        return controleTransacao.listarTransacoesPorUsuario(idUsuario);
    }

    @Override
    public Estrutura_Usuario obterUsuario(int idUsuario){
        return controleUsuario.buscarPorId(idUsuario);
    }
}
