package Kodzik.Lokacje;

import Kodzik.Postacie.Monster;
import Kodzik.Postacie.Player;

import java.util.LinkedList;
import java.util.List;

public abstract class Location {
    protected int level;
    public int counter;
    protected String name;
    protected Player player;

    List<String> names = new LinkedList<>();
    public List<Monster> monsters = new LinkedList<>();

    public abstract void enter(Player player);
    public abstract void encounter();
    public abstract int enemyRound();
    public void reset(){
        counter = 0;
        for (Monster mon:monsters) {
            mon.reset();
        }
    }

    public String toString(){
        return name + ", lvl: " + level;
    }

    public String getName() {
        return name;
    }
}
