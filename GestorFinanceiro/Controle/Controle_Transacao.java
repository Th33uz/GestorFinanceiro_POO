package GestorFinanceiro.Controle;

import GestorFinanceiro.Estrutura.Estrutura_Usuario;
import GestorFinanceiro.Estrutura.Estrutura_Transacao;

import java.time.LocalDate;

public class Controle_Transacao{

    public void adicionarTransacao(Estrutura_Usuario usuario, String descricao, double valor, LocalDate data, String tipo, int idCategoria){
        if(descricao == null || descricao.length() == 0 || valor <= 0 || data == null || tipo == null || tipo.length() == 0){
            System.out.println("Campos com valores ERRADOS para adicionar a transação!");
        }

        else{
            Estrutura_Transacao transacao = new Estrutura_Transacao(descricao, valor, data, tipo, idCategoria);
            usuario.adicionarTransacao(transacao);
        }
    }

    public void editarTransacao(Estrutura_Usuario usuario, String descricaoAntiga, String novaDescricao, double novoValor, LocalDate novaData, String novoTipo, int novoIdCategoria){
        boolean transacaoEncontrada = false;

        for(Estrutura_Transacao transacao : usuario.getTransacoes()){
            if(transacao.getDescricao().equals(descricaoAntiga)){
                transacao.setDescricao(novaDescricao);
                transacao.setValor(novoValor);
                transacao.setData(novaData);
                transacao.setTipo(novoTipo);
                transacao.setIdCategoria(novoIdCategoria);
                transacaoEncontrada = true;
                break;
            }
        }

        if(transacaoEncontrada == false){
            System.out.println("Transação não encontrada!");
        }
    }

    public void excluirTransacao(Estrutura_Usuario usuario, String descricao){
        boolean transacaoRemovida = false;

        for(Estrutura_Transacao transacao : usuario.getTransacoes()){
            if(transacao.getDescricao().equals(descricao)){
                usuario.getTransacoes().remove(transacao);
                transacaoRemovida = true;
                break;
            }
        }

        if(transacaoRemovida == false){
            System.out.println("Transação não encontrada, não foi possível excluir!");
        }
    }
}
