/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import domain.Enumeraciones.SolicitudEstado;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 *
 * @author alejandro
 */
@Entity
@Access(AccessType.PROPERTY)
public class Solicitud extends DomainEntity{
    public Solicitud(){
        super();
    }
    
    private Date fecha;
    private SolicitudEstado estado;

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setEstado(SolicitudEstado estado) {
        this.estado = estado;
    }

    @NotNull
    @Past
    public Date getFecha() {
        return fecha;
    }

    @NotNull
    @Valid
    public SolicitudEstado getEstado() {
        return estado;
    }
    
    
}
