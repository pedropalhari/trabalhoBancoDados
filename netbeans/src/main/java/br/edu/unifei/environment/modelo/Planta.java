/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.environment.modelo;

import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author pedro
 */
@Entity
public abstract class Planta<P extends Planta> extends Ser {

    @Override
    public void update() {
        this.vida += 2;

        if (vida >= 60) {
            this.vida -= 50;
            this.gerarBroto().forEach(broto -> {
                boolean espacoLivre = true;

                for (Ser ser : this.getMundo().getSeres()) {
                    if (ser.getX() == broto.getX()
                            && ser.getY() == broto.getY()) {
                        espacoLivre = false;
                    }
                };

                if (espacoLivre) {
                    this.getMundo().addSer(broto);
                }
            });
        }
    }

    public abstract List<P> gerarBroto();
}
