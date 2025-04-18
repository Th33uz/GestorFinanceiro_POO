package GestorFinanceiro.Estrutura;

import java.util.ArrayList;
import java.util.List;

public class Estrutura_Usuario{
    private int id;
    private String nome;
    private String login;
    private String senha;
    private List<Estrutura_Transacao> transacoes;
    private List<Estrutura_Categoria> categorias;

    public Estrutura_Usuario(int id, String nome, String login, String senha){
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.transacoes = new ArrayList<>();
        this.categorias = new ArrayList<>();
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        if(id <= 0){
            //Para os métodos da classe aonde possuem "print's", não serão exibidos ao usuário pois não estão implementados na classe de Interface
            System.out.println("Erro: O ID deve ser maior que zero.");
        }

        else{
            this.id = id;
        }
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        if(nome == null || nome.length() == 0){
            System.out.println("Erro: O nome não pode ser vazio.");
        }

        else{
            this.nome = nome;
        }
    }

    public String getLogin(){
        return login;
    }

    public void setLogin(String login){
        if(login == null || login.length() == 0){
            System.out.println("Erro: O login não pode ser vazio.");
        }

        else{
            this.login = login;
        }
    }

    public String getSenha(){
        return senha;
    }

    public void setSenha(String senha){
        if(senha == null || senha.length() == 0){
            System.out.println("Erro: A senha não pode ser vazia.");
        }

        else{
            this.senha = senha;
        }
    }

    public List<Estrutura_Transacao> getTransacoes(){
        if(transacoes.isEmpty()){
            System.out.println("Nenhuma transação registrada.");
        }

        else{
            return transacoes;
        }
        return new ArrayList<>();
    }

    public List<Estrutura_Categoria> getCategorias(){
        if(categorias.isEmpty()){
            System.out.println("Nenhuma categoria registrada.");
        }

        else{
            return categorias;
        }
        return new ArrayList<>();
    }

    public void adicionarTransacao(Estrutura_Transacao transacao){
        if(transacao == null){
            System.out.println("Erro: A transação não pode ser nula.");
        }

        else{
            this.transacoes.add(transacao);
        }
    }

    public void adicionarCategoria(Estrutura_Categoria categoria){
        if(categoria == null){
            System.out.println("Erro: A categoria não pode ser nula.");
        }

        else{
            this.categorias.add(categoria);
        }
    }
}
