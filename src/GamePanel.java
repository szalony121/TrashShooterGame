import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
    Timer timer;
    int playerX = 375;
    int score = 0, level = 1, weaponLevel = 1;
    boolean survivalMode = false, showMenu = true, showGameOver = false;
    int skinIndex = 0;
    Color[] playerColors = {Color.GREEN, Color.CYAN, Color.ORANGE};
    Color[] backgroundColors = {Color.BLACK, Color.DARK_GRAY, Color.BLUE};

    ArrayList<Trash> trashList = new ArrayList<>();
    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<Explosion> explosions = new ArrayList<>();
    ArrayList<Enemy> enemies = new ArrayList<>();

    Boss boss = null;
    boolean bossActive = false, showBossIntro = false;
    int introTimer = 0, levelTimer = 0;

    Rectangle startButton = new Rectangle(300, 200, 200, 50);
    Rectangle survivalButton = new Rectangle(300, 270, 200, 50);
    Rectangle exitButton = new Rectangle(300, 340, 200, 50);

    public GamePanel() {
        timer = new Timer(20, this);
        timer.start();
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(backgroundColors[skinIndex]);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (showMenu) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 32));
            g.drawString("Trash Shooter Game", 230, 120);
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawRect(startButton.x, startButton.y, startButton.width, startButton.height);
            g.drawString("START", startButton.x + 60, startButton.y + 35);
            g.drawRect(survivalButton.x, survivalButton.y, survivalButton.width, survivalButton.height);
            g.drawString("TRYB PRZETRWANIA", survivalButton.x + 10, survivalButton.y + 35);
            g.drawRect(exitButton.x, exitButton.y, exitButton.width, exitButton.height);
            g.drawString("WYJŚCIE", exitButton.x + 60, exitButton.y + 35);
            return;
        }

        if (showBossIntro) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("NADCHODZI BOSS!", 250, 300);
            return;
        }

        g.setColor(playerColors[skinIndex]);
        g.fillRect(playerX, 550, 50, 20);

        for (Trash t : trashList) t.draw(g);
        for (Bullet b : bullets) b.draw(g);
        for (Explosion ex : explosions) ex.draw(g);
        for (Enemy e : enemies) e.draw(g);
        if (bossActive && boss != null) boss.draw(g);

        g.setColor(Color.WHITE);
        g.drawString("Wynik: " + score + " | Poziom: " + level, 10, 20);
    }

    public void actionPerformed(ActionEvent e) {
        levelTimer++;
        if (levelTimer % 1000 == 0) level++;

        if (new Random().nextInt(30 - level * 5) == 0) trashList.add(new Trash(level));
        if (new Random().nextInt(100) == 0) enemies.add(new Enemy(new Random().nextInt(750)));

        for (Trash t : trashList) t.update();
        for (Bullet b : bullets) b.update();
        for (Explosion ex : explosions) ex.update();
        for (Enemy en : enemies) en.update(playerX);

        trashList.removeIf(t -> t.y > 600);
        bullets.removeIf(b -> b.y < 0);
        explosions.removeIf(ex -> !ex.active);

        if (level % 10 == 0 && !bossActive) {
            boss = new Boss(level);
            bossActive = true;
            Main.musicPlayer.switchToBossMusic();
            showBossIntro = true;
            introTimer = 0;
        }

        if (showBossIntro) {
            introTimer++;
            if (introTimer > 100) showBossIntro = false;
        }

        repaint();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) playerX -= 20;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) playerX += 20;
        if (e.getKeyCode() == KeyEvent.VK_SPACE) bullets.add(new Bullet(playerX + 20, 540, weaponLevel));
        if (e.getKeyCode() == KeyEvent.VK_T) skinIndex = (skinIndex + 1) % playerColors.length;
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        if (showMenu) {
            if (startButton.contains(p)) {
                showMenu = false;
                survivalMode = false;
            } else if (survivalButton.contains(p)) {
                showMenu = false;
                survivalMode = true;
            } else if (exitButton.contains(p)) {
                System.exit(0);
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        playerX = e.getX() - 25;
    }

    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
}