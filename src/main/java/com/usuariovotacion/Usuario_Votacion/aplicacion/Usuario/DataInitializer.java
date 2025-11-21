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
            Usuario admin = new Usuario(null, "{noop}1738", "admin", "ADMIN");
            servicioUsuario.crear(admin);
            System.out.println("Usuario admin creado con clave: 1738 ");
        }
    }
}