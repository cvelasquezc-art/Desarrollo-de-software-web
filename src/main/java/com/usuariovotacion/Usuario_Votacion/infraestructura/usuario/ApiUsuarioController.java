package com.usuariovotacion.Usuario_Votacion.infraestructura.usuario;

import com.usuariovotacion.Usuario_Votacion.aplicacion.Usuario.ServicioUsuario;
import com.usuariovotacion.Usuario_Votacion.dominio.Usuario.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ApiUsuarioController {
    private final ServicioUsuario servicio;

    @GetMapping
    public List<Usuario> listar() {
        return servicio.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtener(@PathVariable Long id) {
        return servicio.consultar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
        // {CODIFICAR_CLAVE}: Lógica eliminada, ahora reside en ServicioUsuario
        Usuario creado = servicio.crear(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        if (!servicio.consultar(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        usuario.setId(id);
        // {CODIFICAR_CLAVE}: Lógica eliminada, ahora reside en ServicioUsuario
        servicio.actualizar(usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}


