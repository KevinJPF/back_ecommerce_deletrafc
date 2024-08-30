package fatec.les.ecommerce.strategy;

import fatec.les.ecommerce.model.Cliente;
import fatec.les.ecommerce.model.DomainEntity;

public class ValidarCamposObrigatoriosCliente implements IStrategy {
    private static final ValidarCamposObrigatoriosCliente instance = new ValidarCamposObrigatoriosCliente();

    private ValidarCamposObrigatoriosCliente() {
        // Construtor privado para evitar instanciamento
    }

    public static ValidarCamposObrigatoriosCliente getInstance() {
        return instance;
    }

    @Override
    public String process(DomainEntity entidade) {
        StringBuilder message = new StringBuilder();

        try {
            Cliente cliente = (Cliente) entidade;

            if (!validarGenero(cliente)) {
                message.append("Gênero inválido.\n");
            }

            if (!validarNomeCliente(cliente)) {
                message.append("Nome do cliente inválido.\n");
            }

            if (!validarDataNascimento(cliente)) {
                message.append("Data de nascimento inválida.\n");
            }

            if (!validarCPF(cliente)) {
                message.append("CPF inválido.\n");
            }

            if (!validarTelefoneTipo(cliente)) {
                message.append("Tipo de telefone inválido.\n");
            }

            if (!validarTelefoneNumero(cliente)) {
                message.append("Número de telefone inválido.\n");
            }

            if (!validarEmail(cliente)) {
                message.append("E-mail inválido.\n");
            }

            if (!validarSenha(cliente)) {
                message.append("Senha inválida.\n");
            }

        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }

        return message.toString();
    }

    private boolean validarGenero(Cliente cliente) {
        return cliente.getGenero() != null && !cliente.getGenero().trim().isEmpty() && cliente.getGenero().length() == 1;
    }

    private boolean validarNomeCliente(Cliente cliente) {
        return cliente.getNomeCliente() != null && !cliente.getNomeCliente().trim().isEmpty();
    }

    private boolean validarDataNascimento(Cliente cliente) {
        // Supondo que a data seja no formato "dd/MM/yyyy"
        String dataNascimento = cliente.getDataNascimento();
        return dataNascimento != null && dataNascimento.matches("\\d{2}/\\d{2}/\\d{4}");
    }

    private boolean validarCPF(Cliente cliente) {
        // CPF deve ter 11 dígitos
        String cpf = cliente.getCpf();
        return cpf != null && cpf.matches("\\d{11}");
    }

    private boolean validarTelefoneTipo(Cliente cliente) {
        return cliente.getTelefoneTipo() != null && !cliente.getTelefoneTipo().trim().isEmpty();
    }

    private boolean validarTelefoneNumero(Cliente cliente) {
        // Número de telefone com ou sem DDD, aceitando formatos com 10 ou 11 dígitos
        String telefoneNumero = cliente.getTelefoneNumero();
        return telefoneNumero != null && telefoneNumero.matches("\\d{10,11}");
    }

    private boolean validarEmail(Cliente cliente) {
        String email = cliente.getEmail();
        return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    private boolean validarSenha(Cliente cliente) {
        String senha = cliente.getSenha();

        // A senha deve ter pelo menos 8 caracteres, conter pelo menos uma letra maiúscula, uma letra minúscula e um caractere especial
        if (senha != null) {
            return senha.length() >= 8 &&
                    senha.matches(".*[A-Z].*") &&  // Pelo menos uma letra maiúscula
                    senha.matches(".*[a-z].*") &&  // Pelo menos uma letra minúscula
                    senha.matches(".*[!@#$%^&*(),.?\":{}|<>].*");  // Pelo menos um caractere especial
        }

        return false;
    }

}

