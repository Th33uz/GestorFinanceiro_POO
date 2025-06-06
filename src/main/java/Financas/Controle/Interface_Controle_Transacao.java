package Financas.Controle;

import Financas.Estrutura.Estrutura_Transacao;

import java.util.List;

public interface Interface_Controle_Transacao{

    boolean cadastrarTransacao(Estrutura_Transacao transacao);
    boolean editarTransacao(Estrutura_Transacao nova, Estrutura_Transacao antiga);
    boolean excluirTransacao(Estrutura_Transacao transacao);
    List<Estrutura_Transacao> listarTransacoesPorUsuario(int idUsuario);

}
