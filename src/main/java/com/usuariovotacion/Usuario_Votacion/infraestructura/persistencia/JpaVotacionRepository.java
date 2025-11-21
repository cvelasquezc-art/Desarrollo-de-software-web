package com.usuariovotacion.Usuario_Votacion.infraestructura.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaVotacionRepository extends JpaRepository<JpaVotacionEntity, Long> {

}

