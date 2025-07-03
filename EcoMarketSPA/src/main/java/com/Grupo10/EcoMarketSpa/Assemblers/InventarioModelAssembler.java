package com.Grupo10.EcoMarketSpa.Assemblers;

import com.Grupo10.EcoMarketSpa.Controller.InventarioController;
import com.Grupo10.EcoMarketSpa.Model.Inventario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
// InventarioModelAssembler.java
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class InventarioModelAssembler implements RepresentationModelAssembler<Inventario, EntityModel<Inventario>> {
    public Object toModel;

    @SuppressWarnings("null")
    @Override
    public EntityModel<Inventario> toModel(Inventario model) {
        return EntityModel.of(model,
                linkTo(methodOn(InventarioController.class).getInventarioById(model.getId())).withSelfRel(),
                linkTo(methodOn(InventarioController.class).getAllInventarios()).withRel("GET")
        );
    }

    public EntityModel<Inventario> toModel(String inventario) {
        return null;
    }
}
