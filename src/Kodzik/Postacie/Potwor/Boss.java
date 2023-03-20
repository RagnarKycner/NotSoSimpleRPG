package Kodzik.Postacie.Potwor;

import Kodzik.Akcje.Block;
import Kodzik.Akcje.Rest;
import Kodzik.Akcje.Strong;
import Kodzik.Akcje.Weak;
import Kodzik.Postacie.Monster;

public class Boss extends Monster {

    public Boss(String name, int level, int maxHP, int att, int def, int exp, String[] names) {
        this.name = name;
        this.level = level;
        this.maxHP = maxHP*level;
        this.HP = this.maxHP;
        this.att = att*level;
        this.def = def*level;
        this.exp = exp*level;
        this.energy = this.maxHP / 10;
        attPattern = new int[]{1,0,1,2,0};
        actions[0] = new Weak(names[0]);
        actions[1] = new Strong(names[1]);
        actions[2] = new Block(names[2]);
        actions[3] = new Rest();
    }

    @Override
    public void takeDamage(int damage) {
        int block = 0;
        String blString = null;
        if (blocked) {
            blString = "(zablokowane)";
            block = 10 * level;
            blocked = false;
        }
        int temp =(damage - (def + block));
        if(temp < 0) temp = 0;
        HP -= temp;
        if (blString != null){
            System.out.println(name + " otrzymal/a " + temp + blString +" obrazen i ma:" + HP +"zdrowia");
        } else {
            System.out.println(name + " otrzymal/a " + temp +" obrazen i ma:" + HP +"zdrowia");
        }
    }

    @Override
    public int attack() {
        if (num >= attPattern.length) num = 0;
        int temp = actions[attPattern[num]].execute(this);

        if (temp < 0) {
            System.out.println("Na szczescie byl zbyt zmeczony i musial odpoczac");
            actions[3].execute(this);
            this.HP += 10;
            return 0;
        }
        this.setNum(num+1);
        return temp;
    }

    @Override
    public void reset() {
        this.HP = maxHP;
        setMaxEnergy();
    }
}
