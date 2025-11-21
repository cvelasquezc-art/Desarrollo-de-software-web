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

    public Usuario crear(Usuario u) { return repo.crear(u); }
    public void actualizar(Usuario u) {repo.actualizar(u);}
    public void eliminar(Long id) { repo.eliminar(id); }
    public Optional<Usuario> consultar(Long id) { return repo.consultar(id); }
    public List<Usuario> listar() { return repo.listar(); }
    public Optional<Usuario> buscarPorNombre(String nombre) { return repo.buscarPorNombre(nombre); }

    }
