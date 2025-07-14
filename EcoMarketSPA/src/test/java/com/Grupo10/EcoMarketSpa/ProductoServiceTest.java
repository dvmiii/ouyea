package com.Grupo10.EcoMarketSpa;

import com.Grupo10.EcoMarketSpa.Model.Producto;
import com.Grupo10.EcoMarketSpa.Repository.ProductoRepository;
import com.Grupo10.EcoMarketSpa.Service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    MockMvc mockMvc;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;

    @BeforeEach
    void setUp() {
        Producto producto1T = new Producto();
        producto1T.setIdProducto(1);
        producto1T.setNombreProducto("Manzanas Orgánicas");
        producto1T.setDescripcionProducto("Bolsa de 1kg");
        producto1T.setPrecioProducto(2500.0);
        producto1T.setStockProducto(120);
        producto1T.setCategoriaProducto("Frutas");

        producto = producto1T;
    }


    @Test
    void testFindAll() {

        when(productoRepository.findAll()).thenReturn(List.of(producto));

        List<Producto> productos = productoRepository.findAll();

        assertNotNull(productos);
        assertEquals(1, productos.size());
    }

    @Test
    void testFindById() {

        when(productoRepository.existsById(1)).thenReturn(true);
        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));

        String result = productoService.getProductoById(1);

        assertNotNull(result);
        assertTrue(result.contains("Manzanas Orgánicas"));
    }

    @Test
    void testSave() {
        // Given
        when(productoRepository.save(producto)).thenReturn(producto);
        // When
        String result = productoService.addProducto(producto);  // ✅
        // Then
        assertNotNull(result);
        assertEquals("El producto se agrego correctamente", result);
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void testDeleteById() {
        // Given
        doNothing().when(productoRepository).deleteById(1);
        // When
        productoService.deleteProducto(1);
        // Then
        verify(productoRepository, times(1)).deleteById(1);

    }
}

