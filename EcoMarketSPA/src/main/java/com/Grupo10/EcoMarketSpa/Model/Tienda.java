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

public class Tienda {
    private int idTienda;
    private String nombre;
    private String ubicacion;
    private String horario_apertura;
    private String horario_cierre;
    private List<Usuario> usuarios;
}
