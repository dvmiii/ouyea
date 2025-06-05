package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Model.Envio;
import com.Grupo10.EcoMarketSpa.Service.EnvioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Envio")
@Tag(name = "Productos",description = "Servicios de gestion de productos para EcoMarket SPA")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    @Operation(summary = "Obtener Productos",description = "Servicio GET para  todos los productos existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ""),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String getAllEnvios() {
        return envioService.getAllEnvios();
    }

    @PostMapping
    @Operation(summary = "Obtener Productos",description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Envio.class))),
            @ApiResponse(responseCode = "204", description = "")
    })
    public String addEnvio(@RequestBody Envio envio) {
        return envioService.addEnvio(envio);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Productos",description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ""),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String getEnvioById(@PathVariable int id) {
        return envioService.getEnvioById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Obtener Productos",description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ""),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String deleteEnvio(@PathVariable int id) {
        return envioService.deleteEnvio(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Obtener Productos",description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Envio.class))),
            @ApiResponse(responseCode = "204", description = "")
    })
    public String updateEnvio(@PathVariable int id, @RequestBody Envio envio) {
        return envioService.updateEnvio(id, envio);
    }
}
