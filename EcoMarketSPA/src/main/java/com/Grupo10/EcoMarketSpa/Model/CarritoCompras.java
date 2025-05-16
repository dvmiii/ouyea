package com.Grupo10.EcoMarketSpa.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity

public class CarritoCompras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idcliente;
    private List<ItemCarrito> itemCarritos;
}
