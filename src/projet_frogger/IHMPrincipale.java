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
import java.awt.event.KeyEvent;
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
    private BufferedImage imageMap, imageMapResize, imagePerso, imagePersoResize;
    private int[] dimEcran = new int[2];

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
        jPanel1.remove(jButtonFacile);
        jPanel1.remove(jButtonMoyen);
        jPanel1.remove(jButtonDifficile);
        jPanel1.remove(jButtonSound);
        jPanel1.remove(jButtonNoSound);

        try {
            imageMap = ImageIO.read(fichierMap); //chargement
            imagePerso = ImageIO.read(fichierPerso);
            imageMapResize = a.resize(imageMap, dimEcran[0], dimEcran[1]);
            imagePersoResize = a.resize(imagePerso, (int) (dimEcran[0] * 0.11), (int) (dimEcran[1] * 0.07));
        } catch (IOException ex) {
            System.out.println("fichier introuvable");
        }
        Timer t = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //code exécuté toutes les 3000 ms
                Random hasard = new Random();
                //xPerso = hasard.nextInt(500);
                //yPerso = hasard.nextInt(500);
                //jPanel1.repaint();
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
                g.drawImage(imagePersoResize, xPerso, yPerso, null);
            }
        }
        ;
        jButtonAide = new javax.swing.JButton();
        jButtonDifficulte = new javax.swing.JButton();
        jButtonReglage = new javax.swing.JButton();
        jButtonFacile = new javax.swing.JButton();
        jButtonMoyen = new javax.swing.JButton();
        jButtonDifficile = new javax.swing.JButton();
        jButtonJouer = new javax.swing.JButton();
        jButtonNoSound = new javax.swing.JButton();
        jButtonSound = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
        });

        jButtonAide.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jButtonAide.setText("Aide");

        jButtonDifficulte.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jButtonDifficulte.setText("Difficulté");
        jButtonDifficulte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonDifficulteMouseClicked(evt);
            }
        });
        jButtonDifficulte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDifficulteActionPerformed(evt);
            }
        });

        jButtonReglage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bouton/boutonReglage.jpg"))); // NOI18N
        jButtonReglage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonReglageMouseClicked(evt);
            }
        });

        jButtonFacile.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jButtonFacile.setText("FACILE");
        jButtonFacile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonFacileMouseClicked(evt);
            }
        });
        jButtonFacile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFacileActionPerformed(evt);
            }
        });

        jButtonMoyen.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jButtonMoyen.setText("MOYEN");
        jButtonMoyen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonMoyenMouseClicked(evt);
            }
        });

        jButtonDifficile.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jButtonDifficile.setText("DIFFICILE");
        jButtonDifficile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDifficileActionPerformed(evt);
            }
        });

        jButtonJouer.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jButtonJouer.setText("Jouer");
        jButtonJouer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonJouerMouseClicked(evt);
            }
        });
        jButtonJouer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonJouerActionPerformed(evt);
            }
        });

        jButtonNoSound.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jButtonNoSound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bouton/son coupé.png"))); // NOI18N
        jButtonNoSound.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonNoSoundMouseClicked(evt);
            }
        });
        jButtonNoSound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNoSoundActionPerformed(evt);
            }
        });

        jButtonSound.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jButtonSound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bouton/son ouvert.png"))); // NOI18N
        jButtonSound.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonSoundMouseClicked(evt);
            }
        });
        jButtonSound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSoundActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonDifficile, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonFacile, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonMoyen, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(112, 112, 112)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAide, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonJouer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDifficulte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(628, 628, 628)
                        .addComponent(jButtonReglage, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonNoSound, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSound, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonMoyen)
                            .addComponent(jButtonAide)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonNoSound, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addComponent(jButtonSound, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonFacile)
                                    .addComponent(jButtonJouer))
                                .addGap(211, 211, 211)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonDifficulte)
                                    .addComponent(jButtonDifficile))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jButtonReglage, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(550, Short.MAX_VALUE))
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

    private int xPerso = 0, yPerso = 0;

    public void jPanel1KeyPressed(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                xPerso = xPerso + 10;
                break;
            case KeyEvent.VK_LEFT:
                xPerso = xPerso - 10;
                break;
            case KeyEvent.VK_UP:
                yPerso = yPerso - 10;
                break;
            case KeyEvent.VK_DOWN:
                yPerso = yPerso + 10;
                break;
        }
        jPanel1.repaint();
    }

    private void jButtonDifficulteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDifficulteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonDifficulteActionPerformed

    private void jButtonDifficulteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDifficulteMouseClicked
        jButtonFacile.setBounds((dimEcran[0] - 400) / 2, 100, 400, 100);
        jButtonMoyen.setBounds((dimEcran[0] - 400) / 2, 300, 400, 100);
        jButtonDifficile.setBounds((dimEcran[0] - 400) / 2, 500, 400, 100);
        jButtonJouer.setVisible(false);
        jButtonAide.setVisible(false);
        jButtonDifficulte.setVisible(false);
        jButtonReglage.setVisible(false);
        jButtonSound.setVisible(false);
        jButtonNoSound.setVisible(false);

        jPanel1.add(jButtonFacile);
        jPanel1.add(jButtonMoyen);
        jPanel1.add(jButtonDifficile);


    }//GEN-LAST:event_jButtonDifficulteMouseClicked

    private void jButtonFacileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFacileActionPerformed
        
    }//GEN-LAST:event_jButtonFacileActionPerformed

    private void jButtonDifficileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDifficileActionPerformed

    }//GEN-LAST:event_jButtonDifficileActionPerformed

    private void jButtonMoyenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonMoyenMouseClicked

    }//GEN-LAST:event_jButtonMoyenMouseClicked

    private void jButtonFacileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonFacileMouseClicked

    }//GEN-LAST:event_jButtonFacileMouseClicked

    private void jButtonReglageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonReglageMouseClicked
        jButtonSound.setBounds((dimEcran[0] - 200) / 2, 100, 200, 200);
        jButtonNoSound.setBounds((dimEcran[0] - 200) / 2, 400, 200, 200);
        jButtonJouer.setVisible(false);
        jButtonAide.setVisible(false);
        jButtonDifficulte.setVisible(false);
        jButtonReglage.setVisible(false);

        jPanel1.add(jButtonSound);
        jPanel1.add(jButtonNoSound);

    }//GEN-LAST:event_jButtonReglageMouseClicked

    private void jButtonJouerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonJouerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonJouerActionPerformed

    private void jButtonNoSoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNoSoundActionPerformed
        
    }//GEN-LAST:event_jButtonNoSoundActionPerformed

    private void jButtonSoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSoundActionPerformed
        
    }//GEN-LAST:event_jButtonSoundActionPerformed

    private void jButtonJouerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonJouerMouseClicked
        jButtonJouer.setVisible(false);
        jButtonAide.setVisible(false);
        jButtonDifficulte.setVisible(false);
        jButtonReglage.setVisible(false);
    }//GEN-LAST:event_jButtonJouerMouseClicked

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
            java.util.logging.Logger.getLogger(IHMPrincipale.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IHMPrincipale.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IHMPrincipale.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IHMPrincipale.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

    private void traitementBorne() {
        if (xPerso < -50) {
            xPerso = 1500;
        }
        if (xPerso > 1500) {
            xPerso = -50;
        }
        if (yPerso < -50) {
            yPerso = 750;
        }
        if (yPerso > 750) {
            yPerso = -50;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAide;
    private javax.swing.JButton jButtonDifficile;
    private javax.swing.JButton jButtonDifficulte;
    private javax.swing.JButton jButtonFacile;
    private javax.swing.JButton jButtonJouer;
    private javax.swing.JButton jButtonMoyen;
    private javax.swing.JButton jButtonNoSound;
    private javax.swing.JButton jButtonReglage;
    private javax.swing.JButton jButtonSound;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
