package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Model.Permiso;
import com.Grupo10.EcoMarketSpa.Service.PermisoService;
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
@RequestMapping("/Permiso")
@Tag(name = "Permiso", description = "Servicios de gestión de permisos para EcoMarket SPA")
public class PermisoController {

    @Autowired
    private PermisoService permisoService;

    @GetMapping
    @Operation(summary = "Listar todos los permisos", description = "Servicio GET para obtener todos los permisos existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permisos encontrados"),
            @ApiResponse(responseCode = "204", description = "No se encontraron permisos")
    })
    public ResponseEntity<String> getAllPermisos() {
        String permisos = permisoService.getAllPermisos();
        if (permisos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(permisos);
    }

    @PostMapping
    @Operation(summary = "Agregar un permiso", description = "Servicio POST para crear un nuevo permiso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Permiso creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Permiso.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<String> addPermiso(@RequestBody Permiso permiso) {
        String nuevoPermiso = permisoService.addPermisos(permiso);
        return ResponseEntity.status(201).body(nuevoPermiso);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener permiso por ID", description = "Servicio GET para obtener un permiso específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permiso encontrado"),
            @ApiResponse(responseCode = "404", description = "Permiso no encontrado")
    })
    public ResponseEntity<String> getPermisoById(@PathVariable int id) {
        String permiso = permisoService.getPermisosById(id);
        if (permiso != null) {
            return ResponseEntity.ok(permiso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar permiso", description = "Servicio DELETE para eliminar un permiso por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permiso eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Permiso no encontrado")
    })
    public ResponseEntity<String> deletePermiso(@PathVariable int id) {
        boolean eliminado = Boolean.parseBoolean(permisoService.deletePermiso(id));
        if (eliminado) {
            return ResponseEntity.ok("Permiso eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar permiso", description = "Servicio PUT para actualizar un permiso existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permiso actualizado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Permiso.class))),
            @ApiResponse(responseCode = "404", description = "Permiso no encontrado")
    })
    public ResponseEntity<String> updatePermiso(@PathVariable int id, @RequestBody Permiso permiso) {
        String actualizado = permisoService.updatePermiso(id, permiso);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
