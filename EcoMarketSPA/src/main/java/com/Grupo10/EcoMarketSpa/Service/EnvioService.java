package com.Grupo10.EcoMarketSpa.Service;

import com.Grupo10.EcoMarketSpa.Model.Envio;
import com.Grupo10.EcoMarketSpa.Repository.EnvioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnvioService {

    private final EnvioRepository envioRepository;

    @Autowired
    public EnvioService(EnvioRepository envioRepository) {
        this.envioRepository = envioRepository;
    }


    public List<Envio> getAllEnvios() {
        return envioRepository.findAll();
    }


    public Envio addEnvio(Envio envio) {
        return envioRepository.save(envio);
    }


    public void deleteEnvio(int id) {
        if (envioRepository.existsById(id)) {
            envioRepository.deleteById(id);
        } else {
        }
    }

    public Optional<Envio> updateEnvio(int id, Envio envioNuevosDatos) {
        return envioRepository.findById(id)
                .map(envioExistente -> {
                    envioExistente.setEstadoEnvio(envioNuevosDatos.getEstadoEnvio());
                    envioExistente.setFechaEnvio(envioNuevosDatos.getFechaEnvio());

                    envioExistente.setFechaEntregaEstimada(envioNuevosDatos.getFechaEntregaEstimada());
                    envioExistente.setRuta(envioNuevosDatos.getRuta());
                    return envioRepository.save(envioExistente);
                });

    }

}