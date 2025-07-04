package com.Grupo10.EcoMarketSpa.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity

public class RutaEntrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRutaEntrega;
//Falta la Creacion de Ubicacion    private List<Ubicacion> rutaEntrega = new ArrayList<>();
    private String distanciaTotal;
    private String tiempoEstimado;

}
