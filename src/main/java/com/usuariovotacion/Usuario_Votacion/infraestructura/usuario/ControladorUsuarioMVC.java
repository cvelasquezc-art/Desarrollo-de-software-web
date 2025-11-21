package com.usuariovotacion.Usuario_Votacion.infraestructura.usuario;

import com.usuariovotacion.Usuario_Votacion.aplicacion.Usuario.ServicioUsuario;
import com.usuariovotacion.Usuario_Votacion.dominio.Usuario.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ControladorUsuarioMVC {

    private final ServicioUsuario servicio;

    @GetMapping("/usuarios")
    public String listar(Model model) {
        model.addAttribute("usuarios", servicio.listar());
        return "listarUsuarios";
    }

    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "formUsuario";
    }

    @PostMapping("/usuarios/crear")
    public String guardar(@ModelAttribute Usuario usuario, @RequestParam(required = false) String clave) {
        boolean esNuevo = (usuario.getId() == null);
        if (clave != null && !clave.isBlank()) {
            usuario.setClave("{noop}" + clave);
        } else if (!esNuevo) {
            usuario.setClave( servicio.consultar(usuario.getId()).map(Usuario::getClave).orElse(usuario.getClave()));
        }
        if (esNuevo) {
            servicio.crear(usuario);
        } else {
            servicio.actualizar(usuario);
        }
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/consultar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        Optional<Usuario> usuario = servicio.consultar(id);
        model.addAttribute("usuario", usuario.orElse(new Usuario()));
        return "formUsuario";
        }

    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return "redirect:/usuarios";
    }
}
