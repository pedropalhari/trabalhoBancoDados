/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.environment.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author pedro
 */
public abstract class Animal<S extends Ser, A extends Animal> extends Ser {

    int ataque;
    int movimento;
    int fome;
    int desejo;
    S presa;
    A companheiro;

    public void update() {
        if (this.vida <= 100) {
            this.vida += 1;
        }
        
        if(this.fome <= 100){
            this.fome += 2;
        } else {
            this.vida -= 8;
        }

        //Vai caçar
        if (this.fome >= 40) {
            S presaMaisProxima = this.acharPresaMaisProxima();
            
            if(presaMaisProxima == null)
                this.andar(); //Nao tem presa, anda ate morrer de fome
            else
                this.perseguir(x, y);
        } else {
            this.andar();
        }
    }

    //Dou um findNearest no DAO e vou tentando comer, se a 
    //condiçao se satisfazer tira vida
    public void comer(S comida) {
        //Se estiver do lado, tira vida
        if (Math.abs(comida.getX() - this.x) <= 1
                && Math.abs(comida.getY() - this.y) <= 1) {
            comida.setVida(comida.getVida() - this.ataque);
            this.vida += this.ataque;
            this.fome -= this.ataque;
        }
    }

    //Dou um findNearest no DAO e vou tentando comer, se a 
    //condiçao se satisfazer tira vida
    public boolean reproduzir(A parceiro) {
        //Se estiver do lado, tira vida
        if (Math.abs(parceiro.getX() - this.x) == 1
                && Math.abs(parceiro.getY() - this.y) == 1) {

            parceiro.setDesejo(0);
            this.desejo = 0;
            return true;
        }

        return false;
    }

    public void andar() {
        //8 quadrados de deslocamento
        int randX = (int) (Math.random() * this.movimento - this.movimento / 2);
        int randY = (int) (Math.random() * this.movimento - this.movimento / 2);

        this.x += randX;
        this.y += randY;
    }

    public void perseguir(int x, int y) {
        //Faz a direçao do vetor de deslocamento, 1 ou -1
        int dirX = (this.x - x) / Math.abs(this.x - x);
        int dirY = (this.y - y) / Math.abs(this.y - y);

        this.x += dirX;
        this.y += dirY;
    }

    public S acharPresaMaisProxima() {
        S presaMaisProxima = null;
        
        //Escaneia os arredores dos seres um por um
        for (Ser ser : this.mundo.getSeres()) {
            if (ser.getClass() == this.presa.getClass()) {
                //Pega a primeira presa
                if (presaMaisProxima == null) {
                    presaMaisProxima = (S) ser;
                } else if (distancia(this.x, ser.getX(), this.y, ser.getY()) //Checa a distancia e pega a mais proxima
                        < distancia(this.x, presaMaisProxima.getX(), this.y, presaMaisProxima.getY())) {
                    presaMaisProxima = (S) ser;
                }
            }
        }

        return presaMaisProxima;
    }

    public double distancia(int x1, int x2, int y1, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getMovimento() {
        return movimento;
    }

    public void setMovimento(int movimento) {
        this.movimento = movimento;
    }

    public int getFome() {
        return fome;
    }

    public void setFome(int fome) {
        this.fome = fome;
    }

    public int getDesejo() {
        return desejo;
    }

    public void setDesejo(int desejo) {
        this.desejo = desejo;
    }

    public S getPresa() {
        return presa;
    }

    public void setPresa(S presa) {
        this.presa = presa;
    }

    public A getCompanheiro() {
        return companheiro;
    }

    public void setCompanheiro(A companheiro) {
        this.companheiro = companheiro;
    }
}
