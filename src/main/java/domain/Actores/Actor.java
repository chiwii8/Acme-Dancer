package domain.Actores;

import domain.DomainEntity;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;


@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity {

    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;
    private String codigoPostal;
    
    public Actor(){
        super();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @NotBlank
    public String getNombre() {
        return nombre;
    }

    @NotBlank
    public String getApellidos() {
        return apellidos;
    }

    @Email
    public String getCorreo() {
        return correo;
    }

    @NotBlank
    @Pattern(regexp="^(\\+\\d+\\s+)?(\\(\\d+\\)\\s+)?\\d{4,}")
    public String getTelefono() {
        return telefono;
    }

    @NotBlank
    @Pattern(regexp="\\w{5}")
    public String getCodigoPostal() {
        return codigoPostal;
    }

    
    

}
