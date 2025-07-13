package com.Grupo10.EcoMarketSpa.Controller;


import com.Grupo10.EcoMarketSpa.Assemblers.RolModelAssembler;
import com.Grupo10.EcoMarketSpa.Model.Rol;
import com.Grupo10.EcoMarketSpa.Service.RolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/Rol")
@Tag(name = "Rol",description = "Servicios de gestion de Rol para EcoMarket SPA")
public class RolController {

    @Autowired
    private RolService rolService;

    @Autowired
    private RolModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener Rol",description = "Servicio GET para obtener todos los roles existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna los Roles"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public CollectionModel<EntityModel<Rol>> getAllRol(){
        List<Rol> usuariosList = (List<Rol>) rolService.getAllRol();
        List<EntityModel<Rol>> usuarios = usuariosList
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(usuarios, linkTo(methodOn(RolController.class).getAllRol()).withSelfRel());
    }

    @PostMapping
    @Operation(summary = "Agregar Rol",description = "Servicio POST para crear los roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Rol Creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Rol.class))),
            @ApiResponse(responseCode = "204",description = "No hay contenido en la solicitud")
    })
    public String addRol(@RequestBody Rol rol){
        return rolService.addRol(rol);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Rol",description = "Servicio GET para Obtener los roles  por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Rol Retornado"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String getRolById(@PathVariable int id){
        return rolService.getRolById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Rol",description = "Servicio DELETE para los roles existentes segun el ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Rol Eliminado"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String deleteRol(@PathVariable int id){
        return rolService.deleteRol(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Rol",description = "Servicio PUT para los roles existentes segun el ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Rol Actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Rol.class))),
            @ApiResponse(responseCode = "204",description = "No hay contenido en la solicitud")
    })
    public String updateRol(@PathVariable int id, @RequestBody Rol rol){
        return rolService.updateRol(id, rol);
    }
}
