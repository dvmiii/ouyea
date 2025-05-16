package com.Grupo10.EcoMarketSpa.Model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity

public class ProductoInventario {
    private String producto;
    private int cantidad;
    private String fecha_actualizacion;

}
