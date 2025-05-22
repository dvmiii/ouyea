package com.Grupo10.EcoMarketSpa.Service;

import com.Grupo10.EcoMarketSpa.Model.ItemCarrito;
import com.Grupo10.EcoMarketSpa.Repository.ItemCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCartService {

    @Autowired
    private ItemCartRepository itemCartRepository;

    //Listar
    public String getAllItemCarts(){
        String info="";
        for (ItemCarrito itemCarrito:itemCartRepository.findAll()){
            info+="Id Item Carrito: "+itemCarrito.getIdItemCarrito()+"\n";
            info+="Cantidad: "+itemCarrito.getCantidad()+"\n";
        }
        if (info.isEmpty()){
            return "No existen item en el Carrito";
        }else{
            return info;
        }
    }
    //Buscar por Id
    public String getItemCartById(int id){
        String info="";
        if (itemCartRepository.existsById(id)){
            ItemCarrito itemCarrito=itemCartRepository.findById(id).get();
            info+="Id Item Carrito: "+itemCarrito.getIdItemCarrito()+"\n";
            info+="Cantidad: "+itemCarrito.getCantidad()+"\n";
            return  info;
        }else{
            return "No existen item en el Carrito";
        }
    }
    //Agregar
    public String addItemCart(ItemCarrito itemCarrito){
        if (!itemCartRepository.existsById(itemCarrito.getIdItemCarrito())){
            itemCartRepository.save(itemCarrito);
            return "Item Carrito agregado correctamente";
        }else{
            return "El item carrito ya existe";
        }
    }
    //Eliminar
    public String deleteItemCart(int id){
        if (!itemCartRepository.existsById(id)){
            itemCartRepository.deleteById(id);
            return "Item Carrito eliminado correctamente";
        }else{
            return "El item carrito no existe";
        }
    }
    //Actualizar
    public String updateItemCart(int id,ItemCarrito itemCarrito){
        if (itemCartRepository.existsById(id)){
            ItemCarrito itemCarritoUpdate = itemCartRepository.findById(id).get();
            itemCarritoUpdate.setIdItemCarrito(itemCarrito.getIdItemCarrito());
            itemCarritoUpdate.setCantidad(itemCarrito.getCantidad());
            return "Item Carrito actualizado correctamente";
        }else{
            return "El Item Carrito no existe";
        }
    }
}
