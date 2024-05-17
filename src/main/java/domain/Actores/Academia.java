/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain.Actores;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.*;

/**
 *
 * @author alejandro
 */
@Entity
@Access(AccessType.PROPERTY)
public class Academia extends Actor{
    private String idAcademia;
    private String nombreComercial;
    
    public Academia(){
        super();
    }

    public void setIdAcademia(String idAcademia) {
        this.idAcademia = idAcademia;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    @NotBlank
    public String getIdAcademia() {
        return idAcademia;
    }

    @NotBlank
    public String getNombreComercial() {
        return nombreComercial;
    }
    
    
}
