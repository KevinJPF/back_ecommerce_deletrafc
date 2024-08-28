package fatec.les.ecommerce.model;

import java.util.List;

public class Cliente extends DomainEntity {
    private String genero;
    private String nome;
    private String dataNascimento;
    private String cpf;
    private String telefoneTipo;
    private String telefoneNumero;
    private String email;
    private String senha;
    private List<Endereco> enderecos;
    private List<CartaoCredito> cartoesCredito;

    // Construtor completo
    public Cliente(int idCliente, String genero, String nome, String dataNascimento, String cpf,
                   String telefoneTipo, String telefoneNumero, String email, String senha, List<Endereco> enderecos, List<CartaoCredito> cartoesCredito) {
        super(idCliente);
        setGenero(genero);
        setNome(nome);
        setDataNascimento(dataNascimento);
        setCpf(cpf);
        setTelefoneTipo(telefoneTipo);
        setTelefoneNumero(telefoneNumero);
        setEmail(email);
        setSenha(senha);
        setEnderecos(enderecos);
        setCartoesCredito(cartoesCredito);
    }

    // Getters e Setters
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefoneTipo() {
        return telefoneTipo;
    }

    public void setTelefoneTipo(String telefoneTipo) {
        this.telefoneTipo = telefoneTipo;
    }

    public String getTelefoneNumero() {
        return telefoneNumero;
    }

    public void setTelefoneNumero(String telefoneNumero) {
        this.telefoneNumero = telefoneNumero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<CartaoCredito> getCartoesCredito() {
        return cartoesCredito;
    }

    public void setCartoesCredito(List<CartaoCredito> cartoesCredito) {
        this.cartoesCredito = cartoesCredito;
    }
}
