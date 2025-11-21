package com.usuariovotacion.Usuario_Votacion.infraestructura.adaptador;

import com.usuariovotacion.Usuario_Votacion.dominio.Votacion.Votacion;
import com.usuariovotacion.Usuario_Votacion.dominio.Votacion.VotacionRepositorio;
import com.usuariovotacion.Usuario_Votacion.infraestructura.persistencia.JpaVotacionEntity;
import com.usuariovotacion.Usuario_Votacion.infraestructura.persistencia.JpaVotacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdaptadorVotacionRepositorio implements VotacionRepositorio {
    private final JpaVotacionRepository jpa;

    @Override
    public Votacion crear(Votacion votacion) { JpaVotacionEntity entity = JpaVotacionEntity.fromDomain(votacion);
        entity = jpa.save(entity);
        return entity.toDomain();
    }

    @Override
    public void actualizar(Votacion votacion) { JpaVotacionEntity entity = JpaVotacionEntity.fromDomain(votacion);
        jpa.save(entity);
    }

    @Override
    public void eliminar(Long id) {
        jpa.deleteById(id);
    }

    @Override
    public Optional<Votacion> consultar(Long id) {
        return jpa.findById(id).map(JpaVotacionEntity::toDomain);
    }

    @Override
    public List<Votacion> listar() {
        return jpa.findAll().stream().map(JpaVotacionEntity::toDomain).toList();
    }
}
