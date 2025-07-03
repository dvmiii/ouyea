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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto();
        producto.setIdProducto(1);
        producto.setNombreProducto("Laptop");
        producto.setDescripcionProducto("Laptop HP");
        producto.setPrecioProducto(899.99);
        producto.setStockProducto(10);
        producto.setCategoriaProducto("Alimentacion");
    }

    @Test
    void testFindAll() {
        // Given
        List<Producto> productos = Arrays.asList(producto);
        when(productoRepository.findAll()).thenReturn(productos);

        // When
        List<Producto> result = productoService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).getNombreProducto());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Given
        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));

        // When
        Optional<Producto> result = productoService.findById(1);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Laptop", result.get().getNombreProducto());
        verify(productoRepository, times(1)).findById(1);
    }

    @Test
    void testSave() {
        // Given
        when(productoRepository.save(producto)).thenReturn(producto);

        // When
        Producto result = productoService.save(producto);

        // Then
        assertNotNull(result);
        assertEquals("Laptop", result.getNombreProducto());
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

