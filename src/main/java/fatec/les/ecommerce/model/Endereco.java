package fatec.les.ecommerce.model;

public class Endereco extends DomainEntity {
    private String nomeEndereco;
    private String tipoResidencia;
    private String tipoLogradouro;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;
    private String obsEndereco;
    private boolean enderecoEntrega;
    private boolean enderecoCobranca;
    private boolean favorito;
    private int clienteId;

    // Construtor completo
    public Endereco(int idEndereco, String nomeEndereco, String tipoResidencia, String tipoLogradouro, String logradouro, String numero,
                    String bairro, String cep, String cidade, String estado, String pais, String obsEndereco, boolean enderecoEntrega,
                    boolean enderecoCobranca, boolean favorito, int clienteId) {
        super(idEndereco);
        setNomeEndereco(nomeEndereco);
        setTipoResidencia(tipoResidencia);
        setTipoLogradouro(tipoLogradouro);
        setLogradouro(logradouro);
        setNumero(numero);
        setBairro(bairro);
        setCep(cep);
        setCidade(cidade);
        setEstado(estado);
        setPais(pais);
        setObsEndereco(obsEndereco);
        setEnderecoEntrega(enderecoEntrega);
        setEnderecoCobranca(enderecoCobranca);
        setFavorito(favorito);
        setClienteId(clienteId);
    }

    // Getters e Setters
    public String getTipoResidencia() {
        return tipoResidencia;
    }

    public void setTipoResidencia(String tipoResidencia) {
        this.tipoResidencia = tipoResidencia;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public boolean isEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(boolean enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public boolean isEnderecoCobranca() {
        return enderecoCobranca;
    }

    public void setEnderecoCobranca(boolean enderecoCobranca) {
        this.enderecoCobranca = enderecoCobranca;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getNomeEndereco() {
        return nomeEndereco;
    }

    public void setNomeEndereco(String nomeEndereco) {
        this.nomeEndereco = nomeEndereco;
    }

    public String getObsEndereco() {
        return obsEndereco;
    }

    public void setObsEndereco(String obsEndereco) {
        this.obsEndereco = obsEndereco;
    }
}
