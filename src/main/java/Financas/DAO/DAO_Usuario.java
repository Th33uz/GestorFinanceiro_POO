package Financas.DAO;

import Financas.Armazenamento.Conexao_SQLite;
import Financas.Estrutura.Estrutura_Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class DAO_Usuario implements Interface_DAO_Usuario {

    @Override
    public boolean salvarUsuario(Estrutura_Usuario usuario) {
        Session sessao = Conexao_SQLite.abrirConexao();
        Transaction transacao = null;
        boolean sucesso = false;

        try {
            transacao = sessao.beginTransaction();
            sessao.save(usuario);
            transacao.commit();
            sucesso = true;
        } catch (Exception e) {
            if (transacao != null) transacao.rollback();
            e.printStackTrace();
        } finally {
            sessao.close();
        }
        return sucesso;
    }

    @Override
    public Estrutura_Usuario buscarPorLogin(String login) {
        Session sessao = Conexao_SQLite.abrirConexao();
        Estrutura_Usuario usuario = null;

        try {
            String hql = "FROM Estrutura_Usuario WHERE login = :login";
            Query<Estrutura_Usuario> query = sessao.createQuery(hql, Estrutura_Usuario.class);
            query.setParameter("login", login);
            usuario = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessao.close();
        }
        return usuario;
    }

    @Override
    public Estrutura_Usuario buscarPorId(int id) {
        Session sessao = Conexao_SQLite.abrirConexao();
        Estrutura_Usuario usuario = null;

        try {
            usuario = sessao.get(Estrutura_Usuario.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessao.close();
        }
        return usuario;
    }

    @Override
    public boolean atualizarUsuario(Estrutura_Usuario usuario) {
        Session sessao = Conexao_SQLite.abrirConexao();
        Transaction transacao = null;
        boolean sucesso = false;

        try {
            transacao = sessao.beginTransaction();
            sessao.update(usuario);
            transacao.commit();
            sucesso = true;
        } catch (Exception e) {
            if (transacao != null) transacao.rollback();
            e.printStackTrace();
        } finally {
            sessao.close();
        }
        return sucesso;
    }
}
