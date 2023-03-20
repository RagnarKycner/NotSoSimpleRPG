package Kodzik.Akcje;


import java.util.Random;

public class Dice {
    private Dice(){}

    static public int roll(int dol, int gora) {
        if (dol > gora) {
            int temp = gora;
            gora = dol;
            dol = temp;
        }
        Random random = new Random();
        int result;
        result = random.nextInt(gora - dol) + dol;
        return result;
    }
}
