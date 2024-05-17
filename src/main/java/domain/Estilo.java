/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.Collection;
import java.util.HashSet;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

/**
 *
 * @author alejandro
 */
@Entity
@Access(AccessType.PROPERTY)
public class Estilo extends DomainEntity{
    public Estilo(){
        super();
        
        imagenes = new HashSet<String>();
        videos = new HashSet<String>();
    }
    
    private String nombre;
    private String descripcion;
    private Collection<String> imagenes;
    private Collection<String> videos;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImagenes(Collection<String> imagenes) {
        this.imagenes = imagenes;
    }

    public void setVideos(Collection<String> videos) {
        this.videos = videos;
    }

    @NotBlank
    public String getNombre() {
        return nombre;
    }

    @NotBlank
    public String getDescripcion() {
        return descripcion;
    }

    @URL
    public Collection<String> getImagenes() {
        return imagenes;
    }

    @URL
    public Collection<String> getVideos() {
        return videos;
    }
    
    
}
