package com.Grupo10.EcoMarketSpa.Assemblers;

import com.Grupo10.EcoMarketSpa.Controller.ProveedorController;
import com.Grupo10.EcoMarketSpa.Model.Proveedor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
// ProveedorModelAssembler.java
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProveedorModelAssembler implements RepresentationModelAssembler<Proveedor, EntityModel<Proveedor>> {
    @Override
    public EntityModel<Proveedor> toModel(Proveedor model) {
        return EntityModel.of(model,
                linkTo(methodOn(ProveedorController.class).getProveedorById(model.getIdproveedor())).withSelfRel(),
                linkTo(methodOn(ProveedorController.class).getAllProveedores()).withRel("GET")
        );
    }
}
