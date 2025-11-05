public class Main {
    public static SoundPlayer musicPlayer;

    public static void main(String[] args) {
        musicPlayer = new SoundPlayer("assets/muzyka.wav", "assets/boss.wav");
        javax.swing.JFrame frame = new javax.swing.JFrame("Trash Shooter Game");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new GamePanel());
        frame.setVisible(true);
    }