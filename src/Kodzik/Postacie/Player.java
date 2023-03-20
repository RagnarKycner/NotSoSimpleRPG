package Kodzik.Postacie;

public abstract class Player extends Entity {

    protected int shield = 0;
    protected int expNeeded;
    protected int exp;
    protected int HPPerLevel;
    protected int attPerLevel;
    protected int defPerLevel;
    protected float attmod = 1;
    protected float defmod = 1;

    public float getAttmod() {
        return attmod;
    }

    public void setAttmod(float attmod) {
        this.attmod = attmod;
    }

    public float getDefmod() {
        return defmod;
    }

    public void setDefmod(float defmod) {
        this.defmod = defmod;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public abstract void reset();

    @Override
    public abstract void takeDamage(int damage);

    public void levelUp() {
        if(exp >= expNeeded) {
            level += 1;
            maxHP += HPPerLevel;
            HP = maxHP;
            att += attPerLevel;
            def += defPerLevel;
            expNeeded = 100 * level;
            exp = 0;
            setMaxEnergy();
            System.out.println("Wbiles nastepny poziom");
        }
    }

    @Override
    public void setMaxEnergy() {
        energy = maxHP / 5;
    }
}
