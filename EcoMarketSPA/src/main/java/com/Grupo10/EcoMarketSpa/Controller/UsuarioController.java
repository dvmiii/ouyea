package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Model.Usuario;
import com.Grupo10.EcoMarketSpa.Service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Usuario")
@Tag(name = "Usuarios", description = "Servicios de gestión de usuarios para EcoMarket SPA")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todos los usuarios
    @GetMapping
    @Operation(summary = "Listar Usuarios", description = "Servicio GET para obtener todos los usuarios existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron usuarios")
    })
    public ResponseEntity<String> getAllUser() {
        String usuarios = usuarioService.getAllUser();
        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // Crear un nuevo usuario
    @PostMapping
    @Operation(summary = "Crear Usuario", description = "Servicio POST para registrar un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Error en los datos enviados")
    })
    public ResponseEntity<String> addUser(@RequestBody Usuario usuario) {
        String nuevoUsuario = usuarioService.addUser(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar Usuario por ID", description = "Servicio GET para obtener un usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<String> getUserById(@PathVariable int id) {
        String usuario = usuarioService.getUserById(id);
        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Usuario", description = "Servicio DELETE para eliminar un usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        boolean eliminado = Boolean.parseBoolean(usuarioService.deleteUser(id));
        if (!eliminado) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Usuario", description = "Servicio PUT para actualizar los datos de un usuario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Usuario> updateUser(@PathVariable int id,@RequestBody Usuario usuario) {
        String actualizado = usuarioService.updateUser(id, usuario);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
