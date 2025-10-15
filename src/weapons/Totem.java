package weapons;
import interfaces.Weapon;

public class Totem implements Weapon {
    private final String name = "Totem";
    private final int damage = 15;
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
        return "A mystical totem;";
    }
}


