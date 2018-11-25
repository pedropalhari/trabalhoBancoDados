/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.environment.dao;

import br.edu.unifei.environment.modelo.Mundo;
import javax.persistence.EntityManager;

/**
 *
 * @author pedro
 */
public class MundoDao extends GenericoDao<Mundo, Integer> {

    public MundoDao(EntityManager em) {
        super(em);
    }
}
