package GestorFinanceiro.Controle;

import GestorFinanceiro.Estrutura.Estrutura_Usuario;

import java.util.ArrayList;
import java.util.List;

public class Controle_Usuario{
    private List<Estrutura_Usuario> usuarios;

    public Controle_Usuario() {
        this.usuarios = new ArrayList<>();
    }

    public void adicionarUsuario(Estrutura_Usuario usuario){
        if(usuario == null){
            System.out.println("Erro: Usuário não pode ser nulo.");
        }

        else{
            usuarios.add(usuario);
        }
    }

    public Estrutura_Usuario validarLogin(String login, String senha){
        for(Estrutura_Usuario usuario : usuarios){
            if(usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)){
                return usuario;
            }
        }

        System.out.println("Erro: Login ou senha inválidos.");
        return null;
    }

    public List<Estrutura_Usuario> getUsuarios(){
        if(usuarios.isEmpty()){
            System.out.println("Nenhum usuário cadastrado.");
            return new ArrayList<>();
        }

        else{
            return usuarios;
        }
    }
}
