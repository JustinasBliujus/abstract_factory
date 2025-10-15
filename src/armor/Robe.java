package armor;
import interfaces.Armor;

public class Robe implements Armor {
    private String name;
    private int defense;
    private String type;
    
    public Robe() {
        this.name = "Mage Robe";
        this.defense = 5;
        this.type = "Light";
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public int getDefense() {
        return defense;
    }
    
    @Override
    public String getType() {
        return type;
    }
    
    @Override
    public String getDescription() {
        return "An enchanted robe";
    }
}
