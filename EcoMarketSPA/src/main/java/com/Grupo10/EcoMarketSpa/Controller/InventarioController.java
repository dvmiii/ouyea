package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Model.Inventario;
import com.Grupo10.EcoMarketSpa.Service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Inventario")
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public String getAllInventario() {
        return inventarioService.getAllInventarios();
    }

    @PostMapping
    public String addInventario(@RequestBody Inventario inventario) {
        return inventarioService.addInventario(inventario);
    }

    @GetMapping("/{id}")
    public String getInventarioById(@PathVariable int id) {
        return inventarioService.getInventarioById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteInventario(@PathVariable int id) {
        return inventarioService.deleteInventario(id) ;
    }

    @PutMapping("/{id}")
    public String updateInventario(@PathVariable int id, @RequestBody Inventario inventario) {
        return inventarioService.updateInventario(id, inventario);
    }
}