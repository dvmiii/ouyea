package com.Grupo10.EcoMarketSpa.Assemblers;

import com.Grupo10.EcoMarketSpa.Controller.UsuarioController;
import com.Grupo10.EcoMarketSpa.Model.Usuario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
// UserModelAssembler.java
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>>{
    @Override
    public EntityModel<Usuario> toModel(Usuario model) {
        return EntityModel.of(model,
                linkTo(methodOn(UsuarioController.class).getUserById(model.getIdUsuario())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).getAllUser()).withRel("GET")
        );
    }
}
