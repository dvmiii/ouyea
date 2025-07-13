package com.Grupo10.EcoMarketSpa.Service;

import com.Grupo10.EcoMarketSpa.Model.Usuario;
import com.Grupo10.EcoMarketSpa.Repository.UsuarioRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Listar
    public List<Usuario> getAllUser(){
        String info = "";

        for (Usuario user:usuarioRepository.findAll()){
            info += "Id Usuario: "+user.getIdUsuario()+"\n";
            info += "Nombre: "+user.getNombreUsuario()+"\n";
            info += "Email: "+user.getEmail()+"\n";
            info += "Contraseña: "+user.getPassword()+"\n";
            info += "Rol del Usuario"+user.getIdRol()+"\n";
        }
        if (info.isEmpty()){
            return java.util.Collections.emptyList();
        }else{
            return usuarioRepository.findAll();
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
            info += "Rol del Usuario"+user.getIdRol()+"\n";
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
    public String deleteUser(int id){
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
            userUpdate.setIdRol(user.getIdRol());
            usuarioRepository.save(userUpdate);
            return "Usuario actualizado correctamente";
        }else {
            return "Usuario no encontrado";
        }
    }
}
