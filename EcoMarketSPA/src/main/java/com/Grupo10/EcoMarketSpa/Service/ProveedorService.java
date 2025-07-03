package com.Grupo10.EcoMarketSpa.Service;

import com.Grupo10.EcoMarketSpa.Model.Proveedor;
import com.Grupo10.EcoMarketSpa.Repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    //Listar
    public String getAllProveedores(){
        String info="";
        for (Proveedor proveedor:proveedorRepository.findAll()){
            info+="Id Proveedor: "+proveedor.getIdproveedor()+"\n";
            info+="Nombre: "+proveedor.getNombre()+"\n";
            info+="Contacto: "+proveedor.getContacto()+"\n";
            info+="Productos: "+proveedor.getProductos()+"\n";
        }
        if (info.isEmpty()){
            return "No existen proveedores";
        }else{
            return info;
        }
    }
    //Buscar por Id
    public String getProveedorById(int id){
        String info="";
        if (proveedorRepository.existsById(id)){
            Proveedor proveedor=proveedorRepository.findById(id).get();
            info+="Id Proveedor: "+proveedor.getIdproveedor()+"\n";
            info+="Nombre: "+proveedor.getNombre()+"\n";
            info+="Contacto: "+proveedor.getContacto()+"\n";
            info+="Productos: "+proveedor.getProductos()+"\n";
            return  info;
        }else{
            return "No existe proveedor";
        }
    }
    //Agregar
    public String addProveedor(Proveedor proveedor){
        if (!proveedorRepository.existsById(proveedor.getIdproveedor())){
            proveedorRepository.save(proveedor);
            return "Proveedor agregado correctamente";
        }else{
            return "El proveedor ya existe";
        }
    }
    //Eliminar
    public String deleteProveedor(int id){
        if (!proveedorRepository.existsById(id)){
            proveedorRepository.deleteById(id);
            return "Proveedor eliminado correctamente";
        }else{
            return "El proveedor no existe";
        }
    }
    //Actualizar
    public String updateProveedor(int id,Proveedor proveedor){
        if (proveedorRepository.existsById(id)){
            Proveedor proveedorUpdate = proveedorRepository.findById(id).get();
            proveedorUpdate.setNombre(proveedor.getNombre());
            proveedorUpdate.setContacto(proveedor.getContacto());
            proveedorUpdate.setProductos(proveedor.getProductos());
            proveedorRepository.save(proveedorUpdate);
            return "Proveedor actualizado correctamente";
        }else{
            return "El proveedor no existe";
        }
    }
}
