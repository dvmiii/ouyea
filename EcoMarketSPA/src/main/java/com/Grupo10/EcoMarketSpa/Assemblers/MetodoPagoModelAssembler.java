package com.Grupo10.EcoMarketSpa.Assemblers;

import com.Grupo10.EcoMarketSpa.Controller.MetodoPagoController;
import com.Grupo10.EcoMarketSpa.Model.MetodoPago;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
// MetodoPagoModelAssembler.java
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class MetodoPagoModelAssembler implements RepresentationModelAssembler<MetodoPago, EntityModel<MetodoPago>> {
    @Override
    public EntityModel<MetodoPago> toModel(MetodoPago model) {
        return EntityModel.of(model,
                linkTo(methodOn(MetodoPagoController.class).getMetodoPagoById(model.getIdMetodoPago())).withSelfRel(),
                linkTo(methodOn(MetodoPagoController.class).getAllMetodoPagos()).withRel("GET")
        );
    }
}
