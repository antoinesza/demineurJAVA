import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Panel extends JPanel {
    public void paintComponent(Graphics g){
        g.setColor(new Color(74, 74, 74));
        g.fillRect(0, 0, getSize().width, getSize().height);
        for(int x = 0; x < Frame.x; x++ ){
            for(int y = 0; y < Frame.y; y++ ){
                g.drawImage(new ImageIcon("caseN.png").getImage(), x*20, y*20, null);
                if(Frame.Cases[x][y] == -1){
                    g.drawImage(new ImageIcon("-1.png").getImage(),x*20, y*20,null);
                }
                else if(Frame.Cases[x][y] > 0) {
                    g.setColor(new Color (0, 0, 0));
                    g.drawString(Integer.toString(Frame.Cases[x][y]), x*20+2, y*20+15);
                }
            }
        }
    }
}
