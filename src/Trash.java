import java.awt.*;
import java.util.Random;

public class Trash {
    int x, y, size = 30, speed;
    Color color;

    public Trash(int level) {
        x = new Random().nextInt(770);
        y = 0;
        speed = 2 + level;
        color = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
    }

    public void update() {
        y += speed;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, size, size);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }
}