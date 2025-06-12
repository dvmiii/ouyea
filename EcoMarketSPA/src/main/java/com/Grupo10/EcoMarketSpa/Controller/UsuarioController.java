package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Assemblers.UserModelAssembler;
import com.Grupo10.EcoMarketSpa.Model.Usuario;
import com.Grupo10.EcoMarketSpa.Service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Usuario")
@Tag(name = "Usuarios",description = "Servicios de gestion de usuarios para EcoMarket SPA")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Obtener Usuarios",description = "Servicio GET para obtener todos los usuarios existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna los Usuarios"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String getAllUser(){
        return usuarioService.getAllUser();
    }

    @PostMapping
    @Operation(summary = "Obtener Productos",description = "Servicio POST para crear los usuarioss")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Usuario Creado",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "204",description = "No hay contenido en la solicitud")
    })
    public String addUser(@RequestBody Usuario usuario){
        return usuarioService.addUser(usuario);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Productos",description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ""),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String getUserById(@PathVariable int id){
        return usuarioService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Obtener Productos",description = "Servicio DELETE para  los usuarios existentes segun el ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ""),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String deleteUser(@PathVariable int id){
        return usuarioService.deleteUser(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Obtener Productos",description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Usuario Actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "204",description = "No hay contenido en la solicitud")
    })
    public String updateUser(@PathVariable int id, @RequestBody Usuario usuario){
        return usuarioService.updateUser(id, usuario);
    }
}
