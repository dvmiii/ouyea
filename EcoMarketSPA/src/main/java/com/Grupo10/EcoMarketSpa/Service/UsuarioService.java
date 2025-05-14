package com.Grupo10.EcoMarketSpa.Service;

import com.Grupo10.EcoMarketSpa.Model.Usuario;
import com.Grupo10.EcoMarketSpa.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Listar Usuarios
    public String getAllUSer(){
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
    // Buscar Usuario por ID
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
    //Agregar Usuarios
    public String addUser(Usuario user){

        if(!usuarioRepository.existsById(user.getIdUsuario())){
            usuarioRepository.save(user);
            return "Usuario Agregado Correctamente";
        }else{
            return "Usuario ya Existe";
        }
    }
}
