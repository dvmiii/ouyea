package com.Grupo10.EcoMarketSpa.Assemblers;

import com.Grupo10.EcoMarketSpa.Controller.PermisoController;
import com.Grupo10.EcoMarketSpa.Model.Permiso;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
// PermisoModelAssembler.java
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PermisoModelAssembler implements RepresentationModelAssembler<Permiso, EntityModel<Permiso>> {
    @Override
    public EntityModel<Permiso> toModel(Permiso model) {
        return EntityModel.of(model,
                linkTo(methodOn(PermisoController.class).getPermisoById(model.getIdPermiso())).withSelfRel(),
                linkTo(methodOn(PermisoController.class).getAllPermisos()).withRel("GET")
        );
    }
}
