package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Model.MetodoPago;
import com.Grupo10.EcoMarketSpa.Service.MetodoPagoService;
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
@RequestMapping("/MetodoPago")
@Tag(name = "MetodoPago", description = "Servicios de gestión de métodos de pago para EcoMarket SPA")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @GetMapping
    @Operation(summary = "Listar todos los métodos de pago", description = "Servicio GET para obtener todos los métodos de pago")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Métodos de pago encontrados"),
            @ApiResponse(responseCode = "204", description = "No se encontraron métodos de pago")
    })
    public ResponseEntity<String> getAllMetodoPagos() {
        String metodos = metodoPagoService.getAllMetodosPagos();
        if (metodos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(metodos);
    }

    @PostMapping
    @Operation(summary = "Agregar un nuevo método de pago", description = "Servicio POST para agregar un método de pago")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Método de pago creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MetodoPago.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<String> addMetodoPago(@RequestBody MetodoPago metodoPago) {
        String nuevoMetodo = metodoPagoService.addMetodoPago(metodoPago);
        return ResponseEntity.status(201).body(nuevoMetodo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener método de pago por ID", description = "Servicio GET para obtener un método de pago específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Método de pago encontrado"),
            @ApiResponse(responseCode = "404", description = "Método de pago no encontrado")
    })
    public ResponseEntity<String> getMetodoPagoById(@PathVariable int id) {
        String metodo = metodoPagoService.getMetodoPagoById(id);
        if (metodo != null) {
            return ResponseEntity.ok(metodo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar método de pago", description = "Servicio DELETE para eliminar un método de pago por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Método de pago eliminado"),
            @ApiResponse(responseCode = "404", description = "Método de pago no encontrado")
    })
    public ResponseEntity<String> deleteMetodoPago(@PathVariable int id) {
        boolean eliminado = Boolean.parseBoolean(metodoPagoService.deleteMetodoPago(id));
        if (eliminado) {
            return ResponseEntity.ok("Método de pago eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar método de pago", description = "Servicio PUT para actualizar un método de pago existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Método de pago actualizado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MetodoPago.class))),
            @ApiResponse(responseCode = "404", description = "Método de pago no encontrado")
    })
    public ResponseEntity<String> updateMetodoPago(@PathVariable int id, @RequestBody MetodoPago metodoPago) {
        String actualizado = metodoPagoService.updateMetodoPago(id, metodoPago);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
