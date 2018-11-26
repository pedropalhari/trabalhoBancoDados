/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.environment.modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author pedro
 */
@Entity
public class Arvore extends Planta<Arvore> {

    public List<Arvore> gerarBroto() {
        List<Arvore> brotoList = new ArrayList<>();
        if (this.x + 1 < 20) {
            Arvore broto = new Arvore();
            broto.setX(this.x + 1);
            broto.setY(this.y);
            broto.setVida(10);

        }

        return brotoList;
    }

}
