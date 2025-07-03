package com.Grupo10.EcoMarketSpa.Repository;

import com.Grupo10.EcoMarketSpa.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
}
