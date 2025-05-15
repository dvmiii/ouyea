package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Model.Usuario;
import com.Grupo10.EcoMarketSpa.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String getAllUser(){
        return usuarioService.getAllUser();
    }

    @PostMapping
    public String addUser(@RequestBody Usuario usuario){
        return usuarioService.addUser(usuario);
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable int id){
        return usuarioService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        return usuarioService.DeleteUser(id);
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @RequestBody Usuario usuario){
        return usuarioService.updateUser(id, usuario);
    }
}
