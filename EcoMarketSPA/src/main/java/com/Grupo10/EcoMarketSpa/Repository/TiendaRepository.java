package com.Grupo10.EcoMarketSpa.Repository;

import com.Grupo10.EcoMarketSpa.Model.RutaEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiendaRepository extends JpaRepository<RutaEntrega,Integer> {
}
