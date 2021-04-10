/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_frogger;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author trist
 */
public class Affichage {

    private final File fichierMap = new File("src/images/map.jpg");
    private final int[] dimMap = new int[2];
    private BufferedImage imageMap;

    public int[] dimensionImage() {
        try {
            imageMap = ImageIO.read(fichierMap); //chargement
        } catch (IOException ex) {
            System.out.println("fichier introuvable");
        }
        dimMap[0] = imageMap.getWidth();
        dimMap[1] = imageMap.getHeight();

        return dimMap;
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return dimg;
    }

}
