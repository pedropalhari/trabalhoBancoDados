/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.environment.modelo;
import javax.persistence.Entity;

/**
 *
 * @author pedro
 */

@Entity
public abstract class Planta extends Ser{
    @Override
    public void update() {        
        this.vida += 2;
    }    
}
