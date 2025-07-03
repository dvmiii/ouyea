package com.Grupo10.EcoMarketSpa.Service;


import com.Grupo10.EcoMarketSpa.Model.ItemPedido;
import com.Grupo10.EcoMarketSpa.Repository.ItemOrderRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemOrderService {

    @Autowired
    private ItemOrderRepository itemOrderRepository;

    //Listar
    public List<ItemPedido> getAllItemOrders() {
        List<ItemPedido> items = itemOrderRepository.findAll();
        return items;
    }
    //Buscar por Id
    public String getItemOrderById(int id){
        String info="";
        if (itemOrderRepository.existsById(id)){
            ItemPedido itemPedido=itemOrderRepository.findById(id).get();
            info+="Id Item pedido: "+itemPedido.getIdItemPedido()+"\n";
            info+="Producto pedido: "+itemPedido.getProductos()+"\n";
            info+="Cantidad: "+itemPedido.getCantidad()+"\n";
            info+="Precio unitario: "+itemPedido.getPrecioUnitario()+"\n";
            return  info;
        }else{
            return "No existen item pedido";
        }
    }
    //Agregar
    public String addItmOrder(ItemPedido itemPedido){
        if (!itemOrderRepository.existsById(itemPedido.getIdItemPedido())){
            itemOrderRepository.save(itemPedido);
            return "Item Pedido agregado correctamente";
        }else{
            return "El item pedido ya existe";
        }
    }
    //Eliminar
    public String deleteItemOrder(int id){
        if (!itemOrderRepository.existsById(id)){
            itemOrderRepository.deleteById(id);
            return "Item pedido eliminado correctamente";
        }else{
            return "El item pedido no existe";
        }
    }
    //Actualizar
    public String updateItemOrder(int id,ItemPedido itemPedido){
        if (itemOrderRepository.existsById(id)){
            ItemPedido itemPedidoUpdate = itemOrderRepository.findById(id).get();
            itemPedidoUpdate.setIdItemPedido(itemPedido.getIdItemPedido());
            itemPedidoUpdate.setProductos(itemPedido.getProductos());
            itemPedidoUpdate.setCantidad(itemPedido.getCantidad());
            itemPedidoUpdate.setPrecioUnitario(itemPedido.getPrecioUnitario());
            return "Item Pedido actualizado correctamente";
        }else{
            return "El Item Pedido no existe";
        }
    }
}
