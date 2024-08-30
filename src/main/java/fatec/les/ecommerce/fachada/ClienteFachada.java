package fatec.les.ecommerce.fachada;

import fatec.les.ecommerce.dao.ClienteDAO;
import fatec.les.ecommerce.model.Cliente;
import fatec.les.ecommerce.model.DomainEntity;
import fatec.les.ecommerce.strategy.ValidarCamposObrigatoriosCliente;

import java.util.List;

public class ClienteFachada implements IFachada {
    private static final ClienteFachada instance = new ClienteFachada();

    private ClienteFachada() {
        // Construtor privado para evitar instanciamento
    }

    public static ClienteFachada getInstance() {return instance;}

    @Override
    public List<DomainEntity> selectEntities() {
        List<DomainEntity> clientes = ClienteDAO.getInstance().select();

        return clientes;
    }

    @Override
    public DomainEntity selectEntity(Integer id) {
        return ClienteDAO.getInstance().select(id);
    }

    @Override
    public String insertEntity(DomainEntity entidadeCliente) {
        if (entidadeCliente instanceof Cliente) {
            Cliente cliente = (Cliente) entidadeCliente;
            String returnString = "";

            returnString += ValidarCamposObrigatoriosCliente.getInstance().process(cliente);

            if (returnString.isEmpty()) {
                int id = ClienteDAO.getInstance().insert(cliente);

                if (id > 0) {
//                    if (!LivroDAO.getInstance().insertLivroCategoria(id, cliente.getCategorias()).equals("sucesso")) {
//                        LivroDAO.getInstance().delete(id);
//                        returnString +=  "Erro ao inserir categorias.\n";
//                    }
//
//                    if (!LivroDAO.getInstance().insertLivroAutor(id, cliente.getAutores()).equals("sucesso")) {
//                        LivroDAO.getInstance().delete(id);
//                        returnString +=  "Erro ao inserir autores.\n";
//                    }

//                    returnString += GerarLog.getInstance().process(id, 1);
                } else {
                    returnString += "O cliente não foi adicionado no banco de dados.\n";
                }
            }
            return returnString.isEmpty() ? "Cliente inserido com sucesso." : returnString;
        } else {
            return "A entidadeCliente fornecida não é um objeto Cliente válido.";
        }
    }

    @Override
    public String updateEntity(DomainEntity entidadeCliente) {
        if (entidadeCliente instanceof Cliente) {
            Cliente cliente = (Cliente) entidadeCliente;
            String returnString = "";

            returnString += ValidarCamposObrigatoriosCliente.getInstance().process(cliente);

            if (returnString.isEmpty()) {
//                returnString += GerarLog.getInstance().process(cliente);

                returnString += !ClienteDAO.getInstance().update(cliente).equals("sucesso") ? "Erro ao atualizar o cliente.\n" : "";

//                returnString += GerenciarRelacoesLivroCategoriasAutores.getInstance().process(cliente);
            }
            return returnString.isEmpty() ? "Cliente atualizado com sucesso." : returnString;
        } else {
            return "A entidadeCliente fornecida não é um objeto Cliente válido.";
        }
    }

    @Override
    public String deleteEntity(Integer id) {
        return ClienteDAO.getInstance().delete(id);
    }

    public String changePassword(DomainEntity entidadeCliente) {

        if (entidadeCliente instanceof Cliente) {
            Cliente cliente = (Cliente) entidadeCliente;
            String returnString = "";

            if (returnString.isEmpty()) {
                returnString += !ClienteDAO.getInstance().changePassword(cliente).equals("sucesso") ? "Erro ao ativar o cliente.\n" : "";
            }
            return returnString.isEmpty() ? "Senha alterada com sucesso." : returnString;
        } else {
            return "A entidadeCliente fornecida não é um objeto Cliente válido.";
        }
    }

    public String activateCliente(DomainEntity entidadeCliente) {

        if (entidadeCliente instanceof Cliente) {
            Cliente cliente = (Cliente) entidadeCliente;
            String returnString = "";

            if (returnString.isEmpty()) {
                returnString += !ClienteDAO.getInstance().activate(cliente).equals("sucesso") ? "Erro ao ativar o cliente.\n" : "";
            }
            return returnString.isEmpty() ? "Cliente ativado com sucesso." : returnString;
        } else {
            return "A entidadeCliente fornecida não é um objeto Cliente válido.";
        }
    }

    public String inactivateCliente(DomainEntity entidadeCliente) {

        if (entidadeCliente instanceof Cliente) {
            Cliente cliente = (Cliente) entidadeCliente;
            String returnString = "";

            if (returnString.isEmpty()) {
                returnString += !ClienteDAO.getInstance().inactivate(cliente).equals("sucesso") ? "Erro ao inativar o cliente.\n" : "";
            }
            return returnString.isEmpty() ? "Cliente inativado com sucesso." : returnString;
        } else {
            return "A entidadeCliente fornecida não é um objeto Cliente válido.";
        }
    }
}
