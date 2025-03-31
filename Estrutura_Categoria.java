import java.time.LocalDate;

public class Estrutura_Categoria {
    private int id;
    private String nome;
    private LocalDate dataCriacao;

    public Estrutura_Categoria(int id, String nome, LocalDate dataCriacao) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        if (id <= 0) {
            System.out.println("Erro: O ID deve ser maior que zero.");
        } else {
            this.id = id;
        }

    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        if (nome != null && nome.length() != 0) {
            this.nome = nome;
        } else {
            System.out.println("Erro: O nome não pode ser vazio.");
        }

    }

    public LocalDate getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        if (dataCriacao == null) {
            System.out.println("Erro: A data de criação não pode ser nula.");
        } else {
            this.dataCriacao = dataCriacao;
        }

    }
}
