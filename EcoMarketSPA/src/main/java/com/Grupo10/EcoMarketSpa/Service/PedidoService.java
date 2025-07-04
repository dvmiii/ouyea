package com.Grupo10.EcoMarketSpa.Service;

import com.Grupo10.EcoMarketSpa.Model.Pedido;
import com.Grupo10.EcoMarketSpa.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    //Listar
    public String getAllPedido(){
        String info = "";

        for (Pedido pedido:pedidoRepository.findAll()){
            info += "Id Pedido: "+pedido.getIdPedido() +"\n";
            info += "Id Cliente: "+pedido.getIdCliente()+"\n";
            info += "Lista de los Item Pedidos: "+pedido.getItemPedidoList()+"\n";
            info += "Estado: "+pedido.getEstado()+"\n";
            info += "Fecha Pedido"+pedido.getFechaPedido()+"\n";
            info += "Direccion Envio"+pedido.getDireccionEnvio()+"\n";
        }
        if (info.isEmpty()){
            return "No hay Pedidos registrados";
        }else{
            return info;
        }
    }
    //Buscar por Id
    public String getPedidoById(int id){
        String info = "";
        if (pedidoRepository.existsById(id)){
            Pedido pedido= pedidoRepository.findById(id).get();
            info += "Id Pedido: "+pedido.getIdPedido() +"\n";
            info += "Id Cliente: "+pedido.getIdCliente()+"\n";
            info += "Lista de los Item Pedidos: "+pedido.getItemPedidoList()+"\n";
            info += "Estado: "+pedido.getEstado()+"\n";
            info += "Fecha Pedido"+pedido.getFechaPedido()+"\n";
            info += "Direccion Envio"+pedido.getDireccionEnvio()+"\n";
            return info;
        }else{
            return "No Existe Pedido";
        }
    }
    //Agregar
    public String addPedido(Pedido pedido){
        if (!pedidoRepository.existsById(pedido.getIdPedido())){
            pedidoRepository.save(pedido);
            return "Pedido agregado correctamente";
        }else{
            return "El pedido ya existe";
        }
    }
    //Eliminar
    public String deletePedido(int id){
        if (!pedidoRepository.existsById(id)){
            pedidoRepository.deleteById(id);
            return "Pedido eliminado correctamente";
        }else{
            return "El pedido no existe";
        }
    }
    //Actualizar
    public String updatePedido(int id,Pedido pedido){
        if (pedidoRepository.existsById(id)){
            Pedido pedidoUpdate = pedidoRepository.findById(id).get();
            pedidoUpdate.setIdPedido(pedido.getIdPedido());
            pedidoUpdate.setIdCliente(pedido.getIdCliente());
            pedidoUpdate.setItemPedidoList(pedido.getItemPedidoList());
            pedidoUpdate.setEstado(pedido.getEstado());
            pedidoUpdate.setFechaPedido(pedido.getFechaPedido());
            pedidoUpdate.setDireccionEnvio(pedido.getDireccionEnvio());
            pedidoRepository.save(pedidoUpdate);
            return "Pedido actualizado correctamente";
        }else{
            return "El Pedido no existe";
        }
    }
}
