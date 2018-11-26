package br.edu.unifei.environment.view;

import br.edu.unifei.environment.dao.ArvoreDao;
import br.edu.unifei.environment.dao.CoelhoDao;
import br.edu.unifei.environment.dao.FonteDados;
import br.edu.unifei.environment.dao.MundoDao;
import br.edu.unifei.environment.dao.SerDao;
import br.edu.unifei.environment.modelo.Arvore;
import br.edu.unifei.environment.modelo.Mundo;
import java.awt.Dimension;
import java.awt.Toolkit;
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

    static void initialize() {
        EntityManager entityManager
                = FonteDados.createEntityManager();
        //GrandePremioDao grDao = new GrandePremioDao(entityManager);
        ArvoreDao arvoreDao = new ArvoreDao(entityManager);
        CoelhoDao coelhoDao = new CoelhoDao(entityManager);
        Mundo m = new Mundo();
//
//        Arvore a = new Arvore();
//        a.setX(16);
//        a.setY(10);
//        a.setVida(10);
//
//        Arvore a2 = new Arvore();
//        a2.setX(0);
//        a2.setY(3);
//        a2.setVida(55);
//
//        Coelho c1 = new Coelho();
//        c1.setX(3);
//        c1.setY(3);
//        c1.setVida(50);
//        c1.setAtaque(15);
//        c1.setFome(0);
//        c1.setMovimento(4);
//        c1.setDesejo(40);
//
//        Lobo l1 = new Lobo();
//        l1.setX(10);
//        l1.setY(10);
//        l1.setVida(50);
//        l1.setAtaque(15);
//        l1.setFome(40);
//        l1.setMovimento(4);
//        l1.setDesejo(40);

//
//        Coelho c2 = new Coelho();
//        c2.setX(0);
//        c2.setY(0);
//        c2.setVida(50);
//        c2.setAtaque(15);
//        c2.setFome(0);
//        c2.setMovimento(4);
//        c2.setDesejo(40);
//        m.addSer(a);
//        m.addSer(c1);
//        m.addSer(l1);
//        //m.addSer(c2);
//        m.addSer(a2);
        MundoDao mundoDao = new MundoDao(entityManager);
        mundoDao.create(m);
    }

    static void print(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) throws InterruptedException {
        if (DEBUG) {
            initialize();
        }

        EntityManager entityManager
                = FonteDados.createEntityManager();

        Mundo m = new Mundo();

        Arvore a2 = new Arvore();
        a2.setX(0);
        a2.setY(3);
        a2.setVida(55);
        m.addSer(a2);

        MundoDao mundoDao = new MundoDao(entityManager);
        mundoDao.create(m);

        SerDao serDao = new SerDao(entityManager);

        CrudFrame crudFrame = new CrudFrame(entityManager, mundoDao, m, serDao);
        JFrame viewFrame = new JFrame("View");

        ViewPanel viewPanel = new ViewPanel(entityManager, mundoDao, m, serDao);
        viewFrame.add(viewPanel);

        crudFrame.setVisible(true);
        crudFrame.setSize(410, 440);
        crudFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        viewFrame.setVisible(true);
        viewFrame.setSize(410, 440);
        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        viewFrame.setLocation(dim.width / 2 - viewFrame.getSize().width / 2, dim.height / 2 - viewFrame.getSize().height / 2);

        while (true) {
            System.err.println("SAAA");
            Thread.sleep(1000);
            viewFrame.getContentPane().repaint();
        }
    }
}
