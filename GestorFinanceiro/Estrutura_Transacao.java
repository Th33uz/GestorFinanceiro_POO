package GestorFinanceiro;

import java.time.LocalDate;

public class Estrutura_Transacao{
    private String descricao;
    private double valor;
    private LocalDate data;
    private String tipo; // Só vai poder ser Receita ou Despesa!
    private int idCategoria;

    public Estrutura_Transacao(String descricao, double valor, LocalDate data, String tipo, int idCategoria){
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.idCategoria = idCategoria;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        if(descricao == null || descricao.length() == 0){
            System.out.println("Erro: A descrição não pode ser vazia.");
        }

        else{
            this.descricao = descricao;
        }
    }

    public double getValor(){
        return valor;
    }

    public void setValor(double valor){
        if(valor <= 0){
            System.out.println("Erro: O valor deve ser maior que zero.");
        }

        else{
            this.valor = valor;
        }
    }

    public LocalDate getData(){
        return data;
    }

    public void setData(LocalDate data){
        if(data == null){
            System.out.println("Erro: A data não pode ser nula.");
        }

        else{
            this.data = data;
        }
    }

    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo){
        if(tipo == null || tipo.length() == 0){
            System.out.println("Erro: O tipo deve ser especificado (Receita ou Despesa).");
        }

        else{
            this.tipo = tipo;
        }
    }

    public int getIdCategoria(){
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria){
        if(idCategoria <= 0){
            System.out.println("Erro: O ID da categoria deve ser maior que zero.");
        }

        else{
            this.idCategoria = idCategoria;
        }
    }
}
