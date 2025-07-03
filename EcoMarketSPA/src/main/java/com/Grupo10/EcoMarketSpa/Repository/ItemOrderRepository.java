package com.Grupo10.EcoMarketSpa.Repository;

import com.Grupo10.EcoMarketSpa.Model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOrderRepository extends JpaRepository<ItemPedido,Integer> {
}
