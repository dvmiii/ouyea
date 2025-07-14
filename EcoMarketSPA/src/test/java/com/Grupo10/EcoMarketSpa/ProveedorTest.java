package com.Grupo10.EcoMarketSpa;

import com.Grupo10.EcoMarketSpa.Model.Proveedor;
import com.Grupo10.EcoMarketSpa.Repository.ProveedorRepository;
import com.Grupo10.EcoMarketSpa.Service.ProveedorService;
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
public class ProveedorTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorService proveedorService;

    private Proveedor proveedor;

    @BeforeEach
    void setUp() {
        Proveedor proveedor1T= new Proveedor();
        proveedor1T.setIdproveedor(1);
        proveedor1T.setNombre("Distribuidora Verde");
        proveedor1T.setContacto("contacto@verde.cl");

        proveedor = proveedor1T;
    }

    @Test
    void testFindAll() {

        when(proveedorRepository.findAll()).thenReturn(List.of(proveedor));

        List<Proveedor> proveedores = proveedorRepository.findAll();

        assertNotNull(proveedores);
        assertEquals(1, proveedores.size());
    }

    @Test
    void testFindById() {

        when(proveedorRepository.existsById(1)).thenReturn(true);
        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedor));

        String result = proveedorService.getProveedorById(1);

        assertNotNull(result);
        assertTrue(result.contains("Distribuidora Verde"));
    }

    @Test
    void testSave() {
        // Given
        when(proveedorRepository.save(proveedor)).thenReturn(proveedor);
        // When
        String result = proveedorService.addProveedor(proveedor);  // ✅
        // Then
        assertNotNull(result);
        assertEquals("El permiso se agrego correctamente", result);
        verify(proveedorRepository, times(1)).save(proveedor);
    }

    @Test
    void testDeleteById() {
        // Given
        doNothing().when(proveedorRepository).deleteById(1);
        // When
        proveedorService.deleteProveedor(1);
        // Then
        verify(proveedorRepository, times(1)).deleteById(1);

    }
}
