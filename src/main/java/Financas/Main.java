package Financas;

import Financas.Interface.Interface_Login;
import Financas.Controle.Controle_Usuario;

public class Main{
    public static void main(String[] args){

        new Interface_Login(new Controle_Usuario()).setVisible(true);

    }
}