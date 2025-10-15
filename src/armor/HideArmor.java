package armor;
import interfaces.Armor;

public class HideArmor implements Armor {
    private String name;
    private int defense;
    private String type;
    
    public HideArmor() {
        this.name = "Leather Hide Armor";
        this.defense = 8;
        this.type = "Medium";
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
        return "Leather armor from hides";
    }
}
