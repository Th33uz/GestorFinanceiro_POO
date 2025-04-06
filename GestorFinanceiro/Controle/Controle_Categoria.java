package GestorFinanceiro.Controle;

import GestorFinanceiro.Estrutura.Estrutura_Usuario;
import GestorFinanceiro.Estrutura.Estrutura_Categoria;

import java.time.LocalDate;

public class Controle_Categoria{

    public void adicionarCategoria(Estrutura_Usuario usuario, int id, String nome){

        if(nome == null || nome.length() == 0){
            //A mensagem aki é só para aparecer no terminal, pra ter certeza que a Categoria não foi instanciada corretamente
            // vai ter que colocar uma mensagemn de erro na interface depois!
            //(Isso se aplica para os demais metodos como o de editar e excluir tbm)
            System.out.println("A Categoria precisa ter um NOME!.");
        }

        else{
            Estrutura_Categoria categoria = new Estrutura_Categoria(id, nome, LocalDate.now());
            usuario.adicionarCategoria(categoria);
        }
    }

    public void editarCategoria(Estrutura_Usuario usuario, int id, String novoNome){
        boolean categoriaEncontrada = false;

        for(Estrutura_Categoria categoria : usuario.getCategorias()){
            if(categoria.getId() == id){
                categoria.setNome(novoNome);
                categoriaEncontrada = true;
                break;
            }
        }

        if(categoriaEncontrada == false){
            System.out.println("Categoria não encontrada!.");
        }
    }

    public void excluirCategoria(Estrutura_Usuario usuario, int id){
        boolean categoriaRemovida = false;

        for(Estrutura_Categoria categoria : usuario.getCategorias()){
            if(categoria.getId() == id){
                usuario.getCategorias().remove(categoria);
                categoriaRemovida = true;
                break;
            }
        }

        if(categoriaRemovida == false){
            System.out.println("Categoria não encontrada, não foi possivel excluir!.");
        }
    }

}
