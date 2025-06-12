package com.Grupo10.EcoMarketSpa.Assemblers;

import com.Grupo10.EcoMarketSpa.Controller.ItemOrderController;
import com.Grupo10.EcoMarketSpa.Model.ItemPedido;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
// ItemOrderModelAssembler.java
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ItemOrderModelAssembler implements RepresentationModelAssembler<ItemPedido, EntityModel<ItemPedido>> {
    @Override
    public EntityModel<ItemPedido> toModel(ItemPedido model) {
        return EntityModel.of(model,
                linkTo(methodOn(ItemOrderController.class).getItemOrderById(model.getIdItemPedido())).withSelfRel(),
                linkTo(methodOn(ItemOrderController.class).getAllItemOrder()).withRel("GET")
        );
    }
}
