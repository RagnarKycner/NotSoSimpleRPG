package Kodzik.Postacie;

import java.util.List;

public abstract class Monster extends Entity {
    protected int exp;
    protected int num = 0;
    protected int[] attPattern;

    public String getName() { return this.name; }

    public int getActionNum() {
        return attPattern[num];
    }

    public void setNum(int num) {
        if(num >= attPattern.length){
            this.num = 0;
        } else {
            this.num = num;
        }
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public abstract int attack();
    public abstract void reset();
}
