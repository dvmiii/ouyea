package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Model.Inventario;
import com.Grupo10.EcoMarketSpa.Service.InventarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Inventario")
@Tag(name = "Productos",description = "Servicios de gestion de productos para EcoMarket SPA")
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    @Operation(summary = "Obtener Productos",description = "Servicio GET para obtener todos los productos existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ""),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String getAllInventario() {
        return inventarioService.getAllInventarios();
    }

    @PostMapping
    @Operation(summary = "Obtener Productos",description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class))),
            @ApiResponse(responseCode = "404", description = "")
    })
    public String addInventario(@RequestBody Inventario inventario) {
        return inventarioService.addInventario(inventario);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Productos",description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ""),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String getInventarioById(@PathVariable int id) {
        return inventarioService.getInventarioById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Obtener Productos",description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ""),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String deleteInventario(@PathVariable int id) {
        return inventarioService.deleteInventario(id) ;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Obtener Productos",description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class))),
            @ApiResponse(responseCode = "204", description = "")
    })
    public String updateInventario(@PathVariable int id, @RequestBody Inventario inventario) {
        return inventarioService.updateInventario(id, inventario);
    }
}