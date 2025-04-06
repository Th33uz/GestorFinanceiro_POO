package GestorFinanceiro;

public class Main{
    public static void main(String[] args){

        Controle_Usuario controleUsuario = new Controle_Usuario();

        new Interface_Login(controleUsuario);

    }
}

