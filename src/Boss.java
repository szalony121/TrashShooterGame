import java.awt.*;
import java.util.Random;

public class Boss {
    int x = 300, y = 50, width = 200, height = 60, hp, direction = 1, type;

    public Boss(int level) {
        type = new Random().nextInt(3);
        hp = 10 + level * 2;
    }

    public void update() {
        int speed = (type == 1) ? 6 : 3;
        x += direction * speed;
        if (x <= 0 || x + width >= 800) direction *= -1;
    }

    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, width, height);
        g.setColor(Color.RED);
        g.fillRect(x, y - 10, width, 5);
        g.setColor(Color.GREEN);
        g.fillRect(x, y - 10, (int)((hp