import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;

public class Demineur {
    public Demineur() {
    }

    public static void main(String[] var0) throws IOException {
        NumberFormat var1 = NumberFormat.getNumberInstance();
        var1.setMinimumIntegerDigits(2);
        Grille var2 = new Grille();
        int var3 = 0;
        boolean var4 = false;
        int var5 = 0;
        int var6 = 0;
        int var7 = 0;
        boolean var8 = false;
        boolean var9 = false;
        boolean var10 = false;
        BufferedReader var11 = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Demineur");
        System.out.println("Dans le terminal");

        do {
            try {
                System.out.println("Entrez un nombre de lignes");
                var5 = Integer.parseInt(var11.readLine()) + 2;
            } catch (NumberFormatException var23) {
                System.out.println("\nVeuillez entrer un entier pour le nombre de lignes\n");
                var10 = true;
            }
        } while(var10 && var5 < 5);

        var10 = false;

        do {
            try {
                System.out.println("\nEntrez un nombre de colonnes");
                var6 = Integer.parseInt(var11.readLine()) + 2;
            } catch (NumberFormatException var22) {
                System.out.println("\nVeuillez entrer un entier pour le nombre de lignes\n");
                var10 = true;
            }
        } while(var10 && var6 < 5);

        var10 = false;

        do {
            try {
                System.out.println("\nEntrez un nombre de mines a decouvrire");
                var7 = Integer.parseInt(var11.readLine());
            } catch (NumberFormatException var21) {
                System.out.println("\nVeuillez entrer un entier pour le nombre de mines a decouvrire\n");
                var10 = true;
            }
        } while(var10 && (var7 < 1 || var7 > (var5 - 3) * (var6 - 3)));

        var10 = false;
        int[][] var12 = new int[var5][var6];
        String[][] var13 = new String[var5][var6];
        boolean var14 = false;
        boolean var15 = false;

        for(int var16 = 0; var16 < var7; ++var16) {
            int var26 = (int)(Math.random() * (double)(var5 - 2) + 1.0D);
            int var27 = (int)(Math.random() * (double)(var6 - 2) + 1.0D);
            if (var12[var26][var27] != 9) {
                var12[var26][var27] = 9;
            } else {
                --var16;
            }
        }

        int var18;
        int var19;
        int var20;
        for(int var17 = 0; var17 < var5; ++var17) {
            for(var18 = 0; var18 < var6; ++var18) {
                if (var12[var17][var18] == 9) {
                    for(var19 = var17 - 1; var19 < var17 + 2; ++var19) {
                        for(var20 = var18 - 1; var20 < var18 + 2; ++var20) {
                            if (var12[var19][var20] != 9) {
                                ++var12[var19][var20];
                            }
                        }
                    }
                }
            }
        }

        for(var18 = 0; var18 < var5; ++var18) {
            for(var19 = 0; var19 < var6; ++var19) {
                var13[var18][var19] = "\u0003";
            }
        }

        for(var19 = 0; var19 < var5; ++var19) {
            var13[var19][0] = var1.format((long)var19) + "";
            var13[var19][var6 - 1] = var1.format((long)var19) + "";

            for(var20 = 0; var20 < var6; ++var20) {
                var13[0][var20] = var1.format((long)var20) + "";
                var13[var5 - 1][var20] = var1.format((long)var20) + "";
            }
        }

        var13[var5 - 1][0] = "  ";
        var13[0][0] = "  ";
        var13[0][var6 - 1] = "  ";
        var13[var5 - 1][var6 - 1] = "  ";
        var2.Grille(var6, var5, var13);

        do {
            int var24;
            do {
                do {
                    System.out.println("\nEntrer le numero de ligne que vous souhaitez devoiler");
                    var24 = Integer.parseInt(var11.readLine()) - 1;
                } while(var24 < 0);
            } while(var24 > var5 - 3);

            int var25;
            do {
                do {
                    System.out.println("\nEntrer le numero de colonne que vous souhaitez devoiler");
                    var25 = Integer.parseInt(var11.readLine()) - 1;
                } while(var25 < 0);
            } while(var25 > var6 - 3);

            if (var13[var24 + 1][var25 + 1] == "\u0003") {
                ++var3;
            }

            if (var12[var24 + 1][var25 + 1] == 9) {
                var13[var24 + 1][var25 + 1] = "*";
            } else if (var12[var24 + 1][var25 + 1] == 0) {
                var13[var24 + 1][var25 + 1] = " ";
            } else {
                var13[var24 + 1][var25 + 1] = var12[var24 + 1][var25 + 1] + "";
            }

            var2.Grille(var6, var5, var13);
            System.out.println();
            if (var12[var24 + 1][var25 + 1] == 9) {
                var4 = true;

                for(var20 = 0; var20 < 25; ++var20) {
                    System.out.println("GANG");
                    System.out.println("BANG");
                    System.out.println("<=====3");
                }
            }

            if (var3 == (var5 - 2) * (var6 - 2) - var7 && !var4) {
                var4 = true;
                System.out.println("\nEt");
                System.out.println("C'est");
                System.out.println("GAGNE");
            }
        } while(!var4);

        System.out.println();
        var2.Grille(var6, var5, var13);
        System.out.println("\nMerci d'avoir joue\n");
    }
}