package armor;
import interfaces.Armor;

public class PlateArmor implements Armor {
    private String name;
    private int defense;
    private String type;
    
    public PlateArmor() {
        this.name = "Steel Plate Armor";
        this.defense = 12;
        this.type = "Heavy";
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
        return "Heavy steel armor";
    }
}
