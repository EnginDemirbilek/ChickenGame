package tavuk;

import javax.swing.JFrame;

public class Tavuk {

    public Tavuk() {}

    public static void main(String[] args) {
        JFrame obj = new JFrame();
        GamePlay gamePlay = new GamePlay();
        obj.setBounds(10, 10, 300, 600);
        obj.setName("Game Frame");
        obj.setVisible(true);
        obj.setResizable(false);
        obj.setDefaultCloseOperation(3);
        obj.add(gamePlay);
    }

}