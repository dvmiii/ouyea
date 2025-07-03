package com.Grupo10.EcoMarketSpa.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity

public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private int idPedido;
    private int idCliente;
    @OneToMany
    private List<ItemPedido> itemPedidoList = new ArrayList<>();
    private String estado;
    private String fechaPedido;
    private String direccionEnvio;

}
