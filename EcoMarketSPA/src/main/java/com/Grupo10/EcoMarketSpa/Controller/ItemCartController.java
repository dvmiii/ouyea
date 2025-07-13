package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Model.ItemCarrito;
import com.Grupo10.EcoMarketSpa.Service.ItemCartService;
import com.Grupo10.EcoMarketSpa.Assemblers.ItemCartModelAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/ItemCart")
@Tag(name = "Item del Carrito",description = "Servicios de gestion de item de carrito para EcoMarket SPA")
public class ItemCartController {

    @Autowired
    private ItemCartService itemCartService;

    @Autowired
    private ItemCartModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener ItemCart",description = "Servicio GET para obtener todos los item del carrito existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna los Item del Carrito"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public CollectionModel<EntityModel<ItemCarrito>> getAllItemCart(){
        List<ItemCarrito> itemList = (List<ItemCarrito>) itemCartService.getAllItemCarts();
        List<EntityModel<ItemCarrito>> items = itemList.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(items, linkTo(methodOn(ItemCartController.class).getAllItemCart()).withSelfRel());
    }

    @PostMapping
    @Operation(summary = "Agregar Producto al Carrito",description = "Servicio POST para agregar todos los item al carrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Agrega los Item del Carrito",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemCarrito.class))),
            @ApiResponse(responseCode = "204", description = "")
    })
    public EntityModel<ItemCarrito> addItemCart(@RequestBody ItemCarrito itemCart){
        ItemCarrito saved = (ItemCarrito) itemCartService.addItemCart(itemCart);
        return assembler.toModel(saved);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Producto del Carrito por ID",description = "Servicio GET para obtener los item del carrito por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Obtener los Item del Carrito"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public EntityModel<ItemCarrito> getItemCartById(@PathVariable int id){
        ItemCarrito item = (ItemCarrito) itemCartService.getItemCartById(id);
        return assembler.toModel(item);
    }
}
