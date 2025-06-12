package com.Grupo10.EcoMarketSpa.Assemblers;

import com.Grupo10.EcoMarketSpa.Controller.ItemCartController;
import com.Grupo10.EcoMarketSpa.Model.ItemCarrito;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
// ItemCartModelAssembler.java
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ItemCartModelAssembler implements RepresentationModelAssembler<ItemCarrito, EntityModel<ItemCarrito>> {
    @Override
    public EntityModel<ItemCarrito> toModel(ItemCarrito model) {
        return EntityModel.of(model,
                linkTo(methodOn(ItemCartController.class).getItemCartById(model.getIdItemCarrito())).withSelfRel(),
                linkTo(methodOn(ItemCartController.class).getAllItemCart()).withRel("GET")
        );
    }
}
