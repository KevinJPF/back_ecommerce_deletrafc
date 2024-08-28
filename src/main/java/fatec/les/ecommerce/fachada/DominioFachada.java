package fatec.les.ecommerce.fachada;

import fatec.les.ecommerce.dao.DominioDAO;
import fatec.les.ecommerce.model.DomainEntity;

import java.util.List;
import java.util.Map;

public class DominioFachada implements IFachada {
    private static final DominioFachada instance = new DominioFachada();

    private DominioFachada() {
        // Construtor privado para evitar instanciamento
    }

    public static DominioFachada getInstance() {return instance;}

    @Override
    public List<DomainEntity> selectEntities() {
        List<DomainEntity> dominios = DominioDAO.getInstance().select();

        return dominios;
    }

    public Map<String, List<DomainEntity>> selectGambiarra() {
        Map<String, List<DomainEntity>> dominios = DominioDAO.getInstance().selectGambiarra();

        return dominios;
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
