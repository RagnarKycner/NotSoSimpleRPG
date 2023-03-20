package Kodzik.Akcje;

import Kodzik.Postacie.Entity;

public class Weak extends Action {

    public Weak(String name) {
        this.name = name;
        this.cost = 5;
    }

    @Override
    public int execute(Entity entity) {
        if(entity.blocked) entity.blocked = false;
        if (cost > entity.getEnergy()) return -1;
        entity.setEnergy(entity.getEnergy()-cost);
        return entity.getAtt();
    }
}
