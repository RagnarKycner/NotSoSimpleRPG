package Kodzik.Postacie.Gracz;

import Kodzik.Akcje.Block;
import Kodzik.Akcje.Rest;
import Kodzik.Akcje.Strong;
import Kodzik.Akcje.Weak;
import Kodzik.Postacie.Player;

public class Archer extends Player {

    public Archer(String name, int level) {
        HPPerLevel = 100;
        attPerLevel = 10;
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

        actions[0]=new Weak("Strzal");
        actions[1]=new Strong("Ognista strzala");
        actions[2]=new Block("Unik");
        actions[3]=new Rest();

    }

    public Archer(String name, int level, int exp) {
        this(name,level);
        this.exp = exp;
    }

    @Override
    public void reset() {
        this.HP = maxHP;
        setMaxEnergy();
    }

    @Override
    public void takeDamage(int damage) {
        if(blocked) {
            System.out.println(name + " uniknal/a ataku.");
            blocked = false;
            return;
        }
        int temp = (int)(damage - def/5*defmod);
        if (temp < 0) {
            temp = 0;
        }
        HP -= temp;
        System.out.println(name + " otrzymal/a " + temp + " obrazen");
    }
}
