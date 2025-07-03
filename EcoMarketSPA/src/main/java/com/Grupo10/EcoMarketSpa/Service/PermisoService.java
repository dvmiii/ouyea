package com.Grupo10.EcoMarketSpa.Service;

import com.Grupo10.EcoMarketSpa.Model.Permiso;
import com.Grupo10.EcoMarketSpa.Repository.PermisoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermisoService {

    @Autowired
    private PermisoRepository permisoRepository;

    //Listar
    public List<Permiso> getAllPermisos(){
        String info="";
        for (Permiso permiso : permisoRepository.findAll()) {
            info+="Id permiso: "+permiso.getIdPermiso()+"\n";
            info+="Nombre permiso: "+permiso.getNombre()+"\n";
            info+="Descripcion permiso: "+permiso.getDescripcion()+"\n";
        }
        if (info.isEmpty()){
            return java.util.Collections.emptyList();
        }else{
            return permisoRepository.findAll();
        }
    }
    //Buscar por Id
    public String getPermisosById(int id){
        String info="";
        if (permisoRepository.existsById(id)){
            Permiso permiso = permisoRepository.findById(id).get();
            info+="Id permiso: "+permiso.getIdPermiso()+"\n";
            info+="Nombre permiso: "+permiso.getNombre()+"\n";
            info+="Descripcion permiso: "+permiso.getDescripcion()+"\n";
            return info;
        }else{
            return "No existe el permiso con el id: "+id;
        }
    }
    //Agregar
    public String addPermisos(Permiso permiso){
        if (!permisoRepository.existsById(permiso.getIdPermiso())){
            permisoRepository.save(permiso);
            return "Permiso insertado";
        }else{
            return "Permiso duplicado";
        }
    }
    //Eliminar
    public String deletePermiso(int id){
        if (!permisoRepository.existsById(id)){
            permisoRepository.deleteById(id);
            return "Permiso Eliminado";
        }else{
            return "Permiso no existe";
        }
    }
    //Actualizar
    public String updatePermiso(int id,Permiso permiso){
        if (permisoRepository.existsById(id)){
            Permiso permisoUpdate = permisoRepository.findById(id).get();
            permisoUpdate.setNombre(permiso.getNombre());
            permisoUpdate.setDescripcion(permiso.getDescripcion());
            permisoRepository.save(permisoUpdate);
            return "Permiso actualizado";
        }else{
            return "Permiso no existe";
        }
    }
}
