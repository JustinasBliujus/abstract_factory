package armor;
import interfaces.Armor;

public class BoneArmor implements Armor {
    private String name;
    private int defense;
    private String type;
    
    public BoneArmor() {
        this.name = "Bone Armor";
        this.defense = 6;
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
        return "Armor made from bones";
    }
}
