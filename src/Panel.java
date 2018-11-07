import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Panel extends JPanel {
   static int mouseX = 0;
   static int mouseY = 0;
    public void paintComponent(Graphics g){
        mouseX = MouseInfo.getPointerInfo().getLocation().x-getLocationOnScreen().x;
        mouseY = MouseInfo.getPointerInfo().getLocation().y-getLocationOnScreen().y;
        g.setColor(new Color(74, 74, 74));
        g.fillRect(0, 0, getSize().width, getSize().height);
        for(int x = 0; x < Frame.x; x++ ){
            for(int y = 0; y < Frame.y; y++ ){
                g.drawImage(new ImageIcon("case.png").getImage(), x*Frame.Size, y*Frame.Size, null);
                if(Frame.Cases[x][y] == -1){
                    g.drawImage(new ImageIcon("-1.png").getImage(),x*Frame.Size, y*Frame.Size,null);
                }
                else if(Frame.Cases[x][y] > 0) {
                    if(Frame.Colors == 1){
                        if(Frame.Cases[x][y] == 1) g.setColor(new Color(0, 39, 255));
                        if(Frame.Cases[x][y] == 2) g.setColor(new Color(34, 166, 0));
                        if(Frame.Cases[x][y] == 3) g.setColor(new Color(209, 2, 0));
                        if(Frame.Cases[x][y] == 4) g.setColor(new Color(0, 40, 157));
                        if(Frame.Cases[x][y] == 5) g.setColor(new Color(128, 0, 9));
                        if(Frame.Cases[x][y] == 6) g.setColor(new Color(255, 255, 0));
                        if(Frame.Cases[x][y] == 7) g.setColor(new Color(120, 120, 120));
                        if(Frame.Cases[x][y] == 8) g.setColor(new Color(0, 0, 0));
                    }
                    g.setFont(new Font("Baby Kruffy", Font.PLAIN, Frame.Size));
                    g.drawString(Integer.toString(Frame.Cases[x][y]), x*Frame.Size+4, y*Frame.Size+18);
                }
                if(Frame.Hidden[x][y]){
                    Graphics2D g2d = (Graphics2D) g;
                   g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .8F));
                    g.drawImage(new ImageIcon("caseN.png").getImage(), x*Frame.Size, y*Frame.Size, null);
                    //g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .1F));
                }
                if(Frame.Flags[x][y]){
                    if(!Frame.finish){
                        g.drawImage(new ImageIcon("flag.png").getImage(), x*Frame.Size, y*Frame.Size, null);}
                    else if(Frame.Cases[x][y] == -1){
                        g.drawImage(new ImageIcon("flagT.png").getImage(), x*Frame.Size, y*Frame.Size, null);
                    }
                    else g.drawImage(new ImageIcon("flag.png").getImage(), x*Frame.Size, y*Frame.Size, null);
                }
            }
        }
        if(Frame.onScreen){
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(Frame.Cursor[0]*Frame.Size, Frame.Cursor[1]*Frame.Size, Frame.Size , Frame.Size );
        }
    }
}
