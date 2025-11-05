import java.awt.*;

public class Explosion {
    int x, y, radius = 0, maxRadius = 30;
    boolean active = true;

    public Explosion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        radius += 2;
        if (radius >= maxRadius) active = false;
    }

    public void draw(Graphics g) {
        if (!active) return;
        g.setColor(Color.ORANGE);
        g.fillOval(x - radius / 2, y - radius / 2, radius, radius);
    }
}