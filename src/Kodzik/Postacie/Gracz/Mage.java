package Kodzik.Postacie.Gracz;

import Kodzik.Akcje.Block;
import Kodzik.Akcje.Rest;
import Kodzik.Akcje.Strong;
import Kodzik.Akcje.Weak;
import Kodzik.Postacie.Player;

public class Mage extends Player {

    int shield = 0;

    public Mage(String name, int level) {
        HPPerLevel = 75;
        attPerLevel = 15;
        defPerLevel = 10;

        this.expNeeded = 100 * level-1;
        this.exp = 0;
        this.name = name;
        this.level = level;
        this.maxHP = 100 + HPPerLevel*level;
        this.HP = maxHP;
        setMaxEnergy();

        att = 20 + attPerLevel * (level-1);
        def = 20 + defPerLevel * (level-1);

        actions[0]=new Weak("Magiczny pocisk");
        actions[1]=new Strong("Kula ognia");
        actions[2]=new Block("Tarcza");
        actions[3]=new Rest();

    }

    public Mage(String name, int level, int exp) {
        this(name,level);
        this.exp = exp;
    }

    @Override
    public void reset() {
        this.shield = 0;
        this.HP = maxHP;
        setMaxEnergy();
    }

    @Override
    public void takeDamage(int damage) {
        if(blocked) {
            System.out.println(name + " uzyl/a tarczy");
            shield = 25 * level;
            blocked = false;
        }
        int temp = (int)(damage - def/5*defmod);
        if (temp < 0) {
            temp = 0;
        }
        if (shield >= temp) {
            shield -= temp;
            System.out.println(name + ": tarcza zablokowala cale obrazenia");
        } else {
            temp -= shield;
            shield = 0;
            HP -= temp;
            System.out.println(name + " otrzymal/a " + temp + " obrazen");
        }
    }

    @Override
    public int getShield() {
        return shield;
    }
}
