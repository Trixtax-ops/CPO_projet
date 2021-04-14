/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_frogger;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author trist
 */
public class Obstacles {

    private int xObstacles;
    private int yObstacles;
    private BufferedImage imageObstacleResize;
    private int vitesse;

    public Obstacles(int xObstacles, int yObstacles, BufferedImage imageObstacleResize, int vitesse) {
        this.xObstacles = xObstacles;
        this.yObstacles = yObstacles;
        this.imageObstacleResize = imageObstacleResize;
        this.vitesse = vitesse;
    }

    public int getxObstacles() {
        return xObstacles;
    }

    public int getyObstacles() {
        return yObstacles;
    }

    public BufferedImage getImageObstacleResize() {
        return imageObstacleResize;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setxObstacles(int xObstacles) {
        this.xObstacles = xObstacles;
    }

    public void setyObstacles(int yObstacles) {
        this.yObstacles = yObstacles;
    }

    public void setImageObstacleResize(BufferedImage imageObstacleResize) {
        this.imageObstacleResize = imageObstacleResize;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }
            
}
