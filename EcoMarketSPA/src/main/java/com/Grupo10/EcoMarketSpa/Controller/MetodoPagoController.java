package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Model.MetodoPago;
import com.Grupo10.EcoMarketSpa.Service.MetodoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/MetodoPago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @GetMapping
    public String getAllMetodoPagos() {
        return metodoPagoService.getAllMetodosPagos();
    }

    @PostMapping
    public String addMetodoPago(@RequestBody MetodoPago metodoPago) {
        return metodoPagoService.addMetodoPago(metodoPago);
    }

    @GetMapping("/{id}")
    public String getMetodoPagoById(@PathVariable int id) {
        return metodoPagoService.getMetodoPagoById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteMetodoPago(@PathVariable int id) {
        return metodoPagoService.deleteMetodoPago(id);
    }

    @PutMapping("/{id}")
    public String updateMetodoPago(@PathVariable int id, @RequestBody MetodoPago metodoPago) {
        return metodoPagoService.updateMetodoPago(id, metodoPago);
    }
}
