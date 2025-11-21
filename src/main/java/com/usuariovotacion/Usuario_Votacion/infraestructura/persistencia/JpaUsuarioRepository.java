package com.usuariovotacion.Usuario_Votacion.infraestructura.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUsuarioRepository extends JpaRepository<JpaUsuarioEntity, Long> {
    Optional<JpaUsuarioEntity> findByNombre(String nombre);
}
