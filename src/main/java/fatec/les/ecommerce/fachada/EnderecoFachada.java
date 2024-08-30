package fatec.les.ecommerce.fachada;

import fatec.les.ecommerce.model.DomainEntity;
import fatec.les.ecommerce.model.Endereco;
import fatec.les.ecommerce.dao.EnderecoDAO;
import fatec.les.ecommerce.strategy.ValidarCamposObrigatoriosEndereco;

import java.util.List;

public class EnderecoFachada implements IFachada {
    private static final EnderecoFachada instance = new EnderecoFachada();

    private EnderecoFachada() {
        // Construtor privado para evitar instanciamento
    }

    public static EnderecoFachada getInstance() {return instance;}

    @Override
    public List<DomainEntity> selectEntities() {
        List<DomainEntity> enderecos = EnderecoDAO.getInstance().select();

        return enderecos;
    }

    @Override
    public DomainEntity selectEntity(Integer id) {
        return null;
    }

    @Override
    public String insertEntity(DomainEntity entity) {
        if (entity instanceof Endereco) {
            Endereco endereco = (Endereco) entity;
            String returnString = "";

            returnString += ValidarCamposObrigatoriosEndereco.getInstance().process(endereco);

            if (returnString.isEmpty()) {
                int id = EnderecoDAO.getInstance().insert(endereco);

                if (id <= 0) {
                    returnString += "O endereco não foi adicionado no banco de dados.\n";
                }
            }
            return returnString.isEmpty() ? "Endereco inserido com sucesso." : returnString;
        } else {
            return "A entidadeEndereco fornecida não é um objeto endereco válido.";
        }
    }

    @Override
    public String updateEntity(DomainEntity entity) {
        if (entity instanceof Endereco) {
            Endereco endereco = (Endereco) entity;
            String returnString = "";

            returnString += ValidarCamposObrigatoriosEndereco.getInstance().process(endereco);

            if (returnString.isEmpty()) {
                returnString += !EnderecoDAO.getInstance().update(endereco).equals("sucesso") ? "Erro ao atualizar o endereco.\n" : "";
            }
            return returnString.isEmpty() ? "Endereco atualizado com sucesso." : returnString;
        } else {
            return "A entidadeEndereco fornecida não é um objeto endereco válido.";
        }
    }

    @Override
    public String deleteEntity(Integer id) {
        return EnderecoDAO.getInstance().delete(id);
    }
}
