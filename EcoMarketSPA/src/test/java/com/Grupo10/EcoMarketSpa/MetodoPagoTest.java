package com.Grupo10.EcoMarketSpa;

import com.Grupo10.EcoMarketSpa.Model.MetodoPago;
import com.Grupo10.EcoMarketSpa.Repository.MetodoPagoRepository;
import com.Grupo10.EcoMarketSpa.Service.MetodoPagoService;
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
public class MetodoPagoTest {

    @Mock
    private MetodoPagoRepository metodoPagoRepository;

    @InjectMocks
    private MetodoPagoService metodoPagoService;

    private MetodoPago metodoPago;

    @BeforeEach
    void setUp() {
        MetodoPago metodoPago1T = new MetodoPago();
        metodoPago1T.setIdMetodoPago(1);
        metodoPago1T.setTipo("Tarjeta");
        metodoPago1T.setDetalles("Visa 1234");

        metodoPago = metodoPago1T;
    }

    @Test
    void testFindAll() {

        when(metodoPagoRepository.findAll()).thenReturn(List.of(metodoPago));

        List<MetodoPago> metodoPagos = metodoPagoRepository.findAll();

        assertNotNull(metodoPagos);
        assertEquals(1, metodoPagos.size());
    }

    @Test
    void testFindById() {

        when(metodoPagoRepository.existsById(1)).thenReturn(true);
        when(metodoPagoRepository.findById(1)).thenReturn(Optional.of(metodoPago));

        String result = metodoPagoService.getMetodoPagoById(1);

        assertNotNull(result);
        assertTrue(result.contains("Tarjeta"));
    }

    @Test
    void testSave() {
        // Given
        when(metodoPagoRepository.save(metodoPago)).thenReturn(metodoPago);
        // When
        String result = metodoPagoService.addMetodoPago(metodoPago);  // ✅
        // Then
        assertNotNull(result);
        assertEquals("El metodo de pago se agrego correctamente", result);
        verify(metodoPagoRepository, times(1)).save(metodoPago);
    }

    @Test
    void testDeleteById() {
        // Given
        doNothing().when(metodoPagoRepository).deleteById(1);
        // When
        metodoPagoService.deleteMetodoPago(1);
        // Then
        verify(metodoPagoRepository, times(1)).deleteById(1);

    }
}
