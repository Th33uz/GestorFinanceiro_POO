public class Estrutura_Usuario{
    private int id;
    private String nome;
    private String login;
    private String senha;

    public Estrutura_Usuario(int id, String nome, String login, String senha){
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        if(id <= 0){
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

}
