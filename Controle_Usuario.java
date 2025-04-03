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
}
