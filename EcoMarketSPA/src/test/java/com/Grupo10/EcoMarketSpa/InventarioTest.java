package com.Grupo10.EcoMarketSpa;

import com.Grupo10.EcoMarketSpa.Model.Inventario;
import com.Grupo10.EcoMarketSpa.Repository.InventarioRepository;
import com.Grupo10.EcoMarketSpa.Service.InventarioService;
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
public class InventarioTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioService inventarioService;

    private Inventario inven;

    @BeforeEach
    void setUp() {
        Inventario inven1T = new Inventario();
        inven1T.setId(1);
        inven1T.setTienda("Sucursal Providencia");

        inven = inven1T;
    }

    @Test
    void testFindAll() {

        when(inventarioRepository.findAll()).thenReturn(List.of(inven));

        List<Inventario> inventario = inventarioRepository.findAll();

        assertNotNull(inventario);
        assertEquals(1, inventario.size());
    }

    @Test
    void testFindById() {

        when(inventarioRepository.existsById(1)).thenReturn(true);
        when(inventarioRepository.findById(1)).thenReturn(Optional.of(inven));

        String result = inventarioService.getInventarioById(1);

        assertNotNull(result);
        assertTrue(result.contains("Sucursal Providencia"));
    }

    @Test
    void testSave() {
        // Given
        when(inventarioRepository.save(inven)).thenReturn(inven);
        // When
        String result = inventarioService.addInventario(inven);  // ✅
        // Then
        assertNotNull(result);
        assertEquals("El invetario se agrego correctamente", result);
        verify(inventarioRepository, times(1)).save(inven);
    }

    @Test
    void testDeleteById() {
        // Given
        doNothing().when(inventarioRepository).deleteById(1);
        // When
        inventarioService.deleteInventario(1);
        // Then
        verify(inventarioRepository, times(1)).deleteById(1);

    }
}
