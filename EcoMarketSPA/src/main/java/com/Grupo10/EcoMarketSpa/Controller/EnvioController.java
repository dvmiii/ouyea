package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Model.Envio;
import com.Grupo10.EcoMarketSpa.Service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Envio")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public String getAllEnvios() {
        return envioService.getAllEnvios();
    }

    @PostMapping
    public String addEnvio(@RequestBody Envio envio) {
        return envioService.addEnvio(envio);
    }

    @GetMapping("/{id}")
    public String getEnvioById(@PathVariable int id) {
        return envioService.getEnvioById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteEnvio(@PathVariable int id) {
        return envioService.deleteEnvio(id);
    }

    @PutMapping("/{id}")
    public String updateEnvio(@PathVariable int id, @RequestBody Envio envio) {
        return envioService.updateEnvio(id, envio);
    }
}
