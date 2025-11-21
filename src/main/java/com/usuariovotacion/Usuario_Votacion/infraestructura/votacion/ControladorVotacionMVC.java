package com.usuariovotacion.Usuario_Votacion.infraestructura.votacion;

import com.usuariovotacion.Usuario_Votacion.aplicacion.Votacion.ServicioVotacion;
import com.usuariovotacion.Usuario_Votacion.dominio.Votacion.Votacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ControladorVotacionMVC {

    private final ServicioVotacion servicio;

    @GetMapping("/votaciones")
    public String listar(Model model) {
        model.addAttribute("votaciones", servicio.listar());
        return "listarVotaciones";
    }

    @GetMapping("/votaciones/nueva")
    public String nuevaVotacion(Model model) {
        model.addAttribute("votacion", new Votacion());
        return "formVotacion";
    }

    @GetMapping("/votaciones/consultar/{id}")
    public String editarVotacion(@PathVariable Long id, Model model) {
        Optional<Votacion> votacion = servicio.consultar(id);
        model.addAttribute("votacion", votacion.orElse(new Votacion()));
        return "formVotacion";
    }

    @PostMapping("/votaciones/crear")
    public String guardarVotacion(@ModelAttribute Votacion votacion) {
        if (votacion.getId() == null) {
            servicio.crear(votacion);
        } else {
            servicio.actualizar(votacion);
        }
        return "redirect:/votaciones";
    }

    @GetMapping("/votaciones/eliminar/{id}")
    public String eliminarVotacion(@PathVariable Long id) {
        servicio.eliminar(id);
        return "redirect:/votaciones";
    }
}
