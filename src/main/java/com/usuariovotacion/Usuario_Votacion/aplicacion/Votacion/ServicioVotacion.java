package com.usuariovotacion.Usuario_Votacion.aplicacion.Votacion;

import com.usuariovotacion.Usuario_Votacion.dominio.Votacion.Votacion;
import com.usuariovotacion.Usuario_Votacion.dominio.Votacion.VotacionRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicioVotacion {
    private final VotacionRepositorio repo;
    public Votacion crear(Votacion v) {return repo.crear(v);}
    public void actualizar(Votacion v) {repo.actualizar(v);}
    public void eliminar(Long id) {repo.eliminar(id);}
    public Optional<Votacion> consultar(Long id) {return repo.consultar(id);}
    public List<Votacion> listar() { return repo.listar(); }
}
