package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Model.Producto;
import com.Grupo10.EcoMarketSpa.Service.ProductoService;
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
@RequestMapping("/Producto")
@Tag(name = "Producto", description = "Servicios de gestión de productos para EcoMarket SPA")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(summary = "Listar todos los productos", description = "Servicio GET para obtener todos los productos existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos encontrados"),
            @ApiResponse(responseCode = "204", description = "No se encontraron productos")
    })
    public ResponseEntity<String> getAllProductos() {
        String productos = productoService.getAllProducto();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    @Operation(summary = "Agregar un producto", description = "Servicio POST para crear un nuevo producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<String> addProducto(@RequestBody Producto producto) {
        String nuevoProducto = productoService.addProducto(producto);
        return ResponseEntity.status(201).body(nuevoProducto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID", description = "Servicio GET para obtener un producto específico según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<String> getProductoById(@PathVariable int id) {
        String producto = productoService.getProductoById(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto por ID", description = "Servicio DELETE para eliminar un producto existente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<String> deleteProducto(@PathVariable int id) {
        boolean eliminado = Boolean.parseBoolean(productoService.deleteProducto(id));
        if (eliminado) {
            return ResponseEntity.ok("Producto eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto por ID", description = "Servicio PUT para actualizar los datos de un producto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<String> updateProducto(@PathVariable int id, @RequestBody Producto producto) {
        String actualizado = productoService.updateProducto(id, producto);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
