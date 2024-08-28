package fatec.les.ecommerce.fachada;

import fatec.les.ecommerce.dao.ClienteDAO;
import fatec.les.ecommerce.model.Cliente;
import fatec.les.ecommerce.model.DomainEntity;

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

//            returnString += ValidarCamposObrigatorios.getInstance().process(entidadeLivro);
//            returnString += ValidarAutoresLivro.getInstance().process(entidadeLivro);
//            returnString += ValidarCategoriasLivro.getInstance().process(entidadeLivro);
//            returnString += ValidarMargemLucro.getInstance().process(entidadeLivro);
//            returnString += ValidarExistencia.getInstance().process(entidadeLivro);

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

//            returnString += ValidarCamposObrigatorios.getInstance().process(entidadeCliente);
//            returnString += ValidarAutoresLivro.getInstance().process(entidadeCliente);
//            returnString += ValidarCategoriasLivro.getInstance().process(entidadeCliente);
//            returnString += ValidarMargemLucro.getInstance().process(entidadeCliente);

            if (returnString.isEmpty()) {
//                returnString += GerarLog.getInstance().process(cliente);

                returnString += !ClienteDAO.getInstance().update(cliente).equals("sucesso") ? "Erro ao atualizar o cliente.\n" : "";

//                returnString += GerenciarRelacoesLivroCategoriasAutores.getInstance().process(cliente);
            }
            return returnString.isEmpty() ? "Cliente atualizado com sucesso." : returnString;
        } else {
            return "A entidadeCliente fornecida não é um objeto Livro válido.";
        }
    }

    @Override
    public String deleteEntity(Integer id) {
        return ClienteDAO.getInstance().delete(id);
    }
}
