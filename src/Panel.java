import java.awt.*;

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
                    if(Frame.Colors == 1){
                        if(Frame.Cases[x][y] == 1) g.setColor(new Color(255, 0, 0));
                        if(Frame.Cases[x][y] == 2) g.setColor(new Color(255, 50, 0));
                        if(Frame.Cases[x][y] == 3) g.setColor(new Color(255, 100, 0));
                        if(Frame.Cases[x][y] == 4) g.setColor(new Color(255, 150, 0));
                        if(Frame.Cases[x][y] == 5) g.setColor(new Color(255, 200, 0));
                        if(Frame.Cases[x][y] == 6) g.setColor(new Color(255, 255, 0));
                        if(Frame.Cases[x][y] == 7) g.setColor(new Color(120, 120, 120));
                        if(Frame.Cases[x][y] == 8) g.setColor(new Color(0, 0, 0));
                    }
                    g.setFont(new Font("Baby Kruffy", Font.PLAIN, 20));
                    g.setColor(new Color (0, 0, 0));
                    g.drawString(Integer.toString(Frame.Cases[x][y]), x*20+4, y*20+18);
                }
            }
        }
    }
}
