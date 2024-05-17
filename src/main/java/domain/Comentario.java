/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author alejandro
 */
@Entity
@Access(AccessType.PROPERTY)
public class Comentario extends DomainEntity{
    public Comentario(){
        super();
    }
    
    private Date fechaRealizacion;
    private String texto;

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @NotNull
    //@Temporal(TemporalType.DATE)
    @Past
    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    @NotBlank
    @Length(min = 0, max = 140)
    public String getTexto() {
        return texto;
    }
    
    
    
}
