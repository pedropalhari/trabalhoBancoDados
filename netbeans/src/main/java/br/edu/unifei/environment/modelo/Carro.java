package br.edu.unifei.environment.modelo;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Carro implements Serializable {

    @Id
    private int numero;
    private String cor;
    private float potencia;
    private boolean kers;

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public boolean isKers() {
        return kers;
    }

    public void setKers(boolean kers) {
        this.kers = kers;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public float getPotencia() {
        return potencia;
    }

    public void setPotencia(float potencia) {
        this.potencia = potencia;
    }

}
