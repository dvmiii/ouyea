package com.Grupo10.EcoMarketSpa.Repository;

import com.Grupo10.EcoMarketSpa.Model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvioRepository extends JpaRepository<Envio,Integer> {
}
