package Financas.Estrutura;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Estrutura_Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Usuario")
    private int id;

    @Column(name = "Nome", nullable = false)
    private String nome;

    @Column(name = "Login", nullable = false, unique = true)
    private String login;

    @Column(name = "Senha", nullable = false)
    private String senha;

    @Column(name = "Historico_Valor_Total", nullable = false)
    private double historicoValorTotal;

    // Getters e Setters

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getLogin(){
        return login;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getSenha(){
        return senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public double getHistoricoValorTotal(){
        return historicoValorTotal;
    }

    public void setHistoricoValorTotal(double historicoValorTotal){
        this.historicoValorTotal = historicoValorTotal;
    }
}
