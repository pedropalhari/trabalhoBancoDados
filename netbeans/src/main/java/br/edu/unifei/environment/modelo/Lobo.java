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
public class Lobo extends Animal<Coelho, Lobo> {

    public Lobo() {
        this.classePresa = Coelho.class;
        this.classeCompanheiro = Lobo.class;
    }

    @Override
    public Lobo gerarFilho() {
        Lobo filho = new Lobo();
        filho.setX(this.x + 1);
        filho.setY(this.y);
        filho.setVida(50);
        filho.setAtaque(15);
        filho.setFome(40);
        filho.setMovimento(4);
        filho.setDesejo(10);
        filho.setMundo(this.mundo);

        return filho;
    }
}
