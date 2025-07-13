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
@Tag(name = "Metodo de pago",description = "Servicios de gestion del metodo de pago para EcoMarket SPA")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @Autowired
    private MetodoPagoModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener Metodos de Pagos",description = "Servicio GET para obtener todos los metodos de pagos existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna los Metodos de Pagos"),
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
    @Operation(summary = "Agregar Metodo de Pago",description = "Servicio Post para agregar todos los metodos de pagos existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Metodos de Pagos Agregado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MetodoPago.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public String addMetodoPago(@RequestBody MetodoPago metodoPago) {
        return metodoPagoService.addMetodoPago(metodoPago);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Metodo de Pago",description = "Servicio GET para obtener los metodos de pagos por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Registrar un Metodo de Pago"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String getMetodoPagoById(@PathVariable int id) {
        return metodoPagoService.getMetodoPagoById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Metodo de Pago",description = "Servicio DELETE para eliminar los metodos de pagos por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Eliminado"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String deleteMetodoPago(@PathVariable int id) {
        return metodoPagoService.deleteMetodoPago(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Metodo de Pago",description = "Servicio PUT para actualizar los metodos de pagos por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MetodoPago.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public String updateMetodoPago(@PathVariable int id, @RequestBody MetodoPago metodoPago) {
        return metodoPagoService.updateMetodoPago(id, metodoPago);
    }
}
