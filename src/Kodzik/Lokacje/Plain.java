package Kodzik.Lokacje;

import Kodzik.Postacie.Monster;
import Kodzik.Postacie.Player;
import Kodzik.Postacie.Potwor.Boss;
import Kodzik.Postacie.Potwor.Normal;

public class Plain extends Location {

    public Plain(String name, int level, String[] monsterNames, String[][] actionsNames) {
        this.counter = 0;
        this.name = name;
        this.level = level;
        monsters.add(new Normal(monsterNames[0],level,100,5,10,50,actionsNames[0]));
        monsters.add(new Normal(monsterNames[1],level,125,10,8,50,actionsNames[1]));
        monsters.add(new Normal(monsterNames[2],level,75,12,5,50,actionsNames[2]));
        monsters.add(new Normal(monsterNames[3],level,100,10,10,75,actionsNames[3]));
        monsters.add(new Boss(monsterNames[4],level+1,125,15,10,100,actionsNames[4]));
    }

    @Override
    public void enter(Player player) {
        player.setAttmod(1f);
        player.setDefmod(1f);
        this.player = player;
        System.out.println("Idziesz do "+ name + " gdzie jest sporo otwartej przestrzeni.");
    }

    @Override
    public void encounter() {
        System.out.println("\nWidzisz zbilzajacego sie " + monsters.get(counter).getName());
    }

    @Override
    public int enemyRound() {
        Monster monster =  monsters.get(counter);
        System.out.println("");
        System.out.println(monster.getName() + " zamierza uzyc " + monster.actions[monster.getActionNum()].getName());
        return monster.attack();
    }
}
