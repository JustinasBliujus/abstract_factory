package characters;
import interfaces.Armor;
import interfaces.Character;
import interfaces.Weapon;

public class HumanWarrior implements Character {
    private String name;
    private int health;
    private int attack;
    private int defense;
    private Weapon weapon;
    private Armor armor;
    
    public HumanWarrior(String name, Weapon weapon, Armor armor) {
        this.name = name;
        this.health = 120;
        this.attack = 25;
        this.defense = 15;
        this.weapon = weapon;
        this.armor = armor;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public int getHealth() {
        return health;
    }
    
    @Override
    public int getAttack() {
        return attack + (weapon.getDamage());
    }
    
    @Override
    public int getDefense() {
        return defense + (armor.getDefense());
    }
    
    @Override
    public void attack(Character target) {
        int totalDamage = getAttack();
        target.takeDamage(totalDamage);
        System.out.println(name + " attacks " + target.getName() + 
                          " with " + weapon.getName() + 
                          " for " + totalDamage + " damage!");
    }
    
    @Override
    public void takeDamage(int damage) {
        int actualDamage = Math.max(1, damage - getDefense());
        health -= actualDamage;
        System.out.println(name + " takes " + actualDamage + " damage! " +
                          "Health remaining: " + Math.max(0, health));
    }
    
    @Override
    public boolean isAlive() {
        return health > 0;
    }
    
    @Override
    public String getDescription() {
        return "A Human Warrior with " + weapon.getName() + " and " + armor.getName();
    }
}
