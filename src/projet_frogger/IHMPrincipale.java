/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_frogger;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class IHMPrincipale extends javax.swing.JFrame {

    private File fichierMap = new File("src/images/map.jpg");
    private File fichierPerso = new File("src/images/perso1.png");
    private BufferedImage imageMap, imageMapResize, imagePerso;
    private int[] dimEcran = new int[2];
    private int xPerso=1000, yPerso=500;

    public IHMPrincipale() {
        initComponents();
        Affichage a = new Affichage();
        dimEcran = a.dimensionEcran();
        jPanel1.setFocusable(true);
        setBounds(0, 0, dimEcran[0] + 15, dimEcran[1]);
        jButtonJouer.setBounds((dimEcran[0] - 400) / 2, 100, 400, 100);
        jButtonAide.setBounds((dimEcran[0] - 400) / 2, 300, 400, 100);
        jButtonDifficulte.setBounds((dimEcran[0] - 400) / 2, 500, 400, 100);
        jButtonReglage.setBounds(dimEcran[0] - 50, dimEcran[1] - 107, 50, 50);
        jPanel1.add(jButtonJouer);
        jPanel1.add(jButtonAide);
        jPanel1.add(jButtonDifficulte);
        jPanel1.add(jButtonReglage);
        try {
            imageMap = ImageIO.read(fichierMap); //chargement
            imageMapResize = a.resize(imageMap, dimEcran[0], dimEcran[1]);
        } catch (IOException ex) {
            System.out.println("fichier introuvable");
        }
        Timer t = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //code exécuté toutes les 3000 ms
                Random hasard = new Random();
                //xGhost = hasard.nextInt(1000);
                //yGhost = hasard.nextInt(1000);
                //Flute
                jPanel1.repaint();
            }
        });
        t.start(); //lancer le timer
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel(){
            public void paintComponent(Graphics g)
            {
                g.drawImage(imageMapResize, 0, 0, null);
            }
        }
        ;
        jButtonJouer = new javax.swing.JButton();
        jButtonAide = new javax.swing.JButton();
        jButtonDifficulte = new javax.swing.JButton();
        jButtonReglage = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonJouer.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jButtonJouer.setText("Jouer");
        jButtonJouer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonJouerActionPerformed(evt);
            }
        });

        jButtonAide.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jButtonAide.setText("Aide");

        jButtonDifficulte.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jButtonDifficulte.setText("Difficulté");
        jButtonDifficulte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDifficulteActionPerformed(evt);
            }
        });

        jButtonReglage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bouton/boutonReglage.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(205, 205, 205)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonDifficulte, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAide, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonJouer, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addComponent(jButtonReglage, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jButtonJouer)
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAide)
                    .addComponent(jButtonReglage, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addComponent(jButtonDifficulte)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDifficulteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDifficulteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonDifficulteActionPerformed

    private void jButtonJouerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonJouerActionPerformed
        jButtonJouer.setVisible(false);
        jButtonAide.setVisible(false);
        jButtonDifficulte.setVisible(false);
        jButtonReglage.setVisible(false);
    }//GEN-LAST:event_jButtonJouerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IHMPrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IHMPrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IHMPrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IHMPrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new IHMPrincipale().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAide;
    private javax.swing.JButton jButtonDifficulte;
    private javax.swing.JButton jButtonJouer;
    private javax.swing.JButton jButtonReglage;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
