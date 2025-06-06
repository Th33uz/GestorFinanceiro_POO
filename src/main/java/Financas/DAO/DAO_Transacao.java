package Financas.DAO;

import Financas.Armazenamento.Conexao_SQLite;
import Financas.Estrutura.Estrutura_Transacao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class DAO_Transacao implements Interface_DAO_Transacao {

    @Override
    public boolean cadastrarTransacao(Estrutura_Transacao transacao){
        Transaction tx = null;

        try(Session sessao = Conexao_SQLite.abrirConexao()){
            tx = sessao.beginTransaction();
            sessao.save(transacao);
            tx.commit();
            return true;
        }
        catch(Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editarTransacao(Estrutura_Transacao transacao){
        Transaction tx = null;

        try(Session sessao = Conexao_SQLite.abrirConexao()){
            tx = sessao.beginTransaction();
            sessao.update(transacao);
            tx.commit();
            return true;
        }
        catch(Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean excluirTransacao(Estrutura_Transacao transacao){
        Transaction tx = null;

        try(Session sessao = Conexao_SQLite.abrirConexao()){
            tx = sessao.beginTransaction();
            sessao.delete(transacao);
            tx.commit();
            return true;
        }
        catch(Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Estrutura_Transacao> listarTransacoesPorUsuario(int idUsuario){
        try(Session sessao = Conexao_SQLite.abrirConexao()){
            return sessao.createQuery("FROM Estrutura_Transacao WHERE usuario.id = :idUsuario")
                    .setParameter("idUsuario", idUsuario)
                    .list();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Estrutura_Transacao> listarTransacoesPorCategoria(int idCategoria) {
        try(Session sessao = Conexao_SQLite.abrirConexao()){
            return sessao.createQuery("FROM Estrutura_Transacao WHERE categoria.id = :idCategoria")
                    .setParameter("idCategoria", idCategoria)
                    .list();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
