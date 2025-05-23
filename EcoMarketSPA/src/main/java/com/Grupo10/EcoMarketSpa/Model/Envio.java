package com.Grupo10.EcoMarketSpa.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity

public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEnvio;
    private String estadoEnvio;
    private String fechaEnvio;
    private String fechaEntregaEstimada;
    private String ruta;
}
