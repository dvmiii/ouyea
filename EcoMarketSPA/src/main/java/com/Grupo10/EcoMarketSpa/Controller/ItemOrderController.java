package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Model.ItemPedido;
import com.Grupo10.EcoMarketSpa.Service.ItemOrderService;
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
@RequestMapping("/ItemOrder")
@Tag(name = "ItemPedido", description = "Servicios de gestión de ítems de pedidos para EcoMarket SPA")
public class ItemOrderController {

    @Autowired
    private ItemOrderService itemOrderService;

    @GetMapping
    @Operation(summary = "Listar todos los ítems de pedidos", description = "Servicio GET para obtener todos los ítems de pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ítems encontrados"),
            @ApiResponse(responseCode = "204", description = "No hay ítems en el pedido")
    })
    public ResponseEntity<String> getAllItemOrder() {
        String items = itemOrderService.getAllItemOrders();
        if (items.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(items);
    }

    @PostMapping
    @Operation(summary = "Agregar ítem al pedido", description = "Servicio POST para agregar un ítem a un pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ítem agregado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemPedido.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<String> addItemOrder(@RequestBody ItemPedido itemOrder) {
        String nuevoItem = itemOrderService.addItmOrder(itemOrder);
        return ResponseEntity.status(201).body(nuevoItem);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener ítem de pedido por ID", description = "Servicio GET para obtener un ítem de pedido específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ítem encontrado"),
            @ApiResponse(responseCode = "404", description = "Ítem no encontrado")
    })
    public ResponseEntity<String> getItemOrderById(@PathVariable int id) {
        String item = itemOrderService.getItemOrderById(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar ítem de pedido", description = "Servicio DELETE para eliminar un ítem de pedido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ítem eliminado"),
            @ApiResponse(responseCode = "404", description = "Ítem no encontrado")
    })
    public ResponseEntity<String> deleteItemOrder(@PathVariable int id) {
        boolean eliminado = Boolean.parseBoolean(itemOrderService.deleteItemOrder(id));
        if (eliminado) {
            return ResponseEntity.ok("Ítem eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar ítem de pedido", description = "Servicio PUT para actualizar un ítem de pedido existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ítem actualizado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemPedido.class))),
            @ApiResponse(responseCode = "404", description = "Ítem no encontrado")
    })
    public ResponseEntity<String> updateItemOrder(@PathVariable int id, @RequestBody ItemPedido itemOrder) {
        String actualizado = itemOrderService.updateItemOrder(id, itemOrder);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
