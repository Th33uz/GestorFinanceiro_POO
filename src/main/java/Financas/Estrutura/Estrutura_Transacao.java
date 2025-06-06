package Financas.Estrutura;

import jakarta.persistence.*;

@Entity
@Table(name = "Transacao")
public class Estrutura_Transacao{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Transacao")
    private int id;

    @Column(name = "Tipo", nullable = false)
    private String tipo;  // "Receita" ou "Despeza"

    @Column(name = "Descricao", nullable = false)
    private String descricao;

    @Column(name = "Valor", nullable = false)
    private double valor;

    @Column(name = "Data", nullable = false)
    private String dataTransacao;

    @ManyToOne
    @JoinColumn(name = "ID_Categoria", nullable = false)
    private Estrutura_Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario", nullable = false)
    private Estrutura_Usuario usuario;

    // Getters e setters

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public double getValor(){
        return valor;
    }

    public void setValor(double valor){
        this.valor = valor;
    }

    public String getDataTransacao(){
        return dataTransacao;
    }

    public void setDataTransacao(String dataTransacao){
        this.dataTransacao = dataTransacao;
    }

    public Estrutura_Categoria getCategoria(){
        return categoria;
    }

    public void setCategoria(Estrutura_Categoria categoria){
        this.categoria = categoria;
    }

    public Estrutura_Usuario getUsuario(){
        return usuario;
    }

    public void setUsuario(Estrutura_Usuario usuario){
        this.usuario = usuario;
    }
}
