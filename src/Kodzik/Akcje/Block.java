package Kodzik.Akcje;

import Kodzik.Postacie.Entity;

public class Block extends Action{

    public Block(String name) {
        this.name = name;
        this.cost = 5;
    }
    @Override
    public int execute(Entity entity) {
        if(cost > entity.getEnergy()) {
            return -1;
        }
        entity.setEnergy(entity.getEnergy()-cost);
        entity.blocked = true;

        return 0;
    }
}
