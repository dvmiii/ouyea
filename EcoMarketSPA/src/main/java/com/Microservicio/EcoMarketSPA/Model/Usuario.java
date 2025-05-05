package com.Microservicio.EcoMarketSPA.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String email;
    private String password;
    private String rol;

}
