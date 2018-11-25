/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.environment.dao;

import br.edu.unifei.environment.modelo.Coelho;
import javax.persistence.EntityManager;

/**
 *
 * @author pedro
 */
public class CoelhoDao extends GenericoDao<Coelho, Integer>{

    public CoelhoDao(EntityManager em) {
        super(em);
    }
    
}
