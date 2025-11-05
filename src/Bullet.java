import java.awt.*;

public class Bullet {
    int x, y, power;

    public Bullet(int x, int y, int power) {
        this.x = x;
        this.y = y;
        this.power = power;
    }

    public void update() {
        y -= 10;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 5 + power * 2, 10);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 5 + power * 2, 10);
    }
}