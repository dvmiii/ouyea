package com.Microservicio.EcoMarketSPA.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Producto {
    private int idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private double precioProducto;
    private int stockProducto;
    private String categoriaProducto;
    private boolean esEcologico;

}
