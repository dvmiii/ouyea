package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Assemblers.MetodoPagoModelAssembler;
import com.Grupo10.EcoMarketSpa.Model.MetodoPago;
import com.Grupo10.EcoMarketSpa.Service.MetodoPagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/MetodoPago")
@Tag(name = "Productos",description = "Servicios de gestion de productos para EcoMarket SPA")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @Autowired
    private MetodoPagoModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener Productos",description = "Servicio GET para obtener todos los productos existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ""),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
        public CollectionModel<EntityModel<MetodoPago>> getAllMetodoPagos() {
        List<MetodoPago> metodoPagoList = (List<MetodoPago>) metodoPagoService.getAllMetodosPagos();
        List<EntityModel<MetodoPago>> metodos = metodoPagoList.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(metodos, linkTo(methodOn(MetodoPagoController.class).getAllMetodoPagos()).withSelfRel());
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
