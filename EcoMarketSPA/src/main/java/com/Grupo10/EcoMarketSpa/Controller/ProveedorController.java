package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Model.Proveedor;
import com.Grupo10.EcoMarketSpa.Service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public String getAllProveedores() {
        return proveedorService.getAllProveedores();  // ✅ Nombre correcto
    }

    @PostMapping
    public String addProveedor(@RequestBody Proveedor proveedor) {
        return proveedorService.addProveedor(proveedor);  // ✅
    }

    @GetMapping("/{id}")
    public String getProveedorById(@PathVariable int id) {
        return proveedorService.getProveedorById(id);  // ✅
    }

    @DeleteMapping("/{id}")
    public String deleteProveedor(@PathVariable int id) {
        return proveedorService.deleteProveedor(id);  // ✅
    }

    @PutMapping("/{id}")
    public String updateProveedor(@PathVariable int id, @RequestBody Proveedor proveedor) {
        return proveedorService.updateProveedor(id, proveedor);  // ✅
    }
}


