import java.awt.*;

public class Enemy {
    int x, y, speed = 2;
    int width = 30, height = 30;

    public Enemy(int startX) {
        x = startX;
        y = 0;
    }

    public void update(int playerX) {
        if (x < playerX) x += speed;
        else if (x > playerX) x -= speed;
        y += 1;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}