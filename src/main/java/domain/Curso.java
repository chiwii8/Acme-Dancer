/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import domain.Enumeraciones.CursoNivel;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author alejandro
 */
@Entity
@Access(AccessType.PROPERTY)
public class Curso extends DomainEntity{
    public Curso(){
        super();
    }
    
    private String idAcademia;
    private String titulo;
    private Date fechaInicio;
    private Date fechaFin;
    private String diaSemana;
    private int hora;
    private int minuto;
    private CursoNivel nivel;

    public void setIdAcademia(String idAcademia) {
        this.idAcademia = idAcademia;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public void setNivel(CursoNivel nivel) {
        this.nivel = nivel;
    }

    @NotBlank
    public String getIdAcademia() {
        return idAcademia;
    }

    @NotBlank
    public String getTitulo() {
        return titulo;
    }

    @NotNull
    public Date getFechaInicio() {
        return fechaInicio;
    }

    @NotNull
    public Date getFechaFin() {
        return fechaFin;
    }

    @NotBlank
    public String getDiaSemana() {
        return diaSemana;
    }

    @Range(min = 0, max = 23)
    public int getHora() {
        return hora;
    }

    @Range(min = 0, max = 59)
    public int getMinuto() {
        return minuto;
    }

    @NotNull
    @Valid
    public CursoNivel getNivel() {
        return nivel;
    }
    
    
    
}
