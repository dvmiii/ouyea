package com.Grupo10.EcoMarketSpa;

import com.Grupo10.EcoMarketSpa.Model.Permiso;
import com.Grupo10.EcoMarketSpa.Repository.PermisoRepository;
import com.Grupo10.EcoMarketSpa.Service.PermisoService;
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
public class PermisoTest {

    @Mock
    private PermisoRepository permisoRepository;

    @InjectMocks
    private PermisoService permisoService;

    private Permiso permiso;

    @BeforeEach
    void setUp() {
        Permiso permiso1T= new Permiso();
        permiso1T.setIdPermiso(1);
        permiso1T.setNombre("VER_PRODUCTOS");
        permiso1T.setDescripcion("Permite ver los productos del inventario");

        permiso = permiso1T;
    }

    @Test
    void testFindAll() {

        when(permisoRepository.findAll()).thenReturn(List.of(permiso));

        List<Permiso> permisos = permisoRepository.findAll();

        assertNotNull(permisos);
        assertEquals(1, permisos.size());
    }

    @Test
    void testFindById() {

        when(permisoRepository.existsById(1)).thenReturn(true);
        when(permisoRepository.findById(1)).thenReturn(Optional.of(permiso));

        String result = permisoService.getPermisosById(1);

        assertNotNull(result);
        assertTrue(result.contains("VER_PRODUCTOS"));
    }

    @Test
    void testSave() {
        // Given
        when(permisoRepository.save(permiso)).thenReturn(permiso);
        // When
        String result = permisoService.addPermisos(permiso);  // ✅
        // Then
        assertNotNull(result);
        assertEquals("El permiso se agrego correctamente", result);
        verify(permisoRepository, times(1)).save(permiso);
    }

    @Test
    void testDeleteById() {
        // Given
        doNothing().when(permisoRepository).deleteById(1);
        // When
        permisoService.deletePermiso(1);
        // Then
        verify(permisoRepository, times(1)).deleteById(1);

    }
}
