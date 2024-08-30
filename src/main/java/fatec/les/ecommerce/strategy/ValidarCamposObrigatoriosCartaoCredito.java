package fatec.les.ecommerce.strategy;

import fatec.les.ecommerce.model.CartaoCredito;
import fatec.les.ecommerce.model.DomainEntity;

public class ValidarCamposObrigatoriosCartaoCredito implements IStrategy {
    private static final ValidarCamposObrigatoriosCartaoCredito instance = new ValidarCamposObrigatoriosCartaoCredito();

    private ValidarCamposObrigatoriosCartaoCredito() {
        // Construtor privado para evitar instanciamento
    }

    public static ValidarCamposObrigatoriosCartaoCredito getInstance() {return instance;}

    @Override
    public String process(DomainEntity entidade) {
        StringBuilder message = new StringBuilder();

        try {
            CartaoCredito cartao = (CartaoCredito) entidade;

            if (!validarNomeCartao(cartao)) {
                message.append("Nome do cartão inválido.\n");
            }

            if (!validarNumeroCartao(cartao)) {
                message.append("Número do cartão inválido.\n");
            }

            if (!validarNomeImpresso(cartao)) {
                message.append("Nome impresso inválido.\n");
            }

            if (!validarBandeiraCartao(cartao)) {
                message.append("Bandeira do cartão inválida.\n");
            }

            if (!validarCodigoSeguranca(cartao)) {
                message.append("Código de segurança inválido.\n");
            }

            if (!validarCodigoCliente(cartao)) {
                message.append("Código de cliente inválido.\n");
            }

        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }

        return message.toString();
    }

    private boolean validarNomeCartao(CartaoCredito cartao) {
        return cartao.getNomeCartao() != null && !cartao.getNomeCartao().trim().isEmpty();
    }

    private boolean validarNumeroCartao(CartaoCredito cartao) {
        return cartao.getNumeroCartao() != null && cartao.getNumeroCartao().matches("\\d{16}");
    }

    private boolean validarNomeImpresso(CartaoCredito cartao) {
        return cartao.getNomeImpresso() != null && !cartao.getNomeImpresso().trim().isEmpty();
    }

    private boolean validarBandeiraCartao(CartaoCredito cartao) {
        return cartao.getBandeiraCartao() != null && !cartao.getBandeiraCartao().trim().isEmpty();
    }

    private boolean validarCodigoSeguranca(CartaoCredito cartao) {
        return cartao.getCodigoSeguranca() != null && cartao.getCodigoSeguranca().matches("\\d{3}");
    }

    private boolean validarCodigoCliente(CartaoCredito cartao) {
        return cartao.getClienteId() > 0;
    }
}
