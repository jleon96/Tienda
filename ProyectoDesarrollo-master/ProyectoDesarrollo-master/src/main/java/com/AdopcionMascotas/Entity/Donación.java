package com.AdopcionMascotas.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "donacion")
public class Donación implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private int numero_tajeta;
    private int codigo_seguridad;
    private int monto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumero_tajeta() {
        return numero_tajeta;
    }

    public void setNumero_tajeta(int numero_tajeta) {
        this.numero_tajeta = numero_tajeta;
    }

    public int getCodigo_seguridad() {
        return codigo_seguridad;
    }

    public void setCodigo_seguridad(int codigo_seguridad) {
        this.codigo_seguridad = codigo_seguridad;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }
    
    
}
