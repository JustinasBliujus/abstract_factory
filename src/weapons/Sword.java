package weapons;
import interfaces.Weapon;

public class Sword implements Weapon {
    private final String name = "Sword";
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
        return "A sword, balanced and sharp.";
    }
}


