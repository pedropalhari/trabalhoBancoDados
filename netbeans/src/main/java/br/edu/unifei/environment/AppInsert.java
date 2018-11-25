/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.environment;

import br.edu.unifei.environment.dao.FonteDados;
import br.edu.unifei.environment.dao.ArvoreDao;
import br.edu.unifei.environment.dao.SerDao;
import br.edu.unifei.environment.modelo.Arvore;
import javax.persistence.EntityManager;

/**
 *
 * @author windows
 */
public class AppInsert {

    public static void main(String args[]) {

        EntityManager entityManager
                = FonteDados.createEntityManager();
        //GrandePremioDao grDao = new GrandePremioDao(entityManager);
        SerDao serDao = new SerDao(entityManager);
        ArvoreDao arvoreDao = new ArvoreDao(entityManager);
        
        Arvore a = new Arvore();
        a.setX(3);
        a.setY(4);
        a.setVida(10);
        
        arvoreDao.create(a);
        
        serDao.findAll().forEach(cnsmr -> {
            System.out.println(cnsmr.getVida());
        });
        
        FonteDados.close();
        
    }
}
