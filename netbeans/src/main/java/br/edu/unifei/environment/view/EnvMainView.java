package br.edu.unifei.environment.view;

import br.edu.unifei.environment.dao.ArvoreDao;
import br.edu.unifei.environment.dao.CoelhoDao;
import br.edu.unifei.environment.dao.FonteDados;
import br.edu.unifei.environment.dao.MundoDao;
import br.edu.unifei.environment.modelo.Arvore;
import br.edu.unifei.environment.modelo.Coelho;
import br.edu.unifei.environment.modelo.Mundo;
import javax.persistence.EntityManager;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pedro
 */
public class EnvMainView {
    
    static boolean DEBUG = true;
    
    static void initialize(){
        EntityManager entityManager
                = FonteDados.createEntityManager();
        //GrandePremioDao grDao = new GrandePremioDao(entityManager);
        ArvoreDao arvoreDao = new ArvoreDao(entityManager);
        CoelhoDao coelhoDao = new CoelhoDao(entityManager);
        MundoDao mundoDao = new MundoDao(entityManager);
        
        Arvore a = new Arvore();
        a.setX(0);
        a.setY(0);
        a.setVida(10);
        
        arvoreDao.create(a);        
        
        Coelho c = new Coelho();
        c.setX(10);
        c.setY(10);
        c.setVida(50);
        c.setFome(35);
        
        coelhoDao.create(c);
        
        
        Mundo m = new Mundo();
        m.addSer(a);
        
        mundoDao.create(m);
    }
    
    static void print(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) throws InterruptedException {
        if(DEBUG)
            initialize();
        
        
        CrudFrame crudFrame = new CrudFrame();
        JFrame viewFrame = new JFrame("View");

        ViewPanel viewPanel = new ViewPanel();
        viewFrame.add(viewPanel);

        viewFrame.setVisible(true);
        viewFrame.setSize(410, 440);
        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        crudFrame.setVisible(true);
        crudFrame.setSize(410, 440);
        crudFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        while(true){
            Thread.sleep(1000);
            viewFrame.getContentPane().repaint();
        }
    }
}
