package com.Grupo10.EcoMarketSpa.Service;

import com.Grupo10.EcoMarketSpa.Model.Usuario;
import com.Grupo10.EcoMarketSpa.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Listar
    public String getAllUser(){
        String info = "";

        for (Usuario user:usuarioRepository.findAll()){
            info += "Id Usuario: "+user.getIdUsuario()+"\n";
            info += "Nombre: "+user.getNombreUsuario()+"\n";
            info += "Email: "+user.getEmail()+"\n";
            info += "Contraseña: "+user.getPassword()+"\n";
            info += "Rol del Usuario"+user.getRol()+"\n";
        }
        if (info.isEmpty()){
            return "No hay Usuarios registrados";
        }else{
            return info;
        }
    }
    // Buscar por ID
    public String getUserById(int id){
        String info = "";
        if (usuarioRepository.existsById(id)){
            Usuario user=usuarioRepository.findById(id).get();
            info += "Id Usuario: "+user.getIdUsuario()+"\n";
            info += "Nombre: "+user.getNombreUsuario()+"\n";
            info += "Email: "+user.getEmail()+"\n";
            info += "Contraseña: "+user.getPassword()+"\n";
            info += "Rol del Usuario"+user.getRol()+"\n";
            return info;
        }else{
            return "Usuario no encontrado";
        }
    }
    //Agregar
    public String addUser(Usuario user){

        if(!usuarioRepository.existsById(user.getIdUsuario())){
            usuarioRepository.save(user);
            return "Usuario Agregado Correctamente";
        }else{
            return "Usuario ya Existe";
        }
    }
    //ELiminar
    public String DeleteUser(int id){
        if (usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
            return "Usuario eliminado correctamente";
        }else{
            return "Usuario no encontrado";
        }
    }
    //Actualizar
    public String updateUser(int id,Usuario user){
        if (usuarioRepository.existsById(id)){
            Usuario userUpdate = usuarioRepository.findById(id).get();
            userUpdate.setNombreUsuario(user.getNombreUsuario());
            userUpdate.setEmail(user.getEmail());
            userUpdate.setPassword(user.getPassword());
            userUpdate.setRol(user.getRol());
            usuarioRepository.save(userUpdate);
            return "Usuario actualizado correctamente";
        }else {
            return "Usuario no encontrado";
        }
    }
}
