package fatec.les.ecommerce.fachada;

import fatec.les.ecommerce.dao.CartaoCreditoDAO;
import fatec.les.ecommerce.model.DomainEntity;

import java.util.List;

public class CartaoCreditoFachada implements IFachada {
    private static final CartaoCreditoFachada instance = new CartaoCreditoFachada();

    private CartaoCreditoFachada() {
        // Construtor privado para evitar instanciamento
    }

    public static CartaoCreditoFachada getInstance() {return instance;}

    @Override
    public List<DomainEntity> selectEntities() {
        List<DomainEntity> cartoes = CartaoCreditoDAO.getInstance().select();

        return cartoes;
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
