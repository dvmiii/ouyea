package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Assemblers.PermisoModelAssembler;
import com.Grupo10.EcoMarketSpa.Model.Permiso;
import com.Grupo10.EcoMarketSpa.Service.PermisoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permiso")
@Tag(name = "Permisos",description = "Servicios de gestion de permisos para EcoMarket SPA")
public class PermisoController {

    @Autowired
    private PermisoService permisoService;
    
    @Autowired
    private PermisoModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener Permisos",description = "Servicio GET para obtener todos los permisos existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retornar los Permisos"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
   public CollectionModel<EntityModel<Permiso>> getAllPermisos() {
        List<Permiso> permisoList = (List<Permiso>) permisoService.getAllPermisos();
        List<EntityModel<Permiso>> permisos = permisoList
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(permisos, linkTo(methodOn(PermisoController.class).getAllPermisos()).withSelfRel());
    }

    @PostMapping
    @Operation(summary = "Agregar Permisos",description = "Servicio POST para crear los permisos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Permisos Agregados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Permiso.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public String addPermiso(@RequestBody Permiso permiso) {
        return permisoService.addPermisos(permiso);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Permisos",description = "Servicio GET para obtener los permisos por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna un Permiso"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String getPermisoById(@PathVariable int id) {
        return permisoService.getPermisosById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Permisos",description = "Servicio DELETE para eliminar los permisos por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Permiso Eliminado"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String deletePermiso(@PathVariable int id) {
        return permisoService.deletePermiso(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Permisos",description = "Servicio PUT para actualizar los permisos por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Permiso Actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Permiso.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public String updatePermiso(@PathVariable int id, @RequestBody Permiso permiso) {
        return permisoService.updatePermiso(id, permiso);
    }
}
