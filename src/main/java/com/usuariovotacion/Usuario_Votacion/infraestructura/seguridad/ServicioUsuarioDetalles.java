package com.usuariovotacion.Usuario_Votacion.infraestructura.seguridad;

import com.usuariovotacion.Usuario_Votacion.dominio.Usuario.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServicioUsuarioDetalles implements UserDetailsService {
    private final UsuarioRepositorio repo;

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        var usuario = repo.buscarPorNombre(nombre).orElseThrow(() -> new UsernameNotFoundException
                ("Usuario no encontrado: " + nombre));
        return new DetallesUsuario(usuario);
    }
}
