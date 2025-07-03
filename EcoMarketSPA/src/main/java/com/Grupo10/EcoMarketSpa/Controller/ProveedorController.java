package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Model.Proveedor;
import com.Grupo10.EcoMarketSpa.Service.ProveedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Proveedor")
@Tag(name = "Proveedores", description = "Servicios de gestión de proveedores para EcoMarket SPA")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    // Obtener todos los proveedores
    @GetMapping
    @Operation(summary = "Listar Proveedores", description = "Servicio GET para obtener todos los proveedores registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de proveedores obtenida correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron proveedores")
    })
    public ResponseEntity<String> getAllProveedores() {
        String proveedores = proveedorService.getAllProveedores();
        if (proveedores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(proveedores, HttpStatus.OK);
    }

    // Crear un proveedor
    @PostMapping
    @Operation(summary = "Crear Proveedor", description = "Servicio POST para registrar un nuevo proveedor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Proveedor creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Proveedor.class))),
            @ApiResponse(responseCode = "400", description = "Error en los datos proporcionados")
    })
    public ResponseEntity<String> addProveedor(@RequestBody Proveedor proveedor) {
        String nuevoProveedor = proveedorService.addProveedor(proveedor);
        return new ResponseEntity<>(nuevoProveedor, HttpStatus.CREATED);
    }

    // Obtener un proveedor por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar Proveedor por ID", description = "Servicio GET para obtener un proveedor específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor encontrado"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    public ResponseEntity<String> getProveedorById(@PathVariable int id) {
        String proveedor = proveedorService.getProveedorById(id);
        if (proveedor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(proveedor, HttpStatus.OK);
    }

    // Eliminar proveedor
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Proveedor", description = "Servicio DELETE para eliminar un proveedor por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    public ResponseEntity<Void> deleteProveedor(@PathVariable int id) {
        boolean eliminado = Boolean.parseBoolean(proveedorService.deleteProveedor(id));
        if (!eliminado) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Actualizar proveedor
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Proveedor", description = "Servicio PUT para actualizar un proveedor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<String> updateProveedor(@PathVariable int id, @RequestBody Proveedor proveedor) {
        String proveedorActualizado = proveedorService.updateProveedor(id, proveedor);
        if (proveedorActualizado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(proveedorActualizado, HttpStatus.OK);
    }
}


