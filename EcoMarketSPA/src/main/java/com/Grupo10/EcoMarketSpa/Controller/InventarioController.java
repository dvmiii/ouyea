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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Inventario")
@Tag(name = "Inventario", description = "Servicios de gestión de inventario para EcoMarket SPA")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    @Operation(summary = "Listar inventario", description = "Servicio GET para obtener todos los productos del inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos encontrados"),
            @ApiResponse(responseCode = "204", description = "Inventario vacío")
    })
    public ResponseEntity<String> getAllInventario() {
        String inventarioList = inventarioService.getAllInventarios();
        if (inventarioList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(inventarioList);
    }

    @PostMapping
    @Operation(summary = "Agregar producto al inventario", description = "Servicio POST para agregar un nuevo producto al inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto agregado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<String> addInventario(@RequestBody Inventario inventario) {
        String nuevoInventario = inventarioService.addInventario(inventario);
        return ResponseEntity.status(201).body(nuevoInventario);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto del inventario por ID", description = "Servicio GET para obtener un producto específico del inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<String> getInventarioById(@PathVariable int id) {
        String inventario = inventarioService.getInventarioById(id);
        if (inventario != null) {
            return ResponseEntity.ok(inventario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto del inventario", description = "Servicio DELETE para eliminar un producto del inventario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<String> deleteInventario(@PathVariable int id) {
        boolean eliminado = Boolean.parseBoolean(inventarioService.deleteInventario(id));
        if (eliminado) {
            return ResponseEntity.ok("Producto eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto del inventario", description = "Servicio PUT para actualizar un producto existente en el inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<String> updateInventario(@PathVariable int id, @RequestBody Inventario inventario) {
        String actualizado = inventarioService.updateInventario(id, inventario);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
