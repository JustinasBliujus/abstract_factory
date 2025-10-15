package factories;
import interfaces.Character;
import interfaces.Weapon;
import interfaces.Armor;
import characters.HumanWarrior;
import characters.HumanMage;   
import weapons.Sword;
import weapons.Staff;
import armor.PlateArmor;
import armor.Robe;

public class HumanFactory implements GameFactory {
    
    @Override
    public Character createWarrior(String name) {
        Weapon weapon = createWeapon();
        Armor armor = createArmor();
        return new HumanWarrior(name, weapon, armor);
    }
    
    @Override
    public Character createMage(String name) {
        Weapon weapon = createWeapon();
        Armor armor = createArmor();
        return new HumanMage(name, weapon, armor);
    }
    
    @Override
    public Weapon createWeapon() {
        if (Math.random() < 0.5) {
            return new Sword();
        } else {
            return new Staff();
        }
    }
    
    @Override
    public Armor createArmor() {
        if (Math.random() < 0.5) {
            return new PlateArmor();
        } else {
            return new Robe();
        }
    }
}
