package weapons;
import interfaces.Weapon;

public class Staff implements Weapon {
    private final String name = "Staff";
    private final int damage = 12;
    private final String type = "Magic";

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
        return "An ancient wooden staff.";
    }
}


