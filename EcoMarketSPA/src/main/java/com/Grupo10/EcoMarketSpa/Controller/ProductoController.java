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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Producto")
@Tag(name = "Productos",description = "Servicios de gestion de productos para EcoMarket SPA")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(summary = "Obtener Productos",description = "Servicio GET para obtener todos los productos existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna los Productos"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String getAllProductos(){
        return productoService.getAllProducto();
    }

    @PostMapping
    @Operation(summary = "Agregar Producto",description = "Servicio POST para crear los productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Producto Creado",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "204",description = "No hay contenido en la solicitud")
    })
    public String addProducto(@RequestBody Producto producto){
        return productoService.addProducto(producto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Productos por ID",description = "Servicio GET para obtener los productos segun el ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retona un Producto"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String getProductoById(@PathVariable int id){
        return productoService.getProductoById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Productos por ID",description = "Servicio DELETE para eliminar los productos existentes segun el ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna producto"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String deleteProducto(@PathVariable int id){
        return productoService.deleteProducto(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Productos",description = "Servicio PUT para actualizar los datos de los productos existentes segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Producto Actualizado",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "204",description = "No hay contenido en la solicitud")
    })
    public String updateProducto(@PathVariable int id, @RequestBody Producto producto){
        return productoService.updateProducto(id, producto);
    }
}
