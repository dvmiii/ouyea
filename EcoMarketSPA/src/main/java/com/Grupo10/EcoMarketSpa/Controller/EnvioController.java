package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Model.Envio;
import com.Grupo10.EcoMarketSpa.Service.EnvioService;
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
@RequestMapping("/Envio")
@Tag(name = "Envios", description = "Servicios de gestión de envíos para EcoMarket SPA")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    @Operation(summary = "Listar envíos", description = "Servicio GET para obtener todos los envíos existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envíos encontrados"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<String> getAllEnvios() {
        return ResponseEntity.ok(envioService.getAllEnvios());
    }

    @PostMapping
    @Operation(summary = "Crear un envío", description = "Servicio POST para crear un nuevo envío")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Envío creado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Envio.class))),
            @ApiResponse(responseCode = "400", description = "Error al crear el envío")
    })
    public ResponseEntity<String> addEnvio(@RequestBody Envio envio) {
        String nuevoEnvio = envioService.addEnvio(envio);
        return ResponseEntity.status(201).body(nuevoEnvio);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener envío por ID", description = "Servicio GET para obtener un envío específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envío encontrado"),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado")
    })
    public ResponseEntity<? extends Object> getEnvioById(@PathVariable int id) {
        String envio = envioService.getEnvioById(id);
        return envio != null ? ResponseEntity.ok(envio) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un envío", description = "Servicio DELETE para eliminar un envío por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envío eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado")
    })
    public ResponseEntity<String> deleteEnvio(@PathVariable int id) {
        boolean eliminado = Boolean.parseBoolean(envioService.deleteEnvio(id));
        return eliminado ? ResponseEntity.ok("Envío eliminado") : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un envío", description = "Servicio PUT para actualizar un envío existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envío actualizado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Envio.class))),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado")
    })
    public ResponseEntity<? extends Object> updateEnvio(@PathVariable int id, @RequestBody Envio envio) {
        String envioActualizado = envioService.updateEnvio(id, envio);
        return envioActualizado != null ? ResponseEntity.ok(envioActualizado) : ResponseEntity.notFound().build();
    }
}
