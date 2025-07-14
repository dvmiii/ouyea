package com.Grupo10.EcoMarketSpa;

import com.Grupo10.EcoMarketSpa.Model.ItemPedido;
import com.Grupo10.EcoMarketSpa.Repository.ItemOrderRepository;
import com.Grupo10.EcoMarketSpa.Service.ItemOrderService;
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
public class ItemOrderTest {

    @Mock
    private ItemOrderRepository itemOrderRepository;

    @InjectMocks
    private ItemOrderService itemOrderService;

    private ItemPedido itemOrder;

    @BeforeEach
    void setUp() {
        ItemPedido itemPedido1T = new ItemPedido();
        itemPedido1T.setIdItemPedido(1);
        itemPedido1T.setCantidad(2);
        itemPedido1T.setPrecioUnitario(2000);

        itemOrder = itemPedido1T;
    }

    @Test
    void testFindAll() {

        when(itemOrderRepository.findAll()).thenReturn(List.of(itemOrder));

        List<ItemPedido> itemOrders = itemOrderRepository.findAll();

        assertNotNull(itemOrders);
        assertEquals(1, itemOrders.size());
    }

    @Test
    void testFindById() {

        when(itemOrderRepository.existsById(1)).thenReturn(true);
        when(itemOrderRepository.findById(1)).thenReturn(Optional.of(itemOrder));

        String result = itemOrderService.getItemOrderById(1);

        assertNotNull(result);
        assertTrue(result.contains("2"));
    }

    @Test
    void testSave() {
        // Given
        when(itemOrderRepository.save(itemOrder)).thenReturn(itemOrder);
        // When
        String result = itemOrderService.addItmOrder(itemOrder);  // ✅
        // Then
        assertNotNull(result);
        assertEquals("Se agrego correctamente", result);
        verify(itemOrderRepository, times(1)).save(itemOrder);
    }

    @Test
    void testDeleteById() {
        // Given
        doNothing().when(itemOrderRepository).deleteById(1);
        // When
        itemOrderService.deleteItemOrder(1);
        // Then
        verify(itemOrderRepository, times(1)).deleteById(1);

    }
}
