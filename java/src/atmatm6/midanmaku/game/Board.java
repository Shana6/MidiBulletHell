package atmatm6.midanmaku.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private final int IPLAYER_X = getWidth()/2;
    private final int IPLAYER_Y = 480;
    private final int DELAY = 5;
    private Timer timer;
    private Player PLAYER;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        PLAYER = new Player(IPLAYER_X, IPLAYER_Y);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(PLAYER.getImage(), PLAYER.getX(),
                PLAYER.getY(), this);

        ArrayList ms = PLAYER.getMissiles();

        for (Object m1 : ms) {
            Missile m = (Missile) m1;
            g2d.drawImage(m.getImage(), m.getX(),
                    m.getY(), this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateMissiles();
        updateCraft();

        repaint();
    }

    private void updateMissiles() {
        ArrayList ms = PLAYER.getMissiles();

        for (int i = 0; i < ms.size(); i++) {

            Missile m = (Missile) ms.get(i);

            if (m.isVisible()) {

                m.move();
            } else {

                ms.remove(i);
            }
        }
    }

    private void updateCraft() {
        PLAYER.move();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            PLAYER.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            PLAYER.keyPressed(e);
        }
    }
}