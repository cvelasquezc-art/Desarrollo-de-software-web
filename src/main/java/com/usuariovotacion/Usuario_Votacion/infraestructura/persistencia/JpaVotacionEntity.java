package com.usuariovotacion.Usuario_Votacion.infraestructura.persistencia;

import com.usuariovotacion.Usuario_Votacion.dominio.Votacion.Votacion;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "votaciones")
@Data
public class JpaVotacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public Votacion toDomain() {
        return new Votacion(this.id, this.fecha, this.partidoPolitico, this.candidato, this.votante, this.pais,
                this.departamento, this.ciudad, this.mesa, this.puestoPolitico, this.duracion, this.numeroTarjeton);
    }

    public static JpaVotacionEntity fromDomain(Votacion v) {
        JpaVotacionEntity entity = new JpaVotacionEntity();
        entity.setId(v.getId());
        entity.setFecha(v.getFecha());
        entity.setPartidoPolitico(v.getPartidoPolitico());
        entity.setCandidato(v.getCandidato());
        entity.setVotante(v.getVotante());
        entity.setPais(v.getPais());
        entity.setDepartamento(v.getDepartamento());
        entity.setCiudad(v.getCiudad());
        entity.setMesa(v.getMesa());
        entity.setPuestoPolitico(v.getPuestoPolitico());
        entity.setDuracion(v.getDuracion());
        entity.setNumeroTarjeton(v.getNumeroTarjeton());
        return entity;
    }
}
