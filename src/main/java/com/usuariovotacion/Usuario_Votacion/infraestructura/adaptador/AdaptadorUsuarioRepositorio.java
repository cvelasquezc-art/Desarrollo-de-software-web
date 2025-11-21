package com.usuariovotacion.Usuario_Votacion.infraestructura.adaptador;

import com.usuariovotacion.Usuario_Votacion.dominio.Usuario.Usuario;
import com.usuariovotacion.Usuario_Votacion.dominio.Usuario.UsuarioRepositorio;
import com.usuariovotacion.Usuario_Votacion.infraestructura.persistencia.JpaUsuarioEntity;
import com.usuariovotacion.Usuario_Votacion.infraestructura.persistencia.JpaUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdaptadorUsuarioRepositorio implements UsuarioRepositorio {
    private final JpaUsuarioRepository jpa;

    @Override
    public Usuario crear(Usuario usuario) {
        JpaUsuarioEntity entity = JpaUsuarioEntity.fromDomain(usuario);
        entity = jpa.save(entity);
        return entity.toDomain();
    }

    @Override
    public void actualizar(Usuario usuario) { JpaUsuarioEntity entity = JpaUsuarioEntity.fromDomain(usuario);
        jpa.save(entity);
    }

    @Override
    public void eliminar(Long id) {
        jpa.deleteById(id);
    }

    @Override
    public Optional<Usuario> consultar(Long id) {
        return jpa.findById(id).map(JpaUsuarioEntity::toDomain);
    }

    @Override
    public List<Usuario> listar() {
        return jpa.findAll().stream().map(JpaUsuarioEntity::toDomain).toList();
    }

    @Override
    public Optional<Usuario> buscarPorNombre(String nombre) {
        return jpa.findByNombre(nombre).map(JpaUsuarioEntity::toDomain);
    }
}