package com.usuariovotacion.Usuario_Votacion.infraestructura.seguridad;

import com.usuariovotacion.Usuario_Votacion.aplicacion.Usuario.ServicioUsuario;
import com.usuariovotacion.Usuario_Votacion.dominio.Usuario.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class ControladorSeguridadMVC {
    private final ServicioUsuario servicio;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/forgot-password")
    public String mostrarForgotPassword() {
        return "forgotPassword";
    }

    @PostMapping("/forgot-password")
    public String procesarForgotPassword(@RequestParam String nombre, Model model) {
        Optional<Usuario> opcional = servicio.buscarPorNombre(nombre);

        if (opcional.isEmpty()) {
            model.addAttribute("error", "No existe un usuario con ese nombre");
            return "forgotPassword";
        }

        String nuevaClave = generarClaveAleatoria();
        Usuario usuario = opcional.get();
        usuario.setClave("{noop}" + nuevaClave);
        servicio.actualizar(usuario);

        model.addAttribute("success",
                "¡Contraseña cambiada con éxito! Tu nueva contraseña es: "+ nuevaClave +
                        " Entra con ella y cámbiala por una propia desde el formulario de edición.");

        return "forgotPassword";
    }

    private String generarClaveAleatoria() {
        String clave = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(clave.charAt(random.nextInt(clave.length())));
        }
        return sb.toString();
    }
}
