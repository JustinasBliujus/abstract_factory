package weapons;
import interfaces.Weapon;

public class Club implements Weapon {
    private final String name = "Club";
    private final int damage = 14;
    private final String type = "Melee";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getDescription() {
        return "A heavy wooden club.";
    }
}


