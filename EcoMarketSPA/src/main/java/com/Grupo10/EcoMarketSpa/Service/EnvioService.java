package com.Grupo10.EcoMarketSpa.Service;

import com.Grupo10.EcoMarketSpa.Model.Envio;
import com.Grupo10.EcoMarketSpa.Repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    //Listar
    public String getAllEnvios(){
        String info="";
        for (Envio envio : envioRepository.findAll()){
            info+="Id Envio: "+envio.getIdEnvio()+"\n";
            info+="Estado: "+envio.getEstadoEnvio()+"\n";
            info+="Fecha: "+envio.getFechaEnvio()+"\n";
            info+="Fecha Estimado: "+envio.getFechaEntregaEstimada()+"\n";
            info+="Ruta: "+envio.getRuta()+"\n";
        }
        if (info.isEmpty()){
            return "No hay envios";
        }else{
            return info;
        }
    }
    //Buscar por Id
    public String getEnvioById(int id){
        String info="";
        if (envioRepository.existsById(id)){
            Envio envio = envioRepository.findById(id).get();
            info+="Id Envio: "+envio.getIdEnvio()+"\n";
            info+="Estado: "+envio.getEstadoEnvio()+"\n";
            info+="Fecha: "+envio.getFechaEnvio()+"\n";
            info+="Fecha Estimado: "+envio.getFechaEntregaEstimada()+"\n";
            info+="Ruta: "+envio.getRuta()+"\n";
            return info;
        }else{
            return "No existe Envio";
        }
    }
    //Agregar
    public String addEnvio(Envio envio){
        if (!envioRepository.existsById(envio.getIdEnvio())){
            envioRepository.save(envio);
            return "Envio creada exitosamente";
        }else{
            return "Envio existe";
        }
    }
    //Eliminar
    public String deleteEnvio(int id){
        if (!envioRepository.existsById(id)){
            envioRepository.deleteById(id);
            return "Envio eliminado exitosamente";
        }else{
            return "Envio no existe";
        }
    }
    //Actualizar
    public String updateEnvio(int id,Envio envio){
        if (envioRepository.existsById(id)){
            Envio envioUpdate = envioRepository.findById(id).get();
            envioUpdate.setEstadoEnvio(envio.getEstadoEnvio());
            envioUpdate.setFechaEnvio(envio.getFechaEnvio());
//            envioUpdate.getFechaEntregaEstimada(envio.getFechaEntregaEstimada());
            envioUpdate.setRuta(envio.getRuta());
            envioRepository.save(envioUpdate);
            return "Envio actualizado exitosamente";
        }else{
            return "Envio no existe";
        }
    }
}
