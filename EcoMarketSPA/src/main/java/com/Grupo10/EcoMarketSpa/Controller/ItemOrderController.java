package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Assemblers.ItemOrderModelAssembler;
import com.Grupo10.EcoMarketSpa.Model.ItemPedido;
import com.Grupo10.EcoMarketSpa.Service.ItemOrderService;
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
@RequestMapping("/ItemOrder")
@Tag(name = "Productos",description = "Servicios de gestion de productos para EcoMarket SPA")
public class ItemOrderController {

    @Autowired
    ItemOrderService itemOrderService;

    @Autowired
    private ItemOrderModelAssembler assembler;

   @GetMapping("/cart")
   public CollectionModel<EntityModel<ItemPedido>> getAllItemCart(){
        List<ItemPedido> itemPedidos = itemOrderService.getAllItemOrders();
        List<EntityModel<ItemPedido>> items = itemPedidos
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(items, linkTo(methodOn(ItemOrderController.class).getAllItemCart()).withSelfRel());
    }

    @PostMapping
    @Operation(summary = "Obtener Productos",description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemPedido.class))),
            @ApiResponse(responseCode = "204", description = "")
    })
    public String addItemOrder(@RequestBody ItemPedido itemOrder){
        return itemOrderService.addItmOrder(itemOrder);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Productos",description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ""),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String getItemOrderById(@PathVariable int id) {
        return itemOrderService.getItemOrderById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Obtener Productos",description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ""),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public String deleteItemOrder(@PathVariable int id) {
        return itemOrderService.deleteItemOrder(id) ;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Obtener Productos",description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemPedido.class))),
            @ApiResponse(responseCode = "204", description = "")
    })
    public String updateItemOrder(@PathVariable int id, @RequestBody ItemPedido itemOrder) {
        return itemOrderService.updateItemOrder(id, itemOrder);
    }
}
