package Kodzik.Akcje;

import Kodzik.Postacie.Entity;

public abstract class Action {
    String name;
    int cost;

    public abstract int execute(Entity attacker);

    public String getName() {
        return name;
    }
}
