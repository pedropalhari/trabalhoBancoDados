/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.environment.modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author pedro
 */
@Entity
public class Mundo {

    @Id
    @GeneratedValue
    int id;

    @OneToMany
    List<Ser> seres = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Ser> getSeres() {
        return seres;
    }

    public void setSeres(List<Ser> seres) {
        this.seres = seres;
    }
    
    public void addSer(Ser s) {
        this.seres.add(s);
        s.setMundo(this);
    }

}
