package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Assemblers.InventarioModelAssembler;
import com.Grupo10.EcoMarketSpa.Model.Inventario;
import com.Grupo10.EcoMarketSpa.Service.InventarioService;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
// El path es usualmente en minúsculas y plural
@RequestMapping("/inventarios")
@Tag(name = "Inventario", description = "Servicios de gestión de inventario para EcoMarket SPA")
public class InventarioController {

    private final InventarioService inventarioService;
    private final InventarioModelAssembler assembler;

    // Inyección de dependencias por constructor (práctica recomendada)
    @Autowired
    public InventarioController(InventarioService inventarioService, InventarioModelAssembler assembler) {
        this.inventarioService = inventarioService;
        this.assembler = assembler;
    }

    @Operation(summary = "Obtener todos los registros de inventario", description = "Servicio GET para obtener todos los productos existentes en el inventario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario encontrado",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Inventario.class)))),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos", content = @Content)
    })
    @GetMapping
    public CollectionModel<EntityModel<Inventario>> getAllInventarios() {
        List<Inventario> inventarioList = inventarioService.getAllInventarios();
        List<EntityModel<Inventario>> inventarios = inventarioList.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(inventarios,
                linkTo(methodOn(InventarioController.class).getAllInventarios()).withSelfRel()
        );
    }


    @Operation(summary = "Obtener un registro de inventario por ID", description = "Servicio GET para obtener un producto del inventario específico por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class))),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public EntityModel<Inventario> getInventarioById(@PathVariable int id) {
        
        String inventario = inventarioService.getInventarioById(id);
        return assembler.toModel(inventario); 
    }

    @Operation(summary = "Añadir un nuevo registro al inventario", description = "Servicio POST para crear un nuevo registro de producto en el inventario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class))),
            @ApiResponse(responseCode = "400", description = "Petición inválida", content = @Content)
    })
    @PostMapping
    public ResponseEntity<EntityModel<Inventario>> addInventario(@RequestBody Inventario inventario) {
        // Llama al servicio, que debe devolver el Inventario guardado
        String nuevoInventario = inventarioService.addInventario(inventario);
        EntityModel<Inventario> entityModel = assembler.toModel(nuevoInventario);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) // Header 'Location'
                .body(entityModel);
    }

    @Operation(summary = "Actualizar un registro de inventario", description = "Servicio PUT para modificar un registro existente en el inventario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class))),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Inventario>> updateInventario(@PathVariable int id, @RequestBody Inventario inventario) {
        // Llama al servicio, que debe devolver el Inventario actualizado
        String inventarioActualizado = inventarioService.updateInventario(id, inventario);
        EntityModel<Inventario> entityModel = assembler.toModel(inventarioActualizado);

        return ResponseEntity.ok(entityModel); 
    }

    @Operation(summary = "Eliminar un registro de inventario", description = "Servicio DELETE para eliminar un registro del inventario por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registro eliminado exitosamente", content = @Content),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInventario(@PathVariable int id) {
        
        inventarioService.deleteInventario(id);
        return ResponseEntity.noContent().build(); 
    }
}