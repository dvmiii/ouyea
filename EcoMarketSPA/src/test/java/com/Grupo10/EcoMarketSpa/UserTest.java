package com.Grupo10.EcoMarketSpa;

import com.Grupo10.EcoMarketSpa.Model.Usuario;
import com.Grupo10.EcoMarketSpa.Repository.UsuarioRepository;
import com.Grupo10.EcoMarketSpa.Service.UsuarioService;
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
public class UserTest {

    @Mock
    private UsuarioRepository useRepository;

    @InjectMocks
    private UsuarioService userService;

    private Usuario user;

    @BeforeEach
    void setUp() {
        Usuario user1T= new Usuario();
        user1T.setIdUsuario(1);
        user1T.setNombreUsuario("User1");
        user1T.setEmail("User.prueba@gmail.com");
        user1T.setPassword("Contraseña123");

        user = user1T;
    }

    @Test
    void testFindAll() {

        when(useRepository.findAll()).thenReturn(List.of(user));

        List<Usuario> usuario = useRepository.findAll();

        assertNotNull(usuario);
        assertEquals(1, usuario.size());
    }

    @Test
    void testFindById() {

        when(useRepository.existsById(1)).thenReturn(true);
        when(useRepository.findById(1)).thenReturn(Optional.of(user));

        String result = userService.getUserById(1);

        assertNotNull(result);
        assertTrue(result.contains("User1"));
    }

    @Test
    void testSave() {
        // Given
        when(useRepository.save(user)).thenReturn(user);
        // When
        String result = userService.addUser(user);  // ✅
        // Then
        assertNotNull(result);
        assertEquals("El usuario se agrego correctamente", result);
        verify(useRepository, times(1)).save(user);
    }

    @Test
    void testDeleteById() {
        // Given
        doNothing().when(useRepository).deleteById(1);
        // When
        userService.deleteUser(1);
        // Then
        verify(useRepository, times(1)).deleteById(1);

    }
}
