package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Model.Producto;
import com.Grupo10.EcoMarketSpa.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String getAllProductos(){
        return productoService.getAllProducto();
    }

    @PostMapping
    public String addProducto(@RequestBody Producto producto){
        return productoService.addProducto(producto);
    }

    @GetMapping("/{id}")
    public String getProductoById(@PathVariable int id){
        return productoService.getProductoById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable int id){
        return productoService.deleteProducto(id);
    }

    @PutMapping("/{id}")
    public String updateProducto(@PathVariable int id, @RequestBody Producto producto){
        return productoService.updateProducto(id, producto);
    }
}
