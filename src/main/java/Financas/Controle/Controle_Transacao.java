package Financas.Controle;

import Financas.DAO.DAO_Transacao;
import Financas.Estrutura.Estrutura_Transacao;

import java.util.List;

public class Controle_Transacao implements Interface_Controle_Transacao{

    private DAO_Transacao dao = new DAO_Transacao();
    private Controle_Categoria controleCategoria = new Controle_Categoria();
    private Controle_Usuario controleUsuario = new Controle_Usuario();

    @Override
    public boolean cadastrarTransacao(Estrutura_Transacao transacao){
        boolean sucesso = dao.cadastrarTransacao(transacao);

        if(sucesso){
            controleCategoria.atualizarTotaisCategoria(
                    transacao.getCategoria().getId(),
                    transacao.getTipo(),
                    transacao.getValor()
            );

            controleUsuario.atualizarTotalUsuario(
                    transacao.getUsuario().getId(),
                    "",
                    0.0,
                    transacao.getTipo(),
                    transacao.getValor()
            );
        }

        return sucesso;
    }

    @Override
    public boolean editarTransacao(Estrutura_Transacao nova, Estrutura_Transacao antiga){
        boolean sucesso = dao.editarTransacao(nova);

        if(sucesso){
            double valorAntigo = antiga.getValor();
            double valorNovo = nova.getValor();

            if(!nova.getTipo().equals(antiga.getTipo())){
                controleCategoria.atualizarTotaisCategoria(
                        antiga.getCategoria().getId(),
                        antiga.getTipo(),
                        -valorAntigo
                );

                controleCategoria.atualizarTotaisCategoria(
                        nova.getCategoria().getId(),
                        nova.getTipo(),
                        valorNovo
                );
            }
            else{
                double diferenca = valorNovo - valorAntigo;
                controleCategoria.atualizarTotaisCategoria(
                        nova.getCategoria().getId(),
                        nova.getTipo(),
                        diferenca
                );
            }

            controleUsuario.atualizarTotalUsuario(
                    nova.getUsuario().getId(),
                    antiga.getTipo(),
                    valorAntigo,
                    nova.getTipo(),
                    valorNovo
            );
        }

        return sucesso;
    }

    @Override
    public boolean excluirTransacao(Estrutura_Transacao transacao){
        boolean sucesso = dao.excluirTransacao(transacao);

        if(sucesso){
            double valorAntigo = transacao.getValor();
            double valorNovo = 0.0;

            controleCategoria.atualizarTotaisCategoria(
                    transacao.getCategoria().getId(),
                    transacao.getTipo(),
                    -valorAntigo
            );

            controleUsuario.atualizarTotalUsuario(
                    transacao.getUsuario().getId(),
                    transacao.getTipo(),
                    valorAntigo,
                    "",
                    valorNovo
            );
        }

        return sucesso;
    }

    @Override
    public List<Estrutura_Transacao> listarTransacoesPorUsuario(int idUsuario){
        return dao.listarTransacoesPorUsuario(idUsuario);
    }

    public List<Estrutura_Transacao> listarTransacoesPorCategoria(int idCategoria) {
        return dao.listarTransacoesPorCategoria(idCategoria);
    }
}
