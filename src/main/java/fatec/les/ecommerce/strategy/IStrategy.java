package fatec.les.ecommerce.strategy;

import fatec.les.ecommerce.model.DomainEntity;

public interface IStrategy {
    public String process(DomainEntity entidade);
}
