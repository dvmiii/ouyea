package com.Grupo10.EcoMarketSpa.Assemblers;

import com.Grupo10.EcoMarketSpa.Controller.ProductoController;
import com.Grupo10.EcoMarketSpa.Model.Producto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
// ProductoModelAssembler.java
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {
    @SuppressWarnings("null")
    @Override
    public EntityModel<Producto> toModel(Producto model) {
        return EntityModel.of(model,
                linkTo(methodOn(ProductoController.class).getProductoById(model.getIdProducto())).withSelfRel(),
                linkTo(methodOn(ProductoController.class).getAllProductos()).withRel("GET")
        );
    }
}
