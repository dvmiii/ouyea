package com.Grupo10.EcoMarketSpa.Assemblers;

import com.Grupo10.EcoMarketSpa.Controller.RolController;
import com.Grupo10.EcoMarketSpa.Model.Rol;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RolModelAssembler implements RepresentationModelAssembler<Rol, EntityModel<Rol>> {
    @SuppressWarnings("null")
    @Override
    public EntityModel<Rol> toModel(Rol model){
        return EntityModel.of(model,
                linkTo(methodOn(RolController.class).getRolById(model.getIdRol())).withSelfRel(),
                linkTo(methodOn(RolController.class).getAllRol()).withRel("GET")
        );
    }
}
