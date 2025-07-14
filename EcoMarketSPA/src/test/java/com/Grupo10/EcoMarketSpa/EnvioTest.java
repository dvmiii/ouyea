package com.Grupo10.EcoMarketSpa;

import com.Grupo10.EcoMarketSpa.Model.Envio;
import com.Grupo10.EcoMarketSpa.Repository.EnvioRepository;
import com.Grupo10.EcoMarketSpa.Service.EnvioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class EnvioTest {

    @Mock
    private EnvioRepository envioRepository;

    @InjectMocks
    private EnvioService envioService;

    private Envio envio;

    @BeforeEach
    void setUp() {
        Envio envio1T = new Envio();
        envio1T.setIdEnvio(1);
        envio1T.setEstadoEnvio("En tránsito");
        envio1T.setFechaEnvio("2025-07-14");
        envio1T.setFechaEntregaEstimada("2025-07-16");
        envio1T.setRuta("Bodega Central → Sucursal Providencia");

        envio = envio1T;
    }

    @Test
    void testFindAll() {

        when(envioRepository.findAll()).thenReturn(List.of(envio));

        List<Envio> permisos = envioRepository.findAll();

        assertNotNull(permisos);
        assertEquals(1, permisos.size());
    }

    @Test
    void testSave() {
        // Given
        when(envioRepository.save(envio)).thenReturn(envio);
        // When
        Envio resultado = envioService.addEnvio(envio);
        // Then
        assertEquals("En tránsito", resultado.getEstadoEnvio());
        verify(envioRepository, times(1)).save(envio);
    }

    @Test
    void testDeleteById() {
        // Given
        doNothing().when(envioRepository).deleteById(1);
        // When
        envioService.deleteEnvio(1);
        // Then
        verify(envioRepository, times(1)).deleteById(1);

    }
}
