/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_frogger;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class IHMPrincipale extends javax.swing.JFrame {

    private File fichierMap = new File("src/images/map.jpg");
    private File fichierPerso = new File("src/images/perso1.png");
    private File fichierObstacle1 = new File("src/images/bouleDeFeu.png");
    private File fichierObstacle2 = new File("src/images/bouleDeFeu2.png");
    private File fichierExclamation = new File("src/images/exclamation.png");
    private File fichierRocket = new File("src/images/rocket.png");
    private File fichierRadeau1 = new File("src/images/navette3.png");
    private File fichierRadeau2 = new File("src/images/navette3.2.png");
    private BufferedImage imageMap, imageMapResize, imagePerso, imagePersoResize;
    private BufferedImage imageObstacle1, imageObstacle1Resize;
    private BufferedImage imageObstacle2, imageObstacle2Resize;
    private BufferedImage imageRocket, imageRocketResize;
    private BufferedImage imageExclamation, imageExclamationResize;
    private BufferedImage imageRadeau1, imageRadeau1Resize;
    private BufferedImage imageRadeau2, imageRadeau2Resize;
    private int[] dimImage = new int[2];
    private int xPerso, yPerso;
    private boolean boolJouer;
    private ArrayList<Obstacles> listeObs = new ArrayList<>();
    private BufferedImage imageExclamationAff = null;
    private int xObstacleM, vitesseM = 3;
    private Timer t;
    private ArrayList<Radeaux> listeRad = new ArrayList<>();
    private boolean boolRad = true;
    private Clip clip;
    private AudioInputStream audioIn;
    private int difficulte = 2;
    private int compteurVie = 3;

    public IHMPrincipale() {
        initComponents();
        Affichage a = new Affichage();
        jPanel1.setFocusable(true);
        dimImage = a.dimensionImage();
        setBounds(0, 0, 694, 730);

        jPanel1.remove(jButtonJouer);
        jPanel1.remove(jButtonAide);
        jPanel1.remove(jButtonDifficulte);
        jPanel1.remove(jButtonReglage);
        jPanel1.remove(jLabelCompteur);

        xPerso = (int) (dimImage[0] / 2 - (dimImage[0] / 10) / 2);
        yPerso = (int) (dimImage[1] - (dimImage[1] / 14));
        jButtonJouer.setBounds((dimImage[0] / 2) - 150, 100, 300, 75);
        jButtonAide.setBounds((dimImage[0] / 2) - 150, 300, 300, 75);
        jButtonDifficulte.setBounds((dimImage[0] / 2) - 150, 500, 300, 75);
        jButtonReglage.setBounds(dimImage[0] - 50, dimImage[1] - 50, 50, 50);

        jPanel1.add(jButtonJouer);
        jPanel1.add(jButtonAide);
        jPanel1.add(jButtonDifficulte);
        jPanel1.add(jButtonReglage);
        jPanel1.remove(jButtonFacile);
        jPanel1.remove(jButtonMoyen);
        jPanel1.remove(jButtonDifficile);
        jPanel1.remove(jButtonSoundOn);
        jPanel1.remove(jButtonNoSound);

        try {
            imageMap = ImageIO.read(fichierMap); //chargement
            imagePerso = ImageIO.read(fichierPerso);
            imageObstacle1 = ImageIO.read(fichierObstacle1);
            imageObstacle2 = ImageIO.read(fichierObstacle2);
            imageExclamation = ImageIO.read(fichierExclamation);
            imageRocket = ImageIO.read(fichierRocket);
            imageRadeau1 = ImageIO.read(fichierRadeau1);
            imageRadeau2 = ImageIO.read(fichierRadeau2);
            imageMapResize = Affichage.resize(imageMap, dimImage[0], dimImage[1]);
            imagePersoResize = Affichage.resize(imagePerso, dimImage[0] / 10, dimImage[1] / 13);
            imageObstacle1Resize = Affichage.resize(imageObstacle1, (int) (dimImage[0] / 9), (int) (dimImage[1] / 12));
            imageObstacle2Resize = Affichage.resize(imageObstacle2, (int) (dimImage[0] / 9), (int) (dimImage[1] / 12));
            imageExclamationResize = Affichage.resize(imageExclamation, (int) (dimImage[0] / 9), (int) (dimImage[1] / 12));
            imageRocketResize = Affichage.resize(imageRocket, (int) (dimImage[0] / 9), (int) (dimImage[1] / 12));
            imageRadeau1Resize = Affichage.resize(imageRadeau1, (int) (dimImage[0] / 9) + 40, (int) (dimImage[1] / 12));
            imageRadeau2Resize = Affichage.resize(imageRadeau2, (int) (dimImage[0] / 9) + 40, (int) (dimImage[1] / 12));
        } catch (IOException ex) {
            System.out.println("fichier introuvable");
        }

        Obstacles obs1L1 = new Obstacles(-50,
                (int) dimImage[1] - 2 * (dimImage[1] / 13), imageObstacle1Resize, 4);
        Obstacles obs2L1 = new Obstacles((int) (dimImage[0] * 2 / 3) - 50,
                (int) dimImage[1] - 2 * (dimImage[1] / 13), imageObstacle1Resize, 4);
        Obstacles obs3L1 = new Obstacles((int) (dimImage[0] / 3) - 50,
                (int) dimImage[1] - 2 * (dimImage[1] / 13), imageObstacle1Resize, 4);
        listeObs.add(obs1L1);
        listeObs.add(obs2L1);
        listeObs.add(obs3L1);

        Obstacles obs1L2 = new Obstacles(dimImage[0],
                (int) (dimImage[1] - 3 * (dimImage[1] / 13)), imageObstacle2Resize, -4);
        Obstacles obs2L2 = new Obstacles(dimImage[0] / 2,
                (int) (dimImage[1] - 3 * (dimImage[1] / 13)), imageObstacle2Resize, -4);
        listeObs.add(obs1L2);
        listeObs.add(obs2L2);

        Obstacles obs1L3 = new Obstacles(-50,
                (int) dimImage[1] - 4 * (dimImage[1] / 13), imageObstacle1Resize, 5);
        Obstacles obs2L3 = new Obstacles((int) (dimImage[0] / 2) - 50,
                (int) dimImage[1] - 4 * (dimImage[1] / 13), imageObstacle1Resize, 5);
        listeObs.add(obs1L3);
        listeObs.add(obs2L3);

        Obstacles obs1L4 = new Obstacles(dimImage[0],
                (int) (dimImage[1] - 5 * (dimImage[1] / 13)), imageObstacle2Resize, -3);
        Obstacles obs2L4 = new Obstacles(dimImage[0] * 2 / 3,
                (int) (dimImage[1] - 5 * (dimImage[1] / 13)), imageObstacle2Resize, -3);
        Obstacles obs3L4 = new Obstacles(dimImage[0] / 3,
                (int) (dimImage[1] - 5 * (dimImage[1] / 13)), imageObstacle2Resize, -3);
        listeObs.add(obs1L4);
        listeObs.add(obs2L4);
        listeObs.add(obs3L4);

        Obstacles obs1L5 = new Obstacles(-50,
                (int) dimImage[1] - 6 * (dimImage[1] / 13), imageObstacle1Resize, 4);
        Obstacles obs2L5 = new Obstacles((int) (dimImage[0] / 2) - 50,
                (int) dimImage[1] - 6 * (dimImage[1] / 13), imageObstacle1Resize, 4);
        listeObs.add(obs1L5);
        listeObs.add(obs2L5);

        xObstacleM = -80;

        Radeaux rad1L1 = new Radeaux(-100,
                (int) dimImage[1] - 8 * (dimImage[1] / 13), imageRadeau1Resize, 4);
        Radeaux rad2L1 = new Radeaux((int) ((dimImage[0] + 100) * 2 / 3) - 100,
                (int) dimImage[1] - 8 * (dimImage[1] / 13), imageRadeau1Resize, 4);
        Radeaux rad3L1 = new Radeaux((int) ((dimImage[0] + 100) / 3) - 100,
                (int) dimImage[1] - 8 * (dimImage[1] / 13), imageRadeau1Resize, 4);
        listeRad.add(rad1L1);
        listeRad.add(rad2L1);
        listeRad.add(rad3L1);

        Radeaux rad1L2 = new Radeaux(-100,
                (int) dimImage[1] - 9 * (dimImage[1] / 13), imageRadeau2Resize, -3);
        Radeaux rad2L2 = new Radeaux((int) ((dimImage[0] + 100) / 2) - 100,
                (int) dimImage[1] - 9 * (dimImage[1] / 13), imageRadeau2Resize, -3);
        listeRad.add(rad1L2);
        listeRad.add(rad2L2);

        Radeaux rad1L3 = new Radeaux(-100,
                (int) dimImage[1] - 10 * (dimImage[1] / 13), imageRadeau1Resize, 5);
        Radeaux rad2L3 = new Radeaux((int) ((dimImage[0] + 100) * 2 / 3) - 100,
                (int) dimImage[1] - 10 * (dimImage[1] / 13), imageRadeau1Resize, 5);
        Radeaux rad3L3 = new Radeaux((int) ((dimImage[0] + 100) / 3) - 100,
                (int) dimImage[1] - 10 * (dimImage[1] / 13), imageRadeau1Resize, 5);
        listeRad.add(rad1L3);
        listeRad.add(rad2L3);
        listeRad.add(rad3L3);

        Radeaux rad1L4 = new Radeaux(-100,
                (int) dimImage[1] - 11 * (dimImage[1] / 13), imageRadeau2Resize, -2);
        Radeaux rad2L4 = new Radeaux((int) ((dimImage[0] + 100) / 2) - 100,
                (int) dimImage[1] - 11 * (dimImage[1] / 13), imageRadeau2Resize, -2);
        listeRad.add(rad1L4);
        listeRad.add(rad2L4);

        Radeaux rad1L5 = new Radeaux(-100,
                (int) dimImage[1] - 12 * (dimImage[1] / 13), imageRadeau1Resize, 4);
        Radeaux rad2L5 = new Radeaux((int) ((dimImage[0] + 100) / 2) - 100,
                (int) dimImage[1] - 12 * (dimImage[1] / 13), imageRadeau1Resize, 4);
        listeRad.add(rad1L5);
        listeRad.add(rad2L5);

        traitementMusique(true);

        t = new Timer(16, (ActionEvent e) -> {
            traitementObstacles();
            traitementMilieu();
            traitementRadeaux();
            jPanel1.repaint();
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
                for (int i = 0; i < listeRad.size(); i++) {
                    g.drawImage(listeRad.get(i).getImageRadeauxResize(),
                        listeRad.get(i).getxRadeaux(),
                        listeRad.get(i).getyRadeaux(), null);
                }
                g.drawImage(imagePersoResize, xPerso, yPerso, null);
                g.drawImage(imageRocketResize, xObstacleM, (int) dimImage[1] - 7 * (dimImage[1] / 13) + 10, null);
                g.drawImage(imageExclamationAff, -20, (int) dimImage[1] - 7 * (dimImage[1] / 13) + 10, null);
                for (int i = 0; i < listeObs.size(); i++) {
                    g.drawImage(listeObs.get(i).getImageObstacleResize(),
                        listeObs.get(i).getxObstacles(),
                        listeObs.get(i).getyObstacles(), null);
                }
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
        jButtonSoundOn = new javax.swing.JButton();
        jLabelCompteur = new javax.swing.JLabel();

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
        jButtonReglage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReglageActionPerformed(evt);
            }
        });

        jButtonFacile.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jButtonFacile.setText("Facile");
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
        jButtonMoyen.setText("Moyen");
        jButtonMoyen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonMoyenMouseClicked(evt);
            }
        });

        jButtonDifficile.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jButtonDifficile.setText("Difficile");
        jButtonDifficile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonDifficileMouseClicked(evt);
            }
        });
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
        jButtonNoSound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bouton/sonCoupé.png"))); // NOI18N
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

        jButtonSoundOn.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jButtonSoundOn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bouton/sonOuvert.png"))); // NOI18N
        jButtonSoundOn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonSoundOnMouseClicked(evt);
            }
        });
        jButtonSoundOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSoundOnActionPerformed(evt);
            }
        });

        jLabelCompteur.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabelCompteur.setForeground(new java.awt.Color(255, 255, 255));

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 669, Short.MAX_VALUE)
                        .addComponent(jButtonReglage, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonNoSound, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSoundOn, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelCompteur, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(jLabelCompteur, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonFacile)
                            .addComponent(jButtonJouer)
                            .addComponent(jButtonNoSound))
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSoundOn)
                        .addGap(129, 129, 129)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonDifficulte)
                            .addComponent(jButtonDifficile))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonReglage, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(370, Short.MAX_VALUE))
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

    public void jPanel1KeyPressed(KeyEvent evt) {
        if (boolJouer) {
            switch (evt.getKeyCode()) {
                case KeyEvent.VK_RIGHT -> {
                    if (xPerso < (int) (dimImage[0] * 0.84)) {
                        xPerso = (int) (xPerso + (dimImage[0] / 11));
                    }
                }
                case KeyEvent.VK_LEFT -> {
                    if (xPerso > (int) (dimImage[0] * 0.05)) {
                        xPerso = (int) (xPerso - (dimImage[0] / 11));
                    }
                }
                case KeyEvent.VK_UP -> {
                    if (yPerso > 40) {
                        yPerso = (int) (yPerso - (dimImage[1] / 13));
                    }
                }
                case KeyEvent.VK_DOWN -> {
                    if (yPerso < ((int) dimImage[1] * 0.86)) {
                        yPerso = (int) (yPerso + (dimImage[1] / 13));
                    }
                }
            }
            traitementVictoire();
            jPanel1.repaint();
        }
    }

    public void traitementDefaite() {
        JOptionPane.showMessageDialog(this, "Vous avez perdu une vie!");
        compteurVie -= 1;
        if (compteurVie == 0) {
            JOptionPane.showMessageDialog(this, "Vous avez perdu la partie!");
            compteurVie = 3;
            jLabelCompteur.setVisible(false);
            boolJouer = false;
            jButtonJouer.setVisible(true);
            jButtonAide.setVisible(true);
            jButtonDifficulte.setVisible(true);
            jButtonReglage.setVisible(true);
        }
        jLabelCompteur.setText(Integer.toString(compteurVie));
        xPerso = (int) (dimImage[0] / 2 - (dimImage[0] / 10) / 2);
        yPerso = (int) (dimImage[1] - (dimImage[1] / 14));
    }

    public void traitementVictoire() {
        if (yPerso < 15) {
            JOptionPane.showMessageDialog(this, "Félicitations ! Vous avez traversé la route !");
            xPerso = (int) (dimImage[0] / 2 - (dimImage[0] / 10) / 2);
            yPerso = (int) (dimImage[1] - (dimImage[1] / 14));
        }
    }

    public void traitementObstacles() {
        for (int i = 0; i < listeObs.size(); i++) {
            if (listeObs.get(i).getxObstacles() <= dimImage[0]
                    && listeObs.get(i).getxObstacles() >= -50) {
                listeObs.get(i).setxObstacles(listeObs.get(i).getxObstacles() + listeObs.get(i).getVitesse());
            } else {
                BufferedImage temp = listeObs.get(i).getImageObstacleResize();
                listeObs.get(i).setImageObstacleResize(null);
                if (listeObs.get(i).getxObstacles() < 100) {
                    listeObs.get(i).setxObstacles(dimImage[0]);
                } else {
                    listeObs.get(i).setxObstacles(-50);
                }
                listeObs.get(i).setImageObstacleResize(Affichage.resize(temp,
                        (int) (dimImage[0] / 9), (int) (dimImage[1] / 12)));
            }
            if (xPerso > listeObs.get(i).getxObstacles() - 25
                    && xPerso < listeObs.get(i).getxObstacles() + (dimImage[0] / 9) - 40
                    && yPerso > listeObs.get(i).getyObstacles()
                    && yPerso < listeObs.get(i).getyObstacles() + (dimImage[1] / 12)) {
                traitementDefaite();
            }
        }
    }

    public void traitementMilieu() {

        if (yPerso <= (int) dimImage[1] - 5.8 * (dimImage[1] / 13)
                && yPerso >= (int) dimImage[1] - 6 * (dimImage[1] / 13)
                && xObstacleM == -80) {
            imageExclamationAff = imageExclamationResize;
        } else {
            imageExclamationAff = null;
        }
        if (yPerso <= (int) dimImage[1] - 6.8 * (dimImage[1] / 13)
                && yPerso >= (int) dimImage[1] - 7 * (dimImage[1] / 13)
                && xObstacleM < dimImage[0] + 50) {
            xObstacleM += vitesseM;
        } else {
            if (xObstacleM != -80 && xObstacleM < dimImage[0] + 50) {
                xObstacleM += vitesseM;
            } else {
                xObstacleM = -80;
            }
        }

        if (xPerso > xObstacleM - 45
                && xPerso < xObstacleM + 50
                && yPerso > (int) dimImage[1] - 7 * (dimImage[1] / 13)
                && yPerso < (int) dimImage[1] - 6.8 * (dimImage[1] / 13)) {
            traitementDefaite();
        }

    }

    public void traitementRadeaux() {
        for (int i = 0; i < listeRad.size(); i++) {
            if (listeRad.get(i).getxRadeaux() <= dimImage[0]
                    && listeRad.get(i).getxRadeaux() >= -100) {
                listeRad.get(i).setxRadeaux(listeRad.get(i).getxRadeaux() + listeRad.get(i).getVitesse());
            } else {
                BufferedImage temp = listeRad.get(i).getImageRadeauxResize();
                listeRad.get(i).setImageRadeauxResize(null);
                if (listeRad.get(i).getxRadeaux() < 100) {
                    listeRad.get(i).setxRadeaux(dimImage[0]);
                } else {
                    listeRad.get(i).setxRadeaux(-100);
                }
                listeRad.get(i).setImageRadeauxResize(Affichage.resize(temp,
                        (int) (dimImage[0] / 9) + 40, (int) (dimImage[1] / 12)));
            }
            if (yPerso > dimImage[1] - 13 * (dimImage[1] / 14)
                    && yPerso < dimImage[1] - 7.6 * (dimImage[1] / 14)) {
                for (int j = 0; j < listeRad.size(); j++) {
                    if (xPerso > listeRad.get(j).getxRadeaux() - 30
                            && xPerso < listeRad.get(j).getxRadeaux() + (dimImage[0] / 9) - 10
                            && yPerso > listeRad.get(j).getyRadeaux()
                            && yPerso < listeRad.get(j).getyRadeaux() + (dimImage[1] / 12)) {
                        boolRad = false;
                    }
                }
                if (boolRad) {
                    traitementDefaite();
                }
                if (xPerso > listeRad.get(i).getxRadeaux() - 30
                        && xPerso < listeRad.get(i).getxRadeaux() + (dimImage[0] / 9) - 10
                        && yPerso > listeRad.get(i).getyRadeaux()
                        && yPerso < listeRad.get(i).getyRadeaux() + (dimImage[1] / 12)) {
                    xPerso += listeRad.get(i).getVitesse();
                }
            }
        }
        boolRad = true;
    }

    public void traitementMusique(boolean boolMusique) {

        long clipTime = 0;

        if (boolMusique) {
            try {
                URL url = IHMPrincipale.class.getClassLoader().getResource("musique/frogger-theme.wav");
                audioIn = AudioSystem.getAudioInputStream(url);
                clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.setMicrosecondPosition(clipTime);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            clipTime = clip.getMicrosecondPosition();
            clip.stop();
        }

    }


    private void jButtonDifficulteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDifficulteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonDifficulteActionPerformed

    private void jButtonDifficulteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDifficulteMouseClicked
        jButtonFacile.setBounds((dimImage[0] - 300) / 2, 100, 300, 75);
        jButtonMoyen.setBounds((dimImage[0] - 300) / 2, 300, 300, 75);
        jButtonDifficile.setBounds((dimImage[0] - 300) / 2, 500, 300, 75);
        jButtonJouer.setVisible(false);
        jButtonAide.setVisible(false);
        jButtonDifficulte.setVisible(false);
        jButtonReglage.setVisible(false);
        jButtonFacile.setVisible(true);
        jButtonMoyen.setVisible(true);
        jButtonDifficile.setVisible(true);

        jPanel1.add(jButtonFacile);
        jPanel1.add(jButtonMoyen);
        jPanel1.add(jButtonDifficile);


    }//GEN-LAST:event_jButtonDifficulteMouseClicked

    private void jButtonFacileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFacileActionPerformed

    }//GEN-LAST:event_jButtonFacileActionPerformed

    private void jButtonDifficileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDifficileActionPerformed

    }//GEN-LAST:event_jButtonDifficileActionPerformed

    private void jButtonMoyenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonMoyenMouseClicked
        jButtonFacile.setVisible(false);
        jButtonMoyen.setVisible(false);
        jButtonDifficile.setVisible(false);
        jButtonJouer.setVisible(true);
        jButtonAide.setVisible(true);
        jButtonDifficulte.setVisible(true);
        jButtonReglage.setVisible(true);

        if (difficulte == 1) {
            for (int i = 0; i < listeObs.size(); i++) {
                if (listeObs.get(i).getVitesse() > 0) {
                    listeObs.get(i).setVitesse(listeObs.get(i).getVitesse() + 2);
                } else {
                    listeObs.get(i).setVitesse(listeObs.get(i).getVitesse() - 2);
                }
            }
            for (int i = 0; i < listeRad.size(); i++) {
                if (listeRad.get(i).getVitesse() > 0) {
                    listeRad.get(i).setVitesse(listeRad.get(i).getVitesse() + 2);
                } else {
                    listeRad.get(i).setVitesse(listeRad.get(i).getVitesse() - 2);
                }
            }
            vitesseM += 2;
        }
        if (difficulte == 3) {
            for (int i = 0; i < listeObs.size(); i++) {
                if (listeObs.get(i).getVitesse() > 0) {
                    listeObs.get(i).setVitesse(listeObs.get(i).getVitesse() - 3);
                } else {
                    listeObs.get(i).setVitesse(listeObs.get(i).getVitesse() + 3);
                }
            }
            for (int i = 0; i < listeRad.size(); i++) {
                if (listeRad.get(i).getVitesse() > 0) {
                    listeRad.get(i).setVitesse(listeRad.get(i).getVitesse() - 3);
                } else {
                    listeRad.get(i).setVitesse(listeRad.get(i).getVitesse() + 3);
                }
            }
            vitesseM -= 2;
        }
        difficulte = 2;
    }//GEN-LAST:event_jButtonMoyenMouseClicked

    private void jButtonFacileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonFacileMouseClicked
        jButtonFacile.setVisible(false);
        jButtonMoyen.setVisible(false);
        jButtonDifficile.setVisible(false);
        jButtonJouer.setVisible(true);
        jButtonAide.setVisible(true);
        jButtonDifficulte.setVisible(true);
        jButtonReglage.setVisible(true);

        if (difficulte == 2) {
            for (int i = 0; i < listeObs.size(); i++) {
                if (listeObs.get(i).getVitesse() > 0) {
                    listeObs.get(i).setVitesse(listeObs.get(i).getVitesse() - 2);
                } else {
                    listeObs.get(i).setVitesse(listeObs.get(i).getVitesse() + 2);
                }
            }
            for (int i = 0; i < listeRad.size(); i++) {
                if (listeRad.get(i).getVitesse() > 0) {
                    listeRad.get(i).setVitesse(listeRad.get(i).getVitesse() - 2);
                } else {
                    listeRad.get(i).setVitesse(listeRad.get(i).getVitesse() + 2);
                }
            }
            vitesseM -= 2;
        }
        if (difficulte == 3) {
            for (int i = 0; i < listeObs.size(); i++) {
                if (listeObs.get(i).getVitesse() > 0) {
                    listeObs.get(i).setVitesse(listeObs.get(i).getVitesse() - 5);
                } else {
                    listeObs.get(i).setVitesse(listeObs.get(i).getVitesse() + 5);
                }
            }
            for (int i = 0; i < listeRad.size(); i++) {
                if (listeRad.get(i).getVitesse() > 0) {
                    listeRad.get(i).setVitesse(listeRad.get(i).getVitesse() - 5);
                } else {
                    listeRad.get(i).setVitesse(listeRad.get(i).getVitesse() + 5);
                }
            }
            vitesseM -= 4;
        }
        difficulte = 1;
    }//GEN-LAST:event_jButtonFacileMouseClicked

    private void jButtonReglageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonReglageMouseClicked
        jButtonSoundOn.setBounds((dimImage[0] - 200) / 2, 100, 177, 148);
        jButtonNoSound.setBounds((dimImage[0] - 200) / 2, 400, 177, 148);
        jButtonJouer.setVisible(false);
        jButtonAide.setVisible(false);
        jButtonDifficulte.setVisible(false);
        jButtonReglage.setVisible(false);
        jButtonNoSound.setVisible(true);
        jButtonSoundOn.setVisible(true);

        jPanel1.add(jButtonSoundOn);
        jPanel1.add(jButtonNoSound);


    }//GEN-LAST:event_jButtonReglageMouseClicked

    private void jButtonJouerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonJouerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonJouerActionPerformed

    private void jButtonJouerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonJouerMouseClicked
        jButtonJouer.setVisible(false);
        jButtonAide.setVisible(false);
        jButtonDifficulte.setVisible(false);
        jButtonReglage.setVisible(false);
        jButtonNoSound.setVisible(false);
        jButtonSoundOn.setVisible(false);

        boolJouer = true;
        jLabelCompteur.setBounds(dimImage[0] - 25, 0, 50, 50);
        jLabelCompteur.setVisible(true);
        jPanel1.add(jLabelCompteur);
        jLabelCompteur.setText(Integer.toString(compteurVie));

    }//GEN-LAST:event_jButtonJouerMouseClicked

    private void jButtonNoSoundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonNoSoundMouseClicked
        jButtonSoundOn.setVisible(false);
        jButtonNoSound.setVisible(false);
        jButtonJouer.setVisible(true);
        jButtonAide.setVisible(true);
        jButtonDifficulte.setVisible(true);
        jButtonReglage.setVisible(true);

        traitementMusique(false);
    }//GEN-LAST:event_jButtonNoSoundMouseClicked

    private void jButtonNoSoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNoSoundActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonNoSoundActionPerformed

    private void jButtonSoundOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSoundOnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSoundOnActionPerformed

    private void jButtonReglageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReglageActionPerformed

    }//GEN-LAST:event_jButtonReglageActionPerformed

    private void jButtonSoundOnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSoundOnMouseClicked
        jButtonSoundOn.setVisible(false);
        jButtonNoSound.setVisible(false);
        jButtonJouer.setVisible(true);
        jButtonAide.setVisible(true);
        jButtonDifficulte.setVisible(true);
        jButtonReglage.setVisible(true);

        traitementMusique(true);
    }//GEN-LAST:event_jButtonSoundOnMouseClicked

    private void jButtonDifficileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDifficileMouseClicked
        jButtonFacile.setVisible(false);
        jButtonMoyen.setVisible(false);
        jButtonDifficile.setVisible(false);
        jButtonJouer.setVisible(true);
        jButtonAide.setVisible(true);
        jButtonDifficulte.setVisible(true);
        jButtonReglage.setVisible(true);

        if (difficulte == 1) {
            for (int i = 0; i < listeObs.size(); i++) {
                if (listeObs.get(i).getVitesse() > 0) {
                    listeObs.get(i).setVitesse(listeObs.get(i).getVitesse() + 5);
                } else {
                    listeObs.get(i).setVitesse(listeObs.get(i).getVitesse() - 5);
                }
            }
            for (int i = 0; i < listeRad.size(); i++) {
                if (listeRad.get(i).getVitesse() > 0) {
                    listeRad.get(i).setVitesse(listeRad.get(i).getVitesse() + 5);
                } else {
                    listeRad.get(i).setVitesse(listeRad.get(i).getVitesse() - 5);
                }
            }
            vitesseM += 4;
        }
        if (difficulte == 2) {
            for (int i = 0; i < listeObs.size(); i++) {
                if (listeObs.get(i).getVitesse() > 0) {
                    listeObs.get(i).setVitesse(listeObs.get(i).getVitesse() + 3);
                } else {
                    listeObs.get(i).setVitesse(listeObs.get(i).getVitesse() - 3);
                }
            }
            for (int i = 0; i < listeRad.size(); i++) {
                if (listeRad.get(i).getVitesse() > 0) {
                    listeRad.get(i).setVitesse(listeRad.get(i).getVitesse() + 3);
                } else {
                    listeRad.get(i).setVitesse(listeRad.get(i).getVitesse() - 3);
                }
            }
            vitesseM += 2;
        }
        difficulte = 3;
    }//GEN-LAST:event_jButtonDifficileMouseClicked

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
        java.awt.EventQueue.invokeLater(() -> {
            new IHMPrincipale().setVisible(true);
        });
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
    private javax.swing.JButton jButtonSoundOn;
    private javax.swing.JLabel jLabelCompteur;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
