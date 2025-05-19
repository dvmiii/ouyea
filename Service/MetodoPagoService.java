package com.Grupo10.EcoMarketSpa.Service;

import com.Grupo10.EcoMarketSpa.Model.MetodoPago;
import com.Grupo10.EcoMarketSpa.Repository.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    //Listar
    public String getAllMetodosPagos(){
        String info="";
        for(MetodoPago metodoPago:metodoPagoRepository.findAll()){
            info+="Id Metodo de Pago: "+metodoPago.getIdMetodoPago()+"\n";
            info+="Tipo de Metodo de Pago: "+metodoPago.getTipo()+"\n";
            info+="Detalles del Metodo de Pago: "+metodoPago.getDetalles()+"\n";
        }
        if (info.isEmpty()){
            return "No existe Ningun Metodo de Pago";
        }else{
            return info;
        }
    }
    //Buscar por Id
    public String getMetodoPagoById(int id){
        String info="";
        if (metodoPagoRepository.existsById(id)){
            MetodoPago metodoPago=metodoPagoRepository.findById(id).get();
            info+="Id Metodo de Pago: "+metodoPago.getIdMetodoPago()+"\n";
            info+="Tipo de Metodo de Pago: "+metodoPago.getTipo()+"\n";
            info+="Detalles del Metodo de Pago: "+metodoPago.getDetalles()+"\n";
            return info;
        }else{
            return "No existe Metodo de Pago";
        }
    }
    //Agregar
    public String addMetodoPago(MetodoPago metodoPago){
        if (!metodoPagoRepository.existsById(metodoPago.getIdMetodoPago())){
            metodoPagoRepository.save(metodoPago);
            return "Metodo de Pago Insertado";
        }else{
            return "Metodo de Pago ya existe";
        }
    }
    //Eliminar
    public String deleteMetodoPago(int id){
        if (!metodoPagoRepository.existsById(id)){
            metodoPagoRepository.deleteById(id);
            return "Metodo de Pago eliminado";
        }else{
            return "Metodo de Pago no existe";
        }
    }
    //Actualizar
    public String updateMetodoPago(int id,MetodoPago metodoPago){
        if (metodoPagoRepository.existsById(id)){
            MetodoPago metodoPagoUpdate=metodoPagoRepository.findById(id).get();
            metodoPagoUpdate.setTipo(metodoPago.getTipo());
            metodoPagoUpdate.setDetalles(metodoPago.getDetalles());
            metodoPagoRepository.save(metodoPagoUpdate);
            return "Metodo de Pago actualizado";
        }else{
            return "Metodo de Pago no existe";
        }
    }
}
