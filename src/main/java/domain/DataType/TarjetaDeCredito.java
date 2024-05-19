/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain.dataType;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.*;

/**
 *
 * @author alejandro
 */
@Embeddable
@Access(AccessType.PROPERTY)
public class TarjetaDeCredito {
    public TarjetaDeCredito() {
        super();
    }

    private String titular;
    private String marca;
    private String numero;
    private int mesCaducidad;
    private int anioCaducidad;
    private int cvv;

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setMesCaducidad(int mesCaduucidad) {
        this.mesCaducidad = mesCaduucidad;
    }

    public void setAnioCaducidad(int anioCaducidad) {
        this.anioCaducidad = anioCaducidad;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    @NotBlank
    public String getTitular() {
        return titular;
    }

    @NotBlank
    public String getMarca() {
        return marca;
    }

    @NotNull
    @CreditCardNumber
    public String getNumero() {
        return numero;
    }

    @Range(min = 1, max = 12)
    public int getMesCaducidad() {
        return mesCaducidad;
    }

    @Min(2024)
    public int getAnioCaducidad() {
        return anioCaducidad;
    }

    @Range(min = 100, max = 999)
    public int getCvv() {
        return cvv;
    }

}
