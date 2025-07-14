package com.Grupo10.EcoMarketSpa;

import com.Grupo10.EcoMarketSpa.Model.ItemCarrito;
import com.Grupo10.EcoMarketSpa.Repository.ItemCartRepository;
import com.Grupo10.EcoMarketSpa.Service.ItemCartService;
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
public class ItemCartTest {

    @Mock
    private ItemCartRepository itemCartRepository;

    @InjectMocks
    private ItemCartService itemCartService;

    private ItemCarrito itemCart;

    @BeforeEach
    void setUp() {
        ItemCarrito itemCart1T = new ItemCarrito();
        itemCart1T.setIdItemCarrito(1);
        itemCart1T.setCantidad(3);

        itemCart = itemCart1T;
    }

    @Test
    void testFindAll() {

        when(itemCartRepository.findAll()).thenReturn(List.of(itemCart));

        List<ItemCarrito> itemCarts = itemCartRepository.findAll();

        assertNotNull(itemCarts);
        assertEquals(1, itemCarts.size());
    }

    @Test
    void testFindById() {

        when(itemCartRepository.existsById(1)).thenReturn(true);
        when(itemCartRepository.findById(1)).thenReturn(Optional.of(itemCart));

        ItemCarrito result = itemCartService.getItemCartById(1);

        assertNotNull(result);
        assertEquals(3, result.getCantidad());
    }

    @Test
    void testSave() {
        // Given
        when(itemCartRepository.save(itemCart)).thenReturn(itemCart);
        // When
        ItemCarrito result = itemCartService.addItemCart(itemCart);  // ✅
        // Then
        assertNotNull(result);
        assertEquals("Se agrego correctamente", result);
        verify(itemCartRepository, times(1)).save(itemCart);
    }

    @Test
    void testDeleteById() {
        // Given
        doNothing().when(itemCartRepository).deleteById(1);
        // When
        itemCartService.deleteItemCart(1);
        // Then
        verify(itemCartRepository, times(1)).deleteById(1);

    }
}
