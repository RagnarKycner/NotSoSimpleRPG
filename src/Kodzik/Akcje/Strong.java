package Kodzik.Akcje;

import Kodzik.Postacie.Entity;

public class Strong extends Action {

    public Strong(String name) {
        this.name = name;
        this.cost = 10;
    }

    @Override
    public int execute(Entity entity) {
        if(entity.blocked) entity.blocked = false;
        if (cost > entity.getEnergy()) return -1;
        entity.setHP(entity.getHP()-5);
        entity.setEnergy(entity.getEnergy()-cost);
        return entity.getAtt() + 5 * entity.getLevel();
    }
}
