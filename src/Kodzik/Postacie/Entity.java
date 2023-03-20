package Kodzik.Postacie;

import Kodzik.Akcje.Action;

public abstract class Entity {
    protected String name;
    protected int level;

    protected int maxHP;
    protected int HP;

    protected int att;
    protected int def;
    protected int energy;

    public boolean blocked = false;
    public Action[] actions = new Action[4];

    abstract public void takeDamage(int damage);

    public boolean isDead(){
        return HP <= 0;
    }

    public void setMaxEnergy() {
        energy = maxHP / 10;
    }

    public abstract void reset();

    public int getHP() {
        return HP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public String getName() {
        return name;
    }

    public int getAtt() {
        return att;
    }

    public int getLevel() {
        return level;
    }

    public int getDef() {
        return def;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
}
