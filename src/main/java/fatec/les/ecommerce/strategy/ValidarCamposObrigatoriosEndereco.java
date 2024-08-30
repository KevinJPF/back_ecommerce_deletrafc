package fatec.les.ecommerce.strategy;

import fatec.les.ecommerce.model.CartaoCredito;
import fatec.les.ecommerce.model.DomainEntity;
import fatec.les.ecommerce.model.Endereco;

public class ValidarCamposObrigatoriosEndereco implements IStrategy {
    private static final ValidarCamposObrigatoriosEndereco instance = new ValidarCamposObrigatoriosEndereco();

    private ValidarCamposObrigatoriosEndereco() {
        // Construtor privado para evitar instanciamento
    }

    public static ValidarCamposObrigatoriosEndereco getInstance() {
        return instance;
    }

    @Override
    public String process(DomainEntity entidade) {
        StringBuilder message = new StringBuilder();

        try {
            Endereco endereco = (Endereco) entidade;

            if (!validarNomeEndereco(endereco)) {
                message.append("Nome do endereço inválido.\n");
            }

            if (!validarTipoResidencia(endereco)) {
                message.append("Tipo de residência inválido.\n");
            }

            if (!validarTipoLogradouro(endereco)) {
                message.append("Tipo de logradouro inválido.\n");
            }

            if (!validarLogradouro(endereco)) {
                message.append("Logradouro inválido.\n");
            }

            if (!validarNumero(endereco)) {
                message.append("Número inválido.\n");
            }

            if (!validarBairro(endereco)) {
                message.append("Bairro inválido.\n");
            }

            if (!validarCEP(endereco)) {
                message.append("CEP inválido.\n");
            }

            if (!validarCidade(endereco)) {
                message.append("Cidade inválida.\n");
            }

            if (!validarEstado(endereco)) {
                message.append("Estado inválido.\n");
            }

            if (!validarPais(endereco)) {
                message.append("País inválido.\n");
            }

            if (!validarCodigoCliente(endereco)) {
                message.append("Código de cliente inválido.\n");
            }

            // `obsEndereco` é opcional, então não precisa de validação

        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }

        return message.toString();
    }

    private boolean validarNomeEndereco(Endereco endereco) {
        return endereco.getNomeEndereco() != null && !endereco.getNomeEndereco().trim().isEmpty();
    }

    private boolean validarTipoResidencia(Endereco endereco) {
        return endereco.getTipoResidencia() != null && !endereco.getTipoResidencia().trim().isEmpty();
    }

    private boolean validarTipoLogradouro(Endereco endereco) {
        return endereco.getTipoLogradouro() != null && !endereco.getTipoLogradouro().trim().isEmpty();
    }

    private boolean validarLogradouro(Endereco endereco) {
        return endereco.getLogradouro() != null && !endereco.getLogradouro().trim().isEmpty();
    }

    private boolean validarNumero(Endereco endereco) {
        return endereco.getNumero() != null && !endereco.getNumero().trim().isEmpty();
    }

    private boolean validarBairro(Endereco endereco) {
        return endereco.getBairro() != null && !endereco.getBairro().trim().isEmpty();
    }

    private boolean validarCEP(Endereco endereco) {
        return endereco.getCep() != null && endereco.getCep().matches("\\d{5}-\\d{3}");
    }

    private boolean validarCidade(Endereco endereco) {
        return endereco.getCidade() != null && !endereco.getCidade().trim().isEmpty();
    }

    private boolean validarEstado(Endereco endereco) {
        return endereco.getEstado() != null && !endereco.getEstado().trim().isEmpty();
    }

    private boolean validarPais(Endereco endereco) {
        return endereco.getPais() != null && !endereco.getPais().trim().isEmpty();
    }

    private boolean validarCodigoCliente(Endereco endereco) {
        return endereco.getClienteId() > 0;
    }
}

