package GestorFinanceiro;

import GestorFinanceiro.Controle.Controle_Usuario;
import GestorFinanceiro.Interface.Interface_Login;

public class Main{
    public static void main(String[] args){

        Controle_Usuario controleUsuario = new Controle_Usuario();

        new Interface_Login(controleUsuario);

    }
}

