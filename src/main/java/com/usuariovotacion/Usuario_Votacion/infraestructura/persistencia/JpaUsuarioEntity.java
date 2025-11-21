package com.usuariovotacion.Usuario_Votacion.infraestructura.persistencia;

import com.usuariovotacion.Usuario_Votacion.dominio.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class JpaUsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private String clave;

    @Column(nullable = false)
    private String rol;

    public Usuario toDomain() {
        return new Usuario(this.id, this.clave, this.nombre, this.rol);
    }

    public static JpaUsuarioEntity fromDomain(Usuario u) {
        JpaUsuarioEntity entity = new JpaUsuarioEntity();
        entity.setId(u.getId());
        entity.setClave(u.getClave());
        entity.setNombre(u.getNombre());
        entity.setRol(u.getRol());
        return entity;
    }
}