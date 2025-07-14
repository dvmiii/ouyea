package com.Grupo10.EcoMarketSpa;

import com.Grupo10.EcoMarketSpa.Model.Rol;
import com.Grupo10.EcoMarketSpa.Repository.RolRepository;
import com.Grupo10.EcoMarketSpa.Service.RolService;
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
public class RolTest {

    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private RolService rolService;

    private Rol rol;

    @BeforeEach
    void setUp() {
        Rol rol1T= new Rol();
        rol1T.setIdRol(1);
        rol1T.setNombreRol("Administrador");

        rol = rol1T;
    }

    @Test
    void testFindAll() {

        when(rolRepository.findAll()).thenReturn(List.of(rol));

        List<Rol> rols = rolRepository.findAll();

        assertNotNull(rols);
        assertEquals(1, rols.size());
    }

    @Test
    void testFindById() {

        when(rolRepository.existsById(1)).thenReturn(true);
        when(rolRepository.findById(1)).thenReturn(Optional.of(rol));

        String result = rolService.getRolById(1);

        assertNotNull(result);
        assertTrue(result.contains("Administrador"));
    }

    @Test
    void testSave() {
        // Given
        when(rolRepository.save(rol)).thenReturn(rol);
        // When
        String result = rolService.addRol(rol);  // ✅
        // Then
        assertNotNull(result);
        assertEquals("El rol se agrego correctamente", result);
        verify(rolRepository, times(1)).save(rol);
    }

    @Test
    void testDeleteById() {
        // Given
        doNothing().when(rolRepository).deleteById(1);
        // When
        rolService.deleteRol(1);
        // Then
        verify(rolRepository, times(1)).deleteById(1);

    }
}
