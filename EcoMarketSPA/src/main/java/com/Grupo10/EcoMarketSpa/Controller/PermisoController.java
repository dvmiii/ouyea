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
@RequestMapping("/Permiso")
@Tag(name = "Productos",description = "Servicios de gestion de productos para EcoMarket SPA")
public class PermisoController {

    @Autowired
    private PermisoService permisoService;

    
    @Autowired
    private PermisoModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener Productos",description = "Servicio GET para obtener todos los productos existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ""),
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
    @Operation(summary = "Obtener Productos",description = "Servicio POST para crear los productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Permiso.class))),
            @ApiResponse(responseCode = "204", description = "")
    })
    public String addPermiso(@RequestBody Permiso permiso) {
        return permisoService.addPermisos(permiso);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Productos",description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ""),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String getPermisoById(@PathVariable int id) {
        return permisoService.getPermisosById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Obtener Productos",description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ""),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String deletePermiso(@PathVariable int id) {
        return permisoService.deletePermiso(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Obtener Productos",description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Permiso.class))),
            @ApiResponse(responseCode = "204", description = "")
    })
    public String updatePermiso(@PathVariable int id, @RequestBody Permiso permiso) {
        return permisoService.updatePermiso(id, permiso);
    }
}
