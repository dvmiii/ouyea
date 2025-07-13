package com.Grupo10.EcoMarketSpa.Service;

import com.Grupo10.EcoMarketSpa.Model.Rol;
import com.Grupo10.EcoMarketSpa.Repository.RolRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    //Listar
    public List<Rol> getAllRol(){
        String info = "";

        for (Rol rol:rolRepository.findAll()){
            info += "Id Rol: "+rol.getIdRol()+"\n";
            info += "Nombre del rol: "+rol.getNombreRol()+"\n";
            info += "Permiso: "+rol.getIdPermiso()+"\n";
        }
        if (info.isEmpty()){
            return java.util.Collections.emptyList();
        }else{
            return rolRepository.findAll();
        }
    }
    // Buscar por ID
    public String getRolById(int id){
        String info = "";
        if (rolRepository.existsById(id)){
            Rol rol=rolRepository.findById(id).get();
            info += "Id Rol: "+rol.getIdRol()+"\n";
            info += "Nombre del rol: "+rol.getNombreRol()+"\n";
            info += "Permiso: "+rol.getIdPermiso()+"\n";
            return info;
        }else{
            return "Rol no encontrado";
        }
    }
    //Agregar
    public String addRol(Rol rol){

        if(!rolRepository.existsById(rol.getIdRol())){
            rolRepository.save(rol);
            return "Rol Agregado Correctamente";
        }else{
            return "Rol ya Existe";
        }
    }
    //ELiminar
    public String deleteRol(int id){
        if (rolRepository.existsById(id)){
            rolRepository.deleteById(id);
            return "Rol eliminado correctamente";
        }else{
            return "Rol no encontrado";
        }
    }
    //Actualizar
    public String updateRol(int id,Rol rol){
        if (rolRepository.existsById(id)){
            Rol rolUpdate = rolRepository.findById(id).get();
            rolUpdate.setNombreRol(rol.getNombreRol());
            rolUpdate.setIdPermiso(rol.getIdPermiso());
            rolRepository.save(rolUpdate);
            return "Rol actualizado correctamente";
        }else {
            return "Rol no encontrado";
        }
    }
}
