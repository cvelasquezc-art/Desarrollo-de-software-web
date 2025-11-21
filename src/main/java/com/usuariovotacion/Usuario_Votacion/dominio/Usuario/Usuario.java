package com.usuariovotacion.Usuario_Votacion.dominio.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private Long id;
    private String clave;
    private String nombre;
    private String rol;
}
