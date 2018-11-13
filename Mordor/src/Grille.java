import java.io.IOException;

class Grille {
    Grille() {
    }

    public void Grille(int var1, int var2, String[][] var3) throws IOException {
        for(int var4 = 0; var4 < var2; ++var4) {
            for(int var5 = 0; var5 < var1; ++var5) {
                if (var4 == var2 - 1 && var1 > 0) {
                    System.out.print(var3[var4][var5] + " ");
                } else if ((var4 <= 0 || var5 <= 0) && var5 != var1 - 1) {
                    System.out.print(var3[var4][var5] + " ");
                } else {
                    System.out.print(var3[var4][var5] + "  ");
                }
            }

            System.out.println();
        }

    }
}
