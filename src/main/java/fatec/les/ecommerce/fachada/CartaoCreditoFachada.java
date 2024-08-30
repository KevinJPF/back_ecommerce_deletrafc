package fatec.les.ecommerce.fachada;

import fatec.les.ecommerce.model.DomainEntity;
import fatec.les.ecommerce.model.CartaoCredito;
import fatec.les.ecommerce.dao.CartaoCreditoDAO;
import fatec.les.ecommerce.strategy.ValidarCamposObrigatoriosCartaoCredito;

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
        if (entity instanceof CartaoCredito) {
            CartaoCredito cartao = (CartaoCredito) entity;
            String returnString = "";

            returnString += ValidarCamposObrigatoriosCartaoCredito.getInstance().process(cartao);

            if (returnString.isEmpty()) {
                int id = CartaoCreditoDAO.getInstance().insert(cartao);

                if (id <= 0) {
                    returnString += "O cartao de crédito não foi adicionado no banco de dados.\n";
                }
            }
            return returnString.isEmpty() ? "Cartao de crédito inserido com sucesso." : returnString;
        } else {
            return "A entidadeCartaoCredito fornecida não é um objeto cartao de crédito válido.";
        }
    }

    @Override
    public String updateEntity(DomainEntity entity) {
        if (entity instanceof CartaoCredito) {
            CartaoCredito cartao = (CartaoCredito) entity;
            String returnString = "";

            returnString += ValidarCamposObrigatoriosCartaoCredito.getInstance().process(cartao);

            if (returnString.isEmpty()) {
                returnString += !CartaoCreditoDAO.getInstance().update(cartao).equals("sucesso") ? "Erro ao atualizar o cartao de crédito.\n" : "";
            }
            return returnString.isEmpty() ? "Cartao de crédito atualizado com sucesso." : returnString;
        } else {
            return "A entidadeCartaoCredito fornecida não é um objeto cartao de crédito válido.";
        }
    }

    @Override
    public String deleteEntity(Integer id) {
        return CartaoCreditoDAO.getInstance().delete(id);
    }
}
