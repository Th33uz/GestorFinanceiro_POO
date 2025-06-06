package Financas.Armazenamento;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Conexao_SQLite{

    private static final SessionFactory fabricaSessoes;

    static{
        try{
            fabricaSessoes = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        }
        catch(Throwable excecao){
            System.err.println("Erro ao criar a Sessão do Hibernate: ");
            throw new ExceptionInInitializerError(excecao);
        }
    }

    public static Session abrirConexao(){
        return fabricaSessoes.openSession();
    }

    public static void encerrar(){
        if(fabricaSessoes != null){
            fabricaSessoes.close();
        }
    }
}
