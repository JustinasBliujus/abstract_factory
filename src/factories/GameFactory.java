package factories;
import interfaces.Armor;
import interfaces.Character;
import interfaces.Weapon;

public interface GameFactory {
    Character createWarrior(String name);
    Character createMage(String name);
    Weapon createWeapon();
    Armor createArmor();
}
