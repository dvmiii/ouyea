package com.Grupo10.EcoMarketSpa.Controller;


import com.Grupo10.EcoMarketSpa.Model.Envio;
import com.Grupo10.EcoMarketSpa.Service.EnvioService;
import com.Grupo10.EcoMarketSpa.Assemblers.EnvioModelAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/envios")
public class EnvioController {

    private final EnvioService envioService;
    private final EnvioModelAssembler assembler;

    @Autowired
    public EnvioController(EnvioService envioService,
                           EnvioModelAssembler assembler) {
        this.envioService = envioService;
        this.assembler   = assembler;
    }


    @GetMapping
    public CollectionModel<EntityModel<Envio>> getAllEnvios() {
        List<EntityModel<Envio>> envios = envioService.getAllEnvios().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(envios,
                linkTo(methodOn(EnvioController.class).getAllEnvios()).withSelfRel()
        );
    }



    @PostMapping
    public ResponseEntity<EntityModel<Envio>> addEnvio(@RequestBody Envio envio) {
        Envio nuevo = envioService.addEnvio(envio);
        EntityModel<Envio> model = assembler.toModel(nuevo);

        return ResponseEntity
                .created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(model);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEnvio(@PathVariable int id) {
        envioService.deleteEnvio(id);
        return ResponseEntity.noContent().build();
    }
}