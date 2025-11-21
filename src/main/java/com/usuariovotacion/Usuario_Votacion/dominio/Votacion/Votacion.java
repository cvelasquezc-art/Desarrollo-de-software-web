package com.usuariovotacion.Usuario_Votacion.dominio.Votacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Votacion {
    private Long id;
    private String fecha;
    private String partidoPolitico;
    private String candidato;
    private String votante;
    private String pais;
    private String departamento;
    private String ciudad;
    private String mesa;
    private String puestoPolitico;
    private String duracion;
    private Integer numeroTarjeton;
}
