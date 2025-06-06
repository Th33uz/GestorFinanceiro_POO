package Financas.DAO;

import Financas.Estrutura.Estrutura_Transacao;
import java.util.List;

public interface Interface_DAO_Transacao{

    boolean cadastrarTransacao(Estrutura_Transacao transacao);
    boolean editarTransacao(Estrutura_Transacao transacao);
    boolean excluirTransacao(Estrutura_Transacao transacao);
    List<Estrutura_Transacao> listarTransacoesPorUsuario(int idUsuario);
    List<Estrutura_Transacao> listarTransacoesPorCategoria(int idCategoria);

}
