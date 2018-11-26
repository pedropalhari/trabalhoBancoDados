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
public abstract class Animal<S extends Ser, A extends Animal> extends Ser {

    int ataque;
    int movimento;
    int fome;
    int desejo;
    int gestacao;
    Class<S> classePresa;
    Class<A> classeCompanheiro;

    @Override
    public void update() {
        if (this.vida <= 100) {
            this.vida += 1;
        }

        if (this.fome <= 100) {
            this.fome += 2;
            this.desejo += 4;
        } else {
            this.vida -= 8;
        }

        //Vai caçar
        if (this.fome >= 40) {
            System.out.println("Ta com fome");

            Ser presaMaisProxima = this.acharPresaMaisProxima();

            if (presaMaisProxima == null) {
                this.andar(); //Nao tem presa, anda ate morrer de fome
            } else {
                this.perseguir(presaMaisProxima.getX(), presaMaisProxima.getY());
                this.comer(presaMaisProxima);
            }
        } else if (this.desejo >= 40) {
            System.out.println("Ta com desejo");

            A parceiroMaisProximo = (A) this.acharParceiroMaisProximo();

            System.out.println("Parceiro " + parceiroMaisProximo);

            if (parceiroMaisProximo == null) {
                this.andar(); //Nao tem presa, anda ate morrer de fome
            } else {
                this.perseguir(parceiroMaisProximo.getX(), parceiroMaisProximo.getY());
                this.reproduzir(parceiroMaisProximo);
            }
        } else {
            this.andar();
        }

        if (this.gestacao > 1) {
            this.gestacao -= 1;
        } else if (this.gestacao == 1) {
            this.mundo.getSeres().add(this.gerarFilho());
            this.gestacao -= 1;
        }
    }

    //Dou um findNearest no DAO e vou tentando comer, se a 
    //condiçao se satisfazer tira vida
    public void comer(Ser comida) {
        //Se estiver do lado, tira vida
        if (Math.abs(comida.getX() - this.x) < 1
                && Math.abs(comida.getY() - this.y) < 1) {
            comida.setVida(comida.getVida() - this.ataque);
            this.vida += this.ataque / 2;
            this.fome -= this.ataque / 2;
        }
    }

    //Dou um findNearest no DAO e vou tentando comer, se a 
    //condiçao se satisfazer tira vida
    public void reproduzir(A parceiro) {
        //Se estiver do lado, tira vida
        if (Math.abs(parceiro.getX() - this.x) < 1
                && Math.abs(parceiro.getY() - this.y) < 1) {

            parceiro.setDesejo(0);
            this.desejo = 0;
            this.gestacao = 3;
        }
    }

    public abstract A gerarFilho(); //Necessario para que as classes gerem seus filhos

    public void andar() {
        //8 quadrados de deslocamento
        int randX = (int) (Math.random() * this.movimento - this.movimento / 2);
        int randY = (int) (Math.random() * this.movimento - this.movimento / 2);

        System.out.println(this.movimento + " " + randX + " " + randY);

        this.x += randX;
        this.y += randY;

        if (this.x > 20) {
            this.x = 20;
        }
        if (this.y > 20) {
            this.y = 20;
        }
        if (this.x < 0) {
            this.x = 0;
        }
        if (this.y < 0) {
            this.y = 0;
        }
    }

    public void perseguir(int ax, int ay) {
        //Faz a direçao do vetor de deslocamento, 1 ou -1
        if (this.x == ax && this.y == ay) {
            return; //Em cima da presa
        }

        int dirX;
        int dirY;

        if (Math.abs(this.x - ax) == 0) {
            dirX = 0;
        } else {
            dirX = (this.x - ax) / Math.abs(this.x - ax);
        }

        if (Math.abs(this.y - ay) == 0) {
            dirY = 0;
        } else {
            dirY = (this.y - ay) / Math.abs(this.y - ay);
        }

        this.x -= dirX;
        this.y -= dirY;
    }

    public Ser acharPresaMaisProxima() {
        Ser presaMaisProxima = null;

        //Escaneia os arredores dos seres um por um
        for (Ser ser : this.mundo.getSeres()) {
            if (ser.getClass() == classePresa) {
                //Pega a primeira presa
                if (presaMaisProxima == null) {
                    presaMaisProxima = (Ser) ser;
                } else if (distancia(this.x, ser.getX(), this.y, ser.getY()) //Checa a distancia e pega a mais proxima
                        < distancia(this.x, presaMaisProxima.getX(), this.y, presaMaisProxima.getY())) {
                    presaMaisProxima = (Ser) ser;
                }
            }
        }

        return presaMaisProxima;
    }

    public Animal acharParceiroMaisProximo() {
        Animal parceiroMaisProximo = null;

        //Escaneia os arredores dos seres um por um
        for (Ser ser : this.mundo.getSeres()) {
            if (ser.getClass() == classeCompanheiro && ser != this) {
                //Pega a primeira presa
                if (parceiroMaisProximo == null) {
                    parceiroMaisProximo = (Animal) ser;
                } else if (distancia(this.x, ser.getX(), this.y, ser.getY()) //Checa a distancia e pega a mais proxima
                        < distancia(this.x, parceiroMaisProximo.getX(), this.y, parceiroMaisProximo.getY())) {
                    parceiroMaisProximo = (Animal) ser;
                }
            }
        }

        return parceiroMaisProximo;
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
}
