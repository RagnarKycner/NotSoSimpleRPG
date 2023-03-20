package Kodzik.Postacie.Gracz;

import Kodzik.Akcje.Block;
import Kodzik.Akcje.Rest;
import Kodzik.Akcje.Strong;
import Kodzik.Akcje.Weak;
import Kodzik.Postacie.Player;

public class Warrior extends Player {

    public Warrior(String name, int level) {
        HPPerLevel = 150;
        attPerLevel = 5;
        defPerLevel = 10;

        this.expNeeded = 100 * level;
        this.exp = 0;
        this.name = name;
        this.level = level;
        this.maxHP = 100 + HPPerLevel*level;
        this.HP = maxHP;
        setMaxEnergy();

        att = 20 + attPerLevel * (level-1);
        def = 20 + defPerLevel * (level-1);

        actions[0]=new Weak("Pchniecie mieczem");
        actions[1]=new Strong("Cios znad glowy");
        actions[2]=new Block("Blok");
        actions[3]=new Rest();

    }

    public Warrior(String name, int level, int exp) {
        this(name, level);
        this.exp = exp;
    }

    @Override
    public void reset() {
        this.HP = maxHP;
        setMaxEnergy();
    }

    @Override
    public void takeDamage(int damage) {
        int block = 0;
        String blString = null;
        if (blocked) {
            blString = "(zablokowane)";
            block = 20 * level;
            blocked = false;
        }
        int temp =(int)(damage - defmod*(def + block));
        if (temp < 0) {
            temp = 0;
        }
        HP -= temp;
        if (blString != null){
            System.out.print(name + " otrzymal/a " + temp + blString +" obrazen");
        } else {
            System.out.print(name + " otrzymal/a " + temp +" obrazen");
        }
    }
}
