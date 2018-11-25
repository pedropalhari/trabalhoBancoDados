package br.edu.unifei.environment.dao;

import br.edu.unifei.environment.modelo.Ser;
import javax.persistence.EntityManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pedro
 */
public class SerDao extends GenericoDao<Ser, Integer> {
    public SerDao(EntityManager em) {
        super(em);
    }
}
