import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class mouseListener implements MouseListener {
    static boolean Left = false;
    static boolean Right = false;
    public void mouseClicked(MouseEvent arg0) {}

    public void mouseEntered(MouseEvent arg0) {}

    public void mouseExited(MouseEvent arg0) {}

    public void mousePressed(MouseEvent arg0) {
        if(arg0.getButton() == 1) Left = true;
        if(arg0.getButton() == 3) Right = true;
    }

    public void mouseReleased(MouseEvent arg0){ }
}
