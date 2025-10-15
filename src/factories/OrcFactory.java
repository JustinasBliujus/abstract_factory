package factories;
import interfaces.Character;
import interfaces.Weapon;
import interfaces.Armor;
import characters.OrcBerserker;
import characters.OrcShaman;
import weapons.Club;
import weapons.Totem;
import armor.HideArmor;
import armor.BoneArmor;

public class OrcFactory implements GameFactory {
    
    @Override
    public Character createWarrior(String name) {
        Weapon weapon = createWeapon();
        Armor armor = createArmor();
        return new OrcBerserker(name, weapon, armor);
    }
    
    @Override
    public Character createMage(String name) {
        Weapon weapon = createWeapon();
        Armor armor = createArmor();
        return new OrcShaman(name, weapon, armor);
    }
    
    @Override
    public Weapon createWeapon() {
        if (Math.random() < 0.5) {
            return new Club();
        } else {
            return new Totem();
        }
    }
    
    @Override
    public Armor createArmor() {
        if (Math.random() < 0.5) {
            return new HideArmor();
        } else {
            return new BoneArmor();
        }
    }
}
