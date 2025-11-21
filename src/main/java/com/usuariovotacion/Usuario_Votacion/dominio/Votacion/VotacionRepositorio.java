package com.usuariovotacion.Usuario_Votacion.dominio.Votacion;

import java.util.List;
import java.util.Optional;

public interface VotacionRepositorio {
    Votacion crear(Votacion v);
    void actualizar(Votacion v);
    void eliminar(Long id);
    Optional<Votacion> consultar(Long id);
    List<Votacion> listar();
}
