package fatec.les.ecommerce.model;

public class Autor extends DomainEntity {
    private String nome;

    public Autor(int id, String nome) {
        super(id);
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
