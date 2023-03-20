package Kodzik.Akcje;

import Kodzik.Postacie.Entity;

public class Rest extends Action{

    public Rest() {
        this.name = "Odpoczynek";
        this.cost = 0;
    }
    @Override
    public int execute(Entity entity) {
        if(entity.blocked) entity.blocked = false;
        entity.setMaxEnergy();
        //entity.HP += 5;
        return 0;
    }
}
