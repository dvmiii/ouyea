package com.Grupo10.EcoMarketSpa.Assemblers;

import com.Grupo10.EcoMarketSpa.Controller.EnvioController;
import com.Grupo10.EcoMarketSpa.Model.Envio;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EnvioModelAssembler implements RepresentationModelAssembler<Envio, EntityModel<Envio>> {


    @SuppressWarnings("null")
    @Override
    public EntityModel<Envio> toModel(Envio model) {
        return EntityModel.of(model,
                linkTo(methodOn(EnvioController.class).getAllEnvios()).withRel("GET")
        );
    }

}
