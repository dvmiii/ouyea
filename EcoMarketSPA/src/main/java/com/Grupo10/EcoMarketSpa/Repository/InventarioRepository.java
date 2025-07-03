package com.Grupo10.EcoMarketSpa.Repository;

import com.Grupo10.EcoMarketSpa.Model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario,Integer> {
}
