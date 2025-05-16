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

public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private int idPedido;
    private int idCliente;
    private List<ItemPedido> itemPedidoList;
    private String estado;
    private String fecha_pedido;
    private String direccion_envio;

}
