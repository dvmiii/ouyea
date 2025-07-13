package com.Grupo10.EcoMarketSpa.Controller;


import com.Grupo10.EcoMarketSpa.Model.Envio;
import com.Grupo10.EcoMarketSpa.Model.Inventario;
import com.Grupo10.EcoMarketSpa.Service.EnvioService;
import com.Grupo10.EcoMarketSpa.Assemblers.EnvioModelAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/envios")
@Tag(name = "Envio", description = "Servicios de gestión de envio para EcoMarket SPA")
public class EnvioController {

    private final EnvioService envioService;
    private final EnvioModelAssembler assembler;

    @Autowired
    public EnvioController(EnvioService envioService,
                           EnvioModelAssembler assembler) {
        this.envioService = envioService;
        this.assembler   = assembler;
    }

    @GetMapping
    @Operation(summary = "Obtener los registros de Envio", description = "Servicio GET para obtener todos los envios existentes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envio encontrado",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Inventario.class)))),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos", content = @Content)
    })
    public CollectionModel<EntityModel<Envio>> getAllEnvios() {
        List<EntityModel<Envio>> envios = envioService.getAllEnvios().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(envios,
                linkTo(methodOn(EnvioController.class).getAllEnvios()).withSelfRel()
        );
    }

    @Operation(summary = "Añadir un nuevo registro al envio", description = "Servicio POST para crear un nuevo registro de envio.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class))),
            @ApiResponse(responseCode = "400", description = "Petición inválida", content = @Content)
    })
    @PostMapping
    public ResponseEntity<EntityModel<Envio>> addEnvio(@RequestBody Envio envio) {
        Envio nuevo = envioService.addEnvio(envio);
        EntityModel<Envio> model = assembler.toModel(nuevo);

        return ResponseEntity
                .created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(model);
    }

    @Operation(summary = "Eliminar un Envio", description = "Servicio DELETE para eliminar un envio por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registro eliminado exitosamente", content = @Content),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEnvio(@PathVariable int id) {
        envioService.deleteEnvio(id);
        return ResponseEntity.noContent().build();
    }
}