package com.Grupo10.EcoMarketSpa.Service;

import com.Grupo10.EcoMarketSpa.Model.Inventario;
import com.Grupo10.EcoMarketSpa.Repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private static InventarioRepository inventarioRepository;

    public List<Inventario> getAllInventarios() {
            return null;
        
    }

    //Listar
    public String getAllInventariosInfo(){
        String info="";
        for (Inventario inventario:inventarioRepository.findAll()){
            info+="Id Inventario: "+inventario.getId()+"\n";
            info+="Tienda: "+inventario.getTienda()+"\n";
            info+="Lista: "+inventario.getInventarioList()+"\n";
        }
        if (info.isEmpty()){
            return "No existen Inventarios";
        }else{
            return info;
        }
    }
    //Buscar por Id
    public String getInventarioById(int id){
        String info="";
        if (inventarioRepository.existsById(id)){
            Inventario inventario=inventarioRepository.findById(id).get();
            info+="Id Inventario: "+inventario.getId()+"\n";
            info+="Tienda: "+inventario.getTienda()+"\n";
            info+="Lista: "+inventario.getInventarioList()+"\n";
            return  info;
        }else{
            return "No existe inventario";
        }
    }
    //Agregar
    public String addInventario(Inventario inventario){
        if (!inventarioRepository.existsById(inventario.getId())){
            inventarioRepository.save(inventario);
            return "Inventario agregado correctamente";
        }else{
            return "El inventario ya existe";
        }
    }
    //Eliminar
    public String deleteInventario(int id){
        if (!inventarioRepository.existsById(id)){
            inventarioRepository.deleteById(id);
            return "Inventario eliminado correctamente";
        }else{
            return "El inventario no existe";
        }
    }
    //Actualizar
    public String updateInventario(int id,Inventario inventario){
        if (inventarioRepository.existsById(id)){
            Inventario inventarioUpdate = inventarioRepository.findById(id).get();
            inventarioUpdate.setId(inventario.getId());
            inventarioUpdate.setTienda(inventario.getTienda());
            inventarioUpdate.setInventarioList(inventario.getInventarioList());
            inventarioRepository.save(inventarioUpdate);
            return "Inventario actualizado correctamente";
        }else{
            return "El inventario no existe";
        }
    }
}
