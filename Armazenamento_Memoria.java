import java.util.ArrayList;
import java.util.List;

public class Armazenamento_Memoria {
    private static Armazenamento_Memoria Instancia;
    private List<Estrutura_Usuario> ListaUsuarios;

    private Armazenamento_Memoria(){
        ListaUsuarios = new ArrayList<>();
    }

    public static Armazenamento_Memoria getInstance(){
        if(Instancia == null){
            Instancia = new Armazenamento_Memoria();
        }

        return Instancia;
    }

    public List<Estrutura_Usuario> getListaUsuarios(){
        if(ListaUsuarios.isEmpty()){
            System.out.println("Nenhum usuário armazenado no momento.");
        }

        else{
            return ListaUsuarios;
        }

        return new ArrayList<>();
    }

    public void adicionarUsuario(Estrutura_Usuario Usuario){
        if(Usuario == null){
            System.out.println("Erro: O usuário não pode ser nulo.");
        }

        else{
            ListaUsuarios.add(Usuario);
            System.out.println("Usuário adicionado com sucesso.");
        }
    }

    public Estrutura_Usuario buscarUsuarioPorLogin(String Login){
        for(Estrutura_Usuario Usuario : ListaUsuarios){
            if(Usuario.getLogin().equals(Login)){
                return Usuario;
            }
        }

        System.out.println("Usuário com o login especificado não encontrado.");
        return null;
    }
}
