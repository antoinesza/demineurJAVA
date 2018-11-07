import javax.swing.JFrame;
import java.nio.Buffer;


public class Frame extends JFrame {

    public static int Discovered = 0;
    public static int Flag = 0;
    public static int Bombs = 0;
    public static int Cursor[] = new int[2];
    public static boolean onScreen = false;
    public static int Cases[][] = new int[1000][1000];
    public static boolean Hidden[][] = new boolean[1000][1000];
    public static boolean Flags[][] = new boolean[1000][1000];
    public static int x = 64;
    public static int y = 36;
    public static int Size = 0;
    public static int Colors = 1;
    public static Boolean finish = false;
    public static boolean won = false;
    static Panel panel = new Panel();
    static mouseListener ml = new mouseListener();
    static int FrameRate = 12;

    public Frame(String title) {
        this.setVisible(true);
        this.setSize(1296, 758);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle(title);
        this.setContentPane(panel);
        this.addMouseListener(ml);
        generate(20, 10, 20, 4);
        GameTrame();
    }

    public static void GameTrame() {
        while (true) {
            int Buffer = TimerThread.MILLI;
            onScreen = false;
            if (Panel.mouseX > 0 && Panel.mouseX < x * Size && Panel.mouseY > 0 && Panel.mouseY < y * Size) {
                onScreen = true;
                Cursor[0] = Panel.mouseX / Size;
                Cursor[1] = Panel.mouseY / Size;
                if (ml.Left && !finish && Hidden[Cursor[0]][Cursor[1]]){
                    delete(Cursor[0], Cursor[1]);
                }
                if (ml.Right && !finish && Hidden[Cursor[0]][Cursor[1]]){
                    Flag();}
            }

            if (ml.Left) ml.Left = false;
            if (ml.Right) ml.Right = false;

            panel.repaint();

            if ((TimerThread.MILLI - Buffer) < FrameRate) {
                try {
                    Thread.sleep(FrameRate - (TimerThread.MILLI - Buffer));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void generate(int X, int Y, int size, int pourcent) {
        x = X;
        y = Y;
        Size = size;
        for (int x1 = 0; x1 < x; x1++) {
            for (int y1 = 0; y1 < Y; y1++) {
                Hidden[x1][y1] = true;
                if (Math.random() * 100 < pourcent) {
                    Cases[x1][y1] = -1;
                    Bombs++;
                }
            }
        }
        for (int x1 = 0; x1 < X; x1++) {
            for (int y1 = 0; y1 < Y; y1++) {
                if (Cases[x1][y1] != -1) {
                    System.out.println(x1 + "\t" + y1);
                    int Buffer = 0;
                    if (x1 == 0) {
                        if (y1 == 0) {
                            if (Cases[x1 + 1][y1] == -1) Buffer++;
                            if (Cases[x1 + 1][y1 + 1] == -1) Buffer++;
                            if (Cases[x1][y1 + 1] == -1) Buffer++;
                        } else if (y1 == Y) {
                            if (Cases[x1 - 1][y1] == -1) Buffer++;
                            if (Cases[x1 - 1][y1 + 1] == -1) Buffer++;
                            if (Cases[x1][y1 + 1] == -1) Buffer++;
                        } else {
                            if (Cases[x1 + 1][y1 + 1] == -1) Buffer++;
                            if (Cases[x1 + 1][y1] == -1) Buffer++;
                            if (Cases[x1 + 1][y1 + 1] == -1) Buffer++;
                            if (Cases[x1][y1 - 1] == -1) Buffer++;
                            if (Cases[x1][y1 + 1] == -1) Buffer++;
                        }
                    } else if (x1 == X) {
                        if (y1 == 0) {
                            if (Cases[x1 - 1][y1] == -1) Buffer++;
                            if (Cases[x1 - 1][y1 + 1] == -1) Buffer++;
                            if (Cases[x1][y1 + 1] == -1) Buffer++;
                        } else if (y1 == Y) {
                            if (Cases[x1 - 1][y1] == -1) Buffer++;
                            if (Cases[x1 - 1][y1 + 1] == -1) Buffer++;
                            if (Cases[x1][y1 + 1] == -1) Buffer++;
                        } else {
                            if (Cases[x1 + 1][y1 + 1] == -1) Buffer++;
                            if (Cases[x1 - 1][y1] == -1) Buffer++;
                            if (Cases[x1 - 1][y1 + 1] == -1) Buffer++;
                            if (Cases[x1][y1 - 1] == -1) Buffer++;
                            if (Cases[x1][y1 + 1] == -1) Buffer++;
                        }
                    } else {
                        if (y1 == 0) {
                            if (Cases[x1 + 1][y1 + 1] == -1) Buffer++;
                            if (Cases[x1][y1 + 1] == -1) Buffer++;
                            if (Cases[x1 - 1][y1 + 1] == -1) Buffer++;
                            if (Cases[x1 + 1][y1] == -1) Buffer++;
                            if (Cases[x1 - 1][y1] == -1) Buffer++;
                        } else if (y1 == Y) {
                            if (Cases[x1 - 1][y1 - 1] == -1) Buffer++;
                            if (Cases[x1][y1 - 1] == -1) Buffer++;
                            if (Cases[x1 + 1][y1 - 1] == -1) Buffer++;
                            if (Cases[x1 + 1][y1] == -1) Buffer++;
                            if (Cases[x1 - 1][y1] == -1) Buffer++;
                        } else {
                            if (Cases[x1 + 1][y1] == -1) Buffer++;
                            if (Cases[x1 + 1][y1 - 1] == -1) Buffer++;
                            if (Cases[x1 + 1][y1 + 1] == -1) Buffer++;
                            if (Cases[x1][y1 + 1] == -1) Buffer++;
                            if (Cases[x1][y1 - 1] == -1) Buffer++;
                            if (Cases[x1 - 1][y1] == -1) Buffer++;
                            if (Cases[x1 - 1][y1 - 1] == -1) Buffer++;
                            if (Cases[x1 - 1][y1 + 1] == -1) Buffer++;
                        }
                    }
                    Cases[x1][y1] = Buffer;
                }
            }
        }
    }

    public static void delete(int x1, int y1) {
        if (Cases[x1][y1] == -1) {
            for (int X = 0; X < x; X++) {
                for (int Y = 0; Y < y; Y++) {
                    Hidden[X][Y] = false;
                }
            }
            finish = true;
            won = false;
        } else if (Cases[x1][y1] > 0) {
            Hidden[x1][y1] = false;
            Discovered++;
        }
        else if (Cases[x1][y1] == 0) {
            int Buffer[][] = new int[100000][2];
            int Size = 1;
            Buffer[0][0] = x1;
            Buffer[0][1] = y1;
            while (Size > 0) {
                int buffer = TimerThread.MILLI;
                onScreen = false;
                if (Panel.mouseX > 0 && Panel.mouseX < x * Size && Panel.mouseY > 0 && Panel.mouseY < y * Size) {
                    onScreen = true;
                    Cursor[0] = Panel.mouseX / Size;
                    Cursor[1] = Panel.mouseY / Size;
                }
                if (ml.Left) ml.Left = false;
                if (ml.Right) ml.Right = false;

                panel.repaint();

                if ((TimerThread.MILLI - buffer) < FrameRate) {
                    try {
                        Thread.sleep(FrameRate - (TimerThread.MILLI - buffer));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int Buffer2[][] = new int[100000][2];
                int size = 0;

                System.out.println(Size);
                for (int s = 0; s < Size; s++) {

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    panel.repaint();
                    if (Hidden[Buffer[s][0]][Buffer[s][1]]) {
                        if (Cases[Buffer[s][0]][Buffer[s][1]] != 0) {
                            if(Flags[Buffer[s][0]][Buffer[s][1]]) {
                                Flags[Buffer[s][0]][Buffer[s][1]] = false;
                            Flag--;}
                            Hidden[Buffer[s][0]][Buffer[s][1]] = false;
                            Discovered++;
                        } else {
                            if(Flags[Buffer[s][0]][Buffer[s][1]]) {
                                Flags[Buffer[s][0]][Buffer[s][1]] = false;
                                Flag--;}
                            Hidden[Buffer[s][0]][Buffer[s][1]] = false;
                            Discovered++;

                            if (Buffer[s][0] > 0){
                                if(Hidden[Buffer[s][0]-1][Buffer[s][1]]){
                                Buffer2[size][0] = Buffer[s][0] - 1;
                                Buffer2[size][1] = Buffer[s][1];
                                size++;
                            }}
                            if (Buffer[s][0] < x) {
                                if(Hidden[Buffer[s][0]+1][Buffer[s][1]]){
                                Buffer2[size][0] = Buffer[s][0] + 1;
                                Buffer2[size][1] = Buffer[s][1];
                                size++;
                            }}
                            if (Buffer[s][1] > 0) {
                                if(Hidden[Buffer[s][0]][Buffer[s][1]-1]){
                                Buffer2[size][0] = Buffer[s][0];
                                Buffer2[size][1] = Buffer[s][1] - 1;
                                size++;
                            }}
                            if (Buffer[s][1] < y) {

                                if(Hidden[Buffer[s][0]][Buffer[s][1]+1]){
                                Buffer2[size][0] = Buffer[s][0];
                                Buffer2[size][1] = Buffer[s][1] + 1;
                                size++;
                            }}
                        }
                    }
                }
                Size = size;
                for (int s = 0; s < Size; s++) {
                    Buffer[s][0] = Buffer2[s][0];
                    Buffer[s][1] = Buffer2[s][1];
                }
            }
        }

    }

    public static void Flag(){
        if(!Flags[Cursor[0]][Cursor[1]]){
        Flags[Cursor[0]][Cursor[1]] = true;
        Flag++;}
        else{
            Flags[Cursor[0]][Cursor[1]] = false;
            Flag--;}
        if(Flag == Bombs){
            Boolean cond = true;
            System.out.println(x+"; "+y);
            for(int i = 0; i < x; i++){
                for(int j = 0; j < y; j++){
                    System.out.println(j+"; "+i);
                    if(Flags[i][j]){
                        System.out.println(" = flag");
                        if(Cases[i][j] != -1){
                            cond = false;
                        }
                    }
                    System.out.println();
                }
            }
            if(cond){
                finish = true;
                won = true;
            }
        }
    }
}
