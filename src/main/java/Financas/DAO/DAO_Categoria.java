package Financas.DAO;

import Financas.Armazenamento.Conexao_SQLite;
import Financas.Estrutura.Estrutura_Categoria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DAO_Categoria implements Interface_DAO_Categoria{

    @Override
    public List<Estrutura_Categoria> listarCategoriasPorUsuario(int idUsuario){
        Session session = Conexao_SQLite.abrirConexao();

        List<Estrutura_Categoria> categorias = null;

        try{
            Query<Estrutura_Categoria> query = session.createQuery("FROM Estrutura_Categoria WHERE usuario.id = :idUsuario", Estrutura_Categoria.class);
            query.setParameter("idUsuario", idUsuario);

            categorias = query.list();
        }
        finally{
            session.close();
        }

        return categorias;
    }

    @Override
    public void alterarCategoria(Estrutura_Categoria categoria){
        Session session = Conexao_SQLite.abrirConexao();

        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            session.update(categoria);
            tx.commit();
        }
        catch(Exception e){
            if(tx != null){
                tx.rollback();
            }
        }
        finally{
            session.close();
        }
    }

    @Override
    public boolean excluirCategoria(int idCategoria){
        Session session = Conexao_SQLite.abrirConexao();

        boolean sucesso = false;
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Estrutura_Categoria categoria = session.get(Estrutura_Categoria.class, idCategoria);

            if(categoria != null){
                session.delete(categoria);
            }
            tx.commit();
            sucesso = true;
        }
        catch(Exception e){
            if(tx != null){
                tx.rollback();
            }
        }
        finally{
            session.close();
        }

        return sucesso;
    }

    @Override
    public void cadastrarCategoria(Estrutura_Categoria categoria){
        Session session = Conexao_SQLite.abrirConexao();

        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            session.save(categoria);
            tx.commit();
        }
        catch(Exception e){
            if(tx != null){
                tx.rollback();
            }
        }
        finally{
            session.close();
        }
    }

    @Override
    public Estrutura_Categoria buscarCategoriaPorId(int idCategoria){
        Session session = Conexao_SQLite.abrirConexao();

        Estrutura_Categoria categoria = null;

        try{
            categoria = session.get(Estrutura_Categoria.class, idCategoria);
        }
        catch(Exception e){
        }
        finally{
            session.close();
        }

        return categoria;
    }
}
