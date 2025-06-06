package Financas.Estrutura;

import jakarta.persistence.*;

@Entity
@Table(name = "Categoria")
public class Estrutura_Categoria{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Categoria")
    private int id;

    @Column(name = "Nome_Categoria", nullable = false)
    private String nomeCategoria;

    @Column(name = "Valor_Total_Despesa", nullable = false)
    private double valorTotalDespesa;

    @Column(name = "Valor_Total_Receita", nullable = false)
    private double valorTotalReceita;

    @Column(name = "Data_Criacao_Categoria", nullable = false)
    private String dataCriacaoCategoria;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario", nullable = false)
    private Estrutura_Usuario usuario;

    // Getters e Setters

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNomeCategoria(){
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria){
        this.nomeCategoria = nomeCategoria;
    }

    public double getValorTotalDespesa(){
        return valorTotalDespesa;
    }

    public void setValorTotalDespesa(double valorTotalDespesa){
        this.valorTotalDespesa = valorTotalDespesa;
    }

    public double getValorTotalReceita(){
        return valorTotalReceita;
    }

    public void setValorTotalReceita(double valorTotalReceita){
        this.valorTotalReceita = valorTotalReceita;
    }

    public String getDataCriacaoCategoria(){
        return dataCriacaoCategoria;
    }

    public void setDataCriacaoCategoria(String dataCriacaoCategoria){
        this.dataCriacaoCategoria = dataCriacaoCategoria;
    }

    public Estrutura_Usuario getUsuario(){
        return usuario;
    }

    public void setUsuario(Estrutura_Usuario usuario){
        this.usuario = usuario;
    }
}
