package com.Grupo10.EcoMarketSpa.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity

public class RutaEntrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRutaEntrega;


    private List<RutaEntrega> rutaEntrega;
    private String distancia_total;
    private String tiempo_estimado;

}
