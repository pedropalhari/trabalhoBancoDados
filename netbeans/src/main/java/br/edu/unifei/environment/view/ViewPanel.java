/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.environment.view;

import br.edu.unifei.environment.dao.FonteDados;
import br.edu.unifei.environment.dao.MundoDao;
import br.edu.unifei.environment.dao.SerDao;
import br.edu.unifei.environment.modelo.Arvore;
import br.edu.unifei.environment.modelo.Coelho;
import br.edu.unifei.environment.modelo.Lobo;
import br.edu.unifei.environment.modelo.Mundo;
import br.edu.unifei.environment.modelo.Ser;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JPanel;

/**
 *
 * @author pedro
 */
class DrawBlock {

    String caractere;
    Color cor;

    DrawBlock(String c, Color cl) {
        this.caractere = c;
        this.cor = cl;
    }
}

public class ViewPanel extends JPanel {

    SerDao serDao;
    MundoDao mundoDao;

    String[] colorGradient = {"#F39582", "#DA9178", "#C18E6F",
        "#A88A66", "#8F875D", "#768353",
        "#5D804A", "#447C41", "#2B7938", "#13762F"};

    public ViewPanel() {
        EntityManager entityManager
                = FonteDados.createEntityManager();
        //GrandePremioDao grDao = new GrandePremioDao(entityManager);
        this.serDao = new SerDao(entityManager);
        this.mundoDao = new MundoDao(entityManager);
    }

    Color hex2Rgb(String colorStr) {
        return new Color(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));
    }

    DrawBlock getDrawBlock(Ser ser) {
        String c;
        Color col;

        if (ser.getClass() == Arvore.class) {
            c = "A";
        } else if (ser.getClass() == Coelho.class) {
            c = "C";
        } else if (ser.getClass() == Lobo.class) {
            c = "L";
        } else {
            c = "*";
        }

        c += ser.getId();

        int colorGradientIndex = ser.getVida() / 10;

        if (colorGradientIndex < 10) {
            col = hex2Rgb(colorGradient[colorGradientIndex]);
        } else {
            col = hex2Rgb(colorGradient[9]);
        }

        return new DrawBlock(c, col);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                g.setColor(Color.WHITE);
                g.fillRect(x * 20 + 3, y * 20 + 4, 20, 20);

                //Border
                g.setColor(new Color(0.8f, 0.8f, 0.8f));
                g.drawRect(x * 20 + 3, y * 20 + 4, 20, 20);
            }
        }

        g.setFont(new Font("Arial Bold", Font.BOLD, 12));

        List<Ser> auxList = new ArrayList<>();

        Mundo m = (Mundo) mundoDao.findAll().toArray()[0];

        for (Ser ser : m.getSeres()) {
            ser.update();

            if (ser.getVida() <= 0) {
                m.getSeres().remove(ser);
            }

        };

        mundoDao.update(m);

        //s.bulkUpdate(auxList);
        for (Object serAux : serDao.findAll().toArray()) {
            Ser ser = (Ser) serAux;
            DrawBlock db = this.getDrawBlock(ser);
            g.setColor(db.cor);
            g.drawString(db.caractere, 20 * ser.getX() + 5, 20 * ser.getY() + 20);
        };

    }
}
