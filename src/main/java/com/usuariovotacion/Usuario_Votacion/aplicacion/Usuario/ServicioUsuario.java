package com.usuariovotacion.Usuario_Votacion.aplicacion.Usuario;

import com.usuariovotacion.Usuario_Votacion.dominio.Usuario.Usuario;
import com.usuariovotacion.Usuario_Votacion.dominio.Usuario.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicioUsuario {
    private final UsuarioRepositorio repo;

    private Usuario codificarClave(Usuario u) {
        // 🔑 {CODIFICAR_CLAVE} Lógica movida y saneada.
        // Protegemos contra null antes de llamar a métodos de String
        if (u.getClave() == null || u.getClave().isBlank()) {
            throw new IllegalArgumentException("La clave no puede estar vacía.");
        }

        if (!u.getClave().startsWith("{noop}")) {
            u.setClave("{noop}" + u.getClave());
        }
        return u;
    }

    public Usuario crear(Usuario u) {
        u = codificarClave(u); // Aplicar la codificación aquí
        return repo.crear(u);
    }

    public void actualizar(Usuario u) {
        // Asumimos que la actualización de la clave es opcional.
        // Solo codificamos si se proporciona una nueva clave.
        if (u.getClave() != null && !u.getClave().isBlank()) {
            u = codificarClave(u);
        }
        repo.actualizar(u);
    }

    public void eliminar(Long id) { repo.eliminar(id); }
    public Optional<Usuario> consultar(Long id) { return repo.consultar(id); }
    public List<Usuario> listar() { return repo.listar(); }
    public Optional<Usuario> buscarPorNombre(String nombre) { return repo.buscarPorNombre(nombre); }
}
