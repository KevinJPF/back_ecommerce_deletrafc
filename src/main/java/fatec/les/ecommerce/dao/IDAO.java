package fatec.les.ecommerce.dao;

import fatec.les.ecommerce.model.DomainEntity;

import java.util.List;

interface IDAO {
    int insert(DomainEntity entidade);

    String update(DomainEntity entidade);

    String delete(int id);

    List<DomainEntity> select();

    DomainEntity select(int id);
}