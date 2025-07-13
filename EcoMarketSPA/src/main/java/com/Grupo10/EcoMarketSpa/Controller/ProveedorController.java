package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Assemblers.ProveedorModelAssembler;
import com.Grupo10.EcoMarketSpa.Model.Proveedor;
import com.Grupo10.EcoMarketSpa.Service.ProveedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proveedor")
@Tag(name = "Proveedor",description = "Servicios de gestion de proveedor para EcoMarket SPA")
public class ProveedorController {

    private final ProveedorService proveedorService;
    private final ProveedorModelAssembler assembler;

    @Autowired
    public ProveedorController(ProveedorService proveedorService, ProveedorModelAssembler assembler) {
        this.proveedorService = proveedorService;
        this.assembler = assembler;
    }

    @GetMapping
    @Operation(summary = "Obtener Proveedor",description = "Servicio GET para obtener todos los proveedores existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna los Proveedores"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String getAllProveedores() {
        return proveedorService.getAllProveedores();  
    }

    @PostMapping
    @Operation(summary = "Agregar Proveedor",description = "Servicio POST para crear los proveedores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Proveedor Agregado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Proveedor.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public String addProveedor(@RequestBody Proveedor proveedor) {
        return proveedorService.addProveedor(proveedor);  
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Proveedor",description = "Servicio GET para obtener los proveedores por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Proveedor Retornado"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String getProveedorById(@PathVariable int id) {
        return proveedorService.getProveedorById(id);  
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "ELiminar Proveedor",description = "Servicio DELETE para eliminar los proveedores por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Proveedor Eliminado"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String deleteProveedor(@PathVariable int id) {
        return proveedorService.deleteProveedor(id); 
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Proveedor",description = "Servicio PUT para actualizar los proveedores por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Proveedor Actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Proveedor.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public String updateProveedor(@PathVariable int id, @RequestBody Proveedor proveedor) {
        return proveedorService.updateProveedor(id, proveedor); 
    }
}


