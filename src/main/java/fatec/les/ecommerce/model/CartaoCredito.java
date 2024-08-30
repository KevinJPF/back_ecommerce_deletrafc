package fatec.les.ecommerce.model;

public class CartaoCredito extends DomainEntity {
    private String nomeCartao;
    private String numeroCartao;
    private String nomeImpresso;
    private String bandeiraCartao;
    private String codigoSeguranca;
    private boolean favorito;
    private int clienteId;

    // Construtor completo
    public CartaoCredito(int idCartao, String nomeCartao, String numeroCartao, String nomeImpresso, String bandeiraCartao,
                         String codigoSeguranca, boolean favorito, int clienteId) {
        super(idCartao);
        setNomeCartao(nomeCartao);
        setNumeroCartao(numeroCartao);
        setNomeImpresso(nomeImpresso);
        setBandeiraCartao(bandeiraCartao);
        setCodigoSeguranca(codigoSeguranca);
        setFavorito(favorito);
        setClienteId(clienteId);
    }

    // Getters e Setters
    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNomeImpresso() {
        return nomeImpresso;
    }

    public void setNomeImpresso(String nomeImpresso) {
        this.nomeImpresso = nomeImpresso;
    }

    public String getBandeiraCartao() {
        return bandeiraCartao;
    }

    public void setBandeiraCartao(String bandeiraCartao) {
        this.bandeiraCartao = bandeiraCartao;
    }

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(String codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
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

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }
}
