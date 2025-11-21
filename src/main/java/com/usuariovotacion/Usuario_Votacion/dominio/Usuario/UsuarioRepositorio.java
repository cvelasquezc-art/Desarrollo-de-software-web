package com.usuariovotacion.Usuario_Votacion.dominio.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositorio {
    Usuario crear(Usuario u);
    void actualizar(Usuario u);
    void eliminar(Long id);
    Optional<Usuario> consultar(Long id);
    List<Usuario> listar();
    Optional<Usuario> buscarPorNombre(String nombre);
}
