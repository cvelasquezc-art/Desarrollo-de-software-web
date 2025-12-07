package com.usuariovotacion.Usuario_Votacion.infraestructura.votacion;

import com.usuariovotacion.Usuario_Votacion.aplicacion.Votacion.ServicioVotacion;
import com.usuariovotacion.Usuario_Votacion.dominio.Votacion.Votacion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votaciones")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ApiVotacionController {

    private final ServicioVotacion servicio;

    // GET /api/votaciones → listar todas
    @GetMapping
    public List<Votacion> listar() {
        return servicio.listar();
    }

    // GET /api/votaciones/5 → obtener una
    @GetMapping("/{id}")
    public ResponseEntity<Votacion> obtener(@PathVariable Long id) {
        return servicio.consultar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/votaciones → crear nueva
    @PostMapping
    public ResponseEntity<Votacion> crear(@RequestBody Votacion votacion) {
        Votacion creada = servicio.crear(votacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    // PUT /api/votaciones/5 → actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Votacion> actualizar(@PathVariable Long id, @RequestBody Votacion votacion) {
        if (!servicio.consultar(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        votacion.setId(id);
        servicio.actualizar(votacion);
        return ResponseEntity.ok(votacion);
    }

    // DELETE /api/votaciones/5 → eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
