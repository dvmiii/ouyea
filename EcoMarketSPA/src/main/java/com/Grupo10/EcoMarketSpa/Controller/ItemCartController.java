package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Model.ItemCarrito;
import com.Grupo10.EcoMarketSpa.Service.ItemCartService;
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
@RequestMapping("/ItemCart")
@Tag(name = "ItemCarrito", description = "Servicios de gestión de ítems del carrito para EcoMarket SPA")
public class ItemCartController {

    @Autowired
    ItemCartService itemCartService;

    @GetMapping
    @Operation(summary = "Listar todos los ítems del carrito", description = "Servicio GET para obtener todos los ítems del carrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ítems encontrados"),
            @ApiResponse(responseCode = "404", description = "No se encontraron ítems")
    })
    public ResponseEntity<String> getAllItemCart() {
        String items = itemCartService.getAllItemCarts();
        if (items.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(items);
    }

    @PostMapping
    @Operation(summary = "Agregar un ítem al carrito", description = "Servicio POST para agregar un nuevo ítem al carrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ítem creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemCarrito.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<String> addItemCart(@RequestBody ItemCarrito itemCart) {
        String nuevoItem = itemCartService.addItemCart(itemCart);
        return ResponseEntity.status(201).body(nuevoItem);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un ítem por ID", description = "Servicio GET para obtener un ítem específico del carrito por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ítem encontrado"),
            @ApiResponse(responseCode = "404", description = "Ítem no encontrado")
    })
    public ResponseEntity<String> getItemCartById(@PathVariable int id) {
        String item = itemCartService.getItemCartById(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un ítem del carrito", description = "Servicio DELETE para eliminar un ítem por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ítem eliminado"),
            @ApiResponse(responseCode = "404", description = "Ítem no encontrado")
    })
    public ResponseEntity<String> deleteItemCart(@PathVariable int id) {
        boolean eliminado = Boolean.parseBoolean(itemCartService.deleteItemCart(id));
        if (eliminado) {
            return ResponseEntity.ok("Ítem eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un ítem del carrito", description = "Servicio PUT para actualizar un ítem existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ítem actualizado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemCarrito.class))),
            @ApiResponse(responseCode = "404", description = "Ítem no encontrado")
    })
    public ResponseEntity<String> updateItemCart(@PathVariable int id, @RequestBody ItemCarrito itemCart) {
        String actualizado = itemCartService.updateItemCart(id, itemCart);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
