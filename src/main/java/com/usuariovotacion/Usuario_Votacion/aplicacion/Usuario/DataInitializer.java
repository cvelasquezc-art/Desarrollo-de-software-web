package com.usuariovotacion.Usuario_Votacion.aplicacion.Usuario;

import com.usuariovotacion.Usuario_Votacion.dominio.Usuario.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ServicioUsuario servicioUsuario;

    public DataInitializer(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    @Override
    public void run(String... args) {
        if (servicioUsuario.buscarPorNombre("admin").isEmpty()) {
            // Pasamos la clave SIN codificar. El ServicioUsuario se encargará de añadir {noop}.
            Usuario admin = new Usuario(null, "1738", "admin", "ADMIN");
            servicioUsuario.crear(admin);
            System.out.println("Usuario admin creado con clave: 1738 (codificada por el servicio)");
        }
    }
}