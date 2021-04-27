/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_frogger;

import java.awt.image.BufferedImage;

/**
 *
 * @author trist
 */
public class Radeaux {

    private int xRadeaux;
    private final int yRadeaux;
    private BufferedImage imageRadeauxResize;
    private int vitesse;

    public Radeaux(int xRadeaux, int yRadeaux, BufferedImage imageRadeauxResize, int vitesse) {
        this.xRadeaux = xRadeaux;
        this.yRadeaux = yRadeaux;
        this.imageRadeauxResize = imageRadeauxResize;
        this.vitesse = vitesse;
    }

    public int getxRadeaux() {
        return xRadeaux;
    }

    public int getyRadeaux() {
        return yRadeaux;
    }

    public BufferedImage getImageRadeauxResize() {
        return imageRadeauxResize;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setxRadeaux(int xRadeaux) {
        this.xRadeaux = xRadeaux;
    }

    public void setImageRadeauxResize(BufferedImage imageRadeauxResize) {
        this.imageRadeauxResize = imageRadeauxResize;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

}
