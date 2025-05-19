package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Model.Permiso;
import com.Grupo10.EcoMarketSpa.Service.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Permiso")
public class PermisoController {

    @Autowired
    private PermisoService permisoService;

    @GetMapping
    public String getAllPermisos() {
        return permisoService.getAllPermisos();
    }

    @PostMapping
    public String addPermiso(@RequestBody Permiso permiso) {
        return permisoService.addPermisos(permiso);
    }

    @GetMapping("/{id}")
    public String getPermisoById(@PathVariable int id) {
        return permisoService.getPermisosById(id);
    }

    @DeleteMapping("/{id}")
    public String deletePermiso(@PathVariable int id) {
        return permisoService.deletePermiso(id);
    }

    @PutMapping("/{id}")
    public String updatePermiso(@PathVariable int id, @RequestBody Permiso permiso) {
        return permisoService.updatePermiso(id, permiso);
    }
}
