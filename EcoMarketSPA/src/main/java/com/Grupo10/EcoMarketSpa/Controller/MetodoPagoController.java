package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Model.MetodoPago;
import com.Grupo10.EcoMarketSpa.Service.MetodoPagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/MetodoPago")
@Tag(name = "Productos",description = "Servicios de gestion de productos para EcoMarket SPA")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @GetMapping
    @Operation(summary = "Obtener Productos",description = "Servicio GET para obtener todos los productos existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ""),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String getAllMetodoPagos() {
        return metodoPagoService.getAllMetodosPagos();
    }

    @PostMapping
    @Operation(summary = "Obtener Productos",description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MetodoPago.class))),
            @ApiResponse(responseCode = "204", description = "")
    })
    public String addMetodoPago(@RequestBody MetodoPago metodoPago) {
        return metodoPagoService.addMetodoPago(metodoPago);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Productos",description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ""),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String getMetodoPagoById(@PathVariable int id) {
        return metodoPagoService.getMetodoPagoById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Obtener Productos",description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ""),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String deleteMetodoPago(@PathVariable int id) {
        return metodoPagoService.deleteMetodoPago(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Obtener Productos",description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MetodoPago.class))),
            @ApiResponse(responseCode = "204", description = "")
    })
    public String updateMetodoPago(@PathVariable int id, @RequestBody MetodoPago metodoPago) {
        return metodoPagoService.updateMetodoPago(id, metodoPago);
    }
}
