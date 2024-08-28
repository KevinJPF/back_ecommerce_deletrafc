package fatec.les.ecommerce.fachada;

import fatec.les.ecommerce.dao.EnderecoDAO;
import fatec.les.ecommerce.model.DomainEntity;

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
        return "";
    }

    @Override
    public String updateEntity(DomainEntity entity) {
        return "";
    }

    @Override
    public String deleteEntity(Integer id) {
        return "";
    }
}
