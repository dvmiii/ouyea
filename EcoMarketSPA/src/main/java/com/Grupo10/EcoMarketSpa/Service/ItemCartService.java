package com.Grupo10.EcoMarketSpa.Service;

import com.Grupo10.EcoMarketSpa.Model.ItemCarrito;
import com.Grupo10.EcoMarketSpa.Repository.ItemCartRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCartService {

    @Autowired
    private ItemCartRepository itemCartRepository;


    //Listar
    public List<ItemCarrito> getAllItemCarts(){
        if (!itemCartRepository.findAll().iterator().hasNext()){
            return java.util.Collections.emptyList();
        } else {
            return itemCartRepository.findAll();
        }
    }
    //Buscar por Id
    public ItemCarrito getItemCartById(int id){
        if (itemCartRepository.existsById(id)){
            ItemCarrito itemCarrito = itemCartRepository.findById(id).get();
            return itemCarrito;
        }else{
            return null;
        }
    }
    //Agregar
    public ItemCarrito addItemCart(ItemCarrito itemCarrito){
        if (!itemCartRepository.existsById(itemCarrito.getIdItemCarrito())){
            return itemCartRepository.save(itemCarrito);
        }else{
            return null;
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
